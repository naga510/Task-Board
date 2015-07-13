package com.src.board.service.impl;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.src.board.dao.GenericDao;
import com.src.board.domain.AuthorizationToken;
import com.src.board.enums.UserStatusEnum;
import com.src.board.service.contract.rest.v1.AuthenticatedUserToken;
import com.src.board.service.contract.rest.v1.LoginRequest;
import com.src.board.service.contract.rest.v1.User;
import com.src.board.service.contract.rest.v1.UserService;
import com.src.board.util.BasicValidator;
import com.src.board.util.UserUtil;

@Component("userServiceImpl")
public class UserServiceImpl implements UserService {

	@Autowired
	GenericDao userDao;
	
	@Autowired
	GenericDao authTokenDao;
	
	
	@Override
	public User createUser(String name, String email, String password) {
		validateUserDetails(name, email, password);
		Map<String,String> userDetails=new HashMap();
		userDetails.put("userName", email.substring(0, email.indexOf('@')));
		com.src.board.domain.User user=(com.src.board.domain.User) userDao.findSingleRecordByNamedQueryAndParams("findUserByUserName", userDetails);
		if(user!=null) {
			//TODO:Throw Exception
			return createUser(user);
		}
		com.src.board.domain.User dbUser=createDbUser(name, email, password);
		dbUser=(com.src.board.domain.User) userDao.create(dbUser);
		System.out.println("User Created");
		return createUser(dbUser);
	}
	
	@Override
	public AuthenticatedUserToken login(LoginRequest request) {
		BasicValidator.isSafeText(request.getUserName());
		Map<String,String> userDetails=new HashMap();
		userDetails.put("userName", request.getUserName());
		com.src.board.domain.User user=(com.src.board.domain.User) userDao.findSingleRecordByNamedQueryAndParams("findUserByUserName", userDetails);
		if(user==null) {
			return null;
		}
		try {
			byte[] bDigest = UserUtil.base64ToByte(user.getDigest());
			byte[] bSalt = UserUtil.base64ToByte(user.getSalt());
			byte[] bDigestNew = UserUtil.getHash(10, request.getPassword(),bSalt);
			if (Arrays.equals(bDigestNew, bDigest)) {
				AuthenticatedUserToken userToken = new AuthenticatedUserToken();
				AuthorizationToken authToken=user.getAuthorizeToken();
				userToken.setUserId(user.getUserId());
				if(authToken.hasExpired()) {
					authToken.setToken(UUID.randomUUID().toString());
					authToken.setExpirationDate(LocalDateTime.now().plusSeconds(300));
					authTokenDao.update(authToken);
					userToken.setToken(authToken.getToken());
					return userToken;
				}
				userToken.setToken(user.getAuthorizeToken().getToken());				
				return userToken;
			} else {
				return null;
			}
		}
		catch(Exception e) {
			throw new RuntimeException("Error occurred during login");
		}
		
	}
	
	private void validateUserDetails(String name, String email, String password) {
		BasicValidator.isSafeText(name);
		BasicValidator.isValidEmail(email);
		BasicValidator.isValidPassword(password);
	}
	
	private com.src.board.domain.User createDbUser(String name, String email, String password) {
		com.src.board.domain.User dbUser=new com.src.board.domain.User();
		dbUser.setEmailId(email);
		dbUser.setFullName(name);
		dbUser.setUserName(email.substring(0, email.indexOf('@')));		
		dbUser.setStatus(UserStatusEnum.NEW.getStatus());		
		try {
		SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
		byte[] bSalt = new byte[8];
        random.nextBytes(bSalt);
        byte[] bDigest = UserUtil.getHash(10,password,bSalt);
        String sDigest = UserUtil.byteToBase64(bDigest);
        String sSalt = UserUtil.byteToBase64(bSalt);
		dbUser.setDigest(sDigest);
		dbUser.setSalt(sSalt);
		AuthorizationToken authorizeToken = new AuthorizationToken();
		authorizeToken.setUser(dbUser);
		dbUser.setAuthorizeToken(authorizeToken);
		}
		catch(NoSuchAlgorithmException|UnsupportedEncodingException ex) {
			throw new RuntimeException("User Creation Failed");
		}
		return dbUser;
	}
	
	private User createUser(com.src.board.domain.User dbUser) {
		System.out.println(dbUser.getAuthorizeToken());
		User user=new User();
		user.setEmail(dbUser.getEmailId());
		user.setName(dbUser.getFullName());
		user.setStatus(UserStatusEnum.fromString(dbUser.getStatus()));
		user.setUserType("normal");
		return user;
		
	}

}

package com.src.board.service.impl;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.src.board.dao.GenericDao;
import com.src.board.domain.AuthorizationToken;
import com.src.board.domain.User;
import com.src.board.enums.UserStatusEnum;
import com.src.board.service.contract.rest.v1.AuthenticatedUserToken;
import com.src.board.service.contract.rest.v1.LoginRequest;
import com.src.board.service.contract.rest.v1.ExternalUser;
import com.src.board.service.contract.rest.v1.UserService;
import com.src.board.util.BasicValidator;
import com.src.board.util.UserUtil;

@Component("userServiceImpl")
public class UserServiceImpl implements UserService {

	@Autowired
	GenericDao<User, ?> userDao;
	
	@Autowired
	GenericDao<AuthorizationToken, ?> authTokenDao;
	
	
	@Override
	public ExternalUser createUser(String name, String email, String password) {
		validateUserDetails(name, email, password);
		User user=(User) userDao.findSingleRecordByNamedQueryAndParams("findUserByUserName", Collections.singletonMap("userName", email.substring(0, email.indexOf('@'))));
		if(user==null) {
			//TODO:Throw Exception
			return null;
		}
		User dbUser=createDbUser(name, email, password);
		dbUser=(User) userDao.create(dbUser);
		return new ExternalUser(dbUser);
	}
	
	@Override
	public AuthenticatedUserToken login(LoginRequest request) {
		BasicValidator.isSafeText(request.getUserName());
		User user=(User) userDao.findSingleRecordByNamedQueryAndParams("findUserByUserName", Collections.singletonMap("userName", request.getUserName()));
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
	
	private User createDbUser(String name, String email, String password) {
		User dbUser=new User();
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

}

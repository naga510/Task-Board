package com.src.board.service.impl;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.src.board.dao.GenericDao;
import com.src.board.enums.UserStatusEnum;
import com.src.board.service.contract.rest.v1.User;
import com.src.board.service.contract.rest.v1.UserService;
import com.src.board.util.BasicValidator;
import com.src.board.util.UserUtil;

@Component("userServiceImpl")
public class UserServiceImpl implements UserService {

	@Autowired
	GenericDao userDao;
	
	
	@Override
	public User createUser(String name, String email, String password) {		
		System.out.println("Start Creating DB user");
		validateUserDetails(name, email, password);
		com.src.board.domain.User dbUser=createDbUser(name, email, password);
		System.out.println("dbUser:"+dbUser);
		userDao.create(dbUser);
		return createUser(dbUser);
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
		}
		catch(NoSuchAlgorithmException|UnsupportedEncodingException ex) {
			throw new RuntimeException("User Creation Failed");
		}
		return dbUser;
	}
	
	private User createUser(com.src.board.domain.User dbUser) {
		User user=new User();
		user.setEmail(dbUser.getEmailId());
		user.setName(dbUser.getFullName());
		user.setStatus(UserStatusEnum.fromString(dbUser.getStatus()));
		user.setUserType("normal");
		return user;
		
	}

}

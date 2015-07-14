package com.src.board.service.impl;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.src.board.dao.GenericDao;
import com.src.board.domain.AuthorizationToken;
import com.src.board.service.contract.rest.v1.AuthorizationRequestContext;
import com.src.board.service.contract.rest.v1.AuthorizationService;
import com.src.board.service.contract.rest.v1.ExternalUser;

@Component("authServiceImpl")
public class AuthorizationServiceImpl implements AuthorizationService {

	@Autowired
	GenericDao<AuthorizationToken, ?> authTokenDao;
	
	@Override
	public ExternalUser authorize(
			AuthorizationRequestContext authorizationRequestContext) {
		String token = authorizationRequestContext.getAuthorizationToken();		
        if(token == null) {
            return null;
        }
        AuthorizationToken authToken=authTokenDao.findSingleRecordByNamedQueryAndParams("findUserByAuthToken", Collections.singletonMap("token", token));
        if(authToken==null|| authToken.hasExpired()) {
        	return null;
        }
		return new ExternalUser(authToken.getUser());
	}

}

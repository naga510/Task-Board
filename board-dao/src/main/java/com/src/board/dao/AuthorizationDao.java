package com.src.board.dao;

import org.springframework.stereotype.Component;

import com.src.board.domain.AuthorizationToken;

@Component("authTokenDao")
public class AuthorizationDao extends GenericDaoJpaImpl<AuthorizationToken, String> implements GenericDao<AuthorizationToken, String> {

	public AuthorizationDao() {
		this.entityClass = AuthorizationToken.class;
	}
}

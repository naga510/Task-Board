package com.src.board.dao;


import org.springframework.stereotype.Component;

import com.src.board.domain.User;

@Component("userDao")
public class UserDao extends GenericDaoJpaImpl<User, String> implements GenericDao<User, String>{

	public UserDao() {
		this.entityClass=User.class;		
	}
	
}

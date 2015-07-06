package com.src.board.dao;

import org.springframework.stereotype.Component;

import com.src.board.domain.Item;


@Component("itemDao")
public class ItemDao extends GenericDaoJpaImpl<Item, String> implements GenericDao<Item, String> {
	
	public ItemDao() {
		this.entityClass=Item.class;		
	}
	
}

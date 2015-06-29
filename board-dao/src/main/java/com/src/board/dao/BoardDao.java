package com.src.board.dao;

import org.springframework.stereotype.Component;

import com.src.board.domain.Board;

@Component("boardDao")
public class BoardDao extends GenericDaoJpaImpl<Board, String> implements GenericDao<Board, String> {

	
	public BoardDao() {
		this.entityClass=Board.class;		
	}
	
	
}

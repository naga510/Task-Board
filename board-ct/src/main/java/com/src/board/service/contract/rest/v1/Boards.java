package com.src.board.service.contract.rest.v1;

import java.io.Serializable;
import java.util.List;

public class Boards implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8579784017179584418L;
	private List<BoardOutput> boards;

	public List<BoardOutput> getBoards() {
		return boards;
	}

	public void setBoards(List<BoardOutput> boards) {
		this.boards = boards;
	}
	
	
}

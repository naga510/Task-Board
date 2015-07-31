package com.src.board.service.contract.rest.v1;

import java.util.List;

public interface BoardService {

	public List<BoardOutput> listBoardsForUser(String userId);
	
	public BoardOutput getBoard(String boardId);
	
	public BoardOutput createBoard(String boardName, String userId);
}

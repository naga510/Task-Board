package com.src.board.service.impl;

import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.src.board.dao.GenericDao;
import com.src.board.domain.Board;
import com.src.board.enums.BoardStatusEnum;
import com.src.board.service.contract.rest.v1.BoardOutput;
import com.src.board.service.contract.rest.v1.BoardService;

@Service("boardServiceImpl")
public class BoardServiceImpl implements BoardService {

	@Autowired
	private GenericDao boardDao;
	
	private Function<Board, BoardOutput> boardOutputFunction= board->toBoardOutput(board);
	
	@Override
	public List<BoardOutput> listBoardsForUser(String userId) {
		List<Board> boards=boardDao.findByNamedQueryAndParams("findBoardsByUserId", Collections.singletonMap("userId", userId));		
		return createBoardOutput(boards);
	}
	
	@Override
	public BoardOutput createBoard(String boardName, String userId) {
		Board board=new Board();
		board.setName(boardName);
		board.setOwner(userId);
		board.setStatus(BoardStatusEnum.ACTIVE.getStatus());
		boardDao.create(board);
		return toBoardOutput(board);
	}

	
	private List<BoardOutput> createBoardOutput(List<Board> boards) {
		if(null==boards)
			return Collections.emptyList();
		return boards.stream().map(boardOutputFunction).collect(Collectors.<BoardOutput>toList());					
	}
	
	private BoardOutput toBoardOutput(Board board) {
		BoardOutput output=new BoardOutput();
		output.setBoardId(board.getBoardId());
		output.setBoardName(board.getName());
		output.setStatus(BoardStatusEnum.fromString(board.getStatus()));
		return output;
	}

}

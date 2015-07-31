package com.src.board.service.impl;

import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.src.board.dao.GenericDao;
import com.src.board.domain.Board;
import com.src.board.domain.Item;
import com.src.board.enums.BoardStatusEnum;
import com.src.board.service.contract.rest.v1.BoardOutput;
import com.src.board.service.contract.rest.v1.BoardService;
import com.src.board.service.contract.rest.v1.ItemOutput;

@Service("boardServiceImpl")
public class BoardServiceImpl implements BoardService {

	@Autowired
	private GenericDao<Board, String> boardDao;
	
	
	private Function<Board, BoardOutput> boardOutputFunction= board->toBoardOutput(board, null);
	private Function<Item, ItemOutput> itemOutputFunction=item->toItemOutput(item);
	
	@Override
	public List<BoardOutput> listBoardsForUser(String userId)
	{
		List<Board> boards=boardDao.findByNamedQueryAndParams("findBoardsByUserId", Collections.singletonMap("userId", userId));		
		return createBoardOutput(boards);
	}
	
	@Override
	@Transactional
	public BoardOutput getBoard(String boardId)
	{
		Board board=boardDao.find(boardId);
		BoardOutput boardOutput=new BoardOutput();
		if(board==null) {
			return null;
		}		
		System.out.println(board.getMembers());
		boardOutput = toBoardOutput(board, board.getItems());
		return boardOutput;
	}
	
	@Override
	public BoardOutput createBoard(String boardName, String userId)
	{
		Board board=new Board();
		board.setName(boardName);
		board.setOwner(userId);
		board.setStatus(BoardStatusEnum.ACTIVE.getStatus());
		boardDao.create(board);
		return toBoardOutput(board,null);
	}

	
	private List<BoardOutput> createBoardOutput(List<Board> boards) {
		if(null==boards)
			return Collections.emptyList();
		return boards.stream().map(boardOutputFunction).collect(Collectors.<BoardOutput>toList());					
	}
	
	private BoardOutput toBoardOutput(Board board, List<Item> items) {		
		BoardOutput output=new BoardOutput();
		output.setBoardId(board.getBoardId());
		output.setBoardName(board.getName());
		output.setStatus(BoardStatusEnum.fromString(board.getStatus()));
		if(items!=null) {
			output.setItems(items.stream().map(itemOutputFunction).collect(Collectors.<ItemOutput>toList()));
		}
		return output;
	}
	
	private ItemOutput toItemOutput(Item item) {
		ItemOutput itemOutput=new ItemOutput();
		itemOutput.setId(item.getItemId());
		itemOutput.setTitle(item.getTitle());
		itemOutput.setDescription(item.getDescription());
		return itemOutput;
	}
}

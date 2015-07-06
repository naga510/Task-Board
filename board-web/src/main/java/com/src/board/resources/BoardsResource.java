package com.src.board.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.src.board.enums.exception.BoardExceptionEnum;
import com.src.board.service.contract.rest.v1.BoardService;

@Component
@Path("/boards")
public class BoardsResource extends BaseResource{

	@Autowired
	private BoardService boardServiceImpl;
		
	@GET
	@Path("/{userId}")
	public Response listBoardsForUser(@PathParam("userId") String userId) throws Exception {
		if(userId==null) {
			throw new Exception(BoardExceptionEnum.INVALID_USER_ID.getErrorMessage());
		}
		return buildResponse(boardServiceImpl.listBoardsForUser(userId));
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Response createBoard(@FormParam("name") String boardName, @FormParam("userId") String userId) throws Exception {
		if(userId==null|boardName==null) {
			throw new Exception(BoardExceptionEnum.INVALID_USER_ID.getErrorMessage());
		}
		return buildResponse(boardServiceImpl.createBoard(boardName, userId));
	}
	
}

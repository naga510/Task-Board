package com.src.board.resources;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.src.board.enums.exception.BoardExceptionEnum;
import com.src.board.service.contract.rest.v1.BoardService;
import com.src.board.service.contract.rest.v1.Boards;
import com.src.board.service.contract.rest.v1.ExternalUser;

@Component
@Path("/board")
public class BoardsResource extends BaseResource{

	@Autowired
	private BoardService boardServiceImpl;
	
		
	@GET
	@RolesAllowed({"authenticated"})
	public Response listBoardsForUser(@Context SecurityContext sc) throws Exception {
		ExternalUser user=(ExternalUser)sc.getUserPrincipal();
		if(user==null) {
			throw new Exception(BoardExceptionEnum.INVALID_USER_ID.getErrorMessage());
		}
		Boards boards=new Boards();
		boards.setBoards(boardServiceImpl.listBoardsForUser(user.getId()));
		return buildResponse(boards);
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

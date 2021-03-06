package com.src.board.resources;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.src.board.enums.exception.ExceptionEnum;
import com.src.board.service.contract.rest.v1.BoardService;
import com.src.board.service.contract.rest.v1.Boards;
import com.src.board.service.contract.rest.v1.ExternalUser;
import com.src.board.service.contract.rest.v1.exception.ValidationException;

@Component
@Path("/boards")
public class BoardsResource extends BaseResource{

	@Autowired
	private BoardService boardServiceImpl;
	
		
	@GET
	@RolesAllowed({"authenticated"})
	public Response listBoardsForUser(@Context SecurityContext sc) throws Exception {
		ExternalUser user=(ExternalUser)sc.getUserPrincipal();			
		Boards boards=new Boards();
		boards.setBoards(boardServiceImpl.listBoardsForUser(user.getId()));
		return buildResponse(boards);
	}
	
	@GET
	@Path("/{boardid}")
	@RolesAllowed({"authenticated"})
	public Response getBoard(@PathParam("boardid") String boardId, @Context SecurityContext sc) {
		if(boardId==null||boardId.isEmpty()) {
			throw new ValidationException(
					ExceptionEnum.INVALID_BOARD_ID.getErrorMessage(),
					ExceptionEnum.INVALID_BOARD_ID.getErrorId());
		}
		return buildResponse(boardServiceImpl.getBoard(boardId));
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@RolesAllowed({"authenticated"})
	public Response createBoard(@FormParam("name") String boardName, @Context SecurityContext sc) throws Exception {
		ExternalUser user=(ExternalUser)sc.getUserPrincipal();
		if(boardName==null) {
			throw new Exception(ExceptionEnum.INVALID_BOARD_NAME.getErrorMessage());
		}
		return buildResponse(boardServiceImpl.createBoard(boardName, user.getId()));
	}
	
}

package com.src.board.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.src.board.enums.exception.ExceptionEnum;
import com.src.board.service.contract.rest.v1.AuthenticatedUserToken;
import com.src.board.service.contract.rest.v1.ExternalUser;
import com.src.board.service.contract.rest.v1.LoginRequest;
import com.src.board.service.contract.rest.v1.SignUpRequest;
import com.src.board.service.contract.rest.v1.UserService;
import com.src.board.validator.BasicValidator;

@Component
@Path("/user")
public class UserResource extends BaseResource{

	@Autowired
	UserService userServiceImpl;
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createUser(SignUpRequest request) {
		BasicValidator.isSafeText(request.getName(), ExceptionEnum.INVALID_USER_NAME.getErrorId(), ExceptionEnum.INVALID_USER_NAME.getErrorMessage());
		ExternalUser user=userServiceImpl.createUser(request.getName(), request.getEmail(), request.getPassword());
		return buildResponse(user);
	}
	
	@POST
	@Path("/login")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response login(LoginRequest request) {
		AuthenticatedUserToken token=userServiceImpl.login(request);
		return buildResponse(token);
	}
}

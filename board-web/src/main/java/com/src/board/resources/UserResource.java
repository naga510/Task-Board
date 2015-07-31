package com.src.board.resources;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.src.board.enums.exception.ExceptionEnum;
import com.src.board.service.contract.rest.v1.AuthenticatedUserToken;
import com.src.board.service.contract.rest.v1.ExternalUser;
import com.src.board.service.contract.rest.v1.LoginRequest;
import com.src.board.service.contract.rest.v1.SignUpRequest;
import com.src.board.service.contract.rest.v1.UserService;
import com.src.board.service.contract.rest.v1.exception.AuthenticationException;
import com.src.board.validator.BasicValidator;

@Component
@Path("/users")
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
	
	
	@GET
	@RolesAllowed({"authenticated"})
	public Response getCurrentUser(@Context SecurityContext sc) {
		ExternalUser user=(ExternalUser)sc.getUserPrincipal();
		System.out.println(user);
		return buildResponse(user);
	}
	
	@GET
	@Path("/validate")
	public Response validateToken(@Context SecurityContext sc) {
		ExternalUser user=(ExternalUser)sc.getUserPrincipal();
		if(user==null) {
			throw new AuthenticationException(ExceptionEnum.INALID_AUTH_TOKEN.getErrorMessage(),ExceptionEnum.INALID_AUTH_TOKEN.getErrorId());
		}
		return buildResponse(user);
	}
	
	@GET
	@Path("/logout")
	public Response logOut() {
		return buildResponse("OK");
	}
}

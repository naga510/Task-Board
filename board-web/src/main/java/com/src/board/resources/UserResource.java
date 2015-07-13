package com.src.board.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.src.board.service.contract.rest.v1.AuthenticatedUserToken;
import com.src.board.service.contract.rest.v1.LoginRequest;
import com.src.board.service.contract.rest.v1.User;
import com.src.board.service.contract.rest.v1.UserService;

@Component
@Path("/user")
public class UserResource extends BaseResource{

	@Autowired
	UserService userServiceImpl;
	
	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Response createUser(@FormParam("name") String name, @FormParam("email") String email, @FormParam("password") String password) {
		User user=userServiceImpl.createUser(name, email, password);
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

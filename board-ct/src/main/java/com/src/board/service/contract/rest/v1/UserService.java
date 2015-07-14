package com.src.board.service.contract.rest.v1;

public interface UserService {

	public ExternalUser createUser(String name, String email, String password);
	
	public AuthenticatedUserToken login(LoginRequest request);
}

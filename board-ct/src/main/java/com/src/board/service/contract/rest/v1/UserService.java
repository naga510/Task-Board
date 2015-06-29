package com.src.board.service.contract.rest.v1;

public interface UserService {

	public User createUser(String name, String email, String password);
}

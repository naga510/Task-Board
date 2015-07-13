package com.src.board.service.contract.rest.v1;

public class AuthenticatedUserToken {

	private String userId;
	private String token;
	 
	public AuthenticatedUserToken() {
	}
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	
	
}

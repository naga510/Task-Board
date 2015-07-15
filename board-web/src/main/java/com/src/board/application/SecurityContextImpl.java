package com.src.board.application;

import java.security.Principal;

import javax.ws.rs.core.SecurityContext;

import com.src.board.service.contract.rest.v1.ExternalUser;

public class SecurityContextImpl implements SecurityContext {
	
	 private final ExternalUser user;
	 
	 public SecurityContextImpl(ExternalUser user) {
	        this.user = user;
	    }

	@Override
	public Principal getUserPrincipal() {
		return user;
	}

	@Override
	public boolean isUserInRole(String role) {
		return true;
	}

	@Override
	public boolean isSecure() {		
		return false;
	}

	@Override
	public String getAuthenticationScheme() {
		return SecurityContext.BASIC_AUTH;
	}

}

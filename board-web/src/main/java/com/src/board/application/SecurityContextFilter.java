package com.src.board.application;

import java.io.IOException;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.PreMatching;
import javax.ws.rs.ext.Provider;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.src.board.service.contract.rest.v1.AuthorizationRequestContext;
import com.src.board.service.contract.rest.v1.AuthorizationService;
import com.src.board.service.contract.rest.v1.ExternalUser;

@Provider
@PreMatching
public class SecurityContextFilter implements ContainerRequestFilter {
	
	private static final Logger LOG = Logger.getLogger(SecurityContextFilter.class);
	private static final String HEADER_AUTHORIZATION = "auth-token";
	
	@Autowired
	AuthorizationService authServiceImpl;

	@Override
	public void filter(ContainerRequestContext requestContext)
			throws IOException {	
		 String authToken = requestContext.getHeaderString(HEADER_AUTHORIZATION);
		AuthorizationRequestContext authContext=new AuthorizationRequestContext(requestContext.getUriInfo().getPath(), requestContext.getMethod(), authToken);
		ExternalUser user=authServiceImpl.authorize(authContext);
		requestContext.setSecurityContext(new SecurityContextImpl(user));
	}
	
}

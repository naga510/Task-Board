package com.src.board.application;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.Provider;

import com.src.board.service.contract.rest.v1.ErrorResponse;

@Provider
public class CustomRespnseFilter implements ContainerResponseFilter{

	@Override
	public void filter(ContainerRequestContext requestContext,
			ContainerResponseContext responseContext)  {
		if(requestContext.getMethod().equalsIgnoreCase("POST") && !(responseContext.getEntity() instanceof ErrorResponse))
		responseContext.setStatus(Status.CREATED.getStatusCode());
	}

}

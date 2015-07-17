package com.src.board.service.contract.rest.v1.exception;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.src.board.service.contract.rest.v1.ErrorResponse;


public class BaseAPIException extends WebApplicationException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3050258931358415020L;
	private final int status;
	private final String errorMessage;
	private final String errorCode;
	
	public BaseAPIException(int status, String errorMessage, String errorCode) {
		super();
		this.status = status;
		this.errorMessage = errorMessage;
		this.errorCode = errorCode;
	}
	
	 @Override
	    public Response getResponse() {
	        return Response.status(status).type(MediaType.APPLICATION_JSON_TYPE).entity(new ErrorResponse(errorCode, errorMessage)).build();
	    }

}

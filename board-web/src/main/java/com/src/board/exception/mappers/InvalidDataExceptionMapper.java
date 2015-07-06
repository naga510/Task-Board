package com.src.board.exception.mappers;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class InvalidDataExceptionMapper implements ExceptionMapper<Throwable>{

	public Response toResponse(Throwable exception) {
		Response.Status httpStatus = Response.Status.BAD_REQUEST;
		return Response.status(httpStatus).entity("Invalid Data").build();
	}

}

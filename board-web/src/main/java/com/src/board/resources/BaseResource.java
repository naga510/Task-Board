package com.src.board.resources;

import java.io.IOException;

import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JSR310Module;

@Produces(MediaType.APPLICATION_JSON)
public class BaseResource {
	
	public <T> Response buildResponse(T result) {		
		return Response.status(Status.OK).entity(result).build();
	}
	
	public  <T> T convertJsonToObject(String jsonString, Class<T> className) {
		ObjectMapper mapper=new ObjectMapper();
		try {
			mapper.registerModule(new JSR310Module());
			return mapper.readValue(jsonString, className);
		} catch (IOException e) {			
			return null;
		}
	}
}

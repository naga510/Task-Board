package com.src.board.application;

import javax.ws.rs.ext.ContextResolver;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JSR310Module;
import com.fasterxml.jackson.module.jaxb.JaxbAnnotationIntrospector;

public class MyJacksonJsonProvider implements ContextResolver<ObjectMapper> {

	private static final ObjectMapper MAPPER = new ObjectMapper();
	
	static {		
		MAPPER.setAnnotationIntrospector(new JaxbAnnotationIntrospector(MAPPER.getTypeFactory()));
		MAPPER.registerModule(new JSR310Module());
		MAPPER.configure(SerializationFeature.WRAP_ROOT_VALUE, true);
	}
	
	@Override
	public ObjectMapper getContext(Class<?> type) {
		return MAPPER;
	}
	
	

}

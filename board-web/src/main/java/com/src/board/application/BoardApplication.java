package com.src.board.application;

import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.filter.RolesAllowedDynamicFeature;
import org.glassfish.jersey.server.spring.scope.RequestContextFilter;

import com.src.board.resources.BoardsResource;
import com.src.board.resources.ItemResource;
import com.src.board.resources.UserResource;

public class BoardApplication extends ResourceConfig{
	
	public BoardApplication() {
		register(RequestContextFilter.class);
		register(BoardsResource.class);
		register(UserResource.class);
		register(ItemResource.class);
		register(MyJacksonJsonProvider.class);
		register(SecurityContextFilter.class);
		register(CustomRespnseFilter.class);
		register(RolesAllowedDynamicFeature.class);
		//register(InvalidDataExceptionMapper.class);
		register(JacksonFeature.class);
	}
}

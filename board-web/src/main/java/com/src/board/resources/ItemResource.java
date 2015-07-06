package com.src.board.resources;

import java.time.LocalDateTime;
import java.time.ZoneId;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.src.board.service.contract.rest.v1.ItemOutput;
import com.src.board.service.contract.rest.v1.ItemService;

@Component
@Path("/item")
public class ItemResource extends BaseResource {

	@Autowired
	ItemService itemServiceImpl;
	
	@POST
	public Response createItem(@FormParam("itemString") String itemJsonString)
	{
		ItemOutput item= convertJsonToObject(itemJsonString, ItemOutput.class);
		return buildResponse(itemServiceImpl.createItem(item));
	}
	
	@GET
	@Path("/{itemId}")
	public Response getItem(@PathParam("itemId") String itemId)
	{
		ItemOutput item=itemServiceImpl.getItem(itemId);
		return buildResponse(item);
	}
	
}

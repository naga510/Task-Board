package com.src.board.service.contract.rest.v1;


public interface ItemService {

	public ItemOutput createItem(ItemOutput item);
	
	public ItemOutput getItem(String itemId);
}

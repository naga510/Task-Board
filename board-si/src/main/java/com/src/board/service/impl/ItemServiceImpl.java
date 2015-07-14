package com.src.board.service.impl;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.src.board.dao.GenericDao;
import com.src.board.domain.Board;
import com.src.board.domain.Item;
import com.src.board.enums.CategoryEnum;
import com.src.board.service.contract.rest.v1.ItemOutput;
import com.src.board.service.contract.rest.v1.ItemService;

@Component("itemServiceImpl")
public class ItemServiceImpl implements ItemService {
	
	@Autowired
	private GenericDao<Board, String> boardDao;
	
	@Autowired
	private GenericDao<Item, String> itemDao;

	@Override
	public ItemOutput createItem(ItemOutput item) {
		Item dbItem = createDbItem(item);
		dbItem = (Item) itemDao.create(dbItem);		
		return createItemOutput(dbItem);
	}
	
	@Override
	public ItemOutput getItem(String itemId) {
		Item dbItem = (Item) itemDao.find(itemId);
		return createItemOutput(dbItem);
	}
	
	private ItemOutput createItemOutput(Item dbItem) {
		ItemOutput itemOutput=new ItemOutput();
		itemOutput.setTitle(dbItem.getTitle());
		itemOutput.setDescription(dbItem.getDescription());
		itemOutput.setBoardId(dbItem.getBoard().getBoardId());
		//itemOutput.setAttachments(attachments);
		itemOutput.setCategory(CategoryEnum.fromString(dbItem.getCategory()));
		itemOutput.setColor(dbItem.getColor());
		itemOutput.setDueDate(dbItem.getDueDate().toEpochSecond(ZoneOffset.UTC));
		itemOutput.setPoints(dbItem.getPoints());
		itemOutput.setId(dbItem.getItemId());
		//itemOutput.setStatus(ItemStatusEnum.fromString(dbItem.get));
		return itemOutput;
	}
	
	private Item createDbItem(ItemOutput item) {
		Item dbItem=new Item();
		dbItem.setTitle(item.getTitle());
		dbItem.setBoard((Board)boardDao.find(item.getBoardId()));
		dbItem.setCategory(item.getCategory().getStatus());
		dbItem.setColor(item.getColor());
		dbItem.setDescription(item.getDescription());
		dbItem.setDueDate(LocalDateTime.ofEpochSecond(item.getDueDate(), 0, ZoneOffset.UTC));
		dbItem.setPoints(item.getPoints());
		return dbItem;
	}

}

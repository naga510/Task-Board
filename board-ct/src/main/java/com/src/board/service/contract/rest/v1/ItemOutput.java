package com.src.board.service.contract.rest.v1;

import java.io.Serializable;
import java.time.LocalDate;

import javax.xml.bind.annotation.XmlRootElement;

import com.src.board.enums.CategoryEnum;
import com.src.board.enums.ItemStatusEnum;

@XmlRootElement(name="item")
public class ItemOutput implements Serializable{

	private String id;
	private String title;
	private String description;
	private CategoryEnum category;
	private ItemStatusEnum status;
	private String color;
	private Long dueDate;
	private String points;
	private AttachmentOutput attachments;
	private String boardId;
	
	public ItemOutput() {
	}
	//private 
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public String getBoardId() {
		return boardId;
	}
	public void setBoardId(String boardId) {
		this.boardId = boardId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public CategoryEnum getCategory() {
		return category;
	}
	public void setCategory(CategoryEnum category) {
		this.category = category;
	}
	public ItemStatusEnum getStatus() {
		return status;
	}
	public void setStatus(ItemStatusEnum status) {
		this.status = status;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	
	public Long getDueDate() {
		return dueDate;
	}
	public void setDueDate(Long dueDate) {
		this.dueDate = dueDate;
	}
	public String getPoints() {
		return points;
	}
	public void setPoints(String points) {
		this.points = points;
	}
	public AttachmentOutput getAttachments() {
		return attachments;
	}
	public void setAttachments(AttachmentOutput attachments) {
		this.attachments = attachments;
	}
	
	
}

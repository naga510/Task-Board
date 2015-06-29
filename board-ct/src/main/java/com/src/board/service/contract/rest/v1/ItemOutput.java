package com.src.board.service.contract.rest.v1;

import java.util.Date;

import com.src.board.enums.CategoryEnum;

public class ItemOutput {

	private String id;
	private String title;
	private String description;
	private CategoryEnum category;
	private String status;
	private String color;
	private Date dueDate;
	private String points;
	private AttachmentOutput attachments;
	//private 
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public Date getDueDate() {
		return dueDate;
	}
	public void setDueDate(Date dueDate) {
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

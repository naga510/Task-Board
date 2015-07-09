package com.src.board.domain;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;


@Entity
@Table(name="ITEMS")
@NamedQuery(name="findItemsByBoardId", query="from Item item where item.board.boardId = :boardId")
public class Item implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5636347127509901234L;
	private String itemId;
	private String title;
	private String description;
	//private Member assignedTo;
	private String category;
	private String color;
	private LocalDateTime dueDate;
	private String points;
	private List<Attachment> attachments;
	private List<Comment> comments;
	private List<Activity> activities;
	private Board board;
	
	@PrePersist
	public void prePersist(){
		itemId = UUID.randomUUID().toString();
	}
	
	@Id	
	@Column(name="ITEM_ID", nullable=false, unique=true)
	public String getItemId() {
		return itemId;
	}
	public void setItemId(String id) {
		this.itemId = id;
	}
	
	@Column(name="ITEM_TITLE", nullable=false)
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	@Column(name="ITEM_DESC")
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
//	public Member getAssignedTo() {
//		return assignedTo;
//	}
//	public void setAssignedTo(Member assignedTo) {
//		this.assignedTo = assignedTo;
//	}
	
	@Column(name="ITEM_CATEGORY", nullable=false)
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	
	@Column(name="ITEM_COLOR", nullable=false)
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	
	@Column(name="ITEM_DUEDATE", nullable=false)
	public LocalDateTime getDueDate() {
		return dueDate;
	}
	public void setDueDate(LocalDateTime dueDate) {
		this.dueDate = dueDate;
	}
	
	@Column(name="ITEM_POINTS", nullable=false)
	public String getPoints() {
		return points;
	}
	public void setPoints(String points) {
		this.points = points;
	}
	
	@OneToMany(cascade=CascadeType.ALL, mappedBy="item")
	public List<Attachment> getAttachments() {
		return attachments;
	}
	public void setAttachments(List<Attachment> attachments) {
		this.attachments = attachments;
	}
	
	@OneToMany(cascade=CascadeType.ALL, mappedBy="item")	
	public List<Comment> getComments() {
		return comments;
	}
	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}
	
	@OneToMany(cascade=CascadeType.ALL, mappedBy="item")	
	public List<Activity> getActivities() {
		return activities;
	}
	public void setActivities(List<Activity> activities) {
		this.activities = activities;
	}
	
	@ManyToOne
    @JoinColumn(name="BOARD_ID", nullable=false)
	public Board getBoard() {
		return board;
	}
	public void setBoard(Board board) {
		this.board = board;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((activities == null) ? 0 : activities.hashCode());
		result = prime * result
				+ ((attachments == null) ? 0 : attachments.hashCode());
		result = prime * result + ((board == null) ? 0 : board.hashCode());
		result = prime * result
				+ ((category == null) ? 0 : category.hashCode());
		result = prime * result + ((color == null) ? 0 : color.hashCode());
		result = prime * result
				+ ((comments == null) ? 0 : comments.hashCode());
		result = prime * result
				+ ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((dueDate == null) ? 0 : dueDate.hashCode());
		result = prime * result + ((itemId == null) ? 0 : itemId.hashCode());
		result = prime * result + ((points == null) ? 0 : points.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Item))
			return false;
		Item other = (Item) obj;
		if (activities == null) {
			if (other.activities != null)
				return false;
		} else if (!activities.equals(other.activities))
			return false;
		if (attachments == null) {
			if (other.attachments != null)
				return false;
		} else if (!attachments.equals(other.attachments))
			return false;
		if (board == null) {
			if (other.board != null)
				return false;
		} else if (!board.equals(other.board))
			return false;
		if (category == null) {
			if (other.category != null)
				return false;
		} else if (!category.equals(other.category))
			return false;
		if (color == null) {
			if (other.color != null)
				return false;
		} else if (!color.equals(other.color))
			return false;
		if (comments == null) {
			if (other.comments != null)
				return false;
		} else if (!comments.equals(other.comments))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (dueDate == null) {
			if (other.dueDate != null)
				return false;
		} else if (!dueDate.equals(other.dueDate))
			return false;
		if (itemId == null) {
			if (other.itemId != null)
				return false;
		} else if (!itemId.equals(other.itemId))
			return false;
		if (points == null) {
			if (other.points != null)
				return false;
		} else if (!points.equals(other.points))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Item [itemId=" + itemId + ", title=" + title + ", description="
				+ description + ", category=" + category + ", color=" + color
				+ ", dueDate=" + dueDate + ", points=" + points
				+ ", attachments=" + attachments + ", comments=" + comments
				+ ", activities=" + activities + ", board=" + board + "]";
	}
	
	
	
	
}

package com.src.board.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="COMMENTS")
public class Comment implements Serializable{

	private Long commentId;
	private Long commentOwner;
	private Item item;
	private String comment;
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="my_seq")
	@SequenceGenerator(name="my_seq", sequenceName="COMMENT_ID_SEQ", initialValue=1, allocationSize=1)
	@Column(name="COMMENT_ID", nullable=false, unique=true)
	public Long getCommentId() {
		return commentId;
	}
	public void setCommentId(Long commentId) {
		this.commentId = commentId;
	}
	
	@Column(name="COMMENT_OWNER_ID", nullable=false)
	public Long getMember() {
		return commentOwner;
	}
	public void setMember(Long member) {
		this.commentOwner = member;
	}
	
	@ManyToOne
    @JoinColumn(name="COMMENT_ITEM_ID", nullable=false)	
	public Item getItem() {
		return item;
	}
	public void setItem(Item item) {
		this.item = item;
	}
	
	@Column(name="COMMENT_TEXT", nullable=false)
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((comment == null) ? 0 : comment.hashCode());
		result = prime * result + ((commentId == null) ? 0 : commentId.hashCode());
		result = prime * result + ((item == null) ? 0 : item.hashCode());
		result = prime * result + ((commentOwner == null) ? 0 : commentOwner.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Comment))
			return false;
		Comment other = (Comment) obj;
		if (comment == null) {
			if (other.comment != null)
				return false;
		} else if (!comment.equals(other.comment))
			return false;
		if (commentId == null) {
			if (other.commentId != null)
				return false;
		} else if (!commentId.equals(other.commentId))
			return false;
		if (item == null) {
			if (other.item != null)
				return false;
		} else if (!item.equals(other.item))
			return false;
		if (commentOwner == null) {
			if (other.commentOwner != null)
				return false;
		} else if (!commentOwner.equals(other.commentOwner))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Comment [commentId=" + commentId + ", commentOwner="
				+ commentOwner + ", item=" + item + ", comment=" + comment
				+ "]";
	}
	
	
}

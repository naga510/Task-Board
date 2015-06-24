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
@Table(name="ATTACHMENTS")
public class Attachment implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4122470936162414329L;
	private Long attachmentId;
	private Item item;
	private String title;
	private String fileName;
	private String systemFileName;
	private Long attachmentOwner;
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="attachment_seq")
	@SequenceGenerator(name="attachment_seq", sequenceName="ATTACHMENT_ID_SEQ", initialValue=1, allocationSize=1)
	@Column(name="ATTACHMENT_ID", nullable=false, unique=true)
	public Long getAttachmentId() {
		return attachmentId;
	}
	public void setAttachmentId(Long attachmentId) {
		this.attachmentId = attachmentId;
	}
	
	@ManyToOne
	@JoinColumn(name="ATTACHMENT_ITEM_ID", nullable=false)	
	public Item getItem() {
		return item;
	}
	public void setItem(Item item) {
		this.item = item;
	}
	
	@Column(name="ATTACHMENT_TITLE")
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	@Column(name="ATTACHMENT_PATH" , nullable=false)
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	@Column(name="ATTACHMENT_OWNER", nullable=false)
	public Long getAttachmentOwner() {
		return attachmentOwner;
	}
	public void setAttachmentOwner(Long attachmentOwner) {
		this.attachmentOwner = attachmentOwner;
	}
	
	@Column(name="ATTACHMENT_SYSTEM_FILENAME", nullable=false)
	public String getSystemFileName() {
		return systemFileName;
	}
	public void setSystemFileName(String systemFileName) {
		this.systemFileName = systemFileName;
	}
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((attachmentOwner == null) ? 0 : attachmentOwner.hashCode());
		result = prime * result + ((attachmentId == null) ? 0 : attachmentId.hashCode());
		result = prime * result + ((item == null) ? 0 : item.hashCode());
		result = prime * result
				+ ((fileName == null) ? 0 : fileName.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Attachment))
			return false;
		Attachment other = (Attachment) obj;
		if (attachmentOwner == null) {
			if (other.attachmentOwner != null)
				return false;
		} else if (!attachmentOwner.equals(other.attachmentOwner))
			return false;
		if (attachmentId == null) {
			if (other.attachmentId != null)
				return false;
		} else if (!attachmentId.equals(other.attachmentId))
			return false;
		if (item == null) {
			if (other.item != null)
				return false;
		} else if (!item.equals(other.item))
			return false;
		if (fileName == null) {
			if (other.fileName != null)
				return false;
		} else if (!fileName.equals(other.fileName))
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
		return "Attachment [attachmentId=" + attachmentId + ", itemId=" + item + ", title="
				+ title + ", fileName=" + fileName + ", attachmentOwner="
				+ attachmentOwner + "]";
	}
	
	
}

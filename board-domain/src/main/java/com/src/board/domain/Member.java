package com.src.board.domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="MEMBERS")
public class Member implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3691526904690928558L;
	private MemberIdPk memberId;
	private Boolean isOwner;
	
	@EmbeddedId
	public MemberIdPk getMemberId() {
		return memberId;
	}
	public void setMemberId(MemberIdPk memberId) {
		this.memberId = memberId;
	}
	
	@Column(name="isOwner")
	public Boolean getIsOwner() {
		return isOwner;
	}
	public void setIsOwner(Boolean isOwner) {
		this.isOwner = isOwner;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((isOwner == null) ? 0 : isOwner.hashCode());
		result = prime * result
				+ ((memberId == null) ? 0 : memberId.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Member))
			return false;
		Member other = (Member) obj;
		if (isOwner == null) {
			if (other.isOwner != null)
				return false;
		} else if (!isOwner.equals(other.isOwner))
			return false;
		if (memberId == null) {
			if (other.memberId != null)
				return false;
		} else if (!memberId.equals(other.memberId))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Member [memberId=" + memberId + ", isOwner=" + isOwner + "]";
	}
	
	
	
}

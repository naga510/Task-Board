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
@Table(name="ACTIVITIES")
public class Activity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 21276372199646721L;
	private Long activityId;
	private Item item;
	private Long memberId;
	private String activityDesc;	
	//TODO: Need to think how we achieve this
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="activity_seq")
	@SequenceGenerator(name="activity_seq", sequenceName="ACTIVITY_ID_SEQ", initialValue=1, allocationSize=1)
	@Column(name="ACTIVITY_ID", nullable=false, unique=true)
	public Long getActivityId() {
		return activityId;
	}
	public void setActivityId(Long activityId) {
		this.activityId = activityId;
	}
	
	@ManyToOne
    @JoinColumn(name="ACTIVITY_ITEM_ID", nullable=false)
	public Item getItem() {
		return item;
	}
	public void setItem(Item item) {
		this.item = item;
	}
	
	@Column(name="ACTIVITY_MEMBER_ID", nullable=false)
	public Long getMemberId() {
		return memberId;
	}
	
	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}
	
	@Column(name="ACTIVITY_DESC", nullable=false)
	public String getActivityDesc() {
		return activityDesc;
	}
	public void setActivityDesc(String activityDesc) {
		this.activityDesc = activityDesc;
	}
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((activityDesc == null) ? 0 : activityDesc.hashCode());
		result = prime * result
				+ ((activityId == null) ? 0 : activityId.hashCode());
		result = prime * result + ((item == null) ? 0 : item.hashCode());
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
		if (!(obj instanceof Activity))
			return false;
		Activity other = (Activity) obj;
		if (activityDesc == null) {
			if (other.activityDesc != null)
				return false;
		} else if (!activityDesc.equals(other.activityDesc))
			return false;
		if (activityId == null) {
			if (other.activityId != null)
				return false;
		} else if (!activityId.equals(other.activityId))
			return false;
		if (item == null) {
			if (other.item != null)
				return false;
		} else if (!item.equals(other.item))
			return false;
		if (memberId == null) {
			if (other.memberId != null)
				return false;
		} else if (!memberId.equals(other.memberId))
			return false;
		return true;
	}
	
	
}

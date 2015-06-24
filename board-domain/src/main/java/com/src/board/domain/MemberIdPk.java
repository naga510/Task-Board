package com.src.board.domain;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class MemberIdPk implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5979497599059603696L;
	private Long memberId;
	private Long boardId;
	
	public Long getMemberId() {
		return memberId;
	}
	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}
	public Long getBoardId() {
		return boardId;
	}
	public void setBoardId(Long boardId) {
		this.boardId = boardId;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((boardId == null) ? 0 : boardId.hashCode());
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
		if (!(obj instanceof MemberIdPk))
			return false;
		MemberIdPk other = (MemberIdPk) obj;
		if (boardId == null) {
			if (other.boardId != null)
				return false;
		} else if (!boardId.equals(other.boardId))
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
		return "MemberIdPk [memberId=" + memberId + ", boardId=" + boardId
				+ "]";
	}
	
	
}

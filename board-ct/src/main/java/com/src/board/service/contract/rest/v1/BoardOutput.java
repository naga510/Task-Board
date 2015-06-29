package com.src.board.service.contract.rest.v1;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import com.src.board.enums.BoardStatusEnum;

@XmlRootElement(name="boardOutput")
public class BoardOutput {

	private String boardId;
	private String boardName;
	private BoardStatusEnum status;
	private List<ItemOutput> items;
	private List<Member> members;
	public String getBoardId() {
		return boardId;
	}
	public void setBoardId(String boardId) {
		this.boardId = boardId;
	}
	public String getBoardName() {
		return boardName;
	}
	public void setBoardName(String boardName) {
		this.boardName = boardName;
	}
	public BoardStatusEnum getStatus() {
		return status;
	}
	public void setStatus(BoardStatusEnum status) {
		this.status = status;
	}
	public List<ItemOutput> getItems() {
		return items;
	}
	public void setItems(List<ItemOutput> items) {
		this.items = items;
	}
	public List<Member> getMembers() {
		return members;
	}
	public void setMembers(List<Member> members) {
		this.members = members;
	}
	
	
}

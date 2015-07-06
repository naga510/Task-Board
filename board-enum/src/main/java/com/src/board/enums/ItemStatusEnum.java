package com.src.board.enums;

public enum ItemStatusEnum {

	TODO("1"),
	DOING("2"),
	DONE("3"),
	ARCHIVED("4");
	
	private String status;

	private ItemStatusEnum(String status) {
		this.status = status;
	}
	
	public String getStatus() {
		return status;
	}	
	
	public static ItemStatusEnum fromString(String text) {
	    if (text != null) {
	      for (ItemStatusEnum b : ItemStatusEnum.values()) {
	        if (text.equalsIgnoreCase(b.status)) {
	          return b;
	        }
	      }
	    }
	    return null;
	  }
	
	@Override
	public String toString() {		
		return getStatus();
	}
}

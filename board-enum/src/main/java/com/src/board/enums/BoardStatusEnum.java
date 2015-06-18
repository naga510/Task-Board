package com.src.board.enums;

public enum BoardStatusEnum {

	ACTIVE("1"),
	INACTIVE("0");
	
	private String status;
	
	private BoardStatusEnum(String status) {
		this.status=status;
	}
	
	public String getStatus() {
		return status;
	}	
	
	public static BoardStatusEnum fromString(String text) {
	    if (text != null) {
	      for (BoardStatusEnum b : BoardStatusEnum.values()) {
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

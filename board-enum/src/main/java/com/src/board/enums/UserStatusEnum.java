package com.src.board.enums;

public enum UserStatusEnum {

	NEW("0"),
	CONFIRMED("1"),
	DEACTIVATED("2"),
	REACTIVATED("3"),
	DELETED("4");
	
	private String status;
	
	private UserStatusEnum(String status) {
		this.status = status;
	}
	
	public String getStatus() {
		return status;
	}	
	
	public static UserStatusEnum fromString(String text) {
	    if (text != null) {
	      for (UserStatusEnum b : UserStatusEnum.values()) {
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

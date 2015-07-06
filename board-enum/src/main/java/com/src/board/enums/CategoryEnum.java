package com.src.board.enums;

public enum CategoryEnum {

	UNCATEGORIZED("1"),
	BUG("2"),
	DOCUMENTATION("3"),
	ENHANCEMENT("4"),
	FEATURE("5");
	
	private String status;

	private CategoryEnum(String status) {
		this.status = status;
	}
	
	public String getStatus() {
		return status;
	}	
	
	public static CategoryEnum fromString(String text) {
	    if (text != null) {
	      for (CategoryEnum b : CategoryEnum.values()) {
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

package com.src.board.enums.exception;

public enum BoardExceptionEnum {

	INVALID_USER_ID("001","User Id is Not Passed");
	
	private String errorId;
	private String errorMessage;
	
	private BoardExceptionEnum(String errorId, String errorMessage) {
		this.errorId=errorId;
		this.errorMessage=errorMessage;
	}

	public String getErrorId() {
		return errorId;
	}
	
	public String getErrorMessage() {
		return errorMessage;
	}
	
	public static BoardExceptionEnum fromErrorId(String errorId) {
	    if (errorId != null) {
	      for (BoardExceptionEnum b : BoardExceptionEnum.values()) {
	        if (errorId.equalsIgnoreCase(b.errorId)) {
	          return b;
	        }
	      }
	    }
	    return null;
	  }
	
}

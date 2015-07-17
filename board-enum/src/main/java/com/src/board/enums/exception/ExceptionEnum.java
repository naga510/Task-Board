package com.src.board.enums.exception;

public enum ExceptionEnum {

	INVALID_BOARD_NAME("001","Board Name is not valid"),
	INVALID_USER_NAME("101", "User Name is not valid"),
	USER_ALREADY_EXISTS("102", "User already exists"),
	INVALID_USERID_PASSWORD("103","The username or password were incorrect");
	
	private String errorId;
	private String errorMessage;
	
	private ExceptionEnum(String errorId, String errorMessage) {
		this.errorId=errorId;
		this.errorMessage=errorMessage;
	}

	public String getErrorId() {
		return errorId;
	}
	
	public String getErrorMessage() {
		return errorMessage;
	}
	
	public static ExceptionEnum fromErrorId(String errorId) {
	    if (errorId != null) {
	      for (ExceptionEnum b : ExceptionEnum.values()) {
	        if (errorId.equalsIgnoreCase(b.errorId)) {
	          return b;
	        }
	      }
	    }
	    return null;
	  }
	
}

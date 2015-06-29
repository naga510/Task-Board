package com.src.board.enums;

import java.util.regex.Pattern;

public enum BasicValidationEnum {

	EMAIL(Pattern.compile("^[a-zA-Z0-9+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$"), "Invalid Email"),
	PASSWORD(Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$"), "Invalid PAssword"),
	SAFETEXT(Pattern.compile("^[a-zA-Z0-9 .-]+$"), "Invalid Text");
	
	private Pattern pattern;
	private String errorMessage;
	
	private BasicValidationEnum(Pattern pattern, String errorMessage) {
		this.pattern = pattern;
		this.errorMessage = errorMessage;
	}
	
	public Pattern getPattern() {
		return pattern;
	}

	public void setPattern(Pattern pattern) {
		this.pattern = pattern;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
}

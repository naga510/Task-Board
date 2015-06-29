package com.src.board.util;

import java.util.regex.Matcher;

import com.src.board.enums.BasicValidationEnum;

public class BasicValidator {
	
	public static void isValidEmail(String email) {
		Matcher matcher=BasicValidationEnum.EMAIL.getPattern().matcher(email);
		if(!matcher.matches()) {
			throw new RuntimeException(BasicValidationEnum.EMAIL.getErrorMessage());
		}
	}
	
	public static void isValidPassword(String password) {
		Matcher matcher=BasicValidationEnum.PASSWORD.getPattern().matcher(password);
		if(!matcher.matches()) {
			throw new RuntimeException(BasicValidationEnum.PASSWORD.getErrorMessage());
		}
	}
	
	public static void isSafeText(String text) {
		Matcher matcher=BasicValidationEnum.SAFETEXT.getPattern().matcher(text);
		if(!matcher.matches()) {
			throw new RuntimeException(BasicValidationEnum.SAFETEXT.getErrorMessage());
		}
	}
		
}

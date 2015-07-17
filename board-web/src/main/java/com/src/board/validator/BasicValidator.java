package com.src.board.validator;

import java.util.regex.Matcher;

import com.src.board.enums.BasicValidationEnum;
import com.src.board.service.contract.rest.v1.exception.ValidationException;

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
	
	public static void isSafeText(String text, String errorCode, String errorMessage) {
		Matcher matcher=BasicValidationEnum.SAFETEXT.getPattern().matcher(text);
		if(!matcher.matches()) {
			throw new ValidationException(errorMessage, errorCode);
		}
	}
		
}
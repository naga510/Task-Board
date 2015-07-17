package com.src.board.service.contract.rest.v1.exception;

public class ValidationException extends BaseAPIException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3266031242651026077L;

	public ValidationException(String errorMessage, String errorCode) {
		super(400, errorMessage, errorCode);		
	}

}

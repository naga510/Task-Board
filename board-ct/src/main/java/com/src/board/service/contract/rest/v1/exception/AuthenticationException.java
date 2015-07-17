package com.src.board.service.contract.rest.v1.exception;

import com.src.board.enums.exception.ExceptionEnum;

public class AuthenticationException extends BaseAPIException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1321011184768735965L;

	public AuthenticationException() {
		super(401, ExceptionEnum.INVALID_USERID_PASSWORD.getErrorMessage(),
				ExceptionEnum.INVALID_USERID_PASSWORD.getErrorId());
	}

}

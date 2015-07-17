package com.src.board.service.contract.rest.v1.exception;

import com.src.board.enums.exception.ExceptionEnum;

public class DuplicateUserException extends BaseAPIException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5853732504378132842L;

	public DuplicateUserException() {
		super(409, ExceptionEnum.USER_ALREADY_EXISTS.getErrorMessage(),
				ExceptionEnum.USER_ALREADY_EXISTS.getErrorId());
	}

}

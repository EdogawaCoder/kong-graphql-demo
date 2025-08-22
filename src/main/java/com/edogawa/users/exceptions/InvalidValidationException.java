package com.edogawa.users.exceptions;

public class InvalidValidationException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	private final String field;

	public InvalidValidationException(String field, String message) {
		super(message);
		this.field = field;

	}
	
	public String getField() {
		return field;
	}

}

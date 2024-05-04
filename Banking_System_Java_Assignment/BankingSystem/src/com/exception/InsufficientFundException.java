package com.exception;

public class InsufficientFundException extends Exception {
	
	private static final long serialVersionUID = -8743788699448086073L;
	private String message;

	public InsufficientFundException(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
}
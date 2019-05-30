package com.rasmivan.commercetools.exception;


public class ConcurrentConflictException extends RuntimeException {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 3630040717052588117L;
	
	public ConcurrentConflictException(String message) {
		super(message);
	}

}

package com.rasmivan.commercetools.exception;

/**
 * The Class DuplicateException.
 */
public class DuplicateException extends RuntimeException {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 3630040717052588117L;
	
	
	/**
	 * Instantiates a new duplicate exception.
	 *
	 * @param message the message
	 */
	public DuplicateException(String message) {
		super(message);
	}

}

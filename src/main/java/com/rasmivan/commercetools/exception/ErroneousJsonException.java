package com.rasmivan.commercetools.exception;

/**
 * The Class ErroneousJsonException.
 */
public class ErroneousJsonException extends RuntimeException {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 3630040717052588117L;
	
	
	/**
	 * Instantiates a new erroneous json exception.
	 *
	 * @param message the message
	 */
	public ErroneousJsonException(String message) {
		super(message);
	}

}
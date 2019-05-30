package com.rasmivan.commercetools.exception;


/**
 * The Class OutdateStockException.
 */
public class OutdateStockException extends RuntimeException {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 3630040717052588117L;
	
	
	/**
	 * Instantiates a new outdate stock exception.
	 *
	 * @param message the message
	 */
	public OutdateStockException(String message) {
		super(message);
	}

}

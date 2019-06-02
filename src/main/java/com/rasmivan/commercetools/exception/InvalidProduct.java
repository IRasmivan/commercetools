package com.rasmivan.commercetools.exception;

/**
 * The Class InvalidProduct.
 */
public class InvalidProduct extends RuntimeException {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 3630040717052588117L;
	
	/**
	 * Instantiates a new invalid product.
	 *
	 * @param message the message
	 */
	public InvalidProduct(String message) {
		super(message);
	}

}

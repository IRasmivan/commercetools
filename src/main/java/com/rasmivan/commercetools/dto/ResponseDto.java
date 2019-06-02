package com.rasmivan.commercetools.dto;

import java.io.Serializable;

/**
 * The Class ResponseDto.
 */
public class ResponseDto implements Serializable{
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 4532841946722605862L;

	/** The message. */
	private String message;
	
	/** The status code. */
	private String statusCode;

	/**
	 * Gets the message.
	 *
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * Sets the message.
	 *
	 * @param message the new message
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * Gets the status code.
	 *
	 * @return the status code
	 */
	public String getStatusCode() {
		return statusCode;
	}

	/**
	 * Sets the status code.
	 *
	 * @param statusCode the new status code
	 */
	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}
	
}

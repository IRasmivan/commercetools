package com.rasmivan.commercetools.dto;

import java.time.Instant;

/**
 * The Class InvoiceDto.
 */
public class InvoiceDto {
	
	/** The id. */
	private String id;
	
	/** The product id. */
	private String productId;
	
	/** The timestamp. */
	private Instant timestamp;
	
	/** The quantity. */
	private Long quantity;

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * Gets the product id.
	 *
	 * @return the product id
	 */
	public String getProductId() {
		return productId;
	}

	/**
	 * Sets the product id.
	 *
	 * @param productId the new product id
	 */
	public void setProductId(String productId) {
		this.productId = productId;
	}

	/**
	 * Gets the timestamp.
	 *
	 * @return the timestamp
	 */
	public Instant getTimestamp() {
		return timestamp;
	}

	/**
	 * Sets the timestamp.
	 *
	 * @param timestamp the new timestamp
	 */
	public void setTimestamp(Instant timestamp) {
		this.timestamp = timestamp;
	}

	/**
	 * Gets the quantity.
	 *
	 * @return the quantity
	 */
	public Long getQuantity() {
		return quantity;
	}

	/**
	 * Sets the quantity.
	 *
	 * @param quantity the new quantity
	 */
	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}

}

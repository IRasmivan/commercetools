package com.rasmivan.commercetools.dto;

import java.time.Instant;

/**
 * The Class TopAvailableProductDto.
 */
public class TopAvailableProductDto {

	/** The id. */
	private String id;
	
	/** The timestamp. */
	private Instant timestamp;
	
	/** The product id. */
	private String productId;
	
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

	/**
	 * Instantiates a new top available product dto.
	 *
	 * @param id the id
	 * @param timestamp the timestamp
	 * @param productId the product id
	 * @param quantity the quantity
	 */
	public TopAvailableProductDto(String id, Instant timestamp, String productId, Long quantity) {
		super();
		this.id = id;
		this.timestamp = timestamp;
		this.productId = productId;
		this.quantity = quantity;
	}
	
}

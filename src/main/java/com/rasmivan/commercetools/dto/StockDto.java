package com.rasmivan.commercetools.dto;

import java.io.Serializable;
import java.time.Instant;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * The Class StockDto.
 */
@JsonInclude(Include.NON_NULL)
public class StockDto implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -3962561527227712555L;

	/** The id. */
	private String id;
	
	/** The product id. */
	private String productId;
	
	/** The timestamp. */
	private Instant timestamp;
	
	/** The quantity. */
	private Long quantity;
	
	/** The version. */
	private Long version;

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
	
	/**
	 * Gets the version.
	 *
	 * @return the version
	 */
	@JsonIgnore 
	public Long getVersion() {
		return version;
	}

	/**
	 * Sets the version.
	 *
	 * @param version the new version
	 */
	public void setVersion(Long version) {
		this.version = version;
	}

	/**
	 * Instantiates a new stock dto.
	 */
	public StockDto() {
		super();
	}

	
	/**
	 * Instantiates a new stock dto.
	 *
	 * @param id the id
	 * @param timestamp the timestamp
	 * @param quantity the quantity
	 * @param version the version
	 */
	public StockDto(String id, Instant timestamp, Long quantity, Long version) {
		this.id = id;
		this.timestamp = timestamp;
		this.quantity = quantity;
		this.version = version;
	}

}

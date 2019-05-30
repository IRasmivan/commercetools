package com.rasmivan.commercetools.domain;

import java.time.Instant;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Version;

import com.fasterxml.jackson.annotation.JsonBackReference;

/**
 * The Class Stock.
 */
@Entity
@Table(name = "stock")
public class Stock {
	
	/** The id. */
	private String id;
	
	/** The quantity. */
	private Long quantity;
	
	/** The product id. */
	private ProductMaster productId;
	
	/** The timestamp. */
	private Instant timestamp;
	
    private Long version;

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	@Column(name = "id", columnDefinition = "INT", unique = true, nullable = false)
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
	 * Gets the quantity.
	 *
	 * @return the quantity
	 */
	@Column(name = "quantity", nullable = false)
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
	 * Gets the product id.
	 *
	 * @return the product id
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "productid", nullable = false)
	@JsonBackReference
	public ProductMaster getProductId() {
		return productId;
	}

	/**
	 * Sets the product id.
	 *
	 * @param productId the new product id
	 */
	public void setProductId(ProductMaster productId) {
		this.productId = productId;
	}

	/**
	 * Gets the timestamp.
	 *
	 * @return the timestamp
	 */
	@Column(name = "timestamp", nullable = true, columnDefinition = "DATETIME(6)")
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
	
	@Version
	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}
	
}

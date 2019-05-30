package com.rasmivan.commercetools.dto;

import java.util.Date;

public class TopAvailableProductDto {

	private String id;
	private Date timestamp;
	private String productId;
	private Long quantity;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public Long getQuantity() {
		return quantity;
	}

	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}

	public TopAvailableProductDto(String id, Date timestamp, String productId, Long quantity) {
		super();
		this.id = id;
		this.timestamp = timestamp;
		this.productId = productId;
		this.quantity = quantity;
	}
	
	
	
	
}

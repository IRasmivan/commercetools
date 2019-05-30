package com.rasmivan.commercetools.dto;

public class TopSellingProductDto {
	
	private String productId;
	private Long itemSold;
	
	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public Long getItemSold() {
		return itemSold;
	}

	public void setItemSold(Long itemSold) {
		this.itemSold = itemSold;
	}

	public TopSellingProductDto(String productId, Long itemSold) {
		this.productId = productId;
		this.itemSold = itemSold;
	}

}
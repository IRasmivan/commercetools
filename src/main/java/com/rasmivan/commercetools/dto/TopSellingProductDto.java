package com.rasmivan.commercetools.dto;

/**
 * The Class TopSellingProductDto.
 */
public class TopSellingProductDto {
	
	/** The product id. */
	private String productId;
	
	/** The item sold. */
	private Long itemSold;
	
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
	 * Gets the item sold.
	 *
	 * @return the item sold
	 */
	public Long getItemSold() {
		return itemSold;
	}

	/**
	 * Sets the item sold.
	 *
	 * @param itemSold the new item sold
	 */
	public void setItemSold(Long itemSold) {
		this.itemSold = itemSold;
	}

	/**
	 * Instantiates a new top selling product dto.
	 *
	 * @param productId the product id
	 * @param itemSold the item sold
	 */
	public TopSellingProductDto(String productId, Long itemSold) {
		this.productId = productId;
		this.itemSold = itemSold;
	}

	/**
	 * Instantiates a new top selling product dto.
	 */
	public TopSellingProductDto() {
		super();
	}
	
	/**
	 * Merge.
	 *
	 * @param another the another
	 * @return the top selling product dto
	 */
	public TopSellingProductDto merge(TopSellingProductDto another){
	     this.itemSold += another.getItemSold();
	     return this;
	}
	
	

}
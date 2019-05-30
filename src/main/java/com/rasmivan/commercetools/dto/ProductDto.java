package com.rasmivan.commercetools.dto;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * The Class Product.
 */
@JsonInclude(Include.NON_NULL)
public class ProductDto implements Serializable {
	
	private static final long serialVersionUID = -8567746055385841911L;

	/** The id. */
	private Long id;
	
	private String productId;
	
	private String requestTimestamp;
	
	@JsonProperty("stock")
	private List<StockDto> stock;
		
	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}
	
	public List<StockDto> getStock() {
		return stock;
	}

	public void setStock(List<StockDto> stock) {
		this.stock = stock;
	}

	/**
	 * Instantiates a new product.
	 *
	 * @param id the id
	 * @param productName the product name
	 */
	public ProductDto(Long id, String productId) {
		super();
		this.id = id;
		this.productId = productId;
	}

	public ProductDto() {
		super();
	}

	public String getRequestTimestamp() {
		return requestTimestamp;
	}

	public void setRequestTimestamp(String requestTimestamp) {
		this.requestTimestamp = requestTimestamp;
	}

}

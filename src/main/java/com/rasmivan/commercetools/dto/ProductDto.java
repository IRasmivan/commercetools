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
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -8567746055385841911L;

	/** The id. */
	private Long id;
	
	/** The product id. */
	private String productId;
	
	/** The request timestamp. */
	private String requestTimestamp;
	
	/** The stock. */
	@JsonProperty("stock")
	private List<StockDto> stock;
	
	/** The stock message. */
	private String stockMessage;
		
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
	 * Gets the stock.
	 *
	 * @return the stock
	 */
	public List<StockDto> getStock() {
		return stock;
	}

	/**
	 * Sets the stock.
	 *
	 * @param stock the new stock
	 */
	public void setStock(List<StockDto> stock) {
		this.stock = stock;
	}

	/**
	 * Instantiates a new product.
	 *
	 * @param id the id
	 * @param productId the product id
	 */
	public ProductDto(Long id, String productId) {
		super();
		this.id = id;
		this.productId = productId;
	}

	/**
	 * Instantiates a new product dto.
	 */
	public ProductDto() {
		super();
	}

	/**
	 * Gets the request timestamp.
	 *
	 * @return the request timestamp
	 */
	public String getRequestTimestamp() {
		return requestTimestamp;
	}

	/**
	 * Sets the request timestamp.
	 *
	 * @param requestTimestamp the new request timestamp
	 */
	public void setRequestTimestamp(String requestTimestamp) {
		this.requestTimestamp = requestTimestamp;
	}

	/**
	 * Gets the stock message.
	 *
	 * @return the stock message
	 */
	public String getStockMessage() {
		return stockMessage;
	}

	/**
	 * Sets the stock message.
	 *
	 * @param stockMessage the new stock message
	 */
	public void setStockMessage(String stockMessage) {
		this.stockMessage = stockMessage;
	}

}

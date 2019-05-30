package com.rasmivan.commercetools.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * The Class StatisticsDto.
 */
@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder({
"requestTimestamp",
"range",
"topAvailableProductMessage",
"topAvailableProducts",
"topSellingProductsMessage",
"topSellingProducts"
})
public class StatisticsDto {

	/** The request timestamp. */
	private String requestTimestamp;
	
	/** The range. */
	private String range;
	
	/** The top available products. */
	private List<TopAvailableProductDto> topAvailableProducts;
	
	/** The top available product message. */
	private String topAvailableProductMessage;
	
	/** The top selling products. */
	private List<TopSellingProductDto> topSellingProducts;
	
	/** The top selling products message. */
	private String topSellingProductsMessage;
	
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
	 * Gets the range.
	 *
	 * @return the range
	 */
	public String getRange() {
		return range;
	}

	/**
	 * Sets the range.
	 *
	 * @param range the new range
	 */
	public void setRange(String range) {
		this.range = range;
	}

	/**
	 * Gets the top available products.
	 *
	 * @return the top available products
	 */
	@JsonProperty("topAvailableProducts")
	public List<TopAvailableProductDto> getTopAvailableProducts() {
		return topAvailableProducts;
	}

	/**
	 * Sets the top available products.
	 *
	 * @param topAvailableProducts the new top available products
	 */
	public void setTopAvailableProducts(List<TopAvailableProductDto> topAvailableProducts) {
		this.topAvailableProducts = topAvailableProducts;
	}

	/**
	 * Gets the top available product message.
	 *
	 * @return the top available product message
	 */
	public String getTopAvailableProductMessage() {
		return topAvailableProductMessage;
	}

	/**
	 * Sets the top available product message.
	 *
	 * @param topAvailableProductMessage the new top available product message
	 */
	public void setTopAvailableProductMessage(String topAvailableProductMessage) {
		this.topAvailableProductMessage = topAvailableProductMessage;
	}

	/**
	 * Gets the top selling products.
	 *
	 * @return the top selling products
	 */
	@JsonProperty("topSellingProducts")
	public List<TopSellingProductDto> getTopSellingProducts() {
		return topSellingProducts;
	}

	/**
	 * Sets the top selling products.
	 *
	 * @param topSellingProducts the new top selling products
	 */
	public void setTopSellingProducts(List<TopSellingProductDto> topSellingProducts) {
		this.topSellingProducts = topSellingProducts;
	}

	/**
	 * Gets the top selling products message.
	 *
	 * @return the top selling products message
	 */
	public String getTopSellingProductsMessage() {
		return topSellingProductsMessage;
	}

	/**
	 * Sets the top selling products message.
	 *
	 * @param topSellingProductsMessage the new top selling products message
	 */
	public void setTopSellingProductsMessage(String topSellingProductsMessage) {
		this.topSellingProductsMessage = topSellingProductsMessage;
	}

		
}

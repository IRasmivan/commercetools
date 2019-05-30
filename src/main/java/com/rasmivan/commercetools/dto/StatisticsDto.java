package com.rasmivan.commercetools.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

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

	private String requestTimestamp;
	private String range;
	private List<TopAvailableProductDto> topAvailableProducts;
	private String topAvailableProductMessage;
	private List<TopSellingProductDto> topSellingProducts;
	private String topSellingProductsMessage;
	
	public String getRequestTimestamp() {
		return requestTimestamp;
	}

	public void setRequestTimestamp(String requestTimestamp) {
		this.requestTimestamp = requestTimestamp;
	}

	public String getRange() {
		return range;
	}

	public void setRange(String range) {
		this.range = range;
	}

	@JsonProperty("topAvailableProducts")
	public List<TopAvailableProductDto> getTopAvailableProducts() {
		return topAvailableProducts;
	}

	public void setTopAvailableProducts(List<TopAvailableProductDto> topAvailableProducts) {
		this.topAvailableProducts = topAvailableProducts;
	}

	public String getTopAvailableProductMessage() {
		return topAvailableProductMessage;
	}

	public void setTopAvailableProductMessage(String topAvailableProductMessage) {
		this.topAvailableProductMessage = topAvailableProductMessage;
	}

	@JsonProperty("topSellingProducts")
	public List<TopSellingProductDto> getTopSellingProducts() {
		return topSellingProducts;
	}

	public void setTopSellingProducts(List<TopSellingProductDto> topSellingProducts) {
		this.topSellingProducts = topSellingProducts;
	}

	public String getTopSellingProductsMessage() {
		return topSellingProductsMessage;
	}

	public void setTopSellingProductsMessage(String topSellingProductsMessage) {
		this.topSellingProductsMessage = topSellingProductsMessage;
	}

		
}

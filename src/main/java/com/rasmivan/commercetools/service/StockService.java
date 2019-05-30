package com.rasmivan.commercetools.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.util.MultiValueMap;

import com.rasmivan.commercetools.domain.Stock;
import com.rasmivan.commercetools.dto.ProductDto;
import com.rasmivan.commercetools.dto.StockDto;

/**
 * The Interface StockService.
 */
public interface StockService {
	
	/**
	 * Adds the stock.
	 *
	 * @param stockDto the stock dto
	 * @return the stock
	 */
	Stock addStock(StockDto stockDto);
	
	
	StockDto updateStock(StockDto stockDto, HttpServletRequest request, MultiValueMap<String, String> headers);
	
	/**
	 * Gets the current stock by product id.
	 *
	 * @param productId the product id
	 * @param headers 
	 * @return the current stock by product id
	 */
	ProductDto getCurrentStockByProductId(String productId, MultiValueMap<String, String> headers);
	
}
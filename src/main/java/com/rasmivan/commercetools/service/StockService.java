package com.rasmivan.commercetools.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.util.MultiValueMap;

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
	 * @return the product dto
	 */
	ProductDto addStock(StockDto stockDto);
	
	
	/**
	 * Update stock.
	 *
	 * @param stockDto the stock dto
	 * @param request the request
	 * @param headers the headers
	 * @return the product dto
	 */
	ProductDto updateStock(StockDto stockDto, HttpServletRequest request, MultiValueMap<String, String> headers);
	
	/**
	 * Gets the current stock by product id.
	 *
	 * @param productId the product id
	 * @return the current stock by product id
	 */
	ProductDto getCurrentStockByProductId(String productId);


	/**
	 * Gets the version no.
	 *
	 * @param productId the product id
	 * @return the version no
	 */
	MultiValueMap<String, String> getVersionNo(String productId);
	
}
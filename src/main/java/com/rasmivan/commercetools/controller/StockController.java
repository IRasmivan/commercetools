package com.rasmivan.commercetools.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rasmivan.commercetools.dto.ProductDto;
import com.rasmivan.commercetools.dto.StockDto;
import com.rasmivan.commercetools.service.StockService;

/**
 * The Class StockController.
 */
@RestController
@CrossOrigin
@RequestMapping("/commercetools/api")
public class StockController {
	
	/** The stock service. */
	@Autowired
	StockService stockService;
	
	/**
	 * Gets the Stock.
	 *
	 * @param productId the product id
	 * @return the product
	 */
	@GetMapping(value = "/v1/stock")
	public ResponseEntity<ProductDto> getStock(@RequestParam String productId){
		ProductDto productDto = stockService.getCurrentStockByProductId(productId);
		return new ResponseEntity<>(productDto, stockService.getVersionNo(productId), HttpStatus.OK);
	}
	
	/**
	 * Adds the stock.
	 *
	 * @param stockDto the stock dto
	 * @return the response entity
	 */
	@CrossOrigin
	@PostMapping(value = "/v1/stock/add")
	public ResponseEntity<ProductDto> addStock(@RequestBody StockDto stockDto){
		return new ResponseEntity<>(stockService.addStock(stockDto), HttpStatus.CREATED);
	}
	
	/**
	 * Update stock.
	 *
	 * @param stockDto the stock dto
	 * @return the response entity
	 */
	@CrossOrigin
	@PostMapping(value = "/v1/stock/update")
	public ResponseEntity<ProductDto> updateStock(@RequestBody StockDto stockDto, HttpServletRequest request){
		MultiValueMap<String, String> headers =  new LinkedMultiValueMap<>();
		ProductDto productDto = stockService.updateStock(stockDto, request, headers);
		return new ResponseEntity<>(productDto, headers, HttpStatus.CREATED);
	}
	
}

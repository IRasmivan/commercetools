package com.rasmivan.commercetools.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rasmivan.commercetools.domain.Stock;
import com.rasmivan.commercetools.dto.ProductDto;
import com.rasmivan.commercetools.dto.StockDto;
import com.rasmivan.commercetools.service.StockService;

/**
 * The Class StockController.
 */
@RestController
@CrossOrigin
@RequestMapping("/commercetools/api/")
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
	@CrossOrigin
	@GetMapping(value = "v1/stock")
	public ResponseEntity<ProductDto> getStock(@RequestParam String productId){
		MultiValueMap<String, String> headers =  new LinkedMultiValueMap<>();
		ProductDto productDto = stockService.getCurrentStockByProductId(productId, headers);
		return new ResponseEntity<>(productDto, headers, HttpStatus.OK);
	}
	
	/**
	 * Adds the stock.
	 *
	 * @param stockDto the stock dto
	 * @return the response entity
	 */
	@CrossOrigin
	@PutMapping(value = "v1/stock/add")
	public ResponseEntity<Stock> addStock(@RequestBody StockDto stockDto){
		return new ResponseEntity<>(stockService.addStock(stockDto), HttpStatus.CREATED);
	}
	
	/**
	 * Update stock.
	 *
	 * @param stockDto the stock dto
	 * @return the response entity
	 */
	@CrossOrigin
	@PatchMapping(value = "v1/stock/update")
	public ResponseEntity<StockDto> updateStock(@RequestBody StockDto stockDto, HttpServletRequest request){
		MultiValueMap<String, String> headers =  new LinkedMultiValueMap<>();
		StockDto stk = stockService.updateStock(stockDto, request, headers);
		return new ResponseEntity<>(stk, headers, HttpStatus.CREATED);
	}
	
}

package com.rasmivan.commercetools.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rasmivan.commercetools.domain.Stock;
import com.rasmivan.commercetools.dto.StockDto;
import com.rasmivan.commercetools.service.StockService;



@RestController
@CrossOrigin
@RequestMapping("/commercetools/api/")
public class StockController {
	
	@Autowired
	StockService stockService;
	
	@CrossOrigin
	@PostMapping(value = "v1/stock/add")
	public ResponseEntity<Stock> addStock(@RequestBody StockDto stockDto){
		return new ResponseEntity<>(stockService.addStock(stockDto), HttpStatus.CREATED);
	}
	
	@CrossOrigin
	@PostMapping(value = "v1/stock/update")
	public ResponseEntity<Stock> updateStock(@RequestBody StockDto stockDto){
		return new ResponseEntity<>(stockService.updateStock(stockDto), HttpStatus.CREATED);
	}
	
}

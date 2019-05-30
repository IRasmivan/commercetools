package com.rasmivan.commercetools.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rasmivan.commercetools.domain.ProductMaster;
import com.rasmivan.commercetools.dto.ProductDto;
import com.rasmivan.commercetools.service.ProductService;


@RestController
@CrossOrigin
@RequestMapping("/commercetools/api/")
public class ProductController {
	
	@Autowired
	ProductService productService;
	
	@CrossOrigin
	@GetMapping(value = "v1/products")
	public ResponseEntity<List<ProductMaster>> getAllProducts(){
		return new ResponseEntity<>(productService.getAllProduct(), HttpStatus.OK);
	}
	
	@CrossOrigin
	@PostMapping(value = "v1/product/add")
	public ResponseEntity<String> addProducts(@RequestBody ProductDto productDto){
		return new ResponseEntity<>(productService.addProduct(productDto).toString(), HttpStatus.CREATED);
	}
	
	@CrossOrigin
	@GetMapping(value = "v1/stock")
	public ResponseEntity<ProductDto> getProduct(@RequestParam String productId){
		return new ResponseEntity<>(productService.getAllStockByProductId(productId), HttpStatus.OK);
	}
	
}

package com.rasmivan.commercetools.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rasmivan.commercetools.domain.ProductMaster;
import com.rasmivan.commercetools.dto.ProductDto;
import com.rasmivan.commercetools.service.ProductService;

/**
 * The Class ProductController.
 */
@RestController
@CrossOrigin
@RequestMapping("/commercetools/api")
public class ProductController {
	
	/** The product service. */
	@Autowired
	ProductService productService;
	
	/**
	 * Gets the all products.
	 *
	 * @return the all products
	 */
	@GetMapping(value = "/v1/products")
	public ResponseEntity<List<ProductMaster>> getAllProducts(){
		return new ResponseEntity<>(productService.getAllProduct(), HttpStatus.OK);
	}
	
	/**
	 * Adds the products.
	 *
	 * @param productDto the product dto
	 * @return the response entity
	 */
	@PutMapping(value = "/v1/product/add")
	public ResponseEntity<String> addProducts(@RequestBody ProductDto productDto){
		return new ResponseEntity<>(productService.addProduct(productDto).toString(), HttpStatus.CREATED);
	}
	
	/**
	 * Gets the product.
	 *
	 * @param productId the product id
	 * @return the product
	 */
	@GetMapping(value = "/v1/product/stock")
	public ResponseEntity<ProductDto> getProduct(@RequestParam String productId){
		return new ResponseEntity<>(productService.getAllStockByProductId(productId), HttpStatus.OK);
	}
	
}

package com.rasmivan.commercetools.service;

import java.util.List;

import com.rasmivan.commercetools.domain.ProductMaster;
import com.rasmivan.commercetools.dto.ProductDto;

/**
 * The Interface ProductService.
 */
public interface ProductService {
	
	/**
	 * Gets the all product.
	 *
	 * @return the all product
	 */
	List<ProductMaster> getAllProduct();

	/**
	 * Adds the product.
	 *
	 * @param productDto the product dto
	 * @return the long
	 */
	Long addProduct(ProductDto productDto);
	
	/**
	 * Gets the all stock by product id.
	 *
	 * @param productId the product id
	 * @return the all stock by product id
	 */
	ProductDto getAllStockByProductId(String productId);

	/**
	 * Product exists.
	 *
	 * @param productId the product id
	 * @return the boolean
	 */
	Boolean productExists(String productId);
	
}
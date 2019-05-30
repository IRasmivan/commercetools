package com.rasmivan.commercetools.service;

import java.util.List;

import com.rasmivan.commercetools.domain.ProductMaster;
import com.rasmivan.commercetools.dto.ProductDto;

public interface ProductService {
	
	List<ProductMaster> getAllProduct();

	Long addProduct(ProductDto productDto);
	
	ProductDto getAllStockByProductId(String productId);

	Boolean productExists(String productId);
	
}
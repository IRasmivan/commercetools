package com.rasmivan.commercetools.service;

import com.rasmivan.commercetools.domain.Stock;
import com.rasmivan.commercetools.dto.StockDto;

public interface StockService {
	
	Stock addStock(StockDto stockDto);
	
	Stock updateStock(StockDto stockDto);
	
}
package com.rasmivan.commercetools.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rasmivan.commercetools.dto.StockDto;
import com.rasmivan.commercetools.exception.ErroneousJsonException;
import com.rasmivan.commercetools.exception.OutdateStockException;
import com.rasmivan.commercetools.repository.ProductRepository;
import com.rasmivan.commercetools.repository.StockRepository;
import com.rasmivan.commercetools.constants.MessageConstantsUtils;
import com.rasmivan.commercetools.domain.Stock;


@Service
public class StockServiceImp implements StockService {
	
	@Autowired
	ProductRepository productRepository;
	
	@Autowired
	StockRepository stockRepository;
	
	@Autowired
	ProductService productService;
	
	
	@Override
	public Stock addStock(StockDto stockDto) {
		if(checkForNullEmptyStockDto(stockDto)) {
			return stockRepository.save(copyStockProperties(stockDto)); // Save a Stock
		} else {
			throw new ErroneousJsonException(MessageConstantsUtils.ERROR_SAVING);
		}
	}
	
	
	@Override
	public Stock updateStock(StockDto stockDto) {
		if(checkForNullEmptyStockDto(stockDto) && stockDto.getId() != null) {
			if(!stockExists(stockDto.getId())) {
				throw new ErroneousJsonException(MessageConstantsUtils.INVALID_STOCK_ID);
			}
			return stockRepository.save(copyStockProperties(stockDto));
		} else {
			throw new ErroneousJsonException(MessageConstantsUtils.ERROR_SAVING);
		}
	}
	
	private boolean checkForNullEmptyStockDto(StockDto stockDto) {
		return stockDto != null && stockDto.getProductId() != null && stockDto.getQuantity() != null && stockDto.getTimestamp() != null;
	}
	
	private Boolean stockExists(String stockId) {
		Optional<Stock> stk = stockRepository.findById(stockId);
		return stk.isPresent();
	}

	private Stock copyStockProperties(StockDto stockDto) {
		if(!productService.productExists(stockDto.getProductId())) {
			throw new ErroneousJsonException(MessageConstantsUtils.PRODUCT_NOTFOUND);
		}
		
		Stock stk = new Stock();
		if(stockDto.getId() != null) {
			Optional<Stock> optStk = stockRepository.findById(stockDto.getId());
			if(optStk.isPresent()) {
				stk = optStk.get();
				if(stockDto.getTimestamp().before(stk.getTimestamp())) {
					throw new OutdateStockException(MessageConstantsUtils.OUTDATED_STOCK);
				}
			} else {
				throw new ErroneousJsonException(MessageConstantsUtils.INVALID_STOCK_ID);
			}
		}
		
		stk.setTimestamp(stockDto.getTimestamp());
		stk.setQuantity(stockDto.getQuantity());
		stk.setProductId(productRepository.findByProductId(stockDto.getProductId()).get(0));
		return stk;
	}
	
}

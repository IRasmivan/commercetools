package com.rasmivan.commercetools.service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rasmivan.commercetools.dto.ProductDto;
import com.rasmivan.commercetools.dto.StockDto;
import com.rasmivan.commercetools.exception.DatabaseException;
import com.rasmivan.commercetools.exception.DuplicateException;
import com.rasmivan.commercetools.exception.InvalidProduct;
import com.rasmivan.commercetools.repository.InvoiceRepository;
import com.rasmivan.commercetools.repository.ProductRepository;
import com.rasmivan.commercetools.repository.StockRepository;
import com.rasmivan.commercetools.constants.MessageConstantsUtils;
import com.rasmivan.commercetools.domain.ProductMaster;


@Service
public class ProductServiceImp implements ProductService {
	
	@Autowired
	ProductRepository productRepository;
	
	@Autowired
	StockRepository stockRepository;
	
	@Autowired
	InvoiceRepository invoiceRepository;

	@Override
	public Long addProduct(ProductDto productDto) {
		if(checkForNullEmptyProductDto(productDto)) { // Checking if the user have provided a valid Product
			ProductMaster productMaster = new ProductMaster();
			BeanUtils.copyProperties(productDto, productMaster); // Copy from DTO to Domain
			try {
				if(productExists(productMaster.getProductId())) {
					throw new DuplicateException(MessageConstantsUtils.DUPLICATE_PRODUCT);
				}
				return productRepository.save(productMaster).getId(); // JPA Save
			} catch(Exception e) {
				throw new DatabaseException(MessageConstantsUtils.ERROR_SAVING + e.getMessage());
			}
		} else {
			throw new InvalidProduct(MessageConstantsUtils.INVALID_PRODUCT);
		}
	}

	@Override
	public List<ProductMaster> getAllProduct() {
		try {
			return productRepository.findAll(); // Get all the Products from DB
		} catch(Exception e) {
			throw new DatabaseException(MessageConstantsUtils.DATABASE_CONNECTION + e.getMessage());
		}
	}
	
	@Override
	public ProductDto getAllStockByProductId(String productId) {
		if(productExists(productId)) {
			return copyProductProperties(productRepository.findByProductId(productId).get(0));
		} else {
			throw new InvalidProduct(MessageConstantsUtils.INVALID_PRODUCT);
		}
	}
	
	@Override
	public Boolean productExists(String productId) {
		List<ProductMaster> prodMast = productRepository.findByProductId(productId);
		return prodMast != null && !prodMast.isEmpty();
	}

	private ProductDto copyProductProperties(ProductMaster productMaster) {
		ProductDto productDto = new ProductDto();
		List<StockDto> stockDtos = new ArrayList<>();
		BeanUtils.copyProperties(productMaster, productDto);
		productMaster.getStock().forEach(stk -> {
			StockDto stockDto = new StockDto();
			BeanUtils.copyProperties(stk, stockDto);
			stockDtos.add(stockDto);
		});
		productDto.setId(null);
		productDto.setRequestTimestamp(Instant.now().toString());
		productDto.setStock(stockDtos);
		return productDto;
	}
	
	private boolean checkForNullEmptyProductDto(ProductDto productDto) {
		return productDto != null && productDto.getProductId() != null && !productDto.getProductId().isEmpty();
	}
	
	
	
}

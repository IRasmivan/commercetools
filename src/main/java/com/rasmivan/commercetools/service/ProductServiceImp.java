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

/**
 * The Class ProductServiceImp.
 */
@Service
public class ProductServiceImp implements ProductService {
	
	/** The product repository. */
	@Autowired
	ProductRepository productRepository;
	
	/** The stock repository. */
	@Autowired
	StockRepository stockRepository;
	
	/** The invoice repository. */
	@Autowired
	InvoiceRepository invoiceRepository;

	/*************************************
	 *************************************
	 * Add a New Product into the Database
	 *************************************
	 *
	 ***** Failure Scenario Coverage *****
	 **** The below condition are checked before adding the product into the database.
	 *** 1) Check if the User have given valid JSON. If its Invalid JSON, then respond user as InvalidProduct
	 *** 2) Check if the productId given by the user already exists in the database. If exists respond user with DuplicateException
	 *** 3) If there are any exception while saving the products in database, then respond user as DuplicateException 
	 *
	 *************************************
	 * If the user have provided a valid productId, then save the productId into Database.
	 *************************************
	 *
	 *************************************
	 *************************************/
	@Override
	public Long addProduct(ProductDto productDto) {
		if(checkForNullEmptyProductDto(productDto)) { // Checking if the user have provided a valid Product
			ProductMaster productMaster = new ProductMaster();
			BeanUtils.copyProperties(productDto, productMaster); // Copy from DTO to Domain using BeanUtils
			try {
				if(productExists(productMaster.getProductId())) {
					throw new DuplicateException(MessageConstantsUtils.DUPLICATE_PRODUCT); // Checking if the productId already exists in database.
				}
				return productRepository.save(productMaster).getId(); // JPA Save
			} catch(Exception e) {
				throw new DatabaseException(MessageConstantsUtils.ERROR_SAVING + e.getMessage());
			}
		} else {
			throw new InvalidProduct(MessageConstantsUtils.INVALID_PRODUCT); // Invalid JSON provided by the user.
		}
	}

	/** Gets all the products that are available.
	 * TODO: return page by page and restrict the size of the page that gets returned. This is out of scope for this excises.
	 */
	@Override
	public List<ProductMaster> getAllProduct() {
		try {
			return productRepository.findAll(); // Get all the Products from DB
		} catch(Exception e) {
			throw new DatabaseException(MessageConstantsUtils.DATABASE_CONNECTION + e.getMessage());
		}
	}
	
	/** 
	 * Gets the first occurrence of a Stocks associated to the product.
	 **/
	@Override
	public ProductDto getAllStockByProductId(String productId) {
		if(productExists(productId)) {
			return copyProductProperties(productRepository.findByProductId(productId));
		} else {
			throw new InvalidProduct(MessageConstantsUtils.INVALID_PRODUCT);
		}
	}
	
	/** 
	 * Check if the productId exists or not, if exists return true else false.
	 */
	@Override
	public Boolean productExists(String productId) {
		ProductMaster prodMast = productRepository.findByProductId(productId);
		return prodMast != null && prodMast.getId() != null;
	}

	/**
	 * Copy product properties.
	 *
	 * @param productMaster the product master
	 * @return the product dto
	 */
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
	
	/**
	 * Check for null empty product dto.
	 *
	 * @param productDto the product dto
	 * @return true, if successful
	 */
	private boolean checkForNullEmptyProductDto(ProductDto productDto) {
		return productDto != null && productDto.getProductId() != null && !productDto.getProductId().isEmpty();
	}
	
}

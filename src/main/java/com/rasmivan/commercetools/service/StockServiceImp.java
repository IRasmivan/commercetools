package com.rasmivan.commercetools.service;

import java.time.Instant;
import java.util.Arrays;
import java.util.Optional;


import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import com.rasmivan.commercetools.dto.ProductDto;
import com.rasmivan.commercetools.dto.StockDto;
import com.rasmivan.commercetools.exception.ConcurrentConflictException;
import com.rasmivan.commercetools.exception.ConcurrentPreconditionException;
import com.rasmivan.commercetools.exception.ErroneousJsonException;
import com.rasmivan.commercetools.exception.OutdateStockException;
import com.rasmivan.commercetools.repository.ProductRepository;
import com.rasmivan.commercetools.repository.StockRepository;

import com.rasmivan.commercetools.constants.GeneralConstantsUtils;
import com.rasmivan.commercetools.constants.MessageConstantsUtils;
import com.rasmivan.commercetools.domain.Stock;


/**
 * The Class StockServiceImp.
 */
@Service
@CacheConfig(cacheNames={"stock"})
public class StockServiceImp implements StockService {
	
	/** The product repository. */
	@Autowired
	ProductRepository productRepository;
	
	/** The stock repository. */
	@Autowired
	StockRepository stockRepository;
	
	/** The product service. */
	@Autowired
	ProductService productService;
	
	@Autowired
	CacheManager cacheManager;
	
	
	/**
	 * ************************************
	 * ************************************
	 * Get Current stock for the ProductId
	 * ************************************
	 * 
	 * **** Failure Scenario Coverage *****
	 * *** The below condition are checked before adding the stock into the database.
	 * ** 1) Check if the User have given valid prodidId for the stock. If its invalid Product, then respond user as ErroneousJsonException with message as 'Product not found'.
	 * 
	 * ** ~~ Special Scenario ~~
	 * ** 1) To Enable Concurrent requests for getting the stock for an productId. If an Stock is found for an productId, then populate header with Key 'ETAG' and value as the version from the database for the stock.
	 * ** 2) If there are no stock available for the productId, then I have populated an attribute called StockMessage which has message as 'There are no stock available for this productId'.
	 * 
	 * ************************************
	 * If the user have provided a valid Stock, then update the stock into Database.
	 * ************************************
	 * 
	 * ************************************
	 * ************************************
	 *
	 * @param productId the product id
	 * @return the current stock by product id
	 */
	@Override
	public ProductDto getCurrentStockByProductId(String productId) {
		ProductDto productDto;
		if(!productService.productExists(productId)) { // Check if the product Exists
			throw new ErroneousJsonException(MessageConstantsUtils.PRODUCT_NOTFOUND);
		}
		productDto = new ProductDto();
		productDto.setProductId(productId);
		productDto.setRequestTimestamp(Instant.now().toString());
		Stock stks = getStockForProductId(productId);
		if(stks != null && stks.getId() != null) { // Check if there are any stock for the provided productId
			productDto.setStock(Arrays.asList(copyStockToDto(stks)));		
		} else {
			productDto.setStockMessage(MessageConstantsUtils.NO_STOCK_PRODUCTID);
		}
		return productDto;
	}
	
	@Cacheable(key="{#productId}")
	private Stock getStockForProductId(String productId) {
		return stockRepository.getCurrentStockByProductId(productId);
	}
	
	/**
	 * ************************************
	 * ************************************
	 * Add a New Stock into the Database
	 * ************************************
	 * 
	 * **** Failure Scenario Coverage *****
	 * *** The below condition are checked before adding the stock into the database.
	 * ** 1) Check if the User have given valid JSON. If its Invalid JSON, then respond user as ErroneousJsonException with message as 'Invalid JSON Request'
	 * ** 2) Check if the User have given valid prodidId for the stock. If its invalid Product, then respond user as ErroneousJsonException with message as 'Product not found'
	 * 
	 * ************************************
	 * If the user have provided a valid Stock, then save the stock into Database.
	 * ************************************
	 * 
	 * ************************************
	 * ************************************
	 *
	 * @param stockDto the stock dto
	 * @return the product dto
	 */
	@Override
	public ProductDto addStock(StockDto stockDto) {
		if(checkForNullEmptyStockDto(stockDto)) {
			Stock stk = saveStock(stockDto); // Save a Stock
			return buildProductDto(stockDto, null, stk);
		} else {
			throw new ErroneousJsonException(MessageConstantsUtils.INVALID_STOCK_REQUEST);
		}
	}
	
	/**
	 * ************************************
	 * ************************************
	 * Update Stock into the Database
	 * ************************************
	 * 
	 * **** Failure Scenario Coverage *****
	 * *** The below condition are checked before adding the stock into the database.
	 * ** 1) Check if the User have given valid JSON. If its Invalid JSON, then respond user as ErroneousJsonException with message as 'Update failed due to erroneous JSON'
	 * ** 2) Check if the User have given valid prodidId for the stock. If its invalid Product, then respond user as ErroneousJsonException with message as 'Product not found'
	 * ** 3) Check if the User have given valid stockId that needs to be updated. If its invalid stockId, then respond user as ErroneousJsonException with message as 'Invalid stockid'
	 * ** 4) Check if the User have given time stamp for the stock greater than or equal to existing stock time stamp for the productId. If lesser than then respond user that its an 'Outdated Stock'
	 * 
	 * ** ~~ Special Scenario ~~
	 * ** 1) To Enable Concurrent requests for getting the stock for an productId. 
	 * 			If a Stock is found for a productId, The request HTTP header is checked for key 'if-Match' and the value is checked if the value matches the current database version for the stock.
	 * 			If match, the stock is updated
	 * 			else, http status precondition failed exception is thrown to user. 
	 * 
	 * ************************************
	 * If the user have provided a valid Stock, then update the stock into Database.
	 * ************************************
	 * 
	 * ************************************
	 * ************************************
	 *
	 * @param stockDto the stock dto
	 * @param request the request
	 * @param headers the headers
	 * @return the product dto
	 */
	@Override
	public ProductDto updateStock(StockDto stockDto, HttpServletRequest request, MultiValueMap<String, String> headers) {
		Stock stk;
		if(checkForNullEmptyStockDto(stockDto) && stockDto.getId() != null) { // Basic Checks
			if(!stockExists(stockDto.getId())) { // Check if the stock Exists
				throw new ErroneousJsonException(MessageConstantsUtils.INVALID_STOCK_ID);
			}
			validateHeader(stockDto, request);
			stk = saveStock(stockDto); // Updated Stock
			return buildProductDto(stockDto, headers, stk);
		} else {
			throw new ErroneousJsonException(MessageConstantsUtils.UPDATE_STOCK_FAILED);
		}
	}
	

	@CachePut(key="#stockDto.productId")
	@CacheEvict(key="#stockDto.productId + '_version'")
	private Stock saveStock(StockDto stockDto) {
		return stockRepository.save(copyStockProperties(stockDto));
	}
	
	/* (non-Javadoc)
	 * @see com.rasmivan.commercetools.service.StockService#getVersionNo(java.lang.String)
	 */
	@Override
	public MultiValueMap<String, String> getVersionNo(String productId){
		MultiValueMap<String, String> headers =  new LinkedMultiValueMap<>();
		headers.add(GeneralConstantsUtils.ETAG,getVersionByProductId(productId)); /** Populate ETAG with the version  **/
		return headers;
	}

	@CachePut(key="#productId + '_version'")
	private String getVersionByProductId(String productId) {
		return stockRepository.findByProductId(productRepository.findByProductId(productId)).getVersion().toString();
	}
	
	/**
	 * Check for null empty stock dto.
	 *
	 * @param stockDto the stock dto
	 * @return true, if successful
	 */
	private boolean checkForNullEmptyStockDto(StockDto stockDto) {
		return stockDto != null && stockDto.getProductId() != null && stockDto.getQuantity() != null && stockDto.getTimestamp() != null;
	}
	
	/**
	 * Stock exists.
	 *
	 * @param stockId the stock id
	 * @return the boolean
	 */
	private Boolean stockExists(String stockId) {
		Optional<Stock> stk = stockRepository.findById(stockId);
		return stk.isPresent();
	}

	/**
	 * ************************************
	 * ************************************
	 * Copy stock properties
	 * ************************************
	 * 
	 * **** Failure Scenario Coverage *****
	 * *** The below condition are checked before adding the stock into the database.
	 * ** 1) Check if the User have given valid prodidId for the stock. If its invalid Product, then respond user as ErroneousJsonException with message as 'Product not found'
	 * ** 2) Check if the User have given valid stockId that needs to be updated. If its invalid stockId, then respond user as ErroneousJsonException with message as 'Invalid stockid'
	 * ** 3) Check if the User have given time stamp for the stock greater than or equal to existing stock time stamp for the productId. If lesser than then respond user that its an 'Outdated Stock'
	 * 
	 * ************************************
	 * ************************************
	 *
	 * @param stockDto the stock dto
	 * @return the stock
	 */
	private Stock copyStockProperties(StockDto stockDto) {
		Stock stk;
		Optional<Stock> optStk;
		
		if(!productService.productExists(stockDto.getProductId())) {
			throw new ErroneousJsonException(MessageConstantsUtils.PRODUCT_NOTFOUND);
		}
		
		stk = new Stock();
		if(stockDto.getId() != null) {
			optStk = stockRepository.findById(stockDto.getId());
			if(optStk.isPresent()) {
				stk = optStk.get();
				if(stockDto.getTimestamp().isBefore(stk.getTimestamp())) {
					throw new OutdateStockException(MessageConstantsUtils.OUTDATED_STOCK);
				}
			} else {
				throw new ErroneousJsonException(MessageConstantsUtils.INVALID_STOCK_ID);
			}
		}
		stk.setTimestamp(stockDto.getTimestamp());
		stk.setQuantity(stockDto.getQuantity());
		stk.setProductId(productRepository.findByProductId(stockDto.getProductId()));
		return stk;
	}
	
	/**
	 * Gets the version.
	 *
	 * @param stockId the stock id
	 * @return the version
	 */
	private String getVersion(String stockId) {
		Optional<Stock> stk = stockRepository.findById(stockId);
		if(stk.isPresent()) {
			return stk.get().getVersion().toString();	
		}
		return null;
	}
	
	/**
	 * Validate header.
	 *
	 * @param stockDto the stock dto
	 * @param request the request
	 */
	private void validateHeader(StockDto stockDto, HttpServletRequest request) {
		String ifMatch = request.getHeader(GeneralConstantsUtils.IF_MATCH);
		if(ifMatch!= null) {
			String version = getVersion(stockDto.getId());
			if(version == null || !version.equals(ifMatch)) {
				throw new ConcurrentConflictException(MessageConstantsUtils.CONFLICT_MSG);
			}
		}else {
			throw new ConcurrentPreconditionException(MessageConstantsUtils.PRECONDITION_MSG);
		}
	}
	
	/**
	 * Builds the product dto.
	 *
	 * @param stockDto the stock dto
	 * @param headers the headers
	 * @param stk the stk
	 * @return the product dto
	 */
	private ProductDto buildProductDto(StockDto stockDto, MultiValueMap<String, String> headers, Stock stk) {
		ProductDto productDto  = new ProductDto();
		StockDto stkDto = copyStockToDto(stk);
		if(headers != null) {
			headers.add(GeneralConstantsUtils.ETAG,stk.getVersion().toString());
		}
		productDto.setProductId(stockDto.getProductId());
		productDto.setRequestTimestamp(Instant.now().toString());
		productDto.setStock(Arrays.asList(stkDto));
		return productDto;
	}

	private StockDto copyStockToDto(Stock stk) {
		StockDto stkDto = new StockDto();
		BeanUtils.copyProperties(stk, stkDto);
		return stkDto;
	}
	
	
}

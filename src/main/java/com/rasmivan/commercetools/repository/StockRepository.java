package com.rasmivan.commercetools.repository;

import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.rasmivan.commercetools.domain.Stock;
import com.rasmivan.commercetools.dto.TopAvailableProductDto;

/**
 * The Interface StockRepository.
 */
@Transactional
@CacheConfig(cacheNames={"stock_repo"})
public interface StockRepository  extends JpaRepository<Stock, String>, JpaSpecificationExecutor<Stock>{
	
	/**
	 * Gets the stockfor timestamp.
	 *
	 * @param startDateTime the start date time
	 * @param endDateTime the end date time
	 * @param pageable the pageable
	 * @return the stockfor timestamp
	 */
	@Query("Select NEW com.rasmivan.commercetools.dto.TopAvailableProductDto("
			+ "stk.id, "
			+ "stk.timestamp, "
			+ "stk.productId.productId, "
			+ "stk.quantity) from Stock stk "
			+ "where stk.timestamp >= :startDateTime "
			+ "and stk.timestamp <= :endDateTime "
			+ "order by stk.quantity desc")
	List<TopAvailableProductDto> getStockforTimestamp(@Param("startDateTime")Instant startDateTime, 
			@Param("endDateTime")Instant endDateTime, Pageable pageable);
	
	
	/**
	 * Gets the current stock by product id.
	 *
	 * @param productId the product id
	 * @param pageable the pageable
	 * @return the current stock by product id
	 */
	@Cacheable(value = "stocks",  key="{#productId}")
	@Query("Select NEW com.rasmivan.commercetools.domain.Stock("
			+ "stk.id, "
			+ "stk.timestamp, "
			+ "stk.quantity,"
			+ "stk.version) from Stock stk "
			+ "where stk.productId.productId = :productId "
			+ "order by stk.timestamp, stk.quantity desc")
	Stock getCurrentStockByProductId(@Param("productId")String productId);
	
	
	@CacheEvict(value = "stocks", allEntries = true)
	<S extends Stock> S save(S entity);
	
}

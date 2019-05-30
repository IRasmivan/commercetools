package com.rasmivan.commercetools.repository;

import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.rasmivan.commercetools.domain.Stock;
import com.rasmivan.commercetools.dto.StockDto;
import com.rasmivan.commercetools.dto.TopAvailableProductDto;

@Transactional
public interface StockRepository  extends JpaRepository<Stock, String>, JpaSpecificationExecutor<Stock>{
	
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
	
	
	@Query("Select NEW com.rasmivan.commercetools.dto.StockDto("
			+ "stk.id, "
			+ "stk.timestamp, "
			+ "stk.quantity,"
			+ "stk.version) from Stock stk "
			+ "where stk.productId.productId = :productId "
			+ "order by stk.timestamp, stk.quantity desc")
	List<StockDto> getCurrentStockByProductId(@Param("productId")String productId, Pageable pageable);
	
}

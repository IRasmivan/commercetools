package com.rasmivan.commercetools.repository;

import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.rasmivan.commercetools.domain.Stock;
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
	List<TopAvailableProductDto> getStockforTimestamp(@Param("startDateTime")Timestamp startDateTime, 
			@Param("endDateTime")Timestamp endDateTime, Pageable pageable);
	
}

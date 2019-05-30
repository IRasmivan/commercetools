package com.rasmivan.commercetools.repository;

import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.rasmivan.commercetools.domain.Invoice;
import com.rasmivan.commercetools.dto.TopSellingProductDto;

@Transactional
public interface InvoiceRepository  extends JpaRepository<Invoice, String>, JpaSpecificationExecutor<Invoice>{

	
	@Query("Select NEW com.rasmivan.commercetools.dto.TopSellingProductDto("
			+ "inv.productId.productId, "
			+ "inv.quantity) from Invoice inv "
			+ "where inv.timestamp >= :startDateTime "
			+ "and inv.timestamp <= :endDateTime "
			+ "order by inv.quantity desc")
	List<TopSellingProductDto> getSalesforTimestamp(@Param("startDateTime")Timestamp startDateTime, 
			@Param("endDateTime")Timestamp endDateTime, Pageable pageable);
}

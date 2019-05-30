package com.rasmivan.commercetools.repository;

import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;


import com.rasmivan.commercetools.domain.ProductMaster;

@Transactional
public interface ProductRepository  extends JpaRepository<ProductMaster, Long>, JpaSpecificationExecutor<ProductMaster>{

	
	List<ProductMaster> findByProductId(String productId);
	
}

package com.rasmivan.commercetools.repository;

import org.springframework.transaction.annotation.Transactional;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import com.rasmivan.commercetools.domain.ProductMaster;

/**
 * The Interface ProductRepository.
 */
@Transactional
public interface ProductRepository  extends JpaRepository<ProductMaster, Long>, JpaSpecificationExecutor<ProductMaster>{

	
	/**
	 * Find by product id.
	 *
	 * @param productId the product id
	 * @return the product master
	 */
	ProductMaster findByProductId(String productId);
	
}

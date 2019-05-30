package com.rasmivan.commercetools.domain;


import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;


/**
 * The Class ProductMaster.
 */
@Entity
@Table(name = "product_master")
public class ProductMaster {
	
	/** The id. */
	private Long id;
	
	private String productId;
	
	private Set<Stock> stock = new HashSet<>(0);
	
	private Set<Invoice> invoice = new HashSet<>(0);

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	@Column(name = "id", unique = true, nullable = false)
	public Long getId() {
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "productid",columnDefinition = "VARCHAR(60)", unique = true, nullable = false)
	public String getProductId() {
		return productId;
	}

	
	public void setProductId(String productId) {
		this.productId = productId;
	}

	

	/**
	 * Instantiates a new product master.
	 *
	 * @param id the id
	 * @param productId the product id
	 * @param createdDate the created date
	 * @param modifiedDate the modified date
	 */
	public ProductMaster(Long id, String productId) {
		super();
		this.id = id;
		this.productId = productId;
	}

	public ProductMaster() {
		super();
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "productId")
	@JsonManagedReference
	public Set<Stock> getStock() {
		return stock;
	}

	public void setStock(Set<Stock> stock) {
		this.stock = stock;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "productId")
	@JsonManagedReference
	public Set<Invoice> getInvoice() {
		return invoice;
	}

	public void setInvoice(Set<Invoice> invoice) {
		this.invoice = invoice;
	}

}

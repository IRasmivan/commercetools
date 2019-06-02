package com.rasmivan.commercetools.service;

import com.rasmivan.commercetools.domain.Invoice;
import com.rasmivan.commercetools.dto.InvoiceDto;

/**
 * The Interface InvoiceService.
 */
public interface InvoiceService {
	
	/**
	 * Adds the invoice.
	 *
	 * @param invoiceDto the invoice dto
	 * @return the invoice
	 */
	Invoice addInvoice(InvoiceDto invoiceDto);
	
}
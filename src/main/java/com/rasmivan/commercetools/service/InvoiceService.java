package com.rasmivan.commercetools.service;

import com.rasmivan.commercetools.domain.Invoice;
import com.rasmivan.commercetools.dto.InvoiceDto;

public interface InvoiceService {
	
	Invoice addInvoice(InvoiceDto invoiceDto);
	
}
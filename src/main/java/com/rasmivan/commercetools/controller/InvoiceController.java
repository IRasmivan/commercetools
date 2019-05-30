package com.rasmivan.commercetools.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rasmivan.commercetools.domain.Invoice;
import com.rasmivan.commercetools.dto.InvoiceDto;
import com.rasmivan.commercetools.service.InvoiceService;

@RestController
@CrossOrigin
@RequestMapping("/commercetools/api/")
public class InvoiceController {
	
	@Autowired
	InvoiceService invoiceService;
	
	@CrossOrigin
	@PostMapping(value = "v1/invoice/add")
	public ResponseEntity<Invoice> addInvoice(@RequestBody InvoiceDto invoiceDto){
		return new ResponseEntity<>(invoiceService.addInvoice(invoiceDto), HttpStatus.CREATED);
	}
	
}

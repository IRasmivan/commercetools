package com.rasmivan.commercetools.advice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.rasmivan.commercetools.constants.MessageConstantsUtils;
import com.rasmivan.commercetools.exception.DatabaseException;
import com.rasmivan.commercetools.exception.DuplicateException;
import com.rasmivan.commercetools.exception.ErroneousJsonException;
import com.rasmivan.commercetools.exception.InvalidProduct;
import com.rasmivan.commercetools.exception.OutdateStockException;


@ControllerAdvice
public class ExceptionControllerAdvice {
	
	/** The logger. */
	private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionControllerAdvice.class);
	
	@ExceptionHandler(value = InvalidProduct.class)
	public ResponseEntity<String> handleInvalidProductException(InvalidProduct e) {
		LOGGER.error(e.getMessage());
		return new ResponseEntity<>(e.getMessage(), HttpStatus.PRECONDITION_FAILED);
	}
	
	@ExceptionHandler(value = ErroneousJsonException.class)
	public ResponseEntity<String> handleErroneousJsonException(ErroneousJsonException e) {
		LOGGER.error(e.getMessage());
		return new ResponseEntity<>(MessageConstantsUtils.ERRONROUS_JSON + e.getMessage(), HttpStatus.BAD_REQUEST); // RETURN STATUS CODE AS 400
	}
	
	
	@ExceptionHandler(value = DatabaseException.class)
	public ResponseEntity<String> handleDatabaseException(DatabaseException e) {
		LOGGER.error(e.getMessage());
		return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	
	@ExceptionHandler(value = DuplicateException.class)
	public ResponseEntity<String> handleDuplicateException(DuplicateException e) {
		LOGGER.error(e.getMessage());
		return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(value = OutdateStockException.class)
	public ResponseEntity<String> handleOutdateStockException(OutdateStockException e) {
		LOGGER.error(e.getMessage());
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}

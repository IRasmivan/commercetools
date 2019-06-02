package com.rasmivan.commercetools.advice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.rasmivan.commercetools.constants.MessageConstantsUtils;
import com.rasmivan.commercetools.dto.ResponseDto;
import com.rasmivan.commercetools.exception.ConcurrentConflictException;
import com.rasmivan.commercetools.exception.ConcurrentPreconditionException;
import com.rasmivan.commercetools.exception.DatabaseException;
import com.rasmivan.commercetools.exception.DuplicateException;
import com.rasmivan.commercetools.exception.ErroneousJsonException;
import com.rasmivan.commercetools.exception.InvalidProduct;
import com.rasmivan.commercetools.exception.InvalidStatisticTimeException;
import com.rasmivan.commercetools.exception.OutdateStockException;

/**
 * The Class ExceptionControllerAdvice.
 */
@ControllerAdvice
public class ExceptionControllerAdvice {
	
	/** The logger. */
	private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionControllerAdvice.class);
	
	/**
	 * Handle invalid product exception.
	 *
	 * @param e the e
	 * @return the response entity
	 */
	@ExceptionHandler(value = InvalidProduct.class)
	public ResponseEntity<ResponseDto> handleInvalidProductException(InvalidProduct e) {
		ResponseDto responseDto = new ResponseDto();
		responseDto.setMessage(e.getMessage());
		responseDto.setStatusCode(HttpStatus.PRECONDITION_FAILED.name());
		LOGGER.error(e.getMessage());
		return new ResponseEntity<>(responseDto, HttpStatus.PRECONDITION_FAILED);  // RETURN STATUS CODE AS 412
	}
	
	/**
	 * Handle erroneous json exception.
	 *
	 * @param e the e
	 * @return the response entity
	 */
	@ExceptionHandler(value = ErroneousJsonException.class)
	public ResponseEntity<ResponseDto> handleErroneousJsonException(ErroneousJsonException e) {
		ResponseDto responseDto = new ResponseDto();
		responseDto.setMessage(MessageConstantsUtils.ERRONROUS_JSON + e.getMessage());
		responseDto.setStatusCode(HttpStatus.BAD_REQUEST.name());
		LOGGER.error(e.getMessage());
		return new ResponseEntity<>(responseDto, HttpStatus.BAD_REQUEST); // RETURN STATUS CODE AS 400
	}
	
	
	/**
	 * Handle database exception.
	 *
	 * @param e the e
	 * @return the response entity
	 */
	@ExceptionHandler(value = DatabaseException.class)
	public ResponseEntity<ResponseDto> handleDatabaseException(DatabaseException e) {
		ResponseDto responseDto = new ResponseDto();
		responseDto.setMessage(e.getMessage());
		responseDto.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.name());
		LOGGER.error(e.getMessage());
		return new ResponseEntity<>(responseDto, HttpStatus.INTERNAL_SERVER_ERROR); // RETURN STATUS CODE AS 500
	}
	
	
	/**
	 * Handle duplicate exception.
	 *
	 * @param e the e
	 * @return the response entity
	 */
	@ExceptionHandler(value = DuplicateException.class)
	public ResponseEntity<ResponseDto> handleDuplicateException(DuplicateException e) {
		ResponseDto responseDto = new ResponseDto();
		responseDto.setMessage(e.getMessage());
		responseDto.setStatusCode(HttpStatus.BAD_REQUEST.name());
		LOGGER.error(e.getMessage());
		return new ResponseEntity<>(responseDto, HttpStatus.BAD_REQUEST);  // RETURN STATUS CODE AS 400
	}
	
	/**
	 * Handle invalid statistic time exception.
	 *
	 * @param e the e
	 * @return the response entity
	 */
	@ExceptionHandler(value = InvalidStatisticTimeException.class)
	public ResponseEntity<ResponseDto> handleInvalidStatisticTimeException(InvalidStatisticTimeException e) {
		ResponseDto responseDto = new ResponseDto();
		responseDto.setMessage(e.getMessage());
		responseDto.setStatusCode(HttpStatus.BAD_REQUEST.name());
		LOGGER.error(e.getMessage());
		return new ResponseEntity<>(responseDto, HttpStatus.BAD_REQUEST);  // RETURN STATUS CODE AS 400
	}
	
	/**
	 * Handle outdate stock exception.
	 *
	 * @param e the e
	 * @return the response entity
	 */
	@ExceptionHandler(value = OutdateStockException.class)
	public ResponseEntity<String> handleOutdateStockException(OutdateStockException e) {
		LOGGER.error(e.getMessage());
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	
	/**
	 * Handle concurrent conflict exception.
	 *
	 * @param e the e
	 * @return the response entity
	 */
	@ExceptionHandler(value = ConcurrentConflictException.class)
	public ResponseEntity<ResponseDto> handleConcurrentConflictException(ConcurrentConflictException e) {
		ResponseDto responseDto = new ResponseDto();
		responseDto.setMessage(e.getMessage());
		responseDto.setStatusCode(HttpStatus.CONFLICT.name());
		LOGGER.error(e.getMessage());
		return new ResponseEntity<>(responseDto, HttpStatus.CONFLICT);  // RETURN STATUS CODE AS 409
	}
	
	
	/**
	 * Handle concurrent precondition exception.
	 *
	 * @param e the e
	 * @return the response entity
	 */
	@ExceptionHandler(value = ConcurrentPreconditionException.class)
	public ResponseEntity<ResponseDto> handleConcurrentPreconditionException(ConcurrentPreconditionException e) {
		ResponseDto responseDto = new ResponseDto();
		responseDto.setMessage(e.getMessage());
		responseDto.setStatusCode(HttpStatus.PRECONDITION_FAILED.name());
		LOGGER.error(e.getMessage());
		return new ResponseEntity<>(responseDto, HttpStatus.PRECONDITION_FAILED);  // RETURN STATUS CODE AS 412
	}

}

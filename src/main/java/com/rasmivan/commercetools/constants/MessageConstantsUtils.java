package com.rasmivan.commercetools.constants;

public final class MessageConstantsUtils {
	
	private MessageConstantsUtils() {
		// This is a default constructor.
	}
	
	public static final String INVALID_PRODUCT = "Invalid ProductId, as the product does not exists.";
	
	public static final String ERROR_SAVING = "Error while saving data  - ";
	
	public static final String UPDATE_STOCK_FAILED = "Update failed due to erroneous JSON";
	
	public static final String DUPLICATE_PRODUCT = "The Product already Exists.";
	
	public static final String DATABASE_CONNECTION = "Database Connection Error. Detail error: ";
	
	public static final String INVALID_STOCK_REQUEST = "Invalid JSON Request";
	
	public static final String PRODUCT_NOTFOUND ="Product not found";
	
	public static final String INVALID_STOCK_ID ="Invalid stockid";
	
	public static final String OUTDATED_STOCK ="Outdated Stock";
	
	public static final String ERRONROUS_JSON = "Invalid Request :: Reason - ";
	
	public static final String STATISTIC_INVALID_TIME = "Invalid time for statistics, Please provide a valid value.";
	
	public static final String NO_STOCK_PRODUCTID = "There are no stock available for this productId";
	
	public static final String CONFLICT_MSG = "Please retry, stock version mismatch";
	
	public static final String PRECONDITION_MSG = "if-Match header is missing.";

}

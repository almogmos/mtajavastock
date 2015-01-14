package com.mta.javacourse.stock.exception;

public class StockNotExistException extends Exception {
	
	/**
	 * Exception of stock that doesnt exist 
	 */
	private static final long serialVersionUID = 1L;
	//private static final long versionId= 1L;
	
	public StockNotExistException (String symbol) {
		super ("sorry, the stock " +symbol+" doesn't exist");
	}

}

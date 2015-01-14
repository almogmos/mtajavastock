package com.mta.javacourse.stock.exception;

public class StockAlreadyExistException extends Exception {
	
	/**
	 * 
	 * Exception of stock that already exist 
	 */
	private static final long serialVersionUID = 1L;
	//public static final long versionId= 1L;
	
	public StockAlreadyExistException (String symbol) {
		super ("Cant add this " +symbol+" Stock. It's already yours");
	}

}

package com.mta.javacourse.stock.exception;

public class PortfolioFullException extends Exception {

	/**
	 * 
	 * Exception of full portfolio
	 */
	private static final long serialVersionUID = 1L;
	//public static final long versionId= 1L;
	
	public PortfolioFullException (int maxSize){
		super("Cant add new stock, portfolio can have only" + maxSize + "stocks");
	}
	
}

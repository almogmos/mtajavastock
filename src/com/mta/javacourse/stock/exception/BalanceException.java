package com.mta.javacourse.stock.exception;

public class BalanceException extends Exception {
	
	/**
	 * 
	 * Exception when there is no enough balance
	 */
	private static final long serialVersionUID = 1L;
	//private static final long versionId = 1L;
	
	public BalanceException (float balance) {
		super ("sorry, but you have not enough balance to buy this, because your balance is just" + balance);
	}
}

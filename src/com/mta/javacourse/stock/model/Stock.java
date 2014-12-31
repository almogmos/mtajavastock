package com.mta.javacourse.stock.model;

import java.io.IOException;
import java.util.Date;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Stock { //The stock class.

	protected String symbol;
	protected float ask, bid;
	protected Date date;
	
	
	public Stock(){
	}
	
	public Stock(String symbol, float ask, float bid, Date date) { //c'tor
		
			setSymbolName(symbol);
			setAsk(ask);
			setBid(bid);
			setDate(date);
	}

	public Stock(Stock s){ //copy c'tor
		this(s.getSymbolName(), s.getAsk(), s.getBid(), new Date(s.getDate().getTime()));
		
	
	}

	public String getSymbolName() {
		return symbol;
	}

	public void setSymbolName(String theSymbolName) {
		symbol = theSymbolName;
	}

	public float getAsk() {
		return ask;
	}

	public void setAsk(float Ask) {
		ask = Ask;
	}

	public float getBid() {
		return bid;
	}

	public void setBid(float Bid) {
		bid = Bid;
	}

	public java.util.Date getDate() {
		return date;
	}

	public void setDate(java.util.Date Date) {
		date = Date;
	}

	public String getHtmlDescription() {
		String DateStr = date.getMonth() + "/" + date.getDate() + "/"
				+ date.getYear();
		String stockHtmlDetailsString = "<b>" + "Stock symbol: " + "</b>"
				+ symbol + ", " + "<b>" + "ask" + "</b>: " + ask + ", " + "<b>"
				+ "bid" + "</b>" + ": " + bid + ", " + "<b>" + "date" + "</b>"
				+ ": " + DateStr + ".";
		return stockHtmlDetailsString;
	}
}

package com.mta.javacourse.stock;

import java.io.IOException;
//import java.util.Calendar;
import java.util.Date;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Stock extends HttpServlet {

	private String symbol;
	private float ask, bid;
	private Date date;

	// Calendar c = Calendar.getInstance();
	// c.set(1978, 0, 5, 0, 0);
	// Date mydate = c.getTime();

	public String getSymbolName() {
		return symbol;
	}

	public void setSymbolName(String theSymbolName) {
		symbol = theSymbolName;
	}

	public float getAsk() {
		return ask;
	}

	public void setAsk(float theAsk) {
		ask = theAsk;
	}

	public float getBid() {
		return bid;
	}

	public void setBid(float theBid) {
		bid = theBid;
	}

	public java.util.Date getDate() {
		return date;
	}

	public void setDate(java.util.Date theDate) {
		date = theDate;
	}

	public String getHtmlDescription() {
		String DateStr = date.getMonth() + "/" + date.getDate() + "/"
				+ date.getYear();
		String stockHtmlDetailsString = "<b>" + "Stock symbol: " + "</b>" + symbol
				+ ", " + "<b>" + "ask" + "</b>: " + ask + ", " + "<b>" + "bid" + "</b>" +": " + bid
				+ ", " + "<b>" + "date" + "</b>" + ": " + DateStr + ".";
		return stockHtmlDetailsString;
	}
}

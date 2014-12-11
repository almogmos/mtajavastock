package com.mta.javacourse.stock.service;

import java.util.Date;

import com.mta.javacourse.stock.model.Portfolio;
import com.mta.javacourse.stock.model.Portfolio.StockStatus;
import com.mta.javacourse.stock.model.Stock;

public class PortfolioService { // the service. create instances and stocks.

	private static final int MAX_PORTFOLIO_SIZE = 5;
	
	public Portfolio getPortfolio() {
		Portfolio myPortfolio = new Portfolio(new Stock[MAX_PORTFOLIO_SIZE], new StockStatus[MAX_PORTFOLIO_SIZE], "unknown", 0); //portfolio instance

		Stock stock1 = new Stock(); //stock instance
		Stock stock2 = new Stock(); //stock instance
		Stock stock3 = new Stock(); //stock instance

		Date date = new java.util.Date(); //instance date and give date's details
		date.setYear(2014);
		date.setMonth(11);
		date.setDate(15);

		//enter details
		
		stock1.setSymbolName("PIH");
		stock1.setAsk(12.4f);
		stock1.setBid(13.1f);
		stock1.setDate(date);

		stock2.setSymbolName("AAL");
		stock2.setAsk(5.5f);
		stock2.setBid(5.78f);
		stock2.setDate(date);

		stock3.setSymbolName("CAAS");
		stock3.setAsk(31.5f);
		stock3.setBid(31.2f);
		stock3.setDate(date);

		myPortfolio.addStock(stock1); //add stock no.1 to array
		myPortfolio.addStock(stock2); //add stock no.2 to array
		myPortfolio.addStock(stock3); //add stock no.1 to array

		myPortfolio.setTitle("Portfolio #1"); //the title

		return myPortfolio;
	}
}

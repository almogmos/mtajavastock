package com.mta.javacourse.stock.service;

import java.util.Date;

import com.mta.javacourse.stock.model.Portfolio;
import com.mta.javacourse.stock.model.Stock;
import com.mta.javacourse.stock.model.Portfolio.StockStatus;;


public class PortfolioService { 
	
	public Portfolio getPortfolio() {
		
		Portfolio myPortfolio = new Portfolio();
		
		myPortfolio.setTitle("<center>Exercise 7 portfolio</center>");
		myPortfolio.setBalance(10000);
		
		
		Stock stock1 = new Stock(); //stock instance
		Stock stock2 = new Stock(); //stock instance
		Stock stock3 = new Stock(); //stock instance

		Date date = new java.util.Date(); //instance date and give date's details
		date.setYear(2014);
		date.setMonth(11);
		date.setDate(15);

		//enter details
		
		stock1.setSymbolName("PIH");
		stock1.setAsk(10);
		stock1.setBid(8.5f);
		stock1.setDate(date);
		

		stock2.setSymbolName("AAL");
		stock2.setAsk(30);
		stock2.setBid(25.5f);
		stock2.setDate(date);

		stock3.setSymbolName("CAAS");
		stock3.setAsk(20);
		stock3.setBid(15.5f);
		stock3.setDate(date);

		myPortfolio.addStock(stock1);
		myPortfolio.addStock(stock2);
		myPortfolio.addStock(stock3);
		
		myPortfolio.buyStock("PIH", 20);
		myPortfolio.buyStock("AAL", 30);
		myPortfolio.buyStock("CAAS", 40);
		
		myPortfolio.sellStock("AAL", -1);
		myPortfolio.removeStock("CAAS");
		
		return myPortfolio;
	}
}

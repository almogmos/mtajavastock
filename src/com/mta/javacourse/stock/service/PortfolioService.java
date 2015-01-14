package com.mta.javacourse.stock.service;

import java.util.Date;

import com.mta.javacourse.stock.model.Portfolio;
import com.mta.javacourse.stock.model.Portfolio.ALGO_RECOMMENDATION;
import com.mta.javacourse.stock.model.Stock;
import com.mta.javacourse.stock.model.StockStatus;


public class PortfolioService { 
	
	private static final int MAX_PORTFOLIO_SIZE = 5;

	public Portfolio getPortfolio() throws Exception {
		{
			
			Portfolio myPortfolio = new Portfolio();
			
			myPortfolio.setTitle("<center>Exercise 8 portfolio</center>");
			myPortfolio.setBalance(10000);
			
			Date date = new java.util.Date(); //instance date and give date's details
			date.setYear(2014);
			date.setMonth(11);
			date.setDate(15);

			Stock stock1 = new Stock ("PIH",10, 8.5f,date);
			Stock stock2 = new Stock ("AAL",30,25.5f,date);
			Stock stock3 = new Stock ("CAAS",20,15.5f,date);
		
			myPortfolio.addStock(stock1);
			myPortfolio.addStock(stock2);
			myPortfolio.addStock(stock3);
			
			myPortfolio.addStock(stock3);
						
			myPortfolio.buyStock("PIH", 20);
			myPortfolio.buyStock("AAL", 30);
			myPortfolio.buyStock("CAAS", 40);
			
			myPortfolio.sellStock("AAL", -1);
			myPortfolio.removeStock("CAAS");
			
			return myPortfolio;
		}
	} 
}

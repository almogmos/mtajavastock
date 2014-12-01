package com.mta.javacourse.stock.service;

import java.util.Date;

//import com.mta.javacourse.stock.StockDetails;
import com.mta.javacourse.stock.model.Portfolio;
import com.mta.javacourse.stock.model.Stock;

public class PortfolioService {

	public Portfolio getPortfolio(){
		Portfolio myPortfolio = new Portfolio();
		
		Stock stock1 = new Stock();
		Stock stock2 = new Stock();
		Stock stock3 = new Stock();

		Date date = new java.util.Date();
		date.setYear(2014);
		date.setMonth(11);
		date.setDate(15);

		stock1.setSymbolName("PIH");
		stock1.setAsk(12.4f);
		stock1.setBid(13.1f);
		stock1.setDate(date);
		//resp.getWriter().println(stock1.getHtmlDescription() + "<br>");

		stock2.setSymbolName("AAL");
		stock2.setAsk(5.5f);
		stock2.setBid(5.78f);
		stock2.setDate(date);
		//resp.getWriter().println(stock2.getHtmlDescription() + "<br>");

		stock3.setSymbolName("CAAS");
		stock3.setAsk(31.5f);
		stock3.setBid(31.2f);
		stock3.setDate(date);
		//resp.getWriter().println(stock3.getHtmlDescription() + "<br>");
		
		myPortfolio.addStock(stock1);
		myPortfolio.addStock(stock2);
		myPortfolio.addStock(stock3);
		
		myPortfolio.setTitle("The Portfolio");
		
		return myPortfolio;
		}
		
}

package com.mta.javacourse.stock.model;

import java.util.Date;


//import com.mta.javacourse.stock.StockDetails;
import com.mta.javacourse.stock.model.Stock;
import com.mta.javacourse.stock.service.PortfolioService;

public class Portfolio {

	private final static int MAX_PORTFOLIO_SIZE = 5;
	private Stock[] stocks;
	private StockStatus[] stockStatus;
	private String title;
	private int portfolioSize;

	public Portfolio()	{	
		stocks = new Stock[MAX_PORTFOLIO_SIZE];
		stockStatus = new StockStatus[MAX_PORTFOLIO_SIZE];
		portfolioSize = 0;
	}

	public void addStock(Stock stock) {
		stocks[portfolioSize] = stock;
		portfolioSize++;
	}

	public Stock[] getStocks() {
		return stocks;
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title=title;
	}
	
	public String getHtmlString() {
	//PortfolioService myPortfolio = new PortfolioService();
	//Portfolio myportfolio = new Portfolio();
		//myportfolio=myPortfolio.getPortfolio();
		String portfolioHtmlString = "<h1>"+getTitle()+"</h1>"+stocks[0].getHtmlDescription()+"<br/>"+stocks[1].getHtmlDescription()+"<br/>"+stocks[2].getHtmlDescription()+"<br/>";
		return portfolioHtmlString;

	}

	public class StockStatus {
		private String symbol;
		private float currentBid, currentAsk;
		private Date date;
		private int recommendation;
		private int stockQuantity;
		private final static int DO_NOTING = 0;
		private final static int BUY = 1;
		private final static int SELL = 2;
	}
}

/*
 * private StockStatus [] stocktatus
 * 
 * public StockStatus getStockStatus() { return stockStatus; }
 */


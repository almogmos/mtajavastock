package com.mta.javacourse.stock.model;

import java.util.Date;

import com.mta.javacourse.stock.model.Stock;
import com.mta.javacourse.stock.service.PortfolioService;

public class Portfolio {

	private final static int MAX_PORTFOLIO_SIZE = 5;
	private Stock[] stocks; //stocks array
	private StockStatus[] stockStatus; //stock's status
	private String title; 
	private int portfolioSize=0;

	public Portfolio(Stock[] Stocks, StockStatus[] nStockStatus, String Title, int PortfolioSize) { //c'tor
		
		/*setStocks(Stocks);
 		setStockStatus(nStockStatus);
 		setTitle(Title);
 		setPortfolioSize(PortfolioSize);
 		*/
		stocks = new Stock[MAX_PORTFOLIO_SIZE]; //initialize the stocks array
		stockStatus = new StockStatus[MAX_PORTFOLIO_SIZE]; //initialize the stockStatus array
		setTitle(title);
		setPortfolioSize(PortfolioSize); //initialize size

	}

	public Portfolio (Portfolio p) //copy c'tor
	{
		this(new Stock[MAX_PORTFOLIO_SIZE], new StockStatus[MAX_PORTFOLIO_SIZE], "UNKNOWE", 0);
		
		this.portfolioSize = p.portfolioSize;
		this.setTitle(p.getTitle());
		
		for (int i = 0; i < p.portfolioSize; i++){
			stocks[i] = new Stock (p.getStocks()[i]);
			stockStatus[i] = new StockStatus (p.getStockStatus()[i]);	
		}	
		
	}

	public StockStatus[] getStockStatus() {
		return stockStatus;
	}

	public void setStockStatus(StockStatus[] stockStatus) {
		this.stockStatus = stockStatus;
	}

	public void addStock(Stock stock) { // get stock and put him in the stock's array
		stocks[portfolioSize] = stock;
		portfolioSize++;
	}

	public void setStocks(Stock[] stocks) {
		this.stocks = stocks;
	}

	public Stock[] getStocks() {
		return stocks;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getPortfolioSize() {
		return portfolioSize;
	}

	public void setPortfolioSize(int portfolioSize) {
		this.portfolioSize = portfolioSize;
	}
	
	public String getHtmlString() {
		String portfolioHtmlString = "<h1>" + getTitle() + "</h1>"
				+ stocks[0].getHtmlDescription() + "<br/>"
				+ stocks[1].getHtmlDescription() + "<br/>"
				+ stocks[2].getHtmlDescription() + "<br/>";
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
	
		public StockStatus(String string, float nBid, float nAsk, Date ndate, int nRecommendation, int nStockQuantity){ //c'tor
			symbol = string;
			currentBid = nBid;
			currentAsk = nAsk;
			date = ndate;
			recommendation = nRecommendation;
			stockQuantity = nStockQuantity;
			}
		
		
		public StockStatus(StockStatus stockStatus) { //copy c'tor
			if(this.symbol != null)
			{
				this.symbol = stockStatus.symbol;
				this.currentAsk = stockStatus.currentAsk;
				this.currentAsk = stockStatus.currentBid;
				this.date = stockStatus.date;
				this.recommendation = stockStatus.recommendation;
				this.stockQuantity = stockStatus.stockQuantity;
			}
		}


		public String getSymbol() {
			return symbol;
		}


		public void setSymbol(String symbol) {
			this.symbol = symbol;
		}


		public float getCurrentBid() {
			return currentBid;
		}


		public void setCurrentBid(float currentBid) {
			this.currentBid = currentBid;
		}


		public float getCurrentAsk() {
			return currentAsk;
		}


		public void setCurrentAsk(float currentAsk) {
			this.currentAsk = currentAsk;
		}


		public Date getDate() {
			return date;
		}


		public void setDate(Date date) {
			this.date = date;
		}


		public int getRecommendation() {
			return recommendation;
		}


		public void setRecommendation(int recommendation) {
			this.recommendation = recommendation;
		}


		public int getStockQuantity() {
			return stockQuantity;
		}

		public void setStockQuantity(int stockQuantity) {
			this.stockQuantity = stockQuantity;
		}
	}

	public void deleteStock(int location) { //delete stock we want
		if(location == portfolioSize){
				this.portfolioSize--;
				}
			else{
				this.portfolioSize--;
				for(int i = location; i<= portfolioSize-1; i++){
					this.stocks[i] = this.stocks[i+1];
				}
				
			}
	}
	
}
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
	private float balance=0; 

	/*public Portfolio(){ //instance
		stocks = new Stock[MAX_PORTFOLIO_SIZE]; //initialize the stocks array
		stockStatus = new StockStatus[MAX_PORTFOLIO_SIZE]; //initialize the stockStatus array
	}
*/

	public Portfolio() { //instance
		stocks = new Stock[MAX_PORTFOLIO_SIZE]; //initialize the stocks array
		stockStatus = new StockStatus[MAX_PORTFOLIO_SIZE];
	}

	
	public Portfolio(Stock[] Stocks, StockStatus[] StockStatus, String Title, int PortfolioSize, float balance) { //c'tor
		
		/*setStocks(Stocks);
 		setStockStatus(nStockStatus);
 		setTitle(Title);
 		setPortfolioSize(PortfolioSize);
 		*/
		setStocks(Stocks);
		setStockStatus(StockStatus);
		setTitle(title);
		setPortfolioSize(PortfolioSize); //initialize size
		setBalance(balance);

	}

	public Portfolio (Portfolio p) //copy c'tor
	{
		this();//(new Stock[MAX_PORTFOLIO_SIZE], new StockStatus[MAX_PORTFOLIO_SIZE], "UNKNOWE", 0, 0);
		
		this.portfolioSize = p.portfolioSize;
		this.setTitle(p.getTitle());
		
		for (int i = 0; i < p.portfolioSize; i++){
			stocks[i] = new Stock (p.getStocks()[i]);
			stockStatus[i] = new StockStatus (p.getStockStatus()[i]);	
		}	
		this.balance = p.balance;
		
	}

	public StockStatus[] getStockStatus() {
		return stockStatus;
	}

	public void setStockStatus(StockStatus[] stockStatus) {
		this.stockStatus = stockStatus;
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
	
	public float getBalance() {
		return balance;
	}

	public void setBalance(float balance) {
		this.balance = balance;
	}
	
	public void addStock(Stock stock) { // get new stock name and put it in the stock's array
		if (portfolioSize>=MAX_PORTFOLIO_SIZE)
		{
			System.out.println ("Cant add new stock, portfolio can have only " + portfolioSize + " stocks");
		}
		else {
			for (int i = 0; i < portfolioSize; i++)
			{
				if(stock.getSymbolName().equals(this.stocks[i].getSymbolName()))
					System.out.println ("Cant add this Stock. It's already yours");
				break;
			}
			stocks[portfolioSize] = stock;
			stockStatus[portfolioSize] = new StockStatus(stock.getSymbolName(), stock.getAsk(), stock.getBid(), stock.getDate(), ALGO_RECOMMENDATION.DO_NOTING, 0);
			portfolioSize++;	
		}
		
	}
	
	public void updateBalance (float amount){ //the budget we have to the portfolio 
		this.balance += amount;
	}
	
	
	public boolean removeStock (String symbol) { //when we want to sell some, or all, symbol stocks
		for (int i = 0; i < portfolioSize; i++) {
			if (symbol.equals(this.stocks[i].getSymbolName())){
				sellStock(this.stocks[i].getSymbolName(), this.stockStatus[i].getStockQuantity());
				this.stocks[i]=null;
				this.stockStatus[i]=null;
				if (i+1==portfolioSize)//the last stock in the array
					this.portfolioSize--;
				else {
					for (int j=i; j<portfolioSize-1; j++) {//update the array
						this.stocks[j]=this.stocks[j+1];
						this.stockStatus[j]=this.stockStatus[j+1];
					}
					portfolioSize--;
				}
				return true;					
			}		
		}		
		return false;
	}
	

	public boolean sellStock (String symbol, int stockQuantity) { //the act of the sell (with bid price)
		int askingQuantity = 0;
		
		for (int i = 0; i < portfolioSize; i++) {
			if (symbol.equals(this.stocks[i].getSymbolName())){
				
				if (stockQuantity==-1){ //sell all this symbol stocks
					updateBalance(this.stockStatus[i].getStockQuantity() * this.stocks[i].getBid());
					this.stockStatus[i].setStockQuantity(0);
				}

				else if (stockQuantity<-1){
					System.out.println ("Negative stock quantity is not an option");
					return false;
				}
	
				else { //bigger than -1
					if (stockQuantity > this.stockStatus[i].getStockQuantity()){
						askingQuantity = stockQuantity; 
						stockQuantity = this.stockStatus[i].getStockQuantity();
						System.out.println ("ERROR! You asked to sell " + askingQuantity + "but you had " + stockQuantity);
					}
					else {
					updateBalance(stockQuantity * this.stocks[i].getBid());
					this.stockStatus[i].setStockQuantity(this.stockStatus[i].getStockQuantity() - stockQuantity);
					}
				}
				return true;
			}
			
		}
		return false;
	}
	
	public boolean buyStock (String symbol, int stockQuantity) {//buy stock with ask price
		int askingQuantity = 0;
		float realQuantity = 0;
		for (int i = 0; i < portfolioSize; i++) {
			if (symbol.equals(this.stocks[i].getSymbolName())){
				
				if (stockQuantity==-1){//buy all symbol stocks we could with our balance
					realQuantity = getBalance() / this.stocks[i].getAsk();
					System.out.println ("You bought "+ realQuantity +" stocks.");
					updateBalance(-1 * (int)realQuantity * this.stocks[i].getAsk());
					this.stockStatus[i].setStockQuantity(this.stockStatus[i].getStockQuantity() + (int)realQuantity);		
				}

				else if (stockQuantity<-1){
					System.out.println ("Negative stock quantity is not an option");
					return false;
				}
	
				else { //bigger that -1
					if (stockQuantity * this.stocks[i].getAsk() > this.balance) {
						askingQuantity = stockQuantity; 
						realQuantity = getBalance() / this.stocks[i].getAsk();
						System.out.println ("You asked to buy " + askingQuantity + " stocks, but you allowed to buy " + realQuantity + " stocks, so we bought only them");
						updateBalance(-1 * (int)realQuantity * this.stocks[i].getAsk());
						this.stockStatus[i].setStockQuantity(this.stockStatus[i].getStockQuantity() + (int)realQuantity);		
					}
					else {
					updateBalance(-1 * stockQuantity * this.stocks[i].getAsk());
					this.stockStatus[i].setStockQuantity(this.stockStatus[i].getStockQuantity() + stockQuantity);		
					}
				}
				return true;
			}
			
		}	
		
		return false;		
	}

	public float getStocksValue (Stock stocks[]){ //the value of all our stocks in portfolio
		float totalValueOfAllStocks = 0;
		
		for (int i = 0; i < portfolioSize; i++){
			totalValueOfAllStocks += this.stockStatus[i].getCurrentBid() * this.stockStatus[i].getStockQuantity();
		}
		return totalValueOfAllStocks;
		
	}
	
	
	public float getTotalValue (Stock stocks[]){ //the value of our portfolio and the balance we have
		float totalValue = 0;
		totalValue = getBalance() + getStocksValue(stocks);
		
		return totalValue;
		
	}
	
	
	public String getHtmlString() {
		String portfolioHtmlString = "<h1>" + getTitle() + "</h1><br>";
		portfolioHtmlString += "<b>Total Portfolio Value: </b>" + getTotalValue(stocks) + "$, <b>Total Stock value: </b>" + getStocksValue(stocks) + "$, <b>Balance: </b>" + getBalance() + "<br>";
		portfolioHtmlString += "<b>Stock Details:</b><br>";
		for (int i = 0; i < portfolioSize; i++) {
			portfolioHtmlString += "<b>Stock </b>" + (i+1) + ": " + stocks[i].getHtmlDescription() + "<br>";
		}/*
		+ stocks[0].getHtmlDescription() + "<br/>"
				+ stocks[1].getHtmlDescription() + "<br/>"
				+ stocks[2].getHtmlDescription() + "<br/>";*/
		return portfolioHtmlString;
	}
	
	private static enum ALGO_RECOMMENDATION {DO_NOTING, BUY, SELL};

	public class StockStatus {
		
		private String symbol;
		private float currentBid, currentAsk;
		private Date date;
		private ALGO_RECOMMENDATION recommendation;
		private int stockQuantity;
		/*private final static int DO_NOTING = 0;
		private final static int BUY = 1;
		private final static int SELL = 2;*/
	
		public StockStatus(String string, float ask, float bid, Date ndate, ALGO_RECOMMENDATION nRecommendation, int nStockQuantity){ //c'tor
			symbol = string;
			currentAsk = ask;
			currentBid = bid;
			date = ndate;
			recommendation = nRecommendation;
			stockQuantity = nStockQuantity;
			}
		
		
		public StockStatus(StockStatus stockStatus) { //copy c'tor
			if(this.symbol != null)
			{
				this.symbol = stockStatus.symbol;
				this.currentAsk = stockStatus.currentAsk;
				this.currentBid = stockStatus.currentBid;
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


		public float getCurrentAsk() {
			return currentAsk;
		}


		public void setCurrentAsk(float currentAsk) {
			this.currentAsk = currentAsk;
		}



		public float getCurrentBid() {
			return currentBid;
		}


		public void setCurrentBid(float currentBid) {
			this.currentBid = currentBid;
		}


		public Date getDate() {
			return date;
		}


		public void setDate(Date date) {
			this.date = date;
		}


		public ALGO_RECOMMENDATION getRecommendation() {
			return recommendation;
		}


		public void setRecommendation(ALGO_RECOMMENDATION recommendation) {
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
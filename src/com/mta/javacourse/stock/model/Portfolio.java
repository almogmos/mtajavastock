package com.mta.javacourse.stock.model;

import java.util.Date;

import com.mta.javacourse.stock.exception.BalanceException;
import com.mta.javacourse.stock.exception.PortfolioFullException;
import com.mta.javacourse.stock.exception.StockAlreadyExistException;
import com.mta.javacourse.stock.exception.StockNotExistException;
import com.mta.javacourse.stock.model.Stock;
import com.mta.javacourse.stock.model.StockStatus;

public class Portfolio {

	private final static int MAX_PORTFOLIO_SIZE = 5;
	public enum ALGO_RECOMMENDATION {DO_NOTING, BUY, SELL};
	private StockStatus[] stockStatus; //stock's status
	private String title; 
	private int portfolioSize=0;
	private float balance=0;
	
	
	public Portfolio(){
		 	setStockStatus(new StockStatus[MAX_PORTFOLIO_SIZE]);
		 	}
	
	public Portfolio(StockStatus[] stockStatus, String Title, int PortfolioSize, float balance) { //c'tor
		
		setStockStatus(stockStatus);
		setTitle(title);
		setPortfolioSize(PortfolioSize); //initialize size
		setBalance(balance);
	}

	public Portfolio (Portfolio p) //copy c'tor
	{
		this();
		
		for (int i = 0; i < p.portfolioSize; i++){
			stockStatus[i] = new StockStatus (p.getStockStatus()[i]);	
		}	
		this.balance = p.balance;
		this.portfolioSize = p.portfolioSize;
		this.title = p.title;
	}

	public StockStatus[] getStockStatus() {
		return stockStatus;
	}
	
	public void setStockStatus(StockStatus[] stockStatus) {
		this.stockStatus = stockStatus;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getPortfolioSize() {
		return portfolioSize=0;
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
	
	
	public void addStock(Stock stock) throws Exception { // get new stock name and put it in the stock's array
		if (portfolioSize >= MAX_PORTFOLIO_SIZE){
			//System.out.println ("Cant add new stock, portfolio can have only " + portfolioSize + " stocks");
			throw new PortfolioFullException (getPortfolioSize());
		}
		else {
			for (int i = 0; i < portfolioSize; i++)
			{
			if(stock.getSymbolName().equals(stockStatus[i].getSymbolName()) )
			{
				//System.out.println ("Cant add this Stock. It's already yours");
				throw new StockAlreadyExistException (stock.getSymbolName());
			}
		}
			stockStatus[portfolioSize] = new StockStatus(stock.getSymbolName(), stock.getAsk(), stock.getBid(), stock.getDate(), ALGO_RECOMMENDATION.DO_NOTING, 0);
			portfolioSize++;
		}			
	}
	
	public void updateBalance (float amount){ //the budget we have to the portfolio 
		this.balance += amount;
	}
	
	
	public void removeStock (String symbol)  throws StockNotExistException { //when we want to sell some, or all, symbol stocks
		for (int i = 0; i < portfolioSize; i++) {
			if (symbol.equals(this.stockStatus[i].getSymbolName())){
				sellStock(this.stockStatus[i].getSymbolName(), this.stockStatus[i].getStockQuantity());
				this.stockStatus[i]=null;
				if (i+1==portfolioSize)//the last stock in the array
					this.portfolioSize--;
				else {
					for (int j=i; j<portfolioSize-1; j++) {//update the array
						this.stockStatus[j]=this.stockStatus[j+1];
					}
					portfolioSize--;
				}
			}		
		}
		throw new StockNotExistException (symbol);		
	}
	

	public void sellStock (String symbol, int stockQuantity) { //the act of the sell (with bid price)
		int askingQuantity = 0;
		
		for (int i = 0; i < portfolioSize; i++) {
			if (symbol.equals(this.stockStatus[i].getSymbolName())){
				
				if (stockQuantity==-1){ //sell all this symbol stocks
					updateBalance(stockStatus[i].getStockQuantity() * this.stockStatus[i].getBid());
					this.stockStatus[i].setStockQuantity(0);
				}

				else if (stockQuantity<-1){
					System.out.println ("Negative stock quantity is not an option");
					return;
				}
	
				else { //bigger than -1
					if (stockQuantity > this.stockStatus[i].getStockQuantity()){
						askingQuantity = stockQuantity; 
						stockQuantity = this.stockStatus[i].getStockQuantity();
						System.out.println ("ERROR! You asked to sell " + askingQuantity + "but you had " + stockQuantity);
					}
					else {
					updateBalance(stockQuantity * this.stockStatus[i].getBid());
					this.stockStatus[i].setStockQuantity(this.stockStatus[i].getStockQuantity() - stockQuantity);
					}
				}
			}
			
		}
	}
	
	public void buyStock (String symbol, int stockQuantity)  throws BalanceException {//buy stock with ask price
		int askingQuantity = 0;
		float realQuantity = 0;
		for (int i = 0; i < portfolioSize; i++) {
			if (symbol.equals(this.stockStatus[i].getSymbolName())){
				
				if (stockQuantity==-1){//buy all symbol stocks we could with our balance
					realQuantity = getBalance() / this.stockStatus[i].getAsk();
					System.out.println ("You bought "+ realQuantity +" stocks.");
					updateBalance(-1 * (int)realQuantity * this.stockStatus[i].getAsk());
					this.stockStatus[i].setStockQuantity(this.stockStatus[i].getStockQuantity() + (int)realQuantity);		
				}

				else if (stockQuantity<-1){
					System.out.println ("Negative stock quantity is not an option");
					return;
				}
	
				else { //bigger that -1
					if (stockQuantity * this.stockStatus[i].getAsk() > this.balance) {
						//askingQuantity = stockQuantity; 
						//realQuantity = getBalance() / this.stockStatus[i].getAsk();
						//System.out.println ("sorry, but you have not enough balance to buy this");//("You asked to buy " + askingQuantity + " stocks, but you allowed to buy " + realQuantity + " stocks, so we bought only them");
						throw new BalanceException (getBalance());
						//updateBalance(-1 * (int)realQuantity * this.stockStatus[i].getAsk());
						//this.stockStatus[i].setStockQuantity(this.stockStatus[i].getStockQuantity() + (int)realQuantity);		
					}
					else {
					updateBalance(-1 * stockQuantity * this.stockStatus[i].getAsk());
					this.stockStatus[i].setStockQuantity(this.stockStatus[i].getStockQuantity() + stockQuantity);		
					}
				}
			}
			
		}	
		
	}

	public float getStocksValue (Stock stocks[]){ //the value of all our stocks in portfolio
		float totalValueOfAllStocks = 0;
		
		for (int i = 0; i < portfolioSize; i++){
			totalValueOfAllStocks += this.stockStatus[i].getBid() * this.stockStatus[i].getStockQuantity();
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
		portfolioHtmlString += "<b>Total Portfolio Value: </b>" + getTotalValue(stockStatus) + "$, <b>Total Stock value: </b>" + getStocksValue(stockStatus) + "$, <b>Balance: </b>" + getBalance() + "<br>";
		portfolioHtmlString += "<b>Stock Details:</b><br>";
		for (int i = 0; i < portfolioSize; i++) {
			portfolioHtmlString += "<b>Stock </b>" + (i+1) + ": " + stockStatus[i].getHtmlDescription() + "<br>";
		}
		return portfolioHtmlString;
	}
}
	
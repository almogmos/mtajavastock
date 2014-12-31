package com.mta.javacourse.stock.model;

import java.util.Date;

import com.mta.javacourse.stock.model.Portfolio.ALGO_RECOMMENDATION;


public class StockStatus extends Stock  {
	
	private ALGO_RECOMMENDATION recommendation;
	private int stockQuantity;
	
	public StockStatus(String symbol, float ask, float bid, Date date, ALGO_RECOMMENDATION nrecommendation, int nstockQuantity){ //c'tor
		super(symbol, ask,bid, date);
		recommendation = nrecommendation;
		stockQuantity = nstockQuantity;
	}
	
	public StockStatus (StockStatus s) {// copy c'tor
		this.symbol = s.symbol;
		this.ask = s.ask;
		this.bid = s.bid;
		this.date = new Date(s.date.getTime());
		this.recommendation = s.recommendation;
		this.stockQuantity = s.stockQuantity;
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


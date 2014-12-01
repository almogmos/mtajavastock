package com.mta.javacourse.stock.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.mta.javacourse.stock.model.Stock;

import com.mta.javacourse.stock.model.Portfolio;
import com.mta.javacourse.stock.service.PortfolioService;

public class PortfolioServlet extends HttpServlet{

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		resp.setContentType("text/html");
	
	PortfolioService portfolioService = new PortfolioService();
	Portfolio portfolio = portfolioService.getPortfolio();
	Stock[] stocks = portfolio.getStocks();

	resp.getWriter().println(portfolio.getHtmlString());
			
	}
}
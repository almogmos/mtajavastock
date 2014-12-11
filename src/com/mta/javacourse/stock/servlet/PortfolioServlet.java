package com.mta.javacourse.stock.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.mta.javacourse.stock.model.Stock;

import com.mta.javacourse.stock.model.Portfolio;
import com.mta.javacourse.stock.service.PortfolioService;

public class PortfolioServlet extends HttpServlet { // the servlet of portfolio

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		resp.setContentType("text/html");

		PortfolioService portfolioService = new PortfolioService(); // instance portfolio service
		Portfolio portfolio = portfolioService.getPortfolio(); // get portfolio from the portfolio service to portfolio
		Portfolio portfolio1 = portfolioService.getPortfolio();
		Portfolio portfolio2 = new Portfolio(portfolio1);
		
		Stock[] stocks = portfolio.getStocks(); 

		
		// print all the portfolio with changes to chack the copy c'tor
		resp.getWriter().println(portfolio.getHtmlString());  
		portfolio2.setTitle("Potfolio #1");
		resp.getWriter().println(portfolio1.getHtmlString());
		portfolio2.setTitle("Potfolio #2");
		resp.getWriter().println(portfolio2.getHtmlString());
		resp.getWriter().println("<h1>Delete stock at Portfolio #1</h1>");
		portfolio1.deleteStock(0);
		resp.getWriter().println(portfolio1.getHtmlString());
		resp.getWriter().println("<h1>Portfolio #2 regular</h1>");
		resp.getWriter().println(portfolio2.getHtmlString());
		resp.getWriter().println("<h1>Change bid in Portfolio #2</h1>");
		portfolio2.getStocks()[2].setBid((float)55.55);
		resp.getWriter().println(portfolio1.getHtmlString());
		resp.getWriter().println(portfolio2.getHtmlString());
		
		
	}
}

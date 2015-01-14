package com.mta.javacourse.stock.servlet;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mta.javacourse.stock.exception.BalanceException;
import com.mta.javacourse.stock.exception.PortfolioFullException;
import com.mta.javacourse.stock.exception.StockAlreadyExistException;
import com.mta.javacourse.stock.exception.StockNotExistException;
import com.mta.javacourse.stock.model.Stock;
import com.mta.javacourse.stock.model.Portfolio;
import com.mta.javacourse.stock.service.PortfolioService;

@SuppressWarnings("serial")
public class PortfolioServlet extends HttpServlet { // the servlet of portfolio

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		resp.setContentType("text/html");
		
		PortfolioService portfolioService = new PortfolioService(); // instance portfolio service
	
		try {
			Portfolio portfolio = portfolioService.getPortfolio();	
			resp.getWriter().println(portfolio.getHtmlString() + "<br>");
		}
		catch (BalanceException e1) {
			resp.getWriter().println(e1.getMessage());
		} catch (PortfolioFullException e2) {
			resp.getWriter().println(e2.getMessage());
		} catch (StockAlreadyExistException e3) {
				resp.getWriter().println(e3.getMessage());
		} catch (StockNotExistException e4) {
			resp.getWriter().println(e4.getMessage());
		}  
		catch (Exception e) {
			throw new RuntimeException(e);
		}
		
		}
	}

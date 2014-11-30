package com.mta.javacourse;

import java.util.Date;
import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Stock extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		resp.setContentType("text/html");

		StockDetails stock1 = new StockDetails();
		StockDetails stock2 = new StockDetails();
		StockDetails stock3 = new StockDetails();

		Date date = new java.util.Date();
		date.setYear(2014);
		date.setMonth(11);
		date.setDate(15);

		stock1.setSymbolName("PIH");
		stock1.setAsk(12.4f);
		stock1.setBid(13.1f);
		stock1.setDate(date);
		resp.getWriter().println(stock1.getHtmlDescription() + "<br>");

		stock2.setSymbolName("AAL");
		stock2.setAsk(5.5f);
		stock2.setBid(5.78f);
		stock2.setDate(date);
		resp.getWriter().println(stock2.getHtmlDescription() + "<br>");

		stock3.setSymbolName("CAAS");
		stock3.setAsk(31.5f);
		stock3.setBid(31.2f);
		stock3.setDate(date);
		resp.getWriter().println(stock3.getHtmlDescription() + "<br>");
	}
}
package com.mta.javacourse.stock;

import java.io.IOException;
import javax.servlet.http.*;
import java.math.*;

@SuppressWarnings("serial")
public class HelloServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		resp.setContentType("text/html");

		int num1 = 4;
		int num2 = 3;
		int num3 = 7;
		
		double pie = Math.PI;
		double radius = 50;
		double angle = 30;
		double radianAngle = angle*pie/180;
		int hypotenuseLength = 50;
		double opposit = Math.sin(radianAngle)*hypotenuseLength;
		int base = 20;
		int exp = 13;
		int result = (num3*num3)+(num3-num1-num2);
		
		double area = pie*Math.pow(radius, 2); 
		
		String resultStr = new String("<h1>Result of "+num3+"*"+num3+"+"+num3+"-"+num1+"-"+num2+"="+result+"</h1>");
		
		String line1 = new String("<h1>calculation 1: Area of circle with radius "+radius+" is: "+area+"cm"+"</h1>");
		String line2 = new String("<h1>calculation 2: Length of opposite where angle B is: "+opposit+"cm"+"</h1>");
		String line3 = new String("<h1>calculation 3: Power of 20 with exp of 13 is: "+Math.pow(base, exp)+"</h1>");
		
		
		resp.getWriter().println(resultStr);
		resp.getWriter().println(line1);
		resp.getWriter().println(line2);
		resp.getWriter().println(line3);
	}
}
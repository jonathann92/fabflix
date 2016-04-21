package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import objects.Customer;
import objects.Movie;
import objects.MutableInt;
import services.CheckoutService;
import services.Service;

/**
 * Servlet implementation class Checkout
 */
@WebServlet("/Checkout")
public class Checkout extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String ref = request.getHeader("Referer");
        HttpSession session = request.getSession(true);
        String context = request.getContextPath();
        Customer user = (Customer) session.getAttribute("user");
        Map<Movie, MutableInt> cart = (Map<Movie, MutableInt>) session.getAttribute("cart");
        
        String first = request.getParameter("first");
        String last = request.getParameter("last");
        String card = request.getParameter("card");
        String year = request.getParameter("year");
        String month = request.getParameter("month");
        String day = request.getParameter("day");
        
        List<Integer> transactions = null;
        
        try{
        	if(CheckoutService.runCreditCard(first, last, card, year, month, day)){
        		System.out.println("SUCCESS");
        		transactions = CheckoutService.recordTransaction(user, cart);
        		System.out.println("After transactions");
        		session.removeAttribute("cart");
        		request.setAttribute("transactions", transactions);
        		Service.forward(request, response, "/WEB-INF/CheckoutSuccess.jsp");
        		return;
        	} else {
        		System.out.println("Wrong credit card info");
        		request.setAttribute("error", "wrong information");
        		response.sendRedirect(context + "/checkout.jsp");
        	}
        } catch (Exception e){
        	System.out.println("exception in Checkout.java");
        	System.out.println(e.getMessage());
        	//response.sendRedirect(context + "/404.jsp");
        	out.print(e.getLocalizedMessage());
        }
        
        System.out.println("WELP");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

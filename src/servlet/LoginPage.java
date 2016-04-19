package servlet;

import java.io.IOException;
import java.sql.*;

import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import objects.*;
import services.CustomerService;
import cache.Cache;
/**
 * Servlet implementation class LoginPage
 */
@WebServlet("/LoginPage")
public class LoginPage extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected Customer custInfo(String username, String password) throws Exception{
		Customer cust = null;
		if(username != null && password != null)
			cust = CustomerService.verifyCredentials(username, password);


		return cust;
	}


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession(true);
        
        String error = null;
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String referrer = (String) session.getAttribute("refpage");
        String context = request.getContextPath();
        String redirect = "";
        out.print(context);
        out.print("REF: " + referrer);
        
        try{
        	Customer user = custInfo(username, password);
	        if(user != null){
	        	out.print("SUCCESS");
	        	session.setAttribute("user", user);
	        	session.removeAttribute("Referer");
	        	session.removeAttribute("refpage");
	        	redirect = referrer;
	        } else {
	        	session.setAttribute("error", "Invalid Credentials");
	        	redirect = context + "/LoginPrompt.jsp";
	        	
	        }
        } catch (Exception e){
        	session.setAttribute("error", "SQL Server Down");
        	redirect = context + "/LoginPrompt.jsp";
        }
                
        response.sendRedirect(redirect);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

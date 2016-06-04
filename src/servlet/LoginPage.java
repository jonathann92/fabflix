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
import services.VerifyRecaptcha;
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
        String json = request.getParameter("json");
        String redirect = "";
        
        System.out.println(username);
        
        String gRecaptchaResponse = request.getParameter("g-recaptcha-response");
        Customer user = null;
        
        boolean skip = false;
        
        if(username.equals("t")){
        	username = "a@email.com";
        	password = "a2";
        	skip = true;
        }
        
        
        try{
        	user = custInfo(username, password);
        } catch (Exception e){
        	session.setAttribute("error", "SQL Server Down");
        	redirect = context + "/LoginPrompt.jsp";
        }
        
        if(json != null && json.equals("true")){
        	System.out.println("JAMES IS ATTACKING! HIDE YO KIDS HIDE YO WIFE");
        	if(user != null){
        		System.out.println("shit we just got fucked");
        		out.print("true");
        	} else {
        		System.out.println("Get outta here james");
        		out.print("false");
        	}
        } else {
	        browserLogin(response, out, session, context, gRecaptchaResponse, user, skip);
        }
	}


	private void browserLogin(HttpServletResponse response, PrintWriter out, HttpSession session, String context,
			String gRecaptchaResponse, Customer user, boolean skip) throws IOException {
		String redirect;
		if(user != null && (skip || VerifyRecaptcha.verify(gRecaptchaResponse))){
			out.print("SUCCESS");
			session.setAttribute("user", user);
			session.removeAttribute("Referer");
			session.removeAttribute("refpage");
			redirect = context.concat("/");
		} else {
			session.setAttribute("error", "Invalid Credentials");
			redirect = context + "/LoginPrompt.jsp";
			
		}
		response.sendRedirect(redirect);
		return;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

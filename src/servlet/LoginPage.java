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
import cache.Cache;
/**
 * Servlet implementation class LoginPage
 */
@WebServlet("/LoginPage")
public class LoginPage extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected boolean verifyCredentials(String user, String pass){
		// TODO
		
		
		return false;
	}
	
	protected void login(HttpServletResponse response) throws IOException{
		response.sendRedirect("GrabSession");
	}
	
	protected void writeHTML(PrintWriter out){
		 out.print("<!DOCTYPE html><html><head><meta charset=\"UTF-8\"><title>Insert title here</title></head>");
	     out.print("<body><form action=\"LoginPage\" method=POST>Username:  <input type=text length=20 name=username><br>");
	     out.print("Password:  <input type=text length=20 name=password><br><input type=submit></form></body></html>");
	}
	
	

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        
        
        
        out.print("<br/>");
        out.print("SIZE: " + Cache.movies_id.size());
        out.print("<br/>");
        out.print("<a href=\"LoadMovie\"> LoadMovie </a>");

        
        
        out.println("maker pass = 'pass' to mimmick logging in'");
        out.println("<br/> make password != 'pass' to mimmick invalid login credentials");
        
        writeHTML(out);
        
        HttpSession session = request.getSession(true);
        
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        
        if(username != null){ // checks for first time loading
        	//if(verifyCredentials(username, password)){
        	if(password.equals("pass")){
	        	session.setAttribute("username", username);
	        	session.setAttribute("password", password);
	        	
	        	out.print("<br/>");
	        	out.print("Login success!");
	        	out.print("<br/>");
	            out.print("James go to this page <a href=\"GrabSession\">page</a>");
	            
	            
	            // redirects
	            response.sendRedirect("GrabSession");
	            
	            // Forwards
	            RequestDispatcher rd = request.getRequestDispatcher("GrabSession");
	            rd.forward(request, response);
        	} else {
        		out.print("Invalid login credentials please try again");
        	}
        } else
        	out.println("FIRST LOAD");
        
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

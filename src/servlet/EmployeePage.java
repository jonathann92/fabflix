package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import objects.Employee;
import services.CustomerService;
import services.EmployeeService;
import services.Service;
import services.VerifyRecaptcha;

/**
 * Servlet implementation class EmployeePage
 */
@WebServlet("/EmployeePage")
public class EmployeePage extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession(true);
        
        String error = null;
        String username = request.getParameter("email");
        String password = request.getParameter("password");        
        String referrer = (String) session.getAttribute("refpage");
        String context = request.getContextPath();
        String redirect = "";
        String gRecaptchaResponse = request.getParameter("g-recaptcha-response");
        
        try{
        	Employee user = employeeInfo(username, password);
	        if(user != null && VerifyRecaptcha.verify(gRecaptchaResponse)){
	        	out.print("SUCCESS");
	        	session.setAttribute("user", user);
	        	session.removeAttribute("Referer");
	        	session.removeAttribute("refpage");
	        	redirect = "/WEB-INF/EmployeeDashboard.jsp";
	        } else {
	        	session.setAttribute("error", "Invalid Credentials");
	        	redirect = "/employeePage.jsp";
	        }
        } catch (Exception e){
        	session.setAttribute("error", "SQL Server Down");
        	redirect = "/employeePage.jsp";
        }
        Service.forward(request, response, redirect);
	}

	protected Employee employeeInfo(String username, String password) throws Exception{
		Employee e = null;
		if(username != null && password != null)
			e = EmployeeService.verifyCredentials(username, password);
		return e;
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);		
	}

}

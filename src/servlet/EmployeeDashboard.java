package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import objects.Star;
import services.Metadata;
import services.Service;

/**
 * Servlet implementation class EmployeeDashboard
 */
@WebServlet("/EmployeeDashboard")
public class EmployeeDashboard extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		HttpSession session = request.getSession(true);
		session.removeAttribute("action");
		String redirect = "/WEB-INF/EmployeeDashboard.jsp";
		
		Star star = new Star(request.getParameter("firstName"),
				request.getParameter("lastName"),
				request.getParameter("dob"),
				request.getParameter("photo"));
		try {
			boolean ans = enterStarData(request, response, star);	
			if (ans) {
				request.setAttribute("isAdded", "y");
				request.setAttribute("star", star);
			} else {
				request.setAttribute("isAdded", "n");
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		Service.forward(request, response, redirect);
	}
	
	private boolean enterStarData(HttpServletRequest request, HttpServletResponse response, Star star) throws ServletException, IOException  {
		response.setContentType("text/html");

        String first = request.getParameter("firstName");
        String last = request.getParameter("lastName");
        String dob = request.getParameter("dob");
        String photo = request.getParameter("photo");
        try {
        	Metadata m = new Metadata(response.getWriter());
        	return m.addStar(star.getFirst(), star.getLast(), star.getDob(), star.getPhoto());
        } catch (Exception e) {
        	e.printStackTrace();
        }
        return false;
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

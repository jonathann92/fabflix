package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import services.Service;

/**
 * Servlet implementation class XMLParser
 */
@WebServlet("/XMLParser")
public class XMLParser extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String title = request.getParameter("title");
		String director = request.getParameter("director");
		String year = request.getParameter("year");
		String genre = request.getParameter("genre");
		String starFirst = request.getParameter("starFirst");
		String starLast = request.getParameter("starLast");
		String url = "/WEB-INF/EmployeeDashboard.jsp";
		
		
		
    	Service.doXMLStuff(title, director, year, starFirst, starLast, genre);
    	Service.forward(request, response, url);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

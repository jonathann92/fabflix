package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cache.Cache;
import objects.Star;
import site.Site;

/**
 * Servlet implementation class StarPage
 */
@WebServlet("/StarPage")
public class StarPage extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        
        /* TEST */
        // Example id = 631010
		int id = 634005;
		Star s = Cache.stars_id.get(id);
		/* TEST */
		
//		String queryID = request.getParameter("id");
//		int id = 0;
//		Star s = null;
//		if(queryID != null){
//			id = Integer.parseInt(queryID);
//			s = Cache.stars_id.get(id);
//		}
		
		
		request.setAttribute("star", s);
		
		Site.forward(request, response, "/WEB-INF/SingleStar.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cache.Cache;
import objects.Star;
import site.Site;

/**
 * Servlet implementation class UserName
 */
@WebServlet("/UserName")
public class UserName extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String usernamee = "request";
		Star s = Cache.stars_id.get(911);
		request.setAttribute("username", usernamee);
		
		request.setAttribute("star", s);
		
		
		HttpSession session = request.getSession(true);
		
		session.setAttribute("username", "hi2");
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		Site.forward(request, response, "test.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

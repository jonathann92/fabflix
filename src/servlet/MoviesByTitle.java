package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import objects.Movie;
import services.MovieTitle;
import services.Service;

/**
 * Servlet implementation class MoviesByTitle
 */
@WebServlet("/MoviesByTitle")
public class MoviesByTitle extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession(true);

		String parameter = request.getParameter("letter");
		String query = "letter="+parameter;
		
		if(parameter == null || parameter.length() == 0)
			response.sendRedirect("/filmdb/");
		else {
			char letter = parameter.charAt(0);
			List<Movie> list = MovieTitle.getMovieList(letter);
			session.setAttribute("fullMovieList", list);
        	request.setAttribute("prevpage", "MoviesByTitle");
        	request.setAttribute("query", query);
        	Service.forward(request, response, "/MovieList");
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

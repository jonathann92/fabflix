package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import objects.Genre;
import objects.Movie;
import objects.Star;
import services.MovieService;
import services.Service;


/**
 * Servlet implementation class SearchPopBox
 */
@WebServlet("/SearchPopBox")
public class SearchPopBox extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		String idParam = request.getParameter("id");
		out.println("MOVIE POP BOX FOR " + idParam + " goes here");
		
		int id = -1;
		if(idParam != null && idParam.length() > 0){
			id = Integer.parseInt(idParam);
		}
		
		Movie m = MovieService.getMovieInfo(id);
		Set<Star> stars = MovieService.getStarList(id);
		Set<Genre> genres = MovieService.getGenreList(id);
		System.out.println(stars.size());
		
		request.setAttribute("movie", m);
		request.setAttribute("stars", stars);		
		request.setAttribute("genres", genres);
		
		Service.forward(request, response, "/WEB-INF/PopOutBox.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

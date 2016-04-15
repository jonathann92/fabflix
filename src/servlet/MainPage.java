package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import services.MovieListService;
import java.util.List;
import objects.*;
import services.Service;

@WebServlet("/")
public class MainPage extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		request.setAttribute("genreList", Service.getAllGenres());
		request.setAttribute("randomMovies", MovieListService.getRandomMovieList(30));
		//request.setAttribute("movieList", ml);
		MovieListService.forward(request, response, "jsp/index.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

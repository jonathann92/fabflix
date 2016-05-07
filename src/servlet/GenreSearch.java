package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import objects.Genre;
import objects.Movie;
import services.GenreService;
import services.Service;

/**
 * Servlet implementation class GenreSearch
 */
@WebServlet("/GenreSearch")
public class GenreSearch extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
        PrintWriter out = response.getWriter();     
        
        String parameter = request.getParameter("id");
        if(parameter != null && parameter.length() > 0){
        	int id = Integer.parseInt(parameter);
        	listMovies(request, response, id);
        	return;
        }
        
        parameter = request.getParameter("name");
        if(parameter != null && parameter.length() > 0){
        	listMovies(request, response, parameter);
        	return;
        }
        
        out.print("NEED TO IMPLEMENT GENRE LIST");
        listAllGenres();
	}
	
	protected void listMovies(HttpServletRequest request, HttpServletResponse response, int id) throws ServletException, IOException{
		String sql =  "select distinct movies.* from genres_in_movies, movies where "
				+ id + " = genres_in_movies.genre_id "
				+ "and genres_in_movies.movie_id = movies.id";
		
		String query = "id="+id;
    	request.setAttribute("sql", sql);
		request.setAttribute("prevpage", "GenreSearch");
    	request.setAttribute("query", query);
    	Service.forward(request, response, "MovieList");
	}
	
	protected void listMovies(HttpServletRequest request, HttpServletResponse response, String name) throws ServletException, IOException {
    	String sql = "select distinct movies.* from genres, genres_in_movies, movies where "
				+ "genres.name = '" + name + "' and genres.id = genres_in_movies.genre_id "
				+ "and genres_in_movies.movie_id = movies.id";
    	
		String query = "name=" + name;
    	request.setAttribute("sql", sql);
		request.setAttribute("prevpage", "GenreSearch");
    	request.setAttribute("query", query);
    	Service.forward(request, response, "/MovieList");
	}
	
	protected void listAllGenres(){
		List<Genre> genres = GenreService.allGenres();
		
		// TODO Make a page with all genres and redirect or something like that
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

package servlet;

import java.util.List;
import java.util.Set;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import objects.Movie;
import services.AdvancedSearchService;
import services.Service;

/**
 * Servlet implementation class AdvSearch
 */
@WebServlet("/AdvSearch")
public class AdvSearch extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession(true);
        
        String error = "No error";
        
        String title = request.getParameter("title");
        String year = request.getParameter("year");
        String director = request.getParameter("director");
        String first = request.getParameter("first");
        String last = request.getParameter("last");
        String query = "title=" + title + "&year=" + year + "&director=" + director + "&first=" + first + "&last="+last;
        
        try{
        	List<Movie> movieList;
        	movieList = AdvancedSearchService.advSearch(title, year, director, first, last);
        	session.setAttribute("fullMovieList", movieList);
        	request.setAttribute("prevpage", "AdvSearch");
        	request.setAttribute("query", query);
        	Service.forward(request, response, "/MovieList");
        	//response.sendRedirect("/filmdb/MovieList");
        } catch (Exception e){
        	error = e.getMessage();
        	request.setAttribute("error", error);
        	Service.forward(request, response, "/AdvancedSearch.jsp");
        	out.print(error);
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

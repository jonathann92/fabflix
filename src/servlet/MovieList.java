package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import objects.Movie;
import services.Service;

/**
 * Servlet implementation class MovieList
 */
@WebServlet("/MovieList")
public class MovieList extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession(true);
        
        List<Movie> movieList = (List<Movie>) session.getAttribute("fullMovieList");
        String prevPage = (String) request.getAttribute("prevpage");
        String query = (String) request.getAttribute("query");
        
        out.print(query);
        
        if(movieList == null){
        	movieList = new ArrayList<Movie>();
        }
        
        int page = requestInt("page", 1, request);
        int rows = requestInt("rows", 10, request); 
        
        String sort = (String) request.getParameter("sortby");


        movieList = sortList(sort, movieList, out);
                
        List<Movie> subList = Service.subMovieList(movieList, rows * page - rows, rows * page - 1);
        request.setAttribute("movieList", subList);
        session.setAttribute("fullMovieList", movieList);
        request.setAttribute("page", page);
        request.setAttribute("sortby", sort);
        request.setAttribute("rows", rows);
        request.setAttribute("prevpage", prevPage);
        
        Service.forward(request, response, "/WEB-INF/SearchResults.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	protected int requestInt(String attr, int init, HttpServletRequest request){
		attr = request.getParameter(attr);
		if(attr == null || attr.length() == 0)
			return init;
		
		int num = Integer.parseInt(attr);
		
		return Math.max(num, init);
	}
	
	protected List<Movie> sortList(String sort, List<Movie> list, PrintWriter out){
		if(sort == null || sort.length() == 0){
			return list;
		}
		
		if(sort.equals("yearup")){
			Collections.sort(list, Movie.MovieYearComparatorAsc);
			return list;
		}
		
		if(sort.equals("yeardown")){
			Collections.sort(list, Movie.MovieYearComparatorDesc);
			return list;
		}
		
		if(sort.equals("idup")){
			Collections.sort(list, Movie.MovieIdComparatorAsc);
			return list;
		}
		
		if(sort.equals("iddown")){
			Collections.sort(list, Movie.MovieIdComparatorDesc);
			return list;
		}
		
		if(sort.equals("titleup")){
			Collections.sort(list, Movie.MovieTitleComparatorAsc);
			return list;
		}
		
		if(sort.equals("titledown")){
			Collections.sort(list, Movie.MovieTitleComparatorDesc);
			return list;
		}
		
		
		
		
		return list;
	}

}

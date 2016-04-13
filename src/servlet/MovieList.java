package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import objects.Movie;
import site.Site;

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
        
        List<Movie> movieList = (List<Movie>) request.getAttribute("fullMovieList");
//        if(movieList == null){
//        	// TODO Handle exception
//        }
//        
        int page = (int) (request.getAttribute("page") != null ? request.getAttribute("page") : 1 );
        int rows = 5; //(int) (request.getAttribute("rows") != null ? request.getAttribute("rows") : 1 );
        
        String sort = (String) request.getAttribute("sortby");
        
        //sortList(sort, movieList);
        
        //List<Movie> subList = Site.subMovieList(movieList, 0, 10);
        List<Movie> subList = Site.subMovieList(movieList, rows * page - rows, rows * page - 1);
        request.setAttribute("movieList", subList);
        request.setAttribute("fullMovieList", movieList);
        
        //Site.forward(request, response, "/WEB-INF/SearchResults.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	protected List<Movie> sortList(String sort, List<Movie> list, PrintWriter out){
		if(sort.equals(null)){
			out.print("sort==null");
			return list;
		}
		
		if(sort.equals("yearascend")){
			
		}
		
		return list;
	}

}

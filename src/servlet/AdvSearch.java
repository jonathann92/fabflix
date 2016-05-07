package servlet;

import java.util.List;
import java.util.Set;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

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
        
        String error = null;
        
        String id = request.getParameter("id");
        String title = request.getParameter("title");
        String year = request.getParameter("year");
        String director = request.getParameter("director");
        String first = request.getParameter("first");
        String last = request.getParameter("last");
        
        if(checkParameters(request, response, id, title, year, director, first, last) && checkIntParam(request, response, id, title, year, director, first, last) )
        {
        	String query = "id=" + id + "&title=" + title + "&year=" + year + "&director=" + director + "&first=" + first + "&last="+last;
        	String sql;
        	if(first.length() > 0 || last.length() > 0){
    			sql = "select movies.* from movies, stars, stars_in_movies " 
    					+ (title.length() > 0 ? "where movies.title like '%"+ title + "%' " : "" )
    					+ (director.length() > 0 ? "and movies.director like '%" + director +"%' " : "")
    					+ (year.length() > 0 ? "and movies.year like '%" + year+ "%' " : "" )
    					+ (first.length() > 0 ? "and stars.first like '%" + first + "%' " : "" )
    					+ (last.length() > 0 ? "and stars.last like '%" + last + "%' " : "" )
    					+ "and stars.id = stars_in_movies.star_id "
    					+ "and movies.id = stars_in_movies.movie_id "
    					+ (id.length() > 0 ? "and movies.id = " + id : "") ;
    		} else {
    			sql = "select * from movies "
    					+ (title.length() > 0 ? "where title like '%"+ title + "%' " : "" )
    					+ (director.length() > 0 ? "and director like '%" + director +"%' " : "")
    					+ (year.length() > 0 ? "and year like '%" + year+ "%' " : "")
    					+ (id.length() > 0 ? "and id = " + id : "");				
    		} 
        	
	    	request.setAttribute("prevpage", "AdvSearch");
	    	request.setAttribute("query", query);
        	request.setAttribute("sql", sql);
	    	Service.forward(request, response, "/MovieList");
			
        }
        
	}
	
	private boolean checkParameters(HttpServletRequest request, HttpServletResponse response, String idParam, String title,
			String year, String director, String first, String last) {
		String error;
		if(idParam.length() == 0 && title.length() == 0 && year.length() == 0 && director.length() == 0 &&  first.length() == 0 && last.length() == 0)
        {
        	error = "No parameters given";
        	request.setAttribute("error", error);
        	Service.forward(request, response, "/AdvancedSearch.jsp");
        	return false;
        }
		return true;
	}

	private boolean checkIntParam(HttpServletRequest request, HttpServletResponse response, String idParam, String title,
			String year, String director, String first, String last) throws IOException 
	{
        try{
        	if(idParam.length() > 0)
        		Integer.parseInt(idParam);
        	if(year.length() > 0)
        		Integer.parseInt(year);
        } catch (Exception e){
        	String error = "ID or Year is not an integer";
        	setParams(request, error, idParam, title, year, director, first, last);
        	Service.forward(request, response, "/AdvancedSearch.jsp");
        	return false;
        }
        
		return true;
	}

	

	private void setParams(HttpServletRequest request, String error, String id, String title, String year, String director,
			String first, String last) {
		request.setAttribute("error", error);
		request.setAttribute("id", id);
		request.setAttribute("title", title);
		request.setAttribute("year", year);
		request.setAttribute("director", director);
		request.setAttribute("first", first);
		request.setAttribute("last", last);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

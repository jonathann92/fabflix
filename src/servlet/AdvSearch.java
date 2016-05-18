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
        
        String OS = System.getProperty("os.name");
        //System.out.println(OS);
        
        String error = null;
        
        String id = request.getParameter("id");
        String title = request.getParameter("title");
        String year = request.getParameter("year");
        String director = request.getParameter("director");
        String first = request.getParameter("first");
        String last = request.getParameter("last");
        
        if(checkParameters(request, response, id, title, year, director, first, last) && checkIntParam(request, response, id, title, year, director, first, last) )
        {
        	String query = formQuery(id, title, year, director, first, last);
        	String sql;
        	
        	
//        	if(OS.toLowerCase().contains("zzzzzz"))
//        		sql = windowsSQL(id, title, year, director, first, last); 
//        	else 
//        	{
        		if(isValid(first) || isValid(last)){
        			sql = "select movies.* from movies, stars, stars_in_movies where " 
        					+ (isValid(title) ? fuzzyTitle(title) : "" )
        					+ (isValid(director) ? fuzzyAttr("movies.director", director) : "")
        					+ (isValid(year) ? "  movies.year = " + year + " and": "" )
        					+ (isValid(first) ? fuzzyAttr("stars.first", first) : "" )
        					+ (isValid(last) ? fuzzyAttr("stars.last", last) : "" )
        					+ " stars.id = stars_in_movies.star_id and"
        					+ " movies.id = stars_in_movies.movie_id and"
        					+ (isValid(id) ? " movies.id = " + id + " and": "") 
        					+ " true";
        		} else {
        			sql = "select * from movies where"
        					+ (isValid(title) ? fuzzyTitle(title) : "" )
        					+ (isValid(director) ? fuzzyAttr("director", director) : "")
        					+ (isValid(year) ? " year = " + year +" and" : "")
        					+ (isValid(id) ? " id = " + id + " and": "")
        					+ " true";
        		}
//        	}
        	
        	System.out.println(sql);
        	out.print(sql);
        	
//	    	request.setAttribute("prevpage", "AdvSearch");
//	    	request.setAttribute("query", query);
//        	request.setAttribute("sql", sql);
//	    	Service.forward(request, response, "/MovieList");
			
        }
        
	}
	
	private String fuzzyAttr(String attr, String parameter){
		return " edrec(lower('" + parameter + "'), lower(" + attr + "), 2) and ";
	}
	
	private String fuzzyTitle(String title){
		String sql = "";
		String[] tokens = title.split("\\W+");
		
		for(String s: tokens){
			sql += fuzzyAttr("movies.title", s);
		}
		
		return sql;
	}

	private String windowsSQL(String id, String title, String year, String director, String first, String last) {
		String sql;
		if(isValid(first) || isValid(last)){
			sql = "select movies.* from movies, stars, stars_in_movies where" 
					+ (isValid(title) ? " movies.title like '%"+ title + "%' and" : "" )
					+ (isValid(director) ? " movies.director like '%" + director +"%' and" : "")
					+ (isValid(year) ? "  movies.year = " + year + " and": "" )
					+ (isValid(first) ? "  stars.first like '%" + first + "%' and" : "" )
					+ (isValid(last) ? "  stars.last like '%" + last + "%' and" : "" )
					+ " stars.id = stars_in_movies.star_id and"
					+ " movies.id = stars_in_movies.movie_id and"
					+ (isValid(id) ? " movies.id = " + id + " and": "") 
					+ " true";
		} else {
			sql = "select * from movies where"
					+ (isValid(title) ? " title like '%"+ title + "%' and" : "" )
					+ (isValid(director) ? " director like '%" + director +"%' and" : "")
					+ (isValid(year) ? " year = " + year +" and" : "")
					+ (isValid(id) ? " id = " + id + " and": "")
					+ " true";
		}
		return sql;
	}

	private String formQuery(String id, String title, String year, String director, String first, String last) {
		String query = "";
		query += id != null ? "&id=" + id : "";
		query += title != null ? "&title=" + title : "";
		query += year != null ? "&year=" + year : "";
		query += director != null ? "&director=" + director : "";
		query += first != null ? "&first=" + first : "";
		query += last != null ? "&last=" + last : "";
		return query;
	}
	
	private boolean checkParameters(HttpServletRequest request, HttpServletResponse response, String idParam, String title,
			String year, String director, String first, String last) {
		String error;

		if(isValid(idParam) || isValid(title) || isValid(year) || isValid(director) || isValid(first) || isValid(last))
        {
			return true;
        }
		error = "No parameters given";
    	request.setAttribute("error", error);
    	Service.forward(request, response, "/AdvancedSearch.jsp");
    	return false;
	}
	
	private boolean isValid(String s){
		return s != null && s.length() > 0;
	}

	private boolean checkIntParam(HttpServletRequest request, HttpServletResponse response, String idParam, String title,
			String year, String director, String first, String last) throws IOException 
	{
        try{
        	if(idParam != null && idParam.length() > 0)
        		Integer.parseInt(idParam);
        	if(year != null && year.length() > 0)
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

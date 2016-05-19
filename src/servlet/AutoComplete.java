package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.json.Json;
import javax.json.JsonObjectBuilder;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import objects.Movie;
import services.AutoCompleteService;
import services.JSONService;
import services.Service;

/**
 * Servlet implementation class AutoComplete
 */
@WebServlet("/AutoComplete")
public class AutoComplete extends HttpServlet {
	private static final long serialVersionUID = 1L;
     

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String title = request.getParameter("title");
        
        System.out.println("Calling AutoComplete servlet");
		
        if(title.length() > 0){
	        String sql = generateSQL(title);
	        System.out.println(sql);
	        
			List<Movie> movieList = AutoCompleteService.queryMovieList(sql);
			JsonObjectBuilder factory = Json.createObjectBuilder();
			factory.add("suggestions", JSONService.autocompleteMovieList_JSON(movieList));
			out.print(factory.build());
			
			
//			request.setAttribute("movieList", movieList);
//			if(movieList.size() > 0)
//				Service.forward(request, response, "/WEB-INF/autocomplete.jsp");
			
        }
	}
	
	protected String generateSQL(String title){
		String[] tokens = title.split("\\s+");
		
		
		String sql = "select id, title from movies where true ";
		for(String token: tokens){
			sql += "and title rlike '[[:<:]]" + token + ".*' ";
		}
		
		sql += "limit 10;";
		return sql;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

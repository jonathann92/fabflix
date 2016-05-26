package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.json.Json;
import javax.json.JsonObjectBuilder;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import objects.Movie;
import services.JSONService;
import services.SearchService;
import services.Service;

/**
 * Servlet implementation class MovieList
 */
@WebServlet("/MovieList")
public class MovieList extends HttpServlet {
	private static final long serialVersionUID = 1L;
	int count;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession(true);
        
        String prevPage = (String) request.getAttribute("prevpage");
        String params = (String) request.getAttribute("query");
		String jsonParam = request.getParameter("json");
		List<String> questionMarks = (List<String>) request.getAttribute("questionMarks");

        String sql = processQuery(request, questionMarks);
        System.out.println(sql);
        
        List<Movie> movieList = SearchService.movieListQuery(sql, questionMarks);
        
        if(jsonParam != null && jsonParam.equals("true")){
        	JsonObjectBuilder factory = Json.createObjectBuilder();
        	factory.add("movieList", JSONService.movieList_JSON(movieList));
        	out.print(factory.build());
        } else {
	        request.setAttribute("movieList", movieList);
	        request.setAttribute("prevpage", prevPage);
	        
	        Service.forward(request, response, "/WEB-INF/SearchResults.jsp");
        }
	}

	private String processQuery(HttpServletRequest request, List<String> questionMarks) {
        String sql = (String) request.getAttribute("sql");

        count = SearchService.querySize(sql, questionMarks);
        request.setAttribute("count", count);
        System.out.println("SIZE: " + count);
        
        sql = addParameters(request, sql, count, questionMarks);
        
		return sql;
	}

	private String addParameters(HttpServletRequest request, String sql, int size, List<String> questionMarks) {
		String sort = request.getParameter("sortby");
        if(sort != null && sort.length() > 0 && !sort.equals("null")){
        	sql += " order by " + sort;
        }
        
        int page = requestInt("page", 1, request);        		
        Integer rows = requestInt("rows", 10, request); 
        if(size < rows * (page - 1 )){
        	int temp = page;
        	page = (size / rows) + 1;
        	
        	System.out.println("Page out of index resetting page from " + temp + " to " + page);
        }
        Integer offset = rows * page - rows;
        questionMarks.add(rows.toString());
        questionMarks.add(offset.toString());
        
        sql += " limit ?";
        sql += " offset ?";
        
        request.setAttribute("page", page);
        request.setAttribute("sortby", sort);
        request.setAttribute("rows", rows);
		return sql;
	}
	
	protected int requestInt(String attr, int init, HttpServletRequest request){
		attr = request.getParameter(attr);
		if(attr == null || attr.length() == 0)
			return init;
		
		int num = Integer.parseInt(attr);
		
		return Math.max(num, init);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
}

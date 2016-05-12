package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import objects.Star;
import services.JSONService;
import services.Service;
import services.StarService;

/**
 * Servlet implementation class StarPage
 */
@WebServlet("/StarPage")
public class StarPage extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        
		int id = -1;
		String param = request.getParameter("id");
		String jsonParam = request.getParameter("json");

		if(param != null && param.length() != 0)
			id = Integer.parseInt(param);

		Star s = starInfo(id);
			
		if(jsonParam != null && jsonParam.equals("true")){
			JsonObjectBuilder starInfo = JSONService.star_JSON(s);
			JsonArrayBuilder movieList = JSONService.movieList_JSON(s.getMovies());
			JsonObjectBuilder factory = Json.createObjectBuilder();
			factory.add("starInfo", starInfo);
			factory.add("movieList", movieList);
			out.print(factory.build());
			
		} else {
		request.setAttribute("star", s);

		Service.forward(request, response, "/WEB-INF/SingleStar.jsp");
		}
	}
	
	protected Star starInfo(int id){
		Star s = StarService.getStarInfo(id);
		if(s != null)
			s.setMovies(StarService.getMovieList(id));
		
		return s;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

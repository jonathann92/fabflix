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

import services.JSONService;
import services.MovieService;

/**
 * Servlet implementation class MoviePage
 */
@WebServlet("/MoviePage")
public class MoviePage extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private MovieService ms;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = -1;
		PrintWriter out = response.getWriter();
		String param = request.getParameter("id");
		String jsonParam = request.getParameter("json");
		if(param != null && param.length() != 0)
			id = Integer.parseInt(param);
		
		ms = new MovieService(id);
		
		if(jsonParam != null && jsonParam.equals("true")){
			JsonObjectBuilder movieInfo = JSONService.movie_JSON(ms.getMovieInfo());
			JsonArrayBuilder starList = JSONService.starList_JSON(ms.getStarList());
			JsonObjectBuilder factory = Json.createObjectBuilder();
			factory.add("movieInfo", movieInfo);
			factory.add("starList", starList);
			out.print(factory.build());
		} else {
		
		request.setAttribute("movieInfo", ms.getMovieInfo());
		request.setAttribute("starList", ms.getStarList());		
		request.setAttribute("genreList", ms.getGenreList());
		
		
		
		ms.forward(request, response, "/WEB-INF/MoviePage.jsp");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

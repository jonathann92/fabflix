package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
		String param = request.getParameter("id");
		if(param != null && param.length() != 0)
			id = Integer.parseInt(param);
		
		ms = new MovieService(id);
		
		request.setAttribute("movieInfo", ms.getMovieInfo());
		request.setAttribute("starList", ms.getStarList());		
		request.setAttribute("genreList", ms.getGenreList());
		
		ms.forward(request, response, "/WEB-INF/MoviePage.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

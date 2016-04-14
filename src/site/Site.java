package site;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLDecoder;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.*;

import objects.Customer;
import objects.Movie;

public class Site {
	
	public static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	public static final String DB_URL = "jdbc:mysql:///moviedb"; 
	public static  String db = "moviedb";
	public static  String user = "root";
	public static  String pass = "futurama5";
	
	public static Customer verifyCredentials(String username, String password) throws Exception{
		Class.forName(JDBC_DRIVER);
		
		Customer cust = null;
		Connection conn = null;
		Statement select = null;
		ResultSet rs = null;
		
		String query = "select * from customers "
				+ "where email='" + username + "' and password ='" + password +"'";
		
		try{
		conn = DriverManager.getConnection("jdbc:mysql:///"+db, user, pass);
		select = conn.createStatement();
		rs = select.executeQuery(query);
		
		if(rs.next())
			cust = new Customer(rs.getInt(1),rs.getString(2), rs.getString(3), 
				rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7));

		} catch (Exception e){
			throw new Exception(e.getMessage());
		}

		return cust;
	}
	
	public static void forward(HttpServletRequest request, HttpServletResponse response, String url) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher(url);
        rd.forward(request, response);
	}
	
	private static Set<Movie> queryMovieList(String query) throws Exception{
		Set<Movie> movieList = new HashSet<Movie>();
		Connection conn = null;
		Statement select = null;
		ResultSet rs = null;
		
		Class.forName(JDBC_DRIVER);
		conn = DriverManager.getConnection("jdbc:mysql:///"+db, user, pass);
		select = conn.createStatement();
		rs = select.executeQuery(query);
		
		while(rs.next()){
			Movie m = new Movie(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getString(4), rs.getString(5),
					rs.getString(6));
			movieList.add(m);
		}
		
		
		rs.close();
		select.close();
		conn.close();
		return movieList;
	}
	
	public static List<Movie> searchMovie(String title, String year, String director, String first, String last) throws Exception{
		if(title == null)
			throw new Exception("No search arguments given");
		
		String query1 = "select distinct movies.* from movies, stars, stars_in_movies " 
		+ "where movies.title like '%"+ title + "%' "
		+ "and movies.director like '%" + director +"%' "
		+ "and movies.year like '%" + year+ "%' "
		+ "and stars.first like '%" + first + "%' "
		+ "and stars.last like '%" + last + "%' "
		+ "and stars.id = stars_in_movies.star_id "
		+ "and movies.id = stars_in_movies.movie_id"; 
		
		// Query 2 is because you get a smaller number if a movie doesn't have stars in it
		String query2 = "select distinct * from movies "
		+ "where title like '%"+ title + "%' "
		+ "and director like '%" + director +"%' "
		+ "and year like '%" + year+ "%' ";

		Set<Movie> movieList = new HashSet<Movie>();
		movieList.addAll(queryMovieList(query1));
		movieList.addAll(queryMovieList(query2));
		

		return new ArrayList<Movie>(movieList);
	}
	
	public static List<Movie> subMovieList(List<Movie> movieList, int low, int high){
		if(low > movieList.size() - 1) return movieList;
		return movieList.subList(low, high > movieList.size() ? movieList.size() : high);
	}

}

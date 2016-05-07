package services;

import java.io.IOException;
import java.sql.DriverManager;
import java.sql.SQLException;

import objects.*;
import java.util.List;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.ResultSet;
import com.mysql.jdbc.Statement;

import objects.Movie;

public class Service {
	public static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	public static final String DB_URL = "jdbc:mysql:///moviedb"; 
	public static  String db = "moviedb";
	public static  String user = "root";
	public static  String pass = "futurama5";	
	
	public static void forward(HttpServletRequest request, HttpServletResponse response, String url) {
		try {
			RequestDispatcher dispatcher = request.getRequestDispatcher(url);
			dispatcher.forward(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static List<Genre> getAllGenres() {
		List<Genre> g = new ArrayList<>();
		Connection conn = null;
		Statement select = null;
		ResultSet rs = null;
		
		try {
			Class.forName(JDBC_DRIVER);
			conn = (Connection) DriverManager.getConnection(DB_URL, user, pass);
			select = (Statement) conn.createStatement();
			String sql = "SELECT * FROM genres;";
			
			rs = (ResultSet) select.executeQuery(sql);
			while (rs.next()){
				g.add(new Genre(rs.getInt(1), rs.getString(2)));
			}
			
			rs.close();
			select.close();
			conn.close();
			
		} catch(SQLException e) {
			e.printStackTrace();
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
		return g;
	}
	
	public static List<Movie> subMovieList(List<Movie> movieList, int rows, int page){
		int high = rows * page - 1;
		int low = rows * page - rows;
		if(low > movieList.size() - 1) return movieList;
		return movieList.subList(low, high > movieList.size() ? movieList.size() : high);
	}
	
	public static void doXMLStuff(String title, String director, String year, String starFirst, String starLast, String genre)
	 {
		 	try {
	        	Class.forName("com.mysql.jdbc.Driver").newInstance();
	            Connection connection = (Connection) DriverManager.getConnection("jdbc:mysql:///"+db,user, pass);
	            Statement stmt = (Statement) connection.createStatement();
	            
	            String sql = "call add_movie('" + title + "', '" + director + "', " + year + ",'" + starFirst + "', '" + starLast + "', '" + genre + "');";
	        	
	        	stmt.execute(sql);
	            
	            connection.close();	        
			} 
           catch (Exception e) {
	            e.printStackTrace();
	        }
   }
}

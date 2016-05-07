package services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import objects.Movie;

public class SearchService extends Service {
	
	public static int querySize(String query){
		int count = 0;
		Connection conn = null;
		Statement select = null;
		ResultSet rs = null;
		
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, user, pass);
			select =  conn.createStatement();
			
			rs =  select.executeQuery(query);

            if(rs.last()){
                count = rs.getRow();
            }
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally { try { rs.close(); select.close(); conn.close(); } catch (Exception e2) {} }
		
		return count;
	}
	
	public static List<Movie> movieListQuery(String query){
		Set<Movie> movieList = new HashSet<Movie>();
		
		Connection conn = null;
		Statement select = null;
		ResultSet rs = null;
		
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, user, pass);
			select =  conn.createStatement();
			
			rs =  select.executeQuery(query);
			while(rs.next()){
				Movie m = new Movie(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getString(4), rs.getString(5), rs.getString(6));
				movieList.add(m);
			}
			
			MovieService.populateMovieStars(movieList);
			MovieService.populateMovieGenres(movieList);
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally { try { rs.close(); select.close(); conn.close(); } catch (Exception e2) {} }
		
		
		return new ArrayList<Movie>(movieList);
	}

}

package services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import objects.Movie;

public class SearchService extends Service {
	
	public static int querySize(String query, List<String> params){
		int count = 0;
		Connection conn = null;
		PreparedStatement select = null;
		ResultSet rs = null;
		
		try {
			Context initCtx = new InitialContext();
			Context envCtx = (Context) initCtx.lookup("java:comp/env");
			DataSource ds = (DataSource) envCtx.lookup("jdbc/read");
			
			//Class.forName(JDBC_DRIVER);
			//conn = DriverManager.getConnection(DB_URL, user, pass);
			conn = ds.getConnection();
			select =  conn.prepareStatement(query);
			
			
			
			
			for(int i = 0; i < params.size(); ++i){
				select.setString(i+1, params.get(i));
			}
			
			rs =  select.executeQuery();
            if(rs.last()){
                count = rs.getRow();
            }
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally { try { rs.close(); select.close(); conn.close(); } catch (Exception e2) {} }
		return count;
	}
	
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
	
	public static List<Movie> movieListQuery(String query, List<String> params){
		List<Movie> movieList = new ArrayList<Movie>();
		
		Connection conn = null;
		PreparedStatement select = null;
		ResultSet rs = null;
		
		try {
			
			Context initCtx = new InitialContext();
			Context envCtx = (Context) initCtx.lookup("java:comp/env");
			DataSource ds = (DataSource) envCtx.lookup("jdbc/read");
			
//			Class.forName(JDBC_DRIVER);
//			conn = DriverManager.getConnection(DB_URL, user, pass);
			
			conn = ds.getConnection();
			select =  conn.prepareStatement(query);
			
			for(int i = 0; i < params.size() - 2; ++i){
				select.setString(i+1, params.get(i));
			}
			
			for(int i = params.size() - 2; i < params.size(); ++i){
				
				Integer k = Integer.parseInt(params.get(i));
				select.setInt(i+1, k);
			}
			
			rs =  select.executeQuery();
					
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
	
	public static void main(String[] args){
		Set<Movie> movieList = new HashSet<Movie>();
		
		Connection conn = null;
		PreparedStatement select = null;
		ResultSet rs = null;
		
		String query = "select * from movies where edrec(lower(?), lower(title), 1) and  true order by year desc limit ? offset ?";
		
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, user, pass);
			select =  conn.prepareStatement(query);
			
			select.setString(1, "star");
			select.setInt(2, 10);
			select.setInt(3, 0);
			
			rs =  select.executeQuery();
					
			while(rs.next()){
				Movie m = new Movie(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getString(4), rs.getString(5), rs.getString(6));
				movieList.add(m);
			}
			
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally { try { rs.close(); select.close(); conn.close(); } catch (Exception e2) {} }
		
		
	}

}

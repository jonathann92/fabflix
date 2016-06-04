package services;

import java.util.HashSet;
import java.util.Set;

import java.sql.*;
import javax.sql.DataSource;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import objects.Movie;
import objects.Star;

public class StarService extends Service{

	public static Star getStarInfo(int starId){
		Star s = null;
		
		Connection conn = null;
		PreparedStatement select = null;
		ResultSet rs = null;
		
		try {
			//Class.forName(JDBC_DRIVER);
			//conn = DriverManager.getConnection(DB_URL, user, pass);
			Context initCtx = new InitialContext();
			Context envCtx = (Context) initCtx.lookup("java:comp/env");
			DataSource ds = (DataSource) envCtx.lookup("jdbc/read");
			conn = ds.getConnection();

			String sql = "SELECT * FROM stars where id = ?;";
			select = conn.prepareStatement(sql);
			select.setInt(1, starId);
			rs =  select.executeQuery();
			if(rs.next())
				s = new Star(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));

		} catch(Exception e) {
			e.printStackTrace();
		} finally { try { rs.close(); select.close(); conn.close(); } catch (Exception e2) {} }
		
		return s;
	}
	
	public static Set<Movie> getMovieList(int starId){
		Set<Movie> movies = null;
		
		Connection conn = null;
		PreparedStatement select = null;
		ResultSet rs = null;
		
		try {
			movies = new HashSet<Movie>();
			//Class.forName(JDBC_DRIVER);
			//conn = DriverManager.getConnection(DB_URL, user, pass);
			Context initCtx = new InitialContext();
			Context envCtx = (Context) initCtx.lookup("java:comp/env");
			DataSource ds = (DataSource) envCtx.lookup("jdbc/read");
			conn = ds.getConnection();

			String query = "select movies.* from stars, stars_in_movies, movies "
				     + "where stars.id = ? and stars.id=stars_in_movies.star_id "
				     + "and stars_in_movies.movie_id = movies.id";
			select = conn.prepareStatement(query);
			select.setInt(1, starId);
			rs = select.executeQuery();
			while (rs.next()){
				Movie m = new Movie(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getString(4), rs.getString(5), rs.getString(6));
				movies.add(m);
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally { try { rs.close(); select.close(); conn.close(); } catch (Exception e2) {} }
		
		return movies;
		
	}
}

package services;

import java.sql.*;
import java.util.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import objects.*;

public class MovieTitle extends Service {
	
	public static List<Movie> getMovieList(char letter){
		Set<Movie> movieList = new HashSet<Movie>();
		String query = "select * from movies where title like '" + letter + "%'";
		
		Connection conn = null;
		Statement select = null;
		ResultSet rs = null;
		
		try {
			//Class.forName(JDBC_DRIVER);
			//conn = DriverManager.getConnection(DB_URL, user, pass);
			Context initCtx = new InitialContext();
			Context envCtx = (Context) initCtx.lookup("java:comp/env");
			DataSource ds = (DataSource) envCtx.lookup("jdbc/read");
			conn = ds.getConnection();
			select =  conn.createStatement();
			//movieList = new HashSet<Movie>();
			
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

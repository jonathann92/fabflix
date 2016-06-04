package services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import objects.Movie;

public class AutoCompleteService extends Service {
	public static List<Movie> queryMovieList(String sql){
		//This only gets ID and title
		List<Movie> movieList = new ArrayList<Movie>();
		
		Connection conn = null;
		Statement select = null;
		ResultSet rs = null;
		
		try {
			Context initCtx = new InitialContext();
			Context envCtx = (Context) initCtx.lookup("java:comp/env");
			DataSource ds = (DataSource) envCtx.lookup("jdbc/read");

			//Class.forName(JDBC_DRIVER);
			//conn = DriverManager.getConnection(DB_URL, user, pass);
			conn = ds.getConnection();
			select =  conn.createStatement();
			rs = select.executeQuery(sql);
			
			while (rs.next()) {
				Movie m = new Movie(rs.getInt(1), rs.getString(2));
				movieList.add(m);
			}
			
			rs.close();
			select.close();
			conn.close();
			
		} catch(SQLException e) {
			e.printStackTrace();
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return movieList;
	}
	
	public static List<Movie> queryMovieList(String sql, List<String> params){
		//This only gets ID and title
		List<Movie> movieList = new ArrayList<Movie>();
		
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
			select =  conn.prepareStatement(sql);
			
			for(int i = 0; i < params.size(); ++i){
				select.setString(i+1, params.get(i));
			}
			
			rs = select.executeQuery();
			
			while (rs.next()) {
				Movie m = new Movie(rs.getInt(1), rs.getString(2));
				movieList.add(m);
			}
			
			rs.close();
			select.close();
			conn.close();
			
		} catch(SQLException e) {
			e.printStackTrace();
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return movieList;
	}
}

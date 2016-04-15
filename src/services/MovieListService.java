package services;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.ResultSet;
import com.mysql.jdbc.Statement;
import java.util.List;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

import objects.Star;
import objects.Movie;
import objects.Genre;

public class MovieListService extends Service {
	private int listSize;
	
	public MovieListService(int size) {
		this.listSize = size;
	}
	
	public MovieListService() {
		this.listSize = -1;
	}
	
	public List<Movie> getMovieList() {
		List<Movie> m = new ArrayList<>();
		Connection conn = null;
		Statement select = null;
		ResultSet rs = null;
		
		try {
			Class.forName(JDBC_DRIVER);
			conn = (Connection) DriverManager.getConnection(DB_URL, user, pass);
			select = (Statement) conn.createStatement();
			String sql = "SELECT * FROM movies limit " + listSize + ";";
			
			rs = (ResultSet) select.executeQuery(sql);
			int i = 0;
			while (rs.next() && i < listSize){
				m.add(new Movie(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getString(4), rs.getString(5), rs.getString(6)));
				i++;
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
		
		
		return m;
	}
	
	public List<Movie> getRandomMovieList(int listSize) {
		List<Movie> m = new ArrayList<>();
		Connection conn = null;
		Statement select = null;
		ResultSet rs = null;
		
		try {
			Class.forName(JDBC_DRIVER);
			conn = (Connection) DriverManager.getConnection(DB_URL, user, pass);
			select =  (Statement) conn.createStatement();
			String sql = "select * from movies order by RAND() limit " + listSize + ";";
			rs = (ResultSet) select.executeQuery(sql);
			
			while (rs.next()) {
				m.add(new Movie(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getString(4), rs.getString(5), rs.getString(6)));
			}
			
			rs.close();
			select.close();
			conn.close();
			
		} catch(SQLException e) {
			e.printStackTrace();
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return m;
	}
	
}

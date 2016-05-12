package services;

import java.sql.*;

import java.util.List;
import java.util.Set;
import java.util.ArrayList;
import java.util.HashSet;

import objects.Star;
import objects.Movie;
import objects.Genre;

public class MovieService extends Service {
	private int movieId;
	
	public MovieService(int i) {
		this.movieId = i;
	}
	
	public Movie getMovieInfo() {
		Movie m = null;
		Connection conn = null;
		Statement select = null;
		ResultSet rs = null;
		
		try {
			Class.forName(JDBC_DRIVER);
			conn = (Connection) DriverManager.getConnection(DB_URL, user, pass);
			select = (Statement) conn.createStatement();
			String sql = "SELECT * FROM movies where movies.id = " + movieId + ";";
			
			rs = (ResultSet) select.executeQuery(sql);
			while (rs.next()){
				m = new Movie(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getString(4), rs.getString(5), rs.getString(6));
				
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
	
	public Set<Star> getStarList() {
		Set<Star> s = null;
		Connection conn = null;
		Statement select = null;
		ResultSet rs = null;
		
		try {
			Class.forName(JDBC_DRIVER);
			conn = (Connection) DriverManager.getConnection(DB_URL, user, pass);
			select = (Statement) conn.createStatement();
			String sql = "SELECT s.* FROM stars as s, movies as m, stars_in_movies as sm WHERE m.id = " + movieId + " AND sm.movie_id = m.id AND sm.star_id = s.id;";
			rs = (ResultSet) select.executeQuery(sql);
			s = new HashSet<>();
			
			while (rs.next()) {
				s.add(new Star(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5)));
			}
			
			rs.close();
			select.close();
			conn.close();
			
		} catch(SQLException e) {
			e.printStackTrace();
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return s;
	}
	
	public List<Genre> getGenreList() {
		List<Genre> g = null;
		Connection conn = null;
		Statement select = null;
		ResultSet rs = null;
		
		try {
			Class.forName(JDBC_DRIVER);
			conn = (Connection) DriverManager.getConnection(DB_URL, user, pass);
			select = (Statement) conn.createStatement();
			String sql = "SELECT g.* FROM genres as g, movies as m, genres_in_movies as gm WHERE m.id = " + movieId + " and gm.genre_id=g.id and gm.movie_id=m.id;";
			rs = (ResultSet) select.executeQuery(sql);
			g = new ArrayList<>();
			
			while (rs.next()) {
				g.add(new Genre(rs.getInt(1), rs.getString(2)));
			}
			
			rs.close();
			select.close();
			conn.close();
			
		} catch(SQLException e) {
			e.printStackTrace();
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return g;
	}
	
	public static Movie getMovieInfo(int id) {
		Movie m = null;
		Connection conn = null;
		Statement select = null;
		ResultSet rs = null;
		
		try {
			Class.forName(JDBC_DRIVER);
			conn = (Connection) DriverManager.getConnection(DB_URL, user, pass);
			select = (Statement) conn.createStatement();
			String sql = "SELECT * FROM movies where movies.id = " + id + ";";
			
			rs = (ResultSet) select.executeQuery(sql);
			while (rs.next()){
				m = new Movie(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getString(4), rs.getString(5), rs.getString(6));
				
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
	
	public static Set<Star> getStarList(int id) {
		// This only makes a Set of Stars with id, first, and last name
		Set<Star> s = null;
		Connection conn = null;
		Statement select = null;
		ResultSet rs = null;
		
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, user, pass);
			select =  conn.createStatement();
			String sql = "SELECT s.* FROM stars as s, movies as m, stars_in_movies as sm WHERE m.id = " + id + " AND sm.movie_id = m.id AND sm.star_id = s.id;";
			rs = select.executeQuery(sql);
			s = new HashSet<Star>();
			
			while (rs.next()) {
				s.add(new Star(rs.getInt(1), rs.getString(2), rs.getString(3)));
			}
			
			rs.close();
			select.close();
			conn.close();
			
		} catch(SQLException e) {
			e.printStackTrace();
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return s;
	}
	
	public static Set<Genre> getGenreList(int id) {
		Set<Genre> g = null;
		Connection conn = null;
		Statement select = null;
		ResultSet rs = null;
		
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, user, pass);
			select =  conn.createStatement();
			String sql = "SELECT g.* FROM genres as g, movies as m, genres_in_movies as gm WHERE m.id = " + id + " and gm.genre_id=g.id and gm.movie_id=m.id;";
			rs = select.executeQuery(sql);
			g = new HashSet<Genre>();
			
			while (rs.next()) {
				g.add(new Genre(rs.getInt(1), rs.getString(2)));
			}
			
			rs.close();
			select.close();
			conn.close();
			
		} catch(SQLException e) {
			e.printStackTrace();
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return g;
	}
	
	public static Set<Movie> populateMovieStars(Set<Movie> movies){
		for(Movie m : movies){
			int id = m.getId();
			Set<Star> stars = getStarList(id);
			m.setStars(stars);
		}
		
		return movies;
	}
	
	public static Set<Movie> populateMovieGenres(Set<Movie> movies){
		for(Movie m : movies){
			int id = m.getId();
			Set<Genre> genres = getGenreList(id);
			m.setGenres(genres);
		}
		
		return movies;
	}
	
	public static List<Movie> populateMovieStars(List<Movie> movies){
		for(Movie m : movies){
			int id = m.getId();
			Set<Star> stars = getStarList(id);
			m.setStars(stars);
		}
		
		return movies;
	}
	
	public static List<Movie> populateMovieGenres(List<Movie> movies){
		for(Movie m : movies){
			int id = m.getId();
			Set<Genre> genres = getGenreList(id);
			m.setGenres(genres);
		}
		
		return movies;
	}
}

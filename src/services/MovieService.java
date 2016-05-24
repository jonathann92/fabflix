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
		PreparedStatement select = null;
		ResultSet rs = null;
		
		try {
			Class.forName(JDBC_DRIVER);
			conn = (Connection) DriverManager.getConnection(DB_URL, user, pass);
			
			String sql = "SELECT * FROM movies where movies.id = ?;";
			select = conn.prepareStatement(sql);
			select.setInt(1, movieId);
			rs = (ResultSet) select.executeQuery();
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
		PreparedStatement select = null;
		ResultSet rs = null;
		
		try {
			Class.forName(JDBC_DRIVER);
			conn = (Connection) DriverManager.getConnection(DB_URL, user, pass);
			
			String sql = "SELECT s.* FROM stars as s, movies as m, stars_in_movies as sm WHERE m.id = ? AND sm.movie_id = m.id AND sm.star_id = s.id;";
			select = conn.prepareStatement(sql);
			select.setInt(1, movieId);
			rs = (ResultSet) select.executeQuery();
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
		PreparedStatement select = null;
		ResultSet rs = null;
		
		try {
			Class.forName(JDBC_DRIVER);
			conn = (Connection) DriverManager.getConnection(DB_URL, user, pass);
			
			String sql = "SELECT g.* FROM genres as g, movies as m, genres_in_movies as gm WHERE m.id = ? and gm.genre_id=g.id and gm.movie_id=m.id;";
			select = conn.prepareStatement(sql);
			select.setInt(1, movieId);
			rs = (ResultSet) select.executeQuery();
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
		PreparedStatement select = null;
		ResultSet rs = null;
		
		try {
			Class.forName(JDBC_DRIVER);
			conn = (Connection) DriverManager.getConnection(DB_URL, user, pass);
			
			String sql = "SELECT * FROM movies where movies.id = ?;";
			select = conn.prepareStatement(sql);
			select.setInt(1, id);
			rs = (ResultSet) select.executeQuery();
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
		PreparedStatement select = null;
		ResultSet rs = null;
		
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, user, pass);
			
			String sql = "SELECT s.* FROM stars as s, movies as m, stars_in_movies as sm WHERE m.id = ? AND sm.movie_id = m.id AND sm.star_id = s.id;";
			select =  conn.prepareStatement(sql);
			select.setInt(1, id);
			rs = select.executeQuery();
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
		PreparedStatement select = null;
		ResultSet rs = null;
		
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, user, pass);
			
			String sql = "SELECT g.* FROM genres as g, movies as m, genres_in_movies as gm WHERE m.id = ? and gm.genre_id=g.id and gm.movie_id=m.id;";
			select =  conn.prepareStatement(sql);
			select.setInt(1, id);
			rs = select.executeQuery();
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

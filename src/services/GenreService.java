package services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import objects.Genre;
import objects.Movie;

public class GenreService extends Service {

	public static List<Genre> allGenres(){
		List<Genre> genres = null;
		Connection conn = null;
		Statement select = null;
		ResultSet rs = null;
		
		try {
			Class.forName(JDBC_DRIVER);
			conn =  DriverManager.getConnection(DB_URL, user, pass);
			select =  conn.createStatement();
			genres = new ArrayList<Genre>();
			String query = "select * from genres";
			rs = select.executeQuery(query);
			
			while(rs.next()){
				Genre g = new Genre(rs.getInt(1), rs.getString(2));
				genres.add(g);
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally { try { rs.close(); select.close(); conn.close(); } catch (Exception e2) {} }
		
		return genres;
	}
	
	public static List<Movie> moviesInGenre(int id){
		List<Movie> movies = null;
		Connection conn = null;
		Statement select = null;
		ResultSet rs = null;
		
		try {
			Class.forName(JDBC_DRIVER);
			conn =  DriverManager.getConnection(DB_URL, user, pass);
			select =  conn.createStatement();
			movies = new ArrayList<Movie>();
			String query = "select distinct movies.* from genres_in_movies, movies where "
					+ id + " = genres_in_movies.genre_id "
					+ "and genres_in_movies.movie_id = movies.id";
			rs = select.executeQuery(query);
			
			while(rs.next()){
				Movie m = new Movie(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getString(4), rs.getString(5), rs.getString(6));
				movies.add(m);
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally { try { rs.close(); select.close(); conn.close(); } catch (Exception e2) {} }
		
		MovieService.populateMovieGenres(movies);
		MovieService.populateMovieStars(movies);
		
		return movies;
	}
	
	public static List<Movie> moviesInGenre(String name){
		List<Movie> movies = null;
		Connection conn = null;
		Statement select = null;
		ResultSet rs = null;
		
		try {
			Class.forName(JDBC_DRIVER);
			conn =  DriverManager.getConnection(DB_URL, user, pass);
			select =  conn.createStatement();
			movies = new ArrayList<Movie>();
			String query = "select distinct movies.* from genres, genres_in_movies, movies where "
							+ "genres.name = '" + name + "' and genres.id = genres_in_movies.genre_id "
							+ "and genres_in_movies.movie_id = movies.id";
			rs = select.executeQuery(query);
			
			while(rs.next()){
				Movie m = new Movie(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getString(4), rs.getString(5), rs.getString(6));
				movies.add(m);
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally { try { rs.close(); select.close(); conn.close(); } catch (Exception e2) {} }
		
		MovieService.populateMovieGenres(movies);
		MovieService.populateMovieStars(movies);
		
		return movies;
	}
}

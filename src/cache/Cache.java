package cache;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

import objects.Genre;
import objects.Movie;
import objects.Star;

public class Cache {

	public static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	public static final String DB_URL = "jdbc:mysql:///moviedb"; 
	public static  String db = "moviedb";
	public static  String user = "root";
	public static  String pass = "pass";
    
	public static  Map<Integer, Movie> movies_id;
    public static  Map<Integer, Star> stars_id;
    public static  Map<String, Star> stars_name;
    public static  Map<Integer, Genre> genres_id;
    
    public static Map<String, ResultSet> queries;
    
    public static Connection conn = null;
    public static Statement stmt = null;
    
    public static int count = 0;
    
    
    static{
    	try {
    		++count;
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection("jdbc:mysql:///"+db, user, pass);
			stmt = conn.createStatement();
			
			/*
			movies_id = moviesFromDatabase(stmt);
			stars_id = starsFromDatabase(stmt);
			genres_id = genresFromDatabase(stmt);
			movie_star(movies_id, stars_id, stmt);
			movie_genre(movies_id, genres_id, stmt);
			Cache.stars_name = starsByName(stars_id);
			*/
		} catch (Exception e) {
		} //finally { try { conn.close(); stmt.close(); } catch (Exception e2) {} }
    }
    
    public static ResultSet query(String query) throws Exception{
    	
    	if(queries.containsKey(query))
    		return queries.get(query);

    	ResultSet rs = stmt.executeQuery(query);
    	
    	queries.put(query, rs);
    	
    	return rs;
    }
    
    public static void updateCache(){
    	boolean movies = false, stars = false, genres = false;
    	try
    	{
	    	if(rowCount("movies") != movies_id.size()){
	    		movies_id = moviesFromDatabase(stmt);
	    		movies = true;
	    	}
	    	
	    	if(rowCount("stars") != stars_id.size()){
	    		stars_id = starsFromDatabase(stmt);
	    		stars = true;
	    	}
	    	
	    	if(rowCount("genres") != genres_id.size()){
	    		genres_id = genresFromDatabase(stmt);
	    		genres = true;
	    	}
	    	if(movies){
	    		movie_star(movies_id, stars_id, stmt);
				movie_genre(movies_id, genres_id, stmt);
				return;
	    	}
	    	
	    	if(stars)
	    		movie_star(movies_id, stars_id, stmt);
	    	
	    	if(genres)
	    		movie_genre(movies_id, genres_id, stmt);
    	
    	} catch (Exception e) 
    	{ 
    		e.printStackTrace(); 
    	}
    }
    

    
    
    
    public static int rowCount(String tablename) throws Exception{
//    	String query = "select * from " + tablename;
//    	ResultSet rs = stmt.executeQuery(query);
//    	return rowCount(rs);
    	return rowCount(stmt.executeQuery("select * from " + tablename));
    }
	
	public static int rowCount(ResultSet rs) throws Exception{
		int currentRow = rs.getRow();
		int count = rs.last() ? rs.getRow() : 0;
		
		if(currentRow == 0) 
			rs.beforeFirst();
		else 
			rs.absolute(currentRow);
		
		return count;
	}
	
	
	public static Map<String, Star> starsByName(Map<Integer, Star> stars){
		Map<String, Star> toReturn = new HashMap<String, Star>();
		
		for(Star s : stars.values()){
			String key = s.getFirst() + " " + s.getLast();
			toReturn.put(s.getFirst() + " " + s.getLast(), s);
		}
		
		return toReturn;
	}
	
	public static Map<String, Genre> genresByName(Map<Integer, Genre> genres){
		Map<String, Genre> toReturn = new HashMap<String, Genre>();
		
		for(Genre g : genres.values()){
			toReturn.put(g.getGenre(), g);
		}
		
		return toReturn;
	}
	
	public static Map<String, Movie> moviesByTitle(Map<Integer, Movie> movies){
		Map<String, Movie> toReturn = new HashMap<String, Movie>();
		
		for(Movie m : movies.values()){
			toReturn.put(m.getTitle(), m);
		}
		
		return toReturn;
	}
	
	public static void movie_star(Map<Integer, Movie> movies, Map<Integer, Star> stars, Statement select ) throws Exception{
		String query = "select * from stars_in_movies";
		ResultSet rs = select.executeQuery(query);
		
		while(rs.next()){
			int star_id = rs.getInt(1);
			int movie_id = rs.getInt(2);
			
			Star s = stars.get(star_id);
			Movie m = movies.get(movie_id);
			
			s.addMovie(m);
			m.addStar(s);
		}
		
		rs.close();
	}
	
	public static void movie_genre(Map<Integer, Movie> movies, Map<Integer, Genre> genres, Statement select) throws Exception{
		String query = "select * from genres_in_movies";
		ResultSet rs = select.executeQuery(query);
		
		while(rs.next()){
			int genre_id = rs.getInt(1);
			int movie_id = rs.getInt(2);
			
			Genre g = genres.get(genre_id);
			Movie m = movies.get(movie_id);
			
			g.addMovie(m);
			m.addGenre(g);
		}
		
		rs.close();
	}
	
	public static Map<Integer, Movie> moviesFromDatabase(Statement select) throws Exception{
		Map<Integer, Movie> movies = new HashMap<Integer, Movie>();
		String query = "select * from movies";
		
		ResultSet rs = select.executeQuery(query);
		
		while(rs.next()){
			Movie m = new Movie(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getString(4), rs.getString(5),
					rs.getString(6));
			movies.put(m.getId(), m);
		}
		
		rs.close();
		return movies;
	}
	
	public static Map<Integer, Star> starsFromDatabase(Statement select) throws Exception{
		Map<Integer, Star> stars = new HashMap<Integer, Star>();
		
		String query = "select * from stars";
		
		ResultSet rs = select.executeQuery(query);
		
		while(rs.next()){
			Star s = new Star(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));
			stars.put(s.getId(), s);
		}
		
		rs.close();
		return stars;
	}
	
	public static Map<Integer, Genre> genresFromDatabase(Statement select) throws Exception{
		Map<Integer, Genre> genres = new HashMap<Integer, Genre>();
		
		String query = "select * from genres";
		
		ResultSet rs = select.executeQuery(query);
		
		while(rs.next()){
			Genre g = new Genre(rs.getInt(1), rs.getString(2));
			genres.put(g.getId(), g);
		}
		
		rs.close();
		return genres;
	}
}

package services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import objects.Genre;
import objects.Movie;
import objects.Star;

public class AdvancedSearchService extends Service{
	
	public static List<Movie> advSearch(String title, String year, String director, String first, String last) throws Exception{
		if(title == null)
			throw new Exception("No search arguments given");
		
		String query1 = "select distinct movies.* from movies, stars, stars_in_movies " 
		+ "where movies.title like '%"+ title + "%' "
		+ "and movies.director like '%" + director +"%' "
		+ "and movies.year like '%" + year+ "%' "
		+ "and stars.first like '%" + first + "%' "
		+ "and stars.last like '%" + last + "%' "
		+ "and stars.id = stars_in_movies.star_id "
		+ "and movies.id = stars_in_movies.movie_id"; 
		
		// Query 2 is because you get a smaller number if a movie doesn't have stars in it
		String query2 = "select distinct * from movies "
		+ "where title like '%"+ title + "%' "
		+ "and director like '%" + director +"%' "
		+ "and year like '%" + year+ "%' ";

		Set<Movie> movieList = new HashSet<Movie>();
		movieList.addAll(queryMovieList(query1));
		movieList.addAll(queryMovieList(query2));
		
		MovieService.populateMovieStars(movieList);
		MovieService.populateMovieGenres(movieList);

		return new ArrayList<Movie>(movieList);
	}
	
	private static Set<Movie> queryMovieList(String query) throws Exception{
		Set<Movie> movieList = new HashSet<Movie>();
		Connection conn = null;
		Statement select = null;
		ResultSet rs = null;
		
		Class.forName(JDBC_DRIVER);
		conn = DriverManager.getConnection("jdbc:mysql:///"+db, user, pass);
		select = conn.createStatement();
		rs = select.executeQuery(query);
		
		while(rs.next()){
			Movie m = new Movie(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getString(4), rs.getString(5),
					rs.getString(6));
			movieList.add(m);
		}

		rs.close();
		select.close();
		conn.close();
		return movieList;
	}
	
}

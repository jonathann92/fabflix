package services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import objects.Genre;
import objects.Movie;
import objects.Star;

public class AdvancedSearchService extends Service{
	
	public static List<Movie> advSearch(String id, String title, String year, String director, String first, String last) throws Exception {
		Set<Movie> movieList = new HashSet<Movie>();
		String query1 = "select distinct movies.* from movies, stars, stars_in_movies " 
		+ "where movies.title like '%"+ title + "%' "
		+ "and movies.director like '%" + director +"%' "
		+ "and movies.year like '%" + year+ "%' "
		+ "and stars.first like '%" + first + "%' "
		+ "and stars.last like '%" + last + "%' "
		+ "and stars.id = stars_in_movies.star_id "
		+ "and movies.id = stars_in_movies.movie_id "
		+ (id.length() > 0 ? "and movies.id = " + id : "") ;
		
		movieList.addAll(queryMovieList(query1));
		
		if(first.length() != 0 && last.length() != 0){
			// Query 2 is because you get a smaller number if a movie doesn't have stars in it
			String query2 = "select distinct * from movies "
			+ "where title like '%"+ title + "%' "
			+ "and director like '%" + director +"%' "
			+ "and year like '%" + year+ "%' "
			+ "and id = " + id;
		
			movieList.addAll(queryMovieList(query2));
		}
		
		MovieService.populateMovieStars(movieList);
		MovieService.populateMovieGenres(movieList);

		return new ArrayList<Movie>(movieList);
	}
	
	private static Set<Movie> queryMovieList(String query) throws ClassNotFoundException, SQLException {
		Set<Movie> movieList = new HashSet<Movie>();
		Connection conn = null;
		Statement select = null;
		ResultSet rs = null;
		
		
		try {
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
		} catch (ClassNotFoundException e) {
			throw new ClassNotFoundException("Class no found " + JDBC_DRIVER);
		} catch (SQLException e) {
			throw new SQLException("No Server Connected");
		}
		
		return movieList;
	}
	
}

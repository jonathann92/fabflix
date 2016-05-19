package services;

import java.util.List;
import java.util.Set;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;

import objects.Movie;
import objects.Star;

public class JSONService {
	
	public static JsonObjectBuilder movie_JSON(Movie m){
		JsonObjectBuilder factory = Json.createObjectBuilder()
									.add("id", m.getId())
									.add("title", m.getTitle())
									.add("year", m.getYear())
									.add("director", m.getDirector())
									.add("banner", m.getBanner())
									.add("trailer", m.getTrailer());
		
		return factory;
	}
	
	public static JsonObjectBuilder star_JSON(Star s){
		JsonObjectBuilder factory = Json.createObjectBuilder()
									.add("id", s.getId())
									.add("first", s.getFirst())
									.add("last", s.getLast())
									.add("dob", s.getDob())
									.add("photo", s.getPhoto());
		
		return factory;
	}
	
	public static JsonArrayBuilder movieList_JSON(Set<Movie> movieList){
		JsonArrayBuilder arrayFactory = Json.createArrayBuilder();
		
		for(Movie m : movieList)
		{
			arrayFactory.add(Json.createObjectBuilder()
					        .add("id", m.getId())
							.add("title", m.getTitle())
							.add("year", m.getYear())
							.add("director", m.getDirector())
							.add("banner", m.getBanner())
							.add("trailer", m.getTrailer())
						);
		}
		
		return arrayFactory;
	}
	
	public static JsonArrayBuilder autocompleteMovieList_JSON(List<Movie> movieList){
		JsonArrayBuilder arrayFactory = Json.createArrayBuilder();
		
		for(Movie m :movieList){
			arrayFactory.add(Json.createObjectBuilder()
					     	 	 .add("value", m.getTitle())
					     	 	 .add("id", m.getId())
					     	 	 );
		}
		
		return arrayFactory;
	}
	
	public static JsonArrayBuilder movieList_JSON(List<Movie> movieList){
		JsonArrayBuilder arrayFactory = Json.createArrayBuilder();
		
		for(Movie m : movieList)
		{
			arrayFactory.add(Json.createObjectBuilder()
					        .add("id", m.getId())
							.add("title", m.getTitle())
							.add("year", m.getYear())
							.add("director", m.getDirector())
							.add("banner", m.getBanner())
							.add("trailer", m.getTrailer())
						);
		}
		
		return arrayFactory;
	}
	
	
	
	public static JsonArrayBuilder starList_JSON(Set<Star> starList){
		JsonArrayBuilder arrayFactory = Json.createArrayBuilder();
		
		for(Star s : starList)
		{
			arrayFactory.add(Json.createObjectBuilder()
							.add("id", s.getId())
							.add("first", s.getFirst())
							.add("last", s.getLast())
							.add("dob", s.getDob())
							.add("photo", s.getPhoto())
						);
		}
		
		return arrayFactory;
	}
	
	
}

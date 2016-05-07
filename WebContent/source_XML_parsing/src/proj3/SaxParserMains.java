package proj3;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.sql.ResultSet;

import objects.Movie;

public class SaxParserMains extends DefaultHandler{
	final String XMLfile ="mains243.xml";
	private static final Map<String, String> genreCodeNames = Collections.unmodifiableMap(
		    new HashMap<String, String>() {{
		        put("Susp", "thriller");
		        put("CnR", "cops and robbers");
		        put("Dram", "drama");
		        put("West", "western");
		        put("Myst", "mystery");
		        put("S.F.", "science fiction");
		        put("Advt", "adventure");
		        put("Horr", "horror");
		        put("Romt", "romantic");
		        put("Comd", "comedy");
		        put("Musc", "musical");
		        put("Docu", "documentary");
		        put("Porn", "pornography");
		        put("Noir", "black");
		        put("BioP", "biographical Picture");
		        put("TV", "TV show");
		        put("TVs", "TV series");
		        put("TVm", "TV miniseries");
		    }});

	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql:///moviedb_project3_grading"; 
    
    static String db = SaxParser.db;
    static String user = SaxParser.user;
    static String pass = SaxParser.pass;
    
    Connection conn;
    Statement select;
    PreparedStatement moviesTable;
    PreparedStatement genreMovieTable;
    
    Map<String, Integer> movieId;
    Map<String, Integer> genreId;
    
    private String director;
    private String tempVal;
    private Movie tempMovie;
    List<Integer> genres;
    boolean insideDirector;
    
    int count = 0;
	
    
    public SaxParserMains(){
    	movieId = new HashMap<String, Integer>();
    	genreId = new HashMap<String, Integer>();
    	try{
    		Class.forName(JDBC_DRIVER).newInstance();
    		conn = DriverManager.getConnection("jdbc:mysql:///"+db,user, pass);
    		conn.setAutoCommit(false);
    		genreMovieTable = conn.prepareStatement("insert into genres_in_movies(genre_id, movie_id) values (?, ?)");
    	} catch (Exception e) { e.printStackTrace(); }
    }
    
    public void close(){
    	try{
    		genreMovieTable.executeBatch();
    	} catch(Exception e){
    		System.err.println("ERROR IN BATCH GENRES");
  			System.err.println(e.getMessage());
    	}
    	
    	try{
    		conn.commit();
    		genreMovieTable.close();
    		conn.close();
    	} catch (Exception e){ 
    		e.printStackTrace();
    	}
    }
    
    private void parseDocument(){
    	
    	//get a factory
		SAXParserFactory spf = SAXParserFactory.newInstance();
		try {
		
			//get a new instance of parser
			SAXParser sp = spf.newSAXParser();
			
			//parse the file and also register this class for call backs
			sp.parse(XMLfile, this);
			
		}catch(SAXException se) {
			se.printStackTrace();
		}catch(ParserConfigurationException pce) {
			pce.printStackTrace();
		}catch (IOException ie) {
			ie.printStackTrace();
		}
    }
    
    
  //Event Handlers
  	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
  		//reset
  		tempVal = "";
  		if(qName.equalsIgnoreCase("dirname") && insideDirector) {
  			director = "";
  		} else if(qName.equalsIgnoreCase("film")){
  			tempMovie = new Movie();
  			tempMovie.setDirector(director);
  		} else if (qName.equalsIgnoreCase("cats")){
  			genres = new ArrayList<Integer>();
  		} else if(qName.equalsIgnoreCase("director")){
  			insideDirector = true;
  		}
  	}
    
  	public void characters(char[] ch, int start, int length) throws SAXException {
		tempVal = new String(ch,start,length);
	}
  	
  	public void endElement(String uri, String localName, String qName) throws SAXException {

  		if(qName.equalsIgnoreCase("dirname") && tempVal.length() > 0 && insideDirector){
  			director = tempVal;
  				
  		} else if(qName.equalsIgnoreCase("t")){
  			String title = tempVal.replaceAll("\\\\", "").replaceAll("'", "\\\\'");
  			tempMovie.setTitle(title);
  			
  		} else if (qName.equalsIgnoreCase("year") && tempVal.matches("[-+]?\\d*\\.?\\d+")) {
  			
  			tempMovie.setYear(Integer.parseInt(tempVal));
  			
  			
  		} else if (qName.equalsIgnoreCase("cat")){
  			processGenre();
  			
  		} else if(qName.equalsIgnoreCase("film") && notInDatabase()) {
  			addMovieToDatabase();
  			
  		} else if(qName.equalsIgnoreCase("director")){
  			insideDirector = false;
  		}
		
	}
  	
  	private boolean notInDatabase(){
  		if(!movieId.containsKey(tempMovie.getTitle().toLowerCase()))
  				return true;
  		
		String sql = "select * from movies where title = '" + tempMovie.getTitle() + "' and director = '" + tempMovie.getDirector() + "'";

  		
  		try{
  			Statement select = conn.createStatement();
  			ResultSet rs = select.executeQuery(sql);
  			
  			if(rs.next()){
  				return false;
  			}
  			
  			//System.err.println(sql);
  			
  			rs.close();
  			select.close();
  		} catch (Exception e){
  			System.out.println(e.getMessage());
  		}
  		
  		return true;
  	}
  	
  	private void processGenre(){
  		String genre = genreCodeNames.get(tempVal);
  		if(genre == null)
  			return;
  		
  		genre = genre.toLowerCase();
		Integer genreIdNum = genreId.get(genre);
		if(genreIdNum == null){
			genreIdNum = newGenre(genre);
			genreId.put(genre, genreIdNum);
		}
		genres.add(genreIdNum);
  	}
  	
  	private int newGenre(String genre){
  		int genreid = 0;
  		try{
  			Statement select = conn.createStatement();
  			String sql = "insert into genres (name) values ('" + genre + "')";
  			if(genre.equals("null")){
  				System.out.println(genre);
  			}
  			select.executeUpdate(sql);
  			//conn.commit();
  			
  			sql = "select last_insert_id()" ;
  			ResultSet rs = select.executeQuery(sql);
  			
  			if(rs.next()){
  				genreid = rs.getInt(1);
  			}
  			
  			rs.close();
  			select.close();
  		} catch (Exception e){
  			e.printStackTrace();
  		}
  		
  		return genreid;
  	}
  	
  	private void addMovieToDatabase(){
  		int id = 0;
  		try{
  			Statement select = conn.createStatement();
  			
  			String sql = "insert into movies (title, year, director) values " 
  					   + "( '" + tempMovie.getTitle() + "', " + tempMovie.getYear() + ", '" + director + "')";
  			select.executeUpdate(sql);
  			//conn.commit();
  			
  			sql = "select last_insert_id()";
  			ResultSet rs = select.executeQuery(sql);
  			
  			if(rs.next()){
  				id = rs.getInt(1);
  			}
  			
  			movieId.put(tempMovie.getTitle().toLowerCase(), id);
  			
  			batchGenres(id);
  			
  			rs.close();
  			select.close();
  		} catch (Exception e){
  			e.printStackTrace();
  		}
  	}
  	
  	private void batchGenres(int movieid){
  		final int limit = 1000;
  		try{
  			for(int genreid : genres){
  				genreMovieTable.setInt(1, genreid);
  				genreMovieTable.setInt(2, movieid);
  				genreMovieTable.addBatch();
  			}
  			
  			if(++count % 1000 == 0){
  				System.out.println("Batch");
  	  			genreMovieTable.executeBatch();
  	  			//conn.commit();
  	  		}
  		} catch (Exception e) {
  			System.err.println("ERROR IN BATCH GENRES");
  			System.err.println(e.getMessage());
  		}
  	}
    

  	public void setMovieId(Map<String, Integer> movieId){
  		this.movieId = movieId;
  	}
  	
  	public void setGenreId(Map<String, Integer> genreId){
  		this.genreId = genreId;
  	}

    public void run(){
    	parseDocument();
    	close();
    }
    
    public static void main(String[] args){   	
    	
    	Connection conn = null;
		try{
			Class.forName(JDBC_DRIVER).newInstance();
	    	conn = DriverManager.getConnection("jdbc:mysql:///"+db,user, pass);
	    	Statement select = conn.createStatement();
	    	
	    	String sql = "call add_movie('zooo', 'jonathan', 2016,'jon', 'nguyen', 'mygenre');";
	    	
	    	
	    	
	    	select.execute(sql);
	    	System.out.println(sql);
    		
    		select.close();
    		conn.close();
    	} catch (Exception e) { 
    		e.printStackTrace(); 
    	}
		
    }


}

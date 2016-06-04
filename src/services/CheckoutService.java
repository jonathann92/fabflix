package services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import objects.Customer;
import objects.Movie;
import objects.MutableInt;

public class CheckoutService extends Service{
	
	public static List<Integer> recordTransaction(Customer cust, Map<Movie, MutableInt> cart) throws Exception{
		//Class.forName(JDBC_DRIVER);
		List<Integer> transactionId = new ArrayList<Integer>();
		
		Connection conn = null;
		PreparedStatement select = null;
		ResultSet rs = null;
		
		int customerId = cust.getId();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
 	   	Date date = new Date();
	  	String transactionDate = dateFormat.format(date);

		Context initCtx = new InitialContext();
		Context envCtx = (Context) initCtx.lookup("java:comp/env");
		DataSource ds = (DataSource) envCtx.lookup("jdbc/write");
	  	//conn = DriverManager.getConnection("jdbc:mysql:///"+db, user, pass);
		conn = ds.getConnection();
		
	  	
		for (Movie m : cart.keySet()) {
		    int movieId = m.getId();
		    String query = "INSERT INTO sales (`customers`, `movie`, `sale`) "
		    		     + "VALUES (?, ?, ?);";
		    System.out.println(query);
		    select = conn.prepareStatement(query);
		    select.setInt(1, customerId);
		    select.setInt(2, movieId);
		    select.setString(3, transactionDate);
		    int rows = select.executeUpdate();
		    query = "select last_insert_id();";
		    select = conn.prepareStatement(query);
		    rs = select.executeQuery();
		    while(rs.next()){
		    	System.out.print("LAST ID: " + rs.getInt(1) );
		    	transactionId.add(Integer.parseInt(rs.getString(1)));
		    }
		}
		conn.close();
		
		return transactionId;
	}
	
	public static boolean runCreditCard(String first, String last, String card, String year, String month, String day) throws Exception {
		//Class.forName(JDBC_DRIVER);
		
		System.out.println("HERE");
		Connection conn = null;
		PreparedStatement select = null;
		ResultSet rs = null;
		
		String exp = year + "-" + month + "-" + day;
		
		String query = "select * from creditcards "
				+ "where id=? and first =? "
				+ "and last =? and expiration =?";
		
		System.out.println(query);

		Context initCtx = new InitialContext();
		Context envCtx = (Context) initCtx.lookup("java:comp/env");
		DataSource ds = (DataSource) envCtx.lookup("jdbc/read");

		//conn = DriverManager.getConnection("jdbc:mysql:///"+db, user, pass);
		conn = ds.getConnection();
		select = conn.prepareStatement(query);
		select.setString(1, card);
		select.setString(2, first);
		select.setString(3, last);
		select.setString(4, exp);
		rs = select.executeQuery();
		
		if(rs.next()) {
			conn.close();
			return true;
		}
		conn.close();
		return false;
	}

}

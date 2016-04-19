package services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import objects.Customer;
import objects.Movie;
import objects.MutableInt;

public class CheckoutService extends Service{
	
	public static List<Integer> recordTransaction(Customer cust, Map<Movie, MutableInt> cart) throws Exception{
		Class.forName(JDBC_DRIVER);
		List<Integer> transactionId = new ArrayList<Integer>();
		
		Connection conn = null;
		Statement select = null;
		ResultSet rs = null;
		
		int customerId = cust.getId();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
 	   	Date date = new Date();
	  	String transactionDate = dateFormat.format(date);
	  	
	  	conn = DriverManager.getConnection("jdbc:mysql:///"+db, user, pass);
		select = conn.createStatement();
	  	
		for (Movie m : cart.keySet()) {
		    int movieId = m.getId();
		    String query = "INSERT INTO sales (`customers`, `movie`, `sale`) "
		    		     + "VALUES (" + customerId + ", " + movieId + ", '" + transactionDate + "' );";
		    System.out.println(query);
		    int rows = select.executeUpdate(query);
		    query = "select last_insert_id();";
		    rs = select.executeQuery(query);
		    while(rs.next()){
		    	System.out.print("LAST ID: " + rs.getInt(1) );
		    	transactionId.add(Integer.parseInt(rs.getString(1)));
		    }
		}
		
		return transactionId;
	}
	
	public static boolean runCreditCard(String first, String last, String card, String year, String month, String day) throws Exception {
		Class.forName(JDBC_DRIVER);
		
		System.out.println("HERE");
		Connection conn = null;
		Statement select = null;
		ResultSet rs = null;
		
		String exp = year + "-" + month + "-" + day;
		
		String query = "select * from creditcards "
				+ "where id='" + card + "' and first ='" + first +"' "
				+ "and last = '" + last + "' and expiration = '" + exp +"'";
		
		System.out.println(query);
		
		conn = DriverManager.getConnection("jdbc:mysql:///"+db, user, pass);
		select = conn.createStatement();
		rs = select.executeQuery(query);
		
		if(rs.next())
			return true;
		
		return false;
	}

}

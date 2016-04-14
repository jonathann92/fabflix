package services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import objects.Customer;

public class CustomerService extends Service {
	
	public static Customer verifyCredentials(String username, String password) throws Exception{
		Class.forName(JDBC_DRIVER);
		
		Customer cust = null;
		Connection conn = null;
		Statement select = null;
		ResultSet rs = null;
		
		String query = "select * from customers "
				+ "where email='" + username + "' and password ='" + password +"'";
		
		try{
		conn = DriverManager.getConnection("jdbc:mysql:///"+db, user, pass);
		select = conn.createStatement();
		rs = select.executeQuery(query);
		
		if(rs.next())
			cust = new Customer(rs.getInt(1),rs.getString(2), rs.getString(3), 
				rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7));

		} catch (Exception e){
			throw new Exception(e.getMessage());
		}

		return cust;
	}

}

package services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Service {
	public static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	public static final String DB_URL = "jdbc:mysql:///moviedb"; 
	public static  String db = "moviedb";
	public static  String user = "root";
	public static  String pass = "pass";	
	public static Connection conn = null;
    public static Statement stmt = null;
    
    public Service() {
    	try {
    		Class.forName(JDBC_DRIVER).newInstance();
    		conn = DriverManager.getConnection("jdbc:mysql:///"+db, user, pass);
    		stmt = conn.createStatement();	
    	} catch (SQLException e) {
    		e.printStackTrace();
    	} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
    }
}

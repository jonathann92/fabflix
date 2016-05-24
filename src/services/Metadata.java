package services;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class Metadata extends Service {
	private PrintWriter out;
	
	public Metadata(PrintWriter out) {
		this.out = out;
	}
	
	public List<String> getTableNames(Connection conn) throws Exception{
		List<String> tableNames = new ArrayList<String>();
		DatabaseMetaData dbmd = conn.getMetaData();
		
		ResultSet rs = dbmd.getTables(null, null, "%", null);
		while(rs.next())
			tableNames.add(rs.getString(3));
		
		rs.close();
		
		return tableNames;
	}
	
	public void printAttributes(String table, Connection conn, PrintWriter out) throws Exception{
		DatabaseMetaData dbmd = conn.getMetaData();
		ResultSet rs = dbmd.getColumns(null,null,table,null);
		
		while(rs.next())
			System.out.println(rs.getString(4) + " -- " + rs.getString(6) + "(" + rs.getString(7) + ")");
		
		rs.close();
	}
	
	 public void getDatabaseMetaData()
	 {
		 	try {
	        	Class.forName("com.mysql.jdbc.Driver").newInstance();
	            Connection connection = DriverManager.getConnection("jdbc:mysql:///"+db,user, pass);
	            
	            List<String> tableNames = getTableNames(connection);
	            for(String table : tableNames){
	            	System.out.println(table);
	            	printAttributes(table, connection, out);
	            	System.out.println("");
	            }	            
	            connection.close();	        
			} 
            catch (Exception e) {
	            e.printStackTrace();
	        }
    }
	 
	 public boolean addStar(String first, String last, String dob, String photo) {
		 boolean val = false;
		 try {
			 Class.forName(JDBC_DRIVER);
			 Connection conn = DriverManager.getConnection("jdbc:mysql:///"+db, user, pass);
			 
			 ResultSet rs = null;
			 String query = "select count(*) from stars where first = ? and last = ? and dob = ?;";
			 PreparedStatement stmt = conn.prepareStatement(query);
			 stmt.setString(1, first);
			 stmt.setString(2, last);
			 stmt.setString(3, dob);
			 rs = stmt.executeQuery();
			 int count = 1;
			 while (rs.next()) {
				 count = Integer.parseInt(rs.getString(1));
			 }
			 if (count == 0 ) {
				 String insert = "INSERT INTO stars VALUES (NULL, ?, ?, ?, ?)";
				 stmt = conn.prepareStatement(insert);
				 stmt.setString(1, first);
				 stmt.setString(2, last);
				 stmt.setString(3, dob);
				 stmt.setString(4, photo);
				 stmt.executeUpdate();
				 val = true;
			 }
		 } catch (Exception e) {
			 e.printStackTrace();
		 }
		 return val;
	 }
	
}

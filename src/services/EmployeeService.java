package services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import objects.Employee;

public class EmployeeService extends Service{
	public static Employee verifyCredentials(String email, String password) throws Exception {
		Class.forName(JDBC_DRIVER);
		Employee emp = null;
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		String query = "select * from employees "
				+ "where email='" + email + "' and password ='" + password +"';";
		
		conn = DriverManager.getConnection("jdbc:mysql:///"+db, user, pass);
		stmt = conn.createStatement();
		rs = stmt.executeQuery(query);
		while(rs.next()) {
			emp =  new Employee(rs.getString(1), rs.getString(2), rs.getString(3));
			if (password.equals(rs.getString(2)) && email.equals(rs.getString(1))) {
				break;
			}
		}
		return emp;
	}
}
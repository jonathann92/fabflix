package services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import com.mysql.jdbc.PreparedStatement;

import objects.Employee;

public class EmployeeService extends Service{
	public static Employee verifyCredentials(String email, String password) throws Exception {
		//Class.forName(JDBC_DRIVER);
		Employee emp = null;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		String query = "select * from employees where email=? and password=?;";
		
		Context initCtx = new InitialContext();
		Context envCtx = (Context) initCtx.lookup("java:comp/env");
		DataSource ds = (DataSource) envCtx.lookup("jdbc/read");
		//conn = DriverManager.getConnection("jdbc:mysql:///"+db, user, pass);
		conn = ds.getConnection();
		stmt = (PreparedStatement) conn.prepareStatement(query);
		stmt.setString(1, email);
		stmt.setString(2, password);
		rs = stmt.executeQuery();
		while(rs.next()) {
			emp =  new Employee(rs.getString(1), rs.getString(2), rs.getString(3));
			if (password.equals(rs.getString(2)) && email.equals(rs.getString(1))) {
				break;
			}
		}
		conn.close();
		stmt.close();

		return emp;
	}
}

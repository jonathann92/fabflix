package objects;

public class Employee {
	String email = "";
	String password="";
	String fullname = "";
	
	public Employee(String email, String password, String fullname) {
		this.email = email;
		this.password = password;
		this.fullname = fullname;
	}
	
	public String getEmail() {
		return this.email;
	}
	public String getPassword() {
		return this.password;
	}
	public String getFullname() {
		return this.fullname;
	}
}

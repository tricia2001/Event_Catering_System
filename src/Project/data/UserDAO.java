package Project.data;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Project.model.Event;
import Project.model.User;
import Project.util.SQLConnection;

public class UserDAO {

	static SQLConnection DBMgr = SQLConnection.getInstance();
	
	private static ArrayList<User> ReturnMatchingUsersList (String queryString) {
		ArrayList<User> userListInDB = new ArrayList<User>();
		
		Statement stmt = null;
		Connection conn = SQLConnection.getDBConnection();  
		try {
			stmt = conn.createStatement();
			ResultSet userList = stmt.executeQuery(queryString);
			while (userList.next()) {
				User user=new User(); 
				user.setLastname(userList.getString("last_name"));
				user.setFirstname(userList.getString("first_name"));
				user.setPhone(userList.getString("phone"));
				user.setRole(userList.getString("role"));
				user.setUsername(userList.getString("username"));
				user.setPassword(userList.getString("password"));
				user.setEmail(userList.getString("email"));
				user.setUtaId(userList.getString("UTA_ID"));
				user.setStreetName(userList.getString("street_name"));
				user.setStreetNumber(userList.getString("street_number"));
				user.setCity(userList.getString("city"));
				user.setState(userList.getString("state"));
				user.setZipcode(userList.getString("zipcode"));
				userListInDB.add(user);	
			}
		} catch (SQLException e) {
		}
		return userListInDB;
	}
	
	public static ArrayList<User>   searchUserwithLastname (String lastname)  {
		return ReturnMatchingUsersList("SELECT * from catering.system_user WHERE last_name LIKE '%"+lastname+"%' ORDER BY last_name, first_name, role");
}

	public static ArrayList<User>   allUsers ()  {
        return ReturnMatchingUsersList("SELECT * from catering.system_user  ORDER BY last_name, first_name, role");
}

//search specific user
public static ArrayList<User>   searchUser (String username)  {  
	return ReturnMatchingUsersList("SELECT * from catering.system_user WHERE username='"+username+"' ORDER BY last_name, first_name");
}
public static boolean deleteUser(String utaid) {

	Statement stmt = null;
	Connection conn = SQLConnection.getDBConnection();  
	try {
		stmt = conn.createStatement();
		stmt.executeUpdate("delete from catering.system_user where UTA_ID='"+utaid+"'");
		conn.commit();
		}
	catch (SQLException e) {
	}
	
	return true;}
//search USER
public static User searchUserwithUname(String uname) {
	User user=new User();
	Statement stmt = null;
	Connection conn = SQLConnection.getDBConnection();  
	try {
		
		stmt = conn.createStatement();
		ResultSet companyList = stmt.executeQuery("select * from catering.system_user where username='"+uname+"'");
		while (companyList.next()) {
			user.setLastname(companyList.getString("last_name"));
			user.setFirstname(companyList.getString("first_name"));
			user.setPhone(companyList.getString("phone"));
			user.setRole(companyList.getString("role"));
			user.setUsername(companyList.getString("username"));
			user.setPassword(companyList.getString("password"));
			user.setEmail(companyList.getString("email"));
			user.setUtaId(companyList.getString("UTA_ID"));
			user.setStreetName(companyList.getString("street_name"));
			user.setStreetNumber(companyList.getString("street_number"));
			user.setCity(companyList.getString("city"));
			user.setState(companyList.getString("state"));
			user.setZipcode(companyList.getString("zipcode"));	
		}
	} catch (SQLException e) {
	}

	
	return user;}


public static void modifyUser(User user) {
	Statement stmt = null;
	Connection conn = SQLConnection.getDBConnection();  
	try {
		stmt = conn.createStatement();
		stmt.executeUpdate("UPDATE catering.system_user SET first_name='"+user.getFirstname()+"',last_name='"+user.getLastname()+"',password='"+user.getPassword()+"',role='"+user.getRole()+"',UTA_ID='"+user.getUtaId()+"',phone='"+user.getPhone()+"',email='"+user.getEmail()+"',street_number='"+user.getStreetNumber()+"',street_name='"+user.getStreetName()+"',city='"+user.getCity()+"',state='"+user.getState()+"',zipcode='"+user.getZipcode()+"' WHERE username='"+user.getUsername()+"'");
		conn.commit(); 			
	}
	catch (SQLException e) {
	}
}






	
	public static void insertUser (User user) {
		Statement stmt = null;
		Connection conn = SQLConnection.getDBConnection();  
		try {
			stmt = conn.createStatement();
			String insertUser = "INSERT INTO CATERING.SYSTEM_USER VALUES ('"  
					+ user.getUsername()  + "','"
					+ user.getPassword() + "','"		
					+ user.getLastname() +"','"
					+ user.getFirstname() + "','"
					+ user.getRole()+ "','"
					+ user.getUtaId() + "','"
					+ user.getPhone() + "','"
					+ user.getEmail() + "','"
					+ user.getStreetNumber() + "','"
					+ user.getStreetName() + "','"
					+ user.getCity() + "','"
					+ user.getState() + "','"
					+ user.getZipcode() + "')";
			stmt.executeUpdate(insertUser);	
			conn.commit(); 
		} catch (SQLException e) {}
	}
	
	//unique role
	public static Boolean duplicateRole(String role) {  
		Statement stmt = null;   
		Connection conn = null;  
		conn = SQLConnection.getDBConnection(); 
		boolean answer = true;
		ArrayList<User> userListInDB = new ArrayList<User>();
		try {
			stmt = conn.createStatement();
			String searchUser = " SELECT * from CATERING.SYSTEM_USER WHERE role = '"+role+"'";
			ResultSet userList = stmt.executeQuery(searchUser);
			if (!userList.next()) {
				answer =  false;
			}
			 
		} catch (SQLException e) {}  
		return answer;
	}
	
	public static Boolean duplicateId(String id) {  
		Statement stmt = null;   
		Connection conn = null;  
		boolean answer = true;
		conn = SQLConnection.getDBConnection();  
		try {
			stmt = conn.createStatement();
			String searchUser = " SELECT * from CATERING.SYSTEM_USER WHERE UTA_ID = '"+id+"'";
			ResultSet userList = stmt.executeQuery(searchUser);
			if (!userList.next()) answer = false;
			 
		} catch (SQLException e) {}  
		return answer;
	}
	
	public static Boolean duplicateUser(String username) {  
		Statement stmt = null;   
		Connection conn = null;  
		boolean answer = true;
		conn = SQLConnection.getDBConnection();  
		try {
			stmt = conn.createStatement();
			String searchUser = " SELECT * from CATERING.SYSTEM_USER WHERE username = '"+username+"'";
			ResultSet userList = stmt.executeQuery(searchUser);
			if (!userList.next()) answer = false;
			 
		} catch (SQLException e) {}  
		return answer;
	}
	

	public static Boolean passwordMatch(String username, String password) {  
		Statement stmt = null;   
		Connection conn = null;  
		boolean answer = true;
		conn = SQLConnection.getDBConnection();  
		try {
			stmt = conn.createStatement();
			String searchUser = " SELECT * from CATERING.SYSTEM_USER WHERE username = '"+username+"' AND password = '"+password+"'";
			ResultSet userList = stmt.executeQuery(searchUser);
			if (!userList.next()) answer = false;
			 
		} catch (SQLException e) {}  
		return answer;
	}
	
	public static String getRole(String username) {
		Statement stmt = null;   
		Connection conn = null; 
		String role = "";
		conn = SQLConnection.getDBConnection();  
		try {
			stmt = conn.createStatement();
			String searchUser = " SELECT role from CATERING.SYSTEM_USER WHERE username = '"+username+"'";
			ResultSet userList = stmt.executeQuery(searchUser);
			while (userList.next()) {
				role = userList.getString("role");
			}
		} catch (SQLException e) {}  
		return role;
	}
	
	public static String getFirst(String username) {
		Statement stmt = null;   
		Connection conn = null; 
		String first = "";
		conn = SQLConnection.getDBConnection();  
		try {
			stmt = conn.createStatement();
			String searchUser = " SELECT first_name from CATERING.SYSTEM_USER WHERE username = '"+username+"'";
			ResultSet userList = stmt.executeQuery(searchUser);
			while (userList.next()) {
				first = userList.getString("first_name");
			}
		} catch (SQLException e) {}  
		return first;
	}
	
	public static String getLast(String username) {
		Statement stmt = null;   
		Connection conn = null; 
		String last = "";
		conn = SQLConnection.getDBConnection();  
		try {
			stmt = conn.createStatement();
			String searchUser = " SELECT last_name from CATERING.SYSTEM_USER WHERE username = '"+username+"'";
			ResultSet userList = stmt.executeQuery(searchUser);
			while (userList.next()) {
				last = userList.getString("last_name");
			}
		} catch (SQLException e) {}  
		return last;
	}
	
	
}

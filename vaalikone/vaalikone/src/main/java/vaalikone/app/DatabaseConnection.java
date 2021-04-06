package vaalikone.app;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class DatabaseConnection {
	static String dbURL = "jdbc:mysql://localhost:3306/vaalikone";
	static String username = "root";
	static String password = "pswd";
	
	static Connection dbConn;
	
	/**  
	* test connection to the database, keep the connection for future use if the check passes. used for all future queries
	*/
	public void TestConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			dbConn = DriverManager.getConnection(dbURL, username, password);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Connection to the database failed!");
			System.exit(0);
		}
	}
	
	/**  
	* basically executeQuery(), but with error handling so no need to do that elsewhere
	* shouldn't be done like this, but we're doing it anyway B)
	*/
	public ResultSet ExecuteSQL(String sqlStatement) {
		ResultSet result = null;
		try {
			PreparedStatement statement = dbConn.prepareStatement(sqlStatement);
			result = statement.executeQuery();
		} catch (SQLException e){
			e.printStackTrace();
			System.out.println("There was an error making the query to the database");
		}
		return result;
	}
}
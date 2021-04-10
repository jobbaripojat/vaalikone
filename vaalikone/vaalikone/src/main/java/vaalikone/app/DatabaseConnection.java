package vaalikone.app;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DatabaseConnection {
	static String dbURL = "jdbc:mysql://localhost:3306/vaalikone";
	static String username = "root";
	static String password = "pswd";

	Connection dbConn;

	/**
	 * Test connection to the database, keep the connection for future use if the
	 * check passes. Needs to be done when creating a new DatabaseConnection(), as
	 * the created connection is used for all future queries
	 */
	public void TestConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			dbConn = DriverManager.getConnection(dbURL, username, password);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Connection to the database failed!");
		}
	}

	/**
	 * Basically executeQuery(), but with error handling so no need to do that
	 * elsewhere. int type = 1 = executeQuery => outputs a resultset 2 =
	 * executeUpdate => insert, update, delete
	 */
	public ResultSet ExecuteSQL(PreparedStatement statement, int type) {
		ResultSet result = null;
		try {
			switch (type) {
			case 1:
				result = statement.executeQuery();
				break;
			case 2:
				statement.executeUpdate();
				break;
			default:
				System.out.println("1 for executeQuery(), 2 for executeUpdate()");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("There was an error making the query to the database");
		}
		return result;
	}

	/**
	 * Counts the amount of candidates in the database.
	 */
	public int CountCandidates() throws Exception {
		int count = 0;
		PreparedStatement statement = dbConn.prepareStatement("SELECT CANDIDATE_ID FROM CANDIDATES");
		ResultSet rs = ExecuteSQL(statement, 1);
		rs.beforeFirst();
		while (rs.next()) {
			count++;
		}
		return count;
	}
	
	/**
	 * Counts the amount of questions in the database.
	 */
	public int CountQuestions(){
		int count = 0;
		try {
			PreparedStatement statement = dbConn.prepareStatement("SELECT QUESTION_ID FROM QUESTIONS");
			ResultSet rs = ExecuteSQL(statement, 1);
			rs.beforeFirst();
			while (rs.next()) {
				count++;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}
	
	public ArrayList<String> GetMunicipalities() {
		ArrayList<String> municipalities = new ArrayList<String>();
		try {
			PreparedStatement statement = dbConn.prepareStatement("SELECT DISTINCT MUNICIPALITY FROM candidates");
			ResultSet rs = ExecuteSQL(statement, 2);
		} catch (SQLException e){
			e.printStackTrace();
		}
		
		
		return municipalities;
	}
}
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
	 * Counts the amount of candidates in the database.
	 */
	public int CountCandidates() throws Exception {
		int count = 0;
		PreparedStatement statement = dbConn.prepareStatement("SELECT CANDIDATE_ID FROM candidates");
		ResultSet rs = statement.executeQuery();
		rs.beforeFirst();
		while (rs.next()) {
			count++;
		}
		return count;
	}

	/**
	 * Counts the amount of questions in the database.
	 */
	public int CountQuestions() {
		int count = 0;
		try {
			PreparedStatement statement = dbConn.prepareStatement("SELECT QUESTION_ID FROM questions");
			ResultSet rs = statement.executeQuery();
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
			PreparedStatement statement = dbConn
					.prepareStatement("SELECT DISTINCT MUNICIPALITY FROM candidates ORDER BY MUNICIPALITY ASC");
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				municipalities.add(rs.getString(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return municipalities;
	}

	public boolean Validate(String name, String pass) {
		try {
			PreparedStatement statement = dbConn
					.prepareStatement("SELECT * FROM admin WHERE ADMIN_USERNAME = ? AND ADMIN_PASSWORD = ?");
			statement.setString(1, name);
			statement.setString(2, pass);
			ResultSet rs = statement.executeQuery();
			if (rs.next()) {
				return true;
			} else {
				return false;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}

	}
}
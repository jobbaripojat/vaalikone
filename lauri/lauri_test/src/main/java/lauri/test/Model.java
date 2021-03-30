package lauri.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.http.HttpServletResponse;

public class Model {
	static String dbURL = "jdbc:mysql://localhost:3306/vaalikone";
	static String username = "root";
	static String password = "pswd";
	
	static Connection dbConn;
	
	protected void TestConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			dbConn = DriverManager.getConnection(dbURL, username, password);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Connection to the database failed!");
			System.exit(0);
		}
	}
	
	protected int GetAnswersFor(int candidateNumber, int questionNumber) {
		String sql = "SELECT ANSWER FROM answers WHERE CANDIDATE_ID = ? AND QUESTION_ID = ?";

		try {
			PreparedStatement statement = dbConn.prepareStatement(sql);
			statement.setInt(1, candidateNumber);
			statement.setInt(2, questionNumber);
			
			ResultSet answer = statement.executeQuery(sql);
			return answer.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("There was an error fetching the answers. (Candidate numbers 1-19, questions 1-20");
			return -1;
		}
	}

	protected int GetAnswersFor(int candidateNumber) {
		String sql = "SELECT ANSWER FROM answers WHERE CANDIDATE_ID = ?";

		try {
			PreparedStatement statement = dbConn.prepareStatement(sql);
			statement.setInt(1, candidateNumber);
			
			ResultSet answer = statement.executeQuery(sql);
			return answer.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("There was an error fetching the answers. (Candidate numbers 1-19, questions 1-20");
			return -1;
		}
	}
}
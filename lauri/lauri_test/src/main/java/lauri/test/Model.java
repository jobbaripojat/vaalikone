package lauri.test;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

public class Model {
	static String dbURL = "jdbc:mysql://localhost:3306/vaalikone";
	static String username = "root";
	static String password = "pswd";
	
	static Connection dbConn;
	
	static ArrayList<Integer> vastaukset = new ArrayList<Integer>(Arrays.asList(1, 2, 3, 4, 4, 1, 1, 2, 3, 4, 4, 1, 1, 2, 3, 4, 4, 1, 2));
	
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
	
	protected int GetAnswersFor(int candidateID, int questionID) {
		String sql = "SELECT ANSWER FROM answers WHERE CANDIDATE_ID = ? AND QUESTION_ID = ?";
		try {
			PreparedStatement statement = dbConn.prepareStatement(sql);
			statement.setInt(1, candidateID);
			statement.setInt(2, questionID);
			
			ResultSet answer = statement.executeQuery();
			return answer.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("There was an error fetching the answers. (Candidate numbers 1-19, questions 1-20");
			return -1;
		}
	}

	protected ArrayList<Integer> GetAnswersFor(int candidateID) {
		String sql = "SELECT ANSWER FROM answers WHERE CANDIDATE_ID = ?";
		ArrayList<Integer> candidateAnswers = new ArrayList<Integer>();

		try {
			PreparedStatement statement = dbConn.prepareStatement(sql);
			statement.setInt(1, candidateID);
			
			ResultSet answer = statement.executeQuery();
			while (answer.next()) {
				candidateAnswers.add(answer.getInt(1));
            	System.out.println(answer.getInt(1));
			};
			return candidateAnswers;
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("There was an error fetching the answers. (Candidate numbers 1-19, questions 1-20");
			candidateAnswers.add(-1);
			return candidateAnswers;
		}
	}
	
	protected int CompareResults(int candidateAnswer, int userAnswer) {
		int difference = candidateAnswer - userAnswer;
		if (difference < 0) {
			difference = Math.abs(difference);
		}
		difference = 100 - difference * 25;
		return difference;
	}
}
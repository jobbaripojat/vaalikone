package lauri.test;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;

public class Model {
	static String dbURL = "jdbc:mysql://localhost:3306/vaalikone";
	static String username = "root";
	static String password = "pswd";
	
	static Connection dbConn;
	
	ArrayList<Integer> vastaukset = new ArrayList<Integer>(Arrays.asList(3,1,2,2,4,2,4,2,2,5,1,4,3,3,3,5,4,4,1));
	
	/**  
	* test connection to the database, keep the connection for future use if the check passes. used for all future queries
	*/
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
	
	/**  
	* fetch a candidate's answer to a specific question from the database
	*/
	protected int GetAnswersFor(int candidateID, int questionID) {
		String sql = "SELECT ANSWER FROM answers WHERE CANDIDATE_ID = " + candidateID + " AND QUESTION_ID = " + questionID;
		try {
			ResultSet answer = ExecuteSQL(sql);
			return answer.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		}
	}
	
	/**  
	* fetch a candidate's answer to a specific question from the database
	*/
	protected ArrayList<Integer> GetAnswersFor(int candidateID) {
		String sql = "SELECT ANSWER FROM answers WHERE CANDIDATE_ID = " + candidateID;
		ArrayList<Integer> candidateAnswers = new ArrayList<Integer>();
		
		try {
			ResultSet answer = ExecuteSQL(sql);
			while (answer.next()) {
				candidateAnswers.add(answer.getInt(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		};
		return candidateAnswers;
	}
	

	/**  
	* basically executeQuery(), but with error handling so no need to do that elsewhere
	* shouldn't be done like this, but we're doing it anyway B)
	*/
	protected ResultSet ExecuteSQL(String sqlStatement) {
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
	
	/**  
	* find out the users % match to given candidate's answers
	*/
	protected float CompareAnswers(int candidateID) {
		ArrayList<Integer> candidateAnswers = GetAnswersFor(candidateID);
		float maxDistance = candidateAnswers.size() * 4;
		float totalDistance = 0;
		for (int i = 0; i < vastaukset.size();) {
		    int distance = Math.abs(candidateAnswers.get(i) - vastaukset.get(i));
		    totalDistance += distance;
		    i++;
		}
		float percentageMatch = 100 - (totalDistance / maxDistance * 100);
		return percentageMatch;
	}
	
}
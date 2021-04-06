package vaalikone.app;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import javax.servlet.http.HttpServletResponse;

public class QuestionModel {
	DatabaseConnection db;
	HttpServletResponse response;

	int candidateCount = 20;
	
	ArrayList<Integer> USER_ANSWERS = new ArrayList<Integer>(Arrays.asList(3,1,2,2,4,2,4,2,2,5,1,4,3,3,3,5,4,4,1));


	/**
	* Print questions from the database and put them in an ArrayList.
	* This way they can be used later on in the program.
	*/
	protected ArrayList<String> GetQuestion() {
		String sql = "Select * From questions";
		ResultSet rs = db.ExecuteSQL(sql);
		ArrayList<String> QUESTION = new ArrayList<String>();

		try {
			while (rs.next()) {
				String i = rs.getString("QUESTION");
				QUESTION.add(i);
			}
			return QUESTION;
		} catch (Exception ex1) {
			System.out.println("*** Failure ***");
		}
		return QUESTION;
	}
	
	
	/**  
	* fetch a candidate's answer to a specific question from the database
	*/
	protected int GetAnswersFor(int candidateID, int questionID) {
		String sql = "SELECT ANSWER FROM answers WHERE CANDIDATE_ID = " + candidateID + " AND QUESTION_ID = " + questionID;
		try {
			ResultSet answer = db.ExecuteSQL(sql);
			answer.next();
			return answer.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		}
	}

	
	/**  
	* fetch a candidate's answer to questions from the database
	*/
	protected ArrayList<Integer> GetAnswersFor(int candidateID) {
		String sql = "SELECT ANSWER FROM answers WHERE CANDIDATE_ID = " + candidateID;
		ArrayList<Integer> CANDIDATE_ANSWERS = new ArrayList<Integer>();
		
		try {
			ResultSet answer = db.ExecuteSQL(sql);
			while (answer.next()) {
				CANDIDATE_ANSWERS.add(answer.getInt(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return CANDIDATE_ANSWERS;
	}
	

	/**  
	* find out the users % match to given candidate's answers
	*/
	protected float CompareAnswers(int candidateID) {
		ArrayList<Integer> CANDIDATE_ANSWERS = GetAnswersFor(candidateID);
		float maxDistance = CANDIDATE_ANSWERS.size() * 4;
		float totalDistance = 0;
		for (int i = 0; i < USER_ANSWERS.size(); i++) {
		    int distance = Math.abs(CANDIDATE_ANSWERS.get(i) - USER_ANSWERS.get(i));
		    totalDistance += distance;
		}
		float percentageMatch = 100 - (totalDistance / maxDistance * 100);
		return percentageMatch;
	}

	
	/**  
	* go through all the candidates in the database and their answers. 
	* we then use CompareAnswers() and find the best match to users answers
	*/
	protected int GetTopCandidate() {
		ArrayList<Float> CANDIDATE_MATCHES = new ArrayList<Float>();
		for (int i = 1; i < candidateCount; i++) {
			float match = CompareAnswers(i);
			CANDIDATE_MATCHES.add(match);
		}
		float topPercentage = Collections.max(CANDIDATE_MATCHES);
		int topMatch = CANDIDATE_MATCHES.indexOf(topPercentage);
		return topMatch + 1;
	}
}
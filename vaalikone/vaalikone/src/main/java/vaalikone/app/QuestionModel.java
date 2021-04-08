package vaalikone.app;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collections;

import javax.servlet.http.HttpServletResponse;

public class QuestionModel {

	DatabaseConnection db;
	HttpServletResponse response;

	int candidateCount = 0;

	ArrayList<Integer> USER_ANSWERS = new ArrayList<Integer>();

	/**
	 * Print questions from the database and put them in an ArrayList for further
	 * use.
	 */
	protected ArrayList<String> GetQuestions() throws Exception {
		PreparedStatement statement = db.dbConn.prepareStatement("SELECT * FROM questions");
		ResultSet rs = db.ExecuteSQL(statement, 1);
		ArrayList<String> QUESTION = new ArrayList<String>();
		rs.beforeFirst();
		while (rs.next()) {
			String i = rs.getString("QUESTION");
			QUESTION.add(i);
		}
		return QUESTION;
	}

	/**
	 * Fetch a candidate's answer to a specific question from the database.
	 */
	protected int GetAnswersFor(int candidateID, int questionID) throws Exception {
		PreparedStatement statement = db.dbConn
				.prepareStatement("SELECT ANSWER FROM answers WHERE CANDIDATE_ID = ? AND QUESTION_ID = ?");
		statement.setInt(1, candidateID);
		statement.setInt(2, questionID);
		ResultSet rs = db.ExecuteSQL(statement, 1);
		rs.beforeFirst();
		rs.next();
		return rs.getInt(1);
	}

	/**
	 * Fetch a candidate's answer to ALL questions from the database.
	 */
	protected ArrayList<Integer> GetAnswersFor(int candidateID) throws Exception {
		ArrayList<Integer> CANDIDATE_ANSWERS = new ArrayList<Integer>();

		PreparedStatement statement = db.dbConn.prepareStatement("SELECT ANSWER FROM answers WHERE CANDIDATE_ID = ?");
		statement.setInt(1, candidateID);
		ResultSet rs = db.ExecuteSQL(statement, 1);
		rs.beforeFirst();
		while (rs.next()) {
			CANDIDATE_ANSWERS.add(rs.getInt(1));
		}
		return CANDIDATE_ANSWERS;
	}

	/**
	 * Find out the users % match to given candidate's answers.
	 */
	protected float CompareAnswers(int candidateID) throws Exception {
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
	 * Go through all the candidates in the database and their answers. We then use
	 * CompareAnswers and find the best match to users answers.
	 */
	protected int GetTopCandidate() {
		ArrayList<Float> CANDIDATE_MATCHES = new ArrayList<Float>();
		try {
			candidateCount = db.CountCandidates();
			for (int i = 1; i < candidateCount; i++) {
				float match = CompareAnswers(i);
				CANDIDATE_MATCHES.add(match);
			}
			float topPercentage = Collections.max(CANDIDATE_MATCHES);
			int topMatch = CANDIDATE_MATCHES.indexOf(topPercentage);
			return topMatch + 1;
		} catch (Exception e) {
			System.out.println("There was an error finding the top candidate!");
			e.printStackTrace();
		}
		return -1;
	}
}
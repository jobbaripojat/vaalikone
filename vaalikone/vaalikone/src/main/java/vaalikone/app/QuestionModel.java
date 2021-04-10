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
	int questionCount = 0;

	ArrayList<Integer> USER_ANSWERS = new ArrayList<Integer>();

	/**
	 * Print questions from the database and put them in an ArrayList for further
	 * use.
	 */
	protected ArrayList<String> GetQuestions() throws Exception {
		PreparedStatement statement = db.dbConn.prepareStatement("SELECT * FROM questions");
		ResultSet rs = db.ExecuteSQL(statement, 1);
		ArrayList<String> QUESTION = new ArrayList<String>();
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
			PreparedStatement statement = db.dbConn.prepareStatement("SELECT CANDIDATE_ID FROM candidates");
			ResultSet candidateIDs = db.ExecuteSQL(statement, 1);
			while(candidateIDs.next()) {
				System.out.println(candidateIDs.getInt(1));
				float match = CompareAnswers(candidateIDs.getInt(1));
				CANDIDATE_MATCHES.add(match);
			}
			System.out.println(CANDIDATE_MATCHES);
			float topPercentage = Collections.max(CANDIDATE_MATCHES);
			int topMatch = CANDIDATE_MATCHES.indexOf(topPercentage);
			candidateIDs.beforeFirst();
			int i = 0;
			while(i != topMatch) {
				candidateIDs.next();
				i++;
			}
			candidateIDs.next();
			return candidateIDs.getInt(1);
		} catch (Exception e) {
			System.out.println("There was an error finding the top candidate!");
			e.printStackTrace();
		}
		return -1;
	}
	
	protected String GenerateQuestions() throws Exception {
		questionCount = db.CountQuestions();
		ArrayList<String> QUESTIONS = GetQuestions();

		String addToFile = "";
		int idx = 1;
		for (int i = 1; i <= questionCount; i++) {
			addToFile += String.valueOf(idx++) + ". ";
			addToFile += QUESTIONS.get(i - 1);
			addToFile += "<br>";
			addToFile += "<input type='radio' name='Q" + i + "' value='1'> Täysin eri mieltä ";
			addToFile += "<input type='radio' name='Q" + i + "' value='2'> Eri mieltä ";
			addToFile += "<input type='radio' name='Q" + i + "' value='3'> Neutraali ";
			addToFile += "<input type='radio' name='Q" + i + "' value='4'> Samaa mieltä ";
			addToFile += "<input type='radio' name='Q" + i + "' value='5'> Täysin samaa mieltä";
			addToFile += "<br>";
		}
		return addToFile;
	}
}
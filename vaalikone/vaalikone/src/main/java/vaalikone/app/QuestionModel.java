package vaalikone.app;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;

import javax.servlet.http.HttpServletResponse;

public class QuestionModel {

	DatabaseConnection db;
	HttpServletResponse response;

	int candidateCount = 0;
	int questionCount = 0;

	ArrayList<Integer> USER_ANSWERS = new ArrayList<Integer>();

	DecimalFormat df = new DecimalFormat("###.##");

	/**
	 * Print questions from the database and put them in an ArrayList for further
	 * use.
	 */
	protected ArrayList<String> GetQuestions() throws Exception {
		PreparedStatement statement = db.dbConn.prepareStatement("SELECT * FROM questions");
		ResultSet rs = statement.executeQuery();
		ArrayList<String> QUESTION = new ArrayList<String>();
		while (rs.next()) {
			String i = rs.getString("QUESTION");
			QUESTION.add(i);
		}
		return QUESTION;
	}

//	/**
//	 * Fetch a candidate's answer to a specific question from the database.
//	 * not currently in use
//	 */
//	protected int GetAnswersFor(int candidateID, int questionID) throws Exception {
//		PreparedStatement statement = db.dbConn
//				.prepareStatement("SELECT ANSWER FROM answers WHERE CANDIDATE_ID = ? AND QUESTION_ID = ?");
//		statement.setInt(1, candidateID);
//		statement.setInt(2, questionID);
//		ResultSet rs = statement.executeQuery();
//		rs.next();
//		return rs.getInt(1);
//	}

	/**
	 * Fetch a candidate's answer to ALL questions from the database.
	 */
	protected ArrayList<Integer> GetAnswersFor(int candidateID) throws Exception {
		ArrayList<Integer> CANDIDATE_ANSWERS = new ArrayList<Integer>();
		PreparedStatement statement = db.dbConn.prepareStatement("SELECT ANSWER FROM answers WHERE CANDIDATE_ID = ?");
		statement.setInt(1, candidateID);
		ResultSet rs = statement.executeQuery();
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
	protected String GetTopCandidate(String municipality) {
		ArrayList<Float> CANDIDATE_MATCHES = new ArrayList<Float>();
		try {
			candidateCount = db.CountCandidates();
			PreparedStatement statement = db.dbConn
					.prepareStatement("SELECT CANDIDATE_ID FROM candidates WHERE MUNICIPALITY = ?");
			statement.setString(1, municipality);
			if (municipality.endsWith("*")) {
				statement = db.dbConn.prepareStatement("SELECT CANDIDATE_ID FROM candidates");
			}
			ResultSet candidateIDs = statement.executeQuery();
			while (candidateIDs.next()) {
				System.out.println(candidateIDs.getInt(1));
				float match = CompareAnswers(candidateIDs.getInt(1));
				CANDIDATE_MATCHES.add(match);
			}
			float topPercentage = Collections.max(CANDIDATE_MATCHES);
			int topMatch = CANDIDATE_MATCHES.indexOf(topPercentage);
			candidateIDs.beforeFirst();
			int i = 0;
			while (i != topMatch) {
				candidateIDs.next();
				i++;
			}
			candidateIDs.next();
			statement = db.dbConn
					.prepareStatement("SELECT FIRST_NAME, LAST_NAME FROM candidates WHERE CANDIDATE_ID = ?");
			statement.setInt(1, candidateIDs.getInt(1));
			ResultSet candidateNames = statement.executeQuery();
			candidateNames.next();
			return candidateIDs.getInt(1) + "_" + df.format(topPercentage) + "_" + candidateNames.getString(1) + "_"
					+ candidateNames.getString(2);
		} catch (Exception e) {
			System.out.println("There was an error finding the top candidate!");
			e.printStackTrace();
		}
		return "-1_-1_x_y";
	}

	protected String GenerateQuestions() throws Exception {
		questionCount = db.CountQuestions();
		ArrayList<String> QUESTIONS = GetQuestions();

		String addToFile = "";
		for (int i = 1; i <= questionCount; i++) {
			addToFile += "<div class='row question-box pt-3 pb-4 mt-5 d-flex justify-content-center'>";
			addToFile += "<div class='col-12 row d-flex align-items-center'>";
			addToFile += "<div class='col-3'></div>";
			addToFile += "<h5 class='col-6 mt-2 mb-3 text-center'>";
			addToFile += QUESTIONS.get(i - 1);
			addToFile += "</h5>";
			addToFile += "<div class='col-3'></div>";
			addToFile += "</div>";
			addToFile += "<div class='col-1'></div>";
			addToFile += "<div class='col-10 row d-flex align-items-center mt-2 selection-bubble'>";
			addToFile += "<div class='col text-end'>Täysin eri mieltä</div>";
			addToFile += "<div class='col-6 p-4'>";
			addToFile += "<div class='d-flex justify-content-between mx-auto question-bubble-bg'>";
			addToFile += "<input class='form-check-input' type='radio' name='Q" + i + "' value='1' required>";
			addToFile += "<input class='form-check-input' type='radio' name='Q" + i + "' value='2' required>";
			addToFile += "<input class='form-check-input' type='radio' name='Q" + i + "' value='3' required>";
			addToFile += "<input class='form-check-input' type='radio' name='Q" + i + "' value='4' required>";
			addToFile += "<input class='form-check-input' type='radio' name='Q" + i + "' value='5' required>";
			addToFile += "</div>";
			addToFile += "</div>";
			addToFile += "<div class='col text-start'>Täysin samaa mieltä</div>";
			addToFile += "</div>";
			addToFile += "<div class='col-1'></div>";
			addToFile += "</div>";
		}
		return addToFile;
	}
}
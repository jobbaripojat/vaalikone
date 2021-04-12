package vaalikone.app;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

public class AdminModel {

	DatabaseConnection db;

	int candidateCount = 0;

	ArrayList<ArrayList<String>> LIST_OF_CANDIDATES = new ArrayList<ArrayList<String>>();

	/**
	 * Fetches all the candidates from the database and puts them an in arraylist of
	 * arraylists.
	 */
	protected void GetCandidates() {
		try {
			ArrayList<ArrayList<String>> LIST_OF_CANDIDATES = new ArrayList<ArrayList<String>>();
			PreparedStatement statement = db.dbConn.prepareStatement("SELECT * FROM candidates");
			ResultSet rs = statement.executeQuery();
			rs.beforeFirst();
			while (rs.next()) {
				ArrayList<String> CANDIDATE = new ArrayList<String>();
				CANDIDATE.add(Integer.toString(rs.getInt("CANDIDATE_ID")));
				CANDIDATE.add(rs.getString("FIRST_NAME"));
				CANDIDATE.add(rs.getString("LAST_NAME"));
				CANDIDATE.add(rs.getString("MUNICIPALITY"));
				CANDIDATE.add(rs.getString("PARTY"));
				CANDIDATE.add(rs.getString("AGE"));
				CANDIDATE.add(rs.getString("DESCRIPTION"));
				LIST_OF_CANDIDATES.add(CANDIDATE);
			}
			this.LIST_OF_CANDIDATES = LIST_OF_CANDIDATES;
		} catch (SQLException e) {
			System.out.println("There was an error fetching the candidates from the database");
			e.printStackTrace();
		}
	}

	/**
	 * Attempt to insert information from the form into the database. We make sure
	 * the id doesn't already exist.
	 */
	protected String InsertCandidate(HttpServletRequest request) {
		try {
			PreparedStatement statement = db.dbConn.prepareStatement(
					"INSERT INTO candidates (CANDIDATE_ID, LAST_NAME, FIRST_NAME, PARTY, MUNICIPALITY, AGE, DESCRIPTION) VALUES(?,?,?,?,?,?,?)");
			statement.setString(1, request.getParameter("candidate_id"));
			statement.setString(2, request.getParameter("last_name"));
			statement.setString(3, request.getParameter("first_name"));
			statement.setString(4, request.getParameter("party"));
			statement.setString(5, request.getParameter("municipality"));
			statement.setString(6, request.getParameter("age"));
			statement.setString(7, request.getParameter("description"));

			boolean doesExist = IfExists(request);
			if (doesExist) {
				return "Candidate already exists!";
			}
			statement.executeUpdate();
			return "Candidate added";
		} catch (Exception e) {
			return "Unable to add the candidate to the database!";
		}
	}

	/**
	 * Update candidate with the information in the form based on the id given.
	 */
	protected String UpdateCandidate(HttpServletRequest request) {
		try {
			PreparedStatement statement = db.dbConn.prepareStatement(
					"UPDATE candidates SET LAST_NAME = ?, FIRST_NAME = ?, PARTY = ?, MUNICIPALITY = ?, AGE = ?, DESCRIPTION = ? WHERE CANDIDATE_ID = ?");
			statement.setString(1, request.getParameter("last_name"));
			statement.setString(2, request.getParameter("first_name"));
			statement.setString(3, request.getParameter("party"));
			statement.setString(4, request.getParameter("municipality"));
			statement.setString(5, request.getParameter("age"));
			statement.setString(6, request.getParameter("description"));
			statement.setString(7, request.getParameter("candidate_id"));
			statement.executeUpdate();
			return "Candidate updated";
		} catch (Exception e) {
			return "Unable to update the candidate!";
		}
	}

	/**
	 * Delete candidate with the given id from the database.
	 */
	protected String DeleteCandidate(String candidateID) {
		try {
			PreparedStatement statement = db.dbConn.prepareStatement("DELETE FROM candidates WHERE CANDIDATE_ID = ?");
			statement.setString(1, candidateID);
			statement.executeUpdate();
			return "Candidate removed";
		} catch (Exception e) {
			return "Unable to remove candidate from the database!";
		}
	}

	/**
	 * Choose all candidates from the database where candidate_id is the same as the
	 * given id in the form. Used to check if a candidate with the same id already
	 * exists
	 */
	protected boolean IfExists(HttpServletRequest request) throws Exception {
		PreparedStatement statement = db.dbConn.prepareStatement("SELECT * FROM candidates WHERE CANDIDATE_ID = ?");
		statement.setString(1, request.getParameter("candidate_id"));
		ResultSet rs = statement.executeQuery();
		rs.beforeFirst();
		if (rs.next()) {
			System.out.println("Already exists!");
			return true;
		}
		return false;
	}
}

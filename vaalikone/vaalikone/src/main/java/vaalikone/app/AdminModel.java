package vaalikone.app;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

public class AdminModel {
	
	DatabaseConnection db;
	
	int candidateCount = 0;
	
	ArrayList<ArrayList<String>> LIST_OF_CANDIDATES = new ArrayList<ArrayList<String>>();
	/**
	 * Fetches all the candidates from the database and their info.
	 * Prints an error if it is unsuccessful.
	 * See also ExecuteQuery method in DataBaseConnection.java  
	 */
	protected void GetCandidates() {
		ArrayList<ArrayList<String>> LIST_OF_CANDIDATES = new ArrayList<ArrayList<String>>();
		ResultSet rs = db.ExecuteSQL("SELECT * FROM candidates", 1);
		try {
			while(rs.next()) {
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
			e.printStackTrace();
			System.out.println("Error fetching candidates from the database!");
		}
	}
	
	protected String InsertCandidate(HttpServletRequest request) {
		try {
			String sql = "INSERT INTO candidates (CANDIDATE_ID, LAST_NAME, FIRST_NAME, PARTY, MUNICIPALITY, AGE, DESCRIPTION) VALUES('" + request.getParameter("candidate_id") + "', '"
					+ request.getParameter("first_name") + "', '" + request.getParameter("last_name") + "', '"
					+ request.getParameter("party") + "', '" + request.getParameter("municipality") + "', '"
					+ request.getParameter("age") + "', '" + request.getParameter("description") + "')";
			
			boolean doesExist = IfExists(request);
			if (doesExist) {
				return "Candidate already exists!";
			}
			db.ExecuteSQL(sql, 2);
			return "Candidate added to the database!";
		} catch (Exception e) {
			// TODO: handle exception
			return "Unable to add the candidate to the database!";
		}
	}

	protected String UpdateCandidate(HttpServletRequest request) {
		try {
			String sql = "UPDATE candidates SET"
					+ " LAST_NAME = '" + request.getParameter("last_name")
					+ "', FIRST_NAME = '" + request.getParameter("first_name")
					+ "', PARTY = '" + request.getParameter("party")
					+ "', MUNICIPALITY = '" + request.getParameter("municipality")
					+ "', AGE = '" + request.getParameter("age")
					+ "', DESCRIPTION = '" + request.getParameter("description")
					+ "' WHERE CANDIDATE_ID = " + request.getParameter("candidate_id") + ";";
			System.out.println(sql);
			db.ExecuteSQL(sql, 2);
			return "Candidate added to the database!";
		} catch (Exception e) {
			// TODO: handle exception
			return "Unable to add the candidate to the database!";
		}
	}
	
	
	protected String DeleteCandidate(String candidateID) {
		try {
			String sql = "DELETE FROM candidates WHERE CANDIDATE_ID = " + candidateID;
			db.ExecuteSQL(sql, 2);
			return "Candidate removed from the database!";
		} catch (Exception e) {
			// TODO: handle exception
			return "Unable to remove candidate from the database!";
		}
	}
	/**
	 * Choose all candidates from the database where candidate_id is the same as the given parameter.
	 * This way you cannot have two candidates with the same ID.
	 * @throws SQLException 
	 */
	
	protected boolean IfExists(HttpServletRequest request) throws SQLException {
		String sql = "SELECT * FROM candidates WHERE CANDIDATE_ID = '" + request.getParameter("candidate_id") + "'";
		ResultSet rs = db.ExecuteSQL(sql, 1);
		rs.beforeFirst();
		if (rs.next()) {
			System.out.println("Already exists!");
			return true;
		}
		return false;
	}
}


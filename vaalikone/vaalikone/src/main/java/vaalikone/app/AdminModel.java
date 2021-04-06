package vaalikone.app;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AdminModel {
	
	DatabaseConnection db;
	HttpServletResponse response;
	
	int candidateCount = 0;
	
	ArrayList<ArrayList<String>> LIST_OF_CANDIDATES = new ArrayList<ArrayList<String>>();
	
	protected void GetCandidates() {
		ArrayList<ArrayList<String>> LIST_OF_CANDIDATES = new ArrayList<ArrayList<String>>();
		ResultSet rs = db.ExecuteSQL("SELECT * FROM candidates");
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
	
	protected void InsertCandidate() {
		String sql = "INSERT INTO candidates VALUES(VALUE1, ?, ?, ?, ?, ?, ?)";
		ResultSet rs = db.ExecuteSQL(sql);
	}
	
}

package vaalikone.app;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Submit
 */
@SuppressWarnings("serial")
@WebServlet(name = "Submit", urlPatterns = { "/submit" })

public class Submit extends HttpServlet {
	QuestionModel model = new QuestionModel();
	DatabaseConnection db = new DatabaseConnection();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		db.TestConnection();
		model.db = this.db;
		model.questionCount = db.CountQuestions();
		String weComeFromAdmin = request.getParameter("x");
		if (weComeFromAdmin.matches("1")) {
			AddAnswers(request, response);
			return;
		}
		GoToResults(request, response);
	}
	
	/**
	 * Gets the topcandidate from AdminModel.java and prints it out. Gives percentage match, candidates number and candidates name.
	 */

	protected void GoToResults(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ArrayList<Integer> USER_ANSWERS = new ArrayList<Integer>();
		for (int i = 1; i < model.questionCount; i++) {
			String value = request.getParameter("Q" + i);
			USER_ANSWERS.add(Integer.parseInt(value));
		}
		model.USER_ANSWERS = USER_ANSWERS;
		String municipality = request.getParameter("municipality");
		String candidateInfo = model.GetTopCandidate(municipality);
		String[] parts = candidateInfo.split("_");
		int candidate = Integer.parseInt(parts[0]);
		float percentage = Float.parseFloat(parts[1]);
		RequestDispatcher rd = request.getRequestDispatcher("results.jsp");
		request.setAttribute("municipality", "the municipality of " + request.getParameter("municipality"));
		if (municipality.endsWith("*")) {
			request.setAttribute("municipality", "all municipalities.");
		}
		String addToFile = "";
		addToFile += "The best match for you is <br>" + parts[2] + " " + parts[3] + "<br> With the number " + candidate + "<br>Match: " + percentage + "%";
		request.setAttribute("best_candidate", candidate);
		request.setAttribute("percentage", percentage);
		request.setAttribute("match_text", addToFile);
		rd.forward(request, response);
	}
	/**
	 * If the user came from admin, adds the answers given to the right candidate based on candidate ID. 
	 */

	protected void AddAnswers(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			String candidateID = request.getParameter("candidate_id");
			for (int i = 1; i < model.questionCount; i++) {
				String value = request.getParameter("Q" + i);
				PreparedStatement statement = db.dbConn
						.prepareStatement("INSERT INTO answers (CANDIDATE_ID, QUESTION_ID, ANSWER) VALUES(?,?,?)");
				statement.setString(1, candidateID);
				statement.setInt(2, i);
				statement.setString(3, value);
				statement.executeUpdate();
			}
			response.sendRedirect("/admin");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

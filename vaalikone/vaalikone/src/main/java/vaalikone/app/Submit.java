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

	protected void GoToResults(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ArrayList<Integer> USER_ANSWERS = new ArrayList<Integer>();
		for (int i = 1; i < model.questionCount; i++) {
			String value = request.getParameter("Q" + i);
			USER_ANSWERS.add(Integer.parseInt(value));
		}
		model.USER_ANSWERS = USER_ANSWERS;
		int match = model.GetTopCandidate();
		RequestDispatcher rd = request.getRequestDispatcher("results.jsp");
		request.setAttribute("results", match);
		rd.forward(request, response);
	}

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
				db.ExecuteSQL(statement, 2);
			}
			response.sendRedirect("/admin");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

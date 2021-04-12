package vaalikone.app;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
@WebServlet(name = "Insert", urlPatterns = { "/add" })
public class Insert extends HttpServlet {

	DatabaseConnection db = new DatabaseConnection();
	AdminModel aModel = new AdminModel();
	QuestionModel qModel = new QuestionModel();

	/**
	 * Add the candidate given in the form to the database. We check if the
	 * candidate exists.
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		db.TestConnection();
		aModel.db = this.db;
		qModel.db = this.db;

		String x = aModel.InsertCandidate(request);
		if (x.matches("Candidate already exists!")) {
			RequestDispatcher rd = request.getRequestDispatcher("/admin");
			x = "<div class='invalid-feedback d-block'>Candidate already exists!</div>";
			request.setAttribute("exists", x);
			rd.forward(request, response);
		} else {
			try {
				RequestDispatcher rd = request.getRequestDispatcher("/questions.jsp");
				request.setAttribute("questions", qModel.GenerateQuestions());
				request.setAttribute("x", "1");
				request.setAttribute("candidate_id", request.getParameter("candidate_id"));
				rd.forward(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
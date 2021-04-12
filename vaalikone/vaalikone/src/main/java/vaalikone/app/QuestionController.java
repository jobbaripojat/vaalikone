package vaalikone.app;

import java.io.IOException;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@SuppressWarnings("serial")
@WebServlet(name = "QuestionController", urlPatterns = { "/questions" })
public class QuestionController extends HttpServlet {

	QuestionModel model = new QuestionModel();
	DatabaseConnection db = new DatabaseConnection();

	public QuestionController() {

	}

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			Initialize();
			processRequest(request, response);
		} catch (Exception e) {
			System.out.println("Initialization failed!");
			e.printStackTrace();
		}
	}

	protected void Initialize() throws Exception {
		db.TestConnection();
		model.db = this.db;
		model.candidateCount = db.CountCandidates();
	}

	/**
	 * Fetches the questions from the database and prints them out to questions.jsp.
	 * When submit is pressed it will redirect the user to the results page.
	 */
	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			String addToFile = model.GenerateQuestions();
			request.setAttribute("questions", addToFile);
			request.setAttribute("x", "0");
			RequestDispatcher rd = request.getRequestDispatcher("questions.jsp");
			request.setAttribute("municipality", request.getParameter("municipality"));
			rd.forward(request, response);
		} catch (Exception e) {
			System.out.println("Unable to fetch questions from the database!");
			e.printStackTrace();
		}

	}
}
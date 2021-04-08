package vaalikone.app;

import java.io.IOException;
import java.util.ArrayList;

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
			ArrayList<String> QUESTIONS = model.GetQuestions();

			String addToFile = "";

			addToFile += "<form action ='/submit' method='GET'>";

			int index = 1;
			for (int i = 0; i < 19; i++) {
				addToFile += String.valueOf(index++) + ". ";
				addToFile += QUESTIONS.get(i);
				addToFile += "<br>";
				addToFile += "<input type = 'radio' name = 'Q" + i + "rad' value = '1'> Täysin eri mieltä ";
				addToFile += "<input type = 'radio' name = 'Q" + i + "rad' value = '2'> Eri mieltä ";
				addToFile += "<input type = 'radio' name = 'Q" + i + "rad' value = '3'> Neutraali ";
				addToFile += "<input type = 'radio' name = 'Q" + i + "rad' value = '4'> Samaa mieltä ";
				addToFile += "<input type = 'radio' name = 'Q" + i + "rad' value = '5'> Täysin samaa mieltä";
				addToFile += "<br>";
				addToFile += "<br>";
			}

			addToFile += "<input type='submit' value='send'>";
			addToFile += "</form>";

			request.setAttribute("questions", addToFile);
			RequestDispatcher rd = request.getRequestDispatcher("questions.jsp");
			rd.forward(request, response);
		} catch (Exception e) {
			System.out.println("Unable to fetch questions from the database!");
			e.printStackTrace();
		}

		/*
		 * ArrayList<Integer> USER_ANSWERS = new ArrayList<Integer>(); for (int i = 0; i
		 * < USER_ANSWERS.size(); i++) { String value =
		 * request.getParameter("Q"+i+"rad"); USER_ANSWERS.add(Integer.parseInt(value));
		 * } model.USER_ANSWERS = USER_ANSWERS;
		 */

	}
}
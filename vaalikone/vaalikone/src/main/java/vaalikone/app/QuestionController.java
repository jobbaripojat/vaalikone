package vaalikone.app;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@SuppressWarnings("serial")
@WebServlet(
	name = "QuestionController", 
	urlPatterns = { "/questions" }
)
public class QuestionController extends HttpServlet {
	
	QuestionModel model = new QuestionModel();
	DatabaseConnection db = new DatabaseConnection();
	
	public QuestionController() {
		
	}
	
	protected void Initialize() {
		db.TestConnection();
		model.db = this.db;
		model.candidateCount = db.CountCandidates();
	}
/**
 * Fetches the questions from the database and prints them out at questions.jsp.
 * When submit is pressed it redirects the user to a result page.
 */
	
	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");

		RequestDispatcher rd = request.getRequestDispatcher("questions.jsp");
		ArrayList<String> QUESTIONS = new ArrayList<>();
		QUESTIONS = model.GetQuestion();
		
		String addToFile = "";
		
		addToFile += "<form action ='/submit' method='GET'>";
		
		int index = 1;
		for (int i = 0; i < 19; i++) {
			addToFile += String.valueOf(index++) + ". ";
			addToFile += QUESTIONS.get(i);
			addToFile += "<br>";
			addToFile += "<input type = 'radio' name = 'Q"+i+"rad' value = '1'> Täysin eri mieltä ";
			addToFile += "<input type = 'radio' name = 'Q"+i+"rad' value = '2'> Eri mieltä ";
			addToFile += "<input type = 'radio' name = 'Q"+i+"rad' value = '3'> Neutraali ";
			addToFile += "<input type = 'radio' name = 'Q"+i+"rad' value = '4'> Samaa mieltä ";
			addToFile += "<input type = 'radio' name = 'Q"+i+"rad' value = '5'> Täysin samaa mieltä";
			addToFile += "<br>";
			addToFile += "<br>";
		}
		addToFile += "<input type='submit' value='send'>";
		addToFile += "</form>";
		request.setAttribute("questions", addToFile);
		rd.forward(request, response);
		
		
		/*
		 * ArrayList<Integer> USER_ANSWERS = new ArrayList<Integer>(); for (int i = 0; i
		 * < USER_ANSWERS.size(); i++) { String value =
		 * request.getParameter("Q"+i+"rad"); USER_ANSWERS.add(Integer.parseInt(value));
		 * } model.USER_ANSWERS = USER_ANSWERS;
		 */

	}
	
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Initialize();
		processRequest(request, response);
	}
}
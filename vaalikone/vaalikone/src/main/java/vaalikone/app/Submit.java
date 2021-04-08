package vaalikone.app;

import java.io.IOException;
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
@WebServlet(name = "/Submit",
		urlPatterns = { "/results" })

public class Submit extends HttpServlet {
	QuestionModel model = new QuestionModel();
	DatabaseConnection db = new DatabaseConnection();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		db.TestConnection();
		model.db = this.db;
		
		ArrayList<Integer> USER_ANSWERS = new ArrayList<Integer>(); 
		for (int i = 0; i < USER_ANSWERS.size(); i++) { 
			String value = request.getParameter("Q"+i); 
			USER_ANSWERS.add(Integer.parseInt(value));
		} 
		model.USER_ANSWERS = USER_ANSWERS;
		int match = model.GetTopCandidate();
		RequestDispatcher rd = request.getRequestDispatcher("results.jsp");
		request.setAttribute("results",match);
		rd.forward(request, response);
		
	}

}

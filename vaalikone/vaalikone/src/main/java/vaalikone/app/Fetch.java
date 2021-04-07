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
 * Servlet implementation class Fetch
 */

@SuppressWarnings("serial")
@WebServlet(
	name = "Fetch",
	urlPatterns = { "/fetch" }
)
public class Fetch extends HttpServlet {

	DatabaseConnection db = new DatabaseConnection();
	AdminModel model = new AdminModel();
	
    public Fetch() {
        super();
    }
    
    /**
     * When the edit button is clicked, this method fetches the matching data from the database.
     * Loops through the ids until finding a match.
     */
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		db.TestConnection();
		model.db = this.db;
		
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");

		RequestDispatcher rd = request.getRequestDispatcher("/admin");
		
		model.GetCandidates();
		String id = request.getParameter("candidate_id");
		
		for (int i = 0; i < model.LIST_OF_CANDIDATES.size(); i++) {
			ArrayList<String> candidate = model.LIST_OF_CANDIDATES.get(i);
			if (candidate.get(0).matches(id)) {
				request.setAttribute("candidate_id", candidate.get(0));
				request.setAttribute("first_name", candidate.get(1));
				request.setAttribute("last_name", candidate.get(2));
				request.setAttribute("municipality", candidate.get(3));
				request.setAttribute("party", candidate.get(4));
				request.setAttribute("age", candidate.get(5));
				request.setAttribute("description", candidate.get(6));
			}
		}
		
		AdminController.formAction = "/update";
		rd.forward(request, response);
	}
}
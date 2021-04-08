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
	AdminModel model = new AdminModel();

	/**
	 * Add the candidate given in the form to the database. We check if the candidate exists.
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		db.TestConnection();
		model.db = this.db;

		String x = model.InsertCandidate(request);
		if (x.matches("Candidate already exists!")) {
			RequestDispatcher rd = request.getRequestDispatcher("/admin");
			x = "<div class='invalid-feedback d-block'>Candidate already exists!</div>";
			request.setAttribute("exists", x);
			rd.forward(request, response);
		} else {
			response.sendRedirect("/admin");
		}

	}
}
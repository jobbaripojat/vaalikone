package vaalikone.app;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
@WebServlet(name = "Delete", urlPatterns = { "/delete" })
public class Delete extends HttpServlet {

	DatabaseConnection db = new DatabaseConnection();
	AdminModel model = new AdminModel();

	/**
	 * Deletes the chosen candidate from the list and from the database. Redirects
	 * back to the same page, effectively refreshing the page.
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		db.TestConnection();
		model.db = this.db;

		String id = request.getParameter("candidate_id");
		model.DeleteCandidate(id);
		response.sendRedirect("/admin");
	}
}
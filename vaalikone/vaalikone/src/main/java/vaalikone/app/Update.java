package vaalikone.app;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
@WebServlet(name = "Update", urlPatterns = { "/update" })
public class Update extends HttpServlet {

	DatabaseConnection db = new DatabaseConnection();
	AdminModel model = new AdminModel();

	/**
	 * When the submit button is clicked this servlet updates the database with the
	 * information in the form.
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		db.TestConnection();
		model.db = this.db;

		model.UpdateCandidate(request);
		response.sendRedirect("/admin");
	}
}
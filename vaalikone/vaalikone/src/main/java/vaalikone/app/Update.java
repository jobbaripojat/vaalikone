package vaalikone.app;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
@WebServlet(
	name = "Update",
	urlPatterns = {"/update"}
)
public class Update extends HttpServlet {
	
	DatabaseConnection db = new DatabaseConnection();
	AdminModel model = new AdminModel();

	public Update() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		db.TestConnection();
		model.db = this.db;
		
		model.UpdateCandidate(request);
		response.sendRedirect("/admin");
	}
}
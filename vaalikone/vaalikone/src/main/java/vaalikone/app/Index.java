package vaalikone.app;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
@WebServlet(name = "Index", urlPatterns = { "/index" })

public class Index extends HttpServlet {

	DatabaseConnection db = new DatabaseConnection();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		db.TestConnection();

		String addToFile = "";
		ArrayList<String> municipalities = db.GetMunicipalities();
		for (int i = 0; i < municipalities.size(); i++) {
			addToFile += "<option value='" + municipalities.get(i) + "'>" + municipalities.get(i) + "</option>";
		}

		RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
		request.setAttribute("municipalities", addToFile);
		rd.forward(request, response);
	}
}
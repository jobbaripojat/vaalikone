package vaalikone.app;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.*;
import java.util.*;

@WebServlet(
	name = "AdminController",
	urlPatterns = { "/admin"}
)
public class AdminController extends HttpServlet {
	
	AdminModel model = new AdminModel();
	DatabaseConnection db = new DatabaseConnection();

	public AdminController() {
		
	}
	
	protected void Initialize() {
		db.TestConnection();
		model.db = this.db;
		model.candidateCount = db.CountCandidates();
	}

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Initialize();
		processRequest(request, response);
	}
	
	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintAllCandidates(request, response);
	}
	
	protected String GenerateCandidateCell(int candidateID) {
		ArrayList<String> CANDIDATE = model.LIST_OF_CANDIDATES.get(candidateID-1);
		
		String addToFile = "<tr>";
		
		addToFile += "<th scope='row'>" + candidateID + "</th>";
		addToFile += "<td>" + CANDIDATE.get(2) + "</td>";
		addToFile += "<td>" + CANDIDATE.get(1) + "</td>";
		addToFile += "<td><button type='button' class='btn btn-primary'>Edit</button></td>";
		addToFile += "<td><button type='button' class='btn btn-danger'>Delete</button></td>";
		addToFile += "</tr>";
		
		return addToFile;
	}
	
	protected void PrintAllCandidates(HttpServletRequest request, HttpServletResponse response) {
		model.GetCandidates();
		
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		
		RequestDispatcher rd = request.getRequestDispatcher("admin.jsp");
		
		String addToFile = "";
		
		for (int i = 1; i < model.candidateCount; i++) {
			addToFile += GenerateCandidateCell(i);
		}

		request.setAttribute("candidates", addToFile);
		try {
			rd.forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
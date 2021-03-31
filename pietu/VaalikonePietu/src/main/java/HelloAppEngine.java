import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
@WebServlet(
    name = "HelloAppEngine",
    urlPatterns = {"/hello"}
)
public class HelloAppEngine extends HttpServlet {
	  	

    @Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
    	
    	// The needed variables for the program
    	
    	String driver = "com.mysql.jdbc.Driver";
		String DBpath = "//localhost/vaalikone";
		String username = "root";
		String password = "Zelja";
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		//Create a connection to the database
    	
		 try {
			Class.forName(driver).newInstance();
		} catch (Exception ex) {
			Logger.getLogger(HelloAppEngine.class.getName()).log(Level.SEVERE, "poo!!", ex);
		}

		try {
			con = DriverManager.getConnection("jdbc:mysql:" + DBpath, username, password);
		} catch (SQLException e2) {
			e2.printStackTrace();
		}
		try {
			stmt = con.createStatement();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		//What to search from the database

		String sql;
		sql = "Select*From questions";
		
		//Executing the query from the database
		
		try {
			rs = stmt.executeQuery(sql);
		} catch (SQLException e3) {
			e3.printStackTrace();
		}

		ArrayList<String> QUESTION = new ArrayList<String>();
		ArrayList<Integer> QUESTION_ID = new ArrayList<>();
		
		
		try {
			while (rs.next()) {
				int a = rs.getInt("QUESTION_ID");
				String i1 = rs.getString("QUESTION");
				QUESTION_ID.add(a);
				QUESTION.add(i1);

			}
		} catch (Exception ex1) {
			System.out.println("*** Failure ***");
		}
		response.setContentType("text/plain");
    	response.setCharacterEncoding("UTF-8");   	
    	response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<p>");
        response.getWriter().print("Election Machine\r\n");
        out.println("<br>");
        response.getWriter().print("Questions\r\n");
        out.println("</p>");
        out.println("<html>");
        out.println("<body>");
        out.println("<ul>");
        
        int index = 1;
		for (String x : QUESTION) {	
			out.println("<li>"+ String.valueOf(index++)+": "+ x + "</li>");
        }
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

    
  

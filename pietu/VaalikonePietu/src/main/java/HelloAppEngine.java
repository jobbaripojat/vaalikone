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
    urlPatterns = {"/questions"}
)
public class HelloAppEngine extends HttpServlet {
	model m = new model();
	

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
		
		
		m.getConnection();
		
		
		ArrayList<String> k = new ArrayList<>();
		
		k = m.getQuestion();
		
		
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
        
        String value;
        
        
        int index = 1;
		for (String x : k) {
			out.println("<li>"+ String.valueOf(index++)+": "+ x + "</li>");
        }
		
		out.println("<p><form action = \"/submit\" method=\"GET\"");
		index = 1;
		for (int i = 0; i < 19; i++) {
			out.println("<li>" + String.valueOf(index++));
			out.println("<input type = \"radio\" name = \"Q"+i+"rad\" value = \"1\"> Täysin eri mieltä");
			out.println("<input type = \"radio\" name = \"Q"+i+"rad\" value = \"2\">  Eri mieltä");
			out.println("<input type = \"radio\" name = \"Q"+i+"rad\" value = \"3\"> Neutraali");
			out.println("<input type = \"radio\" name = \"Q"+i+"rad\" value = \"4\"> Samaa mieltä");
			out.println("<input type = \"radio\" name = \"Q"+i+"rad\" value = \"5\"> Täysin samaa mieltä");
			out.println("</li>");
		}
		
		ArrayList<String> vastaukset = new ArrayList<>();
		
		/* for (int i = 0; i <20; i++) {
			String value1 = request.getParameter("Q1rad");
			vastaukset.add(value1);	
			response.getWriter().print(value1);
		}
		

		out.println("<input type=\"submit\" value=\"send\">");
		
		out.println("</form> </p>");
		
		out.println("</html>");
        out.println("</body>");
        out.println("</ul>");
        */
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

    
  

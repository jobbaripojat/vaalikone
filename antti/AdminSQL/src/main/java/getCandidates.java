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

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
@WebServlet(
    name = "getCandidates",
    urlPatterns = {"/getcandidates"})
public class getCandidates extends HttpServlet {

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) 
      throws IOException {
	  
	  String driver = "com.mysql.jdbc.Driver";
		String DBpath = "//localhost/vaalikone";
		String username = "root";
		String password = "Antti";
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver).newInstance();
		} catch (Exception ex) {
			Logger.getLogger(getCandidates.class.getName()).log(Level.SEVERE, "poo!!", ex);
		}
		
		try {
			con = DriverManager.getConnection("jdbc:mysql:" + DBpath, username, password);
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		try {
			stmt = con.createStatement();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		String sql;
        
        sql = "Select*From candidates";
        try {
			rs = stmt.executeQuery(sql);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        System.out.print(rs);
        
        try {
            int i = rs.getInt("ID"); // There is also other version for getInt which relies on column index number
            System.out.println("i: " + i + "\n");    
            
        } catch (Exception ex) {
            System.out.println("*** Failure ***");
            
        ArrayList<String> first_name = new ArrayList<String>();
        ArrayList<String> last_name = new ArrayList<String>();
        ArrayList<String> municipality = new ArrayList<String>();
        ArrayList<String> party = new ArrayList<String>();
        ArrayList<String> age = new ArrayList<String>();

        
        try {
			while (rs.next()) {
			  	int a = rs.getInt("CANDIDATE_ID");
			      String i1 = rs.getString("FIRST_NAME");
			      String e = rs.getString("LAST_NAME");
			      String d = rs.getString("MUNICIPALITY");
			      String p = rs.getString("PARTY");
			      String a1 = rs.getString("AGE");
			      first_name.add(i1);
			      last_name.add(e);
			      municipality.add(d);
			      party.add(p);
			      age.add(a1);
			      System.out.println(a);
			      System.out.println(i1);
			      System.out.println(e);
			      System.out.println(d);
			      
			      
				}
        } catch (Exception ex1) {
            System.out.println("*** Failure ***");
        }
        
        	response.setContentType("text/plain");
        	response.setCharacterEncoding("UTF-8");
        	response.getWriter().print("Election Machine\r\n");
        	

        	response.setContentType("text/html");
            PrintWriter out = response.getWriter();

            out.println("<html>");
            out.println("<body>");
            out.println("<ul>");
            
            for (int i = 0; i<first_name.size(); i++) {
            	out.println("<li>" + first_name.get(i) + " " + last_name.get(i) + " " + age.get(i) + ", Puolue : " + party.get(i) +  ", Vaalipiiri: " + municipality.get(i) + "</li>");
                out.println("<br>");
                }
            out.println("</ul>");
            out.println("</body></html>");
    
        }
  	}
}
  
  
  
  
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import java.util.*;

@SuppressWarnings("serial")
@WebServlet(
    name = "removeCandidates",
    urlPatterns = {"/removecandidates"})

public class removeCandidates  extends HttpServlet {
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) 
		      throws IOException {
		String driver = "com.mysql.jdbc.Driver";
		String DBpath = "//localhost/vaalikone";
		String username = "root";
		String password = "Antti";
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		float poistettava_id;

		try {
			Class.forName(driver).newInstance();
		} catch (Exception ex) {
			Logger.getLogger(getCandidates.class.getName()).log(Level.SEVERE, "poo!!", ex);
		}
		
		
		try {
		con = DriverManager.getConnection("jdbc:mysql:" + DBpath, username, password);
		stmt = con.createStatement();
		}catch (Exception ex) {
            System.out.println("*** Failure ***");
		}
		
		

		try {		
		String sql;
        sql = "Select*From candidates";
        rs = stmt.executeQuery(sql);
        System.out.print(rs);
		}catch (Exception ex1) {
            System.out.println("*** Failure ***");
		}
        
        try {
            int i = rs.getInt("ID"); // There is also other version for getInt which relies on column index number
            System.out.println("i: " + i + "\n");    
            
        } catch (Exception ex11) {
            System.out.println("*** Failure ***");
        }


         
         ArrayList<Integer> candidate_id = new ArrayList<Integer>();
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
				      candidate_id.add(a);
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
	         System.out.println("*** Failed to create array lists ***");
	     }
	     
	     	response.setContentType("text/plain");
	     	response.setCharacterEncoding("UTF-8");
	     	response.getWriter().print("Election Machine\r\n");
	     	
	
	     	response.setContentType("text/html");
	        PrintWriter out = response.getWriter();
	         

	         
	         out.println("<html>");
	         out.println("<body>");
	         out.println("<ul>");
	         out.println("<form action=/removecandidates method=GET");
	         
	         for (int i = 0; i<candidate_id.size(); i++) {
	        	 out.println("<br>");
	         	 out.println("<li>" + "Kandidaatin id: " + candidate_id.get(i) + ",   " + first_name.get(i) + " " + last_name.get(i) + " " + age.get(i) + ", Puolue : " + party.get(i) +  ", Vaalipiiri: " + municipality.get(i) + "</li>");
	             out.println("<br>");
	             }
	         
	         out.println("<br>");
	         
	         out.println("<label for=\"fname\">Syötä poistettavan kandidaatin ID:</label><br>");
	         out.println("<input type=\"text\" id=\"poistettava_id\" name=\"poistettava_id\"><br>");
	         out.println("<input type=\"submit\" value=\"Submit\">");
	         	         
	         try {
	         poistettava_id = Float.parseFloat((request.getParameter("poistettava_id")));	         
	         // create sql query	         
	         Statement st = con.createStatement(); 
	         st.executeUpdate("DELETE FROM candidates WHERE CANDIDATE_ID = '"+ poistettava_id +"'");
	         System.out.println("Kandidaatti" + poistettava_id + " poistettu");
	        
	         
	         }catch (Exception ex1) {
		         System.out.println("*** Failed to remove ***");
		     }
	         
	        
	         out.println("</form>");
	         out.println("</ul>");
	         out.println("</body></html>");
	         

	}
}

	

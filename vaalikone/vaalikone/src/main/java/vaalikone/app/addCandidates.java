import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
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
    name = "addCandidates",
    urlPatterns = {"/addcandidates"})

public class addCandidates  extends HttpServlet {
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) 
		      throws IOException {
		String driver = "com.mysql.jdbc.Driver";
		String DBpath = "//localhost/vaalikone";
		String username = "root";
		String password = "Antti";
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;


		String sql;
        sql = "Select*From candidates";
        rs = stmt.executeQuery(sql);
        int i = rs.getInt("ID"); // There is also other version for getInt which relies on column index number
        System.out.println("i: " + i + "\n");    
		
		/* try {		
        System.out.print(rs);
		}catch (Exception ex1) {
            System.out.println("*** failed to select * from candidates ***");
		}
        
        try {
            
        } catch (Exception ex11) {
            System.out.println("*** Failed to get ID ***");
        } */


         
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
	         out.println("<form action=/addcandidates method=post");
	         
	         for (int i = 0; i<candidate_id.size(); i++) {
	        	 out.println("<br>");
	         	 out.println("<li>" + "Kandidaatin id: " + candidate_id.get(i) + ",  " + first_name.get(i) + " " + last_name.get(i) + " " + age.get(i) + ", Puolue : " + party.get(i) +  ", Vaalipiiri: " + municipality.get(i) + "</li>");
	             out.println("<br>");
	             }
	         
	         out.println("<br>");
	         
	         out.println("<label for=\"fname\">Sy�t� ID:</label><br>");
	         out.println("<input type=\"text\" id=\"lis�tt�v�_id\" name=\"lis�tt�v�_id\"><br>");
	         
	         out.println("<label for=\"fname\">Sy�t� sukunimi:</label><br>");
	         out.println("<input type=\"text\" id=\"lis�tt�v�_sukunimi\" name=\"lis�tt�v�_sukunimi\"><br>");
	         
	         out.println("<label for=\"fname\">Sy�t� etunimi:</label><br>");
	         out.println("<input type=\"text\" id=\"lis�tt�v�_etunimi\" name=\"lis�tt�v�_etunimi\"><br>");
	         
	         out.println("<label for=\"fname\">Sy�t� kunta:</label><br>");
	         out.println("<input type=\"text\" id=\"lis�tt�v�_kunta\" name=\"lis�tt�v�_kunta\"><br>");
	         
	         out.println("<label for=\"fname\">Sy�t� puolue:</label><br>");
	         out.println("<input type=\"text\" id=\"lis�tt�v�_puolue\" name=\"lis�tt�v�_puolue\"><br>");
	         
	         out.println("<label for=\"fname\">Sy�t� ik�:</label><br>");
	         out.println("<input type=\"text\" id=\"lis�tt�v�_ik�\" name=\"lis�tt�v�_ik�\"><br>");
	         
	         out.println("<label for=\"fname\">Sy�t� kuvaus:</label><br>");
	         out.println("<input type=\"text\" id=\"lis�tt�v�_desc\" name=\"lis�tt�v�_desc\"><br>");
	         
	         out.println("<input type=\"submit\" value=\"Submit\">");
	         	         

	         
	        
	         out.println("</form>");
	         out.println("</ul>");
	         out.println("</body></html>");
	}
	         
	         
	         public void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
	        	String driver = "com.mysql.jdbc.Driver";
	     		String DBpath = "//localhost/vaalikone";
	     		String username = "root";
	     		String password = "Antti";
	     		Connection con = null;
	     		
	     	

	     		try {
	     			Class.forName(driver).newInstance();
	     		} catch (Exception ex) {
	     			Logger.getLogger(getCandidates.class.getName()).log(Level.SEVERE, "poo!!", ex);
	     		}
	     		
	     		
	     		try {
	     		con = DriverManager.getConnection("jdbc:mysql:" + DBpath, username, password);
	     		}catch (Exception ex) {
	                 System.out.println("*** Failed to connect ***");
	     		}
	     		
	     		
	     		try {
	     		PreparedStatement st = con.prepareStatement("INSERT INTO candidates VALUES(?, ?, ?, ?, ?, ?, ?)");
	     		st.setInt(1, Integer.valueOf(request.getParameter("lis�tt�v�_id")));
	     		st.setString(2, request.getParameter("lis�tt�v�_sukunimi"));
	     		st.setString(3, request.getParameter("lis�tt�v�_etunimi"));
	     		st.setString(4, request.getParameter("lis�tt�v�_puolue"));
	     		st.setString(5, request.getParameter("lis�tt�v�_kunta"));    		
	     		st.setInt(6, Integer.valueOf(request.getParameter("lis�tt�v�_ik�")));
	     		st.setString(7, request.getParameter("lis�tt�v�_desc"));  
     		
	     		st.executeUpdate();	     		          
	   	         

	   	         System.out.println("Kandidaatti lis�tty");
	   	        
	   	         
	   	         }catch (Exception ex1) {
	   		         System.out.println("*** Failed to add candidate ***");
	   		     }
	     		
	}
}

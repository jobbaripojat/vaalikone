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

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(name = "HelloAppEngine", urlPatterns = { "/show2" })
public class Show extends HttpServlet {
	private static final long serialVersionUID = 1L;

	String driver = "com.mysql.jdbc.Driver";
	String DBpath = "//localhost/vaalikone";
	String username = "root";
	String password = "Zelja";
	Connection con = null;
	Statement stmt = null;
	ResultSet rs = null;

	public Show() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			Class.forName(driver).newInstance();
		} catch (Exception ex) {
			Logger.getLogger(Show.class.getName()).log(Level.SEVERE, "poo!!", ex);
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

		sql = "Select*From questions";
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
		}

		ArrayList<String> first_name = new ArrayList<String>();
		try {
			while (rs.next()) {
				int a = rs.getInt("CANDIDATE_ID");
				String i1 = rs.getString("FIRST_NAME");
				first_name.add(i1);
				System.out.println(a);

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

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

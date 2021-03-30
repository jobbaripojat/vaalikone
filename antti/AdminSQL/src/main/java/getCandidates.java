import java.io.IOException;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.*;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class getCandidates {
	public static void main(String[] args) throws SQLException {
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

		con = DriverManager.getConnection("jdbc:mysql:" + DBpath, username, password);
		stmt = con.createStatement();

		String sql;
        
        sql = "Select*From candidates";
        rs = stmt.executeQuery(sql);
        System.out.print(rs);
        
        ArrayList<String> kysymykset = new ArrayList<String>();
        
        try {
            int i = rs.getInt("ID"); // There is also other version for getInt which relies on column index number
            System.out.println("i: " + i + "\n");
        } catch (Exception ex) {
            System.out.println("*** Does not work without first using rs.next()!!! ***");
            // Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
         // Going through the results
            while (rs.next()) {
            	int a = rs.getInt("CANDIDATE_ID");
                String i = rs.getString("FIRST_NAME");
                String e = rs.getString("LAST_NAME");
                kysymykset.add(i);
                System.out.println(a);
                System.out.println(i);
                System.out.println(e);
            }
        }

	}
}

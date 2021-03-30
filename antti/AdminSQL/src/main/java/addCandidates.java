import java.io.IOException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import java.util.*;

public class addCandidates {
	
	public static void main (String[] Args) throws SQLException {
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

		
        try {
            int i = rs.getInt("ID"); // There is also other version for getInt which relies on column index number
            System.out.println("i: " + i + "\n");    
            
        } catch (Exception ex) {
            System.out.println("*** Failure ***");
            
            // create insert sql query
            Statement st = con.createStatement(); 
            st.executeUpdate("INSERT INTO candidates " + "VALUES ()");
            
            System.out.println("Candidate added");
            

        }
		
	}
}
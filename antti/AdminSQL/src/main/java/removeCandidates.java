import java.io.IOException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import java.util.*;

public class removeCandidates {
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
        
        try {
            int i = rs.getInt("ID"); // There is also other version for getInt which relies on column index number
            System.out.println("i: " + i + "\n");    
            
        } catch (Exception ex) {
            System.out.println("*** Failure ***");

            // create delete sql query
            String query = "delete from candidates where CANDIDATE_ID = ?";
            PreparedStatement preparedStmt = con.prepareStatement(query);
            preparedStmt.setInt(1, 2);
            
            // excecute delete sql query
            preparedStmt.execute();
            System.out.println("Candidate removed");
            
        }
	}
}
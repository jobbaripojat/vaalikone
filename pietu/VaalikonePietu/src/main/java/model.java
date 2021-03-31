import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class model {
	
	String driver = "com.mysql.jdbc.Driver";
	String DBpath = "//localhost/vaalikone";
	String username = "root";
	String password = "Zelja";
	Connection con = null;
	Statement stmt = null;
	ResultSet rs = null;

	
	public void getConnection() {	
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
			
			String sql;
			sql = "Select*From questions";
			
			try {
				rs = stmt.executeQuery(sql);
			} catch (SQLException e3) {
				e3.printStackTrace();
			}
			
			// Creating arraylists to store the data
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
			
		
			
		
		
	}

}

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class model {

	protected String driver = "com.mysql.jdbc.Driver";
	protected String DBpath = "//localhost/vaalikone";
	protected String username = "root";
	protected String password = "Zelja";
	protected Connection con = null;
	protected Statement stmt = null;
	protected ResultSet rs = null;
	
	static String dbURL = "jdbc:mysql://localhost:3306/vaalikone";
	static Connection dbConn;

	public void getConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			dbConn = DriverManager.getConnection(dbURL, username, password);
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("Connection to the database failed!");
			System.exit(0);
		}
	}

	public ArrayList<String> getQuestion() {
		

		String sql;
		
		sql = "Select * From questions";

		try {
			Statement stmt = dbConn.createStatement();
			rs = stmt.executeQuery(sql);
		} catch (SQLException e1) {
			e1.printStackTrace();
			System.out.println("Epäonnistui");
		}

		ArrayList<String> QUESTION = new ArrayList<String>();
		ArrayList<Integer> QUESTION_ID = new ArrayList<Integer>();
		

		try {
			while (rs.next()) {
				Integer i2 =  rs.getInt("QUESTION_ID");
				String i1 = rs.getString("QUESTION");
				QUESTION.add(i1);
				QUESTION_ID.add(i2);
			};
			return QUESTION;
		} catch (Exception ex1) {
			System.out.println("*** Failure ***");

		}
		return QUESTION;
		

	}

}

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HaeTietoKannasta {

	public static void getConnection() {
		String driver = "com.mysql.jdbc.Driver";
		String DBpath = "//localhost/vaalikone";
		String username = "root";
		String password = "Zelja";
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver).newInstance();
		} catch (Exception ex) {
			Logger.getLogger(HaeTietoKannasta.class.getName()).log(Level.SEVERE, "poo!!", ex);
		}

		try {
			con = DriverManager.getConnection("jdbc:mysql:" + DBpath, username, password);
			stmt = con.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void GetTableData(Connection conn) {
		HelloAppEngine x = new HelloAppEngine();
		String sql = "SELECT * FROM questions";
		ArrayList<String> kysymykset = new ArrayList<String>();

		try {
			Statement statement = conn.createStatement();
			ResultSet result = statement.executeQuery(sql);

			while (result.next()) {
				int a = result.getInt("QUESTION_ID");
				String i = result.getString("QUESTION");
				kysymykset.add(i);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		

	}

}

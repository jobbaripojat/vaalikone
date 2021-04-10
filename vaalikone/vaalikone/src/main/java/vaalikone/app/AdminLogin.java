package vaalikone.app;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AdminLogin
 */
@WebServlet(name = "/AdminLogin", urlPatterns = { "/login" })
						

public class AdminLogin extends HttpServlet {
	DatabaseConnection db = new DatabaseConnection();

    public AdminLogin() {
        super();
        // TODO Auto-generated constructor stub
    }
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		db.TestConnection();

	}

}

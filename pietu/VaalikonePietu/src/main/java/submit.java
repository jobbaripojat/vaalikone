
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Array;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/submit")
public class submit extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public submit() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();

		ArrayList<Integer> a = new ArrayList<Integer>();

		for (int i = 0; i < 19; i++) {
			String name = "Q"+i+"rad";
			a.add(Integer.parseInt(request.getParameter(name)));
			System.out.println(a);
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

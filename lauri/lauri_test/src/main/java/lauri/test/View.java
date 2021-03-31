package lauri.test;

import java.io.IOException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(
	name = "answers",
	urlPatterns = {"/answers"}
)

public class View extends HttpServlet {

    private Controller controller;
    
    public void RegisterController() {
        Model model = new Model();
        View view = new View();
               
        Controller controller = new Controller(model, view);
        
        this.controller = controller;
        
		controller.Initialize();
    }
    
    public void WriteToDocument(String writable, HttpServletResponse response) {
		try {
			response.getWriter().println(writable);
		} catch (IOException e) {
			System.out.println("Whoops, couldn't write to the document.");
		}
    }
    
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		RegisterController();
		Controller.response = response;
		response.setContentType("text/html;charset=UTF-8");
		
		int candidateID = 0;
		int questionID = 0;
		
		try {
			candidateID = Integer.parseInt(request.getParameter("candidateID"));
			questionID = Integer.parseInt(request.getParameter("questionID"));
		} catch (Exception e) {
			response.getWriter().println("There was an error.");
			System.exit(0);
		}
		controller.GetFromDatabase(candidateID, questionID);
	}
}
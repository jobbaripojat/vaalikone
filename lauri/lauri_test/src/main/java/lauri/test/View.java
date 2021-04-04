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

    
	/**  
	* register controller and create the view and model.
	* test the database connection through Initialize()
	*/
    public void RegisterController() {
        Model model = new Model();
        View view = new View();
               
        Controller controller = new Controller(model, view);
        
        this.controller = controller;
        
		controller.Initialize();
    }

    
	/**  
	* write the string to the response(.html file) given
	*/
    public void WriteToDocument(HttpServletResponse response, String writable) {
		try {
			response.getWriter().println(writable);
		} catch (IOException e) {
			System.out.println("Whoops, couldn't write to the document.");
		}
    }
    
    
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		RegisterController();
		controller.response = response;
		response.setContentType("text/html;charset=UTF-8");
		
		int candidateID = 0;
		int questionID = 0;
		
		try {
			candidateID = Integer.parseInt(request.getParameter("candidateID"));
			String n = request.getParameter("questionID");
			if (n.isEmpty()) {
				questionID = 0;
			} else {
				questionID = Integer.parseInt(n);
			}
		} catch (Exception e) {
			response.getWriter().println("There was an error.");
			System.exit(0);
		}
		controller.GetFromDatabase(candidateID, questionID);
	}
} 
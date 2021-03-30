package lauri.test;

import javax.servlet.http.HttpServletResponse;

public class Controller {
	
    private Model model;
    private View view;
	
	static HttpServletResponse response;	
    
    public Controller(Model model, View frame) {
        this.model = model;
        this.view = frame;
    }

    protected void Initialize() {
    	model.TestConnection();
    }
    
    protected void GetFromDatabase(int candidate_id, int question_id) {
    	if (question_id == 0) {
        	int answer = model.GetAnswersFor(candidate_id);
        	view.WriteToDocument(Integer.toString(answer), response);
    	} else {
    		int answer = model.GetAnswersFor(candidate_id, question_id);
        	view.WriteToDocument(Integer.toString(answer), response);
    	}
    }
}
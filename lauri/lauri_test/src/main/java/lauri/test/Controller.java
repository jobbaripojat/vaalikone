package lauri.test;

import java.util.ArrayList;

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
    
    protected void GetFromDatabase(int candidateID, int questionID) {
    	if (questionID == 0) {
    		ArrayList<Integer> answer = model.GetAnswersFor(candidateID);
    		for (int i = 0; i < answer.size(); i++) {
            	view.WriteToDocument(Integer.toString(answer.get(i)), response);
            	view.WriteToDocument(Integer.toString(model.vastaukset.get(i)), response);
            	view.WriteToDocument(Integer.toString(model.CompareResults(answer.get(i), model.vastaukset.get(i))), response);
            	view.WriteToDocument("<br>", response);
    		}
    	} else {
        	int answer = model.GetAnswersFor(candidateID, questionID);
        	view.WriteToDocument(Integer.toString(answer), response);
    	}
    }
}
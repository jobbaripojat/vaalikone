package lauri.test;

import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.servlet.http.HttpServletResponse;

public class Controller {
	
    private Model model;
    private View view;
    
    HttpServletResponse response;	
	
    DecimalFormat df = new DecimalFormat("###.##");
    
    public Controller(Model model, View frame) {
        this.model = model;
        this.view = frame;
    }

    protected void Initialize() {
    	model.TestConnection();
    }

    
	/**  
	* used to fetch a candidate's answer to a specific question from the database
	* then we print out the results on to the .html file as a table
	*/
    protected void GetFromDatabase(int candidateID, int questionID) {
    	if (questionID == 0) {
    		ArrayList<Integer> answer = model.GetAnswersFor(candidateID);    		
    		float percentageMatch = model.CompareAnswers(candidateID);
        	view.WriteToDocument(response, "<table style='text-align:center'><tr><th>Candidate</th><th>User</th></tr>");       
        	
    		for (int i = 0; i < answer.size(); i++) {
            	view.WriteToDocument(response, "<tr><td>" + Integer.toString(answer.get(i)) + "</td>");            	
            	view.WriteToDocument(response, "<td>" + Integer.toString(model.vastaukset.get(i)) + "</td></tr>");
    		}
    		view.WriteToDocument(response,
    				"<tr><td>Match: " + df.format(percentageMatch) + "%</td></tr>" +
    				"<tr><td>Top candidate id: " + model.GetTopCandidate() + "</td></tr>" +
    				"</table>");
    	} else {
        	int answer = model.GetAnswersFor(candidateID, questionID);
        	view.WriteToDocument(response, Integer.toString(answer));
    	}
    }
}
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package evaluation;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import model.MyResponse;
import model.MyTeam;

/**
 *
 * @author Lam
 */
public class MultipleChoiceEvaluation implements TeamsEvaluation{

    /**
     * Calculate standard deviation of each team combination base on the diversity
     * of all the student responses.
     * @param teamsList
     * @return 
     */
    @Override
    public double calculateTeamsStandardDeviation(List<MyTeam> teamsList) {
            
        int teamCount;
        int aTeam = 0;
        int aPerson = 0;
        int numberOfQuestions = teamsList.get(aTeam).studentList.get(aPerson).getResponseList().size();
        double standardDeviation = 0.0;
        double teamScore;
        List<Double> scores = new LinkedList<Double>();
        
        //foreach question
        for (int i = 0; i < numberOfQuestions; i++) {
            teamCount = 0;
            teamScore = 0;
            List<Double> score = new LinkedList<Double>();
            for (MyTeam team : teamsList) {

                List<MyResponse> responseList = buildResponseListPerQuestion(team, i);
                int responseCount = getSpecificResponseCount(responseList);
                teamCount++;

                //TODO: Need to figure out how to find the total number of choices
                //Assume MC always has 5 choices...
                
                //TODO: Need to incorperate the weight into the calculation
                
                teamScore = responseCount / 5.0;
                team.setTeamScore(teamScore); //store to obj, not used right now
                score.add(teamScore);
            }
            
            scores.add(EvaluationHelper.mean(score));
        }        
        
        standardDeviation = EvaluationHelper.sd(scores);
        
        return standardDeviation;
    }
    
    /**
     * Get the number of unique response from a list of responses
     * @param responseList
     * @return 
     */
    private int getSpecificResponseCount (List<MyResponse> responseList)
    {
        HashMap map = new HashMap();
        
        for (MyResponse response: responseList){
            String responseStr = response.getAnswer().toString();
            
            //Ignore Blank answer
            if(responseStr != null && responseStr.length() != 0)
            {
                if (map.containsKey(responseStr)){
                    int count = (Integer) map.get(responseStr);
                    map.put(responseStr, count + 1);
                }
                else
                {
                    map.put(responseStr, 1);
                }
            }
        }
           
        return map.size();
    }
    
    /**
     * Build a response list base on a given team and the question number
     * @param team
     * @param question
     * @return 
     */
    private List<MyResponse> buildResponseListPerQuestion(MyTeam team, int question) {
        List<MyResponse> responsesList = new LinkedList<MyResponse>();        

        for (int i = 0; i < team.studentList.size(); i++) {
            MyResponse response = new MyResponse();
            final MyResponse studentResponse = team.studentList.get(i).getResponseList().get(question);
            response.setqId(studentResponse.getqId());
            response.setType(studentResponse.getType());
            response.setAnswer(studentResponse.getAnswer());
            responsesList.add(response);
        }

        return responsesList;
    }
    
    
}

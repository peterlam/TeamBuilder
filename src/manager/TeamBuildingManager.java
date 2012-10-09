/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package manager;

import model.MyStudent;
import model.MyResponse;
import builder.RandomTeamsBuilder;
import builder.TeamBuilder;
import evaluation.MultipleChoiceEvaluation;
import evaluation.TeamsEvaluation;
import java.io.File;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import model.MyTeam;
import org.netbeans.j2ee.wsdl.teambuilder.TeamInput;
import output.OutputResult;

/**
 *
 * @author Lam
 */
public class TeamBuildingManager {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {                
        try {
            testParseEntireDocument();
        } catch (JAXBException ex) {
            Logger.getLogger(TeamBuildingManager.class.getName()).log(Level.SEVERE, null, ex);
        }        
    }
    
    public static void testParseEntireDocument() throws JAXBException {
        JAXBContext ctx = JAXBContext.newInstance(new Class[]{TeamInput.class});
        Unmarshaller um = ctx.createUnmarshaller();
        TeamInput teamInput = (TeamInput) um.unmarshal(new File("Sample.xml"));

        System.out.println("Total Students: " + teamInput.getStudent().size());
        //Not sure what is Minority ID
        System.out.println("Minority ID??: " + teamInput.getMinorityId());
        System.out.println("Number of Groups: " + teamInput.getNumGroups());
        //Question information doesn't seem to matter for the team assignment
        System.out.println("Qeustion ID:" + teamInput.getQuestion().get(1).getId() + " Title: "
                + teamInput.getQuestion().get(1).getTitle() + " Type: "
                + teamInput.getQuestion().get(1).getType());
        
        //Get the list of student from the XML parser
        List<MyStudent> studentList = BuildStudentList(teamInput);                
        //TODO: Hook up logic to swap students from team to team
        //Loop a number time and get the best sd as the team result
        List<MyTeam> bestTeamsList = new LinkedList<MyTeam>();
        double bestSd = 1.0;
        int numberOfGroups = Integer.parseInt(teamInput.getNumGroups());
        
        for(int i = 0; i <20; i++)
        {
            //Assign student to random team given team size and student count
            TeamBuilder teamBuilder = new RandomTeamsBuilder();
            List<MyTeam> teamsList = teamBuilder.buildTeams(numberOfGroups, studentList);            
            //MyTeams teamsList = (MyTeams) teamBuilder.buildTeams(teamInput.getNumGroups(), studentList);
            //Calculate sd of the diversity level of the team combination
            TeamsEvaluation teamsEval = new MultipleChoiceEvaluation(); 
            double sd = teamsEval.calculateTeamsStandardDeviation(teamsList);
            
            if (sd < bestSd)
            {
                bestSd = sd;
                bestTeamsList = teamsList;
            }
        }
        
        //TODO: output team formation base on bestTeamsList
        OutputResult output = new OutputResult();
        output.printResult(bestTeamsList);
    }

    //create a list of students with associated responses
    private static List<MyStudent> BuildStudentList(TeamInput teamInput) {
        List<MyStudent> studentList = new LinkedList<MyStudent>();
        for (int i = 0; i < teamInput.getStudent().size(); i++) {
            MyStudent studentObj = new MyStudent();
            studentObj.setIndex(i);
            List<MyResponse> respList = new LinkedList<MyResponse>();
            studentObj.setUsername(teamInput.getStudent().get(i).getUsername());

            //System.out.println("Student[" + i + "] Username: " + teamInput.getStudent().get(i).getUsername());

            for (int j = 0; j < teamInput.getStudent().get(i).getResponse().size(); j++) {
                MyResponse resp = new MyResponse();
                resp.setAnswer(teamInput.getStudent().get(i).getResponse().get(j).getValue().get(0).getId());
                resp.setType(teamInput.getStudent().get(i).getResponse().get(j).getType());
                resp.setqId(teamInput.getStudent().get(i).getResponse().get(j).getQId());
                respList.add(resp);

                //System.out.println("Student[" + i + "] Response[" + j + "] ID: " + teamInput.getStudent().get(i).getResponse().get(j).getQId());
                //System.out.println("Student[" + i + "] Response[" + j + "] Type: " + teamInput.getStudent().get(i).getResponse().get(j).getType());
                //System.out.println("Student[" + i + "] Response[" + j + "] Type: " + teamInput.getStudent().get(i).getResponse().get(j).getValue().get(0).getId());
            }

            studentObj.setResponseList(respList);
            studentList.add(studentObj);
        }
        return studentList;
    }
}

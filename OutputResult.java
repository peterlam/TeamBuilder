/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package output;

import java.util.List;
import model.MyResponse;
import model.MyStudent;
import model.MyTeam;

/**
 *
 * @author Lam
 */
public class OutputResult {

    public void printResult(List<MyTeam> teams) {
        
        System.out.println("Begin Result");
        
        for (MyTeam team : teams) 
        {
            System.out.println( "---- Team ----");
            System.out.println("Team Score: " + team.getTeamScore());
            for (MyStudent student : team.studentList)
            {
                System.out.println("UserName: " + student.getUsername());
                List<MyResponse> list = student.getResponseList();
                for (int i=0; i<list.size(); i++)
                {
                    System.out.println("Question(" + i + "): " + list.get(i).getAnswer());
                }                
            }
        }
        
        System.out.println("End Result");
    }
}

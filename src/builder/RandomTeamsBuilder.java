/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package builder;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import model.MyStudent;
import model.MyTeam;
/**
 *
 * @author Lam
 */
public class RandomTeamsBuilder extends CommonTeamBuilder{
    
    //Return a randomized team combination
    @Override
    public List<MyTeam> buildTeams(int numberOfTeams, List<MyStudent> studentList) {
        
        int evenTeamSize = studentList.size()/numberOfTeams;
        
        List<MyTeam> teamList = new LinkedList<MyTeam>();

        for (int i=0; i<numberOfTeams; i++)
        {
            List<MyStudent> tempStudentList = new LinkedList<MyStudent>();
            MyTeam team = new MyTeam(tempStudentList, evenTeamSize, i); //+1 in case of left over student
            teamList.add(team);
        }                
        
        int studentCount = studentList.size();

        Random random = new Random();
        int randomNumber = CreateRandomIntegerWithinRange(studentCount + 1, 10000, random);
        
        //TODO: convert to for each
        for (int i = 0; i < studentCount; i++) {
            int assignTeamNum = randomNumber % numberOfTeams;
            while (!IsTeamOpen(evenTeamSize, teamList.get(assignTeamNum))) {
                randomNumber = CreateRandomIntegerWithinRange(studentCount + 1, 10000, random);
                assignTeamNum = randomNumber % numberOfTeams;
            }
            //add student to team      
            teamList.get(assignTeamNum).studentList.add(studentList.get(i));

            if (AreAllTeamsFilledEvenly(evenTeamSize, teamList)) {
                //TODO: assign the leftover students to a different team
                //assignTeamForRemainingStudents(i, numberOfTeams, studentList, teamList);
                for (int j = i+1; j < studentCount; j++) {
                    assignTeamNum = randomNumber % numberOfTeams;
                    while (!(teamList.get(assignTeamNum).studentList.size() < (evenTeamSize + 1))) {
                        randomNumber = CreateRandomIntegerWithinRange(studentCount + 1, 10000, random);
                        assignTeamNum = randomNumber % numberOfTeams;
                    }
                    //add remainder student to team
                    teamList.get(assignTeamNum).setTeamSize(teamList.get(assignTeamNum).getTeamSize() + 1);
                    teamList.get(assignTeamNum).studentList.add(studentList.get(j));
                }

                break;
            }
        }
        
        return teamList;
    }
    
    public static List<MyTeam> swapMember (List<MyTeam> teamList, int teamOneID, int teamTwoID, MyStudent teamOneStudent, MyStudent teamTwoStudent)
    {
        //TODO: put in method detail
        return teamList;
    }
    
    /**
     * Return if a team is still open and not full
     * @param teamSize
     * @param team
     * @return 
     */
    private static boolean IsTeamOpen (int teamSize, MyTeam team)
    {                
        return (team.studentList.size() < teamSize);
      
    }
    
    /**
     * Return if all teams in the given teamList is all filled up evenly
     * @param teamSize
     * @param teamList
     * @return 
     */
    private static boolean AreAllTeamsFilledEvenly (int teamSize, List<MyTeam> teamList)
    {
        for(MyTeam team : teamList){
            if(team.studentList.size() < teamSize)                
            {
                return false;
            }
        }        
        return true;
    }
    
    /**
     * Return a random integer between a range
     * @param start
     * @param end
     * @param random
     * @return 
     */
    private static int CreateRandomIntegerWithinRange(int start, int end, Random random) {
        if (start > end) {
            throw new IllegalArgumentException("Start cannot exceed End.");
        }
        //get the range, casting to long to avoid overflow problems
        long range = (long) end - (long) start + 1;
        // compute a fraction of the range, 0 <= frac < range
        long fraction = (long) (range * random.nextDouble());
        return (int) (fraction + start);
    }

    private void assignTeamForRemainingStudents(int i, int numberOfTeams, List<MyStudent> studentList, List<MyTeam> teamList) {
        int studentCount = studentList.size();
        int evenTeamSize = studentList.size()/numberOfTeams;
        Random random = new Random();
        int randomNumber = CreateRandomIntegerWithinRange(studentCount + 1, 10000, random);
        
        for (int j=i; j<studentCount; j++)
        {
            int assignTeamNum = randomNumber % numberOfTeams;                    
            while (!(teamList.get(assignTeamNum).studentList.size() < (evenTeamSize + 1)))
            {                
                randomNumber = CreateRandomIntegerWithinRange(studentCount + 1, 10000, random);
                assignTeamNum = randomNumber % numberOfTeams;
            }
            //add remainder student to team
            teamList.get(assignTeamNum).setTeamSize(teamList.get(assignTeamNum).getTeamSize() + 1);
            teamList.get(assignTeamNum).studentList.add(studentList.get(j));
        }
    }
    
}

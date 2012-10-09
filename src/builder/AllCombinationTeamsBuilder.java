/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package builder;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import model.MyStudent;
import model.MyTeam;

/**
 *
 * @author Lam
 */
public class AllCombinationTeamsBuilder extends CommonTeamBuilder{

//    private static final int numParticipants = 6;
//    private static final int numGroups = 2;
//    private static final int maxParticipantsPerGroup = 3;
//    private static ArrayList<ArrayList<ArrayList<Integer>>> participantGroups = new ArrayList<ArrayList<ArrayList<Integer>>>();

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
        
        return teamList;
    }
//    private static void generateParticipantGroupings() {
//        ArrayList<ArrayList<Integer>> groupings = new ArrayList<ArrayList<Integer>>();
//        for (int i = 0; i < numGroups; i++) {
//            groupings.add(new ArrayList<Integer>());
//        }
//
//        buildParticipantGrouping(1, groupings);
//    }
//
//    private static void buildParticipantGrouping(int currentParticipant, ArrayList<ArrayList<Integer>> grouping) {
//        if (currentParticipant > numParticipants) {
//            boolean match = false;
//            for (int j = 0; j < participantGroups.size(); j++) {
//                int numMatches = 0;
//                for (int k = 0; k < numGroups; k++) {
//                    for (int m = 0; m < numGroups; m++) {
//                        if (grouping.get(k).equals(participantGroups.get(j).get(m))) {
//                            numMatches++;
//                            break;
//                        }
//                    }
//
//                }
//
//                if (numMatches >= numGroups) {
//                    match = true;
//                    break;
//                }
//            }
//
//            if (!match) {
//                participantGroups.add(grouping);
//            }
//        } else {
//            for (int i = 0; i < numGroups; i++) {
//                if (grouping.get(i).size() < maxParticipantsPerGroup) {
//                    ArrayList<ArrayList<Integer>> tempGroupings = new ArrayList<ArrayList<Integer>>();
//                    for (int a = 0; a < numGroups; a++) {
//                        tempGroupings.add(new ArrayList<Integer>());
//
//                        for (Integer x : grouping.get(a)) {
//                            tempGroupings.get(a).add(x);
//                        }
//                    }
//
//                    tempGroupings.get(i).add(currentParticipant);
//
//                    buildParticipantGrouping(currentParticipant + 1, tempGroupings);
//                }
//            }
//        }
//    }
//    
//    private List<String> charCombinations(List<Character> chars) {
//        if (chars.isEmpty()) {
//            List<String> result = new ArrayList<String>();
//            result.add("");
//            return result;
//        } else {
//            Character c = chars.remove(0);
//            List<String> result = charCombinations(chars);
//            int size = result.size();
//            for (int i = 0; i < size; i++) {
//                result.add(c + result.get(i));
//            }
//            return result;
//        }
//    }

}

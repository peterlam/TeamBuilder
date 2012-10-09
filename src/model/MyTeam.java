/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.List;

/**
 *
 * @author Lam
 */
public class MyTeam{
    public List<MyStudent> studentList;
    private int teamSize;
    protected int teamNumber;
    protected double evaluationScore;
    
    //Constructor
    public MyTeam(List<MyStudent> theStudentList, int size, int teamId)
    {
        studentList = theStudentList;
        teamSize = size;
        teamNumber = teamId;
        evaluationScore = 0.0;       
    }
    
    public void setTeamScore(double score)
    {
        //TODO: call some fitness algorithm to get a score 
        //using the student list
        this.evaluationScore = score;
    }
    
    public double getTeamScore()
    {
        return evaluationScore;
    }

    /**
     * @return the teamSize
     */
    public int getTeamSize() {
        return teamSize;
    }

    /**
     * @param teamSize the teamSize to set
     */
    public void setTeamSize(int teamSize) {
        this.teamSize = teamSize;
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Lam
 */
public class MyTeams {
    //TODO: Add the team list here
    private List<MyTeam> teamsList;
    private double standardDeviation;
    
    public MyTeams(){
        teamsList = new LinkedList<MyTeam>();        
    }

    /**
     * @return the teamsList
     */
    public List<MyTeam> getTeamsList() {
        return teamsList;
    }

    /**
     * @param teamsList the teamsList to set
     */
    public void setTeamsList(List<MyTeam> teamsList) {
        this.teamsList = teamsList;
    }

    /**
     * @return the standardDeviation
     */
    public double getStandardDeviation() {
        return standardDeviation;
    }

    /**
     * @param standardDeviation the standardDeviation to set
     */
    public void setStandardDeviation(double standardDeviation) {
        this.standardDeviation = standardDeviation;
    }
}

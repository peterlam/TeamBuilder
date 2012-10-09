/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package evaluation;

import java.util.List;
import model.MyTeam;

/**
 *
 * @author Lam
 */
public interface TeamsEvaluation {
    public double calculateTeamsStandardDeviation(List<MyTeam> teamsList);
}

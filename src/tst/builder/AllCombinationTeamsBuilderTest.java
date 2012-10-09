/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tst.builder;

import model.MyTeam;
import junit.framework.Assert;
import builder.AllCombinationTeamsBuilder;
import java.util.LinkedList;
import model.MyStudent;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Lam
 */
public class AllCombinationTeamsBuilderTest {
    
    private TestableAllCombinationTeamsBuilder _testableAllCombinationTeamsBuilder 
            = new TestableAllCombinationTeamsBuilder();   

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }
    
    @Before
    public void setUp() {
        _testableAllCombinationTeamsBuilder = new TestableAllCombinationTeamsBuilder();
    }
    
    @After
    public void tearDown() {
        _testableAllCombinationTeamsBuilder = null;
    }
    
    @Test
    public void buildTeams_TestTeamsListFilled(){
        //Test that teamsList contains teams
        final int numOfTeams = 2;
        
        List<MyStudent> studentList = createStudentList (6);
        //TODO: it is really a list of teamsList, so the check should be 15 combination of teamslist
        List<MyTeam> teamsList = _testableAllCombinationTeamsBuilder.buildTeams(numOfTeams, studentList);
        Assert.assertFalse(teamsList.isEmpty());
        Assert.assertEquals(numOfTeams, teamsList.size());
    }
    
    /**
     * Create a list of students base on given size
     * @param numberOfStudent
     * @return 
     */
    private List<MyStudent> createStudentList(int numberOfStudent) {
        List<MyStudent> studentList = new LinkedList<MyStudent>();

        for (int i = 1; i <= numberOfStudent; i++) {
            MyStudent student = new MyStudent();
            student.setIndex(i);
            student.setUsername("user" + i);
            studentList.add(student);
        }

        return studentList;
    }
    
    public class TestableAllCombinationTeamsBuilder extends AllCombinationTeamsBuilder
    {      
        
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Lam
 */
public class MyResponse {

    protected String qId;
    protected String type;
    protected String answer;

    public MyResponse()
    {
        
    }
    
    public MyResponse(String id, String type, String theAnswer )
    {
        qId = id;
        this.type = type;
        answer = theAnswer;
    }
    public String getqId() {
        return qId;
    }

    public void setqId(String value) {
        this.qId = value;
    }

    public String getType() {
        return type;
    }

    public void setType(String value) {
        this.type = value;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String value) {
        this.answer = value;
    }    
}

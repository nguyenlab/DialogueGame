/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab.dialogues.entities.tuples;

import java.util.List;

/**
 *
 * @author s1420211
 */
public class Tuples {
    
    private String dial_id;
    private int turnNum;
    private Tuple[]userTuples;
    private Tuple[]systemTuples;

    public int getTurnNum() {
        return turnNum;
    }

    public void setTurnNum(int turnNum) {
        this.turnNum = turnNum;
    }

    public Tuple[] getUserTuples() {
        return userTuples;
    }

    public void setUserTuples(Tuple[] userTuples) {
        this.userTuples = userTuples;
    }

    public Tuple[] getSystemTuples() {
        return systemTuples;
    }

    public void setSystemTuples(Tuple[] systemTuples) {
        this.systemTuples = systemTuples;
    }

    public String getDial_id() {
        return dial_id;
    }

    public void setDial_id(String dial_id) {
        this.dial_id = dial_id;
    }
    
    public void setTuples(Tuples tuples){
        this.dial_id=tuples.getDial_id();
        this.turnNum=tuples.getTurnNum();
        this.systemTuples=tuples.getSystemTuples();
        this.userTuples=tuples.getUserTuples();
    }

    
}

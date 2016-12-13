/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab.dialogues.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

/**
 *
 * @author Long
 */
//@XmlAccessorType(XmlAccessType.FIELD)
public class Turn implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * id
     */
//    @XmlAttribute(name = "TURN_ID")
    @JsonProperty(value = "turn")
    private int turn;

//    @XmlElement(name = "SYS")
    @JsonProperty(value="Sys")
    private Party system;

//    @XmlElement(name = "USR")
    @JsonProperty(value="Usr")
    private Party user;

    public int getTurn() {
        return turn;
    }

    public void setTurn(int turn) {
        this.turn = turn;
    }

    public Party getSystem() {
        return system;
    }

    public void setSystem(Party system) {
        this.system = system;
    }

    public Party getUser() {
        return user;
    }

    public void setUser(Party user) {
        this.user = user;
    }

   

}

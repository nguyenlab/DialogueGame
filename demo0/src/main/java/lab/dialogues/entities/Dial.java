/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab.dialogues.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlValue;

/**
 *
 * @author Long
 */
//@XmlAccessorType(XmlAccessType.FIELD)
public class Dial implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * id
     */
//    @XmlAttribute(name = "id")
//    private String id;
    @JsonProperty(value="id")
    private String id;

//    @XmlElement(name = "dial")
    @JsonProperty(value = "dial")
    List<Turn> turns;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Turn> getTurns() {
        return turns;
    }

    public void setTurns(List<Turn> turns) {
        this.turns = turns;
    }

}

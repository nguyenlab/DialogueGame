/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab.dialogues.entities;

import java.io.Serializable;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Long
 */
@XmlRootElement(name = "DIALOGUES")
@XmlAccessorType(XmlAccessType.FIELD)
public class Dialogues implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * pairs
     */
//    @XmlAttribute(name = "VERSION")
//    private String version;
    
    
    
    @XmlElement(name = "dial")
    private List<Dial>dials;

//    public String getVersion() {
//        return version;
//    }
//
//    public void setVersion(String version) {
//        this.version = version;
//    }

    public List<Dial> getDials() {
        return dials;
    }

    public void setDials(List<Dial> dials) {
        this.dials = dials;
    }

    
    
    
    
}

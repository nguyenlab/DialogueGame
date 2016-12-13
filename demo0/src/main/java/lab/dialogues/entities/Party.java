/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab.dialogues.entities;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlValue;

/**
 *
 * @author Long
 */
//@XmlAccessorType(XmlAccessType.FIELD)
public class Party implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * id
     */
    
    /**
     
     * content
     */
    
    private String hyp;
    private String ref;
    private String base;
    private String dact;

    public String getHyp() {
        return hyp;
    }

    public void setHyp(String hyp) {
        this.hyp = hyp;
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public String getDact() {
        return dact;
    }

    public void setDact(String dact) {
        this.dact = dact;
    }
    
    
}
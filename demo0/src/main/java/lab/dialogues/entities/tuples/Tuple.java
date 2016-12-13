/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab.dialogues.entities.tuples;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author s1420211
 */
public class Tuple {

    private String actor;
    private String content;
    private String act;
    private List<KeyValue>keyvalueList;

    public String getActor() {
        return actor;
    }

    public void setActor(String actor) {
        this.actor = actor;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAct() {
        return act;
    }

    public void setAct(String act) {
        this.act = act;
    }

    public List<KeyValue> getKeyvalueList() {
        return keyvalueList;
    }

    public void setKeyvalueList(List<KeyValue> keyvalueList) {
        this.keyvalueList = keyvalueList;
    }
    
    public void setKeyvalueList(String key,String value) {
        KeyValue keyvalue = new KeyValue();
        keyvalue.setKey(key);
        keyvalue.setValue(value);
        List<KeyValue> keyvalueList=new ArrayList<>();
        keyvalueList.add(keyvalue);
        this.keyvalueList=keyvalueList;
    }
    
}

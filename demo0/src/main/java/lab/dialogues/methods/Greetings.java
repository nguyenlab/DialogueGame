/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab.dialogues.methods;

import java.util.ArrayList;
import java.util.List;
import lab.dialogues.entities.tuples.Dials;
import lab.dialogues.entities.tuples.KeyValue;
import lab.dialogues.entities.tuples.Tuple;
import lab.dialogues.entities.tuples.Tuples;

/**
 *
 * @author s1420211
 */
public class Greetings {

    public static Dials greetings(Dials inDials, String rulesFile) {

        Dials outDials = new Dials();

        List<Tuples> tuplesList = new ArrayList<>();

        for (Tuples inTuples : inDials.getDials()) {

            String rule = greetings_random_rule(rulesFile);

            Tuples outTuples = inTuples;
            int turnNum = inTuples.getTurnNum() + 1;
            outTuples.setTurnNum(turnNum);

            Tuple[] systemTuples = new Tuple[turnNum];
            Tuple[] userTuples = new Tuple[turnNum];

            String[] rules = rule.split("[|]{3}");

//            System.out.println(rule);
//            System.out.println(rules[0]);
//            System.out.println(rules[1]);
            Tuple systemTuple = new Tuple();
            systemTuple.setContent(rules[0].trim());
//            KeyValue keyvalue = new KeyValue();
//            keyvalue.setKey("key-greeting");
//            keyvalue.setValue("value-greeting");
//            List<KeyValue>keyvalueList=new ArrayList<>();
            systemTuple.setKeyvalueList("key-greeting", "value-greeting");
            Tuple userTuple = new Tuple();
            userTuple.setContent(rules[1].trim());
            systemTuples[0] = systemTuple;
            userTuples[0] = userTuple;

//        systemTuples[0].setContent(rules[0]);
//        userTuples[0].setContent(rules[1]);
            for (int i = 0; i < turnNum - 1; i++) {

                systemTuple = new Tuple();
                systemTuple.setContent(inTuples.getSystemTuples()[i].getContent());
                systemTuple.setKeyvalueList(inTuples.getSystemTuples()[i].getKeyvalueList());
                userTuple = new Tuple();
                userTuple.setContent(inTuples.getUserTuples()[i].getContent());
                systemTuples[i + 1] = systemTuple;
                userTuples[i + 1] = userTuple;

//            systemTuples[i + 1].setContent(inTuples.getSystemTuples()[i].getContent());
//            userTuples[i + 1].setContent(inTuples.getUserTuples()[i].getContent());
            }

            systemTuple = new Tuple();
            systemTuple.setContent("ok, now you can ask for restaurant -s by area , price range or food type . how may i help you ?");
            systemTuple.setKeyvalueList("key-greeting", "value-greeting");

            systemTuples[1] = systemTuple;

            outTuples.setSystemTuples(systemTuples);
            outTuples.setUserTuples(userTuples);

            tuplesList.add(outTuples);

        }

        outDials.setDials(tuplesList);

        return outDials;

    }

    public static Tuples greetings(Tuples inTuples, String rulesFile) {

        Tuples outTuples = inTuples;
        int turnNum = inTuples.getTurnNum() + 1;
        outTuples.setTurnNum(turnNum);
        outTuples.setDial_id(inTuples.getDial_id());

        Tuple[] systemTuples = new Tuple[turnNum];
        Tuple[] userTuples = new Tuple[turnNum];

        String rule = greetings_random_rule(rulesFile);
        
        String[] rules = rule.split("[|]{3}");

//            System.out.println(rule);
//            System.out.println(rules[0]);
//            System.out.println(rules[1]);
        Tuple systemTuple = new Tuple();
        systemTuple.setContent(rules[0].trim());
        systemTuple.setKeyvalueList("key-greeting", "value-greeting");

        Tuple userTuple = new Tuple();
        userTuple.setContent(rules[1].trim());
        systemTuples[0] = systemTuple;
        userTuples[0] = userTuple;

//        systemTuples[0].setContent(rules[0]);
//        userTuples[0].setContent(rules[1]);
        for (int i = 0; i < turnNum - 1; i++) {

            systemTuple = new Tuple();
            systemTuple.setContent(inTuples.getSystemTuples()[i].getContent());
            systemTuple.setKeyvalueList(inTuples.getSystemTuples()[i].getKeyvalueList());

            userTuple = new Tuple();
            userTuple.setContent(inTuples.getUserTuples()[i].getContent());
            systemTuples[i + 1] = systemTuple;
            userTuples[i + 1] = userTuple;

//            systemTuples[i + 1].setContent(inTuples.getSystemTuples()[i].getContent());
//            userTuples[i + 1].setContent(inTuples.getUserTuples()[i].getContent());
        }

        systemTuple = new Tuple();
        systemTuple.setContent("ok, now you can ask for restaurant -s by area , price range or food type . how may i help you ?");
        systemTuple.setKeyvalueList("key-greeting", "value-greeting");

        systemTuples[1] = systemTuple;

        outTuples.setSystemTuples(systemTuples);
        outTuples.setUserTuples(userTuples);

        return outTuples;

    }

    public static Tuples greetings2(Tuples inTuples, String rule) {

        Tuples outTuples = inTuples;
        int turnNum = inTuples.getTurnNum() + 1;
        outTuples.setTurnNum(turnNum);

        Tuple[] systemTuples = new Tuple[turnNum];
        Tuple[] userTuples = new Tuple[turnNum];

        String[] rules = rule.split("[|]{3}");

//            System.out.println(rule);
//            System.out.println(rules[0]);
//            System.out.println(rules[1]);
        Tuple systemTuple = new Tuple();
        systemTuple.setContent(rules[0].trim());
        Tuple userTuple = new Tuple();
        userTuple.setContent(rules[1].trim());
        systemTuples[0] = systemTuple;
        userTuples[0] = userTuple;

//        systemTuples[0].setContent(rules[0]);
//        userTuples[0].setContent(rules[1]);
        for (int i = 0; i < turnNum - 1; i++) {

            systemTuple = new Tuple();
            systemTuple.setContent(inTuples.getSystemTuples()[i].getContent());
            userTuple = new Tuple();
            userTuple.setContent(inTuples.getUserTuples()[i].getContent());
            systemTuples[i + 1] = systemTuple;
            userTuples[i + 1] = userTuple;

//            systemTuples[i + 1].setContent(inTuples.getSystemTuples()[i].getContent());
//            userTuples[i + 1].setContent(inTuples.getUserTuples()[i].getContent());
        }

        systemTuple = new Tuple();
        systemTuple.setContent("ok, now you can ask for restaurant -s by area , price range or food type . how may i help you ?");
        systemTuples[1] = systemTuple;

        outTuples.setSystemTuples(systemTuples);
        outTuples.setUserTuples(userTuples);

        return outTuples;

    }

    public static Tuple greetings(Tuple inTuple, String rule) {

        Tuple outTuple = new Tuple();

        outTuple.setContent(rule);

        return outTuple;

    }

    public static String greetings_random_rule(String rulesFile) {

        List<String> rulesList = lab.dialogues.io.Rules.readRules(rulesFile, "random_greeting_rules", "end_random_greeting_rules");
        String rule = lab.dialogues.io.Rules.randomRule(rulesList);
//        System.out.println(rule);

        return rule;
    }

}

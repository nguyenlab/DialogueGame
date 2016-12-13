/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab.dialogues.methods;

import java.util.ArrayList;
import java.util.List;
import lab.dialogues.entities.tuples.Dials;
import lab.dialogues.entities.tuples.Tuple;
import lab.dialogues.entities.tuples.Tuples;

/**
 *
 * @author s1420211
 */
public class PersonalTopics {

    public static Dials personalTopics(Dials inDials, String rulesFile) {

        Dials outDials = new Dials();
        List<Tuples> tuplesList = new ArrayList<>();

        for (Tuples inTuples : inDials.getDials()) {

            String topic = lab.dialogues.methods.PersonalTopics.topic_random_rule(rulesFile);
            List<String> rulesList = lab.dialogues.methods.PersonalTopics.getTopicRulesList(rulesFile, topic);

            Tuples outTuples = new Tuples();

            int turnNum = inTuples.getTurnNum() + rulesList.size();
            outTuples.setTurnNum(turnNum);

            Tuple[] systemTuples = new Tuple[turnNum];
            Tuple[] userTuples = new Tuple[turnNum];

            int j = 0;
            for (String rule : rulesList) {

                String[] rules = rule.split("[|]{3}");

//                System.out.println("RULE: " + rule);
//                System.out.println(rules[0]);
//                System.out.println(rules[1]);
                Tuple systemTuple = new Tuple();
                systemTuple.setContent(rules[0].trim());
                systemTuple.setKeyvalueList("key-topic", "value-topic");
                Tuple userTuple = new Tuple();
                userTuple.setContent(rules[1].trim());
                systemTuples[j] = systemTuple;
                userTuples[j] = userTuple;
                j++;
            }

//        systemTuples[0].setContent(rules[0]);
//        userTuples[0].setContent(rules[1]);
//        int rulesSize = rulesList.size();
//            System.out.println(j);
            for (int i = 0; i < inTuples.getTurnNum(); i++) {

                Tuple systemTuple = new Tuple();
                systemTuple.setContent(inTuples.getSystemTuples()[i].getContent());
                systemTuple.setKeyvalueList(inTuples.getSystemTuples()[i].getKeyvalueList());
                Tuple userTuple = new Tuple();
                userTuple.setContent(inTuples.getUserTuples()[i].getContent());
                systemTuples[j + i] = systemTuple;
                userTuples[j + i] = userTuple;

//            systemTuples[i + 1].setContent(inTuples.getSystemTuples()[i].getContent());
//            userTuples[i + 1].setContent(inTuples.getUserTuples()[i].getContent());
            }

            Tuple systemTuple = new Tuple();
            systemTuple.setContent("ok, now you can ask for restaurant -s by area , price range or food type . how may i help you ?");
            systemTuple.setKeyvalueList("key-topic", "value-topic");
            systemTuples[j] = systemTuple;

            outTuples.setSystemTuples(systemTuples);
            outTuples.setUserTuples(userTuples);
            tuplesList.add(outTuples);
        }
        outDials.setDials(tuplesList);
        return outDials;

    }

    public static Tuples personalTopics(Tuples inTuples, String rulesFile) {

        String topic = lab.dialogues.methods.PersonalTopics.topic_random_rule(rulesFile);
        List<String> rulesList = lab.dialogues.methods.PersonalTopics.getTopicRulesList(rulesFile, topic);

        Tuples outTuples = new Tuples();

        int turnNum = inTuples.getTurnNum() + rulesList.size();
        outTuples.setTurnNum(turnNum);
        outTuples.setDial_id(inTuples.getDial_id());
        

        Tuple[] systemTuples = new Tuple[turnNum];
        Tuple[] userTuples = new Tuple[turnNum];

        int j = 0;
        for (String rule : rulesList) {

            String[] rules = rule.split("[|]{3}");

//            System.out.println("RULE: " + rule);
//            System.out.println(rules[0]);
//            System.out.println(rules[1]);
            Tuple systemTuple = new Tuple();
            systemTuple.setContent(rules[0].trim());
            systemTuple.setKeyvalueList("key-topic", "value-topic");

            Tuple userTuple = new Tuple();
            userTuple.setContent(rules[1].trim());
            systemTuples[j] = systemTuple;
            userTuples[j] = userTuple;
            j++;
        }

//        systemTuples[0].setContent(rules[0]);
//        userTuples[0].setContent(rules[1]);
//        int rulesSize = rulesList.size();
//        System.out.println(j);
        for (int i = 0; i < inTuples.getTurnNum(); i++) {

            Tuple systemTuple = new Tuple();
            systemTuple.setContent(inTuples.getSystemTuples()[i].getContent());
            systemTuple.setKeyvalueList(inTuples.getSystemTuples()[i].getKeyvalueList());

            Tuple userTuple = new Tuple();
            userTuple.setContent(inTuples.getUserTuples()[i].getContent());
            systemTuples[j + i] = systemTuple;
            userTuples[j + i] = userTuple;

//            systemTuples[i + 1].setContent(inTuples.getSystemTuples()[i].getContent());
//            userTuples[i + 1].setContent(inTuples.getUserTuples()[i].getContent());
        }

        Tuple systemTuple = new Tuple();
        systemTuple.setContent("ok, now you can ask for restaurant -s by area , price range or food type . how may i help you ?");
        systemTuple.setKeyvalueList("key-topic", "value-topic");

        systemTuples[j] = systemTuple;

        outTuples.setSystemTuples(systemTuples);
        outTuples.setUserTuples(userTuples);

        return outTuples;

    }

    public static Tuples personalTopics(Tuples inTuples, List<String> rulesList) {

        Tuples outTuples = new Tuples();

        int turnNum = inTuples.getTurnNum() + rulesList.size();
        outTuples.setTurnNum(turnNum);

        Tuple[] systemTuples = new Tuple[turnNum];
        Tuple[] userTuples = new Tuple[turnNum];

        int j = 0;
        for (String rule : rulesList) {

            String[] rules = rule.split("[|]{3}");

            System.out.println("RULE: " + rule);
            System.out.println(rules[0]);
            System.out.println(rules[1]);
            Tuple systemTuple = new Tuple();
            systemTuple.setContent(rules[0].trim());
            Tuple userTuple = new Tuple();
            userTuple.setContent(rules[1].trim());
            systemTuples[j] = systemTuple;
            userTuples[j] = userTuple;
            j++;
        }

//        systemTuples[0].setContent(rules[0]);
//        userTuples[0].setContent(rules[1]);
//        int rulesSize = rulesList.size();
        System.out.println(j);

        for (int i = 0; i < inTuples.getTurnNum(); i++) {

            Tuple systemTuple = new Tuple();
            systemTuple.setContent(inTuples.getSystemTuples()[i].getContent());
            Tuple userTuple = new Tuple();
            userTuple.setContent(inTuples.getUserTuples()[i].getContent());
            systemTuples[j + i] = systemTuple;
            userTuples[j + i] = userTuple;

//            systemTuples[i + 1].setContent(inTuples.getSystemTuples()[i].getContent());
//            userTuples[i + 1].setContent(inTuples.getUserTuples()[i].getContent());
        }

        Tuple systemTuple = new Tuple();
        systemTuple.setContent("ok, now you can ask for restaurant -s by area , price range or food type . how may i help you ?");
        systemTuples[j] = systemTuple;

        outTuples.setSystemTuples(systemTuples);
        outTuples.setUserTuples(userTuples);

        return outTuples;

    }

    public static List<String> getTopicRulesList(String rulesFile, String topic) {

        List<String> rulesList = new ArrayList<>();

        if (topic.startsWith("teacher")) {

            rulesList = lab.dialogues.io.Rules.readRules(rulesFile, "teacher_rules", "end_teacher_rules");

        } else if (topic.startsWith("medicine")) {
            rulesList = lab.dialogues.io.Rules.readRules(rulesFile, "medicine_rules", "end_medicine_rules");

        }

        return rulesList;

    }

    public static String topic_random_rule(String rulesFile) {

        List<String> rulesList = lab.dialogues.io.Rules.readRules(rulesFile, "occupation_rules", "end_occupation_rules");
        String rule = lab.dialogues.io.Rules.randomRule(rulesList);
        return rule;
    }

}

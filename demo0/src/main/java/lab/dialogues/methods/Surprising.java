/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab.dialogues.methods;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import lab.dialogues.entities.Dial;
import lab.dialogues.entities.tuples.Dials;
import lab.dialogues.entities.tuples.KeyValue;
import lab.dialogues.entities.tuples.Tuple;
import lab.dialogues.entities.tuples.Tuples;

/**
 *
 * @author s1420211
 */
public class Surprising {

    public static void surprising(String inFile, String rulesFile, String outFile) {

        List<Dial> dialsList = lab.dialogues.io.KeyWords.getDialList(inFile);
        List<Tuples> tuplesList = new ArrayList<>();

        for (Dial dial : dialsList) {
            Tuples tuples = lab.dialogues.io.KeyWords.getTuples(dial);
//            Tuples feedbackTuples = new Tuples();            
            Tuples surprisingTuples = surprising(tuples, rulesFile);
            //
            tuplesList.add(surprisingTuples);
        }
        Dials outDials = new Dials();
        outDials.setDials(tuplesList);

        lab.dialogues.io.KeyWords.printDials(outDials, outFile);

    }

    public static Tuples surprising(Tuples inTuples, String rulesFile) {

        Tuples outTuples = new Tuples();

        int turnNum = inTuples.getTurnNum();
        String dial_id = inTuples.getDial_id();
        //
        Tuple[] systemTuples = inTuples.getSystemTuples();
        Tuple[] userTuples = inTuples.getUserTuples();

        List<Tuple> systemOutTuplesList = new ArrayList<>();
        List<Tuple> userOutTuplesList = new ArrayList<>();

        KeyValue areaKeyValue = getArea(userTuples);

        if (areaKeyValue.getKey() == null) {
            return inTuples;
        }
        boolean surprised = true;
        for (int i = 0; i < turnNum; i++) {

            Tuple systemTuple = systemTuples[i];
            Tuple userTuple = userTuples[i];
            //

            if ((isArea(userTuple) && surprised)) {

                //add previous
                systemOutTuplesList.add(systemTuple);
                Tuple newUserTuple0 = new Tuple();
                newUserTuple0.setContent("");
                newUserTuple0.setKeyvalueList("", "");
                userOutTuplesList.add(newUserTuple0);

                boolean ok = false;
                int loop = 1;

                List<String> systemRuleList = lab.dialogues.io.Rules.readRules(rulesFile, "surprising_system_rules", "end_surprising_system_rules");
                Set<String> usedRuleSet = new HashSet<>();

                while (!ok && loop <= 3) {
                    loop++;
                    String systemrule = surprising_system_random_rules(rulesFile);

                    while (usedRuleSet.contains(systemrule)) {
                        systemrule = surprising_system_random_rules(rulesFile);
                    }
                    usedRuleSet.add(systemrule);

                    Tuple newSystemTuple = new Tuple();
                    newSystemTuple.setContent(systemrule);
                    newSystemTuple.setKeyvalueList("area-surprising-key", "are-surprising-value");
                    systemOutTuplesList.add(newSystemTuple);

                    String userrule = "";
                    String keyword_system_rule = systemrule.split(" ")[systemrule.split(" ").length - 1];
                    keyword_system_rule = keyword_system_rule.substring(0, keyword_system_rule.length() - 1);
                    if (areaKeyValue.getValue().contains(keyword_system_rule)) {
                        userrule = yes_answer_user_random_rules(rulesFile);
                        ok = true;
                    } else {
                        userrule = no_answer_user_random_rules(rulesFile);
                    }

                    Tuple newUserTuple = new Tuple();
                    newUserTuple.setContent(userrule);
                    newUserTuple.setKeyvalueList("area-surprising-key", "area-surprising-value");
                    userOutTuplesList.add(newUserTuple);

                    surprised = false;
                }
                //empty system
                Tuple newSystemTuple = new Tuple();
                newSystemTuple.setContent("");
                newSystemTuple.setKeyvalueList("area-surprising-key", "are-surprising-value");
                systemOutTuplesList.add(newSystemTuple);

                userOutTuplesList.add(userTuple);

            } else {
                systemOutTuplesList.add(systemTuple);
                userOutTuplesList.add(userTuple);

            }

        }

        outTuples.setDial_id(dial_id);
        int newTurnNum = systemOutTuplesList.size();
        outTuples.setTurnNum(newTurnNum);

        Tuple[] outSystemTuples = new Tuple[newTurnNum];
        Tuple[] outUserTuples = new Tuple[newTurnNum];

        for (int i = 0; i < newTurnNum; i++) {
            outSystemTuples[i] = systemOutTuplesList.get(i);
            outUserTuples[i] = userOutTuplesList.get(i);
        }

        outTuples.setSystemTuples(outSystemTuples);
        outTuples.setUserTuples(outUserTuples);

        return outTuples;
    }

    public static String surprising_system_random_rules(String rulesFile) {

        List<String> rulesList = lab.dialogues.io.Rules.readRules(rulesFile, "surprising_system_rules", "end_surprising_system_rules");
        String rule = lab.dialogues.io.Rules.randomRule(rulesList);
        return rule;
    }

    public static String yes_answer_user_random_rules(String rulesFile) {

        List<String> rulesList = lab.dialogues.io.Rules.readRules(rulesFile, "yes_answer_user_rules", "end_yes_answer_user_rules");
        String rule = lab.dialogues.io.Rules.randomRule(rulesList);
        return rule;
    }

    public static String no_answer_user_random_rules(String rulesFile) {

        List<String> rulesList = lab.dialogues.io.Rules.readRules(rulesFile, "no_answer_user_rules", "end_no_answer_user_rules");
        String rule = lab.dialogues.io.Rules.randomRule(rulesList);
        return rule;
    }

    public static boolean isArea(Tuple tuple) {

        for (KeyValue keyvalue : tuple.getKeyvalueList()) {

            if (keyvalue.getKey() != null && keyvalue.getValue() != null && keyvalue.getKey().contains("area")) {

                return true;

            }

        }

        return false;

    }

    public static KeyValue getArea(Tuple[] userTuples) {

        KeyValue outKeyValue = new KeyValue();

        for (Tuple tuple : userTuples) {

            for (KeyValue keyvalue : tuple.getKeyvalueList()) {

                if (keyvalue.getKey() != null && keyvalue.getValue() != null && keyvalue.getKey().contains("area")) {

                    if (keyvalue.getValue().contains("village") || keyvalue.getValue().contains("town") || keyvalue.getValue().contains("beach") || keyvalue.getValue().contains("center")) {

                        outKeyValue.setKey(keyvalue.getKey());
                        outKeyValue.setValue(keyvalue.getValue());
                    }

                }

            }

        }

        return outKeyValue;

    }

}

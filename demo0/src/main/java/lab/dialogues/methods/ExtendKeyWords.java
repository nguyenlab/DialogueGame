/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab.dialogues.methods;

import java.util.ArrayList;
import java.util.List;
import lab.dialogues.entities.Dial;
import lab.dialogues.entities.tuples.Dials;
import lab.dialogues.entities.tuples.KeyValue;
import lab.dialogues.entities.tuples.Tuple;
import lab.dialogues.entities.tuples.Tuples;

/**
 *
 * @author s1420211
 */
public class ExtendKeyWords {

    public static void readRules(String rulesFile) {
        List<String> nameSystemRulesList = lab.dialogues.io.Rules.readRules(rulesFile, "restaurant_name_system_rules", "end_restaurant_name_system_rules");
        List<String> nameUserRulesList = lab.dialogues.io.Rules.readRules(rulesFile, "restaurant_name_user_rules", "end_restaurant_name_user_rules");
        List<String> otherSystemRulesList = lab.dialogues.io.Rules.readRules(rulesFile, "extend_other_system_rules", "end_extend_other_system_rules");
        List<String> otherUserRulesList = lab.dialogues.io.Rules.readRules(rulesFile, "extend_other_user_rules", "end_extend_other_user_rules");
    }

    public static void extendKeyWords(String inFile, String rulesFile, String outFile) {

        List<Dial> dialsList = lab.dialogues.io.KeyWords.getDialList(inFile);
        List<Tuples> tuplesList = new ArrayList<>();

        for (Dial dial : dialsList) {
            Tuples tuples = lab.dialogues.io.KeyWords.getTuples(dial);
            Tuples extendTuples = new Tuples();
            extendTuples = extendKeyWords(tuples, rulesFile);
            //
            tuplesList.add(extendTuples);
        }
        Dials outDials = new Dials();
        outDials.setDials(tuplesList);

        lab.dialogues.io.KeyWords.printDials(outDials, outFile);

    }

    public static Tuples extendKeyWords(Tuples inTuples, String rulesFile) {

        List<String> nameSystemRulesList = lab.dialogues.io.Rules.readRules(rulesFile, "restaurant_name_system_rules", "end_restaurant_name_system_rules");
        List<String> nameUserRulesList = lab.dialogues.io.Rules.readRules(rulesFile, "restaurant_name_user_rules", "end_restaurant_name_user_rules");
        List<String> otherSystemRulesList = lab.dialogues.io.Rules.readRules(rulesFile, "extend_other_system_rules", "end_extend_other_system_rules");
        List<String> otherUserRulesList = lab.dialogues.io.Rules.readRules(rulesFile, "extend_other_user_rules", "end_extend_other_user_rules");

        Tuples outTuples = new Tuples();

        int turnNum = inTuples.getTurnNum();
        String dial_id = inTuples.getDial_id();
        //
        Tuple[] systemTuples = inTuples.getSystemTuples();
        Tuple[] userTuples = inTuples.getUserTuples();

        List<Tuple> systemOutTuplesList = new ArrayList<>();
        List<Tuple> userOutTuplesList = new ArrayList<>();

        for (int i = 0; i < turnNum; i++) {

            Tuple systemTuple = systemTuples[i];
            Tuple userTuple = userTuples[i];

            if (isExtend(systemTuple)) {

                for (KeyValue keyvalue : systemTuple.getKeyvalueList()) {

//                    String newSystemSpeech = "";
//                    String newUserSpeech = "";
                    if (keyvalue.getKey().startsWith("name")) {
                        //new system
                        String systemrule = lab.dialogues.io.Rules.randomRule(nameSystemRulesList);
                        String newSystemSpeech = generateSpeech(keyvalue.getValue(), systemrule);

                        Tuple newSystemTuple = new Tuple();
                        newSystemTuple.setContent(newSystemSpeech);
                        newSystemTuple.setKeyvalueList(keyvalue.getKey(), keyvalue.getValue());
                        systemOutTuplesList.add(newSystemTuple);

                        //new user
                        String userrule = lab.dialogues.io.Rules.randomRule(nameUserRulesList);
                        String newUserSpeech = generateSpeech(keyvalue.getValue(), userrule);

                        //
                        Tuple newUserTuple = new Tuple();
                        newUserTuple.setContent(newUserSpeech);
                        newUserTuple.setKeyvalueList(keyvalue.getKey(), keyvalue.getValue());
                        userOutTuplesList.add(newUserTuple);
                        //----------
                        

                    } else {
                        
                        String newSystemSpeech2 = "";

                        Tuple newSystemTuple2 = new Tuple();
                        newSystemTuple2.setContent(newSystemSpeech2);
                        newSystemTuple2.setKeyvalueList(keyvalue.getKey(), keyvalue.getValue());
                        systemOutTuplesList.add(newSystemTuple2);

                        String userrule2 = lab.dialogues.io.Rules.randomRule(otherUserRulesList);
                        String newUserSpeech2 = generateSpeech(keyvalue.getKey(), userrule2);

                        Tuple newUserTuple2 = new Tuple();
                        newUserTuple2.setContent(newUserSpeech2);
                        newUserTuple2.setKeyvalueList(keyvalue.getKey(), keyvalue.getValue());
                        userOutTuplesList.add(newUserTuple2);
                        
                        //
                        String systemrule = lab.dialogues.io.Rules.randomRule(otherSystemRulesList);
                        String newSystemSpeech = generateSpeech2(keyvalue.getKey(), keyvalue.getValue(), systemrule);

                        Tuple newSystemTuple = new Tuple();
                        newSystemTuple.setContent(newSystemSpeech);
                        newSystemTuple.setKeyvalueList(keyvalue.getKey(), keyvalue.getValue());
                        systemOutTuplesList.add(newSystemTuple);

                        //
//                        String userrule = lab.dialogues.io.Rules.randomRule(otherUserRulesList);
//                        String newUserSpeech = generateSpeech(keyvalue.getKey(), userrule);
                        String newUserSpeech = "";

                        
                        Tuple newUserTuple = new Tuple();
                    newUserTuple.setContent(newUserSpeech);
                    newUserTuple.setKeyvalueList(keyvalue.getKey(), keyvalue.getValue());
                    userOutTuplesList.add(newUserTuple);
                    }

                }

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

    public static String generateSpeech(String keyword, String rule) {

        return rule.replace("*", keyword);

    }

    public static String generateSpeech2(String keyword1, String keyword2, String rule) {
        String generated_rule = rule.replace("++", keyword1);
        generated_rule = generated_rule.replace("*", keyword2);
        return generated_rule;
    }

    public static boolean isExtend(Tuple inTuple) {
        boolean ok = false;

        List<KeyValue> keyvalueList = inTuple.getKeyvalueList();
        if (keyvalueList.size() > 1) {
            ok = true;
        }

        return ok;
    }

}

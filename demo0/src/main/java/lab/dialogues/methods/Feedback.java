/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab.dialogues.methods;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import lab.dialogues.entities.Dial;
import lab.dialogues.entities.tuples.Dials;
import lab.dialogues.entities.tuples.KeyValue;
import lab.dialogues.entities.tuples.Tuple;
import lab.dialogues.entities.tuples.Tuples;

/**
 *
 * @author s1420211
 */
public class Feedback {

    public static Tuples insertTuples(Tuples inTuples) {

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

            //
            Tuple newSystemTuple = new Tuple();
            newSystemTuple.setContent("");
            newSystemTuple.setKeyvalueList("", "");
            systemOutTuplesList.add(newSystemTuple);
            //
            Tuple newUserTuple = new Tuple();
            newUserTuple.setContent("");
            newUserTuple.setKeyvalueList("", "");
            userOutTuplesList.add(newUserTuple);
            //

            systemOutTuplesList.add(systemTuple);
            userOutTuplesList.add(userTuple);
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

    public static void feedback(String inFile, String rulesFile, String outFile) {

        List<Dial> dialsList = lab.dialogues.io.KeyWords.getDialList(inFile);
        List<Tuples> tuplesList = new ArrayList<>();

        for (Dial dial : dialsList) {
            Tuples tuples = lab.dialogues.io.KeyWords.getTuples(dial);
//            Tuples feedbackTuples = new Tuples();            
            Tuples feedbackTuples = feedback(tuples, rulesFile);
            //
            tuplesList.add(feedbackTuples);
        }
        Dials outDials = new Dials();
        outDials.setDials(tuplesList);

        lab.dialogues.io.KeyWords.printDials(outDials, outFile);

    }

    public static Tuples feedback(Tuples inTuples, String rulesFile) {

        Tuples outTuples = new Tuples();

        int turnNum = inTuples.getTurnNum();
        String dial_id = inTuples.getDial_id();
        //
        Tuple[] systemTuples = inTuples.getSystemTuples();
        Tuple[] userTuples = inTuples.getUserTuples();

        List<Tuple> systemOutTuplesList = new ArrayList<>();
        List<Tuple> userOutTuplesList = new ArrayList<>();
        int feedbackid = -1;
        for (int i = 0; i < turnNum; i++) {

            Tuple systemTuple = systemTuples[i];
            Tuple userTuple = userTuples[i];

            

            if (feedbackid == i) {

                systemOutTuplesList.add(systemTuple);
                //insert here

                //user: great
                //system: new request
                Tuple newUserTuple = new Tuple();
                String userrule=great_random_rules(rulesFile);
                newUserTuple.setContent(userrule);
                newUserTuple.setKeyvalueList("key-price", "value-price");
                userOutTuplesList.add(newUserTuple);

                Tuple newSystemTuple = new Tuple();
                String systemrule=newRequest_random_rules(rulesFile);
                newSystemTuple.setContent(systemrule);
                newSystemTuple.setKeyvalueList("key-price", "value-price");
                systemOutTuplesList.add(newSystemTuple);
                //

                //end insert
                userOutTuplesList.add(userTuple);

            } else {

                String option = isFeedback(userTuple);
//            System.out.println(userTuple.getContent());
//            System.out.println("option="+option);
                if (option.length() > 0) {
//                System.out.println("ok");
                    feedbackid = i + 1;

                    systemOutTuplesList.add(systemTuple);
                    userOutTuplesList.add(userTuple);

                    //
                    String[] rules = feedback_randomRules(rulesFile).split("[|]{3}");
                    String systemrule = rules[0].trim();
                    String userrule = rules[1].trim();

                    List<Integer> priceRangeList = generatePriceRange(option);
                    String pricerange = "";
                    for (int price : priceRangeList) {
                        pricerange += price + ", ";
                    }
                    pricerange += "YEN for each person";
                    String systemspeech = systemrule.replace("*", pricerange);
                    //
                    Random ran = new Random();
                    int ranChoice = ran.nextInt(priceRangeList.size());
                    String userspeech = userrule.replace("*", priceRangeList.get(ranChoice) + " YEN");
                    //
                    Tuple newSystemTuple = new Tuple();
                    newSystemTuple.setContent(systemspeech);
                    newSystemTuple.setKeyvalueList("key-price", "value-price");
                    systemOutTuplesList.add(newSystemTuple);
                    //
                    Tuple newUserTuple = new Tuple();
                    newUserTuple.setContent(userspeech);
                    newUserTuple.setKeyvalueList("key-price", "value-price");
                    userOutTuplesList.add(newUserTuple);
                    //
                } else {
                    systemOutTuplesList.add(systemTuple);
                    userOutTuplesList.add(userTuple);
                }
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
    
    public static String newRequest_random_rules(String rulesFile) {

        List<String> rulesList = lab.dialogues.io.Rules.readRules(rulesFile, "new_requests_system_rules", "end_new_requests_system_rules");
        String rule = lab.dialogues.io.Rules.randomRule(rulesList);
        return rule;
    }
    
    public static String great_random_rules(String rulesFile) {

        List<String> rulesList = lab.dialogues.io.Rules.readRules(rulesFile, "great_rules", "end_great_rules");
        String rule = lab.dialogues.io.Rules.randomRule(rulesList);
        return rule;
    }

    public static String isFeedback(Tuple inTuple) {
        String option = "";
//        System.out.println("checking");

        List<KeyValue> keyvalueList = inTuple.getKeyvalueList();
        if (keyvalueList.size() > 0) {
            for (KeyValue keyvalue : keyvalueList) {
//                System.out.println("KEY:"+keyvalue.getKey());
//                if(keyvalue.getValue()!=null)System.out.println("VALUE:"+keyvalue.getValue());
                if (keyvalue.getKey().startsWith("pricerange")) {
                    if (keyvalue.getValue() != null) {
                        option = keyvalue.getValue();
                    }
                }
            }
        }

        return option;
    }

    public static String feedback_randomRules(String rulesFile) {

        List<String> rulesList = lab.dialogues.io.Rules.readRules(rulesFile, "feedback_price_rules", "end_feedback_price_rules");
        String rule = lab.dialogues.io.Rules.randomRule(rulesList);

        return rule;
    }

    public static List<Integer> generatePriceRange(String option) {

        List<Integer> priceRangeList = new ArrayList<>();

        Random size = new Random();
        int sizeRandom = size.nextInt(3) + 3;

        if (option.matches("cheap")) {

            for (int i = 1; i < sizeRandom; i++) {
                Random add = new Random();
                int addRandom = add.nextInt(500);
                priceRangeList.add(i * 500 + addRandom);
            }

        } else if (option.matches("moderate")) {

            for (int i = 1; i < sizeRandom; i++) {
                Random add = new Random();
                int addRandom = add.nextInt(1000);
                priceRangeList.add(i * 1000 + addRandom);
            }

        } else if (option.matches("expensive")) {

            for (int i = 1; i < sizeRandom; i++) {
                Random add = new Random();
                int addRandom = add.nextInt(5000);
                priceRangeList.add(i * 5000 + addRandom);
            }

        }

        return priceRangeList;

    }

}

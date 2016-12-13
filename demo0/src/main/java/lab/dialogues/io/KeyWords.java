/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab.dialogues.io;

import lab.dialogues.entities.tuples.KeyValue;
import lab.dialogues.entities.tuples.Tuple;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import lab.dialogues.configs.Configs;
import lab.dialogues.entities.Dial;
import lab.dialogues.entities.Turn;
import lab.dialogues.entities.tuples.Dials;
import lab.dialogues.entities.tuples.Tuples;

/**
 *
 * @author s1420211
 */
public class KeyWords {

    public static List<Dial> getDialList(String jsonFile) {
        List<Dial> dials = null;
        try {
            dials = new ObjectMapper().readValue(new File(jsonFile), new TypeReference<List<Dial>>() {
            });
        } catch (IOException ex) {
            Logger.getLogger(KeyWords.class.getName()).log(Level.SEVERE, null, ex);
        }
        return dials;

    }

    public static void keyWordsDials(String jsonFile) {

        List<Dial> dials = getDialList(jsonFile);
        for (Dial dial : dials) {

            Tuples tuples = getTuples(dial);
            printTuples(tuples);

        }

    }

    public static void keyWordsDials(String jsonFile, String outFile) {

        List<Dial> dials = getDialList(jsonFile);
        for (Dial dial : dials) {

            Tuples tuples = getTuples(dial);
            printTuples(tuples, outFile);

        }

    }

    public static Dials getDials(List<Dial> dialList) {

        Dials dials = new Dials();

        List<Tuples> tuplesList = new ArrayList<>();

        for (Dial dial : dialList) {

            List<Turn> turns = dial.getTurns();
            Tuple[] userTupleList = new Tuple[turns.size()];
            Tuple[] systemTupleList = new Tuple[turns.size()];

            Tuples tuples = new Tuples();
            tuples.setTurnNum(turns.size());
            tuples.setSystemTuples(systemTupleList);
            tuples.setUserTuples(userTupleList);

            tuples.setDial_id(dial.getId());

            for (int turnid = 0; turnid < turns.size(); turnid++) {
                Turn turn = turns.get(turnid);

                String useract = turn.getUser().getDact();
                String systemact = turn.getSystem().getDact();

                Tuple userTuple = new Tuple();

                userTuple.setActor("user");

                userTuple.setContent(turn.getUser().getHyp());

                List<String[]> userActList = getAct(useract);

                List<KeyValue> keyvalueUserList = new ArrayList<>();

                for (String act[] : userActList) {
                    KeyValue keyvalueUser = new KeyValue();

                    keyvalueUser.setKey(act[0]);
                    if (act.length == 2) {
                        keyvalueUser.setValue(act[1]);
                    }
                    keyvalueUserList.add(keyvalueUser);
                }
                userTuple.setKeyvalueList(keyvalueUserList);
                userTupleList[turnid] = userTuple;

                //system acts
                Tuple systemTuple = new Tuple();
                systemTuple.setActor("system");
                systemTuple.setContent(turn.getSystem().getRef());
                List<String[]> systemActList = getAct(systemact);

                List<KeyValue> keyvalueSystemList = new ArrayList<>();

                for (String act[] : systemActList) {

                    KeyValue keyvalueSystem = new KeyValue();

                    keyvalueSystem.setKey(act[0]);

                    if (act.length == 2) {
                        keyvalueSystem.setValue(act[1]);
                    }
                    keyvalueSystemList.add(keyvalueSystem);
                }

                systemTuple.setKeyvalueList(keyvalueSystemList);
                systemTupleList[turnid] = systemTuple;

            }
            tuplesList.add(tuples);
        }
        dials.setDials(tuplesList);

        return dials;
    }

    public static Tuples getTuples(Dial dial) {

//        try {
//            List<Dial> dials = new ObjectMapper().readValue(new File(jsonFile), new TypeReference<List<Dial>>() {
//            });
//            for (Dial dial : dials) {
        List<Turn> turns = dial.getTurns();
//                List<String> userActsList = new ArrayList<>();
//                List<String> systemActsList = new ArrayList<>();

//                List<String> userSpeechesList = new ArrayList<>();
//                List<String> systemSpeechesList = new ArrayList<>();
        Tuple[] userTupleList = new Tuple[turns.size()];
        Tuple[] systemTupleList = new Tuple[turns.size()];

        Tuples tuples = new Tuples();
        tuples.setTurnNum(turns.size());
        tuples.setSystemTuples(systemTupleList);
        tuples.setUserTuples(userTupleList);
        tuples.setDial_id(dial.getId());

        for (int turnid = 0; turnid < turns.size(); turnid++) {
            Turn turn = turns.get(turnid);

//                    Turn nextturn = new Turn();
//                    if (turnid + 1 < turns.size()) {
//                        nextturn = turns.get(turnid + 1);
//                    }
//
//                    String useract = turn.getUser().getDact();
//
//                    userActsList.add(useract);
//
//                    if (turnid + 1 < turns.size()) {
//                        String systemact = nextturn.getSystem().getDact();
//
//                        systemActsList.add(systemact);
//
//                        userSpeechesList.add(turn.getUser().getHyp());
//                        systemSpeechesList.add(nextturn.getSystem().getRef());
//
//                    }
//
//                }
//                Set<String> keyWordSet = new HashSet<>();
//                int SIZE = userActsList.size();
//
//                Tuple[] userTupleList = new Tuple[SIZE];
//                Tuple[] systemTupleList = new Tuple[SIZE];
//
//                for (int i = 0; i < SIZE; i++) {
            String useract = turn.getUser().getDact();
//                    String useract = userActsList.get(i);
//                    if (i < systemActsList.size()) {
            String systemact = turn.getSystem().getDact();

//                    String systemact = systemActsList.get(i);
            Tuple userTuple = new Tuple();

            //user acts
//                        System.out.println("USER");
            userTuple.setActor("user");

//                    String userspeech = userSpeechesList.get(i);
            if (turn.getUser().getHyp().trim().length() > 0) {
                userTuple.setContent(turn.getUser().getHyp());
                String content = turn.getUser().getHyp();
            } else if (turn.getUser().getRef().trim().length() > 0) {
                userTuple.setContent(turn.getUser().getRef());
            } else {
                userTuple.setContent(turn.getUser().getBase());
            }

//                        System.out.println(userspeech);
//            System.out.println(useract);
            List<String[]> userActList = getAct(useract);

            List<KeyValue> keyvalueUserList = new ArrayList<>();

            for (String act[] : userActList) {
                KeyValue keyvalueUser = new KeyValue();

//                            System.out.println("key: " + act[0]);
                keyvalueUser.setKey(act[0]);
                if (act.length == 2) {
//                                System.out.println("value: " + act[1]);
                    keyvalueUser.setValue(act[1]);
                }
                keyvalueUserList.add(keyvalueUser);
            }
            userTuple.setKeyvalueList(keyvalueUserList);
            userTupleList[turnid] = userTuple;

            //--------------------------------------SYSTEM------------------------
//                        System.out.println("");
            //system acts
            Tuple systemTuple = new Tuple();
            systemTuple.setActor("system");
//                        System.out.println("SYSTEM");
//                    String systemspeech = systemSpeechesList.get(i);

            if (turn.getSystem().getRef().trim().length() > 0) {
                systemTuple.setContent(turn.getSystem().getRef());
            } else if (turn.getSystem().getHyp().trim().length() > 0) {
                systemTuple.setContent(turn.getSystem().getHyp());
            } else {
                systemTuple.setContent(turn.getSystem().getBase());
            }
//                        System.out.println(systemspeech);
            List<String[]> systemActList = getAct(systemact);

            List<KeyValue> keyvalueSystemList = new ArrayList<>();

            for (String act[] : systemActList) {

                KeyValue keyvalueSystem = new KeyValue();

//                            System.out.println("key: " + act[0]);
                keyvalueSystem.setKey(act[0]);

                if (act.length == 2) {
//                                System.out.println("value: " + act[1]);
                    keyvalueSystem.setValue(act[1]);
                }
                keyvalueSystemList.add(keyvalueSystem);
            }

            systemTuple.setKeyvalueList(keyvalueSystemList);
            systemTupleList[turnid] = systemTuple;

            //generate here
//                        System.out.println("");
//                        generateDialogues(keyWordSet, userActList, systemActList, nameSystemRulesList, nameUserRulesList, otherSystemRulesList, otherUserRulesList);
//
//                        System.out.println("===============");
//                        System.out.println("");
//                    }
        }
//                System.out.println("------------");
//                System.out.println("key words set");
//                for (String keyword : keyWordSet) {
//                    System.out.println(keyword);
//                }
//                System.out.println("------------");

        //OUTPUT HERE
//        System.out.println(userTupleList.length);
//        System.out.println(turns.size());
//        for (int turnid = 0; turnid < turns.size(); turnid++) {
//
//            System.out.println("turn: " + turnid);
//
//            Tuple systemTuple = systemTupleList[turnid];
//            System.out.println(systemTuple.getActor());
////                    System.out.println(systemTuple.getAct());
//            System.out.println(systemTuple.getContent());
//            List<KeyValue> keyvalueSystemList = systemTuple.getKeyvalueList();
//            for (KeyValue keyvalue : keyvalueSystemList) {
//                System.out.println("key: " + keyvalue.getKey());
//                if (keyvalue.getValue() != null) {
//                    System.out.println("value: " + keyvalue.getValue());
//                }
//            }
//            System.out.println("");
//
//            Tuple userTuple = userTupleList[turnid];
//            System.out.println(userTuple.getActor());
////                    System.out.println(userTuple.getAct());
//            System.out.println(userTuple.getContent());
//            List<KeyValue> keyvalueUserList = userTuple.getKeyvalueList();
//            for (KeyValue keyvalue : keyvalueUserList) {
//                System.out.println("key: " + keyvalue.getKey());
//                if (keyvalue.getValue() != null) {
//                    System.out.println("value: " + keyvalue.getValue());
//                }
//            }
//            System.out.println("");
//
//            System.out.println("-----------------");
//        }
//            }
//        } catch (IOException ex) {
//            Logger.getLogger(KeyWords.class.getName()).log(Level.SEVERE, null, ex);
//        }
        return tuples;
    }

    public static void getKeyWords2(Dial dial) {

//        try {
//            List<Dial> dials = new ObjectMapper().readValue(new File(jsonFile), new TypeReference<List<Dial>>() {
//            });
//            for (Dial dial : dials) {
        List<Turn> turns = dial.getTurns();
//                List<String> userActsList = new ArrayList<>();
//                List<String> systemActsList = new ArrayList<>();

//                List<String> userSpeechesList = new ArrayList<>();
//                List<String> systemSpeechesList = new ArrayList<>();
        Tuple[] userTupleList = new Tuple[turns.size()];
        Tuple[] systemTupleList = new Tuple[turns.size()];

        for (int turnid = 0; turnid < turns.size(); turnid++) {
            Turn turn = turns.get(turnid);

//                    Turn nextturn = new Turn();
//                    if (turnid + 1 < turns.size()) {
//                        nextturn = turns.get(turnid + 1);
//                    }
//
//                    String useract = turn.getUser().getDact();
//
//                    userActsList.add(useract);
//
//                    if (turnid + 1 < turns.size()) {
//                        String systemact = nextturn.getSystem().getDact();
//
//                        systemActsList.add(systemact);
//
//                        userSpeechesList.add(turn.getUser().getHyp());
//                        systemSpeechesList.add(nextturn.getSystem().getRef());
//
//                    }
//
//                }
//                Set<String> keyWordSet = new HashSet<>();
//                int SIZE = userActsList.size();
//
//                Tuple[] userTupleList = new Tuple[SIZE];
//                Tuple[] systemTupleList = new Tuple[SIZE];
//
//                for (int i = 0; i < SIZE; i++) {
            String useract = turn.getUser().getDact();
//                    String useract = userActsList.get(i);
//                    if (i < systemActsList.size()) {
            String systemact = turn.getSystem().getDact();

//                    String systemact = systemActsList.get(i);
            Tuple userTuple = new Tuple();

            //user acts
//                        System.out.println("USER");
            userTuple.setActor("user");

//                    String userspeech = userSpeechesList.get(i);
            userTuple.setContent(turn.getUser().getHyp());

//                        System.out.println(userspeech);
            List<String[]> userActList = getAct(useract);

            List<KeyValue> keyvalueUserList = new ArrayList<>();

            for (String act[] : userActList) {
                KeyValue keyvalueUser = new KeyValue();

//                            System.out.println("key: " + act[0]);
                keyvalueUser.setKey(act[0]);
                if (act.length == 2) {
//                                System.out.println("value: " + act[1]);
                    keyvalueUser.setValue(act[1]);
                }
                keyvalueUserList.add(keyvalueUser);
            }
            userTuple.setKeyvalueList(keyvalueUserList);
            userTupleList[turnid] = userTuple;

            //--------------------------------------SYSTEM------------------------
//                        System.out.println("");
            //system acts
            Tuple systemTuple = new Tuple();
            systemTuple.setActor("system");
//                        System.out.println("SYSTEM");
//                    String systemspeech = systemSpeechesList.get(i);
            systemTuple.setContent(turn.getSystem().getRef());
//                        System.out.println(systemspeech);
            List<String[]> systemActList = getAct(systemact);

            List<KeyValue> keyvalueSystemList = new ArrayList<>();

            for (String act[] : systemActList) {

                KeyValue keyvalueSystem = new KeyValue();

//                            System.out.println("key: " + act[0]);
                keyvalueSystem.setKey(act[0]);

                if (act.length == 2) {
//                                System.out.println("value: " + act[1]);
                    keyvalueSystem.setValue(act[1]);
                }
                keyvalueSystemList.add(keyvalueSystem);
            }

            systemTuple.setKeyvalueList(keyvalueSystemList);
            systemTupleList[turnid] = systemTuple;

            //generate here
//                        System.out.println("");
//                        generateDialogues(keyWordSet, userActList, systemActList, nameSystemRulesList, nameUserRulesList, otherSystemRulesList, otherUserRulesList);
//
//                        System.out.println("===============");
//                        System.out.println("");
//                    }
        }
//                System.out.println("------------");
//                System.out.println("key words set");
//                for (String keyword : keyWordSet) {
//                    System.out.println(keyword);
//                }
//                System.out.println("------------");

        //OUTPUT HERE
        System.out.println(userTupleList.length);
        System.out.println(turns.size());
        for (int turnid = 0; turnid < turns.size(); turnid++) {

            System.out.println("turn: " + turnid);

            Tuple systemTuple = systemTupleList[turnid];
            System.out.println(systemTuple.getActor());
//                    System.out.println(systemTuple.getAct());
            System.out.println(systemTuple.getContent());
            List<KeyValue> keyvalueSystemList = systemTuple.getKeyvalueList();
            for (KeyValue keyvalue : keyvalueSystemList) {
                System.out.println("key: " + keyvalue.getKey());
                if (keyvalue.getValue() != null) {
                    System.out.println("value: " + keyvalue.getValue());
                }
            }
            System.out.println("");

            Tuple userTuple = userTupleList[turnid];
            System.out.println(userTuple.getActor());
//                    System.out.println(userTuple.getAct());
            System.out.println(userTuple.getContent());
            List<KeyValue> keyvalueUserList = userTuple.getKeyvalueList();
            for (KeyValue keyvalue : keyvalueUserList) {
                System.out.println("key: " + keyvalue.getKey());
                if (keyvalue.getValue() != null) {
                    System.out.println("value: " + keyvalue.getValue());
                }
            }
            System.out.println("");

            System.out.println("-----------------");
        }

//            }
//        } catch (IOException ex) {
//            Logger.getLogger(KeyWords.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }

    public static void printTuples(Tuples tuples) {

        System.out.println("turns number: " + tuples.getTurnNum());
        System.out.println("");

        Tuple[] systemTupleList = tuples.getSystemTuples();
        Tuple[] userTupleList = tuples.getUserTuples();

        for (int turnid = 0; turnid < tuples.getTurnNum(); turnid++) {

            System.out.println("turn: " + turnid);

            Tuple systemTuple = systemTupleList[turnid];
            System.out.println(systemTuple.getActor());
//                    System.out.println(systemTuple.getAct());
            System.out.println(systemTuple.getContent());
            List<KeyValue> keyvalueSystemList = systemTuple.getKeyvalueList();
            for (KeyValue keyvalue : keyvalueSystemList) {
                System.out.println("key: " + keyvalue.getKey());
                if (keyvalue.getValue() != null) {
                    System.out.println("value: " + keyvalue.getValue());
                }
            }
            System.out.println("");

            Tuple userTuple = userTupleList[turnid];
            System.out.println(userTuple.getActor());
//                    System.out.println(userTuple.getAct());
            System.out.println(userTuple.getContent());
            List<KeyValue> keyvalueUserList = userTuple.getKeyvalueList();
            for (KeyValue keyvalue : keyvalueUserList) {
                System.out.println("key: " + keyvalue.getKey());
                if (keyvalue.getValue() != null) {
                    System.out.println("value: " + keyvalue.getValue());
                }
            }
            System.out.println("");

            System.out.println("-----------------");
        }
    }

    public static void printDials(Dials dials, String outFile) {

        try {
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File(outFile)), Configs.ENCODING));

            for (Tuples tuples : dials.getDials()) {

                writer.write(String.format("dial id: %s\n", tuples.getDial_id()));

//                Tuples tuples = getTuples(dial);
                writer.write(String.format("turns number: %d\n", tuples.getTurnNum()));

                Tuple[] systemTupleList = tuples.getSystemTuples();
                Tuple[] userTupleList = tuples.getUserTuples();

                for (int turnid = 0; turnid < tuples.getTurnNum(); turnid++) {

//                System.out.println("turn: " + turnid);
//                    writer.write("----------------------\n");
                    writer.write(String.format("\nturn: %d\n", turnid));

                    Tuple systemTuple = systemTupleList[turnid];
//                System.out.println(systemTuple.getActor());
//                    writer.write(String.format("actor: %s\n", systemTuple.getActor()));
//                    System.out.println(systemTuple.getAct());
//                System.out.println(systemTuple.getContent());
                    if (systemTuple.getContent() != null) {
                        if (systemTuple.getContent().trim().length() > 0) {
                            writer.write(String.format("SYSTEM: %s\n", systemTuple.getContent()));
                        }
                    }

                    List<KeyValue> keyvalueSystemList = systemTuple.getKeyvalueList();
//                    for (KeyValue keyvalue : keyvalueSystemList) {
//                    System.out.println("key: " + keyvalue.getKey());
//                        writer.write(String.format("key: %s\n", keyvalue.getKey()));

//                        if (keyvalue.getValue() != null) {
//                        System.out.println("value: " + keyvalue.getValue());
//                            writer.write(String.format("value: %s\n", keyvalue.getValue()));
//                        }
//                    }
//                System.out.println("");
//                    writer.write("\n");
                    Tuple userTuple = userTupleList[turnid];
//                System.out.println(userTuple.getActor());
//                    writer.write(String.format("actor: %s\n", userTuple.getActor()));

//                    System.out.println(userTuple.getAct());
//                System.out.println(userTuple.getContent());
                    if (userTuple.getContent() != null) {
                        if (userTuple.getContent().trim().length() > 0) {
                            writer.write(String.format("USER: %s\n", userTuple.getContent()));
                        }
                    }

                    List<KeyValue> keyvalueUserList = userTuple.getKeyvalueList();
//                    for (KeyValue keyvalue : keyvalueUserList) {
//                    System.out.println("key: " + keyvalue.getKey());
//                        writer.write(String.format("key: %s\n", keyvalue.getKey()));

//                        if (keyvalue.getValue() != null) {
//                        System.out.println("value: " + keyvalue.getValue());
//                            writer.write(String.format("value: %s\n", keyvalue.getValue()));
//                        }
//                    }
//                System.out.println("");
//                System.out.println("-----------------");
                }
                writer.write("=================================================================================\n");

            }
            writer.close();

        } catch (IOException ex) {
            Logger.getLogger(KeyWords.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static void printKeyWordsDials(Dials dials, String outFile) {

        try {
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File(outFile)), Configs.ENCODING));

            for (Tuples tuples : dials.getDials()) {

                writer.write(String.format("dial id: %s\n", tuples.getDial_id()));

//                Tuples tuples = getTuples(dial);
                writer.write(String.format("turns number: %d\n\n", tuples.getTurnNum()));

                Tuple[] systemTupleList = tuples.getSystemTuples();
                Tuple[] userTupleList = tuples.getUserTuples();

                for (int turnid = 0; turnid < tuples.getTurnNum(); turnid++) {

//                System.out.println("turn: " + turnid);
                    writer.write("----------------------\n");
                    writer.write(String.format("turn: %d\n\n", turnid));

                    Tuple systemTuple = systemTupleList[turnid];
//                System.out.println(systemTuple.getActor());
                    writer.write(String.format("actor: %s\n", systemTuple.getActor()));
//                    System.out.println(systemTuple.getAct());
//                System.out.println(systemTuple.getContent());
                    if (systemTuple.getContent().trim().length() > 0) {
                        writer.write(String.format("content: %s\n", systemTuple.getContent()));
                    }

                    List<KeyValue> keyvalueSystemList = systemTuple.getKeyvalueList();
                    for (KeyValue keyvalue : keyvalueSystemList) {
//                    System.out.println("key: " + keyvalue.getKey());
                        if (keyvalue.getKey() != null && keyvalue.getKey().length() > 0) {
                            writer.write(String.format("key: %s\n", keyvalue.getKey()));
                        }

                        if (keyvalue.getValue() != null) {
//                        System.out.println("value: " + keyvalue.getValue());
                            writer.write(String.format("value: %s\n", keyvalue.getValue()));
                        }
                    }
//                System.out.println("");

                    writer.write("\n");
                    Tuple userTuple = userTupleList[turnid];
//                System.out.println(userTuple.getActor());
                    writer.write(String.format("actor: %s\n", userTuple.getActor()));

//                    System.out.println(userTuple.getAct());
//                System.out.println(userTuple.getContent());
                    if (userTuple.getContent().trim().length() > 0) {
                        writer.write(String.format("content: %s\n", userTuple.getContent()));
                    }

                    List<KeyValue> keyvalueUserList = userTuple.getKeyvalueList();
                    for (KeyValue keyvalue : keyvalueUserList) {
//                    System.out.println("key: " + keyvalue.getKey());
                        if (keyvalue.getKey() != null && keyvalue.getKey().length() > 0) {
                            writer.write(String.format("key: %s\n", keyvalue.getKey()));
                        }

                        if (keyvalue.getValue() != null) {
//                        System.out.println("value: " + keyvalue.getValue());
                            writer.write(String.format("value: %s\n", keyvalue.getValue()));

                        }
                    }
//                System.out.println("");
//                System.out.println("-----------------");
                }
                writer.write("==============================\n");

            }
            writer.close();

        } catch (IOException ex) {
            Logger.getLogger(KeyWords.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static void printDials(List<Dial> dials, String outFile) {

        try {
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File(outFile)), Configs.ENCODING));

            for (Dial dial : dials) {

                writer.write(String.format("dial id: %s\n", dial.getId()));

                Tuples tuples = getTuples(dial);

                writer.write(String.format("turns number: %d\n\n", tuples.getTurnNum()));

                Tuple[] systemTupleList = tuples.getSystemTuples();
                Tuple[] userTupleList = tuples.getUserTuples();

                for (int turnid = 0; turnid < tuples.getTurnNum(); turnid++) {

//                System.out.println("turn: " + turnid);
                    writer.write("----------------------\n");
                    writer.write(String.format("tune: %d\n\n", turnid));

                    Tuple systemTuple = systemTupleList[turnid];
//                System.out.println(systemTuple.getActor());
                    writer.write(String.format("actor: %s\n", systemTuple.getActor()));
//                    System.out.println(systemTuple.getAct());
//                System.out.println(systemTuple.getContent());
                    writer.write(String.format("content: %s\n", systemTuple.getContent()));

                    List<KeyValue> keyvalueSystemList = systemTuple.getKeyvalueList();
                    for (KeyValue keyvalue : keyvalueSystemList) {
//                    System.out.println("key: " + keyvalue.getKey());
                        writer.write(String.format("key: %s\n", keyvalue.getKey()));

                        if (keyvalue.getValue() != null) {
//                        System.out.println("value: " + keyvalue.getValue());
                            writer.write(String.format("value: %s\n", keyvalue.getValue()));
                        }
                    }
//                System.out.println("");

                    writer.write("\n");
                    Tuple userTuple = userTupleList[turnid];
//                System.out.println(userTuple.getActor());
                    writer.write(String.format("actor: %s\n", userTuple.getActor()));

//                    System.out.println(userTuple.getAct());
//                System.out.println(userTuple.getContent());
                    writer.write(String.format("content: %s\n", userTuple.getContent()));

                    List<KeyValue> keyvalueUserList = userTuple.getKeyvalueList();
                    for (KeyValue keyvalue : keyvalueUserList) {
//                    System.out.println("key: " + keyvalue.getKey());
                        writer.write(String.format("key: %s\n", keyvalue.getKey()));

                        if (keyvalue.getValue() != null) {
//                        System.out.println("value: " + keyvalue.getValue());
                            writer.write(String.format("value: %s\n", keyvalue.getValue()));

                        }
                    }
//                System.out.println("");
//                System.out.println("-----------------");
                }
                writer.write("==============================\n");

            }
            writer.close();

        } catch (IOException ex) {
            Logger.getLogger(KeyWords.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static void printTuples(Tuples tuples, String outFile) {

        try {
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File(outFile)), Configs.ENCODING));

            writer.write(String.format("turns number: %d\n\n", tuples.getTurnNum()));

            Tuple[] systemTupleList = tuples.getSystemTuples();
            Tuple[] userTupleList = tuples.getUserTuples();

            for (int turnid = 0; turnid < tuples.getTurnNum(); turnid++) {

//                System.out.println("turn: " + turnid);
                writer.write("----------------------\n");
                writer.write(String.format("tune: %d\n\n", turnid));

                Tuple systemTuple = systemTupleList[turnid];
//                System.out.println(systemTuple.getActor());
                writer.write(String.format("actor: %s\n", systemTuple.getActor()));
//                    System.out.println(systemTuple.getAct());
//                System.out.println(systemTuple.getContent());
                writer.write(String.format("content: %s\n", systemTuple.getContent()));

                List<KeyValue> keyvalueSystemList = systemTuple.getKeyvalueList();
                for (KeyValue keyvalue : keyvalueSystemList) {
//                    System.out.println("key: " + keyvalue.getKey());
                    writer.write(String.format("key: %s\n", keyvalue.getKey()));

                    if (keyvalue.getValue() != null) {
//                        System.out.println("value: " + keyvalue.getValue());
                        writer.write(String.format("value: %s\n", keyvalue.getValue()));
                    }
                }
//                System.out.println("");

                writer.write("\n");
                Tuple userTuple = userTupleList[turnid];
//                System.out.println(userTuple.getActor());
                writer.write(String.format("actor: %s\n", userTuple.getActor()));

//                    System.out.println(userTuple.getAct());
//                System.out.println(userTuple.getContent());
                writer.write(String.format("content: %s\n", userTuple.getContent()));

                List<KeyValue> keyvalueUserList = userTuple.getKeyvalueList();
                for (KeyValue keyvalue : keyvalueUserList) {
//                    System.out.println("key: " + keyvalue.getKey());
                    writer.write(String.format("key: %s\n", keyvalue.getKey()));

                    if (keyvalue.getValue() != null) {
//                        System.out.println("value: " + keyvalue.getValue());
                        writer.write(String.format("value: %s\n", keyvalue.getValue()));

                    }
                }
//                System.out.println("");
//                System.out.println("-----------------");
            }
            writer.close();

        } catch (IOException ex) {
            Logger.getLogger(KeyWords.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static List<String[]> getAct(String dact) {

        List<String[]> actList = new ArrayList<>();

        if (dact.isEmpty() || !dact.contains("(") || !dact.contains(")")) {
            return actList;
        }

        String[] dacts = dact.trim().split("[|]{1}");
        for (String dacti : dacts) {
            dact = dacti.trim();

            int start = dact.indexOf("(");
            int end = dact.lastIndexOf(")");

//        System.out.println(start);
//        System.out.println(end);
//        System.out.println(dact);
            String act_content = dact.substring(start + 1, end);
//        System.out.println("act content");
//        System.out.println(act_content);
//        System.out.println("");

            String[] acts = act_content.trim().split(",");

//        System.out.println("acts");
            for (String act : acts) {
//            System.out.println(act);
//String[] fields=new String[2];

//            String keywords = "";
                String[] act_st = act.split("=");
                if (act_st.length == 2) {
//                keywords = act_st[1];
                    act_st[1] = act_st[1].replace("'", "");
                }
//else {
//                keywords = act;
//            }

//            keywords = keywords.replace("'", "");
//            System.out.println(keywords);
                actList.add(act_st);
            }
        }

        return actList;

    }

}

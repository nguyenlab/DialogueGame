/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab.dialogues.methods;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import lab.dialogues.configs.Configs;
import lab.dialogues.entities.tuples.Dials;
import lab.dialogues.entities.tuples.KeyValue;
import lab.dialogues.entities.tuples.Tuple;
import lab.dialogues.entities.tuples.Tuples;

/**
 *
 * @author s1420211
 */
public class Grammar {

    public static Dials grammars(Dials inDials) {

        Dials outDials = new Dials();
        List<Tuples> tuplesList = new ArrayList<>();

        for (Tuples inTuples : inDials.getDials()) {

            Tuples outTuples = new Tuples();

            Tuple[] grammar1SystemTuples = grammar1(inTuples.getSystemTuples());
            outTuples.setSystemTuples(grammar1SystemTuples);
            outTuples.setUserTuples(inTuples.getUserTuples());
            outTuples.setTurnNum(inTuples.getTurnNum());
            outTuples.setDial_id(inTuples.getDial_id());

            tuplesList.add(outTuples);

        }
        outDials.setDials(tuplesList);
        return outDials;

    }

    public static Tuples grammars(Tuples inTuples) {

        Tuples outTuples = new Tuples();

        
        Tuple[] grammar1SystemTuples = grammar1(inTuples.getSystemTuples());
        outTuples.setSystemTuples(grammar1SystemTuples);
        outTuples.setUserTuples(inTuples.getUserTuples());
        outTuples.setTurnNum(inTuples.getTurnNum());
        outTuples.setDial_id(inTuples.getDial_id());

        return outTuples;

    }

    public static Tuple[] grammar1(Tuple[] inTuples) {

        Tuple[] outTuples = new Tuple[inTuples.length];

        for (int i = 0; i < inTuples.length; i++) {

            outTuples[i] = grammar1(inTuples[i]);

        }

        return outTuples;

    }

    public static Tuple grammar1(Tuple inTuple) {
        Tuple outTuple = new Tuple();

        outTuple.setActor("system");

        for (KeyValue keyvalue : inTuple.getKeyvalueList()) {
            String key = keyvalue.getKey();
            if (key.matches("phone") || key.matches("address") || key.matches("area")) {
                outTuple.setContent(keyvalue.getValue());
            } else {
                outTuple.setContent(inTuple.getContent());
            }
        }

        return outTuple;
    }

    public static void grammar(String inFile) {

        BufferedReader reader = null;
        try {

            reader = new BufferedReader(new InputStreamReader(new FileInputStream(new File(inFile)), Configs.ENCODING));
            String line;

            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }

            reader.close();

        } catch (IOException ex) {
            Logger.getLogger(Grammar.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}

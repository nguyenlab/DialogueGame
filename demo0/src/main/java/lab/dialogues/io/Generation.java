/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab.dialogues.io;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import lab.dialogues.configs.Configs;
import lab.dialogues.entities.Dial;
import lab.dialogues.entities.tuples.Dials;
import lab.dialogues.entities.tuples.KeyValue;
import lab.dialogues.entities.tuples.Tuple;
import lab.dialogues.entities.tuples.Tuples;

/**
 *
 * @author s1420211
 */
public class Generation {

    public static void generation(String inFile, String rulesFile, String outFile) {

        List<Dial> dialsList = lab.dialogues.io.KeyWords.getDialList(inFile);
        List<Tuples> tuplesList = new ArrayList<>();
        List<Tuples> baseTuplesList = new ArrayList<>();

        for (Dial dial : dialsList) {

            Tuples inTuples = lab.dialogues.io.KeyWords.getTuples(dial);
            Tuples baseTuples = new Tuples();
            baseTuples.setTuples(inTuples);
            baseTuplesList.add(baseTuples);

            Tuples outTuples = new Tuples();

            //topic            
            Tuples topicTuples = new Tuples();
            topicTuples = lab.dialogues.methods.PersonalTopics.personalTopics(inTuples, rulesFile);

            //greeting
            Tuples greetingTuples = new Tuples();
            greetingTuples = lab.dialogues.methods.Greetings.greetings(inTuples, rulesFile);

            ////random
            int method = random_starting(3);

            Tuples grammarTuples = new Tuples();

            if (method == 0) {
                grammarTuples = lab.dialogues.methods.Grammar.grammars(inTuples);
            } else if (method == 1) {
                grammarTuples = lab.dialogues.methods.Grammar.grammars(greetingTuples);
            } else {
                grammarTuples = lab.dialogues.methods.Grammar.grammars(topicTuples);
            }

            //add
            tuplesList.add(grammarTuples);
        }

        //print
        Dials inDials = new Dials();
        inDials.setDials(baseTuplesList);

        Dials outDials = new Dials();
        outDials.setDials(tuplesList);

//        lab.dialogues.io.KeyWords.printDials(outDials, outFile);
        generateOutput_Web(inDials, outDials, outFile);
    }
    
    public static int random_starting(int numchoices) {

        //1: greeting
        //2: personal topic
        Random ran = new Random();
        int x = ran.nextInt(numchoices);
        return x;

    }

    public static void generateOutput_Web(Dials inDials, Dials outDials, String outFile) {

        try {
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File(outFile)), Configs.ENCODING));

            List<Tuples> inTuples = new ArrayList<>();
            inTuples = inDials.getDials();
            List<Tuples> outTuples = new ArrayList<>();
            outTuples = outDials.getDials();

            for (int i = 0; i < inTuples.size(); i++) {

                Tuples inTuple = inTuples.get(i);
                Tuples outTuple = outTuples.get(i);

                writer.write(String.format("DIALOGUE_%d\n\n", i + 1));
                String baseDialogue = generateOutput(inTuple, "BASELINE").toString();
                String gameDialogue = generateOutput(outTuple, "GAME").toString();

                //order
                Random ran = new Random();
                int choice = ran.nextInt(2);
                //
                if (choice == 0) {
                    writer.write(baseDialogue);
                    writer.write("\n");
                    writer.write(gameDialogue);
                } else {
                    writer.write(gameDialogue);
                    writer.write("\n");
                    writer.write(baseDialogue);
                }
                writer.write("\n");

            }
            writer.close();

        } catch (IOException ex) {
            Logger.getLogger(KeyWords.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static StringBuilder generateOutput(Tuples tuples, String setup) {

        StringBuilder builder = new StringBuilder();

        builder.append(String.format("OUTPUT_%s\n", setup));
        Tuple[] systemTupleList = tuples.getSystemTuples();
        Tuple[] userTupleList = tuples.getUserTuples();

        for (int turnid = 0; turnid < tuples.getTurnNum(); turnid++) {

            Tuple systemTuple = systemTupleList[turnid];
            if (systemTuple.getContent() != null) {
                if (systemTuple.getContent().trim().length() > 0) {
                    builder.append(String.format("SYSTEM: %s\n", systemTuple.getContent()));
                }
            }

            List<KeyValue> keyvalueSystemList = systemTuple.getKeyvalueList();

            Tuple userTuple = userTupleList[turnid];

            if (userTuple.getContent() != null) {
                if (userTuple.getContent().trim().length() > 0) {
                    builder.append(String.format("USER: %s\n", userTuple.getContent()));

                }
            }

            List<KeyValue> keyvalueUserList = userTuple.getKeyvalueList();

        }
        builder.append(String.format("END_OUTPUT_%s\n", setup));

        return builder;
    }

    public static void generateOutput2(Tuples tuples, String outFile) {

        try {
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File(outFile)), Configs.ENCODING));

            writer.write(String.format("turns number: %d\n\n", tuples.getTurnNum()));

            Tuple[] systemTupleList = tuples.getSystemTuples();
            Tuple[] userTupleList = tuples.getUserTuples();

            for (int turnid = 0; turnid < tuples.getTurnNum(); turnid++) {

                writer.write("----------------------\n");
                writer.write(String.format("turn: %d\n\n", turnid));

                //system
                Tuple systemTuple = systemTupleList[turnid];
                writer.write(String.format("SYSTEM: %s\n", systemTuple.getContent()));

                //user
                Tuple userTuple = userTupleList[turnid];
                writer.write(String.format("USER: %s\n", userTuple.getContent()));

            }
            writer.close();

        } catch (IOException ex) {
            Logger.getLogger(KeyWords.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}

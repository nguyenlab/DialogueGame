/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.List;
import lab.dialogues.entities.Dial;
import lab.dialogues.entities.tuples.Dials;

/**
 *
 * @author s1420211
 */
public class Dialogue_GameRefinement {

    public static void main(String[] args) {

        if (args[0].toLowerCase().trim().matches("help")) {
            helps();
        } else {

            //choose input option----------------------------------
            String inFile = "";
            String inputOption = args[0];

            if (inputOption.matches("full")) {
                inFile = "sfx-restaurant.json";
            } else if (inputOption.matches("demo1")) {
                inFile = "sfx-restaurant-demo-1.json";
            } else if (inputOption.matches("demo4")) {
                inFile = "sfx-restaurant-demo-4.json";
            } else {
                inFile = inputOption;
            }

            // choose rule file----------------------------------
            String ruleFile = args[1];

            //choose methods----------------------------------
            String methodOption = args[2];
            String outFile = args[3];

            if (methodOption.matches("show-input")) {                
                show_input(inFile, outFile);
            } else if (methodOption.matches("show-keywords")) {
                show_keywords(inFile, outFile);
            } //method1: greeting----------
            else if (methodOption.matches("greeting") && args.length == 4) {
                greetings(inFile, ruleFile, outFile);
            } //method2: personal-topic----------
            else if (methodOption.matches("personal-topic") && args.length == 4) {
                personalTopics(inFile, ruleFile, outFile);
            } //method3: grammar----------
            else if (methodOption.matches("grammar")) {
                grammar(inFile, "", outFile);
            }
            
            else if (methodOption.matches("extend")) {
                lab.dialogues.methods.ExtendKeyWords.extendKeyWords(inFile, ruleFile, outFile);
            }
            else if (methodOption.matches("feedback")) {
                lab.dialogues.methods.Feedback.feedback(inFile, ruleFile, outFile);
            }
            else if (methodOption.matches("surprising")) {
                lab.dialogues.methods.Surprising.surprising(inFile, ruleFile, outFile);
            }

            //help----------
            else {
                helps();
            }

        }
    }

    public static void helps() {
        lab.dialogues.io.Helps.helps();
    }

    public static void greetings(String inFile, String rulesFile, String outFile) {

        List<Dial> dialsList = lab.dialogues.io.KeyWords.getDialList(inFile);
        Dials inDials = new Dials();
        inDials = lab.dialogues.io.KeyWords.getDials(dialsList);
        Dials greetingDials = new Dials();
        greetingDials = lab.dialogues.methods.Greetings.greetings(inDials, rulesFile);
        lab.dialogues.io.KeyWords.printDials(greetingDials, outFile);
    }

    public static void personalTopics(String inFile, String rulesFile, String outFile) {

        List<Dial> dialsList = lab.dialogues.io.KeyWords.getDialList(inFile);
        Dials inDials = new Dials();
        inDials = lab.dialogues.io.KeyWords.getDials(dialsList);
        Dials topicDials = new Dials();
        topicDials = lab.dialogues.methods.PersonalTopics.personalTopics(inDials, rulesFile);
        lab.dialogues.io.KeyWords.printDials(topicDials, outFile);
    }

    public static void grammar(String inFile, String ruleFile, String outFile) {

        List<Dial> dialsList = lab.dialogues.io.KeyWords.getDialList(inFile);

        Dials inDials = new Dials();
        inDials = lab.dialogues.io.KeyWords.getDials(dialsList);
        Dials grammarDials = new Dials();
        grammarDials = lab.dialogues.methods.Grammar.grammars(inDials);
        lab.dialogues.io.KeyWords.printDials(grammarDials, outFile);

    }

    public static void show_input(String inFile, String outFile) {

        List<Dial> dialList = lab.dialogues.io.KeyWords.getDialList(inFile);

        Dials dials = new Dials();
        dials = lab.dialogues.io.KeyWords.getDials(dialList);
        lab.dialogues.io.KeyWords.printDials(dials, outFile);
    }

    public static void show_keywords(String inFile, String outFile) {

        List<Dial> dialsList = lab.dialogues.io.KeyWords.getDialList(inFile);
        Dials dials = new Dials();
        dials = lab.dialogues.io.KeyWords.getDials(dialsList);
        lab.dialogues.io.KeyWords.printKeyWordsDials(dials, outFile);

    }

    public static void main_old() {
//        keywords(lab.dialogues.configs.Configs.INPUT, lab.dialogues.configs.Configs.KEYWORDS_OUTPUT);
//        full_input(lab.dialogues.configs.Configs.INPUT_DEMO2, lab.dialogues.configs.Configs.OUTPUT);

//        grammar(lab.dialogues.configs.Configs.INPUT_DEMO2, lab.dialogues.configs.Configs.GRAMMAR_OUTPUT);
//        greetings(lab.dialogues.configs.Configs.INPUT_DEMO2, lab.dialogues.configs.Configs.RULES, lab.dialogues.configs.Configs.GREETINGS_OUTPUT);
//        personalTopics(lab.dialogues.configs.Configs.INPUT_DEMO2, lab.dialogues.configs.Configs.RULES, lab.dialogues.configs.Configs.TOPICS_OUTPUT);
        methods(lab.dialogues.configs.Configs.INPUT_DEMO2, lab.dialogues.configs.Configs.RULES, lab.dialogues.configs.Configs.METHODS_OUTPUT);
        //
        lab.dialogues.io.Generation.generation(
                lab.dialogues.configs.Configs.INPUT,
                lab.dialogues.configs.Configs.RULES,
                lab.dialogues.configs.Configs.METHODS_OUTPUT
        );

        lab.dialogues.methods.ExtendKeyWords.extendKeyWords(lab.dialogues.configs.Configs.INPUT_DEMO, lab.dialogues.configs.Configs.RULES, lab.dialogues.configs.Configs.EXTEND_KEYWORDS_OUTPUT);
        lab.dialogues.methods.Feedback.feedback(lab.dialogues.configs.Configs.INPUT_DEMO2, lab.dialogues.configs.Configs.RULES, lab.dialogues.configs.Configs.FEEDBACK_OUTPUT);

        lab.dialogues.methods.Surprising.surprising(lab.dialogues.configs.Configs.INPUT, lab.dialogues.configs.Configs.RULES, lab.dialogues.configs.Configs.SURPRISING_OUTPUT);

    }

    public static void methods(String inFile, String rulesFile, String outFile) {
        List<Dial> dialsList = lab.dialogues.io.KeyWords.getDialList(inFile);
        //greetings
        Dials inDials = new Dials();
        inDials = lab.dialogues.io.KeyWords.getDials(dialsList);
        Dials greetingDials = new Dials();
        greetingDials = lab.dialogues.methods.Greetings.greetings(inDials, rulesFile);

        //grammar
        Dials grammarDials = new Dials();
        grammarDials = lab.dialogues.methods.Grammar.grammars(greetingDials);
        //
        lab.dialogues.io.KeyWords.printDials(grammarDials, outFile);
    }

}

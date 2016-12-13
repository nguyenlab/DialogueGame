/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab.dialogues.configs;

/**
 *
 * @author s1420211
 */
public interface Configs {

    String ROOT = ".";
    String ENCODING = "utf-8";

    String WORD_DIR = ROOT + "/data";

    String INPUT = WORD_DIR + "/sfx-restaurant.json";
    String INPUT_DEMO = WORD_DIR + "/sfx-restaurant-demo.json";
    //
    String KEYWORDS_OUTPUT = WORD_DIR + "/keyword-output.txt";
    String OUTPUT = WORD_DIR + "/output.txt";
    String GRAMMAR_OUTPUT = WORD_DIR + "/grammar-output.txt";
    //
    String GREETINGS_OUTPUT = WORD_DIR + "/greetings-output.txt";
    String TOPICS_OUTPUT = WORD_DIR + "/topics-output.txt";
    String EXTEND_KEYWORDS_OUTPUT = WORD_DIR + "/extend-keywords-output.txt";
    String FEEDBACK_OUTPUT = WORD_DIR + "/feedback-output.txt";
    String SURPRISING_OUTPUT = WORD_DIR + "/surprising-output.txt";
    //
    String RULES = WORD_DIR + "/rules.txt";
    //
    String METHODS_OUTPUT = WORD_DIR + "/methods-output.txt";

    //
    String INPUT_DEMO2 = WORD_DIR + "/sfx-restaurant-demo2.json";
    String STATISTICS = WORD_DIR + "/statistics.txt";

}

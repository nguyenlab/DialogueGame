/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab.dialogues.io;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.List;
import lab.dialogues.entities.Dial;
import lab.dialogues.entities.Turn;

/**
 *
 * @author s1420211
 */
public class AccelerationRatio {

    public static void accelerateRatio(String jsonFile) throws IOException {

        List<Dial> dials = new ObjectMapper().readValue(new File(jsonFile), new TypeReference<List<Dial>>() {
        });

        int totalTurns = 0;
        int numGoals = 0;
        int numShoots = 0;
        int systemInformShoots = 0;
        int totalGeneratedInform = 0;

//        double totalAccelerate = 0;
//        double totalExpectedAccelerate = 0;
//        int numExpectedShoots = 0;
        int numDialogues = dials.size();

//        double average_goals_total = 0;
//        double average_shoots_total = 0;
        for (Dial dial : dials) {
            List<Turn> turns = dial.getTurns();

            totalTurns += turns.size();

            int localshoots = 0;
            int localgoals = 0;
            int localinform = 0;
//            int expectedshoots = 0;

            for (int turnid = 0; turnid < turns.size(); turnid++) {
                Turn turn = turns.get(turnid);
//                String userHyp = turn.getUser().getHyp();
                String userDact = turn.getUser().getDact();
                String systemDact = turn.getSystem().getDact();

//                System.out.println(hyp);
//                System.out.println(dact);
//                if(dact.startsWith("inform("))System.out.println("INFORM");
//                if(dact.startsWith("request(")) System.out.println("REQUEST");
                if (userDact.contains("inform(") || userDact.contains("request(")) {
                    numGoals++;
                    localgoals++;
                }
//                if (systemDact.startsWith("inform(")) {
                if (!systemDact.isEmpty()) {

                    numShoots++;
                    localshoots++;
                }

                if (systemDact.contains("inform(")) {
                    systemInformShoots++;
//                    localinform++;
                    List<String[]> systemActList = lab.dialogues.io.KeyWords.getAct(systemDact);
                    localinform += systemActList.size();

//                    for(String[]acts:systemActList){                        
//                        
//                        for(String act:acts){
//                            System.out.print(act+"\t");
//                        }
//                        System.out.println("");
//                    }
//                    System.out.println("");
                }

            }

//            System.out.println("local inform: "+localinform);
            totalGeneratedInform += localinform * 2;

//            int local_total_shoots = localgoals + localshoots;
//            average_goals_total += (double) localgoals / local_total_shoots;
//            average_shoots_total += (double) localshoots / local_total_shoots;
//            double localaccelerate = accelerateRatio(localgoals, localshoots + localgoals);
//            totalAccelerate += localaccelerate;
            //
//            expectedshoots = localshoots * 10;
//            numExpectedShoots += expectedshoots;
//            double localExpectedAccelerate = accelerateRatio(localgoals, localgoals + expectedshoots);
//            totalExpectedAccelerate += localExpectedAccelerate;
        }

        int totalShoots = numGoals + numShoots;

        double accelerate = accelerateRatio(numGoals, totalShoots);

        System.out.println("#. total turns: " + totalTurns);
        System.out.println("#. total system shoots: " + numShoots);
        System.out.println("#. total goals: " + numGoals);
        System.out.println("total shoots: " + totalShoots);
//        System.out.println("accelerate ratio: " + accelerate);

        System.out.println("------------------");

        double average_shoots = (double) totalShoots / numDialogues;
        double average_goals = (double) numGoals / numDialogues;
//        int average_shoots = Math.round((float) totalShoots / numDialogues * 10) / 10;
//        int average_goals = Math.round((float) numGoals / numDialogues * 10) / 10;
//        int average_shoots = (int) ((double)totalShoots / numDialogues + 0.5);
//        int average_goals = (int) ((double)numGoals / numDialogues + 0.5);

        double average_accelerate = accelerateRatio(average_goals, average_shoots);
        System.out.println("average: shoots, goals/dialogue");
        System.out.println("shoots: " + average_shoots);
        System.out.println("goals: " + average_goals);
        System.out.println("accelerate: " + average_accelerate);
        System.out.println("");

        System.out.println("-------------------------------------");
        System.out.println("generated");

        int generatedShoots = 18_800;
        numShoots = totalGeneratedInform;
        totalShoots = numGoals + numShoots;
//        System.out.println("system inform shoots: "+systemInformShoots);
        System.out.println("generated from inform: " + totalGeneratedInform);
//        System.out.println("generated shoots: " + generatedShoots);
//        average_shoots = (int) ((double)newTotalShoot / numDialogues + 0.5);
//        average_goals = (int) ((double)numGoals / numDialogues + 0.5);
        average_shoots = (double) totalShoots / numDialogues;
        average_goals = (double) numGoals / numDialogues;

        average_accelerate = accelerateRatio(average_goals, average_shoots);
        System.out.println("average: shoots, goals/dialogue");
        System.out.println("shoots: " + average_shoots);
        System.out.println("goals: " + average_goals);
        System.out.println("accelerate: " + average_accelerate);

//        System.out.println("");
//        System.out.println("-------------------------------------");
//        System.out.println("average on each dialouge");
//
//        average_shoots = (double) average_shoots_total / numDialogues;
//        average_goals = (double) average_goals_total / numDialogues;
//        System.out.println("shoots: " + average_shoots);
//        System.out.println("goals: " + average_goals);
//        double tempt = accelerateRatio(10, 40);
//        System.out.println("tempt ratio: " + tempt);
//        System.out.println("------------------");
//        int expectedShoots = 2500;
//        double expectedAccelerate = accelerateRatio(numGoals, expectedShoots);
//        System.out.println("#. total expected shoots: " + expectedShoots);
//        System.out.println("expected accelerate ratio: " + expectedAccelerate);
//        System.out.println("------------------");
//        double averageAccelerate=totalAccelerate/dials.size();
//        System.out.println("total accelerate: "+totalAccelerate);
//        System.out.println("number of dialogues: "+dials.size());
//        System.out.println("average accelerate: "+averageAccelerate);
//        System.out.println("--------------------");
//        System.out.println("expected");
//        int totalExpectedShoots  =numGoals+numExpectedShoots;
//        double averageExpectedAccelerate=totalExpectedAccelerate/dials.size();
//        System.out.println("total system shoots: "+numExpectedShoots);
//        System.out.println("total expected shoots: "+totalExpectedShoots);
//        System.out.println("total expected accelerate: "+totalExpectedAccelerate);
//        System.out.println("average expected acclerate: "+averageExpectedAccelerate);
    }

    public static double accelerateRatio(int goals, int shoots) {

        double accelerate = 1;

        if (shoots != 0) {

            return Math.sqrt(goals) / shoots;

        }

        return accelerate;

    }

    public static double accelerateRatio(double goals, double shoots) {

        double accelerate = 1;

        if (shoots != 0) {

            return Math.sqrt(goals) / shoots;

        }

        return accelerate;

    }

}

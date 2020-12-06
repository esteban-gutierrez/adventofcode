package com.adventofcode.day6;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CustomCustoms {
    private static final String CUSTOMS_ANSWERS = "resources/customs_declaration_answers_day_6.txt";

    public static void main (String [] args) {
        try (BufferedReader br = new BufferedReader(new FileReader(CUSTOMS_ANSWERS))) {
            String line;
            List<String> groupAnswers = new ArrayList<>();
            int yesAnswers = 0;
            while ((line = br.readLine()) != null) {
                if (line.trim().length() == 0) {
                    yesAnswers += countYesAnswers(groupAnswers);
                    groupAnswers = new ArrayList<>();
                } else {
                    groupAnswers.add(line);
                }
            }
            yesAnswers += countYesAnswers(groupAnswers);
            System.out.println("The number of questions answered 'yes' is: " + yesAnswers);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    private static int countYesAnswers(List<String> groupAnswers) {
        String questionsAnsweredWithYes = "";
        for (String answer : groupAnswers) {
            for (int i = 0; i < answer.length(); i++) {
                if (questionsAnsweredWithYes.indexOf(answer.charAt(i)) == -1) {
                    questionsAnsweredWithYes += answer.charAt(i);
                }
            }
        }
        System.out.println(groupAnswers + " -> " + questionsAnsweredWithYes + " length=" + questionsAnsweredWithYes.length());
        return questionsAnsweredWithYes.length();
    }
}

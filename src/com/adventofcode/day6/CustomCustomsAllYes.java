package com.adventofcode.day6;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CustomCustomsAllYes {
    private static final String CUSTOMS_ANSWERS = "resources/day_6_customs_declaration_answers.txt";

    public static void main (String [] args) {
        try (BufferedReader br = new BufferedReader(new FileReader(CUSTOMS_ANSWERS))) {
            String line;
            List<String> groupAnswers = new ArrayList<>();
            int yesAnswers = 0;
            while ((line = br.readLine()) != null) {
                if (line.trim().length() == 0) {
                    yesAnswers += countAllYesAnswers(groupAnswers);
                    groupAnswers = new ArrayList<>();
                } else {
                    groupAnswers.add(line);
                }
            }
            yesAnswers += countAllYesAnswers(groupAnswers);
            System.out.println("The number of questions answered 'yes' is: " + yesAnswers);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    private static int countAllYesAnswers(List<String> groupAnswers) {
        String allYes = "";
        String firstAnswer = groupAnswers.get(0);
        for (char c : firstAnswer.toCharArray()) {
            boolean addChar = true;
            for (int i = 1; i < groupAnswers.size(); i++) {
                if (groupAnswers.get(i).indexOf(c) == -1) {
                    addChar = false;
                    break;
                }
            }
            if (addChar) {
                allYes += c;
            }
        }
        System.out.println("Answers: " + groupAnswers + " -> all yes answers: " + allYes + " length=" + allYes.length());
        return allYes.length();
    }
}

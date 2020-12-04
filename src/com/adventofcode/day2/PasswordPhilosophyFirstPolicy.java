package com.adventofcode.day2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class PasswordPhilosophyFirstPolicy {
    private static final String PASSWORDS = "resources/passwords_day_2.txt";

    public static void main (String [] args) {
        int validPasswords = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(PASSWORDS))) {
            String line;
            while ((line = br.readLine()) != null) {
                String policy = getPolicy(line);
                String password = getPassword(line);
                int min = getMinNumberOfOccurrencies(policy);
                int max = getMaxNumberOfOccurrencies(policy);
                char letter = getPolicyLetter(policy);
                if (isValidPassword(password, letter, min, max)) {
                    validPasswords++;
                }
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        System.out.println(validPasswords);
    }

    private static String getPolicy(String line) {
        return line.substring(0, line.indexOf(":"));
    }

    private static String getPassword(String line) {
        return line.substring(line.indexOf(": ") + 2);
    }

    private static int getMinNumberOfOccurrencies(String policy) {
        return Integer.valueOf(policy.substring(0, policy.indexOf("-")));
    }

    private static int getMaxNumberOfOccurrencies(String policy) {
        return Integer.valueOf(policy.substring(policy.indexOf("-") + 1, policy.indexOf(" ")));
    }

    private static char getPolicyLetter(String policy) {
        return policy.charAt(policy.length() - 1);
    }

    private static boolean isValidPassword(String password, char letter, int minOccurrencies, int maxOccurrencies) {
        return countLetter(password, letter) >= minOccurrencies
                && countLetter(password, letter) <= maxOccurrencies;
    }

    private static int countLetter(String word, char letter) {
        int count = 0;
        for (int i = 0; i < word.length(); i++) {
            if (word.charAt(i) == letter) {
                count++;
            }
        }
        return count;
    }
}

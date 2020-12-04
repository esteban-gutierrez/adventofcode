package com.adventofcode.day2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class PasswordPhilosophySecondPolicy {
    private static final String PASSWORDS = "resources/passwords_day_2.txt";

    public static void main (String [] args) {
        int validPasswords = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(PASSWORDS))) {
            String line;
            while ((line = br.readLine()) != null) {
                String policy = getPolicy(line);
                String password = getPassword(line);
                int first = getFirstPosition(policy);
                int second = getSecondPosition(policy);
                char letter = getPolicyLetter(policy);
                if (isValidPassword(password, letter, first, second)) {
                    validPasswords++;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(validPasswords);
    }

    private static String getPolicy(String line) {
        return line.substring(0, line.indexOf(":"));
    }

    private static String getPassword(String line) {
        return line.substring(line.indexOf(": ") + 2);
    }

    private static int getFirstPosition(String policy) {
        return Integer.valueOf(policy.substring(0, policy.indexOf("-")));
    }

    private static int getSecondPosition(String policy) {
        return Integer.valueOf(policy.substring(policy.indexOf("-") + 1, policy.indexOf(" ")));
    }

    private static char getPolicyLetter(String policy) {
        return policy.charAt(policy.length() - 1);
    }

    private static boolean isValidPassword(String password, char letter, int first, int second) throws StringIndexOutOfBoundsException {
        return password.charAt(first - 1) == letter ^ password.charAt(second - 1) == letter;
    }
}

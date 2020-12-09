package com.adventofcode.day9;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class EncodingError {
    private static final String ENCRYPTED_DATA = "resources/encrypted_data_day_9.txt";
    private static final int AMOUNT_OF_NUMBERS = 25;

    private static long[] encryptedData;
    private static long[] previousNumbers = new long[AMOUNT_OF_NUMBERS];

    public static void main(String[] args) {
        encryptedData = new long[countNumbers()];
        try (BufferedReader br = new BufferedReader(new FileReader(ENCRYPTED_DATA))) {
            String line;
            int i = 0;
            while ((line = br.readLine()) != null) {
                encryptedData[i] = Long.valueOf(line);
                i++;
            }

            boolean pairFound ;
            i = 0;
            do {
                updateNumbers(i);
                pairFound = anyPairSumsTarget(encryptedData[i + AMOUNT_OF_NUMBERS]);
                i++;
            } while ((i < encryptedData.length) && pairFound);


        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    private static int countNumbers() {
        int numbers = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(ENCRYPTED_DATA))) {
            while ((br.readLine()) != null) {
                numbers++;
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return numbers;
    }

    private static void updateNumbers(int position) {
        for (int i = 0; i < AMOUNT_OF_NUMBERS; i++) {
            previousNumbers[i] = encryptedData[i + position];
        }
    }

    private static boolean anyPairSumsTarget(long target) {
        for (int i = 0; i < previousNumbers.length - 1; i++) {
            for (int j = 1; j < previousNumbers.length; j++) {
                if ((i != j) && (previousNumbers[i] + previousNumbers[j] == target)) {
                    return true;
                }
            }
        }
        System.out.println("This number cannot be obtained by adding 2 of the previous 25 numbers: " + target);
        return false;
    }
}

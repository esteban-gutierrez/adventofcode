package com.adventofcode.day9;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class EncodingError {
    private static final String ENCRYPTED_DATA = "resources/encrypted_data_day_9.txt";
    private static long[] encryptedData;
    private static int[] previousNumbers = new int[25];

    public static void main(String[] args) {
        encryptedData = new long[countNumbers()];
        try (BufferedReader br = new BufferedReader(new FileReader(ENCRYPTED_DATA))) {
            String line;
            int i = 0;
            while ((line = br.readLine()) != null) {
                encryptedData[i] = Long.valueOf(line);
                i++;
            }

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
}

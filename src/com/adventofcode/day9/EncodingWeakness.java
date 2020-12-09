package com.adventofcode.day9;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class EncodingWeakness {
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

            long invalidNumber = encryptedData[i + AMOUNT_OF_NUMBERS - 1];
            System.out.println("Invalid number: " + invalidNumber);

            long [] smallestAndLargest = findIntervalThatSumsTarget(i + AMOUNT_OF_NUMBERS - 1);
            System.out.println("The sum of the smallest number and the largest number is:");
            System.out.println(smallestAndLargest[0] + " + " + smallestAndLargest[1] + " = "
                                + (smallestAndLargest[0] + smallestAndLargest[1]));

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
        return false;
    }

    private static long[] findIntervalThatSumsTarget(int targetPosition) {
        long sum;
        for (int i = 0; i < targetPosition - 2; i++) {
            sum = encryptedData[i];
            for (int j = i + 1; j < targetPosition - 1; j++) {
                sum += encryptedData[j];
                if (sum == encryptedData[targetPosition]) {
                    System.out.println("Found interval of numbers: between " + i + " and " + j + " positions");
                    long smallest = findSmallestNumber(i, j);
                    long largest = findLargestNumber(i, j);
                    return new long []{smallest, largest};
                } else if (sum > encryptedData[targetPosition]) {
                    break;
                }
            }
        }
        return null;
    }

    private static long findSmallestNumber(int firstPosition, int lastPosition) {
        long smallest = encryptedData[firstPosition];
        for (int i = firstPosition + 1; i <= lastPosition; i++) {
            if (encryptedData[i] < smallest) {
                smallest = encryptedData[i];
            }
        }
        return smallest;
    }

    private static long findLargestNumber(int firstPosition, int lastPosition) {
        long largest = encryptedData[firstPosition];
        for (int i = firstPosition + 1; i <= lastPosition; i++) {
            if (encryptedData[i] > largest) {
                largest = encryptedData[i];
            }
        }
        return largest;
    }
}

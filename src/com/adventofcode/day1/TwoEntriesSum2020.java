package com.adventofcode.day1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class TwoEntriesSum2020 {
    private static final String PUZZLE_INPUT = "resources/input.txt";
    private static final Integer TARGET = 2020;

    public static void main (String [] args) {
        Map<Integer, Integer> numbers = readPuzzleInput();
        for (Integer number : numbers.values()) {
            if (numbers.containsKey(TARGET - number)) {
                System.out.println(number + " + " + (TARGET - number) + " = " + TARGET);
                System.out.println(number + " * " + (TARGET - number) + " = " + (number * (TARGET - number)));
                break;
            }
        }

    }

    private static Map<Integer, Integer> readPuzzleInput() {
        Map<Integer, Integer> numbers = new HashMap<>();
        String file = PUZZLE_INPUT;
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                numbers.put(Integer.valueOf(line), Integer.valueOf(line));
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return numbers;
    }


}

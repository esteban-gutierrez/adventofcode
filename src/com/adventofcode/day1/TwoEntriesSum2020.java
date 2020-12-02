package com.adventofcode.day1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TwoEntriesSum2020 {
    private static final String PUZZLE_INPUT = "resources/input.txt";

    public static void main (String [] args) {
        List<Integer> numbers = readPuzzleInput();
        for (Integer number : numbers) {
            System.out.println(number);
        }

    }

    private static List<Integer> readPuzzleInput() {
        List<Integer> numbers = new ArrayList<>();
        String file = PUZZLE_INPUT;
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                numbers.add(Integer.valueOf(line));
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return numbers;
    }
}

package com.adventofcode.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class CommonUtils {
    public static final String PUZZLE_INPUT = "resources/input.txt";
    public static Map<Integer, Integer> readPuzzleInput() {
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

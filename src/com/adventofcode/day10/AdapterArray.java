package com.adventofcode.day10;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class AdapterArray {
    private static final String JOLTAGE_ADAPTERS = "resources/day_10_joltage_adapters_input.txt";
    private static final int MAX_JOLT_DIFFERENCE = 3;
    private static final int STARTING_JOLTAGE = 0;

    private static LinkedList<Integer> adapters = new LinkedList<>();
    private static Map<Integer, Integer> joltDifferences = new HashMap<>();

    public static void main(String[] args) {
        readAdaptersInOrder();
        addInitialAndFinalJoltage();
        System.out.println("Adapters:" + adapters);
        initializeJoltDifferences();
        countDifferences();
        System.out.println(joltDifferences);
        System.out.println("Number of 1-jolt differences multiplied by the number of 3-jolt differences: "
                + calculateProductOf1JoltAnd3JoltsDifferences());
    }

    private static void readAdaptersInOrder() {
        try (BufferedReader br = new BufferedReader(new FileReader(JOLTAGE_ADAPTERS))) {
            String line;
            while ((line = br.readLine()) != null) {
                adapters.add(Integer.valueOf(line));
            }
            adapters.sort(Comparator.naturalOrder());
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    private static void addInitialAndFinalJoltage() {
        adapters.addFirst(STARTING_JOLTAGE);
        adapters.addLast(adapters.getLast() + MAX_JOLT_DIFFERENCE);
    }

    private static void initializeJoltDifferences() {
        for (int i = 1; i <= MAX_JOLT_DIFFERENCE; i++) {
            joltDifferences.put(i, 0);
        }
    }

    private static void countDifferences() {
        int referenceJoltage = STARTING_JOLTAGE;
        int difference = 0;
        for (int i = 1; i < adapters.size(); i++) {
            difference = adapters.get(i) - referenceJoltage;
            joltDifferences.put(difference, joltDifferences.get(difference) + 1);
            referenceJoltage = adapters.get(i);
        }
    }

    private static int calculateProductOf1JoltAnd3JoltsDifferences() {
        return joltDifferences.get(1) * joltDifferences.get(3);
    }
}

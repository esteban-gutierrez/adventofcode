package com.adventofcode.day10;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class AdapterArrangements {
    private static final String JOLTAGE_ADAPTERS = "resources/day_10_joltage_adapters_input.txt";
    private static final int MAX_JOLT_DIFFERENCE = 3;
    private static final int STARTING_JOLTAGE = 0;

    private static LinkedList<Integer> adapters = new LinkedList<>();

    public static void main(String[] args) {
        readAdaptersInOrder();
        addInitialAndFinalJoltage();

        int prevAdapter = 0;
        int s = 0; // sequence of adapters without gaps
        long d = 1L;

        for (int adapter: adapters) {
            if (adapter - prevAdapter == 1) {
                s += 1;
            } else {
                d *= Math.pow(2, Math.max(0, s - 1)) - Math.max(0, s - 3);
                s = 0;
            }
            System.out.println(adapter + "\t -> s=" + s + ", d=" + d);
            prevAdapter = adapter;
        }
        System.out.println(d);
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
}

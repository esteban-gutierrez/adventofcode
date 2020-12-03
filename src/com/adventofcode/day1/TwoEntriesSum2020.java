package com.adventofcode.day1;

import com.adventofcode.util.CommonUtils;

import java.util.Map;

import static com.adventofcode.util.CommonUtils.TARGET;

public class TwoEntriesSum2020 {
    public static void main (String [] args) {
        Map<Integer, Integer> numbers = CommonUtils.readPuzzleInput();
        for (Integer number : numbers.values()) {
            if (numbers.containsKey(TARGET - number)) {
                System.out.println(number + " + " + (TARGET - number) + " = " + TARGET);
                System.out.println(number + " * " + (TARGET - number) + " = " + (number * (TARGET - number)));
                break;
            }
        }

    }
}

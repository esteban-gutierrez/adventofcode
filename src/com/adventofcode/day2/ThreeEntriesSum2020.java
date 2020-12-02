package com.adventofcode.day2;

import com.adventofcode.util.CommonUtils;

import java.util.Map;

public class ThreeEntriesSum2020 {
    private static final Integer TARGET = 2020;

    public static void main (String [] args) {
        Map<Integer, Integer> numbers = CommonUtils.readPuzzleInput();
        Integer numberTwo, numberThree;
        for (Integer numberOne : numbers.keySet()) {
            Integer nextTarget = TARGET - numberOne;
            numberTwo = findNumber(numbers, nextTarget);
            if (numberTwo != null) {
                numberThree = nextTarget - numberTwo;
                System.out.println(numberOne + ", " + numberTwo + ", " + numberThree);
                System.out.println(numberOne + " * " + numberTwo + " * " + numberThree + " = " + (numberOne * numberTwo * numberThree));
                break;
            }
        }
    }

    private static Integer findNumber(Map<Integer, Integer> numbers, Integer target) {
        for (Integer number : numbers.values()) {
            if (numbers.containsKey(target - number)) {
                return (target - number);
            }
        }
        return null;
    }
}

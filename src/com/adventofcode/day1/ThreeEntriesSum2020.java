package com.adventofcode.day1;

import java.util.Map;

import static com.adventofcode.day1.UtilsDay1.TARGET;

public class ThreeEntriesSum2020 {
    public static void main (String [] args) {
        Map<Integer, Integer> numbers = UtilsDay1.readPuzzleInput();
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

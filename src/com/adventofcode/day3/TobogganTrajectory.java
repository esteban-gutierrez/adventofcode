package com.adventofcode.day3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class TobogganTrajectory {
    private static final String SQUARES_TREES_MAP = "resources/input_day_3.txt";
    private static final int HORIZONTAL_SLOPE = 3;
    private static final int VERTICAL_SLOPE = 1;

    public static void main (String [] args) {
        int size = countLines(SQUARES_TREES_MAP);
        int [][] squaresAndTreesMap = readSquaresAndTreesMap(size);
        int numberOfTrees = sumTreesInTobogganTrajectory(squaresAndTreesMap);
        System.out.println();
        System.out.println("Number of trees:" + numberOfTrees);
    }

    private static int countLines(String fileName) {
        int size = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            while ((br.readLine()) != null) {
                size++;
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return size;
    }

    private static int[][] readSquaresAndTreesMap(int size) {
        int [][] squaresAndTrees = new int[size][size];
        try (BufferedReader br = new BufferedReader(new FileReader(SQUARES_TREES_MAP))) {
            String line;
            int i = 0;
            while ((line = br.readLine()) != null) {
                squaresAndTrees[i] = readSquaresAndTreesInLine(line, size);
                i++;
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return squaresAndTrees;
    }

    private static int[] readSquaresAndTreesInLine(String line, int size) {
        int [] processedLine = new int[size];
        for (int i = 0; i < size; i++) {
            processedLine[i] = getValue(line.charAt(i % line.length()));
        }
        return processedLine;
    }

    private static int getValue(char geologyPoint) {
        if (geologyPoint == '#') {
            return 1;
        }
        return 0;
    }

    private static int sumTreesInTobogganTrajectory(int [][] squaresAndTreesMap) {
        int numberOfTrees = 0;
        int vertical = 0;
        int horizontal = 0;
        while (vertical < squaresAndTreesMap.length) {
            printLine(vertical, squaresAndTreesMap[vertical]);
            printGeologicalPoint(squaresAndTreesMap[vertical], horizontal);
            numberOfTrees += squaresAndTreesMap[vertical][horizontal];
            vertical += VERTICAL_SLOPE;
            horizontal = (horizontal + HORIZONTAL_SLOPE) % squaresAndTreesMap.length;
        }
        return numberOfTrees;
    }

    private static void printLine(int vertical, int [] line) {
        System.out.println();
        System.out.print(++vertical + "\t");
        for (int i = 0; i < line.length; i++) {
            System.out.print(line[i]);
        }
    }

    private static void printGeologicalPoint(int [] line, int horizontal) {
        System.out.print(" " + line[horizontal]);
    }
}

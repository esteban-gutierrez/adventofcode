package com.adventofcode.day3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class TobogganTrajectory {
    private static final String SQUARES_TREES_MAP = "resources/day_3_map_of_squares_and_trees.txt";
    private static final int HORIZONTAL_SLOPE = 3;
    private static final int VERTICAL_SLOPE = 1;

    public static void main (String [] args) {
        int rows = getNumberOfRows(SQUARES_TREES_MAP);
        int columns = getNumberOfColumns(SQUARES_TREES_MAP);
        int [][] squaresAndTreesMap = readSquaresAndTreesMap(rows, columns);
        int numberOfTrees = sumTreesInTobogganTrajectory(squaresAndTreesMap);
        System.out.println();
        System.out.println("Number of trees:" + numberOfTrees);
    }

    private static int getNumberOfRows(String fileName) {
        int rows = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            while ((br.readLine()) != null) {
                rows++;
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return rows;
    }

    private static int getNumberOfColumns(String fileName) {
        int columns = 0;
        String line;
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            line = br.readLine();
            columns = line.length();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return columns;
    }

    private static int[][] readSquaresAndTreesMap(int rows, int columns) {
        int [][] squaresAndTrees = new int[rows][columns];
        try (BufferedReader br = new BufferedReader(new FileReader(SQUARES_TREES_MAP))) {
            String line;
            int i = 0;
            while ((line = br.readLine()) != null) {
                squaresAndTrees[i] = readSquaresAndTreesInLine(line, columns);
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
        int row = 0;
        int column = 0;
        while (row < squaresAndTreesMap.length) {
            printLine(row, squaresAndTreesMap[row]);
            System.out.print(" " + squaresAndTreesMap[row][column]);
            numberOfTrees += squaresAndTreesMap[row][column];
            row += VERTICAL_SLOPE;
            column = (column + HORIZONTAL_SLOPE) % squaresAndTreesMap[0].length;
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
}

package com.adventofcode.day11;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class SeatingSystem {
    private static final String SEAT_LAYOUT = "resources/day_11_map_seat_layout.txt";
    private static final char OCCUPIED = '#';
    private static final char EMPTY = 'L';

    private static char[][] referenceSeats;
    private static char[][] newSeats;

    public static void main(String[] args) {
        int[] dimensions = getDimensions();
        referenceSeats = new char[dimensions[0]][dimensions[1]];
        newSeats = new char[dimensions[0]][dimensions[1]];
        readSeats();
        int changes;
        int iterations = 0;
        do {
            changes = updateSeats();
            referenceSeats = getNewSeats();
            iterations++;
        } while (changes > 0);

        System.out.println("Seats occupied: " + countAllOccupied() + " after " + (iterations - 1) + " iterations");
    }

    private static int[] getDimensions() {
        int rows = 0;
        int columns = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(SEAT_LAYOUT))) {
            String line;
            while ((line = br.readLine()) != null) {
                rows++;
                if (columns == 0) {
                    columns = line.length();
                }
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        System.out.println("Dimensions: " + rows + " x " + columns);
        return new int[] {rows, columns};
    }

    private static void readSeats() {
        try (BufferedReader br = new BufferedReader(new FileReader(SEAT_LAYOUT))) {
            String line;
            int row = 0;
            while ((line = br.readLine()) != null) {
                for (int i = 0; i < line.length(); i++) {
                    referenceSeats[row][i] = line.charAt(i);
                }
                row++;
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    private static void printSeats() {
        System.out.println();
        for (int i = 0; i < referenceSeats.length; i++) {
            for (int j = 0; j < referenceSeats[0].length; j++) {
                System.out.print(referenceSeats[i][j]);
            }
            System.out.println();
        }
    }

    private static int updateSeats() {
        int changes = 0;
        for (int i = 0; i < referenceSeats.length; i++) {
            for (int j = 0; j < referenceSeats[0].length; j++) {
                // If the seat is empty and there are no occupied seats adjacent to it, the seat becomes occupied
                if (isEmpty(i, j) && countOccupied(i, j) == 0) {
                    newSeats[i][j] = OCCUPIED;
                    changes++;

                // If the seat is occupied and four or more seats adjacent to it are also occupied, the seat becomes empty
                } else if (isOccupied(i, j) && countOccupied(i, j) >= 4) {
                    newSeats[i][j] = EMPTY;
                    changes++;
                } else {
                    newSeats[i][j] = referenceSeats[i][j];
                }
            }
        }
        return changes;
    }

    private static int countOccupied(int row, int column) {
        return countOccupiedAbove(row, column) + countOccupiedInline(row, column) + countOccupiedBelow(row, column);
    }

    private static int countOccupiedAbove(int row, int column) {
        int occupied = 0;
        if (row > 0) {
            if (column > 0 && isOccupied(row - 1, column - 1)) occupied++;
            if (isOccupied(row - 1, column)) occupied++;
            if (column < (referenceSeats[0].length - 1) && isOccupied(row - 1, column + 1)) occupied++;
        }
        return occupied;
    }

    private static int countOccupiedInline(int row, int column) {
        int occupied = 0;
        if (column > 0 && isOccupied(row, column - 1)) occupied++;
        if (column < (referenceSeats[0].length - 1) && isOccupied(row, column + 1)) occupied++;
        return occupied;
    }

    private static int countOccupiedBelow(int row, int column) {
        int occupied = 0;
        if (row < (referenceSeats.length - 1)) {
            if (column > 0 && isOccupied(row + 1, column - 1)) occupied++;
            if (isOccupied(row + 1, column)) occupied++;
            if (column < (referenceSeats[0].length - 1) && isOccupied(row + 1, column + 1)) occupied++;
        }
        return occupied;
    }

    private static boolean isEmpty(int row, int column) {
        return referenceSeats[row][column] == EMPTY;
    }

    private static boolean isOccupied(int row, int column) {
        return referenceSeats[row][column] == OCCUPIED;
    }

    private static int countAllOccupied() {
        int occupied = 0;
        for (int i = 0; i < referenceSeats.length; i++) {
            for (int j = 0; j < referenceSeats[0].length; j++) {
                if (isOccupied(i, j)) {
                    occupied++;
                }
            }
        }
        return occupied;
    }

    private static char[][] getNewSeats() {
        char[][] copy = new char[newSeats.length][newSeats[0].length];
        for (int i = 0; i < newSeats.length; i++) {
            for (int j = 0; j < newSeats[0].length; j++) {
                copy[i][j] = newSeats[i][j];
            }
        }
        return copy;
    }
}

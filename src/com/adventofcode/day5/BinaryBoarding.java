package com.adventofcode.day5;

import java.io.BufferedReader;
import java.io.FileReader;

public class BinaryBoarding {
    private static final String BOARDING_PASSES = "resources/boarding_passes_day_5.txt";

    public static void main (String [] args) {
        try (BufferedReader br = new BufferedReader(new FileReader(BOARDING_PASSES))) {
            String line;
            int biggestSeatId = 0;
            while ((line = br.readLine()) != null) {
                int binaryRow = transformRowIntoBinary(line.substring(0, 7));
                int binarySeat = transformSeatIntoBinary(line.substring(7));
                int seatId = (binaryRow * 8) + binarySeat;
                System.out.println(binaryRow + " " + binarySeat + " -> Seat ID = " + seatId);
                if (seatId > biggestSeatId) {
                    biggestSeatId = seatId;
                }
            }
            System.out.println("Biggest seat ID = " + biggestSeatId);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static int transformRowIntoBinary(String boardingPassRow) {
        String binaryRow = "";
        for (int i = 0; i < boardingPassRow.length(); i++) {
            if (boardingPassRow.charAt(i) == 'F') {
                binaryRow += "0";
            } else {
                binaryRow += "1";
            }
        }
        return Integer.parseInt(binaryRow, 2);
    }

    private static int transformSeatIntoBinary(String boardingPassSeat) {
        String binarySeat = "";
        for (int i = 0; i < boardingPassSeat.length(); i++) {
            if (boardingPassSeat.charAt(i) == 'L') {
                binarySeat += "0";
            } else {
                binarySeat += "1";
            }
        }
        return Integer.parseInt(binarySeat, 2);
    }
}

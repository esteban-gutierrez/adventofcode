package com.adventofcode.day5;

import java.io.BufferedReader;
import java.io.FileReader;

import static com.adventofcode.day5.UtilsDay5.transformRowIntoBinary;
import static com.adventofcode.day5.UtilsDay5.transformSeatIntoBinary;

public class BinaryBoarding {
    private static final String BOARDING_PASSES = "resources/day_5_boarding_passes.txt";

    public static void main (String [] args) {
        try (BufferedReader br = new BufferedReader(new FileReader(BOARDING_PASSES))) {
            String line;
            int biggestSeatId = 0;
            while ((line = br.readLine()) != null) {
                int binaryRow = transformRowIntoBinary(line.substring(0, 7));
                int binarySeat = transformSeatIntoBinary(line.substring(7));
                int seatId = (binaryRow * 8) + binarySeat;
                //System.out.println(binaryRow + " " + binarySeat + " -> Seat ID = " + seatId);
                if (seatId > biggestSeatId) {
                    biggestSeatId = seatId;
                }
            }
            System.out.println("Biggest seat ID = " + biggestSeatId);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

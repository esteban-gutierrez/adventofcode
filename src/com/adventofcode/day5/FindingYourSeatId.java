package com.adventofcode.day5;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FindingYourSeatId {
    private static final String BOARDING_PASSES = "resources/boarding_passes_day_5.txt";

    public static void main (String [] args) {
        try (BufferedReader br = new BufferedReader(new FileReader(BOARDING_PASSES))) {
            String line;
            List<Integer> seatIds = new ArrayList<>();
            while ((line = br.readLine()) != null) {
                int binaryRow = transformRowIntoBinary(line.substring(0, 7));
                int binarySeat = transformSeatIntoBinary(line.substring(7));
                int seatId = (binaryRow * 8) + binarySeat;
                seatIds.add(seatId);
            }
            Object [] orderedSeatIds = seatIds.toArray();
            Arrays.sort(orderedSeatIds);
            List<Integer> missingSeatIds = new ArrayList<>();
            int previousSeatId = (int) orderedSeatIds[0];
            for (int i = 1; i < orderedSeatIds.length; i++) {
                int currentSeatId = (int) orderedSeatIds[i];
                if (currentSeatId > (previousSeatId + 1)) {
                    missingSeatIds.add(currentSeatId - 1);
                }
                previousSeatId = currentSeatId;
            }
            System.out.println("Missing seat Ids = " + missingSeatIds);

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

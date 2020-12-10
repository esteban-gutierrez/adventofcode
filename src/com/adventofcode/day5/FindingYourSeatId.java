package com.adventofcode.day5;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.adventofcode.day5.UtilsDay5.transformRowIntoBinary;
import static com.adventofcode.day5.UtilsDay5.transformSeatIntoBinary;

public class FindingYourSeatId {
    private static final String BOARDING_PASSES = "resources/day_5_boarding_passes.txt";

    public static void main (String [] args) {
        try (BufferedReader br = new BufferedReader(new FileReader(BOARDING_PASSES))) {
            List<Integer> seatIds = readSeatIds(br);
            Object[] orderedSeatIds = orderSeatIds(seatIds);
            List<Integer> missingSeatIds = getMissingSeatIds(orderedSeatIds);
            System.out.println("Missing seat Ids = " + missingSeatIds);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static List<Integer> readSeatIds(BufferedReader br) throws IOException {
        String line;
        List<Integer> seatIds = new ArrayList<>();
        while ((line = br.readLine()) != null) {
            int binaryRow = transformRowIntoBinary(line.substring(0, 7));
            int binarySeat = transformSeatIntoBinary(line.substring(7));
            int seatId = (binaryRow * 8) + binarySeat;
            seatIds.add(seatId);
        }
        return seatIds;
    }

    private static Object[] orderSeatIds(List<Integer> seatIds) {
        Object [] orderedSeatIds = seatIds.toArray();
        Arrays.sort(orderedSeatIds);
        return orderedSeatIds;
    }

    private static List<Integer> getMissingSeatIds(Object[] orderedSeatIds) {
        List<Integer> missingSeatIds = new ArrayList<>();
        int previousSeatId = (int) orderedSeatIds[0];
        for (int i = 1; i < orderedSeatIds.length; i++) {
            int currentSeatId = (int) orderedSeatIds[i];
            if (currentSeatId > (previousSeatId + 1)) {
                missingSeatIds.add(currentSeatId - 1);
            }
            previousSeatId = currentSeatId;
        }
        return missingSeatIds;
    }
}

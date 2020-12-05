package com.adventofcode.day5;

public class UtilsDay5 {
    public static int transformRowIntoBinary(String boardingPassRow) {
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

    public static int transformSeatIntoBinary(String boardingPassSeat) {
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

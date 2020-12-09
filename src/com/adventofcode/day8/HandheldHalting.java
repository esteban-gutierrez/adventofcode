package com.adventofcode.day8;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HandheldHalting {
    private static final String BOOT_CODE = "resources/boot_code_day_8.txt";
    private static final int OFFSET_NEXT_INSTRUCTION = 1;
    private static List<Instruction> instructionList = new ArrayList<>();
    private static List<Integer> instructionPosition = new ArrayList<>();
    private static int accumulator = 0;

    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new FileReader(BOOT_CODE))) {
            String line;
            while ((line = br.readLine()) != null) {
                instructionList.add(readInstruction(line));
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        readBootCode(0);
        System.out.println("Accumulator: " + accumulator);
    }

    private static Instruction readInstruction(String line) {
        Instruction instruction = new Instruction();
        String [] instructionParts = line.split(" ");
        instruction.setOperation(Instruction.Operation.valueOf(instructionParts[0]));
        instruction.setOffset(Integer.valueOf(instructionParts[1]));
        System.out.println(instruction);
        return instruction;
    }

    private static void readBootCode(int position) {
        while (!instructionPosition.contains(position)) {
            instructionPosition.add(position);
            position += processInstruction(instructionList.get(position));
        }
    }

    private static int processInstruction(Instruction instruction) {
        switch (instruction.getOperation()) {
            case acc:
                accumulator += instruction.getOffset();
                return OFFSET_NEXT_INSTRUCTION;
            case nop:
                return OFFSET_NEXT_INSTRUCTION;
            default:
                return instruction.getOffset();
        }
    }
}

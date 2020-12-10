package com.adventofcode.day8;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.adventofcode.day8.Instruction.Operation.*;

public class RepairBootCode {
    private static final String BOOT_CODE = "resources/day_8_boot_code.txt";
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
        readFixedBootCode(0);
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

    private static int readBootCode(Integer positionToChange) {
        System.out.println();
        accumulator = 0;
        int position = 0;
        instructionPosition = new ArrayList<>();
        while (!instructionPosition.contains(position)
                && position < instructionList.size()) {
            instructionPosition.add(position);
            if (positionToChange == position) {
                System.out.println(position + " (reversed) -> " + reverseInstructionOperation(instructionList.get(position)));
                position += processInstruction(reverseInstructionOperation(instructionList.get(position)));
            } else {
                System.out.println(position + " -> " + instructionList.get(position));
                position += processInstruction(instructionList.get(position));
            }
        }
        if (position == instructionList.size()) {
            System.out.println("Position of wrong operation: " + positionToChange);
        }
        return position;
    }

    private static int processInstruction(Instruction instruction) {
        switch (instruction.getOperation()) {
            case acc:
                accumulator += instruction.getOffset();
                return OFFSET_NEXT_INSTRUCTION;
            case nop:
                return OFFSET_NEXT_INSTRUCTION;
            default:
                if (instruction.getOffset() == 0) {
                    return OFFSET_NEXT_INSTRUCTION;
                }
                return instruction.getOffset();
        }
    }

    private static Instruction reverseInstructionOperation(Instruction instruction) {
        Instruction reversed = new Instruction();
        reversed.setOffset(instruction.getOffset());
        if (jmp == instruction.getOperation()) {
            reversed.setOperation(nop);
        } else if (nop == instruction.getOperation()) {
            reversed.setOperation(jmp);
        } else {
            reversed.setOperation(acc);
        }
        return reversed;
    }

    private static int readFixedBootCode(int position) {
        int positionOfWrongOperation = readBootCode(position);
        if (positionOfWrongOperation == instructionList.size()) {
            return positionOfWrongOperation;
        } else {
            return readFixedBootCode(position + 1);
        }
    }
}
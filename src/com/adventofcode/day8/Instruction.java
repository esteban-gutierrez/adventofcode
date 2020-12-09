package com.adventofcode.day8;

public class Instruction {
    enum Operation {
        acc, jmp, nop
    }

    private Operation operation;
    private int offset;

    public Operation getOperation() {
        return operation;
    }

    public void setOperation(Operation operation) {
        this.operation = operation;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public String toString() {
        return "Operation=" + operation + ", offset=" + offset;
    }
}

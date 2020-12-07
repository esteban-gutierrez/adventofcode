package com.adventofcode.day7;

public class BagContent {
    private int quantity;
    private String bagColour;

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getBagColour() {
        return bagColour;
    }

    public void setBagColour(String bagColour) {
        this.bagColour = bagColour;
    }

    public String toString() {
        return "Quantity=" + getQuantity() + " and colour=" + getBagColour();
    }
}

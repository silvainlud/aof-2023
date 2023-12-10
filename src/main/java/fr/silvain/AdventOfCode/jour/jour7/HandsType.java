package fr.silvain.AdventOfCode.jour.jour7;

public enum HandsType {
    FIVE_OF_A_KIND(1),
    FOUR_OF_A_KIND(2),
    FULL_HOUSE(3),
    THREE_OF_A_KIND(4),
    TWO_PAIRS(5),
    PAIR(6),
    HIGH_CARD(7);

    private final int value;

    HandsType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}

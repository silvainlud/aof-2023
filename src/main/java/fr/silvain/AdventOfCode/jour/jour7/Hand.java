package fr.silvain.AdventOfCode.jour.jour7;

import org.apache.commons.lang3.ArrayUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class Hand implements Comparable<Hand> {
    public static char[] CARDS_VALUES = new char[]{'2', '3', '4', '5', '6', '7', '8', '9', 'T', 'J', 'Q', 'K', 'A'};
    private final char[] hands;
    private final Map<Character, Integer> countCards;
    private final Map<Character, Integer> countCardsWithoutJoker;
    private double nbJoker;
    private final double power;

    private boolean allowJoker = false;

    public Hand(char[] hands, double power) {
        this.hands = hands;
        countCards = countCards(hands);
        countCardsWithoutJoker = countCards.entrySet().stream().filter(x -> x.getKey() != 'J').collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        nbJoker = countCards.getOrDefault('J', 0);
        this.power = power;
    }

    public Hand setAllowJoker(boolean allowJoker) {
        this.allowJoker = allowJoker;
        return this;
    }

    public Hand(String hands) {
        this(hands.toCharArray(), 0);
    }

    public Hand(String hands, double power) {
        this(hands.toCharArray(), power);
    }

    public double getPower() {
        return power;
    }

    public char[] getHands() {
        return hands;
    }

    public static Map<Character, Integer> countCards(char[] hands) {
        Map<Character, Integer> result = new HashMap<>();
        for (char hand : hands) {
            result.put(hand, result.getOrDefault(hand, 0) + 1);
        }
        return result;
    }

    public boolean isFiveOfAKind() {
        if (allowJoker) {
            return countCardsWithoutJoker.size() == 1 || nbJoker == hands.length;
        }
        return countCards.size() == 1;
    }

    public boolean isFourOfAKind() {
        boolean res = countCards.size() == 2
                && countCards.values().stream().anyMatch(i -> i == 4)
                && countCards.values().stream().anyMatch(i -> i == 1);
        if (!res && allowJoker) {
            res = countCardsWithoutJoker.size() == 2
                    && countCardsWithoutJoker.values().stream().anyMatch(i -> i + nbJoker == 4);
        }
        return res;
    }

    public boolean isFullHouse() {
        if (allowJoker) {
            return countCardsWithoutJoker.size() == 2 && ((
                    countCardsWithoutJoker.values().stream().anyMatch(i -> i + nbJoker == 3)
                            && countCardsWithoutJoker.values().stream().anyMatch(i -> i == 2)
            ) || (
                    countCardsWithoutJoker.values().stream().anyMatch(i -> i == 3)
                            && countCardsWithoutJoker.values().stream().anyMatch(i -> i + nbJoker == 2)
            ));
        }
        return countCards.size() == 2
                && countCards.values().stream().anyMatch(i -> i == 3)
                && countCards.values().stream().anyMatch(i -> i == 2);
    }

    public boolean isThreeOfAKind() {
        Boolean res = countCards.size() == 3
                && countCards.values().stream().anyMatch(i -> i == 3)
                && countCards.values().stream().filter(i -> i == 1).count() == 2;
        if (!res && allowJoker) {
            res = countCardsWithoutJoker.size() == 3
                    && countCardsWithoutJoker.values().stream().anyMatch(i -> i + nbJoker == 3)
                    && countCardsWithoutJoker.values().stream().filter(i -> i == 1).count() >= 2;
        }
        return res;
    }

    public boolean isTwoPairs() {
        Boolean res =  countCards.size() == 3
                && countCards.values().stream().filter(i -> i == 2).count() == 2
                && countCards.values().stream().filter(i -> i == 1).count() == 1;
        if (!res && allowJoker) {
            res = countCardsWithoutJoker.size() == 3
                    && countCardsWithoutJoker.values().stream().filter(i -> i + nbJoker == 2).count() == 1
                    && countCardsWithoutJoker.values().stream().filter(i -> i == 2).count() == 1
                    && countCardsWithoutJoker.values().stream().filter(i -> i == 1).count() == 1;
        }
        return res;
    }

    public boolean isPair() {
        Boolean res = countCards.size() == 4
                && countCards.values().stream().filter(i -> i == 2).count() == 1
                && countCards.values().stream().filter(i -> i == 1).count() == 3;
        if (!res && allowJoker) {
            if (countCardsWithoutJoker.size() == 4) {
                res = true;
            }
        }
        return res;
    }

    public boolean isHighCard() {
        return countCards.size() == 5;
    }

    public HandsType getHandsType() {
        if (isFiveOfAKind()) {
            return HandsType.FIVE_OF_A_KIND;
        } else if (isFourOfAKind()) {
            return HandsType.FOUR_OF_A_KIND;
        } else if (isFullHouse()) {
            return HandsType.FULL_HOUSE;
        } else if (isThreeOfAKind()) {
            return HandsType.THREE_OF_A_KIND;
        } else if (isTwoPairs()) {
            return HandsType.TWO_PAIRS;
        } else if (isPair()) {
            return HandsType.PAIR;
        } else {
            return HandsType.HIGH_CARD;
        }
    }

    @Override
    public int compareTo(Hand o) {
        if (this.getHandsType().getValue() == o.getHandsType().getValue()) {
            return compareSameType(o);
        } else {
            return (o.getHandsType().getValue() - this.getHandsType().getValue());
        }
    }

    private int compareSameType(Hand o) {
        for (int i = 0; i < 5; i++) {
            char thisCard = this.hands[i];
            char oCard = o.hands[i];
            if (Objects.equals(thisCard, oCard)) {
                continue;
            }
            int powerThisCard = ArrayUtils.indexOf(CARDS_VALUES, thisCard);
            int powerOCard = ArrayUtils.indexOf(CARDS_VALUES, oCard);
            return (powerThisCard - powerOCard);
        }
        return 0;
    }
}

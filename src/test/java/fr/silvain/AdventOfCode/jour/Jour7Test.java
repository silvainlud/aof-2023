package fr.silvain.AdventOfCode.jour;

import fr.silvain.AdventOfCode.JourTest;
import fr.silvain.AdventOfCode.jour.jour7.Hand;
import fr.silvain.AdventOfCode.jour.jour7.HandsType;
import fr.silvain.AdventOfCode.jour.jour7.Jour7;
import org.junit.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class Jour7Test extends JourTest<Jour7> {
    @Override
    protected Jour7 instanceNewJour() {
        return new Jour7();
    }

    @Override
    @Test
    public void testPartie1() {
        this.partie1("6440", List.of());
    }

    @Override
    @Test
    public void testPartie2() {
        this.partie2("5905", List.of("253613643", "253749556"));
    }

    @Override
    @Test
    public void validatePartie1() {
        assertTrue(new Hand("AAAAA").isFiveOfAKind());
        assertFalse(new Hand("AABAA").isFiveOfAKind());
        assertTrue(new Hand("AABAA").isFourOfAKind());
        assertFalse(new Hand("AAAAA").isFourOfAKind());
        assertFalse(new Hand("ABBAA").isFourOfAKind());
        assertTrue(new Hand("23332").isFullHouse());
        assertFalse(new Hand("AAAAA").isFullHouse());
        assertFalse(new Hand("AABAA").isFullHouse());
        assertFalse(new Hand("23333").isFullHouse());
        assertTrue(new Hand("TTT98").isThreeOfAKind());
        assertFalse(new Hand("TTT99").isThreeOfAKind());
        assertFalse(new Hand("CTT98").isThreeOfAKind());
        assertFalse(new Hand("AAAAA").isThreeOfAKind());
        assertTrue(new Hand("23432").isTwoPairs());
        assertFalse(new Hand("23332").isTwoPairs());
        assertTrue(new Hand("A23A4").isPair());
        assertFalse(new Hand("23333").isPair());
        assertTrue(new Hand("23456").isHighCard());
        assertFalse(new Hand("23333").isHighCard());
        assertFalse(new Hand("TTT99").isHighCard());

        assertEquals(HandsType.FIVE_OF_A_KIND, new Hand("AAAAA").getHandsType());
        assertEquals(HandsType.FOUR_OF_A_KIND, new Hand("AABAA").getHandsType());
        assertEquals(HandsType.FULL_HOUSE, new Hand("23332").getHandsType());
        assertEquals(HandsType.THREE_OF_A_KIND, new Hand("TTT98").getHandsType());
        assertEquals(HandsType.TWO_PAIRS, new Hand("23432").getHandsType());
        assertEquals(HandsType.PAIR, new Hand("A23A4").getHandsType());
        assertEquals(HandsType.HIGH_CARD, new Hand("23456").getHandsType());

        // Compare
        assertTrue(new Hand("AAAAA").compareTo(new Hand("KKKKK")) > 0);
        assertTrue(new Hand("KKKKK").compareTo(new Hand("AAAAA")) < 0);
        assertTrue(new Hand("AAAAA").compareTo(new Hand("AAAAA")) == 0);
        assertTrue(new Hand("AAAAA").compareTo(new Hand("KKKKQ")) > 0);
        assertTrue(new Hand("KKKKQ").compareTo(new Hand("AAAAA")) < 0);


    }

    @Override
    @Test
    public void validatePartie2() {
        assertEquals(HandsType.FIVE_OF_A_KIND, new Hand("JJJJJ").setAllowJoker(true).getHandsType());
        assertEquals(HandsType.THREE_OF_A_KIND, new Hand("JJ456").setAllowJoker(true).getHandsType());
        assertEquals(HandsType.FOUR_OF_A_KIND, new Hand("JJJ45").setAllowJoker(true).getHandsType());
        assertEquals(HandsType.THREE_OF_A_KIND, new Hand("TJJ98").setAllowJoker(true).getHandsType());

        assertEquals(HandsType.FIVE_OF_A_KIND, new Hand("AAAAA").setAllowJoker(true).getHandsType());
        assertEquals(HandsType.FOUR_OF_A_KIND, new Hand("AABAA").setAllowJoker(true).getHandsType());
        assertEquals(HandsType.FULL_HOUSE, new Hand("23332").setAllowJoker(true).getHandsType());
        assertEquals(HandsType.THREE_OF_A_KIND, new Hand("TTT98").setAllowJoker(true).getHandsType());
        assertEquals(HandsType.TWO_PAIRS, new Hand("23432").setAllowJoker(true).getHandsType());
        assertEquals(HandsType.PAIR, new Hand("A23A4").setAllowJoker(true).getHandsType());
        assertEquals(HandsType.HIGH_CARD, new Hand("23456").setAllowJoker(true).getHandsType());

        assertEquals(HandsType.FIVE_OF_A_KIND, new Hand("QJJQJ").setAllowJoker(true).getHandsType());
        assertEquals(HandsType.FOUR_OF_A_KIND, new Hand("QJJQ2").setAllowJoker(true).getHandsType());
        assertEquals(HandsType.FULL_HOUSE, new Hand("23J32").setAllowJoker(true).getHandsType());
        assertEquals(HandsType.THREE_OF_A_KIND, new Hand("TTJ98").setAllowJoker(true).getHandsType());
        assertEquals(HandsType.PAIR, new Hand("2345J").setAllowJoker(true).getHandsType());
        assertEquals(HandsType.FOUR_OF_A_KIND, new Hand("KQJQQ").setAllowJoker(true).getHandsType());
        assertEquals(HandsType.FOUR_OF_A_KIND, new Hand("J7772").setAllowJoker(true).getHandsType());
        assertEquals(HandsType.FIVE_OF_A_KIND, new Hand("4J444").setAllowJoker(true).getHandsType());
        assertEquals(HandsType.THREE_OF_A_KIND, new Hand("55JQA").setAllowJoker(true).getHandsType());
        assertEquals(HandsType.FOUR_OF_A_KIND, new Hand("JKJ9K").setAllowJoker(true).getHandsType());
        assertEquals(HandsType.THREE_OF_A_KIND, new Hand("6659J").setAllowJoker(true).getHandsType());
        assertEquals(HandsType.FIVE_OF_A_KIND, new Hand("AAAAJ").setAllowJoker(true).getHandsType());
        assertEquals(HandsType.PAIR, new Hand("J56K2").setAllowJoker(true).getHandsType());
        assertEquals(HandsType.FOUR_OF_A_KIND, new Hand("JAAJ3").setAllowJoker(true).getHandsType());

    }
}

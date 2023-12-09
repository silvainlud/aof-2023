package fr.silvain.AdventOfCode.jour.jour4;

import fr.silvain.AdventOfCode.Jour;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.commons.lang3.tuple.Triple;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Jour4 extends Jour {
    public List<Integer> makeList(String in) {
        return Arrays.stream(in.trim().split(" "))
                .filter(s -> !s.isBlank())
                .map(Integer::parseInt).toList();
    }

    public Triple<Integer, List<Integer>, List<Integer>> parseCard(String in) {
        String[] split = in.split(":");
        String[] values = split[1].split("\\|");
        return Triple.of(Integer.parseInt(split[0].replace("Card", "").trim()), makeList(values[0]), makeList(values[1]));
    }

    public List<Integer> getVictorious(List<Integer> deck1, List<Integer> deck2) {
        return deck2.stream().filter(deck1::contains).toList();
    }

    public int getScore(List<Integer> deck) {
        if (deck.isEmpty()) return 0;
        return (int) Math.pow(2, deck.size() - 1);
    }

    @Override
    public String executePartie1(List<String> in) {
        int sum = in.stream().map(this::parseCard)
                .map(p -> getVictorious(p.getMiddle(), p.getRight()))
                .map(this::getScore)
                .mapToInt(Integer::intValue)
                .sum();
        return String.valueOf(sum);
    }

    @Override
    public String executePartie2(List<String> in) {
        List<Triple<Integer, List<Integer>, List<Integer>>> cards = in.stream().map(this::parseCard).toList();
        List<Triple<Integer, List<Integer>, List<Integer>>> playingsCards = new ArrayList<>(cards);

        HashMap<Integer, Integer> cache = new HashMap<>();
        int score = 0;
        for (int i = cards.size() - 1; i >= 0; i--) {
            Triple<Integer, List<Integer>, List<Integer>> card = playingsCards.get(i);
            int nbVictorious = getVictorious(card.getMiddle(), card.getRight()).size();
            int localScore = 1;
            for (int j = 0; j < nbVictorious; j++) {
                int finalJ = j + card.getLeft() + 1;
                Triple<Integer, List<Integer>, List<Integer>> findCard = playingsCards
                        .stream()
                        .filter(c -> c.getLeft() == finalJ)
                        .findFirst()
                        .orElseThrow();

                localScore += cache.get(findCard.getLeft());
            }
            score += localScore;
            cache.put(card.getLeft(), localScore);
        }

        return String.valueOf(score);
    }
}

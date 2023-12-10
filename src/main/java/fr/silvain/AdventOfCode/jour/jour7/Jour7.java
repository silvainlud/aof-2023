package fr.silvain.AdventOfCode.jour.jour7;

import fr.silvain.AdventOfCode.Jour;

import java.text.DecimalFormat;
import java.util.List;

public class Jour7 extends Jour {
    @Override
    public String executePartie1(List<String> in) {
        List<Hand> hands = in.stream().map(x -> x.split(" ")).map(x -> new Hand(x[0], Double.parseDouble(x[1])))
                .sorted().toList();
        double result = 0;
        for (int i = 0; i < hands.size(); i++) {
            result += hands.get(i).getPower() * (i + 1);
        }
        return (new DecimalFormat("#")).format(result);
    }

    @Override
    public String executePartie2(List<String> in) {
        Hand.CARDS_VALUES = new char[]{'J', '2', '3', '4', '5', '6', '7', '8', '9', 'T', 'Q', 'K', 'A'};
        List<Hand> hands = in.stream().map(x -> x.split(" ")).map(x -> new Hand(x[0], Double.parseDouble(x[1])))
                .map(x -> x.setAllowJoker(true))
                .sorted().toList();
        double result = 0;
        for (int i = 0; i < hands.size(); i++) {
            result += hands.get(i).getPower() * (i + 1);
        }
        return (new DecimalFormat("#")).format(result);
    }
}

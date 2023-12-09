package fr.silvain.AdventOfCode.jour;

import fr.silvain.AdventOfCode.Jour;
import fr.silvain.AdventOfCode.JourTest;
import fr.silvain.AdventOfCode.jour.jour3.Jour3;
import fr.silvain.AdventOfCode.jour.jour3.Position;
import fr.silvain.AdventOfCode.jour.jour3.Segment;
import org.apache.commons.lang3.tuple.Pair;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.util.List;

public class Jour3Test extends JourTest<Jour3> {
    private final String RESULTAT_ATTENDU_PARTIE_1 = "4361";
    private final List<String> RESULTAT_INCORRECTS_PARTIE_1 = List.of();
    private final String RESULTAT_ATTENDU_PARTIE_2 = "467835";
    private final List<String> RESULTAT_INCORRECTS_PARTIE_2 = List.of();


    @Override
    protected Jour3 instanceNewJour() {
        return new Jour3();
    }

    @Override
    @Test
    public void testPartie1() {

        this.partie1(RESULTAT_ATTENDU_PARTIE_1, RESULTAT_INCORRECTS_PARTIE_1);
    }

    @Override
    @Test
    public void testPartie2() {
        this.partie2(RESULTAT_ATTENDU_PARTIE_2, RESULTAT_INCORRECTS_PARTIE_2);
    }

    @Override
    @Test
    public void validatePartie1() {
        Position end = getJour().getEndPosition(new Position(4, 2), "..35..633.");
        Assertions.assertEquals(4, end.getLine());
        Assertions.assertEquals(3, end.getPosition());
        end = getJour().getEndPosition(new Position(4, 6), "..35..633.");
        Assertions.assertEquals(4, end.getLine());
        Assertions.assertEquals(8, end.getPosition());
        end = getJour().getEndPosition(new Position(4, 6), "..35..67");
        Assertions.assertEquals(4, end.getLine());
        Assertions.assertEquals(7, end.getPosition());

        Pair<List<Position>, List<Segment>> segmentsSymbols = getJour().getSegmentsAndSymbols(4, "..35..633.");
        Assertions.assertEquals(0, segmentsSymbols.getLeft().size());
        Assertions.assertEquals(2, segmentsSymbols.getRight().size());
        segmentsSymbols = getJour().getSegmentsAndSymbols(4, "...+..35..633.");
        Assertions.assertEquals(1, segmentsSymbols.getLeft().size());
        Assertions.assertEquals(2, segmentsSymbols.getRight().size());
        Assertions.assertEquals(4, segmentsSymbols.getLeft().get(0).getLine());
        Assertions.assertEquals(3, segmentsSymbols.getLeft().get(0).getPosition());


        Segment segment = new Segment(new Position(0, 0), new Position(0, 2));
        List<Position> symbols = List.of(new Position(1, 3));
        Assertions.assertTrue(getJour().hasSymbolsAdjcacent(segment, symbols));
        symbols = List.of(new Position(1, 4));
        Assertions.assertFalse(getJour().hasSymbolsAdjcacent(segment, symbols));
        symbols = List.of(new Position(1, 0));
        Assertions.assertTrue(getJour().hasSymbolsAdjcacent(segment, symbols));
        symbols = List.of(new Position(2, 0));
        Assertions.assertFalse(getJour().hasSymbolsAdjcacent(segment, symbols));
        symbols = List.of(new Position(0, 3));
        Assertions.assertTrue(getJour().hasSymbolsAdjcacent(segment, symbols));
        segment = new Segment(new Position(0, 1), new Position(0, 3));
        symbols = List.of(new Position(0, 0));
        Assertions.assertTrue(getJour().hasSymbolsAdjcacent(segment, symbols));
        segment = new Segment(new Position(1, 1), new Position(1, 3));
        symbols = List.of(new Position(0, 0));
        Assertions.assertTrue(getJour().hasSymbolsAdjcacent(segment, symbols));

    }

    @Override
    @Test
    public void validatePartie2() {

    }
}

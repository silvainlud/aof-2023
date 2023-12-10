package fr.silvain.AdventOfCode.jour;

import fr.silvain.AdventOfCode.JourTest;
import fr.silvain.AdventOfCode.jour.jour5.Jour5;
import fr.silvain.AdventOfCode.jour.jour5.SeedMapRange;
import org.apache.commons.lang3.tuple.Pair;
import org.junit.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class Jour5Test extends JourTest<Jour5> {
    @Override
    protected Jour5 instanceNewJour() {
        return new Jour5();
    }

    @Override
    @Test
    public void testPartie1() {
        this.partie1("35", List.of());
    }

    @Override
    @Test
    public void testPartie2() {
        this.partie2("46", List.of());
    }

    @Override
    @Test
    public void validatePartie1() {
        List<Double> seeds = getJour().getSeeds(List.of("seeds: 1 2 3 4 5"));
        assertEquals(5, seeds.size());
        Integer index = 3;
        Pair<Integer, List<SeedMapRange>> oneSeedRanges = getJour().getSeedRangesOfMap(List.of(
                "seeds: 79 14 55 13",
                "",
                "seed-to-soil map:",
                "50 98 2",
                "52 50 48",
                ""
        ), index);
        assertEquals(2, oneSeedRanges.getRight().size());
        assertEquals(5, oneSeedRanges.getKey());

        Map<Pair<String, String>, List<SeedMapRange>> seedRanges = getJour().getSeedRanges(List.of(
                "seeds: 79 14 55 13",
                "",
                "seed-to-soil map:",
                "50 98 2",
                "52 50 48"
        ));

        assertEquals(1, seedRanges.size());
        assertEquals(2, seedRanges.get(Pair.of("seed","soil")).size());
        assertEquals(50, seedRanges.get(Pair.of("seed","soil")).get(0).getDestinationStart());
        assertEquals(98, seedRanges.get(Pair.of("seed","soil")).get(0).getSourceStart());
        assertEquals(2, seedRanges.get(Pair.of("seed","soil")).get(0).getRangeLength());
        assertEquals(52, seedRanges.get(Pair.of("seed","soil")).get(1).getDestinationStart());
        assertEquals(50, seedRanges.get(Pair.of("seed","soil")).get(1).getSourceStart());
        assertEquals(48, seedRanges.get(Pair.of("seed","soil")).get(1).getRangeLength());

        SeedMapRange seedMapRange = new SeedMapRange(50.0, 98.0, 2.0);
        assertTrue(seedMapRange.isInTheSourceRange(98));
        assertTrue(seedMapRange.isInTheSourceRange(98));
        assertFalse(seedMapRange.isInTheSourceRange(100));
        assertFalse(seedMapRange.isInTheSourceRange(50));
        assertFalse(seedMapRange.isInTheSourceRange(51));
        assertTrue(seedMapRange.isInTheDestinationRange(50));
        assertTrue(seedMapRange.isInTheDestinationRange(51));
        assertFalse(seedMapRange.isInTheDestinationRange(52));
        assertFalse(seedMapRange.isInTheDestinationRange(49));
        assertEquals(50, seedMapRange.getMapFromSeed(98));
        assertEquals(51, seedMapRange.getMapFromSeed(99));
        assertEquals(97, seedMapRange.getMapFromSeed(97));
        assertEquals(100, seedMapRange.getMapFromSeed(100));

        List<SeedMapRange> seedMapRanges1 = List.of(
                new SeedMapRange(50.0, 98.0, 2.0),
                new SeedMapRange(52.0, 50.0, 48.0)
        );
        assertEquals(81, getJour().getMapFromSeed(79, seedMapRanges1));
        assertEquals(14, getJour().getMapFromSeed(14, seedMapRanges1));
        assertEquals(57, getJour().getMapFromSeed(55, seedMapRanges1));
        assertEquals(13, getJour().getMapFromSeed(13, seedMapRanges1));

    }

    @Override
    public void validatePartie2() {

    }
}

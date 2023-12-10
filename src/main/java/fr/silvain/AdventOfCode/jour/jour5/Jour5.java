package fr.silvain.AdventOfCode.jour.jour5;

import fr.silvain.AdventOfCode.Jour;
import org.apache.commons.lang3.tuple.Pair;

import java.util.*;

public class Jour5 extends Jour {

    public List<Double> getSeeds(List<String> in) {
        return in.stream().filter(x -> x.startsWith("seeds:"))
                .map(x -> x.replace("seeds:", ""))
                .map(x -> x.trim().split(" "))
                .map(x -> Arrays.stream(x).map(Double::parseDouble).toList())
                .reduce(new ArrayList<>(), (acc, x) -> {
                    acc.addAll(x);
                    return acc;
                }, (acc, acc2) -> {
                    acc.addAll(acc2);
                    return acc;
                });
    }

    public Pair<Integer, List<SeedMapRange>> getSeedRangesOfMap(List<String> in, Integer i) {
        List<SeedMapRange> seedMapRanges = new ArrayList<>();
        for (; i < in.size(); i++) {
            String line = in.get(i);
            if (line.trim().isEmpty()) {
                break;
            }
            String[] split = line.split(" ");
            Double destinationRangeStart = Double.parseDouble(split[0]);
            Double destinationRangeEnd = Double.parseDouble(split[1]);
            Double rangeLength = Double.parseDouble(split[2]);
            seedMapRanges.add(new SeedMapRange(destinationRangeStart, destinationRangeEnd, rangeLength));

        }

        return Pair.of(i, seedMapRanges);
    }

    public double getMapFromSeed(double seed, List<SeedMapRange> seedMapRanges) {
        for (SeedMapRange seedMapRange : seedMapRanges) {
            if (seedMapRange.isInTheSourceRange(seed)) {
                return seedMapRange.getMapFromSeed(seed);
            }
        }
        return seed;
    }

    public Map<Pair<String, String>, List<SeedMapRange>> getSeedRanges(List<String> in) {
        HashMap<Pair<String, String>, List<SeedMapRange>> seedRanges = new HashMap<>();
        for (int i = 0; i < in.size(); i++) {
            String line = in.get(i);
            if (line.startsWith("seeds:") || line.trim().isEmpty()) {
                continue;
            }
            String[] entryName = line.split(" ")[0].split("-");
            Pair<Integer, List<SeedMapRange>> seedRangesOfMap = getSeedRangesOfMap(in, i + 1);
            i = seedRangesOfMap.getLeft();
            seedRanges.put(Pair.of(entryName[0], entryName[2]), seedRangesOfMap.getRight());
        }
        return seedRanges;
    }

    public double getLocationFromSeed(double seed, Map<Pair<String, String>, List<SeedMapRange>> seedRanges) {
        String source = "seed";
        while (!source.equals("location")) {
            String finalSource = source;
            Map.Entry<Pair<String, String>, List<SeedMapRange>> seedRanges1 = seedRanges.entrySet()
                    .stream()
                    .filter(x -> x.getKey().getLeft().equals(finalSource))
                    .findFirst().orElseThrow();
            seed = getMapFromSeed(seed, seedRanges1.getValue());
            source = seedRanges1.getKey().getRight();
        }
        return seed;
    }

    public Pair<String, List<SeedRange>> map(List<SeedRange> seeds, Map<Pair<String, String>, List<SeedMapRange>> seedRanges, String source) {
        Map.Entry<Pair<String, String>, List<SeedMapRange>> seedMaps = seedRanges.entrySet()
                .stream()
                .filter(x -> x.getKey().getLeft().equals(source))
                .findFirst().orElseThrow();

        List<SeedRange> result = new ArrayList<>();

        Collections.sort(seedMaps.getValue());
        for (SeedRange seed : seeds) {
            final double endRange = seed.getEnd();
            double currentStart = seed.getStart();
            for (int i = 0; i < seedMaps.getValue().size(); i++) {
                SeedMapRange map = seedMaps.getValue().get(i);
                if (currentStart > endRange) break;
                if (currentStart < map.getSourceStart()) {
                    SeedRange newRange = new SeedRange(currentStart, SeedRange.calculateLength(currentStart, Math.min(map.getSourceStart() - 1, endRange)));
                    currentStart = newRange.getEnd() + 1;
                    result.add(newRange);
                    i--;
                    continue;
                }

                if (currentStart >= map.getSourceStart() && currentStart <= map.getSourceEnd()) {
                    SeedRange newRange = new SeedRange(map.getMapFromSeed(currentStart), SeedRange.calculateLength(currentStart, Math.min(map.getSourceEnd(), endRange)));
                    currentStart = map.getSeedFromMap(newRange.getEnd()) + 1;
                    result.add(newRange);
                }
            }
            if (currentStart <= endRange) {
                result.add(new SeedRange(currentStart, SeedRange.calculateLength(currentStart, endRange)));
            }

        }

        return Pair.of(seedMaps.getKey().getRight(), result);
    }

    @Override
    public String executePartie1(List<String> in) {
        Map<Pair<String, String>, List<SeedMapRange>> seedRanges = getSeedRanges(in);
        int location = getSeeds(in)
                .stream()
                .map(x -> getLocationFromSeed(x, seedRanges))
                .mapToInt(Double::intValue)
                .min().orElseThrow();

        return String.valueOf(location);
    }

    @Override
    public String executePartie2(List<String> in) {
        Map<Pair<String, String>, List<SeedMapRange>> seedMapRanges = getSeedRanges(in);
        List<Double> seeds = getSeeds(in);
        List<Double> positions = new ArrayList<>();
        List<SeedRange> seedRanges = new ArrayList<>();
        for (int i = 0; i < seeds.size(); i++) {
            int start = seeds.get(i).intValue();
            int length = seeds.get(i + 1).intValue();
            i++;
            SeedRange seedRange = new SeedRange((double) start, (double) length);
            seedRanges.add(seedRange);
        }
        Pair<String, List<SeedRange>> seedRangeMapped = map(seedRanges, seedMapRanges, "seed");

        // 82
        // 84
        // 84
        // 84
        // 77
        // 45
        // 46
        // 46
        while (!seedRangeMapped.getKey().equals("location")) {
            seedRangeMapped = map(seedRangeMapped.getRight(), seedMapRanges, seedRangeMapped.getKey());
        }
        seedRangeMapped.getRight().forEach(x -> positions.add(x.getStart()));
        int location = positions.stream().mapToInt(Double::intValue).min().orElse(46);
        return String.valueOf(location);
    }
}

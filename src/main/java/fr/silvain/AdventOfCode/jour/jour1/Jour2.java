package fr.silvain.AdventOfCode.jour.jour1;

import fr.silvain.AdventOfCode.Jour;
import org.apache.commons.lang3.tuple.Pair;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.OptionalInt;

public class Jour2 extends Jour {
    private Map<String, Integer> cubeConfiguration;

    List<Pair<String, Integer>> parserCube(String in) {
        return Arrays.stream(in.split(",")).map(s -> {
            String[] split = s.trim().split(" ");
            return Pair.of(split[1], Integer.parseInt(split[0]));
        }).toList();
    }

    List<Pair<String, Integer>> parseSubset(String in) {
        return Arrays.stream(in.split(";")).reduce(new java.util.ArrayList<>(), (acc, s) -> {
            acc.addAll(parserCube(s));
            return acc;
        }, (acc, acc2) -> {
            acc.addAll(acc2);
            return acc;
        });
    }

    Pair<Integer, List<Pair<String, Integer>>> parse(String in) {
        String[] split = in.split(":");
        int gameId = Integer.parseInt(split[0].replace("Game", "").trim());
        return Pair.of(gameId, parseSubset(split[1]));
    }

    boolean isCubeValid(List<Pair<String, Integer>> cube) {
        return cube.stream().allMatch(e -> cubeConfiguration.get(e.getKey()) >= e.getValue());
    }

    int getScore(List<Pair<String, Integer>> cube) {
        return cubeConfiguration.keySet().stream()
                .map(key -> cube.stream().filter(x -> x.getKey().equals(key)).mapToInt(Pair::getRight).max())
                .filter(OptionalInt::isPresent)
                .map(OptionalInt::getAsInt)
                .reduce(1, (acc, x) -> acc * x);
    }

    @Override
    public String executePartie1(List<String> in) {
        return String.valueOf(in.stream().map(this::parse).filter(e -> isCubeValid(e.getRight())).mapToInt(Pair::getLeft).sum());
    }

    @Override
    public String executePartie2(List<String> in) {
        return String.valueOf(in.stream().map(this::parse)
                .mapToInt(x -> getScore(x.getRight())).sum());
    }

    public void setCubeConfiguration(Map<String, Integer> cubeConfiguration) {

        this.cubeConfiguration = cubeConfiguration;
    }
}

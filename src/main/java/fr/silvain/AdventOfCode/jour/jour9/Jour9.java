package fr.silvain.AdventOfCode.jour.jour9;

import fr.silvain.AdventOfCode.Jour;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Jour9 extends Jour {
    public List<List<Double>> parse(List<String> in) {
        return in.stream()
                .map(s -> s.split(" "))
                .map(s -> Arrays.stream(s).map(Double::parseDouble).toList())
                .toList();
    }

    public List<Double> diffLine(List<Double> line) {
        List<Double> diff = new ArrayList<>();
        for (int i = 0; i < line.size() - 1; i++) {
            diff.add(line.get(i + 1) - line.get(i));
        }
        return diff;
    }

    public List<List<Double>> makeDiff(List<Double> firstLine) {
        List<List<Double>> tree = new ArrayList<>();
        tree.add(firstLine);
        List<Double> diffLine = firstLine;
        while (!isEnd(diffLine)) {
            diffLine = diffLine(diffLine);
            tree.add(diffLine);
        }
        return tree;
    }

    public Double calculateNext(List<List<Double>> line) {
        List<Double> lastLine = line.get(line.size() - 1);
        Double next = lastLine.get(lastLine.size() - 1);
        for (int i = line.size() - 2; i >= 0; i--) {
            next += line.get(i).get(line.get(i).size() - 1);
        }
        return next;
    }

    public Double calculatePrevious(List<List<Double>> line) {
        List<Double> lastLine = line.get(line.size() - 1);
        Double next = lastLine.get(0);
        for (int i = line.size() - 2; i >= 0; i--) {
            next = line.get(i).get(0) - next;
        }
        return next;
    }

    public boolean isEnd(List<Double> diff) {
        return diff.stream().allMatch(x -> x == 0);
    }

    @Override
    public String executePartie1(List<String> in) {
        List<List<Double>> parse = parse(in);
        List<Double> firstLine = parse.get(0);
        List<List<Double>> tree = makeDiff(firstLine);
        Double next = calculateNext(tree);

        Double sum = parse(in).parallelStream().map(this::makeDiff).map(this::calculateNext).mapToDouble(Double::doubleValue).sum();
        return new DecimalFormat("#").format(sum);
    }

    @Override
    public String executePartie2(List<String> in) {
        Double sum = parse(in).parallelStream().map(this::makeDiff).map(this::calculatePrevious).mapToDouble(Double::doubleValue).sum();
        return new DecimalFormat("#").format(sum);
    }
}

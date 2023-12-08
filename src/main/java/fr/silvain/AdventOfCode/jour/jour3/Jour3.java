package fr.silvain.AdventOfCode.jour.jour3;

import fr.silvain.AdventOfCode.Jour;
import org.apache.commons.lang3.tuple.Pair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.IntStream;

public class Jour3 extends Jour {

    public Position getEndPosition(Position start, String in) {
        int line = start.getLine();
        int position = start.getPosition();
        Position end = new Position(line, position);
        for (int i = position; i < in.length(); i++) {
            char c = in.charAt(i);
            if (!Character.isDigit(c)) {
                return new Position(line, i - 1);
            }
            end = new Position(line, i);
        }
        return end;
    }

    public boolean hasSymbolAdjcacent(Segment segment, Position symbol) {
        // TOP
        if (symbol.getLine() == segment.getStart().getLine() - 1 && segment.getStart().getPosition() - 1 <= symbol.getPosition() && symbol.getPosition() <= segment.getEnd().getPosition() + 1) {
            return true;
        }
        //BOTTOM
        if (symbol.getLine() == segment.getStart().getLine() + 1 && segment.getStart().getPosition() - 1 <= symbol.getPosition() && symbol.getPosition() <= segment.getEnd().getPosition() + 1) {
            return true;
        }
        //LEFT
        if (symbol.getPosition() == segment.getStart().getPosition() - 1 && segment.getStart().getLine() - 1 <= symbol.getLine() && symbol.getLine() <= segment.getEnd().getLine() + 1) {
            return true;
        }
        //RIGHT
        if (symbol.getPosition() == segment.getEnd().getPosition() + 1 && segment.getStart().getLine() - 1 <= symbol.getLine() && symbol.getLine() <= segment.getEnd().getLine() + 1) {
            return true;
        }
        return false;
    }

    public boolean hasSymbolsAdjcacent(Segment segment, List<Position> symbols) {
        for (Position symbol : symbols) {
            if (hasSymbolAdjcacent(segment, symbol)) {
                return true;
            }
        }
        return false;
    }

    public Pair<List<Position>, List<Segment>> getSegmentsAndSymbols(int line, String in) {
        List<Position> symbols = new ArrayList<>();
        List<Segment> segments = new ArrayList<>();
        for (int i = 0; i < in.length(); i++) {
            char c = in.charAt(i);
            if (c == '.') {
                continue;
            }
            if (Character.isDigit(c)) {
                Position start = new Position(line, i);
                Position end = getEndPosition(start, in);
                segments.add(new Segment(start, end));
                i = end.getPosition();
                continue;
            }
            symbols.add(new Position(line, i));
        }
        return Pair.of(symbols, segments);
    }

    public int extractValue(Segment segment, List<String> in) {
        assert segment.getStart().getLine().equals(segment.getEnd().getLine());
        String value = in.get(segment.getStart().getLine()).substring(segment.getStart().getPosition(), segment.getEnd().getPosition() + 1);
        return Integer.parseInt(value);
    }

    @Override
    public String executePartie1(List<String> in) {
        Pair<List<Position>, List<Segment>> values = IntStream.range(0, in.size())
                .mapToObj(i -> this.getSegmentsAndSymbols(i, in.get(i)))
                .reduce((pair1, pair2) -> {
                    List<Position> symbols = new ArrayList<>(pair1.getLeft());
                    symbols.addAll(pair2.getLeft());
                    List<Segment> segments = new ArrayList<>(pair1.getRight());
                    segments.addAll(pair2.getRight());
                    return Pair.of(symbols, segments);
                }).orElseThrow();
        int sum = values.getRight().stream().filter(segment -> hasSymbolsAdjcacent(segment, values.getLeft()))
                .mapToInt(segment -> extractValue(segment, in)).sum();
        return String.valueOf(sum);
    }

    @Override
    public String executePartie2(List<String> in) {
        Pair<List<Position>, List<Segment>> values = IntStream.range(0, in.size())
                .mapToObj(i -> this.getSegmentsAndSymbols(i, in.get(i)))
                .reduce((pair1, pair2) -> {
                    List<Position> symbols = new ArrayList<>(pair1.getLeft());
                    symbols.addAll(pair2.getLeft());
                    List<Segment> segments = new ArrayList<>(pair1.getRight());
                    segments.addAll(pair2.getRight());
                    return Pair.of(symbols, segments);
                }).orElseThrow();
        HashMap<Position, List<Segment>> symbolsSegments = new HashMap<>();
        List<Position> symbols = values.getLeft().stream().filter(x -> in.get(x.getLine()).charAt(x.getPosition()) == '*').toList();
        for (Segment segment : values.getRight()) {
            for (Position symbol : symbols) {
                if (hasSymbolAdjcacent(segment, symbol)) {
                    symbolsSegments.computeIfAbsent(symbol, k -> new ArrayList<>()).add(segment);
                }
            }
        }
        int sum = symbolsSegments.entrySet().stream()
                .filter(entry -> entry.getValue().size() >= 2)
                .map(entry -> {
                    return entry.getValue().stream().mapToInt(segment -> extractValue(segment, in)).reduce((i1, i2) -> i1 * i2).orElseThrow();
                }).mapToInt(Integer::intValue).sum();
        return String.valueOf(sum);
    }
}

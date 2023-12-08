package fr.silvain.AdventOfCode.jour.jour3;

public class Position {
    private final Integer line;
    private final Integer position;

    public Position(Integer line, Integer position) {
        this.line = line;
        this.position = position;
    }

    public Integer getLine() {
        return line;
    }

    public Integer getPosition() {
        return position;
    }
}

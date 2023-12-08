package fr.silvain.AdventOfCode.jour.jour3;

public class Segment {
    private final Position start;
    private final Position end;

    public Segment(Position start, Position end) {
        if (start.getLine() > end.getLine()) {
            this.end = start;
            this.start = end;
        } else {
            this.start = start;
            this.end = end;
        }
    }

    public Position getStart() {
        return start;
    }

    public Position getEnd() {
        return end;
    }
}

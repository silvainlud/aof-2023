package fr.silvain.AdventOfCode.jour.jour5;

import java.util.ArrayList;
import java.util.List;

public class SeedRange {
    private Double start;
    private Double length;

    public SeedRange(Double start, Double end) {
        this.start = start;
        this.length = end;
    }

    public Double getStart() {
        return start;
    }

    public void setStart(Double start) {
        this.start = start;
    }

    public Double getEnd() {
        return start + length - 1;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof SeedRange other)) {
            return super.equals(obj);
        }
        return this.start.equals(other.start) && this.length.equals(other.length);
    }

    public static double calculateLength(double start, double end) {
        return end - start + 1;
    }
}

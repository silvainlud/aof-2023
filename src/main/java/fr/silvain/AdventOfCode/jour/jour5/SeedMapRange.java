package fr.silvain.AdventOfCode.jour.jour5;

public class SeedMapRange implements Comparable<SeedMapRange> {
    private Double destinationStart;
    private Double sourceStart;
    private Double rangeLength;

    public SeedMapRange(Double destinationStart, Double sourceStart, Double rangeLength) {
        this.destinationStart = destinationStart;
        this.sourceStart = sourceStart;
        this.rangeLength = rangeLength;
    }

    public Double getDestinationStart() {
        return destinationStart;
    }

    public Double getDestinationEnd() {
        return destinationStart + rangeLength - 1;
    }

    public void setDestinationStart(Double destinationStart) {
        this.destinationStart = destinationStart;
    }

    public Double getSourceStart() {
        return sourceStart;
    }

    public Double getSourceEnd() {
        return sourceStart + rangeLength - 1;
    }

    public void setSourceStart(Double sourceStart) {
        this.sourceStart = sourceStart;
    }

    public Double getRangeLength() {
        return rangeLength;
    }

    public void setRangeLength(Double rangeLength) {
        this.rangeLength = rangeLength;
    }

    public boolean isInTheSourceRange(double seed) {
        return seed >= sourceStart && seed < sourceStart + rangeLength;
    }

    public boolean isInTheDestinationRange(double map) {
        return map >= destinationStart && map < destinationStart + rangeLength;
    }

    public double getMapFromSeed(double seed) {
        if (!isInTheSourceRange(seed)) {
            return seed;
        }
        return seed - sourceStart + destinationStart;
    }

    public double getSeedFromMap(double seed) {
        if (!isInTheDestinationRange(seed)) {
            return seed;
        }
        return seed - destinationStart + sourceStart;
    }

    @Override
    public int compareTo(SeedMapRange o) {
        if (this.sourceStart.equals(o.sourceStart)) {
            return 0;
        }
        return this.sourceStart - o.sourceStart > 0 ? 1 : -1;
    }
}

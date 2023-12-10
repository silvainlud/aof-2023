package fr.silvain.AdventOfCode.jour.jour6;

import fr.silvain.AdventOfCode.Jour;

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.List;

public class Jour6 extends Jour {
    record Segment(double xStart, double xEnd) {
    }

    public Segment getSegment(double t, double d) {
        double a = -1;
        double b = t;
        double c = -d;
        double delta = b * b - 4 * a * c;
        if (delta == 0) {
            double x = -b / (2 * a);
            return new Segment(x, x);

        } else if (delta > 0) {
            double x1 = (-b - Math.sqrt(delta)) / (2 * a);
            double x2 = (-b + Math.sqrt(delta)) / (2 * a);
            return new Segment(Math.min(x1, x2), Math.max(x1, x2));
        }
        return null;
    }

    public Segment fixSegment(Segment segment, double t) {
        double x1 = Math.min(t, Math.max(0, Math.floor(segment.xStart) + 1));
        double x2 = Math.min(t, Math.max(0, Math.ceil(segment.xEnd) - 1));
        return new Segment(x1, x2);
    }

    @Override
    public String executePartie1(List<String> in) {
        List<Double> times = Arrays.stream(in.get(0).replace("Time:", "").trim()
                        .split(" "))
                .map(x -> x.trim())
                .filter(x -> !x.isEmpty())
                .map(Double::parseDouble).toList();
        List<Double> distances = Arrays.stream(in.get(1).replace("Distance:", "").trim()
                        .split(" "))
                .map(x -> x.trim())
                .filter(x -> !x.isEmpty())
                .map(Double::parseDouble).toList();
        double multiplier = 1;
        for (int i = 0; i < times.size(); i++) {
            double t = times.get(i);
            double d = distances.get(i);
            Segment segment = getSegment(t, d);
            if (segment == null) {
                continue;
            }
            segment = fixSegment(segment, t);
            multiplier *= (segment.xEnd - segment.xStart + 1);
        }
        return String.valueOf(multiplier);
    }

    @Override
    public String executePartie2(List<String> in) {
        double time = Double.parseDouble(in.get(0).replace("Time:", "").replace(" ", ""));
        double distance = Double.parseDouble(in.get(1).replace("Distance:", "").replace(" ", ""));
        Segment segment = getSegment(time, distance);
        if (segment == null) {
            return "0";
        }
        segment = fixSegment(segment, time);
        return (new DecimalFormat("#")).format(segment.xEnd - segment.xStart + 1);
    }
}

package fr.silvain.AdventOfCode.jour.jour1;

import fr.silvain.AdventOfCode.Jour;

import java.lang.annotation.Retention;
import java.util.List;
import java.util.stream.IntStream;

public class Jour1 extends Jour {

    private boolean isNumberLetter = false;

    final String[] numbers = new String[]{"zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};

    public void setNumberLetter(boolean numberLetter) {
        isNumberLetter = numberLetter;
    }

    Character extractLastDigit(String s) {
        Character res = null;
        int resIndex = Integer.MIN_VALUE;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(s.length() - 1 - i);
            if (Character.isDigit(c)) {
                res = c;
                resIndex = s.length() - 1 - i;
                break;
            }
        }
        if (isNumberLetter) {
            for (int i = 0; i < numbers.length; i++) {
                int index = s.lastIndexOf(numbers[i]);
                if (index != -1 && index > resIndex) {
                    res = (char) (48 + i);
                    resIndex = index;
                }
            }
        }
        return res;
    }

    Character extractFirstDigit(String s) {
        Character res = null;
        int resIndex = Integer.MAX_VALUE;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (Character.isDigit(c)) {
                res = c;
                resIndex = i;
                break;
            }
        }
        if (isNumberLetter) {
            for (int i = 0; i < numbers.length; i++) {
                int index = s.indexOf(numbers[i]);
                if (index != -1 && index < resIndex) {
                    res = (char) (48 + i);
                    resIndex = index;
                }
            }
        }
        return res;
    }

    private int extract(String s) {
        Character firstDigit = extractFirstDigit(s);
        Character lastDigit = extractLastDigit(s);
        return Integer.parseInt(String.valueOf(firstDigit) + String.valueOf(lastDigit));
    }

    @Override
    public String executePartie1(List<String> in) {
        setNumberLetter(false);
        return String.valueOf(in.stream().mapToInt(this::extract).sum());
    }

    @Override
    public String executePartie2(List<String> in) {
        setNumberLetter(true);
        IntStream sum = in.stream().map(this::extract).mapToInt(Integer::intValue);
        return String.valueOf(sum.sum());
    }
}

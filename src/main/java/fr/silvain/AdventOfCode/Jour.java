package fr.silvain.AdventOfCode;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class Jour {
    protected Integer getDay() {
        String className = this.getClass().getSimpleName();
        return Integer.parseInt(className.substring(className.length() - 1));
    }

    public abstract String executePartie1(List<String> in);

    public abstract String executePartie2(List<String> in);

    private List<String> getFileContentAsList(Integer part, boolean isExemple) {
        try {
            return Arrays.stream(Files.readString(Path.of(String.format("src/test/resources/jour%s%s/part%s.txt", getDay(), isExemple ? "/exemple" : "", part)))
                    .split("\n")).toList();
        } catch (IOException e) {
            System.err.println("Le fichier n'a pas été trouvé");
            return new ArrayList<>();
        }
    }

    public String part1(boolean isExemple) {
        return this.executePartie1(getFileContentAsList(1, isExemple));
    }

    public String part2(boolean isExemple) {
        return this.executePartie2(getFileContentAsList(2, isExemple));
    }
}

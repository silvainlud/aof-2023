package fr.silvain.AdventOfCode.jour;

import fr.silvain.AdventOfCode.Jour;
import fr.silvain.AdventOfCode.JourTest;
import fr.silvain.AdventOfCode.jour.jour1.Jour2;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Jour3Test extends JourTest {
    private final Jour2 jour = new Jour2();
    private final String RESULTAT_ATTENDU_PARTIE_1 = "8";
    private final List<String> RESULTAT_INCORRECTS_PARTIE_1 = List.of();
    private final String RESULTAT_ATTENDU_PARTIE_2 = "2286";
    private final List<String> RESULTAT_INCORRECTS_PARTIE_2 = List.of();

    @Override
    protected Jour getJour() {
        return jour;
    }

    @Override
    @Test
    public void testPartie1() {
        Map<String, Integer> cubeConfiguration = new HashMap<>();
        cubeConfiguration.put("red", 12);
        cubeConfiguration.put("green", 13);
        cubeConfiguration.put("blue", 14);
        jour.setCubeConfiguration(cubeConfiguration);
        this.partie1(RESULTAT_ATTENDU_PARTIE_1, RESULTAT_INCORRECTS_PARTIE_1);
    }

    @Override
    @Test
    public void testPartie2() {
        Map<String, Integer> cubeConfiguration = new HashMap<>();
        cubeConfiguration.put("red", 12);
        cubeConfiguration.put("green", 13);
        cubeConfiguration.put("blue", 14);
        jour.setCubeConfiguration(cubeConfiguration);
        this.partie2(RESULTAT_ATTENDU_PARTIE_2, RESULTAT_INCORRECTS_PARTIE_2);
    }

    @Override
    @Test
    public void validatePartie1() {

    }

    @Override
    @Test
    public void validatePartie2() {
        Map<String, Integer> cubeConfiguration = new HashMap<>();
        cubeConfiguration.put("red", 12);
        cubeConfiguration.put("green", 13);
        cubeConfiguration.put("blue", 14);
        jour.setCubeConfiguration(cubeConfiguration);
        Assertions.assertEquals("48", jour.executePartie2(List.of("Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green")));
        Assertions.assertEquals("12", jour.executePartie2(List.of("Game 2: 1 blue, 2 green; 3 green, 4 blue, 1 red; 1 green, 1 blue")));
        Assertions.assertEquals("1560", jour.executePartie2(List.of("Game 3: 8 green, 6 blue, 20 red; 5 blue, 4 red, 13 green; 5 green, 1 red")));
        Assertions.assertEquals("630", jour.executePartie2(List.of("Game 4: 1 green, 3 red, 6 blue; 3 green, 6 red; 3 green, 15 blue, 14 red")));
        Assertions.assertEquals("36", jour.executePartie2(List.of("Game 5: 6 red, 1 blue, 3 green; 2 blue, 1 red, 2 green")));
    }
}

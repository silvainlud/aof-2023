package fr.silvain.AdventOfCode.jour;

import fr.silvain.AdventOfCode.Jour;
import fr.silvain.AdventOfCode.JourTest;
import fr.silvain.AdventOfCode.jour.jour4.Jour4;
import org.junit.Test;

import java.util.List;

public class Jour4Test extends JourTest<Jour4> {
    private final String RESULTAT_ATTENDU_PARTIE_1 = "13";
    private final List<String> RESULTAT_INCORRECTS_PARTIE_1 = List.of();
    private final String RESULTAT_ATTENDU_PARTIE_2 = "30";
    private final List<String> RESULTAT_INCORRECTS_PARTIE_2 = List.of();

    @Override
    protected Jour4 instanceNewJour() {
        return new Jour4();
    }

    @Override
    @Test
    public void testPartie1() {

        this.partie1(RESULTAT_ATTENDU_PARTIE_1, RESULTAT_INCORRECTS_PARTIE_1);
    }

    @Override
    @Test
    public void testPartie2() {
        this.partie2(RESULTAT_ATTENDU_PARTIE_2, RESULTAT_INCORRECTS_PARTIE_2);
    }

    @Override
    @Test
    public void validatePartie1() {


    }

    @Override
    @Test
    public void validatePartie2() {

    }
}

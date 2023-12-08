package fr.silvain.AdventOfCode.jour;

import fr.silvain.AdventOfCode.Jour;
import fr.silvain.AdventOfCode.JourTest;
import fr.silvain.AdventOfCode.jour.jour1.Jour1;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.util.List;

public class Jour2Test extends JourTest {
    private final Jour jour = new Jour1();
    private final String RESULTAT_ATTENDU_PARTIE_1 = "142";
    private final List<String> RESULTAT_INCORRECTS_PARTIE_1 = List.of();
    private final String RESULTAT_ATTENDU_PARTIE_2 = "281";
    private final List<String> RESULTAT_INCORRECTS_PARTIE_2 = List.of();

    @Override
    protected Jour getJour() {
        return jour;
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
        Assertions.assertEquals("11", jour.executePartie1(List.of("dzdzjgpizhkhzbjzboezuhb18felgjhzlmjbmgljzbmjbvmkblibzvlie21fengelojbhmljghemoh")));
        Assertions.assertEquals("88", jour.executePartie1(List.of("geggegege8gegegeg")));
    }

    @Override
    @Test
    public void validatePartie2() {
        Assertions.assertEquals("21", jour.executePartie2(List.of("twonetwone")));
        Assertions.assertEquals("11", jour.executePartie2(List.of("azazeaeaezonejhojvegojgve")));
    }
}

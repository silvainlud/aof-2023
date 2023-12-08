package fr.silvain.AdventOfCode;

import org.junit.jupiter.api.TestInstance;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@RunWith(JUnit4.class)
public abstract class JourTest {
    protected abstract Jour getJour();

    protected void partie1(String resultatAttenduPartie1, List<String> resultatsIncorrectsPartie2) {
        System.out.printf("========================== JOUR %s ==========================", getJour().getDay());
        String resultatObtenuEx = getJour().part1(true);
        System.out.printf("--------------------------\nRésultat partie 1 exemple: \n%s\n", resultatObtenuEx);
        assertEquals("Le résultat est incorrect", resultatAttenduPartie1, resultatObtenuEx);

        String resultatObtenu = getJour().part1(false);
        System.out.printf("--------------------------\nRésultat partie 1: \n%s\n", resultatObtenu);
        assertFalse("Le résultat a déja été testé", resultatsIncorrectsPartie2.contains(resultatObtenu));
    }

    protected void partie2(String resultatAttenduPartie1, List<String> resultatsIncorrectsPartie2) {
        System.out.printf("========================== JOUR %s ==========================", getJour().getDay());
        String resultatObtenuEx = getJour().part2(true);
        System.out.printf("--------------------------\nRésultat partie 2 exemple: \n%s\n", resultatObtenuEx);
        assertEquals("Le résultat est incorrect", resultatAttenduPartie1, resultatObtenuEx);

        String resultatObtenu = getJour().part2(false);
        System.out.printf("--------------------------\nRésultat partie 2: \n%s\n", resultatObtenu);
        assertFalse("Le résultat a déja été testé", resultatsIncorrectsPartie2.contains(resultatObtenu));
    }

    public abstract void testPartie1();

    public abstract void testPartie2();

    protected abstract void validatePartie1();
    protected abstract void validatePartie2();
}

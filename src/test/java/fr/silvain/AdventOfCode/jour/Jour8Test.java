package fr.silvain.AdventOfCode.jour;

import fr.silvain.AdventOfCode.JourTest;
import fr.silvain.AdventOfCode.jour.jour8.Jour8;
import org.junit.Test;

import java.util.List;

public class Jour8Test extends JourTest<Jour8> {
    @Override
    protected Jour8 instanceNewJour() {
        return new Jour8();
    }

    @Override
    @Test
    public void testPartie1() {
        this.partie1("2", List.of());
    }

    @Override
    @Test
    public void testPartie2() {
        this.partie2("6", List.of("14363"));
    }

    @Override
    protected void validatePartie1() {

    }

    @Override
    protected void validatePartie2() {

    }
}

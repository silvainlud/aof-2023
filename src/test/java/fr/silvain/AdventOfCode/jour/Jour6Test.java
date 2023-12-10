package fr.silvain.AdventOfCode.jour;

import fr.silvain.AdventOfCode.JourTest;
import fr.silvain.AdventOfCode.jour.jour6.Jour6;
import org.junit.Test;

import java.util.List;

public class Jour6Test extends JourTest<Jour6> {
    @Override
    protected Jour6 instanceNewJour() {
        return new Jour6();
    }

    @Override
    @Test
    public void testPartie1() {
        this.partie1("288.0", List.of());
    }

    @Override
    @Test
    public void testPartie2() {
        this.partie2("71503", List.of());
    }

    @Override
    protected void validatePartie1() {

    }

    @Override
    protected void validatePartie2() {

    }
}

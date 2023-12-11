package fr.silvain.AdventOfCode.jour;

import fr.silvain.AdventOfCode.JourTest;
import fr.silvain.AdventOfCode.jour.jour9.Jour9;
import org.junit.Test;

import java.util.List;

public class Jour9Test extends JourTest<Jour9> {
    @Override
    protected Jour9 instanceNewJour() {
        return new Jour9();
    }

    @Override
    @Test
    public void testPartie1() {
        this.partie1("114", List.of());
    }

    @Override
    @Test
    public void testPartie2() {
        this.partie2("2", List.of());
    }

    @Override
    public void validatePartie1() {

    }

    @Override
    public void validatePartie2() {

    }
}

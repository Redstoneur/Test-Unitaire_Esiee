package fr.knap.testunitaire_esiee.model;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class EtatTest {

    @Test
    void valueOf_Attente() {
        assertEquals(Etat.ATTENTE, Etat.valueOf("ATTENTE"));
    }

    @Test
    void valueOf_Accepte() {
        assertEquals(Etat.ACCEPTE, Etat.valueOf("ACCEPTE"));
    }

    @Test
    void valueOf_Refuse() {
        assertEquals(Etat.REFUSE, Etat.valueOf("REFUSE"));
    }

    @Test
    void valueOf_Annuler() {
        assertEquals(Etat.ANNULER, Etat.valueOf("ANNULER"));
    }

    @Test
    void valueOf_InvalidState() {
        assertThrows(IllegalArgumentException.class, () -> Etat.valueOf("INVALID"));
    }

    @Test
    void values_ContainsAllStates() {
        Etat[] states = Etat.values();
        assertEquals(4, states.length);
        assertArrayEquals(new Etat[]{
            Etat.ATTENTE,
            Etat.ACCEPTE,
            Etat.REFUSE,
            Etat.ANNULER
        }, states);
    }
}
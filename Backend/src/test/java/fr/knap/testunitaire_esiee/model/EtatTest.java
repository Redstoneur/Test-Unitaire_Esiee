package fr.knap.testunitaire_esiee.model;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the Etat enum.
 */
@SpringBootTest
class EtatTest {

    /**
     * Tests the valueOf method for the ATTENTE state.
     */
    @Test
    void valueOf_Attente() {
        assertEquals(Etat.ATTENTE, Etat.valueOf("ATTENTE"));
    }

    /**
     * Tests the valueOf method for the ACCEPTE state.
     */
    @Test
    void valueOf_Accepte() {
        assertEquals(Etat.ACCEPTE, Etat.valueOf("ACCEPTE"));
    }

    /**
     * Tests the valueOf method for the REFUSE state.
     */
    @Test
    void valueOf_Refuse() {
        assertEquals(Etat.REFUSE, Etat.valueOf("REFUSE"));
    }

    /**
     * Tests the valueOf method for the ANNULER state.
     */
    @Test
    void valueOf_Annuler() {
        assertEquals(Etat.ANNULER, Etat.valueOf("ANNULER"));
    }

    /**
     * Tests the valueOf method for an invalid state.
     */
    @Test
    void valueOf_InvalidState() {
        assertThrows(IllegalArgumentException.class, () -> Etat.valueOf("INVALID"));
    }

    /**
     * Tests the values method to ensure it contains all states.
     */
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
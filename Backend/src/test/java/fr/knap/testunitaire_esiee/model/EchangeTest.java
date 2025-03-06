package fr.knap.testunitaire_esiee.model;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;

/**
 * Unit tests for the Echange class.
 */
@SpringBootTest
class EchangeTest {

    private static Echange echange;
    private static Echange echange2;

    /**
     * Sets up the test environment before all tests.
     * Initializes the Echange instances with test data.
     */
    @BeforeAll
    static void setUp() {
        echange = new Echange();
        echange.setId(1L);
        echange.setObjetPropose(new Objet());
        echange.setObjetDemande(new Objet());
        echange.setDateProposition(LocalDateTime.now());
        echange.setEtatEchange(Etat.ATTENTE);
        echange.setDateCloture(LocalDateTime.now());

        echange2 = new Echange(
                new Objet(),
                new Objet(),
                LocalDateTime.now(),
                Etat.ATTENTE,
                LocalDateTime.now()
        );
    }

    /**
     * Tests the setId method of the Echange class.
     * Verifies that the ID is set correctly.
     */
    @Test
    void testSetId() {
        assertEquals(1L, echange.getId());
    }

    /**
     * Tests the setDateProposition method of the Echange class.
     * Verifies that the date of proposition is set correctly.
     */
    @Test
    void testSetDateProposition() {
        assertNotNull(echange.getDateProposition());
    }

    /**
     * Tests the setEtatEchange method of the Echange class.
     * Verifies that the state of the exchange is set correctly.
     */
    @Test
    void testSetEtatEchange() {
        assertEquals(Etat.ATTENTE, echange.getEtatEchange());
    }

    /**
     * Tests the setDateCloture method of the Echange class.
     * Verifies that the date of closure is set correctly.
     */
    @Test
    void testSetDateCloture() {
        assertNotNull(echange.getDateCloture());
    }

    /**
     * Tests the setObjetPropose method of the Echange class.
     * Verifies that the proposed object is set correctly.
     */
    @Test
    void testSetObjetPropose() {
        assertNotNull(echange.getObjetPropose());
    }

    /**
     * Tests the setObjetDemande method of the Echange class.
     * Verifies that the requested object is set correctly.
     */
    @Test
    void testSetObjetDemande() {
        assertNotNull(echange.getObjetDemande());
    }

    /**
     * Tests the setDateProposition method of the second Echange instance.
     * Verifies that the date of proposition is set correctly.
     */
    @Test
    void testSetDateProposition2() {
        assertNotNull(echange2.getDateProposition());
    }

    /**
     * Tests the setEtatEchange method of the second Echange instance.
     * Verifies that the state of the exchange is set correctly.
     */
    @Test
    void testSetEtatEchange2() {
        assertEquals(Etat.ATTENTE, echange2.getEtatEchange());
    }

    /**
     * Tests the setDateCloture method of the second Echange instance.
     * Verifies that the date of closure is set correctly.
     */
    @Test
    void testSetDateCloture2() {
        assertNotNull(echange2.getDateCloture());
    }

    /**
     * Tests the setObjetPropose method of the second Echange instance.
     * Verifies that the proposed object is set correctly.
     */
    @Test
    void testSetObjetPropose2() {
        assertNotNull(echange2.getObjetPropose());
    }

    /**
     * Tests the setObjetDemande method of the second Echange instance.
     * Verifies that the requested object is set correctly.
     */
    @Test
    void testSetObjetDemande2() {
        assertNotNull(echange2.getObjetDemande());
    }
}
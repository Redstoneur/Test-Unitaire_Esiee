package fr.knap.testunitaire_esiee.model;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;

@SpringBootTest
class EchangeTest {

    private static Echange echange;
    private static Echange echange2;

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


    @Test
    void testSetId() {
        assertEquals(1L, echange.getId());
    }

    @Test
    void testSetDateProposition() {
        assertNotNull(echange.getDateProposition());
    }

    @Test
    void testSetEtatEchange() {
        assertEquals(Etat.ATTENTE, echange.getEtatEchange());
    }

    @Test
    void testSetDateCloture() {
        assertNotNull(echange.getDateCloture());
    }

    @Test
    void testSetObjetPropose() {
        assertNotNull(echange.getObjetPropose());
    }

    @Test
    void testSetObjetDemande() {
        assertNotNull(echange.getObjetDemande());
    }

    @Test
    void testSetDateProposition2() {
        assertNotNull(echange2.getDateProposition());
    }

    @Test
    void testSetEtatEchange2() {
        assertEquals(Etat.ATTENTE, echange2.getEtatEchange());
    }

    @Test
    void testSetDateCloture2() {
        assertNotNull(echange2.getDateCloture());
    }

    @Test
    void testSetObjetPropose2() {
        assertNotNull(echange2.getObjetPropose());
    }

    @Test
    void testSetObjetDemande2() {
        assertNotNull(echange2.getObjetDemande());
    }
}

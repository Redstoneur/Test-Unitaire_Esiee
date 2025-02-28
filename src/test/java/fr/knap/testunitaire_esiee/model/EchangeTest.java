package fr.knap.testunitaire_esiee.model;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
import java.sql.Timestamp;

@SpringBootTest
class EchangeTest {

    @Test
    void testSetId() {
        Echange echange = new Echange();
        echange.setId(1L);
        assertEquals(1L, echange.getId());
    }

    @Test
    void testSetDateProposition() {
        Echange echange = new Echange();
        echange.setDateProposition(new Timestamp(System.currentTimeMillis()));
        assertNotNull(echange.getDateProposition());
    }

    @Test
    void testSetEtatEchange() {
        Echange echange = new Echange();
        echange.setEtatEchange(Etat.ATTENTE);
        assertEquals(Etat.ATTENTE, echange.getEtatEchange());
    }

    @Test
    void testSetDateCloture() {
        Echange echange = new Echange();
        echange.setDateCloture(new Timestamp(System.currentTimeMillis()));
        assertNotNull(echange.getDateCloture());
    }
}

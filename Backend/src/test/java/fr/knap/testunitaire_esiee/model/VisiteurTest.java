package fr.knap.testunitaire_esiee.model;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class VisiteurTest {

    @Test
    void testVisiteurCreation() {
        Visiteur visiteur = new Visiteur();
        assertNotNull(visiteur);
    }

    @Test
    void testInscription() {
        Visiteur visiteur = new Visiteur();
        assertNull(visiteur.Inscription());
    }
}
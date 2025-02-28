package fr.knap.testunitaire_esiee.model;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;

@SpringBootTest
class ObjetTest {

    private static Objet objet;

    @BeforeAll
    static void setUp() {
        objet = new Objet();
        objet.setId(1L);
        objet.setNom("Test Objet");
        objet.setCategorie(CategorieObjet.INFORMATIQUE);
        objet.setDescription("Description de test");
        objet.setDateCreation(LocalDateTime.now());
    }

    @Test
    void testObjetId() {
        assertEquals(1L, objet.getId());
    }

    @Test
    void testObjetNom() {
        assertEquals("Test Objet", objet.getNom());
    }

    @Test
    void testObjetCategorie() {
        assertEquals(CategorieObjet.INFORMATIQUE, objet.getCategorie());
    }

    @Test
    void testObjetDescription() {
        assertEquals("Description de test", objet.getDescription());
    }

    @Test
    void testObjetDateCreation() {
        assertNotNull(objet.getDateCreation());
    }
}

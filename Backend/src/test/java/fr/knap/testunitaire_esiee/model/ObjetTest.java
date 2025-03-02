package fr.knap.testunitaire_esiee.model;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;

@SpringBootTest
class ObjetTest {

    private static Objet objet;
    private static Objet objet2;

    @BeforeAll
    static void setUp() {
        objet = new Objet();
        objet.setId(1L);
        objet.setNom("Test Objet");
        objet.setCategorie(CategorieObjet.INFORMATIQUE);
        objet.setDescription("Description de test");
        objet.setDateCreation(LocalDateTime.now());

        objet2 = new Objet(
                null,
                "Test Objet 2",
                "Description de test 2",
                CategorieObjet.INFORMATIQUE,
                LocalDateTime.now()
        );
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

    @Test
    void testObjetUtilisateur() {
        assertNull(objet.getUtilisateur());
    }

    @Test
    void testObjetNom2() {
        assertEquals("Test Objet 2", objet2.getNom());
    }

    @Test
    void testObjetCategorie2() {
        assertEquals(CategorieObjet.INFORMATIQUE, objet2.getCategorie());
    }

    @Test
    void testObjetDescription2() {
        assertEquals("Description de test 2", objet2.getDescription());
    }

    @Test
    void testObjetDateCreation2() {
        assertNotNull(objet2.getDateCreation());
    }

    @Test
    void testObjetUtilisateur2() {
        assertNull(objet2.getUtilisateur());
    }
}

package fr.knap.testunitaire_esiee.model;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;

/**
 * Unit tests for the Objet class.
 */
@SpringBootTest
class ObjetTest {

    private static Objet objet;
    private static Objet objet2;

    /**
     * Sets up the test environment before all tests.
     * Initializes the Objet instances with test data.
     */
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

    /**
     * Tests the getId method of the Objet class.
     * Verifies that the ID is returned correctly.
     */
    @Test
    void testObjetId() {
        assertEquals(1L, objet.getId());
    }

    /**
     * Tests the getNom method of the Objet class.
     * Verifies that the name is returned correctly.
     */
    @Test
    void testObjetNom() {
        assertEquals("Test Objet", objet.getNom());
    }

    /**
     * Tests the getCategorie method of the Objet class.
     * Verifies that the category is returned correctly.
     */
    @Test
    void testObjetCategorie() {
        assertEquals(CategorieObjet.INFORMATIQUE, objet.getCategorie());
    }

    /**
     * Tests the getDescription method of the Objet class.
     * Verifies that the description is returned correctly.
     */
    @Test
    void testObjetDescription() {
        assertEquals("Description de test", objet.getDescription());
    }

    /**
     * Tests the getDateCreation method of the Objet class.
     * Verifies that the creation date is returned correctly.
     */
    @Test
    void testObjetDateCreation() {
        assertNotNull(objet.getDateCreation());
    }

    /**
     * Tests the getUtilisateur method of the Objet class.
     * Verifies that the user is returned correctly.
     */
    @Test
    void testObjetUtilisateur() {
        assertNull(objet.getUtilisateur());
    }

    /**
     * Tests the getNom method of the second Objet instance.
     * Verifies that the name is returned correctly.
     */
    @Test
    void testObjetNom2() {
        assertEquals("Test Objet 2", objet2.getNom());
    }

    /**
     * Tests the getCategorie method of the second Objet instance.
     * Verifies that the category is returned correctly.
     */
    @Test
    void testObjetCategorie2() {
        assertEquals(CategorieObjet.INFORMATIQUE, objet2.getCategorie());
    }

    /**
     * Tests the getDescription method of the second Objet instance.
     * Verifies that the description is returned correctly.
     */
    @Test
    void testObjetDescription2() {
        assertEquals("Description de test 2", objet2.getDescription());
    }

    /**
     * Tests the getDateCreation method of the second Objet instance.
     * Verifies that the creation date is returned correctly.
     */
    @Test
    void testObjetDateCreation2() {
        assertNotNull(objet2.getDateCreation());
    }

    /**
     * Tests the getUtilisateur method of the second Objet instance.
     * Verifies that the user is returned correctly.
     */
    @Test
    void testObjetUtilisateur2() {
        assertNull(objet2.getUtilisateur());
    }
}
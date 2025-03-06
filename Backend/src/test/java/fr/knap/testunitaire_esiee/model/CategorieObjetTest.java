package fr.knap.testunitaire_esiee.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Unit tests for the CategorieObjet enum.
 */
@SpringBootTest
class CategorieObjetTest {

    /**
     * Parameterized test for the CategorieObjet enum values.
     * Verifies that each provided category string corresponds to a valid CategorieObjet enum value.
     *
     * @param categorie The category string to be tested.
     */
    @ParameterizedTest
    @ValueSource(strings = {
            "MOBILIER", "JARDINAGE", "INFORMATIQUE", "GAMING",
            "OUTILS", "COLLECTION", "LITTERATURE", "VETEMENTS",
            "ELECTROMENAGER", "AUTRE"
    })
    void testCategorieObjetValues(String categorie) {
        assertNotNull(CategorieObjet.valueOf(categorie));
    }
}
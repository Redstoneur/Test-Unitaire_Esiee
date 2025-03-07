package fr.knap.testunitaire_esiee.model;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the CategorieObjet enum.
 */
@SpringBootTest
class CategorieObjetTest {

    /**
     * Tests the valueOf method for the MOBILIER category.
     */
    @Test
    void valueOf_Mobilier() {
        assertEquals(CategorieObjet.MOBILIER, CategorieObjet.valueOf("MOBILIER"));
    }

    /**
     * Tests the valueOf method for the JARDINAGE category.
     */
    @Test
    void valueOf_Jardinage() {
        assertEquals(CategorieObjet.JARDINAGE, CategorieObjet.valueOf("JARDINAGE"));
    }

    /**
     * Tests the valueOf method for the INFORMATIQUE category.
     */
    @Test
    void valueOf_Informatique() {
        assertEquals(CategorieObjet.INFORMATIQUE, CategorieObjet.valueOf("INFORMATIQUE"));
    }

    /**
     * Tests the valueOf method for the GAMING category.
     */
    @Test
    void valueOf_Gaming() {
        assertEquals(CategorieObjet.GAMING, CategorieObjet.valueOf("GAMING"));
    }

    /**
     * Tests the valueOf method for the OUTILS category.
     */
    @Test
    void valueOf_Outils() {
        assertEquals(CategorieObjet.OUTILS, CategorieObjet.valueOf("OUTILS"));
    }

    /**
     * Tests the valueOf method for the COLLECTION category.
     */
    @Test
    void valueOf_Collection() {
        assertEquals(CategorieObjet.COLLECTION, CategorieObjet.valueOf("COLLECTION"));
    }

    /**
     * Tests the valueOf method for the LITTERATURE category.
     */
    @Test
    void valueOf_Litterature() {
        assertEquals(CategorieObjet.LITTERATURE, CategorieObjet.valueOf("LITTERATURE"));
    }

    /**
     * Tests the valueOf method for the VETEMENTS category.
     */
    @Test
    void valueOf_Vetements() {
        assertEquals(CategorieObjet.VETEMENTS, CategorieObjet.valueOf("VETEMENTS"));
    }

    /**
     * Tests the valueOf method for the ELECTROMENAGER category.
     */
    @Test
    void valueOf_Electromenager() {
        assertEquals(CategorieObjet.ELECTROMENAGER, CategorieObjet.valueOf("ELECTROMENAGER"));
    }

    /**
     * Tests the valueOf method for the AUTRE category.
     */
    @Test
    void valueOf_Autre() {
        assertEquals(CategorieObjet.AUTRE, CategorieObjet.valueOf("AUTRE"));
    }

    /**
     * Tests the valueOf method for an invalid category.
     */
    @Test
    void valueOf_InvalidCategory() {
        assertThrows(IllegalArgumentException.class, () -> CategorieObjet.valueOf("INVALID"));
    }

    /**
     * Tests the values method to ensure it contains all categories.
     */
    @Test
    void values_ContainsAllCategories() {
        CategorieObjet[] categories = CategorieObjet.values();
        assertEquals(10, categories.length);
        assertArrayEquals(new CategorieObjet[]{
                CategorieObjet.MOBILIER,
                CategorieObjet.JARDINAGE,
                CategorieObjet.INFORMATIQUE,
                CategorieObjet.GAMING,
                CategorieObjet.OUTILS,
                CategorieObjet.COLLECTION,
                CategorieObjet.LITTERATURE,
                CategorieObjet.VETEMENTS,
                CategorieObjet.ELECTROMENAGER,
                CategorieObjet.AUTRE
        }, categories);
    }
}
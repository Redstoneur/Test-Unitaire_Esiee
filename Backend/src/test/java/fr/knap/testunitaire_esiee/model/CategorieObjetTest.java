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
    void valueOfMobilier() {
        assertEquals(CategorieObjet.MOBILIER, CategorieObjet.valueOf("MOBILIER"));
    }

    /**
     * Tests the valueOf method for the JARDINAGE category.
     */
    @Test
    void valueOfJardinage() {
        assertEquals(CategorieObjet.JARDINAGE, CategorieObjet.valueOf("JARDINAGE"));
    }

    /**
     * Tests the valueOf method for the INFORMATIQUE category.
     */
    @Test
    void valueOfInformatique() {
        assertEquals(CategorieObjet.INFORMATIQUE, CategorieObjet.valueOf("INFORMATIQUE"));
    }

    /**
     * Tests the valueOf method for the GAMING category.
     */
    @Test
    void valueOfGaming() {
        assertEquals(CategorieObjet.GAMING, CategorieObjet.valueOf("GAMING"));
    }

    /**
     * Tests the valueOf method for the OUTILS category.
     */
    @Test
    void valueOfOutils() {
        assertEquals(CategorieObjet.OUTILS, CategorieObjet.valueOf("OUTILS"));
    }

    /**
     * Tests the valueOf method for the COLLECTION category.
     */
    @Test
    void valueOfCollection() {
        assertEquals(CategorieObjet.COLLECTION, CategorieObjet.valueOf("COLLECTION"));
    }

    /**
     * Tests the valueOf method for the LITTERATURE category.
     */
    @Test
    void valueOfLitterature() {
        assertEquals(CategorieObjet.LITTERATURE, CategorieObjet.valueOf("LITTERATURE"));
    }

    /**
     * Tests the valueOf method for the VETEMENTS category.
     */
    @Test
    void valueOfVetements() {
        assertEquals(CategorieObjet.VETEMENTS, CategorieObjet.valueOf("VETEMENTS"));
    }

    /**
     * Tests the valueOf method for the ELECTROMENAGER category.
     */
    @Test
    void valueOfElectromenager() {
        assertEquals(CategorieObjet.ELECTROMENAGER, CategorieObjet.valueOf("ELECTROMENAGER"));
    }

    /**
     * Tests the valueOf method for the AUTRE category.
     */
    @Test
    void valueOfAutre() {
        assertEquals(CategorieObjet.AUTRE, CategorieObjet.valueOf("AUTRE"));
    }

    /**
     * Tests the valueOf method for an invalid category.
     */
    @Test
    void valueOfInvalidCategory() {
        assertThrows(IllegalArgumentException.class, () -> CategorieObjet.valueOf("INVALID"));
    }

    /**
     * Tests the values method to ensure it contains all categories.
     */
    @Test
    void valuesContainsAllCategories() {
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
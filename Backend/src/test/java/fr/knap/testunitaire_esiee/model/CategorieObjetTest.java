package fr.knap.testunitaire_esiee.model;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CategorieObjetTest {

    @Test
    void valueOf_Mobilier() {
        assertEquals(CategorieObjet.MOBILIER, CategorieObjet.valueOf("MOBILIER"));
    }

    @Test
    void valueOf_Jardinage() {
        assertEquals(CategorieObjet.JARDINAGE, CategorieObjet.valueOf("JARDINAGE"));
    }

    @Test
    void valueOf_Informatique() {
        assertEquals(CategorieObjet.INFORMATIQUE, CategorieObjet.valueOf("INFORMATIQUE"));
    }

    @Test
    void valueOf_Gaming() {
        assertEquals(CategorieObjet.GAMING, CategorieObjet.valueOf("GAMING"));
    }

    @Test
    void valueOf_Outils() {
        assertEquals(CategorieObjet.OUTILS, CategorieObjet.valueOf("OUTILS"));
    }

    @Test
    void valueOf_Collection() {
        assertEquals(CategorieObjet.COLLECTION, CategorieObjet.valueOf("COLLECTION"));
    }

    @Test
    void valueOf_Litterature() {
        assertEquals(CategorieObjet.LITTERATURE, CategorieObjet.valueOf("LITTERATURE"));
    }

    @Test
    void valueOf_Vetements() {
        assertEquals(CategorieObjet.VETEMENTS, CategorieObjet.valueOf("VETEMENTS"));
    }

    @Test
    void valueOf_Electromenager() {
        assertEquals(CategorieObjet.ELECTROMENAGER, CategorieObjet.valueOf("ELECTROMENAGER"));
    }

    @Test
    void valueOf_Autre() {
        assertEquals(CategorieObjet.AUTRE, CategorieObjet.valueOf("AUTRE"));
    }

    @Test
    void valueOf_InvalidCategory() {
        assertThrows(IllegalArgumentException.class, () -> CategorieObjet.valueOf("INVALID"));
    }

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
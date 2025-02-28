package fr.knap.testunitaire_esiee.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CategorieObjetTest {

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

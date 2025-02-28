package fr.knap.testunitaire_esiee.model;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class EtatTest {

    @ParameterizedTest
    @ValueSource(strings = {"ATTENTE", "ACCEPTE", "REFUSE", "ANNULER"})
    void testEtatValues(String etat) {
        assertNotNull(Etat.valueOf(etat));
    }
}

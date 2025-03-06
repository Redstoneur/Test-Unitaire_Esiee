package fr.knap.testunitaire_esiee.model;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the Etat enum.
 */
@SpringBootTest
class EtatTest {

    /**
     * Parameterized test for the Etat enum values.
     * Verifies that each provided state string corresponds to a valid Etat enum value.
     *
     * @param etat The state string to be tested.
     */
    @ParameterizedTest
    @ValueSource(strings = {"ATTENTE", "ACCEPTE", "REFUSE", "ANNULER"})
    void testEtatValues(String etat) {
        assertNotNull(Etat.valueOf(etat));
    }
}
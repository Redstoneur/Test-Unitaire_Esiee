package fr.knap.testunitaire_esiee.dto;

import fr.knap.testunitaire_esiee.model.Etat;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class EchangeEtatDTOTest {

    @Test
    void constructorInitializesIdAndEtat() {
        long expectedId = 10L;
        Etat expectedEtat = Etat.ATTENTE;
        EchangeEtatDTO dto = new EchangeEtatDTO(expectedId, expectedEtat);
        assertEquals(expectedId, dto.getId());
        assertEquals(expectedEtat, dto.getEtat());
    }

    @Test
    void settersUpdateValues() {
        EchangeEtatDTO dto = new EchangeEtatDTO(5L, Etat.ATTENTE);
        dto.setId(20L);
        dto.setEtat(null);
        assertEquals(20L, dto.getId());
        assertNull(dto.getEtat());
    }

    @Test
    void handlesNegativeIdCorrectly() {
        long negativeId = -3L;
        Etat expectedEtat = Etat.ATTENTE;
        EchangeEtatDTO dto = new EchangeEtatDTO(negativeId, expectedEtat);
        assertEquals(negativeId, dto.getId());
        assertEquals(expectedEtat, dto.getEtat());
    }

    @Test
    void canAcceptNullEtat() {
        long id = 15L;
        EchangeEtatDTO dto = new EchangeEtatDTO(id, null);
        assertEquals(id, dto.getId());
        assertNull(dto.getEtat());
    }
}
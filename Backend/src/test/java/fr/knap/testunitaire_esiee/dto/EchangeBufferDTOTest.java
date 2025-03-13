package fr.knap.testunitaire_esiee.dto;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class EchangeBufferDTOTest {

    @Test
    void shouldConstructProperlyWithValidValues() {
        Long expectedIdObjetPropose = 1L;
        Long expectedIdObjetRecherche = 2L;
        EchangeBufferDTO dto = new EchangeBufferDTO(expectedIdObjetPropose, expectedIdObjetRecherche);
        assertEquals(expectedIdObjetPropose, dto.getIdObjetPropose());
        assertEquals(expectedIdObjetRecherche, dto.getIdObjetRecherche());
    }

    @Test
    void shouldAllowModifyingValuesWithSetters() {
        EchangeBufferDTO dto = new EchangeBufferDTO(1L, 2L);
        Long newIdObjetPropose = 3L;
        Long newIdObjetRecherche = 4L;
        dto.setIdObjetPropose(newIdObjetPropose);
        dto.setIdObjetRecherche(newIdObjetRecherche);
        assertEquals(newIdObjetPropose, dto.getIdObjetPropose());
        assertEquals(newIdObjetRecherche, dto.getIdObjetRecherche());
    }

    @Test
    void shouldHandleNullValues() {
        EchangeBufferDTO dto = new EchangeBufferDTO(null, null);
        assertNull(dto.getIdObjetPropose());
        assertNull(dto.getIdObjetRecherche());
    }
}
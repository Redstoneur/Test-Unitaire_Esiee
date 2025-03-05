package fr.knap.testunitaire_esiee.controller;

import fr.knap.testunitaire_esiee.model.Echange;
import fr.knap.testunitaire_esiee.services.EchangeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class EchangeControllerTest {

    @Mock
    private EchangeService echangeService;

    @InjectMocks
    private EchangeController echangeController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreerEchange() {
        Echange echange = new Echange();
        when(echangeService.creerEchange(echange)).thenReturn(echange);
        assertEquals(echange, echangeController.creerEchange(echange));
    }

    @Test
    void testObtenirTousLesEchanges() {
        echangeController.obtenirTousLesEchanges();
        verify(echangeService, times(1)).obtenirTousLesEchanges();
    }
}
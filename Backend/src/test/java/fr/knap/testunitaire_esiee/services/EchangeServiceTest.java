package fr.knap.testunitaire_esiee.services;

import fr.knap.testunitaire_esiee.model.Echange;
import fr.knap.testunitaire_esiee.respository.EchangeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class EchangeServiceTest {

    @Mock
    private EchangeRepository echangeRepository;

    @InjectMocks
    private EchangeService echangeService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreerEchange() {
        Echange echange = new Echange();
        when(echangeRepository.save(echange)).thenReturn(echange);
        assertEquals(echange, echangeService.creerEchange(echange));
    }

    @Test
    void testObtenirTousLesEchanges() {
        echangeService.obtenirTousLesEchanges();
        verify(echangeRepository, times(1)).findAll();
    }
}
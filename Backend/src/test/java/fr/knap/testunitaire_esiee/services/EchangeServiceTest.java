package fr.knap.testunitaire_esiee.services;

import fr.knap.testunitaire_esiee.model.Echange;
import fr.knap.testunitaire_esiee.repository.EchangeRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class EchangeServiceTest {

    @Mock
    private EchangeRepository echangeRepository;

    @InjectMocks
    private EchangeService echangeService;

    public EchangeServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void creerEchange_SavesAndReturnsEchange() {
        Echange echange = new Echange();
        when(echangeRepository.save(echange)).thenReturn(echange);

        Echange result = echangeService.creerEchange(echange);

        assertEquals(echange, result);
        verify(echangeRepository, times(1)).save(echange);
    }

    @Test
    void obtenirTousLesEchanges_ReturnsAllEchanges() {
        List<Echange> echanges = Arrays.asList(new Echange(), new Echange());
        when(echangeRepository.findAll()).thenReturn(echanges);

        List<Echange> result = echangeService.obtenirTousLesEchanges();

        assertEquals(echanges, result);
        verify(echangeRepository, times(1)).findAll();
    }

    @Test
    void obtenirEchangeParId_ReturnsEchangeIfExists() {
        Long id = 1L;
        Echange echange = new Echange();
        when(echangeRepository.findById(id)).thenReturn(Optional.of(echange));

        Echange result = echangeService.obtenirEchangeParId(id);

        assertEquals(echange, result);
        verify(echangeRepository, times(1)).findById(id);
    }

    @Test
    void obtenirEchangeParId_ReturnsNullIfNotExists() {
        Long id = 1L;
        when(echangeRepository.findById(id)).thenReturn(Optional.empty());

        Echange result = echangeService.obtenirEchangeParId(id);

        assertNull(result);
        verify(echangeRepository, times(1)).findById(id);
    }

    @Test
    void mettreAJourEchange_SavesAndReturnsUpdatedEchange() {
        Echange echange = new Echange();
        when(echangeRepository.save(echange)).thenReturn(echange);

        Echange result = echangeService.mettreAJourEchange(echange);

        assertEquals(echange, result);
        verify(echangeRepository, times(1)).save(echange);
    }

    @Test
    void echangeExist_ReturnsTrueIfExists() {
        Long id = 1L;
        when(echangeRepository.existsById(id)).thenReturn(true);

        boolean result = echangeService.echangeExist(id);

        assertTrue(result);
        verify(echangeRepository, times(1)).existsById(id);
    }

    @Test
    void echangeExist_ReturnsFalseIfNotExists() {
        Long id = 1L;
        when(echangeRepository.existsById(id)).thenReturn(false);

        boolean result = echangeService.echangeExist(id);

        assertFalse(result);
        verify(echangeRepository, times(1)).existsById(id);
    }
}
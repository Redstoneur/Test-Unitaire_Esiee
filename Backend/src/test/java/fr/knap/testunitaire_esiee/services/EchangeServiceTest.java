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

/**
 * Unit tests for the EchangeService class.
 */
@SpringBootTest
class EchangeServiceTest {

    @Mock
    private EchangeRepository echangeRepository;

    @InjectMocks
    private EchangeService echangeService;

    /**
     * Initializes mocks for the test class.
     */
    public EchangeServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    /**
     * Tests the creerEchange method to ensure it saves and returns the Echange object.
     */
    @Test
    void creerEchangeSavesAndReturnsEchange() {
        Echange echange = new Echange();
        when(echangeRepository.save(echange)).thenReturn(echange);

        Echange result = echangeService.creerEchange(echange);

        assertEquals(echange, result);
        verify(echangeRepository, times(1)).save(echange);
    }

    /**
     * Tests the obtenirTousLesEchanges method to ensure it returns all Echange objects.
     */
    @Test
    void obtenirTousLesEchangesReturnsAllEchanges() {
        List<Echange> echanges = Arrays.asList(new Echange(), new Echange());
        when(echangeRepository.findAll()).thenReturn(echanges);

        List<Echange> result = echangeService.obtenirTousLesEchanges();

        assertEquals(echanges, result);
        verify(echangeRepository, times(1)).findAll();
    }

    /**
     * Tests the obtenirEchangeParId method to ensure it returns the Echange object if it exists.
     */
    @Test
    void obtenirEchangeParIdReturnsEchangeIfExists() {
        Long id = 1L;
        Echange echange = new Echange();
        when(echangeRepository.findById(id)).thenReturn(Optional.of(echange));

        Echange result = echangeService.obtenirEchangeParId(id);

        assertEquals(echange, result);
        verify(echangeRepository, times(1)).findById(id);
    }

    /**
     * Tests the obtenirEchangeParId method to ensure it returns null if the Echange object does not exist.
     */
    @Test
    void obtenirEchangeParIdReturnsNullIfNotExists() {
        Long id = 1L;
        when(echangeRepository.findById(id)).thenReturn(Optional.empty());

        Echange result = echangeService.obtenirEchangeParId(id);

        assertNull(result);
        verify(echangeRepository, times(1)).findById(id);
    }

    /**
     * Tests the mettreAJourEchange method to ensure it saves and returns the updated Echange object.
     */
    @Test
    void mettreAJourEchangeSavesAndReturnsUpdatedEchange() {
        Echange echange = new Echange();
        when(echangeRepository.save(echange)).thenReturn(echange);

        Echange result = echangeService.mettreAJourEchange(echange);

        assertEquals(echange, result);
        verify(echangeRepository, times(1)).save(echange);
    }

    /**
     * Tests the echangeExist method to ensure it returns true if the Echange object exists.
     */
    @Test
    void echangeExistReturnsTrueIfExists() {
        Long id = 1L;
        when(echangeRepository.existsById(id)).thenReturn(true);

        boolean result = echangeService.echangeExist(id);

        assertTrue(result);
        verify(echangeRepository, times(1)).existsById(id);
    }

    /**
     * Tests the echangeExist method to ensure it returns false if the Echange object does not exist.
     */
    @Test
    void echangeExistReturnsFalseIfNotExists() {
        Long id = 1L;
        when(echangeRepository.existsById(id)).thenReturn(false);

        boolean result = echangeService.echangeExist(id);

        assertFalse(result);
        verify(echangeRepository, times(1)).existsById(id);
    }
}
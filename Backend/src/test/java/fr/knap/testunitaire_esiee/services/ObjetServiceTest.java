package fr.knap.testunitaire_esiee.services;

import fr.knap.testunitaire_esiee.model.Objet;
import fr.knap.testunitaire_esiee.respository.ObjetRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Unit tests for the ObjetService class.
 */
class ObjetServiceTest {

    @Mock
    private ObjetRepository objetRepository;

    @InjectMocks
    private ObjetService objetService;

    /**
     * Sets up the test environment before each test.
     * Initializes the mocks and injects them into the ObjetService instance.
     */
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    /**
     * Tests the creerObjet method of the ObjetService class.
     * Verifies that an object is created and saved correctly.
     */
    @Test
    void testCreerObjet() {
        Objet objet = new Objet();
        when(objetRepository.save(objet)).thenReturn(objet);
        assertEquals(objet, objetService.creerObjet(objet));
    }

    /**
     * Tests the obtenirTousLesObjets method of the ObjetService class.
     * Verifies that all objects are retrieved correctly.
     */
    @Test
    void testObtenirTousLesObjets() {
        objetService.obtenirTousLesObjets();
        verify(objetRepository, times(1)).findAll();
    }

    /**
     * Tests the obtenirObjetParId method of the ObjetService class.
     * Verifies that an object is retrieved correctly by its ID.
     */
    @Test
    void testObtenirObjetParId() {
        Objet objet = new Objet();
        when(objetRepository.findById(1L)).thenReturn(Optional.of(objet));
        assertEquals(objet, objetService.obtenirObjetParId(1L));
    }

    /**
     * Tests the mettreAJourObjet method of the ObjetService class.
     * Verifies that an object is updated correctly.
     */
    @Test
    void testMettreAJourObjet() {
        Objet objet = new Objet();
        when(objetRepository.existsById(1L)).thenReturn(true);
        when(objetRepository.save(objet)).thenReturn(objet);
        assertEquals(objet, objetService.mettreAJourObjet(1L, objet));
    }

    /**
     * Tests the supprimerObjet method of the ObjetService class.
     * Verifies that an object is deleted correctly by its ID.
     */
    @Test
    void testSupprimerObjet() {
        objetService.supprimerObjet(1L);
        verify(objetRepository, times(1)).deleteById(1L);
    }

    /**
     * Tests the obtenirObjetsParUtilisateur method of the ObjetService class.
     * Verifies that objects are retrieved correctly by user ID.
     */
    @Test
    void testObtenirObjetsParUtilisateur() {
        objetService.obtenirObjetsParUtilisateur(1L);
        verify(objetRepository, times(1)).findByUtilisateurId(1L);
    }
}
package fr.knap.testunitaire_esiee.controller;

import fr.knap.testunitaire_esiee.model.Objet;
import fr.knap.testunitaire_esiee.services.ObjetService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Unit tests for the ObjetController class.
 */
class ObjetControllerTest {

    @Mock
    private ObjetService objetService;

    @InjectMocks
    private ObjetController objetController;

    /**
     * Sets up the test environment before each test.
     * Initializes mocks and injects them into the controller.
     */
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    /**
     * Tests the creerObjet method of the ObjetController.
     * Verifies that the created Objet entity is returned correctly.
     */
    @Test
    void testCreerObjet() {
        Objet objet = new Objet();
        when(objetService.creerObjet(objet)).thenReturn(objet);
        assertEquals(objet, objetController.creerObjet(objet));
    }

    /**
     * Tests the obtenirTousLesObjets method of the ObjetController.
     * Verifies that the obtenirTousLesObjets method of the ObjetService is called once.
     */
    @Test
    void testObtenirTousLesObjets() {
        objetController.obtenirTousLesObjets();
        verify(objetService, times(1)).obtenirTousLesObjets();
    }

    /**
     * Tests the obtenirObjetsParUtilisateur method of the ObjetController.
     * Verifies that the obtenirObjetsParUtilisateur method of the ObjetService is called once.
     */
    @Test
    void testObtenirObjetsParUtilisateur() {
        objetController.obtenirObjetsParUtilisateur(1L);
        verify(objetService, times(1)).obtenirObjetsParUtilisateur(1L);
    }

    /**
     * Tests the obtenirObjetParId method of the ObjetController.
     * Verifies that the correct Objet entity is returned for the given ID.
     */
    @Test
    void testObtenirObjetParId() {
        Objet objet = new Objet();
        when(objetService.obtenirObjetParId(1L)).thenReturn(objet);
        assertEquals(objet, objetController.obtenirObjetParId(1L));
    }

    /**
     * Tests the mettreAJourObjet method of the ObjetController.
     * Verifies that the updated Objet entity is returned correctly.
     */
    @Test
    void testMettreAJourObjet() {
        Objet objet = new Objet();
        when(objetService.mettreAJourObjet(1L, objet)).thenReturn(objet);
        assertEquals(objet, objetController.mettreAJourObjet(1L, objet));
    }

    /**
     * Tests the supprimerObjet method of the ObjetController.
     * Verifies that the supprimerObjet method of the ObjetService is called once.
     */
    @Test
    void testSupprimerObjet() {
        objetController.supprimerObjet(1L);
        verify(objetService, times(1)).supprimerObjet(1L);
    }
}
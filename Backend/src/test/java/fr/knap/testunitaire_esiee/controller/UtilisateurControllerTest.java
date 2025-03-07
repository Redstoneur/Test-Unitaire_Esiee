package fr.knap.testunitaire_esiee.controller;

import fr.knap.testunitaire_esiee.model.Utilisateur;
import fr.knap.testunitaire_esiee.services.UtilisateurService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Unit tests for the UtilisateurController class.
 */
class UtilisateurControllerTest {

    @Mock
    private UtilisateurService utilisateurService;

    @InjectMocks
    private UtilisateurController utilisateurController;

    /**
     * Sets up the test environment before each test.
     * Initializes mocks and injects them into the controller.
     */
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    /**
     * Tests the obtenirTousLesUtilisateurs method of the UtilisateurController.
     * Verifies that the obtenirTousLesUtilisateurs method of the UtilisateurService is called once.
     */
    @Test
    void testObtenirTousLesUtilisateurs() {
        utilisateurController.obtenirTousLesUtilisateurs();
        verify(utilisateurService, times(1)).obtenirTousLesUtilisateurs();
    }

    /**
     * Tests the obtenirUtilisateurParId method of the UtilisateurController.
     * Verifies that the correct Utilisateur entity is returned for the given ID.
     */
    @Test
    void testObtenirUtilisateurParId() {
        Utilisateur utilisateur = new Utilisateur();
        when(utilisateurService.obtenirUtilisateurParId(1L)).thenReturn(utilisateur);
        assertEquals(utilisateur, utilisateurController.obtenirUtilisateurInfoParId(1L));
    }

    /**
     * Tests the mettreAJourUtilisateur method of the UtilisateurController.
     * Verifies that the updated Utilisateur entity is returned correctly.
     */
    @Test
    void testMettreAJourUtilisateur() {
        Utilisateur utilisateur = new Utilisateur();
        when(utilisateurService.mettreAJourUtilisateur(1L, utilisateur)).thenReturn(utilisateur);
        assertEquals(utilisateur, utilisateurController.mettreAJourUtilisateur(1L, utilisateur));
    }

    /**
     * Tests the supprimerUtilisateur method of the UtilisateurController.
     * Verifies that the supprimerUtilisateur method of the UtilisateurService is called once.
     */
    @Test
    void testSupprimerUtilisateur() {
        utilisateurController.supprimerUtilisateur(1L);
        verify(utilisateurService, times(1)).supprimerUtilisateur(1L);
    }
}
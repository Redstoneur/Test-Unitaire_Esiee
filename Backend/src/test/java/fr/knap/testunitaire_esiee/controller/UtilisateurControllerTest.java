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

class UtilisateurControllerTest {

    @Mock
    private UtilisateurService utilisateurService;

    @InjectMocks
    private UtilisateurController utilisateurController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testObtenirTousLesUtilisateurs() {
        utilisateurController.obtenirTousLesUtilisateurs();
        verify(utilisateurService, times(1)).obtenirTousLesUtilisateurs();
    }

    @Test
    void testObtenirUtilisateurParId() {
        Utilisateur utilisateur = new Utilisateur();
        when(utilisateurService.obtenirUtilisateurParId(1L)).thenReturn(utilisateur);
        assertEquals(utilisateur, utilisateurController.obtenirUtilisateurParId(1L));
    }


    @Test
    void testMettreAJourUtilisateur() {
        Utilisateur utilisateur = new Utilisateur();
        when(utilisateurService.mettreAJourUtilisateur(1L, utilisateur)).thenReturn(utilisateur);
        assertEquals(utilisateur, utilisateurController.mettreAJourUtilisateur(1L, utilisateur));
    }

    @Test
    void testSupprimerUtilisateur() {
        utilisateurController.supprimerUtilisateur(1L);
        verify(utilisateurService, times(1)).supprimerUtilisateur(1L);
    }
}
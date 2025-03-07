package fr.knap.testunitaire_esiee.controller;

import fr.knap.testunitaire_esiee.dto.UtilisateurDTO;
import fr.knap.testunitaire_esiee.model.Utilisateur;
import fr.knap.testunitaire_esiee.services.UtilisateurService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Unit tests for the UtilisateurController class.
 */
@SpringBootTest
class UtilisateurControllerTest {

    @Mock
    private UtilisateurService utilisateurService;

    @InjectMocks
    private UtilisateurController utilisateurController;

    /**
     * Initializes mocks for the test class.
     */
    public UtilisateurControllerTest() {
        MockitoAnnotations.openMocks(this);
    }

    /**
     * Tests the obtenirTousLesUtilisateurs method to ensure it returns all Utilisateurs.
     */
    @Test
    void obtenirTousLesUtilisateurs_ReturnsAllUtilisateurs() {
        List<Utilisateur> utilisateurs = Arrays.asList(new Utilisateur(), new Utilisateur());
        when(utilisateurService.obtenirTousLesUtilisateurs()).thenReturn(utilisateurs);

        List<Utilisateur> result = utilisateurController.obtenirTousLesUtilisateurs();

        assertEquals(utilisateurs, result);
        verify(utilisateurService, times(1)).obtenirTousLesUtilisateurs();
    }

    /**
     * Tests the obtenirUtilisateurInfoParId method to ensure it returns UtilisateurDTO if the token is valid.
     */
    @Test
    void obtenirUtilisateurInfoParId_ReturnsUtilisateurDTOIfTokenValid() {
        String authToken = "validToken";
        UtilisateurDTO utilisateurDTO = new UtilisateurDTO(
                "pseudo",
                "nom",
                "prenom"
        );
        when(utilisateurService.verifyToken(authToken)).thenReturn(true);
        when(utilisateurService.obtenirUtilisateurInfoParToken(authToken)).thenReturn(utilisateurDTO);

        UtilisateurDTO result = utilisateurController.obtenirUtilisateurInfoParId(authToken);

        assertEquals(utilisateurDTO, result);
        verify(utilisateurService, times(1)).verifyToken(authToken);
        verify(utilisateurService, times(1)).obtenirUtilisateurInfoParToken(authToken);
    }

    /**
     * Tests the obtenirUtilisateurInfoParId method to ensure it throws a Forbidden exception if the token is invalid.
     */
    @Test
    void obtenirUtilisateurInfoParId_ThrowsForbiddenIfTokenInvalid() {
        String authToken = "invalidToken";
        when(utilisateurService.verifyToken(authToken)).thenReturn(false);

        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
            utilisateurController.obtenirUtilisateurInfoParId(authToken);
        });

        assertEquals(HttpStatus.FORBIDDEN, exception.getStatusCode());
        verify(utilisateurService, times(1)).verifyToken(authToken);
        verify(utilisateurService, times(0)).obtenirUtilisateurInfoParToken(anyString());
    }

    /**
     * Tests the obtenirUtilisateurParId method to ensure it returns Utilisateur if the token is valid.
     */
    @Test
    void obtenirUtilisateurParId_ReturnsUtilisateurIfTokenValid() {
        String authToken = "validToken";
        Long id = 1L;
        Utilisateur utilisateur = new Utilisateur();
        when(utilisateurService.verifyToken(authToken)).thenReturn(true);
        when(utilisateurService.obtenirUtilisateurParId(id)).thenReturn(utilisateur);

        Utilisateur result = utilisateurController.obtenirUtilisateurParId(authToken, id);

        assertEquals(utilisateur, result);
        verify(utilisateurService, times(1)).verifyToken(authToken);
        verify(utilisateurService, times(1)).obtenirUtilisateurParId(id);
    }

    /**
     * Tests the obtenirUtilisateurParId method to ensure it throws a Forbidden exception if the token is invalid.
     */
    @Test
    void obtenirUtilisateurParId_ThrowsForbiddenIfTokenInvalid() {
        String authToken = "invalidToken";
        Long id = 1L;
        when(utilisateurService.verifyToken(authToken)).thenReturn(false);

        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
            utilisateurController.obtenirUtilisateurParId(authToken, id);
        });

        assertEquals(HttpStatus.FORBIDDEN, exception.getStatusCode());
        verify(utilisateurService, times(1)).verifyToken(authToken);
        verify(utilisateurService, times(0)).obtenirUtilisateurParId(anyLong());
    }

    /**
     * Tests the obtenirUtilisateurPseudoParId method to ensure it returns UtilisateurDTO if the token is valid.
     */
    @Test
    void obtenirUtilisateurPseudoParId_ReturnsUtilisateurDTOIfTokenValid() {
        String authToken = "validToken";
        Long id = 1L;
        UtilisateurDTO utilisateurDTO = new UtilisateurDTO(
                "pseudo",
                "nom",
                "prenom"
        );
        when(utilisateurService.verifyToken(authToken)).thenReturn(true);
        when(utilisateurService.obtenirUtilisateurPseudoParId(id)).thenReturn(utilisateurDTO);

        UtilisateurDTO result = utilisateurController.obtenirUtilisateurPseudoParId(authToken, id);

        assertEquals(utilisateurDTO, result);
        verify(utilisateurService, times(1)).verifyToken(authToken);
        verify(utilisateurService, times(1)).obtenirUtilisateurPseudoParId(id);
    }

    /**
     * Tests the obtenirUtilisateurPseudoParId method to ensure it throws a Forbidden exception if the token is invalid.
     */
    @Test
    void obtenirUtilisateurPseudoParId_ThrowsForbiddenIfTokenInvalid() {
        String authToken = "invalidToken";
        Long id = 1L;
        when(utilisateurService.verifyToken(authToken)).thenReturn(false);

        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
            utilisateurController.obtenirUtilisateurPseudoParId(authToken, id);
        });

        assertEquals(HttpStatus.FORBIDDEN, exception.getStatusCode());
        verify(utilisateurService, times(1)).verifyToken(authToken);
        verify(utilisateurService, times(0)).obtenirUtilisateurPseudoParId(anyLong());
    }

    /**
     * Tests the mettreAJourUtilisateur method to ensure it returns the updated Utilisateur if the token is valid.
     */
    @Test
    void mettreAJourUtilisateur_ReturnsUpdatedUtilisateurIfTokenValid() {
        String authToken = "validToken";
        Long id = 1L;
        Utilisateur utilisateur = new Utilisateur();
        when(utilisateurService.verifyToken(authToken)).thenReturn(true);
        when(utilisateurService.mettreAJourUtilisateur(id, utilisateur)).thenReturn(utilisateur);

        Utilisateur result = utilisateurController.mettreAJourUtilisateur(authToken, id, utilisateur);

        assertEquals(utilisateur, result);
        verify(utilisateurService, times(1)).verifyToken(authToken);
        verify(utilisateurService, times(1)).mettreAJourUtilisateur(id, utilisateur);
    }

    /**
     * Tests the mettreAJourUtilisateur method to ensure it throws a Forbidden exception if the token is invalid.
     */
    @Test
    void mettreAJourUtilisateur_ThrowsForbiddenIfTokenInvalid() {
        String authToken = "invalidToken";
        Long id = 1L;
        Utilisateur utilisateur = new Utilisateur();

        when(utilisateurService.verifyToken(authToken)).thenReturn(false);

        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
            utilisateurController.mettreAJourUtilisateur(authToken, id, utilisateur);
        });

        assertEquals(HttpStatus.FORBIDDEN, exception.getStatusCode());
        verify(utilisateurService, times(1)).verifyToken(authToken);
        verify(utilisateurService, times(0)).mettreAJourUtilisateur(anyLong(), any(Utilisateur.class));
    }

    /**
     * Tests the supprimerUtilisateur method to ensure it deletes the Utilisateur if the token is valid.
     */
    @Test
    void supprimerUtilisateur_DeletesUtilisateurIfTokenValid() {
        String authToken = "validToken";
        Long id = 1L;

        when(utilisateurService.verifyToken(authToken)).thenReturn(true);

        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
            utilisateurController.supprimerUtilisateur(authToken, id);
        });

        assertEquals(HttpStatus.OK, exception.getStatusCode());
        verify(utilisateurService, times(1)).verifyToken(authToken);
        verify(utilisateurService, times(1)).supprimerUtilisateur(id);
    }

    /**
     * Tests the supprimerUtilisateur method to ensure it throws a Forbidden exception if the token is invalid.
     */
    @Test
    void supprimerUtilisateur_ThrowsForbiddenIfTokenInvalid() {
        String authToken = "invalidToken";
        Long id = 1L;

        when(utilisateurService.verifyToken(authToken)).thenReturn(false);

        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
            utilisateurController.supprimerUtilisateur(authToken, id);
        });

        assertEquals(HttpStatus.FORBIDDEN, exception.getStatusCode());
        verify(utilisateurService, times(1)).verifyToken(authToken);
        verify(utilisateurService, times(0)).supprimerUtilisateur(anyLong());
    }
}
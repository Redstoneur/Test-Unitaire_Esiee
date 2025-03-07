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

@SpringBootTest
class UtilisateurControllerTest {

    @Mock
    private UtilisateurService utilisateurService;

    @InjectMocks
    private UtilisateurController utilisateurController;

    public UtilisateurControllerTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void obtenirTousLesUtilisateurs_ReturnsAllUtilisateurs() {
        List<Utilisateur> utilisateurs = Arrays.asList(new Utilisateur(), new Utilisateur());
        when(utilisateurService.obtenirTousLesUtilisateurs()).thenReturn(utilisateurs);

        List<Utilisateur> result = utilisateurController.obtenirTousLesUtilisateurs();

        assertEquals(utilisateurs, result);
        verify(utilisateurService, times(1)).obtenirTousLesUtilisateurs();
    }

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
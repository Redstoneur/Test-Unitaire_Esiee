package fr.knap.testunitaire_esiee.controller;

import fr.knap.testunitaire_esiee.model.Credentials;
import fr.knap.testunitaire_esiee.model.Token;
import fr.knap.testunitaire_esiee.model.TokenCredential;
import fr.knap.testunitaire_esiee.model.Utilisateur;
import fr.knap.testunitaire_esiee.services.UtilisateurService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CredentialsControllerTest {

    @Mock
    private UtilisateurService utilisateurService;

    @InjectMocks
    private CredentialsController credentialsController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreerUtilisateur() {
        Utilisateur utilisateur = new Utilisateur();
        when(utilisateurService.creerUtilisateur(utilisateur)).thenReturn(utilisateur);
        assertEquals(utilisateur, credentialsController.creerUtilisateur(utilisateur));
    }

    @Test
    void testGetConnexionToken() {
        Credentials credentials = new Credentials("test@mail.com", "password");
        Token token = new Token("test@mail.com", "password");
        when(utilisateurService.login(credentials)).thenReturn(token);
        assertEquals(token, credentialsController.getConnexionToken(credentials));
    }

// Todo: Fix this test
//    @Test
//    void testDisconnect() {
//        Token token = new Token("test@mail.com", "password");
//        when(utilisateurService.verifyToken(token.getToken())).thenReturn(true);
//        doNothing().when(utilisateurService).disconnect(token);
//        assertThrows(ResponseStatusException.class, () -> credentialsController.disconnect(token));
//    }

    @Test
    void testVerifyToken() {
        Token token = new Token("test@mail.com", "password");
        when(utilisateurService.verifyToken(token.getToken())).thenReturn(true);
        assertThrows(ResponseStatusException.class, () -> credentialsController.verifyToken(new TokenCredential(token.getToken())));
    }
}
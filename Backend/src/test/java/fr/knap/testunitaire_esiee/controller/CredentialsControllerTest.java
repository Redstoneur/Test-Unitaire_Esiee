package fr.knap.testunitaire_esiee.controller;

import fr.knap.testunitaire_esiee.dto.TokenCredentialDTO;
import fr.knap.testunitaire_esiee.model.Credentials;
import fr.knap.testunitaire_esiee.model.Token;
import fr.knap.testunitaire_esiee.model.Utilisateur;
import fr.knap.testunitaire_esiee.services.UtilisateurService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class CredentialsControllerTest {

    @Mock
    private UtilisateurService utilisateurService;

    @InjectMocks
    private CredentialsController credentialsController;

    public CredentialsControllerTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void creerUtilisateur_ReturnsTokenCredentialDTO() {
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setMail("user@example.com");
        utilisateur.setMdp("password");
        Token token = new Token();
        token.setToken("sampleToken");

        when(utilisateurService.creerUtilisateur(utilisateur)).thenReturn(utilisateur);
        when(utilisateurService.login(any(Credentials.class))).thenReturn(token);

        TokenCredentialDTO result = credentialsController.creerUtilisateur(utilisateur);

        assertEquals("sampleToken", result.getToken());
        verify(utilisateurService, times(1)).creerUtilisateur(utilisateur);
        verify(utilisateurService, times(1)).login(any(Credentials.class));
    }

    @Test
    void getConnexionToken_ReturnsTokenCredentialDTOIfValid() {
        Credentials credentials = new Credentials("user@example.com", "password");
        Token token = new Token();
        token.setToken("sampleToken");

        when(utilisateurService.login(credentials)).thenReturn(token);

        TokenCredentialDTO result = credentialsController.getConnexionToken(credentials);

        assertEquals("sampleToken", result.getToken());
        verify(utilisateurService, times(1)).login(credentials);
    }

    @Test
    void getConnexionToken_ThrowsExceptionIfInvalid() {
        Credentials credentials = new Credentials("user@example.com", "wrongpassword");

        when(utilisateurService.login(credentials)).thenReturn(null);

        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
            credentialsController.getConnexionToken(credentials);
        });

        assertEquals(HttpStatus.NOT_FOUND, exception.getStatusCode());
        verify(utilisateurService, times(1)).login(credentials);
    }

    @Test
    void disconnect_InvalidatesTokenIfValid() {
        TokenCredentialDTO tokenDTO = new TokenCredentialDTO("validToken");

        when(utilisateurService.verifyToken(tokenDTO.getToken())).thenReturn(true);

        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
            credentialsController.disconnect(tokenDTO);
        });

        assertEquals(HttpStatus.OK, exception.getStatusCode());
        verify(utilisateurService, times(1)).verifyToken(tokenDTO.getToken());
        verify(utilisateurService, times(1)).disconnect(tokenDTO.getToken());
    }

    @Test
    void disconnect_ThrowsExceptionIfInvalid() {
        TokenCredentialDTO tokenDTO = new TokenCredentialDTO("invalidToken");

        when(utilisateurService.verifyToken(tokenDTO.getToken())).thenReturn(false);

        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
            credentialsController.disconnect(tokenDTO);
        });

        assertEquals(HttpStatus.UNAUTHORIZED, exception.getStatusCode());
        verify(utilisateurService, times(1)).verifyToken(tokenDTO.getToken());
        verify(utilisateurService, times(0)).disconnect(tokenDTO.getToken());
    }

    @Test
    void verifyToken_ReturnsOkIfValid() {
        TokenCredentialDTO tokenDTO = new TokenCredentialDTO("validToken");

        when(utilisateurService.verifyToken(tokenDTO.getToken())).thenReturn(true);

        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
            credentialsController.verifyToken(tokenDTO);
        });

        assertEquals(HttpStatus.OK, exception.getStatusCode());
        verify(utilisateurService, times(1)).verifyToken(tokenDTO.getToken());
    }

    @Test
    void verifyToken_ThrowsExceptionIfInvalid() {
        TokenCredentialDTO tokenDTO = new TokenCredentialDTO("invalidToken");

        when(utilisateurService.verifyToken(tokenDTO.getToken())).thenReturn(false);

        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
            credentialsController.verifyToken(tokenDTO);
        });

        assertEquals(HttpStatus.UNAUTHORIZED, exception.getStatusCode());
        verify(utilisateurService, times(1)).verifyToken(tokenDTO.getToken());
    }
}
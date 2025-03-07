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

/**
 * Unit tests for the CredentialsController class.
 */
@SpringBootTest
class CredentialsControllerTest {

    @Mock
    private UtilisateurService utilisateurService;

    @InjectMocks
    private CredentialsController credentialsController;

    /**
     * Initializes mocks for the test class.
     */
    public CredentialsControllerTest() {
        MockitoAnnotations.openMocks(this);
    }

    /**
     * Tests the creerUtilisateur method to ensure it returns a TokenCredentialDTO.
     */
    @Test
    void creerUtilisateurReturnsTokenCredentialDTO() {
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

    /**
     * Tests the getConnexionToken method to ensure it returns a TokenCredentialDTO if credentials are valid.
     */
    @Test
    void getConnexionTokenReturnsTokenCredentialDTOIfValid() {
        Credentials credentials = new Credentials("user@example.com", "password");
        Token token = new Token();
        token.setToken("sampleToken");

        when(utilisateurService.login(credentials)).thenReturn(token);

        TokenCredentialDTO result = credentialsController.getConnexionToken(credentials);

        assertEquals("sampleToken", result.getToken());
        verify(utilisateurService, times(1)).login(credentials);
    }

    /**
     * Tests the getConnexionToken method to ensure it throws an exception if credentials are invalid.
     */
    @Test
    void getConnexionTokenThrowsExceptionIfInvalid() {
        Credentials credentials = new Credentials("user@example.com", "wrongpassword");

        when(utilisateurService.login(credentials)).thenReturn(null);

        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
            credentialsController.getConnexionToken(credentials);
        });

        assertEquals(HttpStatus.NOT_FOUND, exception.getStatusCode());
        verify(utilisateurService, times(1)).login(credentials);
    }

    /**
     * Tests the disconnect method to ensure it invalidates the token if it is valid.
     */
    @Test
    void disconnectInvalidatesTokenIfValid() {
        TokenCredentialDTO tokenDTO = new TokenCredentialDTO("validToken");

        when(utilisateurService.verifyToken(tokenDTO.getToken())).thenReturn(true);

        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
            credentialsController.disconnect(tokenDTO);
        });

        assertEquals(HttpStatus.OK, exception.getStatusCode());
        verify(utilisateurService, times(1)).verifyToken(tokenDTO.getToken());
        verify(utilisateurService, times(1)).disconnect(tokenDTO.getToken());
    }

    /**
     * Tests the disconnect method to ensure it throws an exception if the token is invalid.
     */
    @Test
    void disconnectThrowsExceptionIfInvalid() {
        TokenCredentialDTO tokenDTO = new TokenCredentialDTO("invalidToken");

        when(utilisateurService.verifyToken(tokenDTO.getToken())).thenReturn(false);

        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
            credentialsController.disconnect(tokenDTO);
        });

        assertEquals(HttpStatus.UNAUTHORIZED, exception.getStatusCode());
        verify(utilisateurService, times(1)).verifyToken(tokenDTO.getToken());
        verify(utilisateurService, times(0)).disconnect(tokenDTO.getToken());
    }

    /**
     * Tests the verifyToken method to ensure it returns OK if the token is valid.
     */
    @Test
    void verifyTokenReturnsOkIfValid() {
        TokenCredentialDTO tokenDTO = new TokenCredentialDTO("validToken");

        when(utilisateurService.verifyToken(tokenDTO.getToken())).thenReturn(true);

        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
            credentialsController.verifyToken(tokenDTO);
        });

        assertEquals(HttpStatus.OK, exception.getStatusCode());
        verify(utilisateurService, times(1)).verifyToken(tokenDTO.getToken());
    }

    /**
     * Tests the verifyToken method to ensure it throws an exception if the token is invalid.
     */
    @Test
    void verifyTokenThrowsExceptionIfInvalid() {
        TokenCredentialDTO tokenDTO = new TokenCredentialDTO("invalidToken");

        when(utilisateurService.verifyToken(tokenDTO.getToken())).thenReturn(false);

        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
            credentialsController.verifyToken(tokenDTO);
        });

        assertEquals(HttpStatus.UNAUTHORIZED, exception.getStatusCode());
        verify(utilisateurService, times(1)).verifyToken(tokenDTO.getToken());
    }
}
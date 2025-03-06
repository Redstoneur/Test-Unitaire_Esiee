package fr.knap.testunitaire_esiee.controller;

import fr.knap.testunitaire_esiee.model.*;
import fr.knap.testunitaire_esiee.services.UtilisateurService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.server.ResponseStatusException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Unit tests for the CredentialsController class.
 */
class CredentialsControllerTest {

    @Mock
    private UtilisateurService utilisateurService;

    @InjectMocks
    private CredentialsController credentialsController;

    /**
     * Sets up the test environment before each test.
     * Initializes mocks and injects them into the controller.
     */
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    //    TODO: Fix the test
    //   /**
    //     * Tests the createUtilisateur method of the CredentialsController.
    //     * Verifies that a ResponseStatusException is thrown when the user already exists.
    //     */
    //    @Test
    //    void testCreerUtilisateur() {
    //        Utilisateur utilisateur = new Utilisateur("ddd","test","test@gmail.com","nulle","nulle", new ArrayList<>());
    //        System.out.println(credentialsController.creerUtilisateur(utilisateur));
    //        assertTrue(false);
    //        //assertEquals(TokenCredential.class, credentialsController.creerUtilisateur(utilisateur).getClass());
    //
    //    }
    //
    //    /**
    //     * Tests the getConnexionToken method of the CredentialsController.
    //     * Verifies that a ResponseStatusException is thrown when the credentials are invalid.
    //     */
    //    @Test
    //    void testGetConnexionToken() {
    //        Credentials credentials = new Credentials("aaaaaa", "aaaa");
    //        assertEquals(Token.class,credentialsController.getConnexionToken(credentials).getClass());
    //    }

    /**
     * Tests the disconnect method of the CredentialsController.
     * Verifies that a ResponseStatusException is thrown when the token is valid.
     */
    @Test
    void testDisconnect() {
        Token token = new Token("test@mail.com", "password");
        when(utilisateurService.verifyToken(token.getToken())).thenReturn(true);
        doNothing().when(utilisateurService).disconnect(token.getToken());
        assertThrows(
                ResponseStatusException.class,
                () -> credentialsController.disconnect(new TokenCredential(token.getToken()))
        );
    }

    /**
     * Tests the verifyToken method of the CredentialsController.
     * Verifies that a ResponseStatusException is thrown when the token is valid.
     */
    @Test
    void testVerifyToken() {
        Token token = new Token("test@mail.com", "password");
        when(utilisateurService.verifyToken(token.getToken())).thenReturn(true);
        assertThrows(
                ResponseStatusException.class,
                () -> credentialsController.verifyToken(new TokenCredential(token.getToken()))
        );
    }
}
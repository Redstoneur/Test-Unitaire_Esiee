package fr.knap.testunitaire_esiee.services;

import fr.knap.testunitaire_esiee.model.Utilisateur;
import fr.knap.testunitaire_esiee.repository.UtilisateurRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Unit tests for the UtilisateurService class.
 */
class UtilisateurServiceTest {

    @Mock
    private UtilisateurRepository utilisateurRepository;

    @InjectMocks
    private UtilisateurService utilisateurService;

    /**
     * Sets up the test environment before each test.
     * Initializes the mocks and injects them into the UtilisateurService instance.
     */
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    /**
     * Tests the creerUtilisateur method of the UtilisateurService class.
     * Verifies that a user is created and saved correctly.
     */
    @Test
    void testCreerUtilisateur() {
        Utilisateur utilisateur = new Utilisateur();
        when(utilisateurRepository.save(utilisateur)).thenReturn(utilisateur);
        assertEquals(utilisateur, utilisateurService.creerUtilisateur(utilisateur));
    }

    /**
     * Tests the obtenirTousLesUtilisateurs method of the UtilisateurService class.
     * Verifies that all users are retrieved correctly.
     */
    @Test
    void testObtenirTousLesUtilisateurs() {
        utilisateurService.obtenirTousLesUtilisateurs();
        verify(utilisateurRepository, times(1)).findAll();
    }

    /**
     * Tests the obtenirUtilisateurParId method of the UtilisateurService class.
     * Verifies that a user is retrieved correctly by its ID.
     */
    @Test
    void testObtenirUtilisateurParId() {
        Utilisateur utilisateur = new Utilisateur();
        when(utilisateurRepository.findById(1L)).thenReturn(Optional.of(utilisateur));
        assertEquals(utilisateur, utilisateurService.obtenirUtilisateurParId(1L));
    }

    /**
     * Tests the mettreAJourUtilisateur method of the UtilisateurService class.
     * Verifies that a user is updated correctly.
     */
    @Test
    void testMettreAJourUtilisateur() {
        Utilisateur utilisateur = new Utilisateur();
        when(utilisateurRepository.existsById(1L)).thenReturn(true);
        when(utilisateurRepository.save(utilisateur)).thenReturn(utilisateur);
        assertEquals(utilisateur, utilisateurService.mettreAJourUtilisateur(1L, utilisateur));
    }

    // TODO: Fix the test
    // /**
    //  * Tests the login method of the UtilisateurService class.
    //  * Verifies that a user is logged in correctly.
    //  */
    // @Test
    // void testLogin() {
    //     Credentials credentials = new Credentials("test@mail.com", "password");
    //     when(utilisateurRepository.existsByMail(credentials.getMail())).thenReturn(true);
    //     when(utilisateurRepository.existsByMdp(credentials.getMdp())).thenReturn(true);
    //     assertNotNull(utilisateurService.login(credentials));
    // }

    // TODO: Fix the test
    // /**
    //  * Tests the login method of the UtilisateurService class.
    //  * Verifies that a user is not logged in with incorrect credentials.
    //  */
    // @Test
    // void testDisconnect() {
    //     Token token = new Token("test@mail.com", "password");
    //     token.disconnect();
    //     assertNull(token.getToken());
    // }

    // TODO: Fix the test
    // /**
    //  * Tests the verifyToken method of the UtilisateurService class.
    //  * Verifies that a token is verified correctly.
    //  */
    // @Test
    // void testVerifyToken() {
    //     Token token = new Token("test@mail.com", "password");
    //     assertTrue(utilisateurService.verifyToken(token.getToken()));
    // }

    /**
     * Tests the supprimerUtilisateur method of the UtilisateurService class.
     * Verifies that a user is deleted correctly by its ID.
     */
    @Test
    void testSupprimerUtilisateur() {
        utilisateurService.supprimerUtilisateur(1L);
        verify(utilisateurRepository, times(1)).deleteById(1L);
    }
}
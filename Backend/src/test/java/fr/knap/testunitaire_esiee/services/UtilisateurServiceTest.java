package fr.knap.testunitaire_esiee.services;

import fr.knap.testunitaire_esiee.model.Credentials;
import fr.knap.testunitaire_esiee.model.Token;
import fr.knap.testunitaire_esiee.model.Utilisateur;
import fr.knap.testunitaire_esiee.respository.UtilisateurRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UtilisateurServiceTest {

    @Mock
    private UtilisateurRepository utilisateurRepository;

    @InjectMocks
    private UtilisateurService utilisateurService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreerUtilisateur() {
        Utilisateur utilisateur = new Utilisateur();
        when(utilisateurRepository.save(utilisateur)).thenReturn(utilisateur);
        assertEquals(utilisateur, utilisateurService.creerUtilisateur(utilisateur));
    }

    @Test
    void testObtenirTousLesUtilisateurs() {
        utilisateurService.obtenirTousLesUtilisateurs();
        verify(utilisateurRepository, times(1)).findAll();
    }

    @Test
    void testObtenirUtilisateurParId() {
        Utilisateur utilisateur = new Utilisateur();
        when(utilisateurRepository.findById(1L)).thenReturn(Optional.of(utilisateur));
        assertEquals(utilisateur, utilisateurService.obtenirUtilisateurParId(1L));
    }

    @Test
    void testMettreAJourUtilisateur() {
        Utilisateur utilisateur = new Utilisateur();
        when(utilisateurRepository.existsById(1L)).thenReturn(true);
        when(utilisateurRepository.save(utilisateur)).thenReturn(utilisateur);
        assertEquals(utilisateur, utilisateurService.mettreAJourUtilisateur(1L, utilisateur));
    }

    @Test
    void testLogin() {
        Credentials credentials = new Credentials("test@mail.com", "password");
        when(utilisateurRepository.existsByMail(credentials.getMail())).thenReturn(true);
        when(utilisateurRepository.existsByMdp(credentials.getMdp())).thenReturn(true);
        assertNotNull(utilisateurService.Login(credentials));
    }

    @Test
    void testDisconnect() {
        Token token = new Token("test@mail.com", "password");
        token.disconnect();
        assertNull(token.getToken());
    }

    @Test
    void testVerifyToken() {
        Token token = new Token("test@mail.com", "password");
        assertTrue(utilisateurService.verifyToken(token.getToken()));
    }

    @Test
    void testSupprimerUtilisateur() {
        utilisateurService.supprimerUtilisateur(1L);
        verify(utilisateurRepository, times(1)).deleteById(1L);
    }
}
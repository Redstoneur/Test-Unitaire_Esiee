package fr.knap.testunitaire_esiee.services;

import fr.knap.testunitaire_esiee.dto.UtilisateurDTO;
import fr.knap.testunitaire_esiee.model.Credentials;
import fr.knap.testunitaire_esiee.model.Token;
import fr.knap.testunitaire_esiee.model.Utilisateur;
import fr.knap.testunitaire_esiee.repository.UtilisateurRepository;
import fr.knap.testunitaire_esiee.repository.TokenRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class UtilisateurServiceTest {

    @Mock
    private UtilisateurRepository utilisateurRepository;

    @Mock
    private TokenRepository tokenRepository;

    @InjectMocks
    private UtilisateurService utilisateurService;

    public UtilisateurServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void creerUtilisateur_SavesAndReturnsUtilisateur() {
        Utilisateur utilisateur = new Utilisateur();
        when(utilisateurRepository.save(utilisateur)).thenReturn(utilisateur);

        Utilisateur result = utilisateurService.creerUtilisateur(utilisateur);

        assertEquals(utilisateur, result);
        verify(utilisateurRepository, times(1)).save(utilisateur);
    }

    @Test
    void obtenirTousLesUtilisateurs_ReturnsAllUtilisateurs() {
        List<Utilisateur> utilisateurs = Arrays.asList(new Utilisateur(), new Utilisateur());
        when(utilisateurRepository.findAll()).thenReturn(utilisateurs);

        List<Utilisateur> result = utilisateurService.obtenirTousLesUtilisateurs();

        assertEquals(utilisateurs, result);
        verify(utilisateurRepository, times(1)).findAll();
    }

    @Test
    void obtenirUtilisateurParId_ReturnsUtilisateurIfExists() {
        Long id = 1L;
        Utilisateur utilisateur = new Utilisateur();
        when(utilisateurRepository.findById(id)).thenReturn(Optional.of(utilisateur));

        Utilisateur result = utilisateurService.obtenirUtilisateurParId(id);

        assertEquals(utilisateur, result);
        verify(utilisateurRepository, times(1)).findById(id);
    }

    @Test
    void obtenirUtilisateurParId_ReturnsNullIfNotExists() {
        Long id = 1L;
        when(utilisateurRepository.findById(id)).thenReturn(Optional.empty());

        Utilisateur result = utilisateurService.obtenirUtilisateurParId(id);

        assertNull(result);
        verify(utilisateurRepository, times(1)).findById(id);
    }

    @Test
    void obtenirUtilisateurInfoParId_ReturnsUtilisateurDTOIfExists() {
        Long id = 1L;
        Utilisateur utilisateur = new Utilisateur(
                "user123", "mdp", "Doe.John@mail.com", "Doe", "John"
        );
        when(utilisateurRepository.findById(id)).thenReturn(Optional.of(utilisateur));

        UtilisateurDTO result = utilisateurService.obtenirUtilisateurInfoParId(id);

        assertEquals("user123", result.getPseudo());
        assertEquals("Doe", result.getNom());
        assertEquals("John", result.getPrenom());
        verify(utilisateurRepository, times(1)).findById(id);
    }

    @Test
    void obtenirUtilisateurInfoParId_ReturnsNullIfNotExists() {
        Long id = 1L;
        when(utilisateurRepository.findById(id)).thenReturn(Optional.empty());

        UtilisateurDTO result = utilisateurService.obtenirUtilisateurInfoParId(id);

        assertNull(result);
        verify(utilisateurRepository, times(1)).findById(id);
    }

    @Test
    void obtenirUtilisateurPseudoParId_ReturnsUtilisateurDTOPseudoIfExists() {
        Long id = 1L;
        Utilisateur utilisateur = new Utilisateur(
                "user123", "mdp", "Doe.John@mail.com", "Doe", "John"
        );
        when(utilisateurRepository.findById(id)).thenReturn(Optional.of(utilisateur));

        UtilisateurDTO result = utilisateurService.obtenirUtilisateurPseudoParId(id);

        assertEquals("user123", result.getPseudo());
        assertNull(result.getNom());
        assertNull(result.getPrenom());
        verify(utilisateurRepository, times(1)).findById(id);
    }

    @Test
    void obtenirUtilisateurPseudoParId_ReturnsNullIfNotExists() {
        Long id = 1L;
        when(utilisateurRepository.findById(id)).thenReturn(Optional.empty());

        UtilisateurDTO result = utilisateurService.obtenirUtilisateurPseudoParId(id);

        assertNull(result);
        verify(utilisateurRepository, times(1)).findById(id);
    }

    @Test
    void mettreAJourUtilisateur_SavesAndReturnsUpdatedUtilisateur() {
        Long id = 1L;
        Utilisateur utilisateur = new Utilisateur();
        when(utilisateurRepository.existsById(id)).thenReturn(true);
        when(utilisateurRepository.save(utilisateur)).thenReturn(utilisateur);

        Utilisateur result = utilisateurService.mettreAJourUtilisateur(id, utilisateur);

        assertEquals(utilisateur, result);
        verify(utilisateurRepository, times(1)).save(utilisateur);
    }

    @Test
    void mettreAJourUtilisateur_ReturnsNullIfNotExists() {
        Long id = 1L;
        Utilisateur utilisateur = new Utilisateur();
        when(utilisateurRepository.existsById(id)).thenReturn(false);

        Utilisateur result = utilisateurService.mettreAJourUtilisateur(id, utilisateur);

        assertNull(result);
        verify(utilisateurRepository, times(0)).save(utilisateur);
    }

    @Test
    void login_ReturnsTokenIfCredentialsAreValid() {
        Credentials credentials = new Credentials("user@example.com", "password");
        Utilisateur utilisateur = new Utilisateur();
        when(utilisateurRepository.findByMailAndMdp(credentials.getMail(), credentials.getMdp())).thenReturn(Optional.of(utilisateur));
        Token token = new Token(credentials.getMail(), credentials.getMdp());
        when(tokenRepository.save(any(Token.class))).thenReturn(token);

        Token result = utilisateurService.login(credentials);

        assertEquals(token, result);
        verify(utilisateurRepository, times(1)).findByMailAndMdp(credentials.getMail(), credentials.getMdp());
        verify(tokenRepository, times(1)).save(any(Token.class));
    }

    @Test
    void login_ReturnsNullIfCredentialsAreInvalid() {
        Credentials credentials = new Credentials("user@example.com", "wrongpassword");
        when(utilisateurRepository.findByMailAndMdp(credentials.getMail(), credentials.getMdp())).thenReturn(Optional.empty());

        Token result = utilisateurService.login(credentials);

        assertNull(result);
        verify(utilisateurRepository, times(1)).findByMailAndMdp(credentials.getMail(), credentials.getMdp());
        verify(tokenRepository, times(0)).save(any(Token.class));
    }

    @Test
    void verifyToken_ReturnsTrueIfTokenIsValid() {
        String tokenString = "validToken";
        Token token = new Token();
        token.setExpirationDate(new Date(System.currentTimeMillis() + 10000));
        when(tokenRepository.findByToken(tokenString)).thenReturn(Optional.of(token));

        boolean result = utilisateurService.verifyToken(tokenString);

        assertTrue(result);
        verify(tokenRepository, times(1)).findByToken(tokenString);
    }

    @Test
    void verifyToken_ReturnsFalseIfTokenIsInvalid() {
        String tokenString = "invalidToken";
        when(tokenRepository.findByToken(tokenString)).thenReturn(Optional.empty());

        boolean result = utilisateurService.verifyToken(tokenString);

        assertFalse(result);
        verify(tokenRepository, times(1)).findByToken(tokenString);
    }

    @Test
    void disconnect_InvalidatesToken() {
        String tokenString = "validToken";
        Token token = new Token();
        when(tokenRepository.findByToken(tokenString)).thenReturn(Optional.of(token));

        utilisateurService.disconnect(tokenString);

        assertTrue(!Token.validateToken(token.getToken()));
        verify(tokenRepository, times(1)).findByToken(tokenString);
        verify(tokenRepository, times(1)).save(token);
    }

    @Test
    void supprimerUtilisateur_DeletesUtilisateur() {
        Long id = 1L;

        utilisateurService.supprimerUtilisateur(id);

        verify(utilisateurRepository, times(1)).deleteById(id);
    }

    @Test
    void obtenirUtilisateurParMail_ReturnsUtilisateurIfExists() {
        String mail = "user@example.com";
        Utilisateur utilisateur = new Utilisateur();
        when(utilisateurRepository.findByMail(mail)).thenReturn(Optional.of(utilisateur));

        Utilisateur result = utilisateurService.obtenirUtilisateurParMail(mail);

        assertEquals(utilisateur, result);
        verify(utilisateurRepository, times(1)).findByMail(mail);
    }

    @Test
    void obtenirUtilisateurParMail_ReturnsNullIfNotExists() {
        String mail = "user@example.com";
        when(utilisateurRepository.findByMail(mail)).thenReturn(Optional.empty());

        Utilisateur result = utilisateurService.obtenirUtilisateurParMail(mail);

        assertNull(result);
        verify(utilisateurRepository, times(1)).findByMail(mail);
    }

    @Test
    void obtenirUtilisateurParToken_ReturnsUtilisateurIfTokenExists() {
        String mail = "user@example.com";
        String mdp = "password";
        Token token = new Token(mail, mdp);
        String tokenString = token.getToken();
        Utilisateur utilisateur = new Utilisateur();
        when(tokenRepository.findByToken(tokenString)).thenReturn(Optional.of(token));
        when(
                utilisateurRepository.findByMail(Token.getEmail(token.getToken()))
        ).thenReturn(Optional.of(utilisateur));

        Utilisateur result = utilisateurService.obtenirUtilisateurParToken(tokenString);

        assertEquals(utilisateur, result);
        verify(tokenRepository, times(1)).findByToken(tokenString);
        verify(utilisateurRepository, times(1)).findByMail(Token.getEmail(token.getToken()));
    }

    @Test
    void obtenirUtilisateurParToken_ReturnsNullIfTokenNotExists() {
        String tokenString = "invalidToken";
        when(tokenRepository.findByToken(tokenString)).thenReturn(Optional.empty());

        Utilisateur result = utilisateurService.obtenirUtilisateurParToken(tokenString);

        assertNull(result);
        verify(tokenRepository, times(1)).findByToken(tokenString);
        verify(utilisateurRepository, times(0)).findByMail(anyString());
    }

    @Test
    void obtenirUtilisateurInfoParToken_ReturnsUtilisateurDTOIfTokenExists() {
        Utilisateur utilisateur = new Utilisateur(
                "user123", "mdp", "Doe.John@mail.com", "Doe", "John"
        );
        Token token = new Token(utilisateur.getMail(), utilisateur.getMdp());
        String tokenString = token.getToken();
        when(tokenRepository.findByToken(tokenString)).thenReturn(Optional.of(token));
        when(
                utilisateurRepository.findByMail(Token.getEmail(token.getToken()))
        ).thenReturn(Optional.of(utilisateur));

        UtilisateurDTO result = utilisateurService.obtenirUtilisateurInfoParToken(tokenString);

        assertEquals("user123", result.getPseudo());
        assertEquals("Doe", result.getNom());
        assertEquals("John", result.getPrenom());
        verify(tokenRepository, times(1)).findByToken(tokenString);
        verify(utilisateurRepository, times(1)).findByMail(Token.getEmail(token.getToken()));
    }

    @Test
    void obtenirUtilisateurInfoParToken_ReturnsNullIfTokenNotExists() {
        String tokenString = "invalidToken";
        when(tokenRepository.findByToken(tokenString)).thenReturn(Optional.empty());

        UtilisateurDTO result = utilisateurService.obtenirUtilisateurInfoParToken(tokenString);

        assertNull(result);
        verify(tokenRepository, times(1)).findByToken(tokenString);
        verify(utilisateurRepository, times(0)).findByMail(anyString());
    }
}
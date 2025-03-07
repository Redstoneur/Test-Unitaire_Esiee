package fr.knap.testunitaire_esiee.controller;

import fr.knap.testunitaire_esiee.model.Echange;
import fr.knap.testunitaire_esiee.services.EchangeService;
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
 * Unit tests for the EchangeController class.
 */
@SpringBootTest
class EchangeControllerTest {

    @Mock
    private EchangeService echangeService;

    @Mock
    private UtilisateurService utilisateurService;

    @InjectMocks
    private EchangeController echangeController;

    /**
     * Initializes mocks for the test class.
     */
    public EchangeControllerTest() {
        MockitoAnnotations.openMocks(this);
    }

    /**
     * Tests the creerEchange method to ensure it returns the created Echange.
     */
    @Test
    void creerEchange_ReturnsCreatedEchange() {
        Echange echange = new Echange();
        when(echangeService.creerEchange(echange)).thenReturn(echange);

        Echange result = echangeController.creerEchange(echange);

        assertEquals(echange, result);
        verify(echangeService, times(1)).creerEchange(echange);
    }

    /**
     * Tests the obtenirTousLesEchanges method to ensure it returns all Echanges.
     */
    @Test
    void obtenirTousLesEchanges_ReturnsAllEchanges() {
        List<Echange> echanges = Arrays.asList(new Echange(), new Echange());
        when(echangeService.obtenirTousLesEchanges()).thenReturn(echanges);

        List<Echange> result = echangeController.obtenirTousLesEchanges();

        assertEquals(echanges, result);
        verify(echangeService, times(1)).obtenirTousLesEchanges();
    }

    /**
     * Tests the obtenirUnEchange method to ensure it returns the Echange if the token is valid.
     */
    @Test
    void obtenirUnEchange_ReturnsEchangeIfTokenValid() {
        Long id = 1L;
        String authToken = "validToken";
        Echange echange = new Echange();
        when(utilisateurService.verifyToken(authToken)).thenReturn(true);
        when(echangeService.obtenirEchangeParId(id)).thenReturn(echange);

        Echange result = echangeController.obtenirUnEchange(authToken, id);

        assertEquals(echange, result);
        verify(utilisateurService, times(1)).verifyToken(authToken);
        verify(echangeService, times(1)).obtenirEchangeParId(id);
    }

    /**
     * Tests the obtenirUnEchange method to ensure it throws a Forbidden exception if the token is invalid.
     */
    @Test
    void obtenirUnEchange_ThrowsForbiddenIfTokenInvalid() {
        Long id = 1L;
        String authToken = "invalidToken";
        when(utilisateurService.verifyToken(authToken)).thenReturn(false);

        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
            echangeController.obtenirUnEchange(authToken, id);
        });

        assertEquals(HttpStatus.FORBIDDEN, exception.getStatusCode());
        verify(utilisateurService, times(1)).verifyToken(authToken);
        verify(echangeService, times(0)).obtenirEchangeParId(id);
    }

    /**
     * Tests the mettreAJourEchange method to ensure it returns the updated Echange if valid.
     */
    @Test
    void mettreAJourEchange_ReturnsUpdatedEchangeIfValid() {
        String authToken = "validToken";
        Echange echange = new Echange();
        echange.setId(1L);
        when(utilisateurService.verifyToken(authToken)).thenReturn(true);
        when(echangeService.echangeExist(echange.getId())).thenReturn(true);
        when(echangeService.mettreAJourEchange(echange)).thenReturn(echange);

        Echange result = echangeController.mettreAJourEchange(authToken, echange);

        assertEquals(echange, result);
        verify(utilisateurService, times(1)).verifyToken(authToken);
        verify(echangeService, times(1)).echangeExist(echange.getId());
        verify(echangeService, times(1)).mettreAJourEchange(echange);
    }

    /**
     * Tests the mettreAJourEchange method to ensure it throws an exception if the ID is null.
     */
    @Test
    void mettreAJourEchange_ThrowsExceptionIfIdNull() {
        String authToken = "validToken";
        Echange echange = new Echange();
        when(utilisateurService.verifyToken(authToken)).thenReturn(true);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            echangeController.mettreAJourEchange(authToken, echange);
        });

        assertEquals("L'id de l'échange ne peut pas être null", exception.getMessage());
        verify(utilisateurService, times(1)).verifyToken(authToken);
        verify(echangeService, times(0)).echangeExist(anyLong());
        verify(echangeService, times(0)).mettreAJourEchange(any(Echange.class));
    }

    /**
     * Tests the mettreAJourEchange method to ensure it throws an Unauthorized exception if the Echange does not exist.
     */
    @Test
    void mettreAJourEchange_ThrowsUnauthorizedIfEchangeNotExist() {
        String authToken = "validToken";
        Echange echange = new Echange();
        echange.setId(1L);
        when(utilisateurService.verifyToken(authToken)).thenReturn(true);
        when(echangeService.echangeExist(echange.getId())).thenReturn(false);

        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
            echangeController.mettreAJourEchange(authToken, echange);
        });

        assertEquals(HttpStatus.UNAUTHORIZED, exception.getStatusCode());
        assertEquals("Echange is not valid", exception.getReason());
        verify(utilisateurService, times(1)).verifyToken(authToken);
        verify(echangeService, times(1)).echangeExist(echange.getId());
        verify(echangeService, times(0)).mettreAJourEchange(any(Echange.class));
    }

    /**
     * Tests the mettreAJourEchange method to ensure it throws a Forbidden exception if the token is invalid.
     */
    @Test
    void mettreAJourEchange_ThrowsForbiddenIfTokenInvalid() {
        String authToken = "invalidToken";
        Echange echange = new Echange();
        echange.setId(1L);
        when(utilisateurService.verifyToken(authToken)).thenReturn(false);

        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
            echangeController.mettreAJourEchange(authToken, echange);
        });

        assertEquals(HttpStatus.FORBIDDEN, exception.getStatusCode());
        assertEquals("Token is not valid", exception.getReason());
        verify(utilisateurService, times(1)).verifyToken(authToken);
        verify(echangeService, times(0)).echangeExist(anyLong());
        verify(echangeService, times(0)).mettreAJourEchange(any(Echange.class));
    }
}
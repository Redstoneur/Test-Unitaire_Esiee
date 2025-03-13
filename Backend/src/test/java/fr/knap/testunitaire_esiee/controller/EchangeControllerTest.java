package fr.knap.testunitaire_esiee.controller;

import fr.knap.testunitaire_esiee.dto.EchangeBufferDTO;
import fr.knap.testunitaire_esiee.dto.EchangeEtatDTO;
import fr.knap.testunitaire_esiee.model.Echange;
import fr.knap.testunitaire_esiee.model.Etat;
import fr.knap.testunitaire_esiee.model.Objet;
import fr.knap.testunitaire_esiee.services.EchangeService;
import fr.knap.testunitaire_esiee.services.ObjetService;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
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

    @Mock
    private ObjetService objetService;

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
    void creerEchangeReturnsCreatedEchange() {
        String authToken = "validToken";
        long idObjetPropose = 1L;
        long idObjetRecherche = 2L;

        Objet objetPropose = new Objet();
        objetPropose.setId(idObjetPropose);
        Objet objetRecherche = new Objet();
        objetRecherche.setId(idObjetRecherche);

        EchangeBufferDTO echangeBufferDTO = new EchangeBufferDTO(idObjetPropose, idObjetRecherche);
        Echange createdEchange = new Echange(
                objetPropose,
                objetRecherche
        );

        when(utilisateurService.verifyToken(authToken)).thenReturn(true);
        when(echangeService.creerEchange(any(Echange.class))).thenReturn(createdEchange);
        when(objetService.obtenirObjetParId(idObjetPropose)).thenReturn(objetPropose);
        when(objetService.obtenirObjetParId(idObjetRecherche)).thenReturn(objetRecherche);

        Echange result = echangeController.creerEchange(authToken, echangeBufferDTO);

        assertEquals(createdEchange, result);
        verify(echangeService, times(1)).creerEchange(any(Echange.class));
        verify(utilisateurService, times(1)).verifyToken(authToken);
        verify(objetService, times(1)).obtenirObjetParId(idObjetPropose);
        verify(objetService, times(1)).obtenirObjetParId(idObjetRecherche);
    }

    /**
     * Tests the obtenirTousLesEchanges method to ensure it returns all Echanges.
     */
    @Test
    void obtenirTousLesEchangesReturnsAllEchanges() {
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
    void obtenirUnEchangeReturnsEchangeIfTokenValid() {
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
    void obtenirUnEchangeThrowsForbiddenIfTokenInvalid() {
        Long id = 1L;
        String authToken = "invalidToken";
        when(utilisateurService.verifyToken(authToken)).thenReturn(false);

        ResponseStatusException exception = assertThrows(
                ResponseStatusException.class,
                () -> echangeController.obtenirUnEchange(authToken, id)
        );

        assertEquals(HttpStatus.FORBIDDEN, exception.getStatusCode());
        verify(utilisateurService, times(1)).verifyToken(authToken);
        verify(echangeService, times(0)).obtenirEchangeParId(id);
    }

    /**
     * Tests the mettreAJourEchange method to ensure it returns the updated Echange if valid.
     */
    @Test
    void mettreAJourEchangeReturnsUpdatedEchangeIfValid() {
        String authToken = "validToken";

        Echange echange = new Echange();
        echange.setId(1L);
        echange.setEtatEchange(Etat.ATTENTE);

        EchangeEtatDTO echangeEtatDTO = new EchangeEtatDTO(
                echange.getId(),
                echange.getEtatEchange()
        );

        when(utilisateurService.verifyToken(authToken)).thenReturn(true);
        when(echangeService.echangeExist(echange.getId())).thenReturn(true);
        when(echangeService.mettreAJourEchange(echange)).thenReturn(echange);
        when(echangeService.obtenirEchangeParId(echange.getId())).thenReturn(echange);

        System.out.println(echange.getId());
        System.out.println(echange.getEtatEchange());

        Echange result = echangeController.mettreAJourEchange(authToken, echangeEtatDTO);

        assertEquals(echange, result);
        verify(utilisateurService, times(1)).verifyToken(authToken);
        verify(echangeService, times(1)).echangeExist(echange.getId());
        verify(echangeService, times(1)).mettreAJourEchange(echange);
    }

    /**
     * Tests the mettreAJourEchange method to ensure it throws a Forbidden exception if the id is negative.
     */
    @Test
    void obtenirUnEchangeThrowsForbiddenIfIdNegative() {
        String authToken = "validToken";

        Echange echange = new Echange();
        echange.setId(-1L);
        echange.setEtatEchange(Etat.ATTENTE);

        EchangeEtatDTO echangeEtatDTO = new EchangeEtatDTO(
                echange.getId(),
                echange.getEtatEchange()
        );

        when(utilisateurService.verifyToken(authToken)).thenReturn(true);

        ResponseStatusException exception = assertThrows(
                ResponseStatusException.class,
                () -> echangeController.mettreAJourEchange(authToken, echangeEtatDTO)
        );

        assertEquals(HttpStatus.UNAUTHORIZED, exception.getStatusCode());
        assertEquals("Echange ID is invalid", exception.getReason());
        verify(utilisateurService, times(1)).verifyToken(authToken);
    }


    /**
     * Tests the mettreAJourEchange method to ensure it throws an exception if the ID is null.
     */
    @Test
    void mettreAJourEchangeThrowsExceptionIfIdNull() {
        String authToken = "validToken";

        Echange echange = new Echange();
        echange.setId(675L);

        EchangeEtatDTO echangeEtatDTO = new EchangeEtatDTO(
                echange.getId(),
                echange.getEtatEchange()
        );

        when(utilisateurService.verifyToken(authToken)).thenReturn(true);
        when(echangeService.obtenirEchangeParId(echange.getId())).thenReturn(null);

        ResponseStatusException exception = assertThrows(
                ResponseStatusException.class,
                () -> echangeController.mettreAJourEchange(authToken, echangeEtatDTO)
        );

        assertEquals(HttpStatus.UNAUTHORIZED, exception.getStatusCode());
        assertEquals("Echange does not exist", exception.getReason());
        verify(utilisateurService, times(1)).verifyToken(authToken);
        verify(echangeService, times(0)).echangeExist(anyLong());
        verify(echangeService, times(0)).mettreAJourEchange(any(Echange.class));
    }

    /**
     * Tests the mettreAJourEchange method to ensure it throws an Unauthorized exception if the Echange does not exist.
     */
    @Test
    void mettreAJourEchangeThrowsUnauthorizedIfEchangeNotExist() {
        String authToken = "validToken";

        Echange echange = new Echange();
        echange.setId(1L);
        echange.setEtatEchange(Etat.ATTENTE);

        EchangeEtatDTO echangeEtatDTO = new EchangeEtatDTO(
                echange.getId(),
                echange.getEtatEchange()
        );

        when(utilisateurService.verifyToken(authToken)).thenReturn(true);
        when(echangeService.echangeExist(echange.getId())).thenReturn(false);
        when(echangeService.mettreAJourEchange(echange)).thenReturn(echange);
        when(echangeService.obtenirEchangeParId(echange.getId())).thenReturn(echange);

        ResponseStatusException exception = assertThrows(
                ResponseStatusException.class,
                () -> echangeController.mettreAJourEchange(authToken, echangeEtatDTO)
        );

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
    void mettreAJourEchangeThrowsForbiddenIfTokenInvalid() {
        String authToken = "invalidToken";

        Echange echange = new Echange();
        echange.setId(1L);
        when(utilisateurService.verifyToken(authToken)).thenReturn(false);

        EchangeEtatDTO echangeEtatDTO = new EchangeEtatDTO(
                echange.getId(),
                echange.getEtatEchange()
        );

        ResponseStatusException exception = assertThrows(
                ResponseStatusException.class,
                () -> echangeController.mettreAJourEchange(authToken, echangeEtatDTO)
        );

        assertEquals(HttpStatus.FORBIDDEN, exception.getStatusCode());
        assertEquals("Token is not valid", exception.getReason());
        verify(utilisateurService, times(1)).verifyToken(authToken);
        verify(echangeService, times(0)).echangeExist(anyLong());
        verify(echangeService, times(0)).mettreAJourEchange(any(Echange.class));
    }
}
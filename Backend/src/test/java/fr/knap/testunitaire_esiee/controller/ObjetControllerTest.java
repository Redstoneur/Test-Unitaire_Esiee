package fr.knap.testunitaire_esiee.controller;

import fr.knap.testunitaire_esiee.dto.ObjetBufferDTO;
import fr.knap.testunitaire_esiee.dto.ObjetDTO;
import fr.knap.testunitaire_esiee.model.CategorieObjet;
import fr.knap.testunitaire_esiee.model.Objet;
import fr.knap.testunitaire_esiee.model.Utilisateur;
import fr.knap.testunitaire_esiee.services.ObjetService;
import fr.knap.testunitaire_esiee.services.UtilisateurService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class ObjetControllerTest {

    @Mock
    private ObjetService objetService;

    @Mock
    private UtilisateurService utilisateurService;

    @InjectMocks
    private ObjetController objetController;

    public ObjetControllerTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void creerObjet_ReturnsCreatedObjetIfTokenValid() {
        String authToken = "validToken";
        ObjetBufferDTO objetBufferDTO = new ObjetBufferDTO("Laptop", "A high-end gaming laptop", CategorieObjet.INFORMATIQUE, LocalDateTime.now());
        Utilisateur utilisateur = new Utilisateur();
        Objet objet = new Objet(utilisateur, "Laptop", "A high-end gaming laptop", CategorieObjet.INFORMATIQUE, LocalDateTime.now());

        when(utilisateurService.verifyToken(authToken)).thenReturn(true);
        when(utilisateurService.obtenirUtilisateurParToken(authToken)).thenReturn(utilisateur);
        when(objetService.creerObjet(any(Objet.class))).thenReturn(objet);

        Objet result = objetController.creerObjet(authToken, objetBufferDTO);

        assertEquals(objet, result);
        verify(utilisateurService, times(1)).verifyToken(authToken);
        verify(utilisateurService, times(1)).obtenirUtilisateurParToken(authToken);
        verify(objetService, times(1)).creerObjet(any(Objet.class));
    }

    @Test
    void creerObjet_ThrowsForbiddenIfTokenInvalid() {
        String authToken = "invalidToken";
        ObjetBufferDTO objetBufferDTO = new ObjetBufferDTO("Laptop", "A high-end gaming laptop", CategorieObjet.INFORMATIQUE, LocalDateTime.now());

        when(utilisateurService.verifyToken(authToken)).thenReturn(false);

        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
            objetController.creerObjet(authToken, objetBufferDTO);
        });

        assertEquals(HttpStatus.FORBIDDEN, exception.getStatusCode());
        verify(utilisateurService, times(1)).verifyToken(authToken);
        verify(utilisateurService, times(0)).obtenirUtilisateurParToken(anyString());
        verify(objetService, times(0)).creerObjet(any(Objet.class));
    }

    @Test
    void obtenirTousLesObjets_ReturnsAllObjets() {
        List<ObjetDTO> objets = Arrays.asList(
                new ObjetDTO(
                        "Laptop", "A high-end gaming laptop", CategorieObjet.INFORMATIQUE,
                        "John Doe", 1L, LocalDateTime.now()
                ),
                new ObjetDTO(
                        "Smartphone", "A high-end smartphone", CategorieObjet.INFORMATIQUE,
                        "Jane Doe", 2L, LocalDateTime.now()
                )
        );
        when(objetService.obtenirTousLesObjets()).thenReturn(objets);

        List<ObjetDTO> result = objetController.obtenirTousLesObjets();

        assertEquals(objets, result);
        verify(objetService, times(1)).obtenirTousLesObjets();
    }

    @Test
    void obtenirObjetsParUtilisateur_ReturnsObjetsIfTokenValid() {
        String authToken = "validToken";
        Long idUtilisateur = 1L;
        List<ObjetDTO> objets = Arrays.asList(
                new ObjetDTO(
                        "Laptop", "A high-end gaming laptop", CategorieObjet.INFORMATIQUE,
                        "John Doe", 1L, LocalDateTime.now()
                ),
                new ObjetDTO(
                        "Smartphone", "A high-end smartphone", CategorieObjet.INFORMATIQUE,
                        "Jane Doe", 2L, LocalDateTime.now()
                )
        );

        when(utilisateurService.verifyToken(authToken)).thenReturn(true);
        when(objetService.obtenirObjetsParUtilisateur(idUtilisateur)).thenReturn(objets);

        List<ObjetDTO> result = objetController.obtenirObjetsParUtilisateur(authToken, idUtilisateur);

        assertEquals(objets, result);
        verify(utilisateurService, times(1)).verifyToken(authToken);
        verify(objetService, times(1)).obtenirObjetsParUtilisateur(idUtilisateur);
    }

    @Test
    void obtenirObjetsParUtilisateur_ThrowsForbiddenIfTokenInvalid() {
        String authToken = "invalidToken";
        Long idUtilisateur = 1L;

        when(utilisateurService.verifyToken(authToken)).thenReturn(false);

        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
            objetController.obtenirObjetsParUtilisateur(authToken, idUtilisateur);
        });

        assertEquals(HttpStatus.FORBIDDEN, exception.getStatusCode());
        verify(utilisateurService, times(1)).verifyToken(authToken);
        verify(objetService, times(0)).obtenirObjetsParUtilisateur(anyLong());
    }

    @Test
    void obtenirObjetParId_ReturnsObjetIfExists() {
        Long id = 1L;
        Objet objet = new Objet();
        when(objetService.obtenirObjetParId(id)).thenReturn(objet);

        Objet result = objetController.obtenirObjetParId(id);

        assertEquals(objet, result);
        verify(objetService, times(1)).obtenirObjetParId(id);
    }

    @Test
    void mettreAJourObjet_ReturnsUpdatedObjetIfTokenValid() {
        String authToken = "validToken";
        Long id = 1L;
        Objet objet = new Objet();
        when(utilisateurService.verifyToken(authToken)).thenReturn(true);
        when(objetService.mettreAJourObjet(id, objet)).thenReturn(objet);

        Objet result = objetController.mettreAJourObjet(authToken, id, objet);

        assertEquals(objet, result);
        verify(utilisateurService, times(1)).verifyToken(authToken);
        verify(objetService, times(1)).mettreAJourObjet(id, objet);
    }

    @Test
    void mettreAJourObjet_ThrowsForbiddenIfTokenInvalid() {
        String authToken = "invalidToken";
        Long id = 1L;
        Objet objet = new Objet();

        when(utilisateurService.verifyToken(authToken)).thenReturn(false);

        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
            objetController.mettreAJourObjet(authToken, id, objet);
        });

        assertEquals(HttpStatus.FORBIDDEN, exception.getStatusCode());
        verify(utilisateurService, times(1)).verifyToken(authToken);
        verify(objetService, times(0)).mettreAJourObjet(anyLong(), any(Objet.class));
    }

    @Test
    void supprimerObjet_DeletesObjetIfTokenValid() {
        String authToken = "validToken";
        Long id = 1L;

        when(utilisateurService.verifyToken(authToken)).thenReturn(true);

        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
            objetController.supprimerObjet(authToken, id);
        });

        assertEquals(HttpStatus.OK, exception.getStatusCode());
        verify(utilisateurService, times(1)).verifyToken(authToken);
        verify(objetService, times(1)).supprimerObjet(id);
    }

    @Test
    void supprimerObjet_ThrowsForbiddenIfTokenInvalid() {
        String authToken = "invalidToken";
        Long id = 1L;

        when(utilisateurService.verifyToken(authToken)).thenReturn(false);

        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
            objetController.supprimerObjet(authToken, id);
        });

        assertEquals(HttpStatus.FORBIDDEN, exception.getStatusCode());
        verify(utilisateurService, times(1)).verifyToken(authToken);
        verify(objetService, times(0)).supprimerObjet(anyLong());
    }
}
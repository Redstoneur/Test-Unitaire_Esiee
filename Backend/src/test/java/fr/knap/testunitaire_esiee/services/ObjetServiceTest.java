package fr.knap.testunitaire_esiee.services;

import fr.knap.testunitaire_esiee.dto.ObjetDTO;
import fr.knap.testunitaire_esiee.model.CategorieObjet;
import fr.knap.testunitaire_esiee.model.Objet;
import fr.knap.testunitaire_esiee.model.Utilisateur;
import fr.knap.testunitaire_esiee.repository.ObjetRepository;
import fr.knap.testunitaire_esiee.repository.UtilisateurRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Unit tests for the ObjetService class.
 */
@SpringBootTest
class ObjetServiceTest {

    @Mock
    private ObjetRepository objetRepository;

    @Mock
    private UtilisateurRepository utilisateurRepository;

    @InjectMocks
    private ObjetService objetService;

    /**
     * Initializes mocks for the test class.
     */
    public ObjetServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    /**
     * Tests the creerObjet method to ensure it saves and returns the Objet object.
     */
    @Test
    void creerObjetSavesAndReturnsObjet() {
        Objet objet = new Objet();
        when(objetRepository.save(objet)).thenReturn(objet);

        Objet result = objetService.creerObjet(objet);

        assertEquals(objet, result);
        verify(objetRepository, times(1)).save(objet);
    }

    /**
     * Tests the obtenirTousLesObjets method to ensure it returns all Objet objects.
     */
    @Test
    void obtenirTousLesObjetsReturnsAllObjets() {
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setPseudo("user123");
        utilisateur.setId(1L);

        Objet objet1 = new Objet(utilisateur, "Laptop", "A high-end gaming laptop", CategorieObjet.INFORMATIQUE, LocalDateTime.now());
        Objet objet2 = new Objet(utilisateur, "Smartphone", "A brand new smartphone", CategorieObjet.ELECTROMENAGER, LocalDateTime.now());

        when(objetRepository.findAll()).thenReturn(Arrays.asList(objet1, objet2));

        List<ObjetDTO> result = objetService.obtenirTousLesObjets();

        assertEquals(2, result.size());
        assertEquals("Laptop", result.get(0).getNom());
        assertEquals("Smartphone", result.get(1).getNom());
        verify(objetRepository, times(1)).findAll();
    }

    /**
     * Tests the obtenirObjetParId method to ensure it returns the Objet object if it exists.
     */
    @Test
    void obtenirObjetParIdReturnsObjetIfExists() {
        Long id = 1L;
        Objet objet = new Objet();
        when(objetRepository.findById(id)).thenReturn(Optional.of(objet));

        Objet result = objetService.obtenirObjetParId(id);

        assertEquals(objet, result);
        verify(objetRepository, times(1)).findById(id);
    }

    /**
     * Tests the obtenirObjetParId method to ensure it returns null if the Objet object does not exist.
     */
    @Test
    void obtenirObjetParIdReturnsNullIfNotExists() {
        Long id = 1L;
        when(objetRepository.findById(id)).thenReturn(Optional.empty());

        Objet result = objetService.obtenirObjetParId(id);

        assertNull(result);
        verify(objetRepository, times(1)).findById(id);
    }

    /**
     * Tests the mettreAJourObjet method to ensure it saves and returns the updated Objet object.
     */
    @Test
    void mettreAJourObjetSavesAndReturnsUpdatedObjet() {
        Long id = 1L;
        Objet objet = new Objet();
        when(objetRepository.existsById(id)).thenReturn(true);
        when(objetRepository.save(objet)).thenReturn(objet);

        Objet result = objetService.mettreAJourObjet(id, objet);

        assertEquals(objet, result);
        verify(objetRepository, times(1)).save(objet);
    }

    /**
     * Tests the mettreAJourObjet method to ensure it returns null if the Objet object does not exist.
     */
    @Test
    void mettreAJourObjetReturnsNullIfNotExists() {
        Long id = 1L;
        Objet objet = new Objet();
        when(objetRepository.existsById(id)).thenReturn(false);

        Objet result = objetService.mettreAJourObjet(id, objet);

        assertNull(result);
        verify(objetRepository, times(0)).save(objet);
    }

    /**
     * Tests the supprimerObjet method to ensure it deletes the Objet object.
     */
    @Test
    void supprimerObjetDeletesObjet() {
        Long id = 1L;
        // Given an existing objet with no deletion date
        Objet objet = new Objet();
        objet.setDateSuppression(null);
        when(objetRepository.findById(id)).thenReturn(Optional.of(objet));
        when(objetRepository.save(objet)).thenReturn(objet);

        // When
        objetService.supprimerObjet(id);

        // Then, the objet's deletion date should be set and saved
        assertNotNull(objet.getDateSuppression());
        verify(objetRepository, times(1)).save(objet);
    }

    /**
     * Tests the obtenirObjetsParUtilisateur method to ensure it returns the Objet objects if the user exists.
     */
    @Test
    void obtenirObjetsParUtilisateurReturnsObjetsIfUserExists() {
        Long idUtilisateur = 1L;
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setPseudo("user123");
        utilisateur.setId(idUtilisateur);

        Objet objet1 = new Objet(utilisateur, "Laptop", "A high-end gaming laptop", CategorieObjet.INFORMATIQUE, LocalDateTime.now());
        Objet objet2 = new Objet(utilisateur, "Smartphone", "A brand new smartphone", CategorieObjet.ELECTROMENAGER, LocalDateTime.now());

        when(utilisateurRepository.findById(idUtilisateur)).thenReturn(Optional.of(utilisateur));
        when(objetRepository.findByUtilisateurId(idUtilisateur)).thenReturn(Arrays.asList(objet1, objet2));

        List<ObjetDTO> result = objetService.obtenirObjetsParUtilisateur(idUtilisateur);

        assertEquals(2, result.size());
        assertEquals("Laptop", result.get(0).getNom());
        assertEquals("Smartphone", result.get(1).getNom());
        verify(utilisateurRepository, times(1)).findById(idUtilisateur);
        verify(objetRepository, times(1)).findByUtilisateurId(idUtilisateur);
    }

    /**
     * Tests the obtenirObjetsParUtilisateur method to ensure it returns null if the user does not exist.
     */
    @Test
    void obtenirObjetsParUtilisateurReturnsNullIfUserNotExists() {
        Long idUtilisateur = 1L;
        when(utilisateurRepository.findById(idUtilisateur)).thenReturn(Optional.empty());

        List<ObjetDTO> result = objetService.obtenirObjetsParUtilisateur(idUtilisateur);

        assertNull(result);
        verify(utilisateurRepository, times(1)).findById(idUtilisateur);
        verify(objetRepository, times(0)).findByUtilisateurId(idUtilisateur);
    }
}
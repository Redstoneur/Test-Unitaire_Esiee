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

@SpringBootTest
class ObjetServiceTest {

    @Mock
    private ObjetRepository objetRepository;

    @Mock
    private UtilisateurRepository utilisateurRepository;

    @InjectMocks
    private ObjetService objetService;

    public ObjetServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void creerObjet_SavesAndReturnsObjet() {
        Objet objet = new Objet();
        when(objetRepository.save(objet)).thenReturn(objet);

        Objet result = objetService.creerObjet(objet);

        assertEquals(objet, result);
        verify(objetRepository, times(1)).save(objet);
    }

    @Test
    void obtenirTousLesObjets_ReturnsAllObjets() {
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

    @Test
    void obtenirObjetParId_ReturnsObjetIfExists() {
        Long id = 1L;
        Objet objet = new Objet();
        when(objetRepository.findById(id)).thenReturn(Optional.of(objet));

        Objet result = objetService.obtenirObjetParId(id);

        assertEquals(objet, result);
        verify(objetRepository, times(1)).findById(id);
    }

    @Test
    void obtenirObjetParId_ReturnsNullIfNotExists() {
        Long id = 1L;
        when(objetRepository.findById(id)).thenReturn(Optional.empty());

        Objet result = objetService.obtenirObjetParId(id);

        assertNull(result);
        verify(objetRepository, times(1)).findById(id);
    }

    @Test
    void mettreAJourObjet_SavesAndReturnsUpdatedObjet() {
        Long id = 1L;
        Objet objet = new Objet();
        when(objetRepository.existsById(id)).thenReturn(true);
        when(objetRepository.save(objet)).thenReturn(objet);

        Objet result = objetService.mettreAJourObjet(id, objet);

        assertEquals(objet, result);
        verify(objetRepository, times(1)).save(objet);
    }

    @Test
    void mettreAJourObjet_ReturnsNullIfNotExists() {
        Long id = 1L;
        Objet objet = new Objet();
        when(objetRepository.existsById(id)).thenReturn(false);

        Objet result = objetService.mettreAJourObjet(id, objet);

        assertNull(result);
        verify(objetRepository, times(0)).save(objet);
    }

    @Test
    void supprimerObjet_DeletesObjet() {
        Long id = 1L;

        objetService.supprimerObjet(id);

        verify(objetRepository, times(1)).deleteById(id);
    }

    @Test
    void obtenirObjetsParUtilisateur_ReturnsObjetsIfUserExists() {
        Long idUtilisateur = 1L;
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setPseudo("user123");
        utilisateur.setId(idUtilisateur);

        Objet objet1 = new Objet(utilisateur,"Laptop", "A high-end gaming laptop", CategorieObjet.INFORMATIQUE, LocalDateTime.now());
        Objet objet2 = new Objet(utilisateur,"Smartphone", "A brand new smartphone", CategorieObjet.ELECTROMENAGER, LocalDateTime.now());

        when(utilisateurRepository.findById(idUtilisateur)).thenReturn(Optional.of(utilisateur));
        when(objetRepository.findByUtilisateurId(idUtilisateur)).thenReturn(Arrays.asList(objet1, objet2));

        List<ObjetDTO> result = objetService.obtenirObjetsParUtilisateur(idUtilisateur);

        assertEquals(2, result.size());
        assertEquals("Laptop", result.get(0).getNom());
        assertEquals("Smartphone", result.get(1).getNom());
        verify(utilisateurRepository, times(1)).findById(idUtilisateur);
        verify(objetRepository, times(1)).findByUtilisateurId(idUtilisateur);
    }

    @Test
    void obtenirObjetsParUtilisateur_ReturnsNullIfUserNotExists() {
        Long idUtilisateur = 1L;
        when(utilisateurRepository.findById(idUtilisateur)).thenReturn(Optional.empty());

        List<ObjetDTO> result = objetService.obtenirObjetsParUtilisateur(idUtilisateur);

        assertNull(result);
        verify(utilisateurRepository, times(1)).findById(idUtilisateur);
        verify(objetRepository, times(0)).findByUtilisateurId(idUtilisateur);
    }
}
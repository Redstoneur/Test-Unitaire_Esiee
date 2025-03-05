package fr.knap.testunitaire_esiee.services;

import fr.knap.testunitaire_esiee.model.Objet;
import fr.knap.testunitaire_esiee.respository.ObjetRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ObjetServiceTest {

    @Mock
    private ObjetRepository objetRepository;

    @InjectMocks
    private ObjetService objetService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreerObjet() {
        Objet objet = new Objet();
        when(objetRepository.save(objet)).thenReturn(objet);
        assertEquals(objet, objetService.creerObjet(objet));
    }

    @Test
    void testObtenirTousLesObjets() {
        objetService.obtenirTousLesObjets();
        verify(objetRepository, times(1)).findAll();
    }

    @Test
    void testObtenirObjetParId() {
        Objet objet = new Objet();
        when(objetRepository.findById(1L)).thenReturn(Optional.of(objet));
        assertEquals(objet, objetService.obtenirObjetParId(1L));
    }

    @Test
    void testMettreAJourObjet() {
        Objet objet = new Objet();
        when(objetRepository.existsById(1L)).thenReturn(true);
        when(objetRepository.save(objet)).thenReturn(objet);
        assertEquals(objet, objetService.mettreAJourObjet(1L, objet));
    }

    @Test
    void testSupprimerObjet() {
        objetService.supprimerObjet(1L);
        verify(objetRepository, times(1)).deleteById(1L);
    }

    @Test
    void testObtenirObjetsParUtilisateur() {
        objetService.obtenirObjetsParUtilisateur(1L);
        verify(objetRepository, times(1)).findByUtilisateurId(1L);
    }
}
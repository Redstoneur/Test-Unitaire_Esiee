package fr.knap.testunitaire_esiee.controller;

import fr.knap.testunitaire_esiee.model.Objet;
import fr.knap.testunitaire_esiee.services.ObjetService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ObjetControllerTest {

    @Mock
    private ObjetService objetService;

    @InjectMocks
    private ObjetController objetController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreerObjet() {
        Objet objet = new Objet();
        when(objetService.creerObjet(objet)).thenReturn(objet);
        assertEquals(objet, objetController.creerObjet(objet));
    }

    @Test
    void testObtenirTousLesObjets() {
        objetController.obtenirTousLesObjets();
        verify(objetService, times(1)).obtenirTousLesObjets();
    }

    @Test
    void testObtenirObjetsParUtilisateur() {
        objetController.obtenirObjetsParUtilisateur(1L);
        verify(objetService, times(1)).obtenirObjetsParUtilisateur(1L);
    }

    @Test
    void testObtenirObjetParId() {
        Objet objet = new Objet();
        when(objetService.obtenirObjetParId(1L)).thenReturn(objet);
        assertEquals(objet, objetController.obtenirObjetParId(1L));
    }

    @Test
    void testMettreAJourObjet() {
        Objet objet = new Objet();
        when(objetService.mettreAJourObjet(1L, objet)).thenReturn(objet);
        assertEquals(objet, objetController.mettreAJourObjet(1L, objet));
    }

    @Test
    void testSupprimerObjet() {
        objetController.supprimerObjet(1L);
        verify(objetService, times(1)).supprimerObjet(1L);
    }
}
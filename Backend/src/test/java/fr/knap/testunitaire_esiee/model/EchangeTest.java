package fr.knap.testunitaire_esiee.model;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the Echange class.
 */
@SpringBootTest
class EchangeTest {

    /**
     * Tests the constructor with parameters to ensure it sets all fields correctly.
     */
    @Test
    void constructorWithParametersSetsAllFields() {
        Objet objetPropose = new Objet();
        objetPropose.setUtilisateur(new Utilisateur());
        Objet objetDemande = new Objet();
        objetDemande.setUtilisateur(new Utilisateur());
        LocalDateTime dateProposition = LocalDateTime.now();
        Etat etatEchange = Etat.ATTENTE;
        LocalDateTime dateCloture = LocalDateTime.now().plusDays(1);

        Echange echange = new Echange(objetPropose, objetDemande, dateProposition, etatEchange, dateCloture);

        assertEquals(objetPropose, echange.getObjetPropose());
        assertEquals(objetDemande, echange.getObjetDemande());
        assertEquals(dateProposition, echange.getDateProposition());
        assertEquals(etatEchange, echange.getEtatEchange());
        assertEquals(dateCloture, echange.getDateCloture());
        assertEquals(objetPropose.getUtilisateur(), echange.getProprietaireObjetPropose());
        assertEquals(objetDemande.getUtilisateur(), echange.getProprietaireObjetDemande());
    }

    /**
     * Tests the constructor with only objetPropose and objetDemande to ensure it sets all fields correctly.
     */
    @Test
    void constructorWithObjetProposeAndObjetDemandeSetsAllFields() {
        Objet objetPropose = new Objet();
        objetPropose.setUtilisateur(new Utilisateur());
        Objet objetDemande = new Objet();
        objetDemande.setUtilisateur(new Utilisateur());

        Echange echange = new Echange(objetPropose, objetDemande);

        assertEquals(objetPropose, echange.getObjetPropose());
        assertEquals(objetDemande, echange.getObjetDemande());
        assertNotNull(echange.getDateProposition());
        assertEquals(Etat.ATTENTE, echange.getEtatEchange());
        assertNull(echange.getDateCloture());
        assertEquals(objetPropose.getUtilisateur(), echange.getProprietaireObjetPropose());
        assertEquals(objetDemande.getUtilisateur(), echange.getProprietaireObjetDemande());
    }

    /**
     * Tests the default constructor to ensure it sets all fields to null.
     */
    @Test
    void defaultConstructorSetsFieldsToNull() {
        Echange echange = new Echange();

        assertNull(echange.getObjetPropose());
        assertNull(echange.getObjetDemande());
        assertNull(echange.getDateProposition());
        assertNull(echange.getEtatEchange());
        assertNull(echange.getDateCloture());
        assertNull(echange.getProprietaireObjetPropose());
        assertNull(echange.getProprietaireObjetDemande());
    }

    /**
     * Tests the setObjetPropose method to ensure it updates the objetPropose field.
     */
    @Test
    void setObjetProposeUpdatesObjetPropose() {
        Echange echange = new Echange();
        Objet objetPropose = new Objet();
        echange.setObjetPropose(objetPropose);

        assertEquals(objetPropose, echange.getObjetPropose());
    }

    /**
     * Tests the setObjetDemande method to ensure it updates the objetDemande field.
     */
    @Test
    void setObjetDemandeUpdatesObjetDemande() {
        Echange echange = new Echange();
        Objet objetDemande = new Objet();
        echange.setObjetDemande(objetDemande);

        assertEquals(objetDemande, echange.getObjetDemande());
    }

    /**
     * Tests the setDateProposition method to ensure it updates the dateProposition field.
     */
    @Test
    void setDatePropositionUpdatesDateProposition() {
        Echange echange = new Echange();
        LocalDateTime dateProposition = LocalDateTime.now();
        echange.setDateProposition(dateProposition);

        assertEquals(dateProposition, echange.getDateProposition());
    }

    /**
     * Tests the setEtatEchange method to ensure it updates the etatEchange field.
     */
    @Test
    void setEtatEchangeUpdatesEtatEchange() {
        Echange echange = new Echange();
        Etat etatEchange = Etat.ATTENTE;
        echange.setEtatEchange(etatEchange);

        assertEquals(etatEchange, echange.getEtatEchange());
    }

    /**
     * Tests the setDateCloture method to ensure it updates the dateCloture field.
     */
    @Test
    void setDateClotureUpdatesDateCloture() {
        Echange echange = new Echange();
        LocalDateTime dateCloture = LocalDateTime.now().plusDays(1);
        echange.setDateCloture(dateCloture);

        assertEquals(dateCloture, echange.getDateCloture());
    }

    /**
     * Tests the setProprietaireObjetPropose method to ensure it updates the proprietaireObjetPropose field.
     */
    @Test
    void setProprietaireObjetProposeUpdatesProprietaireObjetPropose() {
        Echange echange = new Echange();
        Utilisateur proprietaireObjetPropose = new Utilisateur();
        echange.setProprietaireObjetPropose(proprietaireObjetPropose);

        assertEquals(proprietaireObjetPropose, echange.getProprietaireObjetPropose());
    }

    /**
     * Tests the setProprietaireObjetDemande method to ensure it updates the proprietaireObjetDemande field.
     */
    @Test
    void setProprietaireObjetDemandeUpdatesProprietaireObjetDemande() {
        Echange echange = new Echange();
        Utilisateur proprietaireObjetDemande = new Utilisateur();
        echange.setProprietaireObjetDemande(proprietaireObjetDemande);

        assertEquals(proprietaireObjetDemande, echange.getProprietaireObjetDemande());
    }
}
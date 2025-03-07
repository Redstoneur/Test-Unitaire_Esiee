package fr.knap.testunitaire_esiee.model;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class EchangeTest {

    @Test
    void constructorWithParameters_SetsAllFields() {
        Objet objetPropose = new Objet();
        Objet objetDemande = new Objet();
        LocalDateTime dateProposition = LocalDateTime.now();
        Etat etatEchange = Etat.ATTENTE;
        LocalDateTime dateCloture = LocalDateTime.now().plusDays(1);

        Echange echange = new Echange(objetPropose, objetDemande, dateProposition, etatEchange, dateCloture);

        assertEquals(objetPropose, echange.getObjetPropose());
        assertEquals(objetDemande, echange.getObjetDemande());
        assertEquals(dateProposition, echange.getDateProposition());
        assertEquals(etatEchange, echange.getEtatEchange());
        assertEquals(dateCloture, echange.getDateCloture());
    }

    @Test
    void defaultConstructor_SetsFieldsToNull() {
        Echange echange = new Echange();

        assertNull(echange.getObjetPropose());
        assertNull(echange.getObjetDemande());
        assertNull(echange.getDateProposition());
        assertNull(echange.getEtatEchange());
        assertNull(echange.getDateCloture());
    }

    @Test
    void setObjetPropose_UpdatesObjetPropose() {
        Echange echange = new Echange();
        Objet objetPropose = new Objet();
        echange.setObjetPropose(objetPropose);

        assertEquals(objetPropose, echange.getObjetPropose());
    }

    @Test
    void setObjetDemande_UpdatesObjetDemande() {
        Echange echange = new Echange();
        Objet objetDemande = new Objet();
        echange.setObjetDemande(objetDemande);

        assertEquals(objetDemande, echange.getObjetDemande());
    }

    @Test
    void setDateProposition_UpdatesDateProposition() {
        Echange echange = new Echange();
        LocalDateTime dateProposition = LocalDateTime.now();
        echange.setDateProposition(dateProposition);

        assertEquals(dateProposition, echange.getDateProposition());
    }

    @Test
    void setEtatEchange_UpdatesEtatEchange() {
        Echange echange = new Echange();
        Etat etatEchange = Etat.ATTENTE;
        echange.setEtatEchange(etatEchange);

        assertEquals(etatEchange, echange.getEtatEchange());
    }

    @Test
    void setDateCloture_UpdatesDateCloture() {
        Echange echange = new Echange();
        LocalDateTime dateCloture = LocalDateTime.now().plusDays(1);
        echange.setDateCloture(dateCloture);

        assertEquals(dateCloture, echange.getDateCloture());
    }
}
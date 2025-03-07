package fr.knap.testunitaire_esiee.model;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the Objet class.
 */
@SpringBootTest
class ObjetTest {

    /**
     * Tests the constructor with parameters to ensure it sets all fields correctly.
     */
    @Test
    void constructorWithParameters_SetsAllFields() {
        Utilisateur utilisateur = new Utilisateur();
        String nom = "Laptop";
        String description = "A high-end gaming laptop";
        CategorieObjet categorie = CategorieObjet.INFORMATIQUE;
        LocalDateTime dateCreation = LocalDateTime.now();

        Objet objet = new Objet(utilisateur, nom, description, categorie, dateCreation);

        assertEquals(utilisateur, objet.getUtilisateur());
        assertEquals(nom, objet.getNom());
        assertEquals(description, objet.getDescription());
        assertEquals(categorie, objet.getCategorie());
        assertEquals(dateCreation, objet.getDateCreation());
    }

    /**
     * Tests the default constructor to ensure it sets all fields to null.
     */
    @Test
    void defaultConstructor_SetsFieldsToNull() {
        Objet objet = new Objet();

        assertNull(objet.getUtilisateur());
        assertNull(objet.getNom());
        assertNull(objet.getDescription());
        assertNull(objet.getCategorie());
        assertNull(objet.getDateCreation());
    }

    /**
     * Tests the setUtilisateur method to ensure it updates the utilisateur field.
     */
    @Test
    void setUtilisateur_UpdatesUtilisateur() {
        Objet objet = new Objet();
        Utilisateur utilisateur = new Utilisateur();
        objet.setUtilisateur(utilisateur);

        assertEquals(utilisateur, objet.getUtilisateur());
    }

    /**
     * Tests the setNom method to ensure it updates the nom field.
     */
    @Test
    void setNom_UpdatesNom() {
        Objet objet = new Objet();
        String nom = "Smartphone";
        objet.setNom(nom);

        assertEquals(nom, objet.getNom());
    }

    /**
     * Tests the setDescription method to ensure it updates the description field.
     */
    @Test
    void setDescription_UpdatesDescription() {
        Objet objet = new Objet();
        String description = "A brand new smartphone";
        objet.setDescription(description);

        assertEquals(description, objet.getDescription());
    }

    /**
     * Tests the setCategorie method to ensure it updates the categorie field.
     */
    @Test
    void setCategorie_UpdatesCategorie() {
        Objet objet = new Objet();
        CategorieObjet categorie = CategorieObjet.ELECTROMENAGER;
        objet.setCategorie(categorie);

        assertEquals(categorie, objet.getCategorie());
    }

    /**
     * Tests the setDateCreation method to ensure it updates the dateCreation field.
     */
    @Test
    void setDateCreation_UpdatesDateCreation() {
        Objet objet = new Objet();
        LocalDateTime dateCreation = LocalDateTime.now();
        objet.setDateCreation(dateCreation);

        assertEquals(dateCreation, objet.getDateCreation());
    }
}
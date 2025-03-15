package fr.knap.testunitaire_esiee.dto;

import fr.knap.testunitaire_esiee.model.CategorieObjet;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the ObjetBufferDTO class.
 */
@SpringBootTest
class ObjetBufferDTOTest {

    /**
     * Tests the constructor with parameters to ensure it sets all fields correctly.
     */
    @Test
    void constructorWithParametersSetsAllFields() {
        String nom = "Laptop";
        String description = "A high-end gaming laptop";
        CategorieObjet categorie = CategorieObjet.INFORMATIQUE;
        LocalDateTime dateCreation = LocalDateTime.now();

        ObjetBufferDTO objetBufferDTO = new ObjetBufferDTO(nom, description, categorie);

        // Check that all fields are set correctly
        assertEquals(nom, objetBufferDTO.getNom()); // Check that the nom field is set correctly
        assertEquals(description, objetBufferDTO.getDescription()); // Check that the description field is set correctly
        assertEquals(categorie, objetBufferDTO.getCategorie()); // Check that the categorie field is set correctly
        assertEquals(
                dateCreation.withSecond(0).withNano(0),
                objetBufferDTO.getDateCreation().withSecond(0).withNano(0)
        ); // Check that the dateCreation field is set correctly (ignoring seconds and nanoseconds)
    }

    /**
     * Tests the setNom method to ensure it updates the nom field.
     */
    @Test
    void setNomUpdatesNom() {
        ObjetBufferDTO objetBufferDTO = new ObjetBufferDTO("Laptop", "A high-end gaming laptop", CategorieObjet.INFORMATIQUE);
        String newNom = "Smartphone";
        objetBufferDTO.setNom(newNom);

        assertEquals(newNom, objetBufferDTO.getNom());
    }

    /**
     * Tests the setDescription method to ensure it updates the description field.
     */
    @Test
    void setDescriptionUpdatesDescription() {
        ObjetBufferDTO objetBufferDTO = new ObjetBufferDTO("Laptop", "A high-end gaming laptop", CategorieObjet.INFORMATIQUE);
        String newDescription = "A brand new smartphone";
        objetBufferDTO.setDescription(newDescription);

        assertEquals(newDescription, objetBufferDTO.getDescription());
    }

    /**
     * Tests the setCategorie method to ensure it updates the categorie field.
     */
    @Test
    void setCategorieUpdatesCategorie() {
        ObjetBufferDTO objetBufferDTO = new ObjetBufferDTO("Laptop", "A high-end gaming laptop", CategorieObjet.INFORMATIQUE);
        CategorieObjet newCategorie = CategorieObjet.ELECTROMENAGER;
        objetBufferDTO.setCategorie(newCategorie);

        assertEquals(newCategorie, objetBufferDTO.getCategorie());
    }

    /**
     * Tests the setDateCreation method to ensure it updates the dateCreation field.
     */
    @Test
    void setDateCreationUpdatesDateCreation() {
        ObjetBufferDTO objetBufferDTO = new ObjetBufferDTO("Laptop", "A high-end gaming laptop", CategorieObjet.INFORMATIQUE);
        LocalDateTime newDateCreation = LocalDateTime.now().plusDays(1);
        objetBufferDTO.setDateCreation(newDateCreation);

        assertEquals(newDateCreation, objetBufferDTO.getDateCreation());
    }
}
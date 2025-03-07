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
    void constructorWithParameters_SetsAllFields() {
        String nom = "Laptop";
        String description = "A high-end gaming laptop";
        CategorieObjet categorie = CategorieObjet.INFORMATIQUE;
        LocalDateTime dateCreation = LocalDateTime.now();

        ObjetBufferDTO objetBufferDTO = new ObjetBufferDTO(nom, description, categorie, dateCreation);

        assertEquals(nom, objetBufferDTO.getNom());
        assertEquals(description, objetBufferDTO.getDescription());
        assertEquals(categorie, objetBufferDTO.getCategorie());
        assertEquals(dateCreation, objetBufferDTO.getDateCreation());
    }

    /**
     * Tests the setNom method to ensure it updates the nom field.
     */
    @Test
    void setNom_UpdatesNom() {
        ObjetBufferDTO objetBufferDTO = new ObjetBufferDTO("Laptop", "A high-end gaming laptop", CategorieObjet.INFORMATIQUE, LocalDateTime.now());
        String newNom = "Smartphone";
        objetBufferDTO.setNom(newNom);

        assertEquals(newNom, objetBufferDTO.getNom());
    }

    /**
     * Tests the setDescription method to ensure it updates the description field.
     */
    @Test
    void setDescription_UpdatesDescription() {
        ObjetBufferDTO objetBufferDTO = new ObjetBufferDTO("Laptop", "A high-end gaming laptop", CategorieObjet.INFORMATIQUE, LocalDateTime.now());
        String newDescription = "A brand new smartphone";
        objetBufferDTO.setDescription(newDescription);

        assertEquals(newDescription, objetBufferDTO.getDescription());
    }

    /**
     * Tests the setCategorie method to ensure it updates the categorie field.
     */
    @Test
    void setCategorie_UpdatesCategorie() {
        ObjetBufferDTO objetBufferDTO = new ObjetBufferDTO("Laptop", "A high-end gaming laptop", CategorieObjet.INFORMATIQUE, LocalDateTime.now());
        CategorieObjet newCategorie = CategorieObjet.ELECTROMENAGER;
        objetBufferDTO.setCategorie(newCategorie);

        assertEquals(newCategorie, objetBufferDTO.getCategorie());
    }

    /**
     * Tests the setDateCreation method to ensure it updates the dateCreation field.
     */
    @Test
    void setDateCreation_UpdatesDateCreation() {
        ObjetBufferDTO objetBufferDTO = new ObjetBufferDTO("Laptop", "A high-end gaming laptop", CategorieObjet.INFORMATIQUE, LocalDateTime.now());
        LocalDateTime newDateCreation = LocalDateTime.now().plusDays(1);
        objetBufferDTO.setDateCreation(newDateCreation);

        assertEquals(newDateCreation, objetBufferDTO.getDateCreation());
    }
}
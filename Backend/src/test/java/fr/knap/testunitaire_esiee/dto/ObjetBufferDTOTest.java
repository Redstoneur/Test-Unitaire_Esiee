package fr.knap.testunitaire_esiee.dto;

import fr.knap.testunitaire_esiee.model.CategorieObjet;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ObjetBufferDTOTest {

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

    @Test
    void setNom_UpdatesNom() {
        ObjetBufferDTO objetBufferDTO = new ObjetBufferDTO("Laptop", "A high-end gaming laptop", CategorieObjet.INFORMATIQUE, LocalDateTime.now());
        String newNom = "Smartphone";
        objetBufferDTO.setNom(newNom);

        assertEquals(newNom, objetBufferDTO.getNom());
    }

    @Test
    void setDescription_UpdatesDescription() {
        ObjetBufferDTO objetBufferDTO = new ObjetBufferDTO("Laptop", "A high-end gaming laptop", CategorieObjet.INFORMATIQUE, LocalDateTime.now());
        String newDescription = "A brand new smartphone";
        objetBufferDTO.setDescription(newDescription);

        assertEquals(newDescription, objetBufferDTO.getDescription());
    }

    @Test
    void setCategorie_UpdatesCategorie() {
        ObjetBufferDTO objetBufferDTO = new ObjetBufferDTO("Laptop", "A high-end gaming laptop", CategorieObjet.INFORMATIQUE, LocalDateTime.now());
        CategorieObjet newCategorie = CategorieObjet.ELECTROMENAGER;
        objetBufferDTO.setCategorie(newCategorie);

        assertEquals(newCategorie, objetBufferDTO.getCategorie());
    }

    @Test
    void setDateCreation_UpdatesDateCreation() {
        ObjetBufferDTO objetBufferDTO = new ObjetBufferDTO("Laptop", "A high-end gaming laptop", CategorieObjet.INFORMATIQUE, LocalDateTime.now());
        LocalDateTime newDateCreation = LocalDateTime.now().plusDays(1);
        objetBufferDTO.setDateCreation(newDateCreation);

        assertEquals(newDateCreation, objetBufferDTO.getDateCreation());
    }
}
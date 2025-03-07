package fr.knap.testunitaire_esiee.dto;

import fr.knap.testunitaire_esiee.model.CategorieObjet;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDateTime;

@SpringBootTest
class ObjetDTOTest {

    @Test
    void constructorWithParameters_SetsAllFields() {
        String nom = "Laptop";
        String description = "A high-end gaming laptop";
        CategorieObjet categorie = CategorieObjet.INFORMATIQUE;
        String utilisateur = "user123";
        Long idUtilisateur = 1L;
        LocalDateTime dateCreation = LocalDateTime.now();

        ObjetDTO objetDTO = new ObjetDTO(nom, description, categorie, utilisateur, idUtilisateur, dateCreation);

        assertEquals(nom, objetDTO.getNom());
        assertEquals(description, objetDTO.getDescription());
        assertEquals(categorie, objetDTO.getCategorie());
        assertEquals(utilisateur, objetDTO.getUtilisateur());
        assertEquals(idUtilisateur, objetDTO.getIdUtilisateur());
        assertEquals(dateCreation, objetDTO.getDateCreation());
    }

    @Test
    void setNom_UpdatesNom() {
        ObjetDTO objetDTO = new ObjetDTO("Laptop", "A high-end gaming laptop", CategorieObjet.INFORMATIQUE, "user123", 1L, LocalDateTime.now());
        String newNom = "Smartphone";
        objetDTO.setNom(newNom);

        assertEquals(newNom, objetDTO.getNom());
    }

    @Test
    void setDescription_UpdatesDescription() {
        ObjetDTO objetDTO = new ObjetDTO("Laptop", "A high-end gaming laptop", CategorieObjet.INFORMATIQUE, "user123", 1L, LocalDateTime.now());
        String newDescription = "A brand new smartphone";
        objetDTO.setDescription(newDescription);

        assertEquals(newDescription, objetDTO.getDescription());
    }

    @Test
    void setCategorie_UpdatesCategorie() {
        ObjetDTO objetDTO = new ObjetDTO("Laptop", "A high-end gaming laptop", CategorieObjet.INFORMATIQUE, "user123", 1L, LocalDateTime.now());
        CategorieObjet newCategorie = CategorieObjet.ELECTROMENAGER;
        objetDTO.setCategorie(newCategorie);

        assertEquals(newCategorie, objetDTO.getCategorie());
    }

    @Test
    void setUtilisateur_UpdatesUtilisateur() {
        ObjetDTO objetDTO = new ObjetDTO("Laptop", "A high-end gaming laptop", CategorieObjet.INFORMATIQUE, "user123", 1L, LocalDateTime.now());
        String newUtilisateur = "user456";
        objetDTO.setUtilisateur(newUtilisateur);

        assertEquals(newUtilisateur, objetDTO.getUtilisateur());
    }

    @Test
    void setIdUtilisateur_UpdatesIdUtilisateur() {
        ObjetDTO objetDTO = new ObjetDTO("Laptop", "A high-end gaming laptop", CategorieObjet.INFORMATIQUE, "user123", 1L, LocalDateTime.now());
        Long newIdUtilisateur = 2L;
        objetDTO.setIdUtilisateur(newIdUtilisateur);

        assertEquals(newIdUtilisateur, objetDTO.getIdUtilisateur());
    }

    @Test
    void setDateCreation_UpdatesDateCreation() {
        ObjetDTO objetDTO = new ObjetDTO("Laptop", "A high-end gaming laptop", CategorieObjet.INFORMATIQUE, "user123", 1L, LocalDateTime.now());
        LocalDateTime newDateCreation = LocalDateTime.now().plusDays(1);
        objetDTO.setDateCreation(newDateCreation);

        assertEquals(newDateCreation, objetDTO.getDateCreation());
    }
}
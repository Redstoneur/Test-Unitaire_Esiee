package fr.knap.testunitaire_esiee.dto;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UtilisateurDTOTest {

    @Test
    void constructorWithAllParameters_SetsAllFields() {
        String pseudo = "user123";
        String nom = "Doe";
        String prenom = "John";

        UtilisateurDTO utilisateurDTO = new UtilisateurDTO(pseudo, nom, prenom);

        assertEquals(pseudo, utilisateurDTO.getPseudo());
        assertEquals(nom, utilisateurDTO.getNom());
        assertEquals(prenom, utilisateurDTO.getPrenom());
    }

    @Test
    void constructorWithPseudo_SetsPseudoOnly() {
        String pseudo = "user123";

        UtilisateurDTO utilisateurDTO = new UtilisateurDTO(pseudo);

        assertEquals(pseudo, utilisateurDTO.getPseudo());
        assertNull(utilisateurDTO.getNom());
        assertNull(utilisateurDTO.getPrenom());
    }

    @Test
    void setPseudo_UpdatesPseudo() {
        UtilisateurDTO utilisateurDTO = new UtilisateurDTO("initialPseudo");
        String newPseudo = "updatedPseudo";
        utilisateurDTO.setPseudo(newPseudo);

        assertEquals(newPseudo, utilisateurDTO.getPseudo());
    }

    @Test
    void setNom_UpdatesNom() {
        UtilisateurDTO utilisateurDTO = new UtilisateurDTO("user123");
        String newNom = "Smith";
        utilisateurDTO.setNom(newNom);

        assertEquals(newNom, utilisateurDTO.getNom());
    }

    @Test
    void setPrenom_UpdatesPrenom() {
        UtilisateurDTO utilisateurDTO = new UtilisateurDTO("user123");
        String newPrenom = "Jane";
        utilisateurDTO.setPrenom(newPrenom);

        assertEquals(newPrenom, utilisateurDTO.getPrenom());
    }
}
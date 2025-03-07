package fr.knap.testunitaire_esiee.model;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class UtilisateurTest {

    @Test
    void constructorWithParameters_SetsAllFields() {
        String pseudo = "user123";
        String mdp = "password123";
        String mail = "user@example.com";
        String nom = "Doe";
        String prenom = "John";

        Utilisateur utilisateur = new Utilisateur(pseudo, mdp, mail, nom, prenom);

        assertEquals(pseudo, utilisateur.getPseudo());
        assertEquals(mdp, utilisateur.getMdp());
        assertEquals(mail, utilisateur.getMail());
        assertEquals(nom, utilisateur.getNom());
        assertEquals(prenom, utilisateur.getPrenom());
        assertNotNull(utilisateur.getObjets());
        assertTrue(utilisateur.getObjets().isEmpty());
    }

    @Test
    void constructorWithParametersAndObjects_SetsAllFields() {
        String pseudo = "user123";
        String mdp = "password123";
        String mail = "user@example.com";
        String nom = "Doe";
        String prenom = "John";
        List<Objet> objets = new ArrayList<>();
        objets.add(new Objet());

        Utilisateur utilisateur = new Utilisateur(pseudo, mdp, mail, nom, prenom, objets);

        assertEquals(pseudo, utilisateur.getPseudo());
        assertEquals(mdp, utilisateur.getMdp());
        assertEquals(mail, utilisateur.getMail());
        assertEquals(nom, utilisateur.getNom());
        assertEquals(prenom, utilisateur.getPrenom());
        assertEquals(objets, utilisateur.getObjets());
    }

    @Test
    void defaultConstructor_SetsFieldsToNull() {
        Utilisateur utilisateur = new Utilisateur();

        assertNull(utilisateur.getPseudo());
        assertNull(utilisateur.getMdp());
        assertNull(utilisateur.getMail());
        assertNull(utilisateur.getNom());
        assertNull(utilisateur.getPrenom());
        assertNull(utilisateur.getObjets());
    }

    @Test
    void setPseudo_UpdatesPseudo() {
        Utilisateur utilisateur = new Utilisateur();
        String pseudo = "newUser123";
        utilisateur.setPseudo(pseudo);

        assertEquals(pseudo, utilisateur.getPseudo());
    }

    @Test
    void setMdp_UpdatesMdp() {
        Utilisateur utilisateur = new Utilisateur();
        String mdp = "newPassword123";
        utilisateur.setMdp(mdp);

        assertEquals(mdp, utilisateur.getMdp());
    }

    @Test
    void setMail_UpdatesMail() {
        Utilisateur utilisateur = new Utilisateur();
        String mail = "newuser@example.com";
        utilisateur.setMail(mail);

        assertEquals(mail, utilisateur.getMail());
    }

    @Test
    void setNom_UpdatesNom() {
        Utilisateur utilisateur = new Utilisateur();
        String nom = "Smith";
        utilisateur.setNom(nom);

        assertEquals(nom, utilisateur.getNom());
    }

    @Test
    void setPrenom_UpdatesPrenom() {
        Utilisateur utilisateur = new Utilisateur();
        String prenom = "Jane";
        utilisateur.setPrenom(prenom);

        assertEquals(prenom, utilisateur.getPrenom());
    }

    @Test
    void setObjets_UpdatesObjets() {
        Utilisateur utilisateur = new Utilisateur();
        List<Objet> objets = new ArrayList<>();
        objets.add(new Objet());
        utilisateur.setObjets(objets);

        assertEquals(objets, utilisateur.getObjets());
    }
}
package fr.knap.testunitaire_esiee.model;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

@SpringBootTest
class UtilisateurTest {

    private static Utilisateur utilisateur;
    private static Utilisateur utilisateur2;
    private static Utilisateur utilisateur3;

    @BeforeAll
    static void setUp() {
        utilisateur = new Utilisateur();
        utilisateur.setPseudo("testPseudo");
        utilisateur.setMdp("testMdp");
        utilisateur.setMail("test@mail.com");
        utilisateur.setNom("TestNom");
        utilisateur.setPrenom("TestPrenom");
        utilisateur.setObjets(new ArrayList<>());

        utilisateur2 = new Utilisateur(
                "testPseudo2",
                "testMdp2",
                "test2@mail.com",
                "TestNom2",
                "TestPrenom2"
        );

        utilisateur3 = new Utilisateur(
                "testPseudo3",
                "testMdp3",
                "test3@mail.com",
                "TestNom3",
                "TestPrenom3",
                new ArrayList<>()
        );
    }

    @Test
    void testUtilisateurPseudo() {
        assertEquals("testPseudo", utilisateur.getPseudo());
        assertEquals("testPseudo2", utilisateur2.getPseudo());
        assertEquals("testPseudo3", utilisateur3.getPseudo());
    }

    @Test
    void testUtilisateurMdp() {
        assertEquals("testMdp", utilisateur.getMdp());
        assertEquals("testMdp2", utilisateur2.getMdp());
        assertEquals("testMdp3", utilisateur3.getMdp());
    }

    @Test
    void testUtilisateurMail() {
        assertEquals("test@mail.com", utilisateur.getMail());
        assertEquals("test2@mail.com", utilisateur2.getMail());
        assertEquals("test3@mail.com", utilisateur3.getMail());
    }

    @Test
    void testUtilisateurNom() {
        assertEquals("TestNom", utilisateur.getNom());
        assertEquals("TestNom2", utilisateur2.getNom());
        assertEquals("TestNom3", utilisateur3.getNom());
    }

    @Test
    void testUtilisateurPrenom() {
        assertEquals("TestPrenom", utilisateur.getPrenom());
        assertEquals("TestPrenom2", utilisateur2.getPrenom());
        assertEquals("TestPrenom3", utilisateur3.getPrenom());
    }

    @Test
    void testUtilisateurObjets() {
        assertNotNull(utilisateur.getObjets());
        assertNotNull(utilisateur3.getObjets());
    }
}
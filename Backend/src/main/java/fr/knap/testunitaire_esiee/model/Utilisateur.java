package fr.knap.testunitaire_esiee.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.*;

/**
 * Entity class representing a user.
 */
@Entity
@Getter
@Setter
public class Utilisateur {

    /**
     * The unique identifier for the user.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The username of the user.
     */
    @Column(nullable = false, unique = true)
    private String pseudo;

    /**
     * The password of the user.
     */
    private String mdp;

    /**
     * The email of the user.
     */
    @Column(nullable = false, unique = true)
    private String mail;

    /**
     * The last name of the user.
     */
    private String nom;

    /**
     * The first name of the user.
     */
    private String prenom;

    /**
     * Constructs a new Utilisateur object with the specified details.
     *
     * @param pseudo The username of the user.
     * @param mdp    The password of the user.
     * @param mail   The email of the user.
     * @param nom    The last name of the user.
     * @param prenom The first name of the user.
     */
    public Utilisateur(String pseudo, String mdp, String mail, String nom, String prenom) {
        this.pseudo = pseudo;
        this.mdp = mdp;
        this.mail = mail;
        this.nom = nom;
        this.prenom = prenom;
    }

    /**
     * Default constructor for Utilisateur.
     */
    public Utilisateur() {
    }
}
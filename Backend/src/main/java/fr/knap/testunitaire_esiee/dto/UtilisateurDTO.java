package fr.knap.testunitaire_esiee.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * Data Transfer Object (DTO) representing a user.
 */
@Getter
@Setter
public class UtilisateurDTO {

    /**
     * The unique identifier for the user.
     */
    private Long id;

    /**
     * The pseudo of the user.
     */
    private String pseudo;

    /**
     * The last name of the user.
     */
    private String nom;

    /**
     * The first name of the user.
     */
    private String prenom;

    /**
     * Constructs a new UtilisateurDTO with the specified pseudo, last name, and first name.
     *
     * @param pseudo the pseudo of the user
     * @param nom the last name of the user
     * @param prenom the first name of the user
     */
    public UtilisateurDTO(Long id, String pseudo, String nom, String prenom) {
        this.id = id;
        this.pseudo = pseudo;
        this.nom = nom;
        this.prenom = prenom;
    }

    /**
     * Constructs a new UtilisateurDTO with the specified pseudo.
     *
     * @param pseudo the pseudo of the user
     */
    public UtilisateurDTO(String pseudo) {
        this.pseudo = pseudo;
    }
}
package fr.knap.testunitaire_esiee.dto;

import fr.knap.testunitaire_esiee.model.CategorieObjet;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * Data Transfer Object (DTO) representing an object.
 */
@Getter
@Setter
public class ObjetDTO {

    /**
     * The unique identifier for the object.
     */
    private Long id;

    /**
     * The name of the object.
     */
    private String nom;

    /**
     * The description of the object.
     */
    private String description;

    /**
     * The category of the object.
     */
    private CategorieObjet categorie;

    /**
     * The creation date of the object.
     */
    private LocalDateTime dateCreation;

    /**
     * The user associated with the object.
     */
    private String utilisateur;

    /**
     * The ID of the user associated with the object.
     */
    private Long idUtilisateur;

    /**
     * Constructs a new ObjetDTO with the specified name, description, category, user, user ID, and creation date.
     *
     * @param nom           the name of the object
     * @param description   the description of the object
     * @param categorie     the category of the object
     * @param utilisateur   the user associated with the object
     * @param idUtilisateur the ID of the user associated with the object
     * @param dateCreation  the creation date of the object
     */
    public ObjetDTO(Long id, String nom, String description, CategorieObjet categorie, String utilisateur, Long idUtilisateur, LocalDateTime dateCreation) {
        this.nom = nom;
        this.description = description;
        this.categorie = categorie;
        this.utilisateur = utilisateur;
        this.idUtilisateur = idUtilisateur;
        this.dateCreation = dateCreation;
    }
}
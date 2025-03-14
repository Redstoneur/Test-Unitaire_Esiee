package fr.knap.testunitaire_esiee.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * Entity class representing an object.
 */
@Entity
@Getter
@Setter
public class Objet {

    /**
     * The unique identifier for the object.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    /**
     * The name of the object.
     */
    private String nom;

    /**
     * The category of the object.
     */
    @Enumerated(EnumType.STRING)
    private CategorieObjet categorie;

    /**
     * The description of the object.
     */
    private String description;

    /**
     * The creation date of the object.
     */
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime dateCreation;

    /**
     * The deletion date of the object.
     */
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime DateSuppression;

    /**
     * The user associated with the object.
     */
    @ManyToOne
    @JoinColumn(name = "UTILISATEUR_ID")
    private Utilisateur utilisateur;

    /**
     * Default constructor.
     */
    public Objet() {
    }

    /**
     * Constructs a new Objet object with the specified details.
     *
     * @param utilisateur  The user associated with the object.
     * @param nom          The name of the object.
     * @param description  The description of the object.
     * @param categorie    The category of the object.
     * @param dateCreation The creation date of the object.
     */
    public Objet(
            Utilisateur utilisateur, String nom,
            String description, CategorieObjet categorie,
            LocalDateTime dateCreation
    ) {
        this.utilisateur = utilisateur;
        this.nom = nom;
        this.description = description;
        this.categorie = categorie;
        this.dateCreation = dateCreation;
        this.DateSuppression = null;
    }
}
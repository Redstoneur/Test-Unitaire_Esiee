package fr.knap.testunitaire_esiee.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZonedDateTime;

/**
 * 
 */
@Entity
@Getter
@Setter
public class Objet {

    @ManyToOne
    @JoinColumn(name = "utilisateur_id")
    private Utilisateur utilisateur;

    /**
     * Default constructor
     */
    public Objet() {
    }

    public Objet(Utilisateur utilisateur, String nom, String description, CategorieObjet categorie, LocalDateTime dateCreation) {
        this.utilisateur = utilisateur;
        this.nom = nom;
        this.description = description;
        this.categorie = categorie;
        this.dateCreation = dateCreation;
    }

    /**
     *
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;


    private String nom;

    @Enumerated(EnumType.STRING)
    private CategorieObjet categorie;

    private String description;


    /**
     * 
     */
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime dateCreation;

}
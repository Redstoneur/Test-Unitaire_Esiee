package fr.knap.testunitaire_esiee.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

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

    /**
     *
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    /**
     * 
     */
    private String nom;

    /**
     * 
     */

    @Enumerated(EnumType.STRING)
    private CategorieObjet categorie;

    private String description;


    /**
     * 
     */
    private Timestamp dateCreation;

}
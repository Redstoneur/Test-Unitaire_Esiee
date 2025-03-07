package fr.knap.testunitaire_esiee.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;


/**
 * Entity class representing an object.
 */
@Getter
@Setter
public class ObjetBuffer {
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

    public ObjetBuffer(String nom, String description, CategorieObjet categorie, LocalDateTime dateCreation) {
        this.nom = nom;
        this.description = description;
        this.categorie = categorie;
        this.dateCreation = dateCreation;
    }
}

package fr.knap.testunitaire_esiee.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import fr.knap.testunitaire_esiee.model.CategorieObjet;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * Data Transfer Object (DTO) representing an object buffer.
 */
@Getter
@Setter
public class ObjetBufferDTO {

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
     * Constructs a new ObjetBufferDTO with the specified name, description, category, and creation date.
     *
     * @param nom          the name of the object
     * @param description  the description of the object
     * @param categorie    the category of the object
     */
    public ObjetBufferDTO(String nom, String description, CategorieObjet categorie) {
        this.nom = nom;
        this.description = description;
        this.categorie = categorie;
        this.dateCreation = LocalDateTime.now();
    }
}
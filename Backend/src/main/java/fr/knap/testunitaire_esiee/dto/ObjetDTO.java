package fr.knap.testunitaire_esiee.dto;

import fr.knap.testunitaire_esiee.model.CategorieObjet;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ObjetDTO {
    private String nom;
    private String description;
    private CategorieObjet categorie;
    private LocalDateTime dateCreation;
    private String utilisateur;
    private Long idUtilisateur;

    public ObjetDTO(String nom, String description, CategorieObjet categorie, String utilisateur, Long idUtilisateur, LocalDateTime dateCreation) {
        this.nom = nom;
        this.description = description;
        this.categorie = categorie;
        this.utilisateur = utilisateur;
        this.idUtilisateur = idUtilisateur;
        this.dateCreation = dateCreation;
    }

    public ObjetDTO(String nom) {
        this.nom = nom;
    }
}

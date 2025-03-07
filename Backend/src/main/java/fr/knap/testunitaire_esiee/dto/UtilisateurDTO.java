package fr.knap.testunitaire_esiee.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UtilisateurDTO {
    private String pseudo;
    private String nom;
    private String prenom;

    public UtilisateurDTO(String pseudo, String nom, String prenom) {
        this.pseudo = pseudo;
        this.nom = nom;
        this.prenom = prenom;
    }

    public UtilisateurDTO(String pseudo) {
        this.pseudo = pseudo;
    }
}
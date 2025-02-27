package fr.knap.testunitaire_esiee.model;

import fr.knap.testunitaire_esiee.respository.Interaction;
import jakarta.persistence.*;

import java.io.*;
import java.sql.Timestamp;
import java.util.*;

/**
 * 
 */
@Entity
public class Utilisateur extends Interaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String pseudo;
    private String mdp;
    private String mail;
    private String nom;
    private String prenom;

    @OneToMany(mappedBy = "utilisateur")
    private List<Objet> objets;

    @Override
    public Object ConsulterObjet(Object object) {
        return null;
    }

    @Override
    public List<Objet> RechercheObjet() {
        return List.of();
    }
}
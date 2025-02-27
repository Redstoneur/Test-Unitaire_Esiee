package fr.knap.testunitaire_esiee.model;

import jakarta.persistence.*;

import java.sql.Timestamp;
import java.util.*;

/**
 * 
 */
@Entity
public class Echange {

    /**
     * Default constructor
     */
    public Echange() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @ManyToOne
    private Objet ObjetPropose;

    @ManyToOne
    private Objet ObjetDemande;

    /**
     * 
     */
    private Timestamp dateProposition;

    @Enumerated(EnumType.STRING)
    private Etat EtatEchange;

    /**
     * 
     */
    private Timestamp  dateCloture;
}
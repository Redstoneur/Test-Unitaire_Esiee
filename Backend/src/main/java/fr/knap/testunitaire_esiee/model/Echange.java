package fr.knap.testunitaire_esiee.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * Entity class representing an exchange.
 */
@Entity
@Getter
@Setter
public class Echange {

    /**
     * The unique identifier for the exchange.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    /**
     * The object proposed in the exchange.
     */
    @ManyToOne
    private Objet ObjetPropose;

    /**
     * The object requested in the exchange.
     */
    @ManyToOne
    private Objet ObjetDemande;

    /**
     * The date and time when the exchange was proposed.
     */
    private LocalDateTime dateProposition;

    /**
     * The current state of the exchange.
     */
    @Enumerated(EnumType.STRING)
    private Etat EtatEchange;

    /**
     * The date and time when the exchange was closed.
     */
    private LocalDateTime dateCloture;

    /**
     * The owner of the proposed object in the exchange.
     */
    @ManyToOne
    private Utilisateur proprietaireObjetPropose;

    /**
     * The owner of the requested object in the exchange.
     */
    @ManyToOne
    private Utilisateur proprietaireObjetDemande;

    /**
     * Default constructor.
     */
    public Echange() {
    }

    /**
     * Constructs a new Echange object with the specified details.
     *
     * @param objetPropose The object proposed in the exchange.
     * @param objetDemande The object requested in the exchange.
     * @param dateProposition The date and time when the exchange was proposed.
     * @param etatEchange The current state of the exchange.
     * @param dateCloture The date and time when the exchange was closed.
     */
    public Echange(Objet objetPropose, Objet objetDemande,
                   LocalDateTime dateProposition, Etat etatEchange,
                   LocalDateTime dateCloture) {
        this.ObjetPropose = objetPropose;
        this.ObjetDemande = objetDemande;
        this.dateProposition = dateProposition;
        this.EtatEchange = etatEchange;
        this.dateCloture = dateCloture;
        this.proprietaireObjetPropose = objetPropose.getUtilisateur();
        this.proprietaireObjetDemande = objetDemande.getUtilisateur();
    }

    /**
     * Constructs a new Echange object with the specified details.
     *
     * @param objetPropose The object proposed in the exchange.
     * @param objetDemande The object requested in the exchange.
     */
    public Echange(Objet objetPropose, Objet objetDemande) {
        this.ObjetPropose = objetPropose;
        this.ObjetDemande = objetDemande;
        this.dateProposition = LocalDateTime.now();
        this.EtatEchange = Etat.ATTENTE;
        this.proprietaireObjetPropose = objetPropose.getUtilisateur();
        this.proprietaireObjetDemande = objetDemande.getUtilisateur();
    }

}
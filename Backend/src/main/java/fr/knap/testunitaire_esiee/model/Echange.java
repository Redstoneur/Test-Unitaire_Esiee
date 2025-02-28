package fr.knap.testunitaire_esiee.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 *
 */
@Entity
@Getter
@Setter
public class Echange {

    /**
     * Default constructor
     */
    public Echange() {
    }

    public Echange(Objet objetPropose, Objet objetDemande, LocalDateTime dateProposition, Etat etatEchange, LocalDateTime dateCloture) {
        ObjetPropose = objetPropose;
        ObjetDemande = objetDemande;
        this.dateProposition = dateProposition;
        EtatEchange = etatEchange;
        this.dateCloture = dateCloture;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    private Objet ObjetPropose;

    @ManyToOne
    private Objet ObjetDemande;


    private LocalDateTime dateProposition;

    @Enumerated(EnumType.STRING)
    private Etat EtatEchange;

    private LocalDateTime dateCloture;
}
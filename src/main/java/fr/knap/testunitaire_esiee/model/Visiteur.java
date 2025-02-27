package fr.knap.testunitaire_esiee.model;

import fr.knap.testunitaire_esiee.respository.Interaction;
import jakarta.persistence.*;

import java.util.List;


@Entity
public class Visiteur extends Interaction {

    /**
     * Default constructor
     */
    public Visiteur() {
    }

    @Override
    public Object ConsulterObjet(Object object) {
        return null;
    }

    /*
    *
    * */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String pseudo;
    private String mdp;
    private String mail;

    /**
     * @return
     */
    public String Inscription() {
        // TODO implement here
        return null;
    }

    /**
     * @return
     */
    public List RechercheObjet() {
        return null;
    }

}
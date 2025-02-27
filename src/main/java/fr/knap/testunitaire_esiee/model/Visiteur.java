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

    /*
    *
    * */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @Override
    public Object ConsulterObjet(Object object) {
        return null;
    }

    /**
     * @return
     */
    public String Connexion() {
        // TODO implement here
        return null;
    }

    /**
     * @return
     */
    public String Inscription() {
        // TODO implement here
        return null;
    }

    /**
     * @param object
     * @return
     */
    public Objet ConsulterObjet(Objet object) {
        return null;
    }

    /**
     * @return
     */
    public List RechercheObjet() {
        return null;
    }

}
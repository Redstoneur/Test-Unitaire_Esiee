package fr.knap.testunitaire_esiee.model;

import fr.knap.testunitaire_esiee.respository.Interaction;
import jakarta.persistence.*;

import java.util.List;



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

    @Override
    public List<Objet> RechercheObjet() {
        return List.of();
    }

    public String Inscription() {
        // TODO implement here
        return null;
    }

}
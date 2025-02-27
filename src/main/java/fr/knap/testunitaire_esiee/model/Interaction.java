package fr.knap.testunitaire_esiee.model;

import java.util.*;

/**
 * 
 */
public abstract class Interaction {

    /**
     * Default constructor
     */
    public Interaction() {
    }

    /**
     * @param object 
     * @return
     */
    public abstract Object ConsulterObjet(Object object);

    /**
     * @return
     */
    public abstract List<Objet> RechercheObjet();

}
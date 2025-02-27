package fr.knap.testunitaire_esiee.respository;

import fr.knap.testunitaire_esiee.model.Objet;

import java.util.List;

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

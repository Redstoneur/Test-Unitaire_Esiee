package fr.knap.testunitaire_esiee.respository;

import fr.knap.testunitaire_esiee.model.Objet;

import java.util.List;

public abstract class Interaction {

    /**
     * @param object
     * @return
     */
    public Object ConsulterObjet(Object object) {
        return null;
    }

    /**
     * @return
     */
    public List<Objet> RechercheObjet() {
        return List.of();
    }
}

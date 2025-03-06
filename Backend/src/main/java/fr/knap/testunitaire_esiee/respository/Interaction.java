package fr.knap.testunitaire_esiee.respository;

import fr.knap.testunitaire_esiee.model.Objet;

import java.util.List;

public abstract class Interaction {

    /**
     * @param object
     * @return
     */
    public Object consulterObjet(Object object) {
        return null;
    }

    /**
     * @return
     */
    public List<Objet> rechercheObjet() {
        return List.of();
    }
}

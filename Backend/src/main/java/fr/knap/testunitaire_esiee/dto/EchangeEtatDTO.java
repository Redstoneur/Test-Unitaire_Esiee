package fr.knap.testunitaire_esiee.dto;

import fr.knap.testunitaire_esiee.model.Etat;
import lombok.Getter;
import lombok.Setter;

/**
 * Data Transfer Object for exchanging the state of an exchange.
 * Contains an identifier and the associated state.
 */
@Getter
@Setter
public class EchangeEtatDTO {

    /**
     * The identifier of the exchange.
     */
    long id;

    /**
     * The state associated with the exchange.
     */
    Etat etat;

    /**
     * Constructs an EchangeEtatDTO with the given exchange identifier and state.
     *
     * @param id the identifier of the exchange
     * @param etat the state associated with the exchange
     */
    public EchangeEtatDTO(long id, Etat etat) {
        this.id = id;
        this.etat = etat;
    }
}
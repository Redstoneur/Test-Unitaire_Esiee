package fr.knap.testunitaire_esiee.model;

/**
 * Enumeration representing the different states.
 */
public enum Etat {
    /**
     * State indicating that the process is waiting.
     */
    ATTENTE,

    /**
     * State indicating that the process is accepted.
     */
    ACCEPTE,

    /**
     * State indicating that the process is refused.
     */
    REFUSE,

    /**
     * State indicating that the process is canceled.
     */
    ANNULER
}
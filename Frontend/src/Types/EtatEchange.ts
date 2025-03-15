/**
 * Represents the state of an exchange.
 *
 * @enum {string}
 */
enum EtatEchange {
    /**
     * The exchange is waiting for a decision.
     */
    ATTENTE = "ATTENTE",

    /**
     * The exchange has been accepted.
     */
    ACCEPTE = "ACCEPTE",

    /**
     * The exchange has been refused.
     */
    REFUSE = "REFUSE",

    /**
     * The exchange has been cancelled.
     */
    ANNULER = "ANNULER"
}

export default EtatEchange;
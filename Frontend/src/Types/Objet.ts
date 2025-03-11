/**
 * Interface representing an object.
 */
interface Objet {
    /**
     * Unique identifier for the object.
     */
    id: number;

    /**
     * Name of the object.
     */
    nom: string;

    /**
     * Description of the object.
     */
    description: string;

    /**
     * Category of the object.
     */
    categorie: string;

    /**
     * Flag indicating whether to show input.
     */
    showInput: boolean;

    /**
     * Flag indicating whether the object is in exchange.
     */
    enEchange: boolean;

    /**
     * Identifier of the user associated with the object.
     */
    idUtilisateur: string;

    /**
     * Identifier of exchange associated with the object.
     */
    idEchange: number;
}

export default Objet;

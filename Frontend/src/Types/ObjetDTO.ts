import CategorieObjet from "./CategorieObjet";

/**
 * Interface representing an object.
 */
interface ObjetDTO {
    /** The name of the object */
    nom: string,
    /** The description of the object */
    description: string,
    /** The category of the object */
    categorie: CategorieObjet,
}

export default ObjetDTO;
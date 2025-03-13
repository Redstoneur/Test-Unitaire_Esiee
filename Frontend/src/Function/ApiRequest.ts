import Request from "./Request";
import RequestType from "../Types/RequestType";
import EtatEchange from "../Types/EtatEchange";
import ObjetDTO from "../Types/ObjetDTO";

/**
 * Base URL for API endpoints.
 */
const baseUrl = "http://localhost:3000/api";

/**
 * Sends a login request.
 *
 * @param mail - Email address used for login.
 * @param mdp - Password for login.
 * @returns A promise that resolves with a Response or an Error.
 */
export const Login = async (mail: string, mdp: string): Promise<Response | Error> => {
    return Request(
        `${baseUrl}/credentials/login`,
        RequestType.POST,
        {
            "Content-Type": "application/json"
        },
        {mail, mdp}
    );
};

/**
 * Sends a registration request.
 *
 * @param pseudo - User pseudo/username.
 * @param mdp - User password.
 * @param mail - User email.
 * @param nom - User last name.
 * @param prenom - User first name.
 * @returns A promise that resolves with a Response or an Error.
 */
export const Register = async (pseudo: string, mdp: string, mail: string, nom: string, prenom: string): Promise<Response | Error> => {
    return Request(
        `${baseUrl}/credentials/register`,
        RequestType.POST,
        {
            "Content-Type": "application/json"
        },
        {pseudo, mdp, mail, nom, prenom}
    );
};

/**
 * Sends a token verification request.
 *
 * @param token - Authentication token to verify.
 * @returns A promise that resolves with a Response or an Error.
 */
export const VerifyToken = async (token: string): Promise<Response | Error> => {
    return Request(
        `${baseUrl}/credentials/verifyToken`,
        RequestType.POST,
        {
            "Content-Type": "application/json"
        },
        {token}
    );
};

/**
 * Verifies if the provided token is valid.
 *
 * @param token - Authentication token.
 * @returns A promise that resolves to true if the token is valid, false otherwise.
 */
export const BooleanVerifyToken = async (token: string): Promise<boolean> => {
    const response = await VerifyToken(token);

    if (response instanceof Error) {
        return false;
    }

    return response.status === 200;
};

/**
 * Fetches user information using the provided token.
 *
 * @param token - Authentication token.
 * @returns A promise that resolves with a Response or an Error.
 */
export const UserInformation = async (token: string): Promise<Response | Error> => {
    return Request(
        `${baseUrl}/utilisateurs/trans`,
        RequestType.GET,
        {
            "Content-Type": "application/json",
            "Authorization": token
        }
    );
};

/**
 * Retrieves all available objects.
 *
 * @returns A promise that resolves with a Response or an Error.
 */
export const GetObjets = async (): Promise<Response | Error> => {
    return Request(
        `${baseUrl}/objets`,
        RequestType.GET,
        {
            "Content-Type": "application/json"
        }
    );
};

/**
 * Deletes an object by its ID.
 *
 * @param id - Identifier of the object to delete.
 * @param token - Authentication token.
 * @returns A promise that resolves with a Response or an Error.
 */
export const DeleteObjet = async (id: number, token: string): Promise<Response | Error> => {
    return Request(
        `${baseUrl}/objets/${id}`,
        RequestType.DELETE,
        {
            "Content-Type": "application/json",
            "Authorization": token
        }
    );
};

/**
 * Adds a new object.
 *
 * @param objet - Object data transfer object containing object details.
 * @param token - Authentication token.
 * @returns A promise that resolves with a Response or an Error.
 */
export const AddObjet = async (objet: ObjetDTO, token: string): Promise<Response | Error> => {
    return Request(
        `${baseUrl}/objets`,
        RequestType.POST,
        {
            "Content-Type": "application/json",
            "Authorization": token
        },
        {
            nom: objet.nom,
            description: objet.description,
            categorie: objet.categorie
        }
    );
};

/**
 * Retrieves all exchanges.
 *
 * @returns A promise that resolves with a Response or an Error.
 */
export const GetEchanges = async (): Promise<Response | Error> => {
    return Request(
        `${baseUrl}/echanges/all`,
        RequestType.GET,
        {
            "Content-Type": "application/json"
        }
    );
};

/**
 * Retrieves a specific exchange by ID.
 *
 * @param id - Identifier of the exchange.
 * @param token - Authentication token.
 * @returns A promise that resolves with a Response or an Error.
 */
export const GetEchange = async (id: number, token: string): Promise<Response | Error> => {
    return Request(
        `${baseUrl}/echanges/${id}`,
        RequestType.GET,
        {
            "Content-Type": "application/json",
            "Authorization": token
        }
    );
};

/**
 * Updates the state of an exchange.
 *
 * @param idObjetPropose - Identifier of the object proposed in the exchange.
 * @param idObjetRecherche - Identifier of the object searched in the exchange.
 * @param token - Authentication token.
 * @returns A promise that resolves with a Response or an Error.
 */
export const CreateEchange = async (idObjetPropose: number, idObjetRecherche: number,
                                    token: string): Promise<Response | Error> => {
    return Request(
        `${baseUrl}/echanges/create`,
        RequestType.POST,
        {
            "Content-Type": "application/json",
            "Authorization": token
        },
        {
            "idObjetPropose": idObjetPropose,
            "idObjetRecherche": idObjetRecherche
        }
    );
}

/**
 * Updates the state of an exchange.
 *
 * @param id - Identifier of the exchange.
 * @param etat - New state of the exchange.
 * @param token - Authentication token.
 * @returns A promise that resolves with a Response or an Error.
 */
export const UpdateEchange = async (id: number, etat: EtatEchange, token: string): Promise<Response | Error> => {
    return Request(
        `${baseUrl}/echanges/update`,
        RequestType.PUT,
        {
            "Content-Type": "application/json",
            "Authorization": token
        },
        {
            "id": id,
            "etat": etat
        }
    );
}

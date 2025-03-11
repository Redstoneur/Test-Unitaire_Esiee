import Request from "../Function/Request";
import RequestType from "../Types/RequestType";
import ObjetDTO from "../Types/ObjetDTO";


/**
 * Class that contains methods to send requests to the API.
 */
namespace ApiRequest {

    /**
     * The base URL of the API.
     */
    const baseUrl = "http://localhost:3000/api";

    /**
     * Sends a login request with the provided username and password.
     *
     * @param {string} mail - The username of the user.
     * @param {string} mdp - The password of the user.
     * @returns {Promise<Response | Error>} A promise that resolves to the response or an error.
     */
    export async function Login(mail: string, mdp: string): Promise<Response | Error> {
        return Request(
            `${baseUrl}/credentials/login`,
            RequestType.POST,
            {
                "Content-Type": "application/json"
            },
            {mail, mdp}
        );
    }

    /**
     * Sends a registration request with the provided username and password.
     *
     * @param {string} pseudo - The username of the user.
     * @param {string} mdp - The password of the user.
     * @param {string} mail - The email of the user.
     * @param {string} nom - The last name of the user.
     * @param {string} prenom - The first name of the user.
     * @returns {Promise<Response | Error>} A promise that resolves to the response or an error.
     */
    export async function Register(pseudo: string, mdp: string, mail: string,
                                 nom: string, prenom: string): Promise<Response | Error> {
        return Request(
            `${baseUrl}/credentials/register`,
            RequestType.POST,
            {
                "Content-Type": "application/json"
            },
            {pseudo, mdp, mail, nom, prenom}
        );
    }

    /**
     * Verifies the provided token.
     *
     * @param {string} token - The token to verify.
     * @returns {Promise<Response | Error>} A promise that resolves to the response or an error.
     */
    export async function VerifyToken(token: string): Promise<Response | Error> {
        return Request(
            `${baseUrl}/credentials/verifyToken`,
            RequestType.POST,
            {
                "Content-Type": "application/json"
            },
            {token}
        );
    }

    /**
     * Verifies the provided token.
     *
     * @param {string} token - The token to verify.
     * @returns {Promise<boolean>} A promise that resolves to true if the token is valid, false otherwise.
     */
    export async function BooleanVerifyToken(token: string): Promise<boolean> {
        const response = await VerifyToken(token);

        if (response instanceof Error) {
            return false;
        }

        return response.status === 200;
    }

    /**
     * Retrieves user information using the provided token.
     *
     * @param {string} token - The authentication token of the user.
     * @returns {Promise<Response | Error>} A promise that resolves to the response or an error.
     */
    export async function UserInformation(token: string): Promise<Response | Error> {
        return Request(
            `${baseUrl}/utilisateurs/trans`,
            RequestType.GET,
            {
                "Content-Type": "application/json",
                "Authorization": token
            }
        );
    }

    /**
     * Retrieves a list of objects.
     *
     * @returns {Promise<Response | Error>} A promise that resolves to the response or an error.
     */
    export async function GetObjets(): Promise<Response | Error> {
        return Request(
            `${baseUrl}/objets`,
            RequestType.GET,
            {
                "Content-Type": "application/json"
            }
        );
    }

    /**
     * Deletes an object with the specified ID.
     *
     * @param {number} id - The ID of the object to delete.
     * @param {string} token - The authentication token of the user.
     * @returns {Promise<Response | Error>} A promise that resolves to the response or an error.
     */
    export async function DeleteObjet(id: number, token: string): Promise<Response | Error> {
        return Request(
            `${baseUrl}/objets/${id}`,
            RequestType.DELETE,
            {
                "Content-Type": "application/json",
                "Authorization": token
            }
        );
    }

    /**
     * Adds a new object.
     *
     * @param {ObjetDTO} objet - The object to add.
     * @param {string} token - The authentication token of the user.
     * @returns {Promise<Response | Error>} A promise that resolves to the response or an error.
     */
    export async function AddObjet(objet: ObjetDTO, token: string): Promise<Response | Error> {
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
    }

    /**
     * Retrieves a list of exchanges.
     *
     * @returns {Promise<Response | Error>} A promise that resolves to the response or an error.
     */
    export async function GetEchanges(): Promise<Response | Error> {
        return Request(
            `${baseUrl}/echanges/all`,
            RequestType.GET,
            {
                "Content-Type": "application/json"
            }
        );
    }

    /**
     * Retrieves an exchange with the specified ID.
     *
     * @param {number} id - The ID of the exchange to retrieve.
     * @param {string} token - The authentication token of the user.
     * @returns {Promise<Response | Error>} A promise that resolves to the response or an error.
     */
    export async function GetEchange(id: number, token: string): Promise<Response | Error> {
        return Request(
            `${baseUrl}/echanges/${id}`,
            RequestType.GET,
            {
                "Content-Type": "application/json",
                "Authorization": token
            }
        );
    }
}

export default ApiRequest;

import Request from "../Function/Request";
import RequestType from "../Types/RequestType";
import ObjetDTO from "../Types/ObjetDTO";


/**
 * Class representing API requests.
 */
class ApiRequest {

    /**
     * The base URL of the API.
     */
    private static readonly baseUrl = "http://localhost:3000/api";

    /**
     * Sends a login request with the provided username and password.
     *
     * @param {string} mail - The username of the user.
     * @param {string} mdp - The password of the user.
     * @returns {Promise<Response | Error>} A promise that resolves to the response or an error.
     */
    public static async Login(mail: string, mdp: string): Promise<Response | Error> {
        return Request(
            `${this.baseUrl}/credentials/login`,
            RequestType.POST,
            {
                "Content-Type": "application/json"
            },
            JSON.stringify({mail, mdp}));
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
    public static async Register(pseudo: string, mdp: string, mail: string,
                                 nom: string, prenom: string): Promise<Response | Error> {
        return Request(
            `${this.baseUrl}/credentials/register`,
            RequestType.POST,
            {
                "Content-Type": "application/json"
            },
            JSON.stringify({pseudo, mdp, mail, nom, prenom})
        );
    }

    /**
     * Verifies the provided token.
     *
     * @param {string} token - The token to verify.
     * @returns {Promise<Response | Error>} A promise that resolves to the response or an error.
     */
    public static async VerifyToken(token: string): Promise<Response | Error> {
        return Request(
            `${this.baseUrl}/credentials/verifyToken`,
            RequestType.POST,
            {
                "Content-Type": "application/json"
            },
            JSON.stringify({token})
        );
    }

    /**
     * Verifies the provided token.
     *
     * @param {string} token - The token to verify.
     * @returns {Promise<boolean>} A promise that resolves to true if the token is valid, false otherwise.
     */
    public static async BooleanVerifyToken(token: string): Promise<boolean> {
        const response = await this.VerifyToken(token);

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
    public static async UserInformation(token: string): Promise<Response | Error> {
        return Request(
            `${this.baseUrl}/utilisateurs/trans`,
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
    public static async GetObjets(): Promise<Response | Error> {
        return Request(
            `${this.baseUrl}/objets`,
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
     * @returns {Promise<Response | Error>} A promise that resolves to the response or an error.
     */
    public static async DeleteObjet(id: number): Promise<Response | Error> {
        return Request(
            `${this.baseUrl}/objets/${id}`,
            RequestType.DELETE,
            {
                "Content-Type": "application/json"
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
    public static async AddObjet(objet: ObjetDTO, token: string): Promise<Response | Error> {
        return Request(
            `${this.baseUrl}/objets`,
            RequestType.POST,
            {
                "Content-Type": "application/json",
                "Authorization": token
            },
            JSON.stringify(objet)
        );
    }

    /**
     * Retrieves a list of exchanges.
     *
     * @returns {Promise<Response | Error>} A promise that resolves to the response or an error.
     */
    public static async GetEchanges(): Promise<Response | Error> {
        return Request(
            `${this.baseUrl}/echanges/all`,
            RequestType.GET,
            {
                "Content-Type": "application/json"
            }
        );
    }
}

export default ApiRequest;

import Request from "../Function/Request";
import RequestType from "../Types/RequestType";
import Objet from "../Types/Objet";

class ApiRequest {
    private static readonly baseUrl = "http://localhost:3000/api";

    /**
     * Sends a login request with the provided username and password.
     *
     * @param {string} username - The username of the user.
     * @param {string} password - The password of the user.
     * @returns {Promise<Response | Error>} A promise that resolves to the response or an error.
     */
    public static async Login(username: string, password: string): Promise<Response | Error> {
        return Request(
            `${this.baseUrl}/credentials/login`,
            RequestType.POST,
            {
                "Content-Type": "application/json"
            },
            JSON.stringify({username, password}));
    }

    /**
     * Sends a registration request with the provided username and password.
     *
     * @param {string} username - The username of the user.
     * @param {string} password - The password of the user.
     * @returns {Promise<Response | Error>} A promise that resolves to the response or an error.
     */
    public static async Register(username: string, password: string): Promise<Response | Error> {
        return Request(
            `${this.baseUrl}/credentials/register`,
            RequestType.POST,
            {
                "Content-Type": "application/json"
            },
            JSON.stringify({username, password}));
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
                "Authorization": `Bearer ${token}`
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
     * @param {Objet} objet - The object to add.
     * @returns {Promise<Response | Error>} A promise that resolves to the response or an error.
     */
    public static async AddObjet(objet: Objet): Promise<Response | Error> {
        return Request(
            `${this.baseUrl}/objets`,
            RequestType.POST,
            {
                "Content-Type": "application/json"
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

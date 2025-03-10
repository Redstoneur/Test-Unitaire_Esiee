import Request from "../Function/Request";
import RequestType from "../Types/RequestType";
import Objet from "../Types/Objet";

class ApiRequest {
    private static readonly baseUrl = "http://localhost:5000/api";

    public static async Login(username: string, password: string): Promise<Response | Error> {
        return Request(
            `${this.baseUrl}/credentials/login`,
            RequestType.POST,
            {
                "Content-Type": "application/json"
            },
            JSON.stringify({username, password}));
    }

    public static async Register(username: string, password: string): Promise<Response | Error> {
        return Request(
            `${this.baseUrl}/credentials/register`,
            RequestType.POST,
            {
                "Content-Type": "application/json"
            },
            JSON.stringify({username, password}));
    }

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

    public static async GetObjets(): Promise<Response | Error> {
        return Request(
            `${this.baseUrl}/objets`,
            RequestType.GET,
            {
                "Content-Type": "application/json"
            }
        );
    }

    public static async DeleteObjet(id: number): Promise<Response | Error> {
        return Request(
            `${this.baseUrl}/objets/${id}`,
            RequestType.DELETE,
            {
                "Content-Type": "application/json"
            }
        );
    }

    public static async AddObjet(objet: Objet): Promise<Response | Error> {
        return Request(
            `${this.baseUrl}/objets`,
            RequestType.POST,
            {
                "Content-Type": "application/json"
            },
            JSON.stringify(objet));
    }

    public static async  GetEchanges(): Promise<Response | Error> {
        return Request(
            `${this.baseUrl}/echanges/all`,
            RequestType.GET,
            {
                "Content-Type": "application/json"
            }
        );
    }
}
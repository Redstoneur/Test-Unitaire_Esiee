import Request from "../Function/Request";
import RequestType from "../Types/RequestType";
import ObjetDTO from "../Types/ObjetDTO";

const baseUrl = "http://localhost:3000/api";

export const Login = async (mail: string, mdp: string): Promise<Response | Error> => {
    return Request(
        `${baseUrl}/credentials/login`,
        RequestType.POST,
        {
            "Content-Type": "application/json"
        },
        { mail, mdp }
    );
};

export const Register = async (pseudo: string, mdp: string, mail: string, nom: string, prenom: string): Promise<Response | Error> => {
    return Request(
        `${baseUrl}/credentials/register`,
        RequestType.POST,
        {
            "Content-Type": "application/json"
        },
        { pseudo, mdp, mail, nom, prenom }
    );
};

export const VerifyToken = async (token: string): Promise<Response | Error> => {
    return Request(
        `${baseUrl}/credentials/verifyToken`,
        RequestType.POST,
        {
            "Content-Type": "application/json"
        },
        { token }
    );
};

export const BooleanVerifyToken = async (token: string): Promise<boolean> => {
    const response = await VerifyToken(token);

    if (response instanceof Error) {
        return false;
    }

    return response.status === 200;
};

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

export const GetObjets = async (): Promise<Response | Error> => {
    return Request(
        `${baseUrl}/objets`,
        RequestType.GET,
        {
            "Content-Type": "application/json"
        }
    );
};

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

export const GetEchanges = async (): Promise<Response | Error> => {
    return Request(
        `${baseUrl}/echanges/all`,
        RequestType.GET,
        {
            "Content-Type": "application/json"
        }
    );
};

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
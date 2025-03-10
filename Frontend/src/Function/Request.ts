import RequestType from "../Types/RequestType";

const Request = async (url: string, method: RequestType, headers?: any, body?: any):Promise<Response | Error> => {

    if (headers === undefined) {
        headers = {"Content-Type": "application/json"};
    } else if (headers["Content-Type"] === undefined) {
        headers["Content-Type"] = "application/json";
    }

    if (body === undefined) {
        body = "{}";
    }

    try {
        return await fetch(url, {
            method: method,
            headers: headers,
            body: body
        });
    } catch (error) {
        console.error(error);
        return error as Error;
    }
}

export default Request;
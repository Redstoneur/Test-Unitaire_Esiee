import RequestType from "../Types/RequestType";

/**
 * Sends an HTTP request to the specified URL with the given method, headers, and body.
 *
 * @param {string} url - The URL to send the request to.
 * @param {RequestType} method - The HTTP method to use for the request.
 * @param {any} [headers] - Optional headers to include in the request. Defaults to `{"Content-Type": "application/json"}` if not provided.
 * @param {any} [body] - Optional body to include in the request. Defaults to `"{}"` if not provided.
 * @returns {Promise<Response | Error>} A promise that resolves to the response or an error.
 */
const Request = async (url: string, method: RequestType, headers?: any, body?: any): Promise<Response | Error> => {

    if (headers === undefined) {
        headers = {"Content-Type": "application/json"};
    } else if (headers["Content-Type"] === undefined) {
        headers["Content-Type"] = "application/json";
    }

    try {
        if (body === undefined)
            return await fetch(url, {
                method: method,
                headers: headers
            });
        else
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

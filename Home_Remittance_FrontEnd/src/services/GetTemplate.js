import axios from "axios";
import { buildApiUrl } from "../utilities/apiUrl";

export const handleGetRequest = async (url, port = 8081) => {
    const link = buildApiUrl(url, port);
    try {
        const response = await axios.get(link, {
            headers: {
                Authorization: localStorage.getItem("token"),
            },
        });
        return response.data;
    } catch (error) {
        // Always return API body shape so callers can do res?.data || []
        return error?.response?.data || { responsecode: 0, data: null, messages: "Request failed" };
    }
};

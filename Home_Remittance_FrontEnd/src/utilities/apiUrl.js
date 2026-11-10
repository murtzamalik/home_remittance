import { baseURL } from "../Config";

/** Build absolute API URL; strips leading slashes to avoid http://host//path */
export function buildApiUrl(url, port = 8080) {
    const path = String(url || "").replace(/^\/+/, "");
    const base = String(baseURL || "").replace(/\/+$/, "");
    if (process.env.NODE_ENV === "development") {
        return `${base}${port}/${path}`;
    }
    return `${base}/${path}`;
}

/** Safe list payload for Dropdown/MultiSelect options */
export function listData(res) {
    return Array.isArray(res?.data) ? res.data : [];
}

import { buildApiUrl } from "../utilities/apiUrl";
import axios from "axios";
import { toast } from "react-toastify";
import CryptoJS from "crypto-js";

export const login = async (data, url, port = 8080) => {
    // Encrypt
    let ciphertext = CryptoJS.AES.encrypt(JSON.stringify(data), "my-secret-key@123").toString();
    console.log("Encrypt", ciphertext);

    // Decrypt
    let bytes = CryptoJS.AES.decrypt(ciphertext, "my-secret-key@123");
    let decryptedData = JSON.parse(bytes.toString(CryptoJS.enc.Utf8));
    console.log("Decrypt", decryptedData);

    const id = toast.loading("Please wait...");

    const link = buildApiUrl(url, port);
    try {
        const response = await axios({
            method: "post",
            url: link,
            data: data,
            headers: {
                "Content-Type": "application/json",
                // Authorization:
                //     "Bearer oBMLUgzWWOl5xl4K0KZRZi27ePrguIyN0AyiAFCwCe/gccJEEZfmWqDn3IvuRY+tb6tXVuKiFexv2U3+hCOqxbESqS3lzjBo/r1Pz99IFw6HEiZ4Gq2AJ2TZyyvMQXZ4pTIckGUzpx+xCeiH5ZhafOop4Qit9DTYeHn+L7H9ehf2yZvaImSCY6wdqRgG3UVNIdwQZn1g41SJ/9ujJU5CPPJL0eRFE80tQCV7qpf5lLZQb9/IYeMrr6L+Y3WcgqrGa72+mmdOb0Autrdcna3sMTe7NjW1ypIGhyanjaYCHeH0Pbz33yB52tppl/eDpvt4bwIZ0src8CbP1Q+kz2iFbaD7p8CuGaDtSEn0SRZYhIHMDelDzgSA1pHFt1/w67hI809oQfbAKkdwfRI31EIi/kRRmg7SIymdMHpkIcmyJsxPnmLaEyttICgoSRyb3iDiAw8w+DGvRDfzEDvA9XLBsw==",
            },
        });
        toast.update(id, { render: response.data.messages, type: "success", isLoading: false, autoClose: 3000 });
        return response.data;
    } catch (error) {
        if (error.response.status === 500) toast.update(id, { render: error.response.data.messages || "Something went wrong !!", type: "error", isLoading: false, autoClose: 3000 });
        else if (error.response.status === 400) toast.update(id, { render: error?.response?.data?.[0]?.toastError || "Something went wrong !!", type: "error", isLoading: false, autoClose: 3000 });
        else toast.update(id, { render: error.response.data.messages || "Something went wrong !!", type: "warn", isLoading: false, autoClose: 3000 });

        return error.response;
    }
};

export const refreshToken = async (data) => {
    let res;
    await axios({
        method: "get",
        url: buildApiUrl("js-hr-login/refreshToken", 8080),
        data: data,
        headers: {
            Authorization: localStorage.getItem("token"),
            "Content-Type": "application/json",
        },
    })
        .then((response) => {
            localStorage.setItem("token", response.data.data);
        })
        .catch((err) => {
            res = false;
            console.err(err);
        });
    return res;
};

export const updatePassword = async (password) => {
    let res;
    await axios({
        method: "post",
        url: buildApiUrl("js_hr_um/userManagement/changePassword", 8083),
        data: { password },
        headers: {
            "Content-Type": "application/json",
            Authorization: localStorage.getItem("token"),
        },
    })
        .then((response) => {
            toast.success(response.data.data);
            res = response?.data?.data;
        })
        .catch((err) => {
            toast.warn(err?.response?.data?.messages || "Something went wrong");
            res = false;
        });
    return res;
};

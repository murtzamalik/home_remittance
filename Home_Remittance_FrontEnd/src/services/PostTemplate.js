import { buildApiUrl } from "../utilities/apiUrl";
import axios from "axios";
import { toast } from "react-toastify";
import { trimData } from "../utilities/TrimPayloadData";
import { loadingAction } from "../redux/actions/loadingAction";

export const handlePostRequest =
    (data, url, isShowLoad = false, isShowToast = true, port = 8081) =>
    async (dispatch) => {
        data = await trimData(data);
        const link = buildApiUrl(url, port);
        try {
            if (isShowLoad) dispatch(loadingAction(true));
            const response = await axios({
                method: "post",
                url: link,
                data: data,
                headers: {
                    "Content-Type": "application/json",
                    Authorization: localStorage.getItem("token"),
                },
            });
            if (isShowToast) toast.success(response?.data?.messages);
            if (isShowLoad) dispatch(loadingAction(false));
            return response?.data;
        } catch (error) {
            if (isShowLoad) dispatch(loadingAction(false));
            if (error?.response?.status === 400) toast.warn(error?.response?.data?.[0]?.toastError || "Something went wrong !!");
            else toast.warn(error?.response?.data?.messages || "Something went wrong !!");
            return error?.response;
        }
    };

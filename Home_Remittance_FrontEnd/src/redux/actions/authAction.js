import { login } from "../../services/Auth";
import { LOGIN_SUCCESS, LOGIN_ERROR } from "../slices/authenticationSlice";
export const loginAction = (authData, url) => async (dispatch) => {
    const res = await login(authData, url);
    if (res?.data?.login === true) {
        localStorage.setItem("token", res?.data?.token);
        localStorage.setItem("nav", JSON.stringify(res?.data?.menu));
        localStorage.setItem("login", true);
        localStorage.setItem("userinfo", JSON.stringify(res?.data?.userResponse));
        dispatch(LOGIN_SUCCESS(res?.data));
        return res;
    } else {
        dispatch(LOGIN_ERROR("Error"));
        return res;
    }
};

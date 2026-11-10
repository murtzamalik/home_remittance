import "react-app-polyfill/ie11";
import React from "react";
import ReactDOM from "react-dom";
import App from "./App";
//import * as serviceWorker from './serviceWorker';
import { HashRouter } from "react-router-dom";
import ScrollToTop from "./ScrollToTop";
import { Provider } from "react-redux";
import store from "./redux/store";
import "./i18n";
import axios from "axios";
import { forceClientLogout, isAuthenticated } from "./utilities/session";

axios.interceptors.response.use(
    (response) => response,
    (error) => {
        const status = error?.response?.status;
        const url = error?.config?.url || "";
        const isLoginCall = url.includes("js-hr-login/accountLogin") || url.includes("js-hr-login/login");
        if (status === 401 && !isLoginCall && isAuthenticated()) {
            forceClientLogout();
        }
        return Promise.reject(error);
    }
);

ReactDOM.render(
    <HashRouter>
        <ScrollToTop>
            <Provider store={store}>
                <App></App>
            </Provider>
        </ScrollToTop>
    </HashRouter>,
    document.getElementById("root")
);

// If you want your app to work offline and load faster, you can change
// unregister() to register() below. Note this comes with some pitfalls.
// Learn more about service workers: http://bit.ly/CRA-PWA
//serviceWorker.unregister();

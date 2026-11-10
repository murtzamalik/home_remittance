import React from "react";
import { Link } from "react-router-dom";
import classNames from "classnames";
import { handlePostRequest } from "./services/PostTemplate";
import { useDispatch } from "react-redux";
import { forceClientLogout } from "./utilities/session";

export const AppTopbar = (props) => {
    const dispatch = useDispatch();

    const handleLogout = async () => {
        try {
            await dispatch(handlePostRequest({}, "js-hr-login/logout", true, false, 8080));
        } catch (e) {
            // Always clear local session even if API fails
        } finally {
            forceClientLogout();
        }
    };

    return (
        <div className="layout-topbar">
            <Link to="/dashboard" className="layout-topbar-logo">
                <img src="assets/layout/images/zindigi-logo.webp" alt="Zindigi" />
            </Link>

            <button type="button" className="p-link layout-menu-button layout-topbar-button" onClick={props.onToggleMenuClick}>
                <i className="pi pi-bars" />
            </button>

            <button type="button" className="p-link layout-topbar-menu-button layout-topbar-button" onClick={props.onMobileTopbarMenuClick}>
                <i className="pi pi-ellipsis-v" />
            </button>

            <ul className={classNames("layout-topbar-menu lg:flex origin-top", { "layout-topbar-menu-mobile-active": props.mobileTopbarMenuActive })}>
                <li>
                    <button type="button" className="p-link layout-topbar-button layout-topbar-logout" onClick={handleLogout} title="Logout">
                        <i className="pi pi-sign-out" />
                        <span>Logout</span>
                    </button>
                </li>
            </ul>
        </div>
    );
};

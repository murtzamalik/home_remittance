import React from "react";
import { Avatar } from "primereact/avatar";
import { useDispatch } from "react-redux";
import { handlePostRequest } from "./services/PostTemplate";
import { forceClientLogout } from "./utilities/session";

export const AppProfile = () => {
    const dispatch = useDispatch();
    const profile = JSON.parse(localStorage.getItem("userinfo") || "null");

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
        <div className="layout-profile">
            <Avatar icon="pi pi-user" className="mr-2 mb-2" size="xlarge" shape="circle" style={{ backgroundColor: "#2196F3", color: "#ffffff" }} />

            <div className="user-text">
                <p>
                    <strong>
                        Hello, <span>{profile?.ldapName ? profile?.ldapName : "User"}</span>
                    </strong>
                </p>
                <p>
                    <strong> Employess ID:</strong> <span>{profile?.employeeNo ? profile?.employeeNo : "Employee Id"}</span>
                </p>

                <p>
                    <strong>Email: </strong> <span>{profile?.ldapEmail ? profile?.ldapEmail : "Email"}</span>
                </p>
                <p>
                    <strong>
                        <span>{profile?.ldapName ? profile?.ldapName : "Title"}</span>{" "}
                    </strong>
                </p>
            </div>

            <button type="button" className="p-link layout-profile-logout" onClick={handleLogout}>
                <i className="pi pi-sign-out" />
                <span>Logout</span>
            </button>
        </div>
    );
};

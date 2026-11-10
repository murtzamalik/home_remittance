export const clearClientSession = () => {
    localStorage.removeItem("login");
    localStorage.removeItem("token");
    localStorage.removeItem("nav");
    localStorage.removeItem("userinfo");
    localStorage.removeItem("loggedIn");
};

export const isAuthenticated = () => localStorage.getItem("login") === "true" && !!localStorage.getItem("token");

export const forceClientLogout = () => {
    clearClientSession();
    window.location.hash = "/";
    window.location.reload();
};

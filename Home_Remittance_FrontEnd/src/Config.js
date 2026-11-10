let baseURL = process.env.NODE_ENV === "development" ? process.env.REACT_APP_BSE_URL_DEV : process.env.REACT_APP_BASE_URL_LIVE;

export { baseURL };

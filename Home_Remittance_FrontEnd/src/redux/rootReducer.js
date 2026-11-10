import { combineReducers } from "redux";
import authenticationSlice from "./slices/authenticationSlice";
import utilitySlice from "./slices/utilitySlice";
import ammendmentTransactionSlice from "./slices/ammendmentTransactionSlice";
import parserSlice from "./slices/parserSlice";

export default combineReducers({
    authenticationSlice,
    utilitySlice,
    ammendmentTransactionSlice,
    parserSlice,
});

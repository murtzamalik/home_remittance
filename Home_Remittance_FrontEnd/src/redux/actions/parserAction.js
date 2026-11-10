import {
    SETPARSEREDITFLAG_SUCCESS,
    GETPARSERHEADDETAILS_SUCCESS,
    GETPARSERHEADDETAILS_ERROR,
    SETPARSERHEADDETAILSEDITFLAG_SUCCESS,
    GETPARSERHEADFURTHERDETAILS_SUCCESS,
    GETPARSERHEADFURTHERDETAILS_ERROR,
    SETPARSERHEADFURTHERDETAILS_NULL_SUCCESS,
    SETPARSERHEADFURTHERDETAILS_SUCCESS,
    SETPARSERHEADFURTHERDETAILSDATA_SUCCESS,
} from "../slices/parserSlice";
import { handleGetRequest } from "../../services/GetTemplate";

// Edit parser head
export const SetEditParserFlagAction = (data) => async (dispatch) => {
    dispatch(SETPARSEREDITFLAG_SUCCESS(data));
};

export const SetParserHeadFurtherDetailsAction = (data) => async (dispatch) => {
    dispatch(SETPARSERHEADFURTHERDETAILS_SUCCESS(data));
};
// Edit parser head Details
export const SetEditParserHeadDetailsFlagAction = (data) => async (dispatch) => {
    dispatch(SETPARSERHEADDETAILSEDITFLAG_SUCCESS(data));
};
//parser details
export const GetParserHeadDetailsAction = (url) => async (dispatch) => {
    const res = await handleGetRequest(url, 8082);
    if (res?.responsecode === 1) {
        dispatch(GETPARSERHEADDETAILS_SUCCESS(res?.data));
        return res?.responsecode;
    } else {
        dispatch(GETPARSERHEADDETAILS_ERROR("Error"));
        return res?.responsecode;
    }
};
//PARSER HEAD DETAILS ROW
export const SetParserHeadExtremeFurtherDetailsAction = (data) => async (dispatch) => {
    dispatch(SETPARSERHEADFURTHERDETAILSDATA_SUCCESS(data));
};

//Set parser head further details null Success
export const SetParserHeadDetailsToNullAction = () => async (dispatch) => {
    dispatch(SETPARSERHEADFURTHERDETAILS_NULL_SUCCESS(null));
};
export const GetParserHeadFurtherDetailsAction = (url) => async (dispatch) => {
    const res = await handleGetRequest(url, 8082);
    if (res?.responsecode === 1) {
        dispatch(GETPARSERHEADFURTHERDETAILS_SUCCESS(res?.data));
        return res?.responsecode;
    } else {
        dispatch(GETPARSERHEADFURTHERDETAILS_ERROR("Error"));
        return res?.responsecode;
    }
};

import { createSlice } from "@reduxjs/toolkit";

const slice = createSlice({
    name: "branchSlice",
    initialState: {
        parserHeadEditFlag: false,
        parseHeadDetails: [],
        parseHeadFurtherDetails: [],
        parseHeadId: "",
        parserHeadDetailsForm: "",
        parserHeadDetailsEditFlag: false,
        parserDetailId: "",
        parserHeadDetailsFormData: "",
    },
    reducers: {
        SETPARSEREDITFLAG_SUCCESS: (state, action) => {
            return {
                ...state,
                parserHeadEditFlag: action.payload,
            };
        },
        GETPARSERHEADDETAILS_SUCCESS: (state, action) => {
            return {
                ...state,
                parseHeadDetails: action.payload,
            };
        },
        GETPARSERHEADDETAILS_ERROR: (state) => {
            return {
                ...state,
                parseHeadDetails: "",
            };
        },
        SETPARSERHEADDETAILSEDITFLAG_SUCCESS: (state, action) => {
            return {
                ...state,
                parserHeadDetailsEditFlag: action.payload,
            };
        },
        GETPARSERHEADFURTHERDETAILS_SUCCESS: (state, action) => {
            return {
                ...state,
                parseHeadFurtherDetails: action.payload,
            };
        },
        SETPARSERHEADFURTHERDETAILSDATA_SUCCESS: (state, action) => {
            return {
                ...state,
                parserDetailId: action.payload?.parserDetailId,
                parserHeadDetailsFormData: action.payload,
                parserHeadDetailsEditFlag: true,
            };
        },
        GETPARSERHEADFURTHERDETAILS_ERROR: (state) => {
            return {
                ...state,
                parseHeadFurtherDetails: "",
            };
        },
        SETPARSERHEADFURTHERDETAILS_NULL_SUCCESS: (state, action) => {
            return {
                ...state,
                parseHeadFurtherDetails: [],
            };
        },
        SETPARSERHEADFURTHERDETAILS_SUCCESS: (state, action) => {
            return {
                ...state,
                parserHeadDetailsForm: action?.payload,
                parseHeadFurtherDetails: action.payload?.tblParserDetails,
                parseHeadId: action.payload?.parserHeadId,
                parserHeadEditFlag: true,
                parserHeadDetailsEditFlag: false,
            };
        },
    },
});

export const {
    SETPARSEREDITFLAG_SUCCESS,
    GETPARSERHEADDETAILS_SUCCESS,
    SETPARSERHEADFURTHERDETAILS_SUCCESS,
    SETPARSERHEADFURTHERDETAILSDATA_SUCCESS,
    SETPARSERHEADDETAILSEDITFLAG_SUCCESS,
    GETPARSERHEADFURTHERDETAILS_ERROR,
    GETPARSERHEADFURTHERDETAILS_SUCCESS,
    GETPARSERHEADDETAILS_ERROR,
    SETPARSERHEADFURTHERDETAILS_NULL_SUCCESS,
} = slice.actions;
export default slice.reducer;

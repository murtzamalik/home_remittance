import { createSlice } from "@reduxjs/toolkit";

const slice = createSlice({
    name: "ammendmentTransactionSlice",
    initialState: {
        rowData: "",
        isEdit: false,
    },
    reducers: {
        SETROWDATA_SUCCESS: (state, action) => {
            return {
                ...state,
                rowData: action.payload,
                isEdit: true,
            };
        },
        RESETROWDATA_SUCCESS: (state) => {
            return {
                ...state,
                rowData: "",
                isEdit: false,
            };
        },
    },
});

export const { SETROWDATA_SUCCESS, RESETROWDATA_SUCCESS } = slice.actions;
export default slice.reducer;

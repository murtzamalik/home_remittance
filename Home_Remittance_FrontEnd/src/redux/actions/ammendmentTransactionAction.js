import { SETROWDATA_SUCCESS, RESETROWDATA_SUCCESS } from "../slices/ammendmentTransactionSlice";

export const SetRowDataAction = (rowData) => async (dispatch) => {
    dispatch(SETROWDATA_SUCCESS(rowData));
};

export const ResetRowDataAction = () => async (dispatch) => {
    dispatch(RESETROWDATA_SUCCESS());
};

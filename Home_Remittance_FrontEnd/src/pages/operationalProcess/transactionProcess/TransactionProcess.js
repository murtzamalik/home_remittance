import React, { useState } from "react";
import TransactionProcessFilter from "./TransactionProcessFilter";
import TransactionProcessData from "./TransactionProcessData";
import { useDispatch } from "react-redux";
import { handlePostRequest } from "../../../services/PostTemplate";

function TransactionProcess() {
    const [data, setdata] = useState([]);
    const [lastParams, setlastParams] = useState();
    const dispatch = useDispatch();

    const getData = async (data) => {
        const response = await dispatch(handlePostRequest(data || lastParams, "/js_hr_transactions/transaction/getTransactionsAgainstRemitCompanyForProcess", true, false, 8084));
        if (response?.data?.responsecode === 0) {
            setdata([]);
            setlastParams(data);
        } else {
            setdata(response?.data === null || response?.data === undefined ? [] : response?.data);
            setlastParams(data);
        }
    };

    return (
        <div>
            <TransactionProcessFilter getData={getData} />
            <TransactionProcessData data={data} getData={getData} setdata={setdata} />
        </div>
    );
}

export default TransactionProcess;

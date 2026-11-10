import React, { useEffect, useState } from "react";
import { handleGetRequest } from "../../services/GetTemplate";
import AmendmentTransactionData from "./amendmentTransactionData";
import AmendmentTransactionFormData from "./amendmentTransactionFormData";

const AmendmentTransaction = () => {
    const [beneficiaryData, setbeneficiaryData] = useState([]);
    const [loading, setloading] = useState(false);
    const getData = async () => {
        setloading(true);
        const response = await handleGetRequest("js_hr_transactions/transaction/getAllAmendmentsTransactions", 8084);
        if (response?.data?.responsecode === 0) {
            setbeneficiaryData([]);
        } else {
            setloading(false);
            setbeneficiaryData(response?.data === null || response?.data === undefined ? [] : response?.data);
        }
    };

    useEffect(() => {
        getData();
    }, []); // eslint-disable-line

    return (
        <div>
            <div className="fluid formgrid grid">
                <div className="field col-4 md:col-12">
                    <AmendmentTransactionData beneficiaryData={beneficiaryData} loading={loading} />
                </div>
                <div className="field col-8  md:col-12">
                    <AmendmentTransactionFormData getData={getData} />
                </div>
            </div>
        </div>
    );
};

export default AmendmentTransaction;

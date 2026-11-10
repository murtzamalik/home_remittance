import React, { useState } from "react";
import SingleTransactionData from "./singleTransactionData";
import SingleTransactionFormData from "./singleTransactionFormData";

const SingleTransaction = () => {
    const [singleTransactionData, setsingleTransactionData] = useState([]);
    const getSingleTransaction = (e) => {
        setsingleTransactionData(e);
    };

    return (
        <div>
            <div className="fluid formgrid grid">
                <div className="field col-8  md:col-12">
                    <SingleTransactionFormData getSingleTransaction={getSingleTransaction} />
                </div>
                <div className="field col-4 md:col-12">
                    <SingleTransactionData singleTransactionData={singleTransactionData} />
                </div>
            </div>
        </div>
    );
};

export default SingleTransaction;

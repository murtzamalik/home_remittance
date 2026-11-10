import React from "react";
import { DataTable } from "primereact/datatable";
import { Column } from "primereact/column";
const SingleTransactionData = ({ singleTransactionData }) => {
    return (
        <div>
            <div className="card Card__Round">
                <div className="datatable-sm">
                    <DataTable
                        header={"Transaction Data"}
                        paginator
                        rows={5}
                        value={singleTransactionData}
                        emptyMessage="No data found."
                        showGridlines
                        responsiveLayout="scroll"
                        dataKey="transDetailId"
                        paginatorTemplate="FirstPageLink PrevPageLink PageLinks NextPageLink LastPageLink CurrentPageReport RowsPerPageDropdown"
                        currentPageReportTemplate="Showing {first} to {last} of {totalRecords} Transactions"
                    >
                        <Column field="beneficiaryName" header="Name" />
                        <Column field="beneficiaryCnic" header="CNIC" />
                        <Column field="beneficiaryAccountNo" header="Account No" />
                        <Column field="beneficiaryAccountTitle" header="Acc Title" />
                        <Column field="beneficiaryBankName" header="Bank Name" />
                        <Column field="beneficiaryBranchAdd1" header="Bank Address" />
                        <Column field="beneficiaryOtherContact" header="Beneficiary Other Contact" />
                        <Column field="remitterName" header="Remitter Name" />
                        <Column field="remitterAddress" header="Remitter Address 1" />
                        <Column field="remitterCountry" header="Remitter Country" />
                        <Column field="beneficiaryBranchAdd2" header="Remitter Address 2" />
                        <Column field="transactionReference" header="Transaction Reference" />
                        <Column field="amount" header="Amount" />
                        <Column field="statusDescr" header="Status" />
                    </DataTable>
                </div>
            </div>
        </div>
    );
};

export default SingleTransactionData;

import React, { useState } from "react";
import { DataTable } from "primereact/datatable";
import { Column } from "primereact/column";
import { useDispatch } from "react-redux";
import { SetRowDataAction } from "../../../redux/actions/ammendmentTransactionAction";
const AmendmentTransactionData = ({ beneficiaryData, loading }) => {
    const [selectedRow, setselectedRow] = useState(false);

    const dispatch = useDispatch();

    const onRowSelect = (event) => {
        dispatch(SetRowDataAction(event.data));
    };

    return (
        <div>
            <div className="card Card__Round">
                <div className="Form__Header">
                    <h1>Amendment Transaction</h1>
                </div>
                <div className="datatable-sm">
                    <DataTable
                        header={"Amendment Data"}
                        onRowSelect={onRowSelect}
                        loading={loading}
                        paginator
                        rows={5}
                        value={beneficiaryData}
                        emptyMessage="No data found."
                        selectionMode="single"
                        selection={selectedRow}
                        onSelectionChange={(e) => setselectedRow(e.value)}
                        showGridlines
                        responsiveLayout="scroll"
                        dataKey="transDetailId"
                        paginatorTemplate="FirstPageLink PrevPageLink PageLinks NextPageLink LastPageLink CurrentPageReport RowsPerPageDropdown"
                        currentPageReportTemplate="Showing {first} to {last} of {totalRecords} Amendment Records"
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

export default AmendmentTransactionData;

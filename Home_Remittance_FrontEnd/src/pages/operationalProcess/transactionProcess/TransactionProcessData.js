import React, { useState } from "react";
import { DataTable } from "primereact/datatable";
import { Column } from "primereact/column";
import "../../../components/DataTable.css";
import { useSelector, useDispatch } from "react-redux";
import { InputText } from "primereact/inputtext";
import { Button } from "primereact/button";
import { handlePostRequest } from "../../../services/PostTemplate";
import { Dialog } from "primereact/dialog";
import { toast } from "react-toastify";

function TransactionProcessData({ data, getData }) {
    const [globalFilter, setGlobalFilter] = useState(null);
    const [expandedRows, setExpandedRows] = useState();
    const [selectedTransData, setSelectedTransData] = useState(null);
    const [displayDialog, setDisplayDialog] = useState(false);
    const [dialogText, setDialogText] = useState("");

    const dispatch = useDispatch();
    const isLoading = useSelector((state) => state?.utilitySlice?.isLoadingComponent);

    const header = (
        <div className="flex flex-column md:flex-row md:justify-content-between md:align-items-center">
            <h5 className="m-0">File Processing Data</h5>
            <span className="block mt-2 md:mt-0">
                <InputText type="search" onInput={(e) => setGlobalFilter(e.target.value)} placeholder="Search..." />
            </span>
        </div>
    );
    const renderFooter = () => {
        return (
            <div>
                <Button label="Ok" icon="pi pi-check" onClick={() => onHide()} autoFocus />
            </div>
        );
    };
    const onHide = (name) => {
        setDisplayDialog(false);
    };

    const rowExpansionTemplate = (data) => {
        return (
            <div className="expanded-card">
                <div className="fluid formgrid grid">
                    <div className="field col-6">
                        <div className="box">
                            <strong>Beneficiary Name : {data.beneficiaryBankName || "-"}</strong>
                        </div>
                        <div className="box">
                            <strong>Beneficiary Address 1 : {data.beneficiaryBranchAdd1 || "-"}</strong>
                        </div>
                        <div className="box">
                            <strong>Beneficiary Address 2 : {data.beneficiaryBranchAdd2 || "-"}</strong>
                        </div>
                        <div className="box">
                            <strong>Remitter Number : {data.remitterNumber || "-"}</strong>
                        </div>
                        <div className="box">
                            <strong>Transaction Reference : {data.transactionReference || "-"}</strong>
                        </div>
                    </div>
                    <div className="field col-6">
                        <div className="box">
                            <strong>Beneficiary Branch Code : {data.beneficiaryBranchCode || "-"}</strong>
                        </div>
                        <div className="box">
                            <strong>Beneficiary Other Contact : {data.beneficiaryOtherContact || "-"}</strong>
                        </div>
                        <div className="box">
                            <strong>Remitter Address : {data.remitterAddress || "-"}</strong>
                        </div>
                        <div className="box">
                            <strong>Transaction Type : {data.transType || "-"}</strong>
                        </div>
                    </div>
                </div>
            </div>
        );
    };

    const handleSubmit = async (type) => {
        const transDetailIds = selectedTransData.map((item) => item.transDetailId);
        let url = "";

        if (type === "ibft") {
            url = "js_hr_transactions/transaction/processTransactions";
        } else if (type === "a") {
            url = "js_hr_transactions/transaction/parkTransactionsForAmendments";
        } else if (type === "park") {
            url = "js_hr_transactions/transaction/parkTransactionsForBt";
        }
        const res = await dispatch(handlePostRequest({ transDetailIds }, url, true, false, 8084));
        if (res?.responsecode && res?.responsecode === 1) {
            console.log("If statement");
            toast.success(res?.messages);
            getData();
        }
        if (res?.responsecode && res?.responsecode === 1 && type === "ibft") {
            url = "js_hr_transactions/transaction/processTransactions";
            setDisplayDialog(true);
            setDialogText(res?.data);
            getData();
        }
    };

    return (
        <div className="mt-2">
            <Dialog className="Proccess__Transaction" header="Process Transaction Information" visible={displayDialog} footer={renderFooter("displayDialog")} onHide={() => setDisplayDialog(false)}>
                <table className="Dialog__Table">
                    <tr>
                        <th>Successful Transactions </th>
                        <th>Unsuccessful Inter Bank Transfer </th>
                        <th>Unsuccessful Inter Bank Fund Transfer </th>
                        <th>Total Transactions </th>
                    </tr>
                    <tr>
                        <td>{dialogText?.sucessfulTransactions}</td>
                        <td>{dialogText?.unsucessfullIFT}</td>
                        <td>{dialogText?.unSucessfullIBFT}</td>
                        <td>{dialogText?.totalTransactions}</td>
                    </tr>
                </table>
            </Dialog>
            <div className="card p-datatable-sm">
                <DataTable
                    header={header}
                    globalFilter={globalFilter}
                    filterDisplay="row"
                    loading={isLoading}
                    paginator
                    rows={12}
                    value={data}
                    emptyMessage="No data found."
                    expandedRows={expandedRows}
                    onRowToggle={(e) => setExpandedRows(e.data)}
                    rowExpansionTemplate={rowExpansionTemplate}
                    showGridlines
                    responsiveLayout="scroll"
                    selection={selectedTransData}
                    onSelectionChange={(e) => setSelectedTransData(e.value)}
                    dataKey="transDetailId"
                    paginatorTemplate="FirstPageLink PrevPageLink PageLinks NextPageLink LastPageLink CurrentPageReport RowsPerPageDropdown"
                    currentPageReportTemplate="Showing {first} to {last} of {totalRecords} inbox data"
                >
                    <Column selectionMode="multiple" headerStyle={{ width: "3em" }}></Column>
                    {/* <Column expander style={{ width: "2.5rem" }} filterMatchMode="contains"></Column> */}
                    <Column filter field="transactionReference" header="Transaction Reference" />
                    <Column filter field="beneficiaryName" header="Name" />
                    <Column filter field="beneficiaryCnic" header="CNIC" />
                    <Column filter field="beneficiaryAccountNo" header="Account No" />
                    <Column filter field="beneficiaryBankName" header="Bank Name" />
                    <Column filter field="beneficiaryAccountTitle" header="Acc Title" />
                    <Column filter field="amount" header="Amount" />
                    <Column filter field="remitterName" header="Remitter Name" />
                    <Column filter field="remitterCountry" header="Remitter Country" />
                    <Column filter field="remitterNumber" header="Remitter Number" />
                    <Column filter field="remitterAddress" header="Remitter Address" />
                    <Column filter field="t24TransactionDescr" header="Transaction Description" />
                    <Column filter field="t24TransactionReference" header="Transaction Refernce" />
                    <Column filter field="beneficiaryBranchAdd1" header="Branch Address1" />
                    <Column filter field="beneficiaryBranchAdd2" header="Branch Address2" />
                    <Column filter field="statusDescr" header="Status" />
                </DataTable>
            </div>
            <div className="Down__Btn mt-4">
                <Button label="Process Transaction" onClick={() => handleSubmit("ibft")} className="Btn__Dark" />
                <Button type="submit" iconPos="right" onClick={() => handleSubmit("a")} label="Amendments" className="Btn__Dark" />
                <Button label="Mark Bt Transactions" className="Btn__Dark" onClick={() => handleSubmit("park")} />
            </div>
        </div>
    );
}

export default TransactionProcessData;

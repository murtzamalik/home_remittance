import React, { useState, useEffect } from "react";
import { DataTable } from "primereact/datatable";
import { Column } from "primereact/column";
import "../../components/DataTable.css";
import { useSelector, useDispatch } from "react-redux";
import { InputText } from "primereact/inputtext";
import { Button } from "primereact/button";
import { handlePostRequest } from "../../services/PostTemplate";
import { Dialog } from "primereact/dialog";
import { toast } from "react-toastify";

function ViewStatus({ data, getData }) {
    const [globalFilter, setGlobalFilter] = useState(null);
    const [selectedTransData, setSelectedTransData] = useState(null);
    const [displayDialog, setDisplayDialog] = useState(false);
    const [dialogText, setDialogText] = useState("");
    const [StatusData, setStatusData] = useState([]);

    const dispatch = useDispatch();
    const isLoading = useSelector((state) => state?.utilitySlice?.isLoadingComponent);

    //Getting info from URL
    const url = require("url");
    const urlObj = url.parse(document.location.href, true);
    const statusCode = urlObj?.query?.statuscode;
    const transHeadId = urlObj?.query?.transid;

    //Converting the JSON Data of table to Excel Sheet
    const exportExcel = () => {
        import("xlsx").then((xlsx) => {
            const worksheet = xlsx.utils.json_to_sheet(StatusData);
            const workbook = { Sheets: { data: worksheet }, SheetNames: ["data"] };
            const excelBuffer = xlsx.write(workbook, { bookType: "xlsx", type: "array" });
            saveAsExcelFile(excelBuffer, "Transaction Data");
        });
    };

    //For Downloading the excel file from Sheet
    const saveAsExcelFile = (buffer, fileName) => {
        import("file-saver").then((module) => {
            if (module && module.default) {
                let EXCEL_TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=UTF-8";
                let EXCEL_EXTENSION = ".xlsx";
                const data = new Blob([buffer], {
                    type: EXCEL_TYPE,
                });

                module.default.saveAs(data, fileName + "_export_" + EXCEL_EXTENSION);
            }
        });
    };

    const header = (
        <div className="flex flex-column md:flex-row md:justify-content-between md:align-items-center">
            <h5 className="m-0">Transactions</h5>

            <span className="block mt-2 md:mt-0">
                <InputText style={{ padding: "20px" }} type="search" onInput={(e) => setGlobalFilter(e.target.value)} placeholder="Search..." />
            </span>
            {StatusData?.length > 0 ? (
                <div className="flex align-items-center export-buttons">
                    <Button style={{ display: "block" }} label="Download Excel Report" type="button" icon="pi pi-file-excel" onClick={exportExcel} className=" mr-2 btn-dark" data-pr-tooltip="XLS" />
                </div>
            ) : (
                ""
            )}
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

    const getStatusTransactionByID = async () => {
        const statusData = {
            statusCode: statusCode,
            transHeadId: transHeadId,
        };
        console.log(statusData);
        const res = await dispatch(handlePostRequest(statusData, "/js_hr_transactions/transaction/transactionsInquiryStatusWise", true, true, 8084));
        if (res?.responsecode === 1) {
            if (res?.data) {
                setStatusData(res?.data);
                console.log(res);
            }
        }
    };

    useEffect(() => {
        getStatusTransactionByID();
    }, []);

    return (
        <div className="mt-2">
            <div className="card Card__Round">
                <div className="Form__Header">
                    <h1>Transactions</h1>
                </div>
            </div>
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
                    value={StatusData || []}
                    emptyMessage="No data found."
                    showGridlines
                    responsiveLayout="scroll"
                    selection={selectedTransData}
                    onSelectionChange={(e) => setSelectedTransData(e.value)}
                    dataKey="transDetailId"
                    paginatorTemplate="FirstPageLink PrevPageLink PageLinks NextPageLink LastPageLink CurrentPageReport RowsPerPageDropdown"
                    currentPageReportTemplate="Showing {first} to {last} of {totalRecords} inbox data"
                    rowsPerPageOptions={[5, 10, 25]}
                >
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
                    {/* <Column filter field="statusDescr" header="Status" /> */}
                </DataTable>
            </div>
        </div>
    );
}

export default ViewStatus;

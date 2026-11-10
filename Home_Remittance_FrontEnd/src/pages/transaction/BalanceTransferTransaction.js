import React, { useEffect, useState } from "react";
import { InputText } from "primereact/inputtext";
import { Button } from "primereact/button";
import { useFormik } from "formik";
import classNames from "classnames";
import * as Yup from "yup";
import { handleGetRequest } from "../../services/GetTemplate";
import { MultiSelect } from "primereact/multiselect";
import { useDispatch } from "react-redux";
import { handlePostRequest } from "../../services/PostTemplate";
import { DataTable } from "primereact/datatable";
import { Column } from "primereact/column";

function BankTransferTransaction() {
    const [loadingIcon, setloadingIcon] = useState("pi pi-search");
    const [transactionInquiry, settransactionInquiry] = useState([]);
    const [remitterCompany, setremitterCompany] = useState([]);
    const [balancetransfer, setbalancetransfer] = useState([]);
    const [selectedRow, setselectedRow] = useState([]);
    const [selectedBt, setSelectedBt] = useState("");

    const dispatch = useDispatch();

    const cancelHandler = (e) => {
        e.preventDefault();
        formik.resetForm();
    };
    const submitHandler = async (e, type) => {
        e.preventDefault();
        let Url = type === "generate" ? "" : "";
        if (type === "generate") {
            Url = "js_hr_transactions/transaction/generateMT102FileForBT";
        }

        const res = await dispatch(handlePostRequest({ transDetailIds: selectedRow.map((item) => item?.transDetialId) }, Url, true, true, 8084));
        console.log("reponse outer", res);
        const response1 = await dispatch(handlePostRequest(selectedBt, "js_hr_transactions/transaction/getAllBtTransactions", true, true, 8084));
        if (response1?.responsecode === 1) {
            console.log(response1?.responsecode, "Respomse code");
            if (response1?.data !== null || response1?.data !== undefined || response1?.data !== "") {
                setbalancetransfer(response1?.data);
                formik.resetForm();
            }
        } else {
            setbalancetransfer([]);
        }
    };

    useEffect(() => {
        handleGetRequest("js_hr_fileupload/lov/lovFinInstBank", 8081)
            .then((res) => {
                settransactionInquiry(Array.isArray(res?.data) ? res.data : []);
            })
            .catch((err) => console.log(err));
    }, []); // eslint-disable-line
    const statusBody = (rowData) => {
        return <div>{rowData?.status === "Y" ? "Yes" : "No"}</div>;
    };
    const statusTB = (rowData) => {
        return <div>{rowData?.transBt === "BT" ? "Balance Transfer" : rowData?.transBt}</div>;
    };

    const onRowSelect = (event) => {};
    const validationSchema = Yup.object().shape({
        fromDate: Yup.string().required("This field is required."),
        toDate: Yup.string().required("This field is required."),
    });

    const formik = useFormik({
        validationSchema: validationSchema,
        initialValues: {
            fininstBankId: "",
            fromDate: "",
            toDate: "",
        },
        onSubmit: async (data) => {
            const fininstBankId = remitterCompany?.map((item) => parseInt(item?.id));
            data["fininstBankId"] = fininstBankId;
            setloadingIcon("pi pi-spin pi-spinner");
            setSelectedBt(data);
            const res = await dispatch(handlePostRequest(data, "js_hr_transactions/transaction/getAllBtTransactions", true, true, 8084));
            if (res?.responsecode === 1) {
                console.log(res?.responsecode, "Respomse code");
                if (res?.data !== null || res?.data !== undefined || res?.data !== "") {
                    console.log("Final If");
                    setbalancetransfer(res?.data);
                    formik.resetForm();
                }
            } else {
                console.log("else statement");
                setbalancetransfer([]);
            }

            setloadingIcon("pi pi-save");
        },
    });
    const isFormFieldValid = (name) => !!(formik.touched[name] && formik.errors[name]);
    const getFormErrorMessage = (name) => {
        return isFormFieldValid(name) && <small className="p-error">{formik.errors[name]}</small>;
    };

    return (
        <div className="card Card__Round">
            <div className="Form__Header">
                <h1>Bank Transfer Transaction</h1>
            </div>
            <form onSubmit={formik.handleSubmit} className="fluid mt-2">
                <div className="fluid formgrid grid">
                    <div className="field col-12 md:col-3 FD_Wrapper">
                        <label htmlFor="fromDate" className="Label__Text">
                            Date From <span className="Label__Required">*</span>
                        </label>
                        <InputText id="fromDate" type="date" value={formik.values.fromDate} onChange={formik.handleChange} className="Input__Round" />
                        {getFormErrorMessage("fromDate")}
                    </div>
                    <div className="field col-12 md:col-3 FD_Wrapper">
                        <label htmlFor="toDate" className="Label__Text">
                            Date To<span className="Label__Required">*</span>
                        </label>
                        <InputText id="toDate" type="date" value={formik.values.toDate} onChange={formik.handleChange} className="Input__Round" />
                        {getFormErrorMessage("toDate")}
                    </div>

                    <div className="field col-12 md:col-3">
                        <div className="field">
                            <label htmlFor="fininstBankId" className={classNames({ "p-error": isFormFieldValid("fininstBankId") }, "Label__Text")}>
                                Remiting Company
                            </label>
                            <MultiSelect
                                placeholder="Select Remiting Company"
                                optionLabel="description"
                                options={transactionInquiry}
                                value={remitterCompany}
                                onChange={(e) => {
                                    setremitterCompany(e.target.value);
                                }}
                                className={classNames("Dropdown__Round")}
                            />
                            {getFormErrorMessage("fininstBankId")}
                        </div>
                    </div>
                </div>
                <div className="Down__Btn mt-4">
                    <Button label="Cancel" className="Btn__Transparent" onClick={(e) => cancelHandler(e)} />
                    <Button type="submit" icon={loadingIcon || ""} iconPos="right" label="Search" className="Btn__Dark" />
                </div>
            </form>
            <div className="card p-datatable-sm">
                <DataTable
                    header="Balance Transfer"
                    responsiveLayout="scroll"
                    selection={selectedRow}
                    onSelectionChange={(e) => setselectedRow(e.value)}
                    selectionMode="multiple"
                    onRowSelect={onRowSelect}
                    paginator
                    rows={10}
                    value={balancetransfer || []}
                    emptyMessage="No data found."
                    dataKey="transDetialId"
                    paginatorTemplate="FirstPageLink PrevPageLink PageLinks NextPageLink LastPageLink CurrentPageReport RowsPerPageDropdown"
                    currentPageReportTemplate="Showing {first} to {last} of {totalRecords} Trasnactions"
                >
                    <Column selectionMode="multiple" headerStyle={{ width: "3em" }}></Column>
                    <Column filter field="beneficaryAccNo" header="beneficary Account No" />
                    <Column filter field="beneficaryBankName" header="Beneficary Bank Name" />
                    <Column filter field="amount" header="Amount" />
                    <Column filter field="beneficaryName" header="Beneficary Name" />
                    <Column filter field="beneficaryCnic" header="Beneficary Cnic" />
                    <Column filter field="beneficaryBranchAdd1" header="Beneficary Branch Address" />
                    <Column filter field="remitterAddress" header="Remitter Address" />
                    <Column filter field="remitterCountry" header="Remitter Country" />
                    <Column filter field="remitterName" header="Remitter Name" />
                    <Column filter field="status" body={statusBody} header="Status" />
                    <Column filter field="transBt" body={statusTB} header="Trans Balance transfer" />
                    <Column filter field="transactionReference" header="Transaction Reference " />
                </DataTable>
                <div className="Down__Btn mt-4">
                    <Button label="Generate MT102 file" className="Btn__Transparent" onClick={(e) => submitHandler(e, "generate")} />
                </div>
            </div>
        </div>
    );
}

export default BankTransferTransaction;

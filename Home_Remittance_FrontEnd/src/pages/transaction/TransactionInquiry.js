import React, { useEffect, useState } from "react";
import { InputText } from "primereact/inputtext";
import { Button } from "primereact/button";
import { useFormik } from "formik";
import classNames from "classnames";
import * as Yup from "yup";
import { handleGetRequest } from "../.././services/GetTemplate";
import { MultiSelect } from "primereact/multiselect";
import { useDispatch } from "react-redux";
import { handlePostRequest } from "../.././services/PostTemplate";
import { DataTable } from "primereact/datatable";
import { Column } from "primereact/column";
import { Dropdown } from "primereact/dropdown";
import { useHistory } from "react-router-dom";

function TransactionProcessFilter({ getData }) {
    const history = useHistory();
    const [loadingIcon, setloadingIcon] = useState("pi pi-search");
    const [transactionInquiry, settransactionInquiry] = useState([]);
    const [selectedTransactionValue, setSelectedTransactionValue] = useState([]);
    const [transHeadId, settransHeadId] = useState();
    const [transactionInquiryData, settransactionInquiryData] = useState([]);
    const [isFileEditable, setisFileEditable] = useState(true);
    const [fileName, setFileName] = useState([]);
    const [fileNameOptions, setFileNameOptions] = useState([]);

    const [selectedRow, setselectedRow] = useState([]);
    const [expandedRows, setExpandedRows] = useState();
    const dispatch = useDispatch();

    const cancelHandler = (e) => {
        e.preventDefault();
        formik.resetForm();
    };
    const sourceOptions = [
        { label: "File name", value: "F" },
        { label: "Push", value: "P" },
        { label: "Pull", value: "PL" },
    ];

    useEffect(() => {
        handleGetRequest("js_hr_fileupload/lov/lovFinInstBank", 8081).then((res) => {
            if (res?.responsecode === 1) {
                settransactionInquiry(res?.data || []);
            }
        });
    }, []); // eslint-disable-line
    const statusBody = (rowData) => {
        return <div>{rowData?.isMandatory === "Y" ? "Yes" : "No"}</div>;
    };

    const onRowSelect = (event) => {};
    const validationSchema = Yup.object().shape({
        // fileNames: Yup.string().required("This field is required."),
        fromDate: Yup.string().required("This field is required."),
        toDate: Yup.string().required("This field is required."),
    });
    //searchable multi select code
    const panelFooterTemplate = () => {
        const selectedItems = selectedTransactionValue;
        const length = selectedItems ? selectedItems.length : 0;
        return (
            <div className="py-2 px-3">
                <b>{length}</b> item{length > 1 ? "s" : ""} selected.
            </div>
        );
    };
    const RemittingCompanyTemplate = (option) => {
        return <div className="country-item">{option?.description}</div>;
    };
    const selectedRemittingCompaniesTemplate = (option) => {
        if (option) {
            return (
                <div style={{ display: "inline" }} className="country-item country-item-value">
                    {option?.description}
                </div>
            );
        }

        return "Select Remitting Countries";
    };
    //end

    const formik = useFormik({
        validationSchema: validationSchema,
        initialValues: {
            fileNames: "",
            fininstBankId: "",
            fromDate: "",
            toDate: "",
            source: "",
            menuType: "",
        },
        onSubmit: async (data) => {
            console.log("data", data);
            delete data.source;
            if (data.menuType === "" || data.menuType === "F") {
                data["apiType"] = "A";
            } else if (data.menuType === "P") {
                data["apiType"] = "PUSH";
                delete data.fileNames;
            } else if (data.menuType === "PL") {
                data["apiType"] = "PULL";
                delete data.fileNames;
            }
            // data["apiType"] = data?.menuType === "F" ? "A" : data?.menuType === "P" ? "PUSH" : "PULL";
            const fininstBankId = selectedTransactionValue.map((item) => parseInt(item.id));
            data["fininstBankId"] = fininstBankId;
            console.log("file", fileName);

            if (data?.menuType === "PL" || data?.menuType === "P") {
                delete data.fileNames;
            } else {
                data["fileNames"] = fileName;
            }

            const res = await dispatch(handlePostRequest(data, "js_hr_transactions/transaction/batchInquiry", true, true, 8084));
            if (res?.responsecode === 1) {
                console.log("checking");
                settransactionInquiryData(res?.data);
            }
            setloadingIcon("pi pi-spin pi-spinner");
            //  await getData(data);
            setloadingIcon("pi pi-save");
        },
    });

    const actionTemplateStatus = (rowData) => {
        console.log(rowData);
        return (
            <div className="flex">
                <Button icon="pi pi-eye" className="p-button-rounded p-button-success mr-2" onClick={() => history.push(`/viewtransactionstatus?transid=${transHeadId}&statuscode=${rowData?.statusCode}`)} />
            </div>
        );
    };

    const rowExpansionTemplate = (data) => {
        settransHeadId(data?.transHeadId);

        return (
            <div className="orders-subtable">
                <DataTable filter paginator rows={10} header={""} value={data?.transStatusCounts} emptyMessage="No data found.">
                    <Column filter field="statusDescr" header="Status Description" />
                    <Column filter field="qty" header="Quantity" />
                    <Column body={actionTemplateStatus}></Column>
                </DataTable>
            </div>
        );
    };
    const isFormFieldValid = (name) => !!(formik.touched[name] && formik.errors[name]);
    const getFormErrorMessage = (name) => {
        return isFormFieldValid(name) && <small className="p-error">{formik.errors[name]}</small>;
    };
    const changeHandler = async (e) => {
        if (e.target.value === "F") {
            const fininstBankId = selectedTransactionValue.map((item) => parseInt(item.id));
            const data = { fininstBankId: fininstBankId, fromDate: formik.values.fromDate, toDate: formik.values.toDate };
            const res = await dispatch(handlePostRequest(data, "js_hr_transactions/transaction/finInstWiseFileName", true, true, 8084));
            if (res?.responsecode === 1) {
                setisFileEditable(false);
                setFileNameOptions(Array.isArray(res?.data) ? res.data : []);
            }
        }
    };

    return (
        <div className="card Card__Round">
            <div className="Form__Header">
                <h1>Transaction Inquiry</h1>
            </div>
            <form onSubmit={formik.handleSubmit} className="fluid mt-2">
                <div className="fluid formgrid grid">
                    <div className="field col-12 md:col-3">
                        <div className="field">
                            <label htmlFor="fininstBankId" className={classNames({ "p-error": isFormFieldValid("fininstBankId") }, "Label__Text")}>
                                Remiting Company
                            </label>
                            <MultiSelect
                                value={selectedTransactionValue}
                                options={transactionInquiry}
                                onChange={(e) => {
                                    setSelectedTransactionValue(e.target.value);
                                }}
                                optionLabel="description"
                                placeholder="Select Remitting Company"
                                filter
                                className={classNames("Dropdown__Round")}
                                itemTemplate={RemittingCompanyTemplate}
                                selectedItemTemplate={selectedRemittingCompaniesTemplate}
                                panelFooterTemplate={panelFooterTemplate}
                            />
                            {getFormErrorMessage("fininstBankId")}
                        </div>
                    </div>
                    <div className="field col-12 md:col-3 FD_Wrapper">
                        <label htmlFor="fromDate" className="Label__Text">
                            Date From<span className="Label__Required">*</span>
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
                    <div className="p-field p-col-12 md:col-3">
                        <div className="p-field">
                            <label htmlFor="menuType" className={classNames({ "p-error": isFormFieldValid("menuType") }, "Label__Text")}>
                                Source<span className="Label__Required">*</span>
                            </label>
                            <Dropdown
                                id="menuType"
                                placeholder="Select Source type"
                                options={sourceOptions}
                                optionLabel="label"
                                name="menuType"
                                value={formik.values.menuType}
                                onChange={(e) => {
                                    changeHandler(e);
                                    formik.handleChange(e);
                                }}
                                className={classNames({ "p-invalid": isFormFieldValid("menuType") }, "Dropdown__Round")}
                            />
                            {getFormErrorMessage("menuType")}
                        </div>
                    </div>
                    {formik.values.menuType === "F" ? (
                        <div className="field col-12 md:col-3">
                            <div className="field">
                                <label htmlFor="fileName" className={classNames({ "p-error": isFormFieldValid("fileName") }, "Label__Text")}>
                                    File Name
                                </label>
                                <MultiSelect
                                    disabled={isFileEditable}
                                    placeholder="Select Remiting Company"
                                    optionLabel="fileName"
                                    options={fileNameOptions}
                                    value={fileName}
                                    optionValue="fileName"
                                    onChange={(e) => {
                                        setFileName(e.target.value);
                                    }}
                                    className={classNames("Dropdown__Round")}
                                />
                                {getFormErrorMessage("fileName")}
                            </div>
                        </div>
                    ) : null}
                </div>
                <div className="Down__Btn mt-4">
                    <Button label="Cancel" className="Btn__Transparent" onClick={(e) => cancelHandler(e)} />
                    <Button type="submit" icon={loadingIcon || ""} iconPos="right" label="Search" className="Btn__Dark" />
                </div>
            </form>
            <div className="card p-datatable-sm">
                <DataTable
                    header="Transactions"
                    selection={selectedRow}
                    expandedRows={expandedRows}
                    onRowToggle={(e) => {
                        setExpandedRows(e.data);
                    }}
                    rowExpansionTemplate={rowExpansionTemplate}
                    onSelectionChange={(e) => setselectedRow(e.value)}
                    selectionMode="single"
                    dataKey="transHeadId"
                    onRowSelect={onRowSelect}
                    paginatorTemplate="FirstPageLink PrevPageLink PageLinks NextPageLink LastPageLink CurrentPageReport RowsPerPageDropdown"
                    currentPageReportTemplate="Showing {first} to {last} of {totalRecords} Transactions"
                    paginator
                    rows={10}
                    value={transactionInquiryData || []}
                    emptyMessage="No data found."
                >
                    <Column expander style={{ width: "3em" }} />
                    <Column filter field="sourceFileName" header="Source File Name" />
                    <Column filter field="fileDate" header="File Date" />
                    <Column filter field="finInstDescr" header="Description" />
                    <Column filter field="totalRecords" header="Total Records " />
                    <Column filter field="paidAmount" header="Paid Amount" />
                    <Column filter field="remainingAmount" header="Remaining Amount" />
                </DataTable>
            </div>
        </div>
    );
}

export default TransactionProcessFilter;

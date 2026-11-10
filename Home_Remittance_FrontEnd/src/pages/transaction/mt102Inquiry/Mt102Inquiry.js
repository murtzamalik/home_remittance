import React, { useEffect, useState } from "react";
import { Button } from "primereact/button";
import { useFormik } from "formik";
import classNames from "classnames";
import * as Yup from "yup";
import { handleGetRequest } from "../../.././services/GetTemplate";
import { useDispatch } from "react-redux";
import { handlePostRequest } from "../../.././services/PostTemplate";
import { DataTable } from "primereact/datatable";
import { Column } from "primereact/column";
import { Dropdown } from "primereact/dropdown";
import { toast } from "react-toastify";
import { saveAs } from "file-saver";

function Mt102Inquiry() {
    const [loadingIcon, setloadingIcon] = useState("pi pi-search");
    const [mt102InquiryOptions, setmt102InquiryOptions] = useState([]);
    const [mt102InquiryData, setmt102InquiryData] = useState([]);
    const [bankName, setBankName] = useState([]);
    const [selectedRow, setselectedRow] = useState([]);
    const [base64, setBase64] = useState([]);
    const [fileName, setFileName] = useState([]);
    const dispatch = useDispatch();

    const cancelHandler = (e) => {
        e.preventDefault();
        formik.resetForm();
    };
    const handleBankName = (e) => {
        handleGetRequest(`js_hr_transactions/lov/lovBankNames/${e.target.value ? e.target.value : 0}`, 8084).then((res) => {
            if (res?.responsecode === 1) {
                setBankName(res?.data || []);
            }
        });
    };

    useEffect(() => {
        handleGetRequest("js_hr_fileupload/lov/lovFinInstBank", 8081).then((res) => {
            if (res?.responsecode === 1) {
                setmt102InquiryOptions(res?.data || []);
            }
        });
    }, []); // eslint-disable-line
    useEffect(() => {
        if (formik.values.finInstId === 0) {
            handleGetRequest(`js_hr_transactions/lov/lovBankNames/${formik.values.finInstId}`, 8084).then((res) => {
                if (res?.responsecode === 1) {
                    setBankName(res?.data || []);
                }
            });
        }
    }, []); // eslint-disable-line

    const onRowSelect = (event) => {
        setBase64([...base64, event?.data?.file]);
        setFileName([...fileName, event?.data?.fileRefNo]);
    };

    const handleDownloadMt102 = () => {
        if (base64) {
            for (let i = 0; i <= base64.length; i++) {
                let buff = new Buffer(base64[i], "base64");
                let text = buff.toString("utf-8");
                saveAs("data:data:text/plain," + text, `${fileName[i]}.txt`);
            }
        }
    };
    const validationSchema = Yup.object().shape({});
    const formik = useFormik({
        validationSchema: validationSchema,
        initialValues: {
            finInstId: 0,
            bankName: "",
        },
        onSubmit: async (data) => {
            if (data.finInstId === 0 && data?.bankName === "") {
                toast.warn("Please Select one of Field");
            } else {
                data["finInstId"] = parseInt(data?.finInstId);
                const res = await dispatch(handlePostRequest(data, "js_hr_transactions/transaction/mt102Inquiry", true, true, 8084));
                if (res?.responsecode === 1) {
                    setmt102InquiryData(res?.data);
                    formik.resetForm();
                }
            }
        },
    });

    const isFormFieldValid = (name) => !!(formik.touched[name] && formik.errors[name]);
    const getFormErrorMessage = (name) => {
        return isFormFieldValid(name) && <small className="p-error">{formik.errors[name]}</small>;
    };

    return (
        <div className="card Card__Round">
            <div className="Form__Header">
                <h1>MT102 Inquiry</h1>
            </div>
            <form onSubmit={formik.handleSubmit} className="fluid mt-2">
                <div className="fluid formgrid grid">
                    <div className="field col-12 md:col-3">
                        <div className="field">
                            <label htmlFor="finInstId" className={classNames({ "p-error": isFormFieldValid("finInstId") }, "Label__Text")}>
                                Remiting Company
                            </label>
                            <Dropdown
                                optionValue="id"
                                placeholder="Select Remitting Company"
                                optionLabel="description"
                                options={mt102InquiryOptions}
                                value={formik.values.finInstId}
                                name="finInstId"
                                onBlur={handleBankName}
                                onChange={(e) => {
                                    formik.handleChange(e);
                                    handleBankName(e);
                                }}
                                className={classNames("Dropdown__Round")}
                            />
                            {getFormErrorMessage("finInstId")}
                        </div>
                    </div>
                    <div className="field col-12 md:col-3">
                        <div className="field">
                            <label htmlFor="bankName" className={classNames({ "p-error": isFormFieldValid("bankName") }, "Label__Text")}>
                                Bank Name
                            </label>
                            <Dropdown placeholder="Select Bank Name" name="bankName" optionValue="code" optionLabel="code" options={bankName} value={formik.values.bankName} onChange={formik.handleChange} className={classNames("Dropdown__Round")} /> {getFormErrorMessage("bankName")}
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
                    responsiveLayout="scroll"
                    selection={selectedRow}
                    onSelectionChange={(e) => setselectedRow(e.value)}
                    selectionMode="multiple"
                    onRowSelect={onRowSelect}
                    paginator
                    rows={10}
                    value={mt102InquiryData || []}
                    emptyMessage="No data found."
                    dataKey="fileRefNo"
                    paginatorTemplate="FirstPageLink PrevPageLink PageLinks NextPageLink LastPageLink CurrentPageReport RowsPerPageDropdown"
                    currentPageReportTemplate="Showing {first} to {last} of {totalRecords} Files"
                >
                    <Column selectionMode="multiple" headerStyle={{ width: "3em" }}></Column>
                    <Column filter field="fileRefNo" header="File Name" />
                    <Column filter field="bankName" header="Bank Name" />
                    <Column filter field="finInstDescr" header="Description" />
                    <Column filter field="noOfTrans" header="No Of Transactions" />
                    <Column filter field="amount" header="Amount" />
                </DataTable>
                <div className="Down__Btn mt-4">
                    <Button label="Download" icon="pi pi-file" iconPos="right" onClick={handleDownloadMt102} className="p-button-raised p-button-success" />
                </div>
            </div>
        </div>
    );
}

export default Mt102Inquiry;

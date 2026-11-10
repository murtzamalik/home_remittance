import React, { useEffect, useState } from "react";
import { InputText } from "primereact/inputtext";
import { Button } from "primereact/button";
import { useFormik } from "formik";
import classNames from "classnames";
import * as Yup from "yup";
import { handleGetRequest } from "../../../services/GetTemplate";
import { useDispatch } from "react-redux";
import { handlePostRequest } from "../../../services/PostTemplate";
import { DataTable } from "primereact/datatable";
import { Column } from "primereact/column";
import { Dropdown } from "primereact/dropdown";
import { handlePutRequest } from "../../../services/PutTemplate";

function AddEditRemitLimit() {
    const [loadingIcon, setloadingIcon] = useState("pi pi-save");
    const [remitLimitData, setremitLimitData] = useState([]);
    const [editable, seteditable] = useState(false);
    const [remitModeId, setremitModeId] = useState("");
    const [loading, setloading] = useState([]);
    const dispatch = useDispatch();

    const cancelHandler = (e) => {
        e.preventDefault();
        formik.resetForm();
        seteditable(false);
    };

    const getRemitLimit = async () => {
        setloading(true);
        const res = await handleGetRequest("js-hr-fi/fi/getAllRemitLimits", 8082);
        if (res?.responsecode === 1) {
            setremitLimitData(res?.data || []);
            setloading(false);
        }
    };
    useEffect(() => {
        getRemitLimit();
    }, []); // eslint-disable-line

    const remitEditHandler = (rowData) => {
        Object.keys(rowData).forEach((key) => {
            if (formik.initialValues.hasOwnProperty(key)) {
                formik.setFieldValue(key, rowData[key]);
            }
        });
        setremitModeId(parseInt(rowData?.remitModeId));
        formik.setFieldValue("isActive", rowData.isActive);
        seteditable(true);
    };

    const statusBody = (rowData) => {
        return <div>{rowData?.status === "Y" ? "Yes" : "No"}</div>;
    };
    const ActiveStatus = [
        { label: "Yes", value: "Y" },
        { label: "No", value: "N" },
    ];
    const actionTemplate = (rowData) => {
        return (
            <div>
                <Button tooltip="Edit" icon="pi pi-pencil" tooltipOptions={{ position: "top" }} onClick={() => remitEditHandler(rowData)} className="p-button-rounded p-button-warning p-mr-2" />
            </div>
        );
    };

    const validationSchema = Yup.object().shape({
        amountCapping: Yup.string().required("This field is required."),
        remitCode: Yup.string().required("This field is required."),
    });

    const formik = useFormik({
        validationSchema: validationSchema,
        initialValues: {
            amountCapping: "",
            remitCode: "",
            remitDefault: "",
            remitDescr: "",
            isActive: "",
        },
        onSubmit: async (data) => {
            setloadingIcon("pi pi-spin pi-spinner");
            if (editable) {
                data["remitModeId"] = remitModeId;
                data["amountCapping"] = parseInt(data.amountCapping);
                const res = await dispatch(handlePutRequest(data, "js-hr-fi/fi/updateRemitLimit", true, true, 8082));
                if (res?.status !== 200 && res?.status !== undefined) {
                } else if (res?.responsecode === 1) {
                    formik.resetForm();
                    seteditable(false);
                }
                getRemitLimit();
            } else {
                const res = await dispatch(handlePostRequest(data, "js-hr-fi/fi/saveRemitLimit", true, true, 8082));
                if (res?.status !== 200 && res?.status !== undefined) {
                } else if (res?.responsecode === 1) {
                    formik.resetForm();
                }
            }

            getRemitLimit();

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
                <h1>Remit Limit</h1>
            </div>
            <form onSubmit={formik.handleSubmit} className="fluid mt-2">
                <div className="fluid formgrid grid">
                    <div className="field col-12 md:col-3 FD_Wrapper">
                        <label htmlFor="amountCapping" className="Label__Text">
                            Ammount Capping <span className="Label__Required">*</span>
                        </label>
                        <InputText id="amountCapping" placeholder="Enter Amount Capping" type="text" value={formik.values.amountCapping} onChange={formik.handleChange} className="Input__Round" />
                        {getFormErrorMessage("amountCapping")}
                    </div>
                    <div className="field col-12 md:col-3 FD_Wrapper">
                        <label htmlFor="remitCode" className="Label__Text">
                            Code<span className="Label__Required">*</span>
                        </label>
                        <InputText id="remitCode" type="text" placeholder="Enter Code" value={formik.values.remitCode} onChange={formik.handleChange} className="Input__Round" />
                        {getFormErrorMessage("remitCode")}
                    </div>
                    <div className="field col-12 md:col-3 FD_Wrapper">
                        <label htmlFor="remitDefault" className="Label__Text">
                            Remit Default<span className="Label__Required">*</span>
                        </label>
                        <InputText id="remitDefault" type="text" placeholder="Enter Remit Default" value={formik.values.remitDefault} onChange={formik.handleChange} className="Input__Round" />
                        {getFormErrorMessage("remitDefault")}
                    </div>
                    <div className="field col-12 md:col-3 FD_Wrapper">
                        <label htmlFor="remitDescr" className="Label__Text">
                            Description <span className="Label__Required">*</span>
                        </label>
                        <InputText id="remitDescr" type="text" placeholder="Enter Description" value={formik.values.remitDescr} onChange={formik.handleChange} className="Input__Round" />
                        {getFormErrorMessage("remitDescr")}
                    </div>
                    <div className="p-field p-col-12 md:col-4">
                        <div className="p-field">
                            <label htmlFor="isActive" className={classNames({ "p-error": isFormFieldValid("isActive") }, "Label__Text")}>
                                Active Status<span className="Label__Required">*</span>
                            </label>
                            <Dropdown id="isActive" placeholder="Select Status" options={ActiveStatus} optionLabel="label" name="isActive" value={formik.values.isActive} onChange={formik.handleChange} className={classNames({ "p-invalid": isFormFieldValid("isActive") }, "Dropdown__Round")} />
                            {getFormErrorMessage("isActive")}
                        </div>
                    </div>
                </div>
                <div className="Down__Btn mt-4">
                    <Button label="Cancel" className="Btn__Transparent" onClick={(e) => cancelHandler(e)} />
                    <Button type="submit" icon={loadingIcon || ""} iconPos="right" label={editable ? "Update" : "Add"} className="Btn__Dark" />
                </div>
            </form>
            <div className="card p-datatable-sm">
                <DataTable
                    header="Remmit Data"
                    responsiveLayout="scroll"
                    paginator
                    rows={10}
                    loading={loading}
                    value={remitLimitData}
                    emptyMessage="No data found."
                    paginatorTemplate="FirstPageLink PrevPageLink PageLinks NextPageLink LastPageLink CurrentPageReport RowsPerPageDropdown"
                    currentPageReportTemplate="Showing {first} to {last} of {totalRecords} "
                    dataKey="transDetialId"
                >
                    <Column filter field="remitDescr" header="Remit Description" />
                    <Column filter field="amountCapping" header="Ammount capping" />
                    <Column filter field="createdate" header="Create date" />
                    <Column filter field="isActive" status={statusBody} header="Active Status" />
                    <Column body={actionTemplate} header="Action" />
                </DataTable>
            </div>
        </div>
    );
}

export default AddEditRemitLimit;

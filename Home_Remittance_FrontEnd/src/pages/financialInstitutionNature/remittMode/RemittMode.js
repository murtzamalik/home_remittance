import React, { useState, useEffect } from "react";
import { DataTable } from "primereact/datatable";
import { Column } from "primereact/column";
import { useSelector, useDispatch } from "react-redux";
import { InputText } from "primereact/inputtext";
import { Button } from "primereact/button";
import { useFormik } from "formik";
import * as Yup from "yup";
import classNames from "classnames";
import { handleGetRequest } from "./../../../services/GetTemplate";
import { handlePostRequest } from "../../../services/PostTemplate";
import { handlePutRequest } from "../../../services/PutTemplate";
import { Dropdown } from "primereact/dropdown";

/* eslint-disable */
function RemittMode() {
    const [globalFilter, setGlobalFilter] = useState(null);
    const [remitData, setRemitdata] = useState([]);
    const dispatch = useDispatch();
    const [editable, setEditable] = useState(false);
    const isLoading = useSelector((state) => state?.utilitySlice?.isLoadingComponent);

    const getData = async () => {
        const response = await handleGetRequest("js-hr-fi/fi/getAllRemitLimits", 8082);
        setRemitdata(response?.data);
    };
    useEffect(() => {
        getData();
    }, []);
    const ActiveOptions = [
        { label: "Yes", value: "Y" },
        { label: "No", value: "N" },
    ];

    const validationSchema = Yup.object().shape({
        amountCapping: Yup.string().required("This field is required."),
        remitCode: Yup.string().required("This field is required."),
        remitDefault: Yup.string().required("This field is required."),
        remitDescr: Yup.string().required("This field is required."),
    });
    const editHandler = (rowData) => {
        setEditable(true);
        const keys = Object.keys(rowData);
        keys.forEach((item) => {
            formik.setFieldValue(`${item}`, rowData[item]);
        });
        formik.setFieldValue("isActive", rowData.isActive);
    };
    const cancelHandler = (e) => {
        e.preventDefault();
        formik.resetForm();
        setEditable(false);
    };

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
            if (editable) {
                const res = await dispatch(handlePutRequest(data, `js-hr-fi/fi/updateRemitLimit`, true, true, 8082));
                if (res?.status && res?.status !== 200) {
                    res?.data.forEach((element) => {
                        formik.setFieldError(element?.field, element?.message);
                    });
                } else if (res?.responsecode === 1) {
                    formik.resetForm();
                }
                await getData();
            } else {
                const res = await dispatch(handlePostRequest(data, `js-hr-fi/fi/saveRemitLimit`, true, true, 8082));
                if (res?.status && res?.status !== 200) {
                    res?.data.forEach((element) => {
                        formik.setFieldError(element?.field, element?.message);
                    });
                } else if (res?.responsecode === 1) {
                    formik.resetForm();
                }
                await getData();
            }
        },
    });
    const isFormFieldValid = (name) => !!(formik.touched[name] && formik.errors[name]);
    const getFormErrorMessage = (name) => {
        return isFormFieldValid(name) && <small className="p-error">{formik.errors[name]}</small>;
    };

    const header = (
        <div className="flex flex-column md:flex-row md:justify-content-between md:align-items-center">
            <h5 className="m-0">Remitt Mode</h5>
            <span className="block mt-2 md:mt-0">
                <InputText type="search" onInput={(e) => setGlobalFilter(e.target.value)} placeholder="Search..." />
            </span>
        </div>
    );

    const actionTemplate = (rowData) => {
        return (
            <div>
                <Button tooltip="Edit" icon="pi pi-pencil" tooltipOptions={{ position: "top" }} onClick={() => editHandler(rowData)} className="p-button-rounded p-button-warning p-mr-2" />
            </div>
        );
    };

    return (
        <div className="mt-2">
            <div className="card Card__Round">
                <div className="Form__Header">
                    <h1>Remitt Mode</h1>
                </div>
                <form onSubmit={formik.handleSubmit} className="fluid mt-2">
                    <div className="fluid formgrid grid">
                        <div className="field col-12 md:col-4">
                            <div className="field">
                                <label htmlFor="amountCapping" className={classNames({ "p-error": isFormFieldValid("amountCapping") }, "Label__Text")}>
                                    Amount Capping<span className="Label__Required">*</span>
                                </label>
                                <InputText id="amountCapping" placeholder="Enter Amount Capping" name="amountCapping" value={formik.values.amountCapping?.toString()?.trim()} onChange={formik.handleChange} className={classNames({ "p-invalid": isFormFieldValid("amountCapping") }, "Input__Round")} />

                                {getFormErrorMessage("amountCapping")}
                            </div>
                        </div>
                        <div className="field col-12 md:col-4">
                            <div className="field">
                                <label htmlFor="remitCode" className={classNames({ "p-error": isFormFieldValid("remitCode") }, "Label__Text")}>
                                    Remit Code<span className="Label__Required">*</span>
                                </label>
                                <InputText id="remitCode" placeholder="Enter Remit Code" name="remitCode" value={formik.values.remitCode?.toString()?.trim()} onChange={formik.handleChange} className={classNames({ "p-invalid": isFormFieldValid("remitCode") }, "Input__Round")} />

                                {getFormErrorMessage("remitCode")}
                            </div>
                        </div>
                        <div className="field col-12 md:col-4">
                            <div className="field">
                                <label htmlFor="remitDefault" className={classNames({ "p-error": isFormFieldValid("remitDefault") }, "Label__Text")}>
                                    Remit Default<span className="Label__Required">*</span>
                                </label>
                                <Dropdown id="remitDefault" placeholder="" options={ActiveOptions} optionLabel="label" name="remitDefault" value={formik.values.remitDefault} onChange={formik.handleChange} className={classNames({ "p-invalid": isFormFieldValid("remitDefault") }, "Dropdown__Round")} />
                                {getFormErrorMessage("remitDefault")}
                            </div>
                        </div>
                        <div className="field col-12 md:col-4">
                            <div className="field">
                                <label htmlFor="remitDescr" className={classNames({ "p-error": isFormFieldValid("remitDescr") }, "Label__Text")}>
                                    Remit Decription<span className="Label__Required">*</span>
                                </label>
                                <InputText id="remitDescr" placeholder="Enter Remit Decription" name="remitDescr" value={formik.values.remitDescr?.toString()?.trim()} onChange={formik.handleChange} className={classNames({ "p-invalid": isFormFieldValid("remitDescr") }, "Input__Round")} />

                                {getFormErrorMessage("remitDescr")}
                            </div>
                        </div>
                        <div className="p-field p-col-12 p-md-4">
                            <div className="p-field">
                                <label htmlFor="isActive" className={classNames({ "p-error": isFormFieldValid("isActive") }, "Label__Text")}>
                                    Menu Type<span className="Label__Required">*</span>
                                </label>
                                <Dropdown id="isActive" placeholder="" options={ActiveOptions} optionLabel="label" name="isActive" value={formik.values.isActive} onChange={formik.handleChange} className={classNames({ "p-invalid": isFormFieldValid("isActive") }, "Dropdown__Round")} />
                                {getFormErrorMessage("isActive")}
                            </div>
                        </div>
                    </div>
                    <div className="Down__Btn_Left mt-4">
                        <Button label="Cancel" className="Btn__Transparent" onClick={(e) => cancelHandler(e)} />
                        <Button type="submit" iconPos="right" label={editable ? "Update" : "Add"} className="Btn__Dark" />
                    </div>
                </form>
            </div>
            <div className="card p-datatable-sm">
                <DataTable header={header} globalFilter={globalFilter} filterDisplay="row" loading={isLoading} paginator rows={12} value={remitData} emptyMessage="No data found." showGridlines responsiveLayout="scroll" dataKey="transDetailId">
                    <Column filter field="amountCapping" header="Amount Capping" />
                    <Column filter field="remitCode" header="Remit Code" />
                    <Column filter field="remitDefault" header="Remit Default" />
                    <Column filter field="remitDescr" header="Remit Descritption" />
                    <Column body={actionTemplate} header="Action" />
                </DataTable>
            </div>
        </div>
    );
}

export default RemittMode;

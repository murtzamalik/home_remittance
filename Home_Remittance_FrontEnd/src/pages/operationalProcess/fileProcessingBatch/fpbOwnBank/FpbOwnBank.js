import React, { useState } from "react";
import { InputText } from "primereact/inputtext";
import { Button } from "primereact/button";
import { useFormik } from "formik";
import classNames from "classnames";
import * as Yup from "yup";
import { Dropdown } from "primereact/dropdown";

function FileProcessingOwnBank() {
    const [loadingIcon, setloadingIcon] = useState("pi pi-save");
    const cancelHandler = (e) => {
        e.preventDefault();
        formik.resetForm();
    };

    const validationSchema = Yup.object().shape({
        benInstitute: Yup.string().required("This field is required."),
        remitInstitute: Yup.string().required("This field is required."),
        filterDate: Yup.string().required("This field is required."),
    });

    const formik = useFormik({
        validationSchema: validationSchema,
        initialValues: {
            benInstitute: "",
            remitInstitute: "",
            filterDate: "",
        },
        onSubmit: async (data) => {
            setloadingIcon("pi pi-spin pi-spinner");
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
                <h1>File Processing Batch - Own Bank</h1>
            </div>
            <form onSubmit={formik.handleSubmit} className="fluid mt-2">
                <div className="fluid formgrid grid">
                    <div className="field col-12 md:col-4">
                        <div className="field">
                            <label htmlFor="benInstitute" className={classNames({ "p-error": isFormFieldValid("benInstitute") }, "Label__Text")}>
                                Beneficiary Institute <span className="Label__Required">*</span>
                            </label>
                            <Dropdown id="benInstitute" placeholder="Select benInstitute" optionLabel="description" name="benInstitute" value={formik.values.benInstitute} onChange={formik.handleChange} className={classNames({ "p-invalid": isFormFieldValid("benInstitute") }, "Dropdown__Round")} />
                            {getFormErrorMessage("benInstitute")}
                        </div>
                        <div className="field">
                            <InputText id="amount" placeholder="Bank Al falah Limited " name="amount" value={formik.values.amount?.replace(/\s\s+/g, " ")} onChange={formik.handleChange} className={classNames({ "p-invalid": isFormFieldValid("amount") }, "Input__Round")} />
                            {getFormErrorMessage("amount")}
                        </div>
                    </div>
                    <div className="field col-12 md:col-4 FD_Wrapper">
                        <label htmlFor="filterDate" className="Label__Text">
                            Filter Date <span className="Label__Required">*</span>
                        </label>
                        <InputText id="filterDate" type="date" value={formik.values.filterDate} onChange={formik.handleChange} className="Input__Round" placeholder="#000000" />
                        {getFormErrorMessage("filterDate")}
                    </div>
                    <div className="field col-12 md:col-4">
                        <div className="field">
                            <label htmlFor="remitInstitute" className={classNames({ "p-error": isFormFieldValid("remitInstitute") }, "Label__Text")}>
                                Remiting Institute <span className="Label__Required">*</span>
                            </label>
                            <Dropdown
                                id="remitInstitute"
                                placeholder="Select remitInstitute"
                                optionLabel="description"
                                name="remitInstitute"
                                value={formik.values.remitInstitute}
                                onChange={formik.handleChange}
                                className={classNames({ "p-invalid": isFormFieldValid("remitInstitute") }, "Dropdown__Round")}
                            />
                            {getFormErrorMessage("remitInstitute")}
                        </div>
                        <div className="field">
                            <InputText id="amount" placeholder="Bank Al falah Limited " name="amount" value={formik.values.amount?.replace(/\s\s+/g, " ")} onChange={formik.handleChange} className={classNames({ "p-invalid": isFormFieldValid("amount") }, "Input__Round")} />
                            {getFormErrorMessage("amount")}
                        </div>
                    </div>
                </div>
                <div className="Down__Btn">
                    <Button label="Cancel" className="Btn__Transparent" onClick={(e) => cancelHandler(e)} />
                    <Button icon={loadingIcon || ""} iconPos="right" label="Add" className="Btn__Dark" />
                </div>
            </form>
        </div>
    );
}

export default FileProcessingOwnBank;

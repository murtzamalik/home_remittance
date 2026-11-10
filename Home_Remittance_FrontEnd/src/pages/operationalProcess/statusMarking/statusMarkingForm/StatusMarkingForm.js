import React from "react";
import { useFormik } from "formik";
import * as Yup from "yup";
import classNames from "classnames";
import { Calendar } from "primereact/calendar";
import { InputText } from "primereact/inputtext";
import { Dropdown } from "primereact/dropdown";

const StatusMarkingForm = () => {
    const proposedStatusTypes = [{ code: "O", label: "O - Outstanding" }];

    const validationSchema = Yup.object().shape({
        remittingId: Yup.string().required("This field is required."),
        remittingValue: Yup.string().required("This field is required."),
        dateFrom: Yup.string().required("This field is required."),
        dateTo: Yup.string().required("This field is required."),
        proposedStatus: Yup.string().required("This field is required."),
        modeId: Yup.string().required("This field is required."),
        modeValue: Yup.string().required("This field is required."),
        fileName: Yup.string().required("This field is required."),
        uniqueRefNo: Yup.string().required("This field is required."),
    });

    const formik = useFormik({
        validationSchema: validationSchema,
        initialValues: {
            remittingId: "",
            remittingValue: "",
            dateFrom: "",
            dateTo: "",
            proposedStatus: "",
            modeId: "",
            modeValue: "",
            fileName: "",
            uniqueRefNo: "",
        },
        onSubmit: (data) => {},
    });

    const isFormFieldValid = (name) => !!(formik.touched[name] && formik.errors[name]);
    const getFormErrorMessage = (name) => {
        return isFormFieldValid(name) && <small className="p-error">{formik.errors[name]}</small>;
    };

    return (
        <div className="card Card__Round">
            <div className="Form__Header">
                <h1>Filters</h1>
            </div>
            <form onSubmit={formik.handleSubmit} className="fluid mt-2">
                <div className="fluid formgrid grid">
                    <div className="field col-12 md:col-6">
                        <div className="field">
                            <label htmlFor="remittingId" className={classNames({ "p-error": isFormFieldValid("remittingId") }, "Label__Text")}>
                                Remitting Institute Id<span className="Label__Required">*</span>
                            </label>
                            <InputText name="remittingId" id="remittingId" value={formik.values.remittingId} onChange={formik.handleChange} className={classNames({ "p-invalid": isFormFieldValid("remittingId") }, "Dropdown__Round")} />
                            {getFormErrorMessage("remittingId")}
                        </div>
                    </div>
                    {/* Remetting Institue Value */}
                    <div className="field col-12 md:col-6">
                        <div className="field">
                            <label htmlFor="remittingValue" className={classNames({ "p-error": isFormFieldValid("remittingValue") }, "Label__Text")}>
                                Remitting Institute Value<span className="Label__Required">*</span>
                            </label>
                            <InputText name="remittingValue" id="remittingValue" value={formik.values.remittingValue} onChange={formik.handleChange} className={classNames({ "p-invalid": isFormFieldValid("remittingValue") }, "Dropdown__Round")} />
                            {getFormErrorMessage("remittingValue")}
                        </div>
                    </div>
                    {/* Remetting Value date From */}
                    <div className="field col-12 md:col-4">
                        <div className="field">
                            <label htmlFor="dateFrom" className={classNames({ "p-error": isFormFieldValid("dateFrom") }, "Label__Text")}>
                                Date From<span className="Label__Required">*</span>
                            </label>
                            <Calendar id="dateFrom" name="dateFrom" value={formik.dateFrom} onChange={formik.handleChange} className={classNames({ "p-invalid": isFormFieldValid("dateFrom") }, "Dropdown__Round", "Dropdown__Round__Calendar")}></Calendar>
                            {getFormErrorMessage("dateFrom")}
                        </div>
                    </div>
                    {/* Remetting Value Date To */}
                    <div className="field col-12 md:col-4">
                        <div className="field">
                            <label htmlFor="dateTo" className={classNames({ "p-error": isFormFieldValid("dateTo") }, "Label__Text")}>
                                Date To<span className="Label__Required">*</span>
                            </label>
                            <Calendar id="dateTo" name="dateTo" value={formik.dateTo} onChange={formik.handleChange} className={classNames({ "p-invalid": isFormFieldValid("dateTo") }, "Dropdown__Round", "Dropdown__Round__Calendar")}></Calendar>
                            {getFormErrorMessage("dateTo")}
                        </div>
                    </div>
                    {/* Proposed Status */}
                    <div className="field col-12 md:col-4">
                        <div className="field">
                            <label htmlFor="proposedStatus" className={classNames({ "p-error": isFormFieldValid("proposedStatus") }, "Label__Text")}>
                                Proposed Status<span className="Label__Required">*</span>
                            </label>
                            <Dropdown
                                id="proposedStatus"
                                placeholder="Proposed Status"
                                optionLabel="label"
                                options={proposedStatusTypes}
                                name="proposedStatus"
                                value={formik.values.proposedStatus}
                                onChange={formik.handleChange}
                                className={classNames({ "p-invalid": isFormFieldValid("proposedStatus") }, "Dropdown__Round")}
                            />
                            {getFormErrorMessage("proposedStatus")}
                        </div>
                    </div>
                    {/* Mode Id */}
                    <div className="field col-12 md:col-6">
                        <div className="field">
                            <label htmlFor="modeId" className={classNames({ "p-error": isFormFieldValid("modeId") }, "Label__Text")}>
                                Mode Id<span className="Label__Required">*</span>
                            </label>
                            <InputText name="modeId" id="modeId" value={formik.values.modeId} onChange={formik.handleChange} className={classNames({ "p-invalid": isFormFieldValid("modeId") }, "Dropdown__Round")} />
                            {getFormErrorMessage("modeId")}
                        </div>
                    </div>
                    {/* Mode Value */}
                    <div className="field col-12 md:col-6">
                        <div className="field">
                            <label htmlFor="modeValue" className={classNames({ "p-error": isFormFieldValid("modeValue") }, "Label__Text")}>
                                Mode Value<span className="Label__Required">*</span>
                            </label>
                            <InputText name="modeValue" id="modeValue" value={formik.values.modeValue} onChange={formik.handleChange} className={classNames({ "p-invalid": isFormFieldValid("modeValue") }, "Dropdown__Round")} />
                            {getFormErrorMessage("modeValue")}
                        </div>
                    </div>
                    {/* FileName */}
                    <div className="field col-12 md:col-6">
                        <div className="field">
                            <label htmlFor="fileName" className={classNames({ "p-error": isFormFieldValid("fileName") }, "Label__Text")}>
                                File Name<span className="Label__Required">*</span>
                            </label>
                            <InputText name="fileName" id="fileName" value={formik.values.fileName} onChange={formik.handleChange} className={classNames({ "p-invalid": isFormFieldValid("fileName") }, "Dropdown__Round")} />
                            {getFormErrorMessage("fileName")}
                        </div>
                    </div>
                    {/* Unique Ref No */}
                    <div className="field col-12 md:col-6">
                        <div className="field">
                            <label htmlFor="uniqueRefNo" className={classNames({ "p-error": isFormFieldValid("uniqueRefNo") }, "Label__Text")}>
                                Unique Ref No<span className="Label__Required">*</span>
                            </label>
                            <InputText name="uniqueRefNo" id="uniqueRefNo" value={formik.values.uniqueRefNo} onChange={formik.handleChange} className={classNames({ "p-invalid": isFormFieldValid("uniqueRefNo") }, "Dropdown__Round")} />
                            {getFormErrorMessage("uniqueRefNo")}
                        </div>
                    </div>
                </div>
            </form>
        </div>
    );
};

export default StatusMarkingForm;

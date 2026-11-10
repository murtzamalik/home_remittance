import React, { useState, useEffect } from "react";
import { useFormik } from "formik";
import classNames from "classnames";
import { handlePostRequest } from "../../../services/PostTemplate";
import { handlePutRequest } from "../../../services/PutTemplate";
import { Button } from "primereact/button";
import * as Yup from "yup";
import { useDispatch } from "react-redux";
import { Dropdown } from "primereact/dropdown";
import { InputText } from "primereact/inputtext";
import { handleGetRequest } from "../../../services/GetTemplate";

const AddEditUser = ({ editable, userRowData, getUser, setEditable, isview, setIsView }) => {
    const [loadingIcon, setloadingIcon] = useState("pi pi-save");
    const [location, setLocation] = useState([]);
    const [role, setRole] = useState([]);
    const dispatch = useDispatch();
    useEffect(() => {
        if (userRowData !== undefined || userRowData !== null || userRowData !== "") {
            Object.keys(userRowData).forEach((key) => {
                if (formik.initialValues.hasOwnProperty(key)) {
                    formik.setFieldValue(key, userRowData[key]);
                }
                formik.setFieldValue("locationId", userRowData?.tblLocation?.locationId?.toString());
                formik.setFieldValue("roleId", userRowData?.tblRole?.roleId?.toString());
            });
        }
    }, [userRowData, editable, isview]); // eslint-disable-line
    console.log("row data User add edit", userRowData);

    useEffect(() => {
        handleGetRequest("js_hr_um/lov/lovLocation", 8083).then((response) => {
            setLocation(Array.isArray(response?.data) ? response.data : []);
        });
    }, []);
    useEffect(() => {
        handleGetRequest("js_hr_um/lov/lovRole", 8083).then((response) => {
            setRole(Array.isArray(response?.data) ? response.data : []);
        });
    }, []);

    const menuTypeOptions = [
        { label: "Yes", value: "Y" },
        { label: "No", value: "N" },
    ];
    const internalExternalOptions = [
        { label: "Internal", value: "L" },
        { label: "External", value: "R" },
    ];

    useEffect(() => {
        if (!editable) {
            formik.resetForm();
        }
    }, [editable]); // eslint-disable-line

    const validationSchema = Yup.object().shape({
        isActive: Yup.mixed().required("This field is required."),
        internalExternal: Yup.mixed().required("This field is required."),
        // locationId: Yup.mixed().required("This field is required."),
        // roleId: Yup.mixed().required("This field is required."),
        usercode: Yup.string().required("This field is required."),
        username: Yup.string().required("This field is required."),
        empCode: Yup.string().required("This field is required."),
        employeeNo: Yup.string().required("This field is required."),
        remarks: Yup.string().required("This field is required."),
        email: Yup.string().required("This field is required."),
        mobileno: Yup.string().required("This field is required."),
    });

    const formik = useFormik({
        validationSchema: validationSchema,
        initialValues: {
            isActive: "",
            usercode: "",
            username: "",
            empCode: "",
            employeeNo: "",
            remarks: "",
            locationId: "",
            email: "",
            mobileno: "",
            internalExternal: "",
            roleId: "",
        },
        onSubmit: async (data) => {
            setloadingIcon("pi pi-spin pi-spinner");
            data["userId"] = userRowData?.userId;
            if (editable === true) {
                const res = await dispatch(handlePutRequest(data, `js_hr_um/userManagement/updateUser`, true, true, 8083));
                if (res?.responsecode === 1) {
                    formik.resetForm();
                    setEditable(false);
                    await getUser();
                }
            } else {
                const res = await dispatch(handlePostRequest(data, "js_hr_um/userManagement/saveUser", true, true, 8083));
                if (res?.responsecode === 1) {
                    formik.resetForm();
                    await getUser();
                }
            }

            setloadingIcon("pi pi-save");
        },
    });
    const cancelHandler = (e) => {
        e.preventDefault();
        formik.resetForm();
        setIsView(false);
        setEditable(false);
    };
    const isFormFieldValid = (name) => !!(formik.touched[name] && formik.errors[name]);
    const getFormErrorMessage = (name) => {
        return isFormFieldValid(name) && <small className="p-error">{formik.errors[name]}</small>;
    };

    return (
        <>
            <div className="card Card__Round">
                <div className="Form__Header">
                    <h1>{isview ? "View User" : editable ? "Edit User" : "Add User"}</h1>
                </div>
                <form onSubmit={formik.handleSubmit} className="p-fluid p-mt-2">
                    <div className="fluid formgrid grid">
                        <div className="field col-12 md:col-3">
                            <div className="field">
                                <label htmlFor="usercode" className={classNames({ "p-error": isFormFieldValid("usercode") }, "Label__Text")}>
                                    User Code<span className="Label__Required">*</span>
                                </label>
                                <InputText id="usercode" placeholder="Enter User Code" name="usercode" value={formik.values.usercode} onChange={formik.handleChange} className={classNames({ "p-invalid": isFormFieldValid("usercode") }, "Input__Round")} />
                                {getFormErrorMessage("usercode")}
                            </div>
                        </div>
                        <div className="field col-12 md:col-3">
                            <div className="field">
                                <label htmlFor="username" className={classNames({ "p-error": isFormFieldValid("username") }, "Label__Text")}>
                                    User Name<span className="Label__Required">*</span>
                                </label>
                                <InputText id="username" placeholder="Enter User Name" name="username" value={formik.values.username} onChange={formik.handleChange} className={classNames({ "p-invalid": isFormFieldValid("username") }, "Input__Round")} />
                                {getFormErrorMessage("username")}
                            </div>
                        </div>
                        <div className="field col-12 md:col-3">
                            <div className="field">
                                <label htmlFor="empCode" className={classNames({ "p-error": isFormFieldValid("empCode") }, "Label__Text")}>
                                    Employee Code<span className="Label__Required">*</span>
                                </label>
                                <InputText id="empCode" placeholder="Enter Emolyee Code" name="empCode" value={formik.values.empCode} onChange={formik.handleChange} className={classNames({ "p-invalid": isFormFieldValid("empCode") }, "Input__Round")} />
                                {getFormErrorMessage("empCode")}
                            </div>
                        </div>
                        <div className="field col-12 md:col-3">
                            <div className="field">
                                <label htmlFor="employeeNo" className={classNames({ "p-error": isFormFieldValid("employeeNo") }, "Label__Text")}>
                                    Employee No<span className="Label__Required">*</span>
                                </label>
                                <InputText id="employeeNo" placeholder="Enter Employee Number" name="employeeNo" value={formik.values.employeeNo} onChange={formik.handleChange} className={classNames({ "p-invalid": isFormFieldValid("employeeNo") }, "Input__Round")} />
                                {getFormErrorMessage("employeeNo")}
                            </div>
                        </div>
                        <div className="field col-12 md:col-3">
                            <div className="field">
                                <label htmlFor="isActive" className={classNames({ "p-error": isFormFieldValid("isActive") }, "Label__Text")}>
                                    Is Active<span className="Label__Required">*</span>
                                </label>
                                <Dropdown id="isActive" placeholder="Select Status" options={menuTypeOptions} optionLabel="label" name="isActive" value={formik.values.isActive} onChange={formik.handleChange} className={classNames({ "p-invalid": isFormFieldValid("isActive") }, "Dropdown__Round")} />
                                {getFormErrorMessage("isActive")}
                            </div>
                        </div>
                        <div className="field col-12 md:col-3">
                            <div className="field">
                                <label htmlFor="remarks" className={classNames({ "p-error": isFormFieldValid("remarks") }, "Label__Text")}>
                                    Remarks<span className="Label__Required">*</span>
                                </label>
                                <InputText id="remarks" placeholder="Enter Remarks" name="remarks" value={formik.values.remarks} onChange={formik.handleChange} className={classNames({ "p-invalid": isFormFieldValid("remarks") }, "Input__Round")} />
                                {getFormErrorMessage("remarks")}
                            </div>
                        </div>
                        <div className="field col-12 md:col-3">
                            <div className="field">
                                <label htmlFor="locationId" className={classNames({ "p-error": isFormFieldValid("locationId") }, "Label__Text")}>
                                    Location<span className="Label__Required">*</span>
                                </label>
                                <Dropdown
                                    id="locationId"
                                    placeholder="Select Location"
                                    options={location}
                                    optionLabel="description"
                                    optionValue="id"
                                    name="locationId"
                                    value={formik.values.locationId}
                                    onChange={formik.handleChange}
                                    className={classNames({ "p-invalid": isFormFieldValid("locationId") }, "Dropdown__Round")}
                                />
                                {getFormErrorMessage("locationId")}
                            </div>
                        </div>
                        <div className="field col-12 md:col-3">
                            <div className="field">
                                <label htmlFor="roleId" className={classNames({ "p-error": isFormFieldValid("roleId") }, "Label__Text")}>
                                    Role<span className="Label__Required">*</span>
                                </label>
                                <Dropdown
                                    id="roleId"
                                    placeholder="Select role"
                                    optionValue="id"
                                    options={role}
                                    optionLabel="description"
                                    name="roleId"
                                    value={formik.values.roleId}
                                    onChange={formik.handleChange}
                                    className={classNames({ "p-invalid": isFormFieldValid("roleId") }, "Dropdown__Round")}
                                />
                                {getFormErrorMessage("roleId")}
                            </div>
                        </div>
                        <div className="field col-12 md:col-3">
                            <div className="field">
                                <label htmlFor="email" className={classNames({ "p-error": isFormFieldValid("email") }, "Label__Text")}>
                                    Email <span className="Label__Required">*</span>
                                </label>
                                <InputText id="email" placeholder="Enter Email" name="email" value={formik.values.email} onChange={formik.handleChange} className={classNames({ "p-invalid": isFormFieldValid("email") }, "Input__Round")} />
                                {getFormErrorMessage("email")}
                            </div>
                        </div>
                        <div className="field col-12 md:col-3">
                            <div className="field">
                                <label htmlFor="mobileno" className={classNames({ "p-error": isFormFieldValid("mobileno") }, "Label__Text")}>
                                    Mobile <span className="Label__Required">*</span>
                                </label>
                                <InputText id="mobileno" placeholder="Enter Length" name="mobileno" value={formik.values.mobileno} onChange={formik.handleChange} className={classNames({ "p-invalid": isFormFieldValid("mobileno") }, "Input__Round")} />
                                {getFormErrorMessage("mobileno")}
                            </div>
                        </div>

                        <div className="field col-12 md:col-3">
                            <div className="field">
                                <label htmlFor="internalExternal" className={classNames({ "p-error": isFormFieldValid("internalExternal") }, "Label__Text")}>
                                    Internal/External<span className="Label__Required">*</span>
                                </label>
                                <Dropdown
                                    id="internalExternal"
                                    placeholder="Select Internal/External"
                                    options={internalExternalOptions}
                                    optionLabel="label"
                                    name="internalExternal"
                                    value={formik.values.internalExternal}
                                    onChange={formik.handleChange}
                                    className={classNames({ "p-invalid": isFormFieldValid("internalExternal") }, "Dropdown__Round")}
                                />
                                {getFormErrorMessage("internalExternal")}
                            </div>
                        </div>
                    </div>
                    {
                        <div className="Down__Btn">
                            <Button iconPos="right" label="Cancel" className="Btn__Transparent" onClick={(e) => cancelHandler(e)} />
                            {isview ? "" : <Button icon={loadingIcon || ""} iconPos="right" label={editable ? "Update" : "Add"} className="Btn__Dark" />}
                        </div>
                    }
                </form>
            </div>
        </>
    );
};

export default AddEditUser;

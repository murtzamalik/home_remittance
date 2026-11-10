import React, { useState, useEffect } from "react";
import { InputTextarea } from "primereact/inputtextarea";
import { useFormik } from "formik";
import classNames from "classnames";
import { handlePostRequest } from "../../../services/PostTemplate";
import { handlePutRequest } from "../../../services/PutTemplate";
import { Button } from "primereact/button";
import { useDispatch } from "react-redux";
import * as Yup from "yup";

const AddEditRole = ({ onHide, editable, roleRowData, getRoles }) => {
    const [loading, setloading] = useState(false);
    const [loadingIcon, setloadingIcon] = useState("pi pi-save");
    const roleId = roleRowData.roleId;
    const dispatch = useDispatch();
    useEffect(() => {
        if (roleRowData !== undefined || roleRowData !== null) {
            formik.setFieldValue("roleDescr", roleRowData.roleDescr);
        }
    }, [roleRowData]); // eslint-disable-line

    const validationSchema = Yup.object().shape({
        roleDescr: Yup.string().required("This field is required."),
    });

    const formik = useFormik({
        validationSchema: validationSchema,
        initialValues: {
            roleDescr: "",
        },
        onSubmit: async (data) => {
            setloading(true);
            data["roleId"] = roleId;
            setloadingIcon("pi pi-spin pi-spinner");
            if (editable === true) {
                await dispatch(handlePutRequest(data, `js_hr_um/userManagement/updateRole`, true, true, 8083));
            } else {
                await dispatch(handlePostRequest(data, "js_hr_um/userManagement/saveRole", true, true, 8083));
            }
            await getRoles();
            onHide();
            setloading(false);
            setloadingIcon("pi pi-save");
            formik.resetForm();
        },
    });
    const isFormFieldValid = (name) => !!(formik.touched[name] && formik.errors[name]);
    const getFormErrorMessage = (name) => {
        return isFormFieldValid(name) && <small className="p-error">{formik.errors[name]}</small>;
    };

    return (
        <>
            <div className="card Card__Round">
                <div className="Form__Header">
                    <h1>{editable ? "Edit Role" : "Add Role"}</h1>
                </div>
                <form onSubmit={formik.handleSubmit} className="p-fluid p-mt-2">
                    <div className="p-field p-col-12 p-md-12">
                        <div className="p-field">
                            <label htmlFor="roleDescr" className={classNames({ "p-error": isFormFieldValid("roleDescr") }, "Label__Text")}>
                                Description<span className="Label__Required">*</span>
                            </label>
                            <InputTextarea placeholder="Add Description" id="roleDescr" name="roleDescr" value={formik.values.roleDescr} rows={5} cols={10} onChange={formik.handleChange} className={classNames({ "p-invalid": isFormFieldValid("roleDescr") }, "TextArea__Round")} />
                            {getFormErrorMessage("roleDescr")}
                        </div>
                    </div>
                    <div className="Down__Btn">
                        <Button disabled={loading} icon={loadingIcon || ""} iconPos="right" label={editable ? "Update" : "Add"} className="Btn__Dark" />
                    </div>
                </form>
            </div>
        </>
    );
};

export default AddEditRole;

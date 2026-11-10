import React, { useState, useEffect } from "react";
import { useFormik } from "formik";
import classNames from "classnames";
import { handlePostRequest } from "../../../services/PostTemplate";
import { handleGetRequest } from "../../../services/GetTemplate";
import { Button } from "primereact/button";
import { Dropdown } from "primereact/dropdown";
import * as Yup from "yup";
import { useDispatch } from "react-redux";
import { handlePutRequest } from "../../../services/PutTemplate";

const AddEditModule = ({ onHide, editable, roleRightsRowData, getRoleRights, roleId }) => {
    const [loadingIcon, setloadingIcon] = useState("pi pi-save");
    const [menuOptions, setmenuOptions] = useState([]);
    const dispatch = useDispatch();

    useEffect(() => {
        const rowData = JSON.parse(JSON.stringify(roleRightsRowData));
        const keys = Object.keys(rowData);
        keys.forEach((item) => {
            formik.setFieldValue(`${item}`, rowData[item]);
        });
    }, [roleRightsRowData]); // eslint-disable-line

    useEffect(() => {
        handleGetRequest("js_hr_um/lov/lovMenu", 8083).then((res) => {
            setmenuOptions(Array.isArray(res?.data) ? res.data : []);
        });
    }, []); // eslint-disable-line

    const menuTypeOptions = [
        { label: "Yes", value: "Y" },
        { label: "No", value: "N" },
    ];

    useEffect(() => {
        formik.setFieldValue("moduleDescr", roleRightsRowData.moduleDescr);
    }, [roleRightsRowData]); // eslint-disable-line

    const validationSchema = Yup.object().shape({
        menuId: Yup.mixed().required("This field is required."),
        authorizeAllowed: Yup.string().required("This field is required."),
        hideYn: Yup.string().required("This field is required."),
        insertAllowed: Yup.string().required("This field is required."),
        selectAllowed: Yup.string().required("This field is required."),
        updateAllowed: Yup.mixed().required("This field is required."),
    });

    const formik = useFormik({
        validationSchema: validationSchema,
        initialValues: {
            authorizeAllowed: "",
            hideYn: "",
            insertAllowed: "",
            selectAllowed: "",
            updateAllowed: "",
            menuId: "",
        },

        onSubmit: async (data) => {
            data["roleId"] = roleId;
            data["menuId"] = data?.menuId?.id;
            setloadingIcon("pi pi-spin pi-spinner");
            if (editable) {
                const res = await dispatch(handlePutRequest(data, "js_hr_um/userManagement/updateRoleRight", true, true, 8083));
                if (res?.responsecode === 1) {
                    await getRoleRights();
                    onHide();
                    formik.resetForm();
                }
            } else {
                const res = await dispatch(handlePostRequest(data, "js_hr_um/userManagement/saveRoleRights", true, true, 8083));
                if (res?.responsecode === 1) {
                    await getRoleRights();
                    onHide();
                    formik.resetForm();
                }
            }

            setloadingIcon("pi pi-save");
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
                    <h1>{editable ? "Edit Roles Right" : "Add Roles Right "} </h1>
                </div>
                <form onSubmit={formik.handleSubmit} className="p-fluid p-mt-2">
                    <div className="fluid formgrid grid">
                        <div className="p-field p-col-12 md:col-4">
                            <div className="p-field">
                                <label htmlFor="menuId" className={classNames({ "p-error": isFormFieldValid("menuId") }, "Label__Text")}>
                                    Menu<span className="Label__Required">*</span>
                                </label>
                                <Dropdown id="menuId" placeholder="" options={menuOptions} optionLabel="description" name="menuId" value={formik.values.menuId} onChange={formik.handleChange} className={classNames({ "p-invalid": isFormFieldValid("menuId") }, "Dropdown__Round")} />
                                {getFormErrorMessage("menuId")}
                            </div>
                        </div>
                        <div className="p-field p-col-12 md:col-4">
                            <div className="p-field">
                                <label htmlFor="authorizeAllowed" className={classNames({ "p-error": isFormFieldValid("authorizeAllowed") }, "Label__Text")}>
                                    Authorize Allowed<span className="Label__Required">*</span>
                                </label>
                                <Dropdown
                                    id="authorizeAllowed"
                                    placeholder=""
                                    options={menuTypeOptions}
                                    optionLabel="label"
                                    name="authorizeAllowed"
                                    value={formik.values.authorizeAllowed}
                                    onChange={formik.handleChange}
                                    className={classNames({ "p-invalid": isFormFieldValid("authorizeAllowed") }, "Dropdown__Round")}
                                />
                                {getFormErrorMessage("authorizeAllowed")}
                            </div>
                        </div>
                        <div className="p-field p-col-12 md:col-4">
                            <div className="p-field">
                                <label htmlFor="hideYn" className={classNames({ "p-error": isFormFieldValid("hideYn") }, "Label__Text")}>
                                    Hidden Allowed<span className="Label__Required">*</span>
                                </label>
                                <Dropdown id="hideYn" placeholder="" options={menuTypeOptions} optionLabel="label" name="hideYn" value={formik.values.hideYn} onChange={formik.handleChange} className={classNames({ "p-invalid": isFormFieldValid("hideYn") }, "Dropdown__Round")} />
                                {getFormErrorMessage("hideYn")}
                            </div>
                        </div>
                        <div className="p-field p-col-12 md:col-4">
                            <div className="p-field">
                                <label htmlFor="insertAllowed" className={classNames({ "p-error": isFormFieldValid("insertAllowed") }, "Label__Text")}>
                                    Insert Allowed<span className="Label__Required">*</span>
                                </label>
                                <Dropdown
                                    id="insertAllowed"
                                    placeholder=""
                                    options={menuTypeOptions}
                                    optionLabel="label"
                                    name="insertAllowed"
                                    value={formik.values.insertAllowed}
                                    onChange={formik.handleChange}
                                    className={classNames({ "p-invalid": isFormFieldValid("insertAllowed") }, "Dropdown__Round")}
                                />
                                {getFormErrorMessage("insertAllowed")}
                            </div>
                        </div>
                        <div className="p-field p-col-12 md:col-4">
                            <div className="p-field">
                                <label htmlFor="selectAllowed" className={classNames({ "p-error": isFormFieldValid("selectAllowed") }, "Label__Text")}>
                                    Select Allowed<span className="Label__Required">*</span>
                                </label>
                                <Dropdown
                                    id="selectAllowed"
                                    placeholder=""
                                    options={menuTypeOptions}
                                    optionLabel="label"
                                    name="selectAllowed"
                                    value={formik.values.selectAllowed}
                                    onChange={formik.handleChange}
                                    className={classNames({ "p-invalid": isFormFieldValid("selectAllowed") }, "Dropdown__Round")}
                                />
                                {getFormErrorMessage("selectAllowed")}
                            </div>
                        </div>
                        <div className="p-field p-col-12 md:col-4">
                            <div className="p-field">
                                <label htmlFor="updateAllowed" className={classNames({ "p-error": isFormFieldValid("updateAllowed") }, "Label__Text")}>
                                    Update Allowed<span className="Label__Required">*</span>
                                </label>
                                <Dropdown
                                    id="updateAllowed"
                                    placeholder=""
                                    options={menuTypeOptions}
                                    optionLabel="label"
                                    name="updateAllowed"
                                    value={formik.values.updateAllowed}
                                    onChange={formik.handleChange}
                                    className={classNames({ "p-invalid": isFormFieldValid("updateAllowed") }, "Dropdown__Round")}
                                />
                                {getFormErrorMessage("updateAllowed")}
                            </div>
                        </div>
                    </div>
                    <div className="Down__Btn">
                        <Button label="Cancel" className="Btn__Transparent mt-5" onClick={onHide} />
                        <Button icon={loadingIcon || ""} iconPos="right" label={editable ? "Edit" : "Add"} className="Btn__Dark" />
                    </div>
                </form>
            </div>
        </>
    );
};

export default AddEditModule;

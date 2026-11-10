import React, { useState, useEffect } from "react";
import { InputTextarea } from "primereact/inputtextarea";
import { useFormik } from "formik";
import classNames from "classnames";
import { handlePostRequest } from "../../../services/PostTemplate";
import { handlePutRequest } from "../../../services/PutTemplate";
import { Button } from "primereact/button";
import { InputText } from "primereact/inputtext";
import { Dropdown } from "primereact/dropdown";
import * as Yup from "yup";
import { useDispatch } from "react-redux";

const AddEditMenu = ({ onHide, editable, menuRowData, moduleId, getMenu }) => {
    const [loadingIcon, setloadingIcon] = useState("pi pi-save");

    const dispatch = useDispatch();

    useEffect(() => {
        const rowData = JSON.parse(JSON.stringify(menuRowData));
        const keys = Object.keys(rowData);
        keys.forEach((item) => {
            formik.setFieldValue(`${item}`, rowData[item]);
        });
    }, [menuRowData]); // eslint-disable-line

    useEffect(() => {
        if (!editable) {
            formik.resetForm();
        }
    }, [editable]); // eslint-disable-line

    const menuTypeOptions = [
        { label: "form", value: "F" },
        { label: "page", value: "P" },
    ];

    const validationSchema = Yup.object().shape({
        menuDescription: Yup.string().required("This field is required."),
        iconName: Yup.string().required("This field is required."),
        iconPath: Yup.string().required("This field is required."),
        menuCode: Yup.string().required("This field is required."),
        menuPath: Yup.string().required("This field is required."),
        menuType: Yup.mixed().required("This field is required."),
    });

    const formik = useFormik({
        validationSchema: validationSchema,
        initialValues: {
            menuDescription: "",
            iconName: "",
            iconPath: "",
            menuCode: "",
            menuPath: "",
            menuType: "",
        },

        onSubmit: async (data) => {
            data["moduleId"] = moduleId;
            data["menuType"] = data?.menuType;
            setloadingIcon("pi pi-spin pi-spinner");

            if (editable === true) {
                const res = await dispatch(handlePutRequest(data, `js_hr_um/userManagement/updateMenu`, true, true, 8083));
                if (res?.responsecode === 1) {
                    onHide();
                    formik.resetForm();
                }
            } else {
                const res = await dispatch(handlePostRequest(data, "js_hr_um/userManagement/saveMenu", true, true, 8083));
                if (res?.responsecode === 1) {
                    onHide();
                    formik.resetForm();
                }
            }
            await getMenu();
            // onHide();
            setloadingIcon("pi pi-save");
            // formik.resetForm();
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
                    <h1>{editable ? "Edit Menu" : "Add Menu"}</h1>
                </div>
                <form onSubmit={formik.handleSubmit} className="p-fluid p-mt-2">
                    <div className="p-field p-col-12 md:col-12">
                        <div className="p-field">
                            <label htmlFor="description" className={classNames({ "p-error": isFormFieldValid("description") }, "Label__Text")}>
                                Description<span className="Label__Required">*</span>
                            </label>
                            <InputTextarea
                                placeholder="Add Description"
                                id="menuDescription"
                                name="menuDescription"
                                value={formik?.values?.menuDescription?.replace(/\s\s+/g, " ")}
                                rows={5}
                                cols={10}
                                onChange={formik.handleChange}
                                className={classNames({ "p-invalid": isFormFieldValid("menuDescription") }, "TextArea__Round")}
                            />
                            {getFormErrorMessage("menuDescription")}
                        </div>
                        <div className="p-fluid p-formgrid p-grid">
                            <div className="p-field p-col-12 p-md-4">
                                <div className="p-field">
                                    <label htmlFor="iconName" className={classNames({ "p-error": isFormFieldValid("iconName") }, "Label__Text")}>
                                        Icon Name<span className="Label__Required">*</span>
                                    </label>
                                    <InputText id="iconName" placeholder="" name="iconName" value={formik.values.iconName} onChange={formik.handleChange} className={classNames({ "p-invalid": isFormFieldValid("iconName") }, "Input__Round")} />

                                    {getFormErrorMessage("iconName")}
                                </div>
                            </div>
                            <div className="p-field p-col-12 p-md-4">
                                <div className="p-field">
                                    <label htmlFor="iconPath" className={classNames({ "p-error": isFormFieldValid("iconPath") }, "Label__Text")}>
                                        Icon Path<span className="Label__Required">*</span>
                                    </label>
                                    <InputText id="iconPath" placeholder="" name="iconPath" value={formik.values.iconPath} onChange={formik.handleChange} className={classNames({ "p-invalid": isFormFieldValid("iconPath") }, "Input__Round")} />

                                    {getFormErrorMessage("iconPath")}
                                </div>
                            </div>
                            <div className="p-field p-col-12 p-md-4">
                                <div className="p-field">
                                    <label htmlFor="menuCode" className={classNames({ "p-error": isFormFieldValid("menuCode") }, "Label__Text")}>
                                        Menu Code<span className="Label__Required">*</span>
                                    </label>
                                    <InputText id="menuCode" placeholder="" name="menuCode" value={formik.values.menuCode} onChange={formik.handleChange} className={classNames({ "p-invalid": isFormFieldValid("menuCode") }, "Input__Round")} />

                                    {getFormErrorMessage("menuCode")}
                                </div>
                            </div>
                            <div className="p-field p-col-12 p-md-4">
                                <div className="p-field">
                                    <label htmlFor="menuPath" className={classNames({ "p-error": isFormFieldValid("menuPath") }, "Label__Text")}>
                                        Menu Path<span className="Label__Required">*</span>
                                    </label>
                                    <InputText id="menuPath" placeholder="" name="menuPath" value={formik.values.menuPath} onChange={formik.handleChange} className={classNames({ "p-invalid": isFormFieldValid("menuPath") }, "Input__Round")} />

                                    {getFormErrorMessage("menuPath")}
                                </div>
                            </div>
                            <div className="p-field p-col-12 p-md-4">
                                <div className="p-field">
                                    <label htmlFor="menuType" className={classNames({ "p-error": isFormFieldValid("menuType") }, "Label__Text")}>
                                        Menu Type<span className="Label__Required">*</span>
                                    </label>
                                    <Dropdown id="menuType" placeholder="" options={menuTypeOptions} optionLabel="label" name="menuType" value={formik.values.menuType} onChange={formik.handleChange} className={classNames({ "p-invalid": isFormFieldValid("menuType") }, "Dropdown__Round")} />
                                    {getFormErrorMessage("menuType")}
                                </div>
                            </div>
                        </div>
                    </div>
                    <div className="Down__Btn">
                        <Button icon={loadingIcon || ""} iconPos="right" label={editable ? "Update" : "Add"} className="Btn__Dark" />
                    </div>
                </form>
            </div>
        </>
    );
};

export default AddEditMenu;

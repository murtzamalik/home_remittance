import React, { useState, useEffect, useCallback } from "react";
import { InputText } from "primereact/inputtext";
import { RadioButton } from "primereact/radiobutton";
import { Button } from "primereact/button";
import { useFormik } from "formik";
import classNames from "classnames";
import { useDispatch, useSelector } from "react-redux";
import { handlePostRequest } from "../../services/PostTemplate";
import { handlePutRequest } from "../../services/PutTemplate";
import { SetEditParserFlagAction, GetParserHeadDetailsAction, SetParserHeadDetailsToNullAction } from "../../.././src/redux/actions/parserAction";
import ParserHeadDetails from "./ParserHeadDetails";
import ParserHead from "./ParserHead";
import AddEditParserHeadDetails from "./AddEditParserHeadDetails";
import * as Yup from "yup";

function AddEditParserHead() {
    const [loadingIcon, setloadingIcon] = useState("pi pi-save");
    const [parserHeadId, setparserHeadId] = useState("");

    const dispatch = useDispatch();
    const validationSchema = Yup.object().shape({
        code: Yup.string()
            .required("This field is required.")
            .matches(/^[A-Za-z0-9 ]+$/, "Special Character are not allowed"),
        description: Yup.string().required("This field is required."),
    });
    const formik = useFormik({
        validationSchema: validationSchema,
        initialValues: {
            code: "",
            description: "",
            isActive: "N",
        },

        onSubmit: async (data) => {
            setloadingIcon("pi pi-spin pi-spinner");

            if (editFlag__Store) {
                data["parserHeadId"] = formData__Store?.parserHeadId;
                await dispatch(handlePutRequest(data, "js-hr-fi/fi/updateParserHead", true, true, 8082));
                await dispatch(GetParserHeadDetailsAction("js-hr-fi/fi/getAllParser"));
                dispatch(SetEditParserFlagAction(false));
                dispatch(SetParserHeadDetailsToNullAction());
            } else {
                const res = await dispatch(handlePostRequest(data, "js-hr-fi/fi/saveParserHead", true, true, 8082));
                dispatch(SetParserHeadDetailsToNullAction());
                setparserHeadId(res?.data?.parserHeadId);
                dispatch(GetParserHeadDetailsAction("js-hr-fi/fi/getAllParser", 8082));
            }
            setloadingIcon("pi pi-save");
        },
    });
    const isFormFieldValid = (name) => !!(formik.touched[name] && formik.errors[name]);
    const getFormErrorMessage = (name) => {
        return isFormFieldValid(name) && <small className="p-error">{formik.errors[name]}</small>;
    };

    const formData__Store = useSelector((state) => state.parserSlice?.parserHeadDetailsForm);
    const editFlag__Store = useSelector((state) => state.parserSlice?.parserHeadEditFlag);

    const fillParserHeadForm = () => {
        formik.setFieldValue("code", formData__Store?.code?.toString());
        formik.setFieldValue("description", formData__Store?.description?.toString());
        formik.setFieldValue("isActive", formData__Store?.isActive);
    };

    useEffect(() => {
        if (editFlag__Store) {
            fillParserHeadForm();
        }
    }, [editFlag__Store, formData__Store]); // eslint-disable-line

    const handleCancel = (e) => {
        e.preventDefault();
        formik.resetForm();
        dispatch(SetEditParserFlagAction(false));
        dispatch(SetParserHeadDetailsToNullAction());
    };

    const handleParserHeadId = () => {
        setparserHeadId("");
    };

    return (
        <div className="card Card__Round">
            <div className="Form__Header">
                <h1>Parser Head</h1>
            </div>
            <form onSubmit={formik.handleSubmit} className="fluid mt-2">
                <div className="fluid formgrid grid">
                    <div className="field col-12 md:col-3">
                        <div className="field">
                            <label htmlFor="code" className={classNames({ "p-error": isFormFieldValid("code") }, "Label__Text")}>
                                Code<span className="Label__Required">*</span>
                            </label>
                            <InputText id="code" placeholder="Enter Code" name="code" value={formik.values.code} onChange={formik.handleChange} className={classNames({ "p-invalid": isFormFieldValid("code") }, "Input__Round")} />

                            {getFormErrorMessage("code")}
                        </div>
                    </div>
                    <div className="field col-12 md:col-3">
                        <div className="field">
                            <label htmlFor="description" className={classNames({ "p-error": isFormFieldValid("description") }, "Label__Text")}>
                                Description<span className="Label__Required">*</span>
                            </label>
                            <InputText id="description" placeholder="Enter Description" name="description" value={formik.values.description} onChange={formik.handleChange} className={classNames({ "p-invalid": isFormFieldValid("description") }, "Input__Round")} />

                            {getFormErrorMessage("description")}
                        </div>
                    </div>
                    <div className="field col-12 md:col-3">
                        <label htmlFor="isActive" className="Label__Text">
                            Active
                        </label>
                        <div className="Radio__Btn">
                            <div className="field-radiobutton">
                                <RadioButton inputId="isActive" name="isActive" value={"N"} onChange={formik.handleChange} checked={formik.values.isActive === "N"} />
                                <label htmlFor="isActive">No</label>
                            </div>
                            <div className="field-radiobutton">
                                <RadioButton inputId="isActive" name="isActive" value={"Y"} onChange={formik.handleChange} checked={formik.values.isActive === "Y"} />
                                <label htmlFor="isActive">Yes</label>
                            </div>
                        </div>
                    </div>
                </div>

                <div className="Down__Btn">
                    <Button label="Cancel" onClick={(e) => handleCancel(e)} className="Btn__Transparent" />
                    <Button type="submit" icon={loadingIcon || ""} iconPos="right" label={editFlag__Store ? "Edit" : "Add"} className="Btn__Dark" />
                </div>
            </form>
            <AddEditParserHeadDetails parserHeadId={parserHeadId} handleParserHeadId={handleParserHeadId} />
            <ParserHeadDetails />
            <ParserHead />
        </div>
    );
}

export default AddEditParserHead;

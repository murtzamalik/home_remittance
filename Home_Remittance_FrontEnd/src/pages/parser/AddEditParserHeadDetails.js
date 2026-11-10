import React, { useState, useEffect } from "react";
import { InputText } from "primereact/inputtext";
import { RadioButton } from "primereact/radiobutton";
import { Button } from "primereact/button";
import { useFormik } from "formik";
import classNames from "classnames";
import { useSelector, useDispatch } from "react-redux";
import { GetParserHeadDetailsAction, GetParserHeadFurtherDetailsAction, SetEditParserHeadDetailsFlagAction } from "../../redux/actions/parserAction";
import { handlePostRequest } from "../.././services/PostTemplate";
import { Checkbox } from "primereact/checkbox";
import { Dropdown } from "primereact/dropdown";
import * as Yup from "yup";
import { handlePutRequest } from "../.././services/PutTemplate";
import { handleGetRequest } from "../../services/GetTemplate";

function AddEditParserHeadDetails({ parserHeadId, handleParserHeadId, parserDetailId }) {
    const [loadingIcon, setloadingIcon] = useState("pi pi-save");
    const [transFiledOptions, settransFiledOptions] = useState([]);
    const dispatch = useDispatch();

    const parserDetailId__Store = useSelector((state) => state.parserSlice?.parserHeadDetailsFormData?.parserDetailId); //remove parserHeadDetailsFormData
    const parseHeadFurtherDetails = useSelector((state) => state.parserSlice?.parseHeadFurtherDetails);
    const formData__Store = useSelector((state) => state.parserSlice?.parserHeadDetailsFormData);
    const editFlag__Store = useSelector((state) => state.parserSlice?.parserHeadDetailsEditFlag);
    const parserHeadIdd = useSelector((state) => state.parserSlice?.parseHeadId);

    // setparserHeadDetails(parserHeadIdd);
    useEffect(() => {
        if ((parserDetailId !== undefined && parserDetailId !== null && parserDetailId !== "") || (parserDetailId__Store !== "" && parserDetailId__Store !== undefined && parserDetailId__Store !== null)) {
        } else {
        }
    }, [parserDetailId, parserDetailId__Store]);

    useEffect(() => {
        handleGetRequest("js-hr-fi/lov/lovTransFields", 8082).then((res) => {
            settransFiledOptions(Array.isArray(res?.data) ? res.data.map((item) => ({ name: item.code, id: item.id })) : []);
        });
    }, []); // eslint-disable-line

    useEffect(() => {
        if (editFlag__Store) {
            fillParserHeadDetailForm();
        }
    }, [editFlag__Store, formData__Store]); // eslint-disable-line

    const fillParserHeadDetailForm = () => {
        formik.setFieldValue("columnName", formData__Store?.columnName?.toString());
        const transFieldId = { name: formData__Store?.tblTransField?.name, id: formData__Store?.tblTransField?.transFieldsId?.toString() };
        formik.setFieldValue("fixedLength", formData__Store?.fixedLength?.toString() === "Y" ? true : false);
        formik.setFieldValue("maxLength", formData__Store?.maxLength?.toString());
        formik.setFieldValue("minLength", formData__Store?.minLength?.toString());
        formik.setFieldValue("isMandatory", formData__Store?.isMandatory);
        formik.setFieldValue("transFieldId", transFieldId);
        formik.setFieldValue("refFieldName", formData__Store?.refFieldName);
        formik.setFieldValue("sequence", formData__Store?.sequence);
    };
    const getParserFurtherDetails = async () => {
        await dispatch(GetParserHeadFurtherDetailsAction("js-hr-fi/fi/getParser/" + parserHeadIdd));
    };

    const validationSchema = Yup.object().shape({
        minLength: Yup.string().required("This field is required.").matches("^[0-9]*$", "Please enter numeric data").max(3, "Max length 3 allowed"),
        maxLength: Yup.string().required("This field is required.").matches("^[0-9]*$", "Please enter numeric data").max(3, "Max length 3 allowed"),
    });

    const formik = useFormik({
        validationSchema: validationSchema,
        initialValues: {
            isMandatory: "N",
            fixedLength: false,
            maxLength: "",
            minLength: "",
            refFieldName: "",
            transFieldId: "",
            sequence: "",
        },
        validate: (data) => {
            let errors = {};

            if (data.minLength) {
                if (parseInt(data.minLength) > parseInt(data.maxLength)) {
                    errors.minLength = "Minimum length should be less than to Maximum length";
                }
            }
            if (data?.sequence) {
                parseHeadFurtherDetails.filter((sequenceCheck) => {
                    return parseInt(sequenceCheck?.sequence) === parseInt(formik.values.sequence) ? (errors.sequence = "Sequence already exist") : "";
                });
            }

            return errors;
        },
        onSubmit: async (data) => {
            data["fixedLength"] = data.fixedLength ? "Y" : "N";
            setloadingIcon("pi pi-spin pi-spinner");
            if (editFlag__Store) {
                data["transFieldId"] = parseInt(data?.transFieldId?.id);
                data["maxLength"] = parseInt(data?.maxLength);
                data["minLength"] = parseInt(data?.minLength);
                if (parserHeadId !== "") data["parserDetailId"] = parserDetailId__Store;
                else data["parserDetailId"] = parseInt(parserDetailId__Store);
                const res = await dispatch(handlePutRequest(data, "js-hr-fi/fi/updateParserDetail", true, true, 8082));
                if (res?.responsecode === 1) {
                    formik.resetForm();
                    getParserFurtherDetails();
                }
                await dispatch(GetParserHeadDetailsAction("js-hr-fi/fi/getAllParser"));
                handleParserHeadId();
                dispatch(SetEditParserHeadDetailsFlagAction(false));
            } else {
                if (parserHeadId !== "") data["parserHeadId"] = parserHeadId;
                else data["parserHeadId"] = parserHeadIdd;
                data["transFieldId"] = parseInt(data?.transFieldId?.id);
                data["maxLength"] = parseInt(data?.maxLength);
                data["minLength"] = parseInt(data?.minLength);
                const res = await dispatch(handlePostRequest(data, "js-hr-fi/fi/saveParserDetails", true, true, 8082));
                if (res?.responsecode === 1) {
                    formik.resetForm();
                    await getParserFurtherDetails();
                }
                handleParserHeadId();
                dispatch(GetParserHeadDetailsAction("js-hr-fi/fi/getAllParser", 8082));
            }
            setloadingIcon("pi pi-save");
        },
    });
    const isFormFieldValid = (name) => !!(formik.touched[name] && formik.errors[name]);
    const getFormErrorMessage = (name) => {
        return isFormFieldValid(name) && <small className="p-error">{formik.errors[name]}</small>;
    };

    const handleCancel = (e) => {
        e.preventDefault();
        formik.resetForm();
        dispatch(SetEditParserHeadDetailsFlagAction(false));
    };

    return (
        <div className="card Card__Round">
            <div className="Form__Header">
                <h1>Parser Head Details</h1>
            </div>
            <form onSubmit={formik.handleSubmit} className="fluid mt-2">
                <div className="fluid formgrid grid">
                    <div className="field col-12 md:col-3">
                        <div className="field">
                            <label htmlFor="refFieldName" className={classNames({ "p-error": isFormFieldValid("refFieldName") }, "Label__Text")}>
                                Refernce field name<span className="Label__Required">*</span>
                            </label>
                            <InputText id="refFieldName" placeholder="Enter Length" name="refFieldName" value={formik.values.refFieldName} onChange={formik.handleChange} className={classNames({ "p-invalid": isFormFieldValid("refFieldName") }, "Input__Round")} />
                            {getFormErrorMessage("refFieldName")}
                        </div>
                    </div>
                    <div className="p-field p-col-12 p-md-3">
                        <div className="p-field">
                            <label htmlFor="transFieldId" className={classNames({ "p-error": isFormFieldValid("transFieldId") }, "Label__Text")}>
                                Trans Field<span className="Label__Required">*</span>
                            </label>
                            <Dropdown
                                id="transFieldId"
                                placeholder="Enter Product"
                                options={transFiledOptions}
                                optionLabel="name"
                                name="transFieldId"
                                value={formik.values.transFieldId}
                                onChange={formik.handleChange}
                                className={classNames({ "p-invalid": isFormFieldValid("transFieldId") }, "Dropdown__Round")}
                            />
                            {getFormErrorMessage("transFieldId")}
                        </div>
                    </div>
                    <div className="field col-12 md:col-3">
                        <div className="field">
                            <label htmlFor="fixedLength" className={classNames({ "p-error": isFormFieldValid("fixedLength") }, "Label__Text")}>
                                Fixed Length
                            </label>
                            <div className="field-checkbox">
                                <Checkbox inputId="fixedLength" name="fixedLength" value={formik.values.fixedLength} checked={formik.values.fixedLength} onChange={formik.handleChange} />
                            </div>
                            {getFormErrorMessage("fixedLength")}
                        </div>
                    </div>
                    <div className="field col-12 md:col-3">
                        <div className="field">
                            <label htmlFor="minLength" className={classNames({ "p-error": isFormFieldValid("minLength") }, "Label__Text")}>
                                Min Length<span className="Label__Required">*</span>
                            </label>
                            <InputText id="minLength" placeholder="Enter Length" name="minLength" value={formik.values.minLength} onChange={formik.handleChange} className={classNames({ "p-invalid": isFormFieldValid("minLength") }, "Input__Round")} />

                            {getFormErrorMessage("minLength")}
                        </div>
                    </div>
                    <div className="field col-12 md:col-3">
                        <div className="field">
                            <label htmlFor="maxLength" className={classNames({ "p-error": isFormFieldValid("maxLength") }, "Label__Text")}>
                                Max Length<span className="Label__Required">*</span>
                            </label>
                            <InputText id="maxLength" placeholder="Enter Length" name="maxLength" value={formik.values.maxLength} onChange={formik.handleChange} className={classNames({ "p-invalid": isFormFieldValid("maxLength") }, "Input__Round")} />

                            {getFormErrorMessage("maxLength")}
                        </div>
                    </div>

                    <div className="field col-12 md:col-3">
                        <label htmlFor="isMandatory" className="Label__Text">
                            Mandatory
                        </label>
                        <div className="Radio__Btn">
                            <div className="field-radiobutton">
                                <RadioButton inputId="isMandatory" name="isMandatory" value={"N"} onChange={formik.handleChange} checked={formik.values.isMandatory === "N"} />
                                <label htmlFor="isMandatory">No</label>
                            </div>
                            <div className="field-radiobutton">
                                <RadioButton inputId="isMandatory" name="isMandatory" value={"Y"} onChange={formik.handleChange} checked={formik.values.isMandatory === "Y"} />
                                <label htmlFor="isMandatory">Yes</label>
                            </div>
                        </div>
                    </div>
                    <div className="field col-12 md:col-3">
                        <div className="field">
                            <label htmlFor="sequence" className={classNames({ "p-error": isFormFieldValid("sequence") }, "Label__Text")}>
                                Sequence<span className="Label__Required">*</span>
                            </label>
                            <InputText id="sequence" placeholder="Enter Sequence" name="sequence" value={formik.values.sequence} onChange={formik.handleChange} className={classNames({ "p-invalid": isFormFieldValid("sequence") }, "Input__Round")} />
                            {getFormErrorMessage("sequence")}
                        </div>
                    </div>
                </div>

                <div className="Down__Btn">
                    <Button label="Cancel" onClick={(e) => handleCancel(e)} className="Btn__Transparent" />
                    <Button type="submit" icon={loadingIcon || ""} iconPos="right" label={editFlag__Store ? "Edit" : "Add"} className="Btn__Dark" />
                </div>
            </form>
        </div>
    );
}

export default AddEditParserHeadDetails;

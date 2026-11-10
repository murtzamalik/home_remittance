import React, { useState, useEffect } from "react";
import { Button } from "primereact/button";
import { Dropdown } from "primereact/dropdown";
import { InputText } from "primereact/inputtext";
import { handleGetRequest } from "../../../services/GetTemplate";

function AddEditGl({ type, isFormFieldValid, getFormErrorMessage, classNames, onChange, values, handleSubmit }) {
    const [accountTypeOptions, setaccountTypeOptions] = useState([]);

    useEffect(() => {
        handleGetRequest("js-hr-fi/lov/lovGlAccType", 8082).then((res) => {
            setaccountTypeOptions(Array.isArray(res?.data) ? res.data : []);
        });
    }, []); // eslint-disable-line

    const cancelHandler = (e) => {
        e.preventDefault();
    };
    const fcAllowedOptions = [
        { label: "Yes", value: "Y" },
        { label: "No", value: "N" },
    ];
    const defaultOptions = [
        { label: "Yes", value: "Y" },
        { label: "No", value: "N" },
    ];

    return (
        <div className="card Card__Round">
            <div className="Form__Header">
                <h1>{type} GL</h1>
            </div>
            <form className="fluid mt-2">
                <div className="fluid formgrid grid">
                    <div className="field col-12 md:col-4">
                        <div className="field">
                            <label htmlFor="glLkpGlAccountTypeId " className={classNames({ "p-error": isFormFieldValid("glLkpGlAccountTypeId ") }, "Label__Text")}>
                                Account Type<span className="Label__Required">*</span>
                            </label>
                            <Dropdown
                                id="glLkpGlAccountTypeId"
                                placeholder="Select Account type"
                                options={accountTypeOptions}
                                optionLabel="description"
                                name="glLkpGlAccountTypeId"
                                value={values.glLkpGlAccountTypeId}
                                onChange={onChange}
                                className={classNames({ "p-invalid": isFormFieldValid("glLkpGlAccountTypeId ") }, "Dropdown__Round")}
                            />
                            {getFormErrorMessage("glLkpGlAccountTypeId ")}
                        </div>
                    </div>
                    <div className="field col-12 md:col-4">
                        <div className="field">
                            <label htmlFor="glAccountCode" className={classNames({ "p-error": isFormFieldValid("glAccountCode") }, "Label__Text")}>
                                Account Code<span className="Label__Required">*</span>
                            </label>
                            <InputText id="glAccountCode" placeholder="Enter Account Code" name="glAccountCode" value={values.glAccountCode?.toString()?.trim()} onChange={onChange} className={classNames({ "p-invalid": isFormFieldValid("glAccountCode") }, "Input__Round")} />

                            {getFormErrorMessage("glAccountCode")}
                        </div>
                    </div>
                    <div className="field col-12 md:col-4">
                        <div className="field">
                            <label htmlFor="glAccountDescr" className={classNames({ "p-error": isFormFieldValid("glAccountDescr") }, "Label__Text")}>
                                Account Description<span className="Label__Required">*</span>
                            </label>
                            <InputText id="glAccountDescr" placeholder="Enter Account Description" name="glAccountDescr" value={values.glAccountDescr?.replace(/\s\s+/g, " ")} onChange={onChange} className={classNames({ "p-invalid": isFormFieldValid("glAccountDescr") }, "Input__Round")} />

                            {getFormErrorMessage("glAccountDescr")}
                        </div>
                    </div>

                    <div className="field col-12 md:col-4">
                        <div className="field">
                            <label htmlFor="glAccountShort" className={classNames({ "p-error": isFormFieldValid("glAccountShort") }, "Label__Text")}>
                                Account Short<span className="Label__Required">*</span>
                            </label>
                            <InputText id="glAccountShort" placeholder="Enter Account Short" name="glAccountShort" value={values.glAccountShort?.replace(/\s\s+/g, " ")} onChange={onChange} className={classNames({ "p-invalid": isFormFieldValid("glAccountShort") }, "Input__Round")} />

                            {getFormErrorMessage("glAccountShort")}
                        </div>
                    </div>
                    <div className="field col-12 md:col-4">
                        <div className="field">
                            <label htmlFor="glIsActive" className={classNames({ "p-error": isFormFieldValid("glIsActive") }, "Label__Text")}>
                                Is Active<span className="Label__Required">*</span>
                            </label>
                            <Dropdown id="glIsActive" placeholder="Select Status" options={defaultOptions} optionLabel="label" name="glIsActive" value={values.glIsActive} onChange={onChange} className={classNames({ "p-invalid": isFormFieldValid("glIsActive") }, "Dropdown__Round")} />
                            {getFormErrorMessage("glIsActive")}
                        </div>
                    </div>
                    <div className="field col-12 md:col-4">
                        <div className="field">
                            <label htmlFor="glFcAllowed" className={classNames({ "p-error": isFormFieldValid("glFcAllowed") }, "Label__Text")}>
                                FC Allowed<span className="Label__Required">*</span>
                            </label>
                            <Dropdown id="glFcAllowed" placeholder="Select Fc Allowed" options={fcAllowedOptions} optionLabel="label" name="glFcAllowed" value={values.glFcAllowed} onChange={onChange} className={classNames({ "p-invalid": isFormFieldValid("glFcAllowed") }, "Dropdown__Round")} />
                            {getFormErrorMessage("glFcAllowed")}
                        </div>
                    </div>
                </div>
                <div className="Down__Btn Down__Actions">
                    {type === "View" ? "" : <Button label="Cancel" className="Btn__Transparent" onClick={(e) => cancelHandler(e)} />}
                    {type === "View" ? "" : type === "Edit" ? <Button onClick={(e) => handleSubmit(e)} label="Update" className="Btn__Dark" /> : <Button onClick={(e) => handleSubmit(e)} label="Add" className="Btn__Dark" />}
                </div>
            </form>
        </div>
    );
}

export default AddEditGl;

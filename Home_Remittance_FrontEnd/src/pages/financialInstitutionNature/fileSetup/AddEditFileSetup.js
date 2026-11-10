import React, { useState, useEffect } from "react";
import { Dropdown } from "primereact/dropdown";
import { handleGetRequest } from "../../../services/GetTemplate";

function AddEditProfile({ type, isFormFieldValid, getFormErrorMessage, classNames, onChange, values }) {
    const [parserOptions, setparserOptions] = useState([]);

    useEffect(() => {
        handleGetRequest("js-hr-fi/lov/lovParsers", 8082).then((res) => {
            setparserOptions(Array.isArray(res?.data) ? res.data : []);
        });
    }, []); // eslint-disable-line

    return (
        <div className="card Card__Round">
            <div className="Form__Header">
                <h1>{type} File Setup</h1>
            </div>
            <form className="fluid mt-2">
                <div className="fluid formgrid grid">
                    <div className="field col-12 md:col-4">
                        <div className="field">
                            <label htmlFor="parserHeadId" className={classNames({ "p-error": isFormFieldValid("parserHeadId") }, "Label__Text")}>
                                Parser<span className="Label__Required">*</span>
                            </label>
                            <Dropdown id="parserHeadId" placeholder="Select Parser" options={parserOptions} optionLabel="description" name="parserHeadId" value={values.parserHeadId} onChange={onChange} className={classNames({ "p-invalid": isFormFieldValid("parserHeadId") }, "Dropdown__Round")} />
                            {getFormErrorMessage("parserHeadId")}
                        </div>
                    </div>
                </div>
            </form>
        </div>
    );
}

export default AddEditProfile;

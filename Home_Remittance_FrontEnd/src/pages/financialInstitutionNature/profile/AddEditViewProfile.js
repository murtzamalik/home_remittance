import React, { useState, useEffect } from "react";
import { InputText } from "primereact/inputtext";
import { Dropdown } from "primereact/dropdown";
import { Divider } from "primereact/divider";
import { handleGetRequest } from "../../../services/GetTemplate";

function AddEditViewProfile({ type, natureOptions, isFormFieldValid, getFormErrorMessage, classNames, onChange, values, handleSubmit }) {
    const [countryOptions, setcountryOptions] = useState([]);
    const [cityOptions, setcityOptions] = useState([]);

    useEffect(() => {
        handleGetRequest("js-hr-fi/lov/lovCountry", 8082).then((res) => {
            setcountryOptions(Array.isArray(res?.data) ? res.data : []);
        });
    }, []); // eslint-disable-line

    const getCityOptions = (countryId) => {
        handleGetRequest(`js-hr-fi/lov/lovCity/${countryId}`, 8082).then((res) => {
            setcityOptions(Array.isArray(res?.data) ? res.data : []);
        });
    };
    const defaultOptions = [
        { label: "Yes", value: "Y" },
        { label: "No", value: "N" },
    ];

    return (
        <div className="card Card__Round">
            <div className="Form__Header">
                <h1> {type} Profile</h1>
            </div>
            <form className="fluid mt-2">
                <div className="fluid formgrid grid">
                    <div className="field col-12 md:col-4">
                        <div className="field">
                            <label htmlFor="fininstCode" className={classNames({ "p-error": isFormFieldValid("fininstCode") }, "Label__Text")}>
                                Code<span className="Label__Required">*</span>
                            </label>
                            <InputText id="fininstCode" placeholder="Enter Code" name="fininstCode" value={values.fininstCode?.toString()?.trim()} onChange={onChange} className={classNames({ "p-invalid": isFormFieldValid("fininstCode") }, "Input__Round")} />

                            {getFormErrorMessage("fininstCode")}
                        </div>
                    </div>
                    <div className="field col-12 md:col-4">
                        <div className="field">
                            <label htmlFor="fininstShort" className={classNames({ "p-error": isFormFieldValid("fininstShort") }, "Label__Text")}>
                                Fininst Short<span className="Label__Required">*</span>
                            </label>
                            <InputText id="fininstShort" placeholder="Enter Short" name="fininstShort" value={values.fininstShort?.replace(/\s\s+/g, " ")} onChange={onChange} className={classNames({ "p-invalid": isFormFieldValid("fininstShort") }, "Input__Round")} />

                            {getFormErrorMessage("fininstShort")}
                        </div>
                    </div>
                    <div className="field col-12 md:col-4">
                        <div className="field">
                            <label htmlFor="fininstNature" className={classNames({ "p-error": isFormFieldValid("fininstNature") }, "Label__Text")}>
                                Nature<span className="Label__Required">*</span>
                            </label>
                            <Dropdown
                                id="fininstNature"
                                placeholder="Select Financial Institution Nature"
                                options={natureOptions}
                                optionLabel="description"
                                name="fininstNature"
                                value={values.fininstNature}
                                onChange={(e) => {
                                    onChange(e);
                                }}
                                className={classNames({ "p-invalid": isFormFieldValid("fininstNature") }, "Dropdown__Round")}
                            />
                            {getFormErrorMessage("fininstNature")}
                        </div>
                    </div>
                    <div className="field col-12 md:col-4">
                        <div className="field">
                            <label htmlFor="fininstDefault" className={classNames({ "p-error": isFormFieldValid("fininstDefault") }, "Label__Text")}>
                                Default<span className="Label__Required">*</span>
                            </label>
                            <Dropdown
                                id="fininstDefault"
                                placeholder="Select Default"
                                options={defaultOptions}
                                optionLabel="label"
                                name="fininstDefault"
                                value={values.fininstDefault}
                                onChange={onChange}
                                className={classNames({ "p-invalid": isFormFieldValid("fininstDefault") }, "Dropdown__Round")}
                            />

                            {getFormErrorMessage("fininstDefault")}
                        </div>
                    </div>
                    <div className="field col-12 md:col-4">
                        <div className="field">
                            <label htmlFor="isActive" className={classNames({ "p-error": isFormFieldValid("isActive") }, "Label__Text")}>
                                Active<span className="Label__Required">*</span>
                            </label>
                            <Dropdown
                                id="isActive"
                                placeholder="Select Active Status"
                                options={defaultOptions}
                                optionLabel="label"
                                name="isActive"
                                value={values.isActive?.toString()?.trim()}
                                onChange={onChange}
                                className={classNames({ "p-invalid": isFormFieldValid("isActive") }, "Dropdown__Round")}
                            />
                            {getFormErrorMessage("isActive")}
                        </div>
                    </div>
                    <div className="field col-12 md:col-4">
                        <div className="field">
                            <label htmlFor="fininstBankcode" className={classNames({ "p-error": isFormFieldValid("fininstBankcode") }, "Label__Text")}>
                                Bank/Branch<span className="Label__Required">*</span>
                            </label>
                            <InputText id="fininstBankcode" placeholder="Enter Bank/Branch" name="fininstBankcode" value={values.fininstBankcode?.toString()?.trim()} onChange={onChange} className={classNames({ "p-invalid": isFormFieldValid("fininstBankcode") }, "Input__Round")} />

                            {getFormErrorMessage("fininstBankcode")}
                        </div>
                    </div>
                    <div className="field col-12 md:col-4">
                        <div className="field">
                            <label htmlFor="ibftAccountLength" className={classNames({ "p-error": isFormFieldValid("ibftAccountLength") }, "Label__Text")}>
                                Account Length<span className="Label__Required">*</span>
                            </label>
                            <InputText id="ibftAccountLength" placeholder="Enter Account Length" name="ibftAccountLength" value={values.ibftAccountLength?.toString()?.trim()} onChange={onChange} className={classNames({ "p-invalid": isFormFieldValid("ibftAccountLength") }, "Input__Round")} />

                            {getFormErrorMessage("ibftAccountLength")}
                        </div>
                    </div>
                    <div className="field col-12 md:col-4">
                        <div className="field">
                            <label htmlFor="ibftAccountFormat" className={classNames({ "p-error": isFormFieldValid("ibftAccountFormat") }, "Label__Text")}>
                                Account Format<span className="Label__Required">*</span>
                            </label>
                            <InputText id="ibftAccountFormat" placeholder="Enter Account Format" name="ibftAccountFormat" value={values.ibftAccountFormat?.toString()?.trim()} onChange={onChange} className={classNames({ "p-invalid": isFormFieldValid("ibftAccountFormat") }, "Input__Round")} />

                            {getFormErrorMessage("ibftAccountFormat")}
                        </div>
                    </div>
                    <div className="field col-12 md:col-4">
                        <div className="field">
                            <label htmlFor="ccEmail" className={classNames({ "p-error": isFormFieldValid("ccEmail") }, "Label__Text")}>
                                CC Email<span className="Label__Required">*</span>
                            </label>
                            <InputText id="ccEmail" placeholder="Enter ccEmail Address" name="ccEmail" value={values.ccEmail?.toString()?.trim()} onChange={onChange} className={classNames({ "p-invalid": isFormFieldValid("ccEmail") }, "Input__Round")} />

                            {getFormErrorMessage("ccEmail")}
                        </div>
                    </div>
                    <div className="field col-12 md:col-4">
                        <div className="field">
                            <label htmlFor="validateIban" className={classNames({ "p-error": isFormFieldValid("validateIban") }, "Label__Text")}>
                                Validate IBAN<span className="Label__Required">*</span>
                            </label>
                            <Dropdown
                                id="validateIban"
                                placeholder="Select Valid IBAN"
                                options={defaultOptions}
                                optionLabel="label"
                                name="validateIban"
                                value={values.validateIban?.toString()?.trim()}
                                onChange={onChange}
                                className={classNames({ "p-invalid": isFormFieldValid("validateIban") }, "Dropdown__Round")}
                            />

                            {getFormErrorMessage("validateIban")}
                        </div>
                    </div>
                    <div className="field col-12 md:col-4">
                        <div className="field">
                            <label htmlFor="fininstDescr" className={classNames({ "p-error": isFormFieldValid("fininstDescr") }, "Label__Text")}>
                                Account Description<span className="Label__Required">*</span>
                            </label>
                            <InputText id="fininstDescr" placeholder="Enter Account Description" name="fininstDescr" value={values.fininstDescr?.toString()?.trim()} onChange={onChange} className={classNames({ "p-invalid": isFormFieldValid("fininstDescr") }, "Input__Round")} />

                            {getFormErrorMessage("fininstDescr")}
                        </div>
                    </div>
                    <Divider />
                    <div className="field col-12 md:col-4">
                        <div className="field">
                            <label htmlFor="contactPerson" className={classNames({ "p-error": isFormFieldValid("contactPerson") }, "Label__Text")}>
                                Contact Person
                            </label>
                            <InputText id="contactPerson" type="text" placeholder="Enter Contact Person" name="contactPerson" value={values.contactPerson?.replace(/\s\s+/g, " ")} onChange={onChange} className={classNames({ "p-invalid": isFormFieldValid("contactPerson") }, "Input__Round")} />

                            {getFormErrorMessage("contactPerson")}
                        </div>
                    </div>
                    <div className="field col-12 md:col-4">
                        <div className="field">
                            <label htmlFor="phone" className={classNames({ "p-error": isFormFieldValid("phone") }, "Label__Text")}>
                                Phone<span className="Label__Required">*</span>
                            </label>
                            <InputText id="phone" placeholder="Enter Phone Number" name="phone" value={values.phone?.toString()?.trim()} onChange={onChange} className={classNames({ "p-invalid": isFormFieldValid("phone") }, "Input__Round")} />

                            {getFormErrorMessage("phone")}
                        </div>
                    </div>
                    <div className="field col-12 md:col-4">
                        <div className="field">
                            <label htmlFor="mobile" className={classNames({ "p-error": isFormFieldValid("mobile") }, "Label__Text")}>
                                Mobile Number
                            </label>
                            <InputText id="mobile" placeholder="Enter Mobile Number" name="mobile" value={values.mobile?.replace(/\s\s+/g, " ")} onChange={onChange} className={classNames({ "p-invalid": isFormFieldValid("mobile") }, "Input__Round")} />

                            {getFormErrorMessage("mobile")}
                        </div>
                    </div>

                    <div className="field col-12 md:col-4">
                        <div className="field">
                            <label htmlFor="fax" className={classNames({ "p-error": isFormFieldValid("fax") }, "Label__Text")}>
                                Fax
                            </label>
                            <InputText id="fax" placeholder="Enter Fax Number" name="fax" value={values.fax?.replace(/\s\s+/g, " ")} onChange={onChange} className={classNames({ "p-invalid": isFormFieldValid("fax") }, "Input__Round")} />

                            {getFormErrorMessage("fax")}
                        </div>
                    </div>
                    <div className="field col-12 md:col-4">
                        <div className="field">
                            <label htmlFor="email" className={classNames({ "p-error": isFormFieldValid("email") }, "Label__Text")}>
                                Email<span className="Label__Required">*</span>
                            </label>
                            <InputText id="email" placeholder="Enter Email Address" name="email" value={values.email?.toString()?.trim()} onChange={onChange} className={classNames({ "p-invalid": isFormFieldValid("email") }, "Input__Round")} />

                            {getFormErrorMessage("email")}
                        </div>
                    </div>
                    <div className="field col-12 md:col-4">
                        <div className="field">
                            <label htmlFor="pobox" className={classNames({ "p-error": isFormFieldValid("pobox") }, "Label__Text")}>
                                P.O.Box
                            </label>
                            <InputText id="pobox" placeholder="Enter Pobox Number" name="pobox" value={values.pobox?.replace(/\s\s+/g, " ")} onChange={onChange} className={classNames({ "p-invalid": isFormFieldValid("pobox") }, "Input__Round")} />

                            {getFormErrorMessage("pobox")}
                        </div>
                    </div>
                    <div className="field col-12 md:col-4">
                        <div className="field">
                            <label htmlFor="postal" className={classNames({ "p-error": isFormFieldValid("postal") }, "Label__Text")}>
                                Postal
                            </label>
                            <InputText id="postal" placeholder="Enter Postal Number" name="postal" value={values.postal?.replace(/\s\s+/g, " ")} onChange={onChange} className={classNames({ "p-invalid": isFormFieldValid("postal") }, "Input__Round")} />

                            {getFormErrorMessage("postal")}
                        </div>
                    </div>
                    <div className="field col-12 md:col-4">
                        <div className="field">
                            <label htmlFor="website" className={classNames({ "p-error": isFormFieldValid("website") }, "Label__Text")}>
                                Website
                            </label>
                            <InputText id="website" placeholder="Enter Website" name="website" value={values.website?.replace(/\s\s+/g, " ")} onChange={onChange} className={classNames({ "p-invalid": isFormFieldValid("website") }, "Input__Round")} />

                            {getFormErrorMessage("website")}
                        </div>
                    </div>

                    <div className="field col-12 md:col-4">
                        <div className="field">
                            <label htmlFor="regAddress1" className={classNames({ "p-error": isFormFieldValid("regAddress1") }, "Label__Text")}>
                                Address Line 1
                            </label>
                            <InputText id="regAddress1" placeholder="Enter Address" name="regAddress1" value={values.regAddress1?.toString()?.trim()} onChange={onChange} className={classNames({ "p-invalid": isFormFieldValid("regAddress1") }, "Input__Round")} />

                            {getFormErrorMessage("regAddress1")}
                        </div>
                    </div>
                    <div className="field col-12 md:col-4">
                        <div className="field">
                            <label htmlFor="regAddress2" className={classNames({ "p-error": isFormFieldValid("regAddress2") }, "Label__Text")}>
                                Address Line 2
                            </label>
                            <InputText id="regAddress2" placeholder="Enter Address" name="regAddress2" value={values.regAddress2?.toString()?.trim()} onChange={onChange} className={classNames({ "p-invalid": isFormFieldValid("regAddress2") }, "Input__Round")} />

                            {getFormErrorMessage("regAddress2")}
                        </div>
                    </div>
                    <div className="field col-12 md:col-4">
                        <div className="field">
                            <label htmlFor="regAddress3" className={classNames({ "p-error": isFormFieldValid("regAddress3") }, "Label__Text")}>
                                Address Line 3
                            </label>
                            <InputText id="regAddress3" placeholder="Enter Address" name="regAddress3" value={values.regAddress3?.toString()?.trim()} onChange={onChange} className={classNames({ "p-invalid": isFormFieldValid("regAddress3") }, "Input__Round")} />

                            {getFormErrorMessage("regAddress3")}
                        </div>
                    </div>
                    <div className="field col-12 md:col-4">
                        <div className="field">
                            <label htmlFor="fininstSbpcode" className={classNames({ "p-error": isFormFieldValid("fininstSbpcode") }, "Label__Text")}>
                                SBP Code
                            </label>
                            <InputText id="fininstSbpcode" placeholder="Enter Sbp code" name="fininstSbpcode" value={values.fininstSbpcode?.toString()?.trim()} onChange={onChange} className={classNames({ "p-invalid": isFormFieldValid("fininstSbpcode") }, "Input__Round")} />

                            {getFormErrorMessage("fininstSbpcode")}
                        </div>
                    </div>
                    <div className="field col-12 md:col-4">
                        <div className="field">
                            <label htmlFor="lkpCountry1Id" className={classNames({ "p-error": isFormFieldValid("lkpCountry1Id") }, "Label__Text")}>
                                Country
                            </label>
                            <Dropdown
                                id="lkpCountry1Id"
                                placeholder="Select Country"
                                options={countryOptions}
                                optionLabel="description"
                                name="lkpCountry1Id"
                                value={values.lkpCountry1Id}
                                onChange={(e) => {
                                    onChange(e);
                                    getCityOptions(e.value?.id);
                                }}
                                className={classNames({ "p-invalid": isFormFieldValid("lkpCountry1Id") }, "Dropdown__Round")}
                            />
                            {getFormErrorMessage("lkpCountry1Id")}
                        </div>
                    </div>
                    <div className="field col-12 md:col-4">
                        <div className="field">
                            <label htmlFor="lkpCityId" className={classNames({ "p-error": isFormFieldValid("lkpCityId") }, "Label__Text")}>
                                City
                            </label>
                            <Dropdown id="lkpCityId" placeholder="Select City" options={cityOptions} optionLabel="description" name="lkpCityId" value={values.lkpCityId} onChange={onChange} className={classNames({ "p-invalid": isFormFieldValid("lkpCityId") }, "Dropdown__Round")} />
                            {getFormErrorMessage("lkpCityId")}
                        </div>
                    </div>
                </div>
            </form>
        </div>
    );
}

export default AddEditViewProfile;

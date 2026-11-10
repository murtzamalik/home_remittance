import React, { useEffect, useState } from "react";
import { InputText } from "primereact/inputtext";
import { Button } from "primereact/button";
import { useFormik } from "formik";
import classNames from "classnames";
import * as Yup from "yup";
import { handleGetRequest } from "../../../services/GetTemplate";
import { listData } from "../../../utilities/apiUrl";
import { MultiSelect } from "primereact/multiselect";

function TransactionProcessFilter({ getData }) {
    const [loadingIcon, setloadingIcon] = useState("pi pi-search");
    const [remittingValues, setRemittingValues] = useState([]);
    const [selectedRemittingValue, setSelectedRemittingValue] = useState([]);
    const [tranactionTypeOptions, settranactionTypeOptions] = useState([]);
    const [transType, settransType] = useState([]);

    const cancelHandler = (e) => {
        e.preventDefault();
        formik.resetForm();
    };

    useEffect(() => {
        handleGetRequest("js_hr_fileupload/lov/lovFinInstBank").then((res) => {
            setRemittingValues(listData(res));
        });
        handleGetRequest("js_hr_transactions/lov/lovTransType", 8084).then((res) => {
            settranactionTypeOptions(listData(res));
        });
    }, []);

    //searchable multi select code
    const panelFooterTemplate = () => {
        const selectedItems = selectedRemittingValue;
        const length = selectedItems ? selectedItems.length : 0;
        return (
            <div className="py-2 px-3">
                <b>{length}</b> item{length > 1 ? "s" : ""} selected.
            </div>
        );
    };
    const RemittingCompanyTemplate = (option) => {
        return <div className="country-item">{option?.description}</div>;
    };
    const selectedRemittingCompaniesTemplate = (option) => {
        if (option) {
            return (
                <div style={{ display: "inline" }} className="country-item country-item-value">
                    {option?.description}
                </div>
            );
        }

        return "Select Remitting Countries";
    };
    //end

    const validationSchema = Yup.object().shape({
        //  transType: Yup.mixed().required("This field is required."),
        fromDate: Yup.string().required("This field is required."),
        toDate: Yup.string().required("This field is required."),
    });

    const formik = useFormik({
        validationSchema: validationSchema,
        initialValues: {
            transType: "",
            remitIds: "",
            fromDate: "",
            toDate: "",
        },
        onSubmit: async (data) => {
            const remitIds = selectedRemittingValue.map((item) => item.id);
            data["transType"] = transType;
            data["remitIds"] = remitIds;
            console.log(data);
            setloadingIcon("pi pi-spin pi-spinner");
            await getData(data);
            setloadingIcon("pi pi-save");
        },
    });
    const isFormFieldValid = (name) => !!(formik.touched[name] && formik.errors[name]);
    const getFormErrorMessage = (name) => {
        return isFormFieldValid(name) && <small className="p-error">{formik.errors[name]}</small>;
    };

    return (
        <div className="card Card__Round">
            <div className="Form__Header">
                <h1>File Processing</h1>
            </div>
            <form onSubmit={formik.handleSubmit} className="fluid mt-2">
                <div className="fluid formgrid grid">
                    <div className="field col-12 md:col-3 FD_Wrapper">
                        <label htmlFor="fromDate" className="Label__Text">
                            Date From <span className="Label__Required">*</span>
                        </label>
                        <InputText id="fromDate" type="date" value={formik.values.fromDate} onChange={formik.handleChange} className="Input__Round" />
                        {getFormErrorMessage("fromDate")}
                    </div>
                    <div className="field col-12 md:col-3 FD_Wrapper">
                        <label htmlFor="toDate" className="Label__Text">
                            Date To<span className="Label__Required">*</span>
                        </label>
                        <InputText id="toDate" type="date" value={formik.values.toDate} onChange={formik.handleChange} className="Input__Round" />
                        {getFormErrorMessage("toDate")}
                    </div>
                    <div className="field col-12 md:col-3">
                        <div className="field">
                            <label htmlFor="remitIds" className={classNames({ "p-error": isFormFieldValid("remitIds") }, "Label__Text")}>
                                Remiting Company
                            </label>
                            <MultiSelect
                                value={selectedRemittingValue}
                                options={remittingValues}
                                onChange={(e) => {
                                    setSelectedRemittingValue(e.target.value);
                                }}
                                optionLabel="description"
                                placeholder="Select Remitting Company"
                                filter
                                className={classNames("Dropdown__Round")}
                                itemTemplate={RemittingCompanyTemplate}
                                selectedItemTemplate={selectedRemittingCompaniesTemplate}
                                panelFooterTemplate={panelFooterTemplate}
                            />
                            {getFormErrorMessage("remitIds")}
                        </div>
                    </div>
                    <div className="field col-12 md:col-3">
                        <div className="field">
                            <label htmlFor="transType" className={classNames({ "p-error": isFormFieldValid("transType") }, "Label__Text")}>
                                Transaction Type <span className="Label__Required">*</span>
                            </label>
                            <MultiSelect
                                placeholder="Select Transaction Type"
                                optionLabel="description"
                                name="transType"
                                optionValue="code"
                                options={tranactionTypeOptions}
                                value={transType}
                                onChange={(e) => {
                                    settransType(e.target.value);
                                }}
                                className={classNames("Dropdown__Round")}
                            />

                            {getFormErrorMessage("transType")}
                        </div>
                    </div>
                </div>
                <div className="Down__Btn mt-4">
                    <Button label="Cancel" className="Btn__Transparent" onClick={(e) => cancelHandler(e)} />
                    <Button type="submit" icon={loadingIcon || ""} iconPos="right" label="Search" className="Btn__Dark" />
                </div>
            </form>
        </div>
    );
}

export default TransactionProcessFilter;

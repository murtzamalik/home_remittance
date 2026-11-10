import React, { useEffect, useState } from "react";
import { InputText } from "primereact/inputtext";
import { Button } from "primereact/button";
import { useFormik } from "formik";
import classNames from "classnames";
import * as Yup from "yup";
import { useDispatch } from "react-redux";
import { handlePostRequest } from "../../../../services/PostTemplate";
import { handleGetRequest } from "../../../../services/GetTemplate";
import { Dropdown } from "primereact/dropdown";

function SingleTransactionFormData({ getSingleTransaction }) {
    const [loadingIcon, setloadingIcon] = useState("pi pi-pencil");
    const [statusOptions, setStatusOptions] = useState([]);
    const [transTypeOptions, setTransTypeOptions] = useState([]);
    const dispatch = useDispatch();

    const cancelHandler = (e) => {
        e.preventDefault();
        formik.resetForm();
    };
    const statusOptionsData = () => {
        handleGetRequest("js_hr_transactions/lov/lovStatus", 8084).then((res) => {
            if (res?.responsecode === 1) {
                setStatusOptions(Array.isArray(res?.data) ? res.data : []);
            } else {
                setStatusOptions([]);
            }
        });
    };
    const transTypeOptionsData = async () => {
        const res = await handleGetRequest("js_hr_transactions/lov/lovTransType", 8084);
        if (res?.responsecode === 1) {
            setTransTypeOptions(Array.isArray(res?.data) ? res.data : []);
        } else {
            setTransTypeOptions([]);
        }
    };

    useEffect(() => {
        statusOptionsData();
    }, []);
    useEffect(() => {
        transTypeOptionsData();
    }, []);

    const validationSchema = Yup.object().shape({
        amount: Yup.string().required("This field is required."),
        beneficiaryAccountNo: Yup.string().required("This field is required."),
        beneficiaryAccountTitle: Yup.string().required("This field is required."),
        beneficiaryBankName: Yup.string().required("This field is required."),
        beneficiaryBranchAdd1: Yup.string().required("This field is required."),
        beneficiaryBranchAdd2: Yup.string().required("This field is required."),
        beneficiaryBranchCode: Yup.string().required("This field is required."),
        beneficiaryCnic: Yup.string().required("This field is required."),
        beneficiaryName: Yup.string().required("This field is required."),
        beneficiaryOtherContact: Yup.string().required("This field is required."),
        remitterAddress: Yup.string().required("This field is required."),
        remitterName: Yup.string().required("This field is required."),
        remitterNumber: Yup.string().required("This field is required."),
        status: Yup.string().required("This field is required."),
        statusDescr: Yup.string().required("This field is required."),
        transactionReference: Yup.string().required("This field is required."),
        transactionType: Yup.string().required("This field is required."),
        transType: Yup.string().required("This field is required."),
    });

    const formik = useFormik({
        // validationSchema: validationSchema,
        initialValues: {
            amount: "",
            beneficiaryAccountNo: "",
            beneficiaryAccountTitle: "",
            beneficiaryBankName: "",
            beneficiaryBranchAdd1: "",
            beneficiaryBranchAdd2: "",
            beneficiaryBranchCode: "",
            beneficiaryCnic: "",
            beneficiaryName: "",
            beneficiaryOtherContact: "",
            remitterAddress: "",
            remitterName: "",
            remitterNumber: "",
            status: "",
            statusDescr: "",
            transactionReference: "",
            transType: "",
        },
        onSubmit: async (data) => {
            setloadingIcon("pi pi-spin pi-spinner");
            const res = await dispatch(handlePostRequest(data, "js_hr_transactions/transaction/singleTransactionInquiry", true, false, 8084));
            if (res?.responsecode === 1) {
                getSingleTransaction(res?.data);
                formik.resetForm();
            }

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
                <h1>Single Transaction Inquiry</h1>
            </div>
            <form onSubmit={formik.handleSubmit} className="fluid mt-2">
                <div className="fluid formgrid grid">
                    <div className="field col-12 md:col-6">
                        <label htmlFor="amount" className={classNames({ "p-error": isFormFieldValid("amount") }, "Label__Text")}>
                            Amount <span className="Label__Required">*</span>
                        </label>
                        <InputText id="amount" placeholder="Enter Amount" name="amount" value={formik.values.amount} onChange={formik.handleChange} className={classNames({ "p-invalid": isFormFieldValid("amount") }, "Input__Round")} />
                        {getFormErrorMessage("amount")}
                    </div>
                    <div className="field col-12 md:col-6">
                        <label htmlFor="beneficiaryAccountNo" className="Label__Text">
                            Beneficiary Account No <span className="Label__Required">*</span>
                        </label>
                        <InputText
                            id="beneficiaryAccountNo"
                            placeholder="Enter Beneficiary Account No"
                            name="beneficiaryAccountNo"
                            value={formik.values.beneficiaryAccountNo}
                            onChange={formik.handleChange}
                            className={classNames({ "p-invalid": isFormFieldValid("beneficiaryAccountNo") }, "Input__Round")}
                        />
                        {getFormErrorMessage("beneficiaryAccountNo")}
                    </div>
                    <div className="field col-12 md:col-6">
                        <div className="field">
                            <label htmlFor="beneficiaryAccountTitle" className="Label__Text">
                                Beneficiary Account Title <span className="Label__Required">*</span>
                            </label>
                            <InputText
                                id="beneficiaryAccountTitle"
                                placeholder="Enter Beneficiary Account Title"
                                name="beneficiaryAccountTitle"
                                value={formik.values.beneficiaryAccountTitle}
                                onChange={formik.handleChange}
                                className={classNames({ "p-invalid": isFormFieldValid("beneficiaryAccountTitle") }, "Input__Round")}
                            />
                            {getFormErrorMessage("beneficiaryAccountTitle")}
                        </div>
                    </div>
                    <div className="field col-12 md:col-6">
                        <div className="field">
                            <label htmlFor="beneficiaryBankName" className="Label__Text">
                                Beneficiary Bank Name <span className="Label__Required">*</span>
                            </label>
                            <InputText
                                id="beneficiaryBankName"
                                placeholder="Enter Beneficiary Bank Name"
                                name="beneficiaryBankName"
                                value={formik.values.beneficiaryBankName}
                                onChange={formik.handleChange}
                                className={classNames({ "p-invalid": isFormFieldValid("beneficiaryBankName") }, "Input__Round")}
                            />
                            {getFormErrorMessage("beneficiaryBankName")}
                        </div>
                    </div>
                    <div className="field col-12 md:col-4">
                        <div className="field">
                            <label htmlFor="beneficiaryBranchAdd1" className={classNames({ "p-error": isFormFieldValid("beneficiaryBranchAdd1") }, "Label__Text")}>
                                Beneficiary Branch Add1 <span className="Label__Required">*</span>
                            </label>

                            <InputText
                                id="beneficiaryBranchAdd1"
                                placeholder="Enter Beneficiary Address"
                                name="beneficiaryBranchAdd1"
                                value={formik.values.beneficiaryBranchAdd1}
                                onChange={formik.handleChange}
                                className={classNames({ "p-invalid": isFormFieldValid("beneficiaryBranchAdd1") }, "Input__Round")}
                            />
                            {getFormErrorMessage("beneficiaryBranchAdd1")}
                        </div>
                    </div>
                    <div className="field col-12 md:col-4">
                        <div className="field">
                            <label htmlFor="beneficiaryBranchAdd2" className={classNames({ "p-error": isFormFieldValid("beneficiaryBranchAdd2") }, "Label__Text")}>
                                Beneficiary Branch Add2 <span className="Label__Required">*</span>
                            </label>

                            <InputText
                                id="beneficiaryBranchAdd2"
                                placeholder="Enter Beneficiary Address"
                                name="beneficiaryBranchAdd2"
                                value={formik.values.beneficiaryBranchAdd2}
                                onChange={formik.handleChange}
                                className={classNames({ "p-invalid": isFormFieldValid("beneficiaryBranchAdd2") }, "Input__Round")}
                            />
                            {getFormErrorMessage("beneficiaryBranchAdd2")}
                        </div>
                    </div>
                    <div className="field col-12 md:col-4">
                        <div className="field">
                            <label htmlFor="beneficiaryBranchCode" className={classNames({ "p-error": isFormFieldValid("beneficiaryBranchCode") }, "Label__Text")}>
                                Beneficiary Branch Code <span className="Label__Required">*</span>
                            </label>

                            <InputText
                                id="beneficiaryBranchCode"
                                placeholder="Enter Beneficiary Branch Code"
                                name="beneficiaryBranchCode"
                                value={formik.values.beneficiaryBranchCode}
                                onChange={formik.handleChange}
                                className={classNames({ "p-invalid": isFormFieldValid("beneficiaryBranchCode") }, "Input__Round")}
                            />

                            {getFormErrorMessage("beneficiaryBranchCode")}
                        </div>
                    </div>
                    <div className="field col-12 md:col-4">
                        <div className="field">
                            <label htmlFor="beneficiaryCnic" className="Label__Text">
                                Beneficiary Cnic <span className="Label__Required">*</span>
                            </label>
                            <InputText id="beneficiaryCnic" placeholder="Enter Beneficiary Cnic" name="beneficiaryCnic" value={formik.values.beneficiaryCnic} onChange={formik.handleChange} className={classNames({ "p-invalid": isFormFieldValid("beneficiaryCnic") }, "Input__Round")} />
                            {getFormErrorMessage("beneficiaryCnic")}
                        </div>
                    </div>
                    <div className="field col-12 md:col-4">
                        <div className="field">
                            <label htmlFor="beneficiaryName" className="Label__Text">
                                Beneficiary Name<span className="Label__Required">*</span>
                            </label>
                            <InputText id="beneficiaryName" placeholder="Enter Beneficiary Name" name="beneficiaryName" value={formik.values.beneficiaryName} onChange={formik.handleChange} className={classNames({ "p-invalid": isFormFieldValid("beneficiaryName") }, "Input__Round")} />
                            {getFormErrorMessage("beneficiaryName")}
                        </div>
                    </div>
                    <div className="field col-12 md:col-4">
                        <div className="field">
                            <label htmlFor="beneficiaryOtherContact" className="Label__Text">
                                Beneficiary Other Contact<span className="Label__Required">*</span>
                            </label>
                            <InputText
                                id="beneficiaryOtherContact"
                                placeholder="Enter Beneficiary Other Contact"
                                name="beneficiaryOtherContact"
                                value={formik.values.beneficiaryOtherContact}
                                onChange={formik.handleChange}
                                className={classNames({ "p-invalid": isFormFieldValid("beneficiaryOtherContact") }, "Input__Round")}
                            />
                            {getFormErrorMessage("beneficiaryOtherContact")}
                        </div>
                    </div>
                    <div className="field col-12 md:col-4">
                        <div className="field">
                            <label htmlFor="remitterAddress" className="Label__Text">
                                Remitter Address <span className="Label__Required">*</span>
                            </label>
                            <InputText id="remitterAddress" placeholder="Enter Remitter Address" name="remitterAddress" value={formik.values.remitterAddress} onChange={formik.handleChange} className={classNames({ "p-invalid": isFormFieldValid("remitterAddress") }, "Input__Round")} />
                            {getFormErrorMessage("remitterAddress")}
                        </div>
                    </div>
                    <div className="field col-12 md:col-4">
                        <div className="field">
                            <label htmlFor="remitterName" className="Label__Text">
                                Remitter Name <span className="Label__Required">*</span>
                            </label>
                            <InputText id="remitterName" placeholder="Enter Remitter Name" name="remitterName" value={formik.values.remitterName} onChange={formik.handleChange} className={classNames({ "p-invalid": isFormFieldValid("remitterName") }, "Input__Round")} />
                            {getFormErrorMessage("remitterName")}
                        </div>
                    </div>
                    <div className="field col-12 md:col-4">
                        <div className="field">
                            <label htmlFor="remitterNumber" className="Label__Text">
                                Remitter Number<span className="Label__Required">*</span>
                            </label>
                            <InputText id="remitterNumber" placeholder="Enter Remitter Number" name="remitterNumber" value={formik.values.remitterNumber} onChange={formik.handleChange} className={classNames({ "p-invalid": isFormFieldValid("remitterNumber") }, "Input__Round")} />
                            {getFormErrorMessage("remitterNumber")}
                        </div>
                    </div>
                    <div className="field col-12 md:col-6">
                        <div className="field">
                            <label htmlFor="status" className="Label__Text">
                                Status <span className="Label__Required">*</span>
                            </label>
                            <Dropdown optionValue="id" placeholder="Select Status" optionLabel="description" options={statusOptions} name="status" value={formik.values.status} onChange={formik.handleChange} className={classNames("Dropdown__Round")} />
                            {getFormErrorMessage("status")}
                        </div>
                    </div>
                    <div className="field col-12 md:col-6">
                        <div className="field">
                            <label htmlFor="transactionReference" className="Label__Text">
                                Transaction Reference<span className="Label__Required">*</span>
                            </label>
                            <InputText
                                id="transactionReference"
                                placeholder="Enter Transaction Reference"
                                name="transactionReference"
                                value={formik.values.transactionReference}
                                onChange={formik.handleChange}
                                className={classNames({ "p-invalid": isFormFieldValid("transactionReference") }, "Input__Round")}
                            />
                            {getFormErrorMessage("transactionReference")}
                        </div>
                    </div>
                    <div className="field col-12 md:col-6">
                        <div className="field">
                            <label htmlFor="transType" className={classNames({ "p-error": isFormFieldValid("transType") }, "Label__Text")}>
                                Trans Type <span className="Label__Required">*</span>
                            </label>

                            <Dropdown optionValue="id" name="transType" placeholder="Select Transaction Type" optionLabel="description" options={transTypeOptions} value={formik.values.transType} onChange={formik.handleChange} className={classNames("Dropdown__Round")} />

                            {getFormErrorMessage("transType")}
                        </div>
                    </div>
                </div>
                <div className="Down__Btn">
                    <Button label="Cancel" className="Btn__Transparent" onClick={(e) => cancelHandler(e)} />
                    <Button iconPos="right" label="Search" className="Btn__Dark" />
                </div>
            </form>
        </div>
    );
}

export default SingleTransactionFormData;

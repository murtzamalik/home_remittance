import React, { useState, useEffect } from "react";
import { Button } from "primereact/button";
import { useFormik } from "formik";
import classNames from "classnames";
import { DataTable } from "primereact/datatable";
import { Column } from "primereact/column";
import { Dropdown } from "primereact/dropdown";
import { useHistory } from "react-router-dom";
import * as Yup from "yup";
import { handleGetRequest } from "../../../services/GetTemplate";
import { toast } from "react-toastify";

function Profiles() {
    const [loadingIcon, setloadingIcon] = useState("pi pi-search");
    const [profile, setProfile] = useState([]);
    const [natureOptions, setnatureOptions] = useState([]);

    useEffect(() => {
        handleGetRequest("js-hr-fi/lov/lovNature", 8082).then((res) => {
            setnatureOptions(Array.isArray(res?.data) ? res.data : []);
        });
    }, []); // eslint-disable-line

    let history = useHistory();
    const actionTemplate = (rowData) => {
        return (
            <div className="flex">
                <Button tooltip="Edit" icon="pi pi-pencil" tooltipOptions={{ position: "top" }} onClick={() => history.push(`/editfinmenu?id=${rowData.fininstBankId}`)} className="p-button-rounded p-button-warning mr-2" />
                <Button tooltip="View Details" tooltipOptions={{ position: "top" }} icon="pi pi-eye" onClick={() => history.push(`/viewfinmenu?id=${rowData?.fininstBankId}`)} className="p-button-rounded p-button-primary mr-2" />
            </div>
        );
    };
    const validationSchema = Yup.object().shape({
        nature: Yup.mixed().required("This field is required."),
    });
    const formik = useFormik({
        validationSchema: validationSchema,
        initialValues: {
            nature: "",
        },
        validate: (data) => {
            let errors = {};

            return errors;
        },
        onSubmit: async (data) => {
            setloadingIcon("pi pi-spin pi-spinner");
            const res = await handleGetRequest(`js-hr-fi/fi/getFinInstsNatureWise/${data?.nature?.id}`, 8082);
            if (res?.status && res?.status !== 200) {
                setloadingIcon("pi pi-search");
                toast.error(res?.data?.messages);
            } else setProfile(res?.data);
            setloadingIcon("pi pi-search");
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
                    <h1>Profile</h1>
                </div>
                <div className="Top__Btn">
                    <Button label="Add New" className="Btn__Dark" onClick={() => history.push("/addfinmenu")} />
                </div>
                <form onSubmit={formik.handleSubmit} className="fluid mt-2">
                    <div className="fluid formgrid grid">
                        <div className="field col-12 md:col-4">
                            <div className="field">
                                <label htmlFor="nature" className={classNames({ "p-error": isFormFieldValid("nature") }, "Label__Text")}>
                                    Nature <span className="Label__Required">*</span>
                                </label>
                                <Dropdown id="nature" placeholder="Select Profile" options={natureOptions} optionLabel="description" name="nature" value={formik.values.nature} onChange={formik.handleChange} className={classNames({ "p-invalid": isFormFieldValid("nature") }, "Dropdown__Round")} />
                                {getFormErrorMessage("nature")}
                            </div>
                        </div>
                        <div className="Down__Btn Down__Actions">
                            <Button icon={loadingIcon || ""} iconPos="Filter" label="Filter" className="Btn__Dark mt-2" />
                        </div>
                    </div>
                </form>
            </div>
            <div className="card p-datatable-sm">
                <DataTable
                    style={{ overflow: "scroll" }}
                    header=""
                    paginator
                    rows={6}
                    value={profile}
                    emptyMessage="No data found."
                    paginatorTemplate="FirstPageLink PrevPageLink PageLinks NextPageLink LastPageLink CurrentPageReport RowsPerPageDropdown"
                    currentPageReportTemplate="Showing {first} to {last} of {totalRecords} Profiles"
                >
                    <Column filter field="accountNoPkr" header="Account number" />
                    <Column filter field="fininstDescr" header="Institution Description" />
                    <Column field="amlDatamode" header="Aml mode" />
                    <Column field="createdate" header="Created date" />
                    <Column filter field="contactPerson" header="Contact Person" />
                    <Column filter field="email" header="Email" />
                    <Column field="ibft" header="IBFT" />
                    <Column field="isActive" header="Active status" />
                    <Column header="Action" body={actionTemplate} />
                </DataTable>
            </div>
            <br />
        </>
    );
}

export default Profiles;

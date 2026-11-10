import React, { useEffect, useState } from "react";
import classNames from "classnames";
import { TabView, TabPanel } from "primereact/tabview";
import Profile from "./profile/AddEditViewProfile";
import FileSetup from "./fileSetup/AddEditFileSetup";
import Gl from "./gl/AddEditGl";
import { handleGetRequest } from "../../services/GetTemplate";
import { initialValues } from "./FinConstants";
import { useFormik } from "formik";
import { handlePostRequest } from "../../services/PostTemplate";
import { useDispatch } from "react-redux";
import { handlePutRequest } from "../../services/PutTemplate";
import { useLocation } from "react-router-dom";

const FinComponents = ({ type }) => {
    const [activeIndex, setActiveIndex] = useState(0);
    const [natureOptions, setnatureOptions] = useState([]);
    const [fininstBankId, setfininstBankId] = useState("");

    let { search } = useLocation();
    const query = new URLSearchParams(search);
    const id = query.get("id");
    const dispatch = useDispatch();

    // formik must be declared before any useEffect that references it —
    // previously this TDZ access blanked /addfinmenu (Add Profile).
    const formik = useFormik({
        // validationSchema: validationSchema,
        initialValues: initialValues,
        onSubmit: async (data) => {
            const payload = { ...data };
            payload["lkpCityId"] = payload["lkpCityId"]?.id;
            payload["lkpCountry1Id"] = payload["lkpCountry1Id"]?.id;
            payload["parserHeadId"] = payload["parserHeadId"]?.id;
            payload["fininstNature"] = payload["fininstNature"]?.id;
            payload["glLkpGlAccountTypeId"] = payload["glLkpGlAccountTypeId"]?.id;
            if (type === "Edit") {
                payload["fininstBankId"] = fininstBankId;
                dispatch(handlePutRequest(payload, "js-hr-fi/fi/updateFinInst", true, true, 8082));
            } else {
                dispatch(handlePostRequest(payload, "js-hr-fi/fi/saveFinInst", true, true, 8082));
            }
        },
    });

    useEffect(() => {
        if (id && (type === "View" || type === "Edit")) {
            handleGetRequest(`js-hr-fi/fi/getSingleFinInsts/${id}`, 8082).then((res) => {
                const data = res?.data;
                if (!data) return;

                setfininstBankId(data?.fininstBankId);
                Object.keys(data).forEach((key) => {
                    if (Object.prototype.hasOwnProperty.call(formik.initialValues, key)) {
                        formik.setFieldValue(key, data[key]);
                    }
                });

                const glAccount = data?.tblFininstGls?.[0]?.tblGlAccount;
                if (glAccount) {
                    Object.keys(glAccount).forEach((key) => {
                        formik.setFieldValue(key, glAccount[key]);
                    });
                }

                if (data?.parserHeadId) {
                    formik.setFieldValue("parserHeadId", {
                        code: data.parserHeadId?.code,
                        description: data.parserHeadId?.description,
                        id: data.parserHeadId?.parserHeadId?.toString(),
                        longText: null,
                        shortText: null,
                    });
                }
                if (data?.lkpCountry1) {
                    formik.setFieldValue("lkpCountry1Id", {
                        code: data.lkpCountry1?.countryCode,
                        description: data.lkpCountry1?.countryDescr,
                        id: data.lkpCountry1?.countryId?.toString(),
                        longText: null,
                        shortText: null,
                    });
                }
                if (data?.lkpFininstNature) {
                    formik.setFieldValue("fininstNature", {
                        code: data.lkpFininstNature?.code,
                        description: data.lkpFininstNature?.description,
                        id: data.lkpFininstNature?.code?.toString(),
                        longText: null,
                        shortText: null,
                    });
                }
                if (glAccount?.glLkpGlAccountTypeId) {
                    formik.setFieldValue("glLkpGlAccountTypeId", {
                        code: glAccount.glLkpGlAccountTypeId?.glAccountTypeCode,
                        description: glAccount.glLkpGlAccountTypeId?.glAccountTypeDescr,
                        id: glAccount.glLkpGlAccountTypeId?.glAccountTypeId?.toString(),
                        longText: null,
                        shortText: null,
                    });
                }
            });
        }
        // formik intentionally omitted from deps — including it causes infinite reloads
    }, [type, id]); // eslint-disable-line react-hooks/exhaustive-deps

    useEffect(() => {
        handleGetRequest("js-hr-fi/lov/lovNature", 8082).then((res) => {
            setnatureOptions(Array.isArray(res?.data) ? res.data : []);
        });
    }, []);
    const isFormFieldValid = (name) => !!(formik.touched[name] && formik.errors[name]);
    const getFormErrorMessage = (name) => {
        return isFormFieldValid(name) && <small className="p-error">{formik.errors[name]}</small>;
    };

    const handleSubmit = (e) => {
        e.preventDefault();
        formik.handleSubmit();
    };

    const props = {
        isFormFieldValid: isFormFieldValid,
        getFormErrorMessage: getFormErrorMessage,
        classNames: classNames,
        onChange: formik.handleChange,
        values: formik.values,
    };

    return (
        <div className="card Card__Round">
            <div className="Form__Header">
                <h1>Financial Institution Nature </h1>
            </div>
            {/* Tab View */}

            <TabView renderActiveOnly={false} activeIndex={activeIndex} onTabChange={(e) => setActiveIndex(e.index)}>
                <TabPanel header="Profile">
                    <Profile type={type} natureOptions={natureOptions} {...props} handleSubmit={handleSubmit} />
                </TabPanel>
                <TabPanel header="File Setup">
                    <FileSetup type={type} natureOptions={natureOptions} {...props} />
                </TabPanel>
                <TabPanel header="Gl's">
                    <Gl type={type} {...props} handleSubmit={handleSubmit} />
                </TabPanel>
            </TabView>

            {/* Tab View Ends */}
        </div>
    );
};

export default FinComponents;

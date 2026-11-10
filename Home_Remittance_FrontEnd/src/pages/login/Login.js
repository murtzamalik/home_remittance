import React, { useState } from "react";
import { useHistory } from "react-router-dom";
import { Button } from "primereact/button";
import { loginAction } from "../../redux/actions/authAction";
import { useDispatch } from "react-redux";
import { useFormik } from "formik";
import LogoImage from "../../Images/zindigi-logo.webp";
import "./Login.css";
import classNames from "classnames";
import * as Yup from "yup";

const Login = () => {
    const [loadingIcon, setloadingIcon] = useState("");
    const [loading, setloading] = useState(false);

    const dispatch = useDispatch();

    let history = useHistory();
    const validationSchema = Yup.object().shape({
        emailId: Yup.string().required("This field is required.").max(254, "Maximum length is 254 characters"),
        password: Yup.string().required("This field is required.").max(100, "Maximum length is 100 characters"),
    });
    const formik = useFormik({
        validationSchema: validationSchema,
        initialValues: {
            emailId: "",
            password: "",
        },
        validate: (data) => {
            let errors = {};
            return errors;
        },
        onSubmit: async (data) => {
            setloadingIcon("pi pi-spin pi-spinner");
            setloading(true);
            const res = await dispatch(loginAction(data, "js-hr-login/login"));
            setloading(false);
            setloadingIcon("");
            if (res?.data.login) {
                history.push("/dashboard");
            }
        },
    });
    const isFormFieldValid = (name) => !!(formik.touched[name] && formik.errors[name]);
    const getFormErrorMessage = (name) => {
        return isFormFieldValid(name) && <small className="p-error">{formik.errors[name]}</small>;
    };

    return (
        <div className="login_body">
            <div align="center" style={{ marginTop: "4%", marginBottom: "1%" }}>
                <img src={LogoImage} alt="Zindigi" className="login_logo" />
            </div>
            <div className="container" id="container">
                <div className="form-container sign-in-container">
                    <form action="#" className="login_form" onSubmit={formik.handleSubmit}>
                        <div className="p-mb-4">
                            <h1 className="login_h1">Login</h1>
                        </div>
                        <div className="p-mt-4 credentail_wrapper">
                            <input id="emailId" className={classNames({ "p-invalid": isFormFieldValid("emailId") }, "login_input")} name="emailId" value={formik.values.emailId} placeholder="Enter Email Address " onChange={formik.handleChange} type="text" />
                            {getFormErrorMessage("emailId")}
                            <input className={classNames({ "p-invalid": isFormFieldValid("password") }, "login_input")} name="password" placeholder="Enter Password" value={formik.values.password} onChange={formik.handleChange} type="password" />
                            {getFormErrorMessage("password")}
                            <div className="p-mt-2 button_wrapper">
                                <Button className="login_button mt-5" label="Login" disabled={loading} icon={loadingIcon || ""} iconPos="right" />
                            </div>
                        </div>
                    </form>
                </div>
                <div className="overlay-container">
                    <div className="overlay">
                        <div className="overlay-panel overlay-right">
                            <h1 className="login_h1">Welcome!</h1>
                            <p className="login_p">Please login to access Home Remittance Portal</p>
                        </div>
                    </div>
                </div>
            </div>
            <footer>
                <p>
                    Powered by <span className="fintiq-brand">FINTIQ SOLUTIONS</span>
                </p>
            </footer>
        </div>
    );
};

export default Login;

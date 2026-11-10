import React, { useState, useEffect, useRef } from "react";
import classNames from "classnames";
import { Redirect, Route, useLocation } from "react-router-dom";
import { CSSTransition } from "react-transition-group";

import { AppTopbar } from "./AppTopbar";
import { AppFooter } from "./AppFooter";
import { AppMenu } from "./AppMenu";
import { AppConfig } from "./AppConfig";

import Dashboard from "./components/Dashboard";
import Login from "./pages/login/Login";

import PrimeReact from "primereact/api";
import { Tooltip } from "primereact/tooltip";

import { ToastContainer } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";
import LoadingOverlay from "react-loading-overlay";

import "primereact/resources/primereact.css";
import "primeicons/primeicons.css";
import "primeflex/primeflex.css";
import "prismjs/themes/prism-coy.css";
import "./assets/demo/flags/flags.css";
import "./assets/demo/Demos.scss";
import "./assets/layout/layout.scss";
import "./App.scss";
import Roles from "./pages/userManagement/roles/Roles";
import RoleRights from "./pages/userManagement/roleRights/RoleRights";
import Module from "./pages/userManagement/module/Module";
import Menu from "./pages/userManagement/menu/Menu";
import AddEditViewProfile from "./pages/financialInstitutionNature/profile/AddEditViewProfile";
import UploadFile from "./pages/operationalProcess/uploadFile/UploadFile";
import AmendmentTransaction from "./pages/amendmentTransaction";
import StatusMarking from "./pages/operationalProcess/statusMarking/StatusMarking";
import ImportAmlFile from "./pages/operationalProcess/importAmlFile/ImportAmlFile";
import OverseaBulkData from "./pages/operationalProcess/overseasBulkData/OverseaBulkData";
import FileProcessingBatch from "./pages/operationalProcess/fileProcessingBatch/fpbAnnex/FpbAnnex";
import FileProcessingOwnBank from "./pages/operationalProcess/fileProcessingBatch/fpbOwnBank/FpbOwnBank";
import NactaFileImport from "./pages/operationalProcess/importNactaFile/NactaFileImport";
import TransactionProcess from "./pages/operationalProcess/transactionProcess/TransactionProcess";
import FinComponents from "./pages/financialInstitutionNature/FinComponents";
import { useSelector } from "react-redux";
import PROFILE from "./pages/financialInstitutionNature/profile/Profiles";
import RemittMode from "./pages/financialInstitutionNature/remittMode/RemittMode";
import AddEditParserHead from "./pages/parser/AddEditParserHead";
import TransactionInquiry from "./pages/transaction/TransactionInquiry";
import BankTransferTransaction from "./pages/transaction/BalanceTransferTransaction";
import AddEditRemitLimit from "./pages/financialInstitutionNature/remitLimit/AddEditRemitLimit";
import { AppProfile } from "./AppProfile";
import User from "./pages/userManagement/user/User";
import ViewStatus from "./pages/transaction/ViewStatus";
import SingleTransaction from "./pages/transaction/singleTransaction";
import Mt102Inquiry from "./pages/transaction/mt102Inquiry/Mt102Inquiry";
import { isAuthenticated } from "./utilities/session";

const App = () => {
    const [layoutMode, setLayoutMode] = useState("static");
    const [layoutColorMode, setLayoutColorMode] = useState("light");
    const [inputStyle, setInputStyle] = useState("outlined");
    const [ripple, setRipple] = useState(true);
    const [staticMenuInactive, setStaticMenuInactive] = useState(false);
    const [overlayMenuActive, setOverlayMenuActive] = useState(false);
    const [mobileMenuActive, setMobileMenuActive] = useState(false);
    const [mobileTopbarMenuActive, setMobileTopbarMenuActive] = useState(false);
    const nav = useSelector((state) => state?.authenticationSlice?.nav);
    const [dynamicroutes, setdynamicroutes] = useState([]);
    const copyTooltipRef = useRef();
    const location = useLocation();
    require("dotenv").config();
    const isLoading = useSelector((state) => state?.utilitySlice?.isLoading);

    PrimeReact.ripple = true;

    let menuClick = false;
    let mobileTopbarMenuClick = false;

    useEffect(() => {
        if (mobileMenuActive) {
            addClass(document.body, "body-overflow-hidden");
        } else {
            removeClass(document.body, "body-overflow-hidden");
        }
    }, [mobileMenuActive]);

    useEffect(() => {
        copyTooltipRef && copyTooltipRef.current && copyTooltipRef.current.updateTargetEvents();
    }, [location]);

    const onInputStyleChange = (inputStyle) => {
        setInputStyle(inputStyle);
    };

    const onRipple = (e) => {
        PrimeReact.ripple = e.value;
        setRipple(e.value);
    };

    const onLayoutModeChange = (mode) => {
        setLayoutMode(mode);
    };

    const onColorModeChange = (mode) => {
        setLayoutColorMode(mode);
    };

    const onWrapperClick = (event) => {
        if (!menuClick) {
            setOverlayMenuActive(false);
            setMobileMenuActive(false);
        }

        if (!mobileTopbarMenuClick) {
            setMobileTopbarMenuActive(false);
        }

        mobileTopbarMenuClick = false;
        menuClick = false;
    };

    const onToggleMenuClick = (event) => {
        menuClick = true;

        if (isDesktop()) {
            if (layoutMode === "overlay") {
                if (mobileMenuActive === true) {
                    setOverlayMenuActive(true);
                }

                setOverlayMenuActive((prevState) => !prevState);
                setMobileMenuActive(false);
            } else if (layoutMode === "static") {
                setStaticMenuInactive((prevState) => !prevState);
            }
        } else {
            setMobileMenuActive((prevState) => !prevState);
        }

        event.preventDefault();
    };

    const onSidebarClick = () => {
        menuClick = true;
    };

    const onMobileTopbarMenuClick = (event) => {
        mobileTopbarMenuClick = true;

        setMobileTopbarMenuActive((prevState) => !prevState);
        event.preventDefault();
    };

    const onMobileSubTopbarMenuClick = (event) => {
        mobileTopbarMenuClick = true;

        event.preventDefault();
    };

    const onMenuItemClick = (event) => {
        if (!event.item.items) {
            setOverlayMenuActive(false);
            setMobileMenuActive(false);
        }
    };
    const isDesktop = () => {
        return window.innerWidth >= 992;
    };

    // const menu = [
    //     {
    //         label: "User Management",
    //         items: [
    //             {
    //                 label: "Roles and Rights Matrix",
    //                 items: [
    //                     { label: "Module", icon: "pi pi-circle", to: "/getmodule" },
    //                     { label: "Role", icon: "pi pi-circle", to: "/getroles" },
    //                 ],
    //             },
    //             {
    //                 label: "Create User",
    //                 items: [{ label: "Add Edit User", icon: "pi pi-circle", to: "/getuser" }],
    //             },
    //         ],
    //     },
    //     {
    //         label: "Profile",
    //         items: [{ label: "Profile", icon: "pi pi-circle", to: "/profile" }],
    //     },
    //     {
    //         label: "Operational Process",
    //         items: [
    //             { label: "Upload File", icon: "pi pi-circle", to: "/uploadFile" },
    //             // { label: "Status Marking", icon: "pi pi-circle", to: "/statusMarking" },
    //             { label: "Import AML File", icon: "pi pi-circle", to: "/importamlfile" },
    //             { label: "Import NACTA File", icon: "pi pi-circle", to: "/importnactafile" },
    //         ],
    //     },
    //     {
    //         label: "Capping",
    //         items: [{ label: "Capping", icon: "pi pi-circle", to: "/remittmode" }],
    //     },
    //     {
    //         label: "Parser",
    //         items: [{ label: "Parser", icon: "pi pi-circle", to: "/parser" }],
    //     },
    //     // {
    //     //     label: "Remittance Mode Setup",
    //     //     items: [{ label: "Remittance Mode Setup", icon: "pi pi-circle", to: "/addprofile" }],
    //     // },
    //     // {
    //     //     label: "FI Mapping",
    //     //     items: [{ label: "Add FI Mapping", icon: "pi pi-circle", to: "/addprofile" }],
    //     // },
    //     // {
    //     //     label: "Allowable Users",
    //     //     items: [
    //     //         {
    //     //             label: "Allowable Users",
    //     //             items: [
    //     //                 { label: "AllowAccess To Users", icon: "pi pi-circle", to: "/addprofile" },
    //     //                 { label: "Drop Down Api", icon: "pi pi-circle", to: "/addprofile" },
    //     //             ],
    //     //         },
    //     //     ],
    //     // },
    //     {
    //         label: "Transaction",
    //         items: [
    //             // {
    //             //     label: "COTC API Online",
    //             //     items: [
    //             //         { label: "Fetch Transaction Against Input", icon: "pi pi-circle", to: "/addprofile" },
    //             //         { label: "Perform Transaction", icon: "pi pi-circle", to: "/addprofile" },
    //             //         { label: "Mark Paid", icon: "pi pi-circle", to: "/addprofile" },
    //             //         { label: "Generate Receipt", icon: "pi pi-circle", to: "/addprofile" },
    //             //     ],
    //             // },
    //             // {
    //             //     label: "Incomplete Transaction",
    //             //     items: [
    //             //         { label: "Search Transaction", icon: "pi pi-circle", to: "/addprofile" },
    //             //         { label: "Repush Transaction", icon: "pi pi-circle", to: "/addprofile" },
    //             //         { label: "Dropdown Apis", icon: "pi pi-circle", to: "/addprofile" },
    //             //     ],
    //             // },
    //             // {
    //             //     label: "Incomplete Account Capping",
    //             //     items: [{ label: "Incomplete Account Capping", icon: "pi pi-circle", to: "/addprofile" }],
    //             // },
    //             { label: "Amendment Transaction", icon: "pi pi-circle", to: "/amendmenttransaction" },
    //             { label: "Transaction Process", icon: "pi pi-circle", to: "/transactionprocess" },
    //             { label: "Bank Transfer Transaction", icon: "pi pi-circle", to: "/banktransfertransaction" },
    //             { label: "Transaction Inquiry", icon: "pi pi-circle", to: "/transactioninquiry" },
    //             // {
    //             //     label: "Transaction Inquiry (FPI Setup)",
    //             //     items: [
    //             //         { label: "Drop Down Api", icon: "pi pi-circle", to: "/addprofile" },
    //             //         { label: "Search Transaction", icon: "pi pi-circle", to: "/addprofile" },
    //             //         { label: "View Transaction Details", icon: "pi pi-circle", to: "/addprofile" },
    //             //     ],
    //             // },
    //         ],
    //     },
    // ];

    const addClass = (element, className) => {
        if (element.classList) element.classList.add(className);
        else element.className += " " + className;
    };

    const removeClass = (element, className) => {
        if (element.classList) element.classList.remove(className);
        else element.className = element.className.replace(new RegExp("(^|\\b)" + className.split(" ").join("|") + "(\\b|$)", "gi"), " ");
    };
    const menu = JSON.parse(localStorage.getItem("nav"));

    const checks = (item, newdynamicroutes) => {
        item &&
            item.length &&
            item.forEach((elm) => {
                if ("items" in elm && elm?.items.length) {
                    for (let i = 0; i < elm?.items?.length; i++) {
                        console.log(elm.items.item);
                    }

                    // elm?.items.forEach((item) => {
                    //     if (item?.to !== null) {
                    //         newdynamicroutes.push(item);
                    //     }
                    // });
                }
            });
        return newdynamicroutes;
    };
    useEffect(() => {
        if (nav) {
            setdynamicroutes(checks(JSON.parse(nav), []));
        }
    }, [nav]);
    console.log("dynamicroutes", checks(dynamicroutes));

    const wrapperClass = classNames("layout-wrapper", {
        "layout-overlay": layoutMode === "overlay",
        "layout-static": layoutMode === "static",
        "layout-static-sidebar-inactive": staticMenuInactive && layoutMode === "static",
        "layout-overlay-sidebar-active": overlayMenuActive && layoutMode === "overlay",
        "layout-mobile-sidebar-active": mobileMenuActive,
        "p-input-filled": inputStyle === "filled",
        "p-ripple-disabled": ripple === false,
        "layout-theme-light": layoutColorMode === "light",
    });

    return (
        <div className={wrapperClass} onClick={onWrapperClick}>
            <LoadingOverlay active={isLoading} spinner text="Loading ...">
                <ToastContainer />
                <Tooltip ref={copyTooltipRef} target=".block-action-copy" position="bottom" content="Copied to clipboard" event="focus" />

                {!isAuthenticated() ? (
                    <Route path="/" component={Login} />
                ) : (
                    <>
                        <AppTopbar onToggleMenuClick={onToggleMenuClick} layoutColorMode={layoutColorMode} mobileTopbarMenuActive={mobileTopbarMenuActive} onMobileTopbarMenuClick={onMobileTopbarMenuClick} onMobileSubTopbarMenuClick={onMobileSubTopbarMenuClick} />
                        <div className="layout-sidebar" onClick={onSidebarClick}>
                            <AppProfile />
                            <AppMenu model={menu} onMenuItemClick={onMenuItemClick} layoutColorMode={layoutColorMode} />
                        </div>
                        <div className="layout-main-container">
                            <div className="layout-main">
                                {/* {dynamicroutes.map((route, index) => (route.to === "/userlist" ? <Route key={index} exact path={route.to} component={Dashboard} /> : ""))} */}
                                {dynamicroutes.map((route, index) => (route.to === "/userlist" ? <Route key={index} exact path={route.to} component={route.label === "PROFILE" ? PROFILE : <Redirect />} /> : ""))}

                                {<Route exact path="/getroles" component={Roles} />}
                                {<Route exact path="/getmodule" component={Module} />}
                                {<Route exact path="/getmenu" component={Menu} />}
                                {<Route exact path="/getrolerights" component={RoleRights} />}
                                <Route path="/dashboard" component={Dashboard} />
                                {<Route exact path="/addeditprofile" component={AddEditViewProfile} />}
                                {<Route exact path="/uploadFile" component={UploadFile} />}
                                {<Route exact path="/amendmenttransaction" component={AmendmentTransaction} />}
                                {<Route exact path="/statusMarking" component={StatusMarking} />}
                                {<Route exact path="/importamlfile" component={ImportAmlFile} />}
                                {<Route exact path="/overseabulkdata" component={OverseaBulkData} />}
                                {<Route exact path="/fileprocessingbatchannex" component={FileProcessingBatch} />}
                                {<Route exact path="/fileprocessingbatchownbank" component={FileProcessingOwnBank} />}
                                {<Route exact path="/importnactafile" component={NactaFileImport} />}
                                {<Route exact path="/transactionprocess" component={TransactionProcess} />}
                                {<Route exact path="/finmenu" component={FinComponents} />}
                                {<Route exact path="/addfinmenu" component={() => <FinComponents type="Add" />} />}
                                {<Route exact path="/editfinmenu" component={() => <FinComponents type="Edit" />} />}
                                {<Route exact path="/viewfinmenu" component={() => <FinComponents type="View" />} />}
                                {<Route exact path="/profile" component={PROFILE} />}
                                {<Route exact path="/remittmode" component={RemittMode} />}
                                {<Route exact path="/parser" component={AddEditParserHead} />}
                                {<Route exact path="/transactioninquiry" component={TransactionInquiry} />}
                                {<Route exact path="/banktransfertransaction" component={BankTransferTransaction} />}
                                {<Route exact path="/remitlimit" component={AddEditRemitLimit} />}
                                {<Route exact path="/getuser" component={User} />}
                                {<Route exact path="/viewtransactionstatus" component={ViewStatus} />}
                                {<Route exact path="/singletransaction" component={SingleTransaction} />}
                                {<Route exact path="/mt102inquiry" component={Mt102Inquiry} />}
                                {/* Path aliases so menu seed / bookmarks match routes */}
                                {<Route exact path="/uploadfile" component={UploadFile} />}
                                {<Route exact path="/user" component={User} />}
                                {<Route exact path="/roles" component={Roles} />}
                                {<Route exact path="/rolerights" component={RoleRights} />}
                                {<Route exact path="/module" component={Module} />}
                                {<Route exact path="/menu" component={Menu} />}
                                {<Route exact path="/profiles" component={PROFILE} />}
                                {<Route exact path="/parserhead" component={AddEditParserHead} />}
                                {<Route exact path="/mt102iinquiry" component={Mt102Inquiry} />}
                            </div>
                            <AppFooter layoutColorMode={layoutColorMode} />
                        </div>
                    </>
                )}

                <AppConfig rippleEffect={ripple} onRippleEffect={onRipple} inputStyle={inputStyle} onInputStyleChange={onInputStyleChange} layoutMode={layoutMode} onLayoutModeChange={onLayoutModeChange} layoutColorMode={layoutColorMode} onColorModeChange={onColorModeChange} />

                <CSSTransition classNames="layout-mask" timeout={{ enter: 200, exit: 200 }} in={mobileMenuActive} unmountOnExit>
                    <div className="layout-mask p-component-overlay"></div>
                </CSSTransition>
            </LoadingOverlay>
        </div>
    );
};

export default App;

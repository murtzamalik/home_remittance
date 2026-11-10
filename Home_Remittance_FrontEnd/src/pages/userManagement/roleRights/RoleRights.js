import React, { useState, useEffect } from "react";
import { handleGetRequest } from "../../../services/GetTemplate";
import { DataTable } from "primereact/datatable";
import { Column } from "primereact/column";
import { Button } from "primereact/button";
import { Dialog } from "primereact/dialog";
import "../../../components/DataTable.css";
import AddEditRolesRights from "./AddEditRolesRights";
import { useLocation } from "react-router-dom";

function RoleRights() {
    const [menu, setMenu] = useState([]);
    const [displayModal, setDisplayModal] = useState(false);
    const [setPosition] = useState("center");
    const [editable, setEditable] = useState(false);
    const [roleRightsRowData, setroleRightsRowData] = useState("");
    const [loading, setloading] = useState([]);

    let { search } = useLocation();
    const query = new URLSearchParams(search);
    const roleId = query.get("rid");

    const editHandler = (rowData) => {
        setDisplayModal(true);
        setroleRightsRowData(rowData);
        setEditable(true);
    };

    const getRoleRights = async () => {
        setloading(true);
        const response = await handleGetRequest(`js_hr_um/userManagement/getRoleRights/${roleId}`, 8083);
        if (response?.responsecode === 1) {
            setMenu(response?.data);
            setloading(false);
        } else {
            setMenu([]);
        }
    };

    useEffect(() => {
        getRoleRights();
    }, []); // eslint-disable-line

    const actionTemplate = (rowData) => {
        return (
            <div>
                <Button tooltip="Edit" icon="pi pi-pencil" tooltipOptions={{ position: "top" }} onClick={() => editHandler(rowData)} className="p-button-rounded p-button-warning p-mr-2" />
            </div>
        );
    };

    const dialogFuncMap = {
        displayModal: setDisplayModal,
    };

    const onClick = (name, position) => {
        dialogFuncMap[`${name}`](true);

        if (position) {
            setPosition(position);
        }
    };

    const onHide = (name) => {
        setDisplayModal(false);
    };

    const authorizeBody = (rowData) => {
        return <React.Fragment>{rowData?.authorizeAllowed === "Y" ? "Yes" : "No"}</React.Fragment>;
    };
    const hideBody = (rowData) => {
        return <React.Fragment>{rowData?.hideYn === "Y" ? "Yes" : "No"}</React.Fragment>;
    };
    const insertBody = (rowData) => {
        return <React.Fragment>{rowData?.insertAllowed === "Y" ? "Yes" : "No"}</React.Fragment>;
    };
    const updateBody = (rowData) => {
        return <React.Fragment>{rowData?.updateAllowed === "Y" ? "Yes" : "No"}</React.Fragment>;
    };

    return (
        <div>
            <div className="Top__Btn">
                <Button label="Add New" className="Btn__Dark" onClick={() => onClick("displayModal", setEditable(false))} />
            </div>
            <Dialog visible={displayModal} style={{ width: "50vw" }} onHide={() => onHide("displayModal")}>
                <AddEditRolesRights onHide={onHide} editable={editable} roleRightsRowData={roleRightsRowData} getRoleRights={getRoleRights} roleId={roleId} />
            </Dialog>

            <div className="card p-datatable-sm">
                <DataTable
                    header="Role Rights"
                    paginator
                    rows={10}
                    loading={loading}
                    value={menu}
                    paginatorTemplate="FirstPageLink PrevPageLink PageLinks NextPageLink LastPageLink CurrentPageReport RowsPerPageDropdown"
                    currentPageReportTemplate="Showing {first} to {last} of {totalRecords} Roles Rights"
                    emptyMessage="No data found."
                >
                    <Column filter field="tblMenu.menuDescription" header="Decsription" />
                    <Column filter field="authorizeAllowed" body={authorizeBody} header="Authorization" />
                    <Column filter field="hideYn" body={hideBody} header="Hidden" />
                    <Column filter field="insertAllowed" body={insertBody} header="Insert Allowed" />
                    <Column filter field="updateAllowed" body={updateBody} header="Update Allowed" />
                    <Column body={actionTemplate} header="Action" />
                </DataTable>
            </div>
        </div>
    );
}

export default RoleRights;

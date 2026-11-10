import React, { useState, useEffect } from "react";
import { handleGetRequest } from "../../../services/GetTemplate";
import { DataTable } from "primereact/datatable";
import { Column } from "primereact/column";
import { Button } from "primereact/button";
import { useHistory } from "react-router-dom";
import { Dialog } from "primereact/dialog";
import "../../../components/DataTable.css";
import AddEditRole from "./AddEditRole";

function Roles() {
    const [roles, setRoles] = useState([]);
    const [loading, setloading] = useState([]);
    const [displayModal, setDisplayModal] = useState(false);
    const [editable, setEditable] = useState(false);
    const [roleRowData, setroleRowData] = useState("");
    const [setPosition] = useState("center");

    let history = useHistory();
    const editHandler = (rowData) => {
        setDisplayModal(true);
        setroleRowData(rowData);
        setEditable(true);
    };
    const actionTemplate = (rowData) => {
        return (
            <div>
                <Button tooltip="Edit" icon="pi pi-pencil" tooltipOptions={{ position: "top" }} onClick={() => editHandler(rowData)} className="p-button-rounded p-button-warning p-mr-2" />
                <Button tooltip="View Details" icon="pi pi-eye" tooltipOptions={{ position: "top" }} onClick={() => history.push(`/getrolerights?rid=${rowData?.roleId}`)} className="p-button-rounded p-button-primary p-mr-2" />
            </div>
        );
    };
    const getRoles = async () => {
        setloading(true);
        const response = await handleGetRequest("js_hr_um/userManagement/getAllRole", 8083);
        if (response?.responsecode === 1) {
            setRoles(response?.data);
            setloading(false);
        }
    };

    useEffect(() => {
        getRoles();
    }, []); // eslint-disable-line

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
    return (
        <div>
            <div className="Top__Btn">
                <Button label="Add New" className="Btn__Dark" onClick={() => onClick("displayModal", setEditable(false))} />
            </div>
            <Dialog visible={displayModal} style={{ width: "50vw" }} onHide={() => onHide("displayModal")}>
                <AddEditRole onHide={onHide} editable={editable} roleRowData={roleRowData} getRoles={getRoles} />
            </Dialog>
            <div className="card p-datatable-sm">
                <DataTable
                    header="Roles"
                    loading={loading}
                    paginator
                    rows={10}
                    value={roles}
                    emptyMessage="No data found."
                    paginatorTemplate="FirstPageLink PrevPageLink PageLinks NextPageLink LastPageLink CurrentPageReport RowsPerPageDropdown"
                    currentPageReportTemplate="Showing {first} to {last} of {totalRecords} Roles"
                >
                    <Column className="Table__Alignment" filter field="roleDescr" header="Role Description" />
                    <Column className="Table__Alignment" body={actionTemplate} header="Action" />
                </DataTable>
            </div>
        </div>
    );
}

export default Roles;

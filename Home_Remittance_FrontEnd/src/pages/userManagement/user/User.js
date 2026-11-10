import React, { useState, useEffect } from "react";
import { handleGetRequest } from "../../../services/GetTemplate";
import { DataTable } from "primereact/datatable";
import { Column } from "primereact/column";
import { Button } from "primereact/button";
import "../../../components/DataTable.css";
import AddEditUser from "./AddEditUser";

function User() {
    const [user, setUser] = useState([]);
    const [editable, setEditable] = useState(false);
    const [userRowData, setuserRowData] = useState("");
    const [loading, setloading] = useState([]);
    const [isview, setIsView] = useState(false);

    const editHandler = (rowData, operation) => {
        console.log("row data user", rowData);
        if (operation === "view" && operation !== undefined) {
            setIsView(true);
            setuserRowData(rowData);
        } else {
            setuserRowData(rowData);
            setEditable(true);
            setIsView(false);
        }
    };

    const getUser = async () => {
        setloading(true);
        const response = await handleGetRequest("js_hr_um/userManagement/getAllUsers", 8083);
        if (response?.responsecode === 1) {
            setUser(response?.data);
            setloading(false);
        } else {
            setUser([]);
        }
    };

    useEffect(() => {
        getUser();
    }, []); // eslint-disable-line

    const actionTemplate = (rowData) => {
        return (
            <div>
                <Button tooltip="Edit" icon="pi pi-pencil" tooltipOptions={{ position: "top" }} onClick={() => editHandler(rowData, "edit")} className="p-button-rounded p-button-warning p-mr-2" />
                <Button tooltip="View Details" tooltipOptions={{ position: "top" }} icon="pi pi-eye" onClick={() => editHandler(rowData, "view")} className="p-button-rounded p-button-primary p-mr-2" />
            </div>
        );
    };

    return (
        <div>
            <AddEditUser editable={editable} userRowData={userRowData} getUser={getUser} setEditable={setEditable} isview={isview} setIsView={setIsView} />
            <div className="card p-datatable-sm">
                <DataTable
                    header="Users"
                    paginator
                    rows={10}
                    loading={loading}
                    value={user}
                    paginatorTemplate="FirstPageLink PrevPageLink PageLinks NextPageLink LastPageLink CurrentPageReport RowsPerPageDropdown"
                    currentPageReportTemplate="Showing {first} to {last} of {totalRecords} Users"
                    emptyMessage="No data found."
                >
                    <Column filter field="usercode" header="User Code" />
                    <Column filter field="username" header="User Name" />
                    <Column filter field="employeeNo" header="Employee No" />
                    <Column filter field="remarks" header="Remarks" />
                    <Column filter field="email" header="Email" />
                    <Column filter field="mobileno" header="Mobile No" />
                    <Column filter field="isActive" header="isActive" />
                    <Column body={actionTemplate} header="Action" />
                </DataTable>
            </div>
        </div>
    );
}

export default User;

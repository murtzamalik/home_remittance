import React, { useState, useEffect } from "react";
import { handleGetRequest } from "../../../services/GetTemplate";
import { DataTable } from "primereact/datatable";
import { Column } from "primereact/column";
import { Button } from "primereact/button";
import { Dialog } from "primereact/dialog";
import "../../../components/DataTable.css";
import AddEditMenu from "./AddEditMenu";
import { useLocation } from "react-router-dom";

function Menu() {
    const [menu, setMenu] = useState([]);
    const [displayModal, setDisplayModal] = useState(false);
    const [setPosition] = useState("center");
    const [editable, setEditable] = useState(false);
    const [menuRowData, setMenuRowData] = useState({});
    const [loading, setloading] = useState(false);

    // Optional filter from Modules page: /getmenu?mid=1
    let { search } = useLocation();
    const query = new URLSearchParams(search);
    const moduleId = query.get("mid");

    const editHandler = (rowData) => {
        setDisplayModal(true);
        setMenuRowData(rowData);
        setEditable(true);
    };

    const getMenu = async () => {
        setloading(true);
        // Sidebar Menus page => all menus; Modules eye icon => module-filtered
        const url = moduleId
            ? `js_hr_um/userManagement/getAllMenuByModuleId/${moduleId}`
            : "js_hr_um/userManagement/getAllMenu";
        const response = await handleGetRequest(url, 8083);
        if (response?.responsecode === 1) {
            setMenu(Array.isArray(response?.data) ? response.data : []);
        } else {
            setMenu([]);
        }
        setloading(false);
    };

    useEffect(() => {
        getMenu();
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

    return (
        <div>
            <div className="Top__Btn">
                <Button label="Add New" className="Btn__Dark" onClick={() => onClick("displayModal", setEditable(false))} />
            </div>
            <Dialog visible={displayModal} style={{ width: "50vw" }} onHide={() => onHide("displayModal")}>
                <AddEditMenu onHide={onHide} editable={editable} menuRowData={menuRowData} moduleId={moduleId || menuRowData?.tblModule?.moduleId || menuRowData?.moduleId} getMenu={getMenu} />
            </Dialog>

            <div className="card p-datatable-sm">
                <DataTable
                    header={moduleId ? `Menu (Module ${moduleId})` : "Menu"}
                    paginator
                    rows={10}
                    loading={loading}
                    value={menu}
                    paginatorTemplate="FirstPageLink PrevPageLink PageLinks NextPageLink LastPageLink CurrentPageReport RowsPerPageDropdown"
                    currentPageReportTemplate="Showing {first} to {last} of {totalRecords} Menus"
                    emptyMessage="No data found."
                >
                    <Column filter field="menuCode" header="Code" />
                    <Column filter field="menuDescription" header="Description" />
                    <Column filter field="menuPath" header="Path" />
                    <Column filter field="iconPath" header="Icon" />
                    <Column body={actionTemplate} header="Action" />
                </DataTable>
            </div>
        </div>
    );
}

export default Menu;

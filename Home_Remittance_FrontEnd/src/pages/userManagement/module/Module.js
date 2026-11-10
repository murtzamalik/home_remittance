import React, { useState, useEffect } from "react";
import { handleGetRequest } from "../../../services/GetTemplate";
import { DataTable } from "primereact/datatable";
import { Column } from "primereact/column";
import { Button } from "primereact/button";
import { Dialog } from "primereact/dialog";
import { useHistory } from "react-router-dom";
import "../../../components/DataTable.css";
import AddEditModule from "./AddEditModule";

function Module() {
    const [module, setModule] = useState([]);
    const [displayModal, setDisplayModal] = useState(false);
    const [setPosition] = useState("center");
    const [editable, setEditable] = useState(false);
    const [moduleRowData, setmoduleRowData] = useState("");
    const [loading, setloading] = useState([]);

    let history = useHistory();

    const editHandler = (rowData) => {
        setDisplayModal(true);
        setmoduleRowData(rowData);
        setEditable(true);
    };

    const getModule = async () => {
        setloading(true);
        const response = await handleGetRequest("js_hr_um/userManagement/getAllModule", 8083);
        if (response?.responsecode === 1) {
            setModule(response?.data);
            setloading(false);
        } else {
            setModule([]);
        }
    };

    useEffect(() => {
        getModule();
    }, []); // eslint-disable-line

    const actionTemplate = (rowData) => {
        return (
            <div>
                <Button tooltip="Edit" icon="pi pi-pencil" tooltipOptions={{ position: "top" }} onClick={() => editHandler(rowData)} className="p-button-rounded p-button-warning p-mr-2" />
                <Button tooltip="View Details" tooltipOptions={{ position: "top" }} icon="pi pi-eye" onClick={() => history.push(`/getmenu?mid=${rowData?.moduleId}`)} className="p-button-rounded p-button-primary p-mr-2" />
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
                <AddEditModule onHide={onHide} editable={editable} moduleRowData={moduleRowData} getModule={getModule} />
            </Dialog>

            <div className="card p-datatable-sm">
                <DataTable
                    header="Module"
                    loading={loading}
                    paginator
                    rows={10}
                    value={module}
                    paginatorTemplate="FirstPageLink PrevPageLink PageLinks NextPageLink LastPageLink CurrentPageReport RowsPerPageDropdown"
                    currentPageReportTemplate="Showing {first} to {last} of {totalRecords} Modules"
                    emptyMessage="No data found."
                >
                    <Column filter field="moduleDescr" header="Description" />
                    <Column body={actionTemplate} header="Action" />
                </DataTable>
            </div>
        </div>
    );
}

export default Module;

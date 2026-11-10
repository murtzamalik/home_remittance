import React from "react";
import { DataTable } from "primereact/datatable";
import { Column } from "primereact/column";

const StatusDataTable = () => {
    const markingData = {
        uniqueReferenceNo: "112FGH32JL",
        fileName: "file.txt",
        remettingInstitute: "LECO Ltd",
        description: "Dummy Description",
        benfInst: "UBL",
        description2: "United Bank",
        mode: "BT",
        description3: "Bank Transfer",
        name: "Dummy Name",
        transNo: "748324HDS9",
        date: "23/12/2020",
    };

    return (
        <div className="card Dt__Wrapper">
            <DataTable className="DT__Style" value={markingData} responsiveLayout="scroll">
                <Column field="uniqueReferenceNo" header="Unique Reference Number"></Column>
                <Column field="fileName" header="File Name"></Column>
                <Column field="remettingInstitute" header="Remetting Institute"></Column>
                <Column field="description" header="Description"></Column>
                <Column field="benfInst" header="Benf.Inst"></Column>
                <Column field="description2" header="Description"></Column>
                <Column field="mode" header="Mode"></Column>
                <Column field="description3" header="Description"></Column>
                <Column field="name" header="Name"></Column>
                <Column field="transNo" header="Transaction No"></Column>
                <Column field="date" header="Date"></Column>
            </DataTable>
        </div>
    );
};

export default StatusDataTable;

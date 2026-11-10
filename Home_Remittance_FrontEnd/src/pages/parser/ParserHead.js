import React, { useState, useEffect } from "react";
import { DataTable } from "primereact/datatable";
import { Column } from "primereact/column";
import { useSelector, useDispatch } from "react-redux";
import { GetParserHeadDetailsAction, SetParserHeadFurtherDetailsAction } from "../../redux/actions/parserAction";

function ParserHead() {
    const [selectedRow, setselectedRow] = useState([]);
    const dispatch = useDispatch();

    const parseHeadDetails = useSelector((state) => state.parserSlice?.parseHeadDetails);

    const onRowSelect = (event) => {
        dispatch(SetParserHeadFurtherDetailsAction(event.data));
    };

    const getParserHeadDetails = () => {
        dispatch(GetParserHeadDetailsAction("js-hr-fi/fi/getAllParser", 8082));
    };

    useEffect(() => {
        getParserHeadDetails();
    }, []); // eslint-disable-line

    const statusBody = (rowData) => {
        return <div>{rowData?.isActive === "Y" ? "Yes" : "No"}</div>;
    };

    useEffect(() => {
        if (parseHeadDetails) {
        }
    }, [parseHeadDetails]);

    return (
        <div>
            <div className="card p-datatable-sm">
                <DataTable
                    header="Parser Head"
                    selection={selectedRow}
                    onSelectionChange={(e) => setselectedRow(e.value)}
                    selectionMode="single"
                    dataKey="parserHeadId"
                    onRowSelect={onRowSelect}
                    paginator
                    rows={10}
                    value={parseHeadDetails}
                    paginatorTemplate="FirstPageLink PrevPageLink PageLinks NextPageLink LastPageLink CurrentPageReport RowsPerPageDropdown"
                    currentPageReportTemplate="Showing {first} to {last} of {totalRecords}"
                    emptyMessage="No data found."
                >
                    <Column filter field="code" header="Code" />
                    <Column filter field="description" header="Description" />
                    <Column filter field="isActive" body={statusBody} header="Active" />
                </DataTable>
            </div>
        </div>
    );
}

export default ParserHead;

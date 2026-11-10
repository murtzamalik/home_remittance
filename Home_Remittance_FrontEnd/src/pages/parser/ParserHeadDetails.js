import React, { useState, useEffect } from "react";
import { DataTable } from "primereact/datatable";
import { Column } from "primereact/column";
import { useSelector, useDispatch } from "react-redux";
import { SetParserHeadExtremeFurtherDetailsAction } from "../.././redux/actions/parserAction";

function ParserHeadDetails() {
    const [selectedRow, setselectedRow] = useState([]);
    const [loading, setloading] = useState(true);
    const dispatch = useDispatch();

    const parseHeadFurtherDetails = useSelector((state) => state.parserSlice?.parseHeadFurtherDetails);

    const onRowSelect = (event) => {
        dispatch(SetParserHeadExtremeFurtherDetailsAction(event.data));
    };

    const statusBody = (rowData) => {
        return <div>{rowData?.isMandatory === "Y" ? "Yes" : "No"}</div>;
    };
    useEffect(() => {
        if (parseHeadFurtherDetails) {
        }
    }, [parseHeadFurtherDetails]);

    useEffect(() => {
        if (parseHeadFurtherDetails) {
            setloading(false);
        }
    }, [parseHeadFurtherDetails]);

    return (
        <div>
            <div className="card p-datatable-sm">
                <DataTable
                    header="Parser Head Details"
                    selection={selectedRow}
                    loading={loading}
                    onSelectionChange={(e) => setselectedRow(e.value)}
                    selectionMode="single"
                    dataKey="parserDetailId"
                    onRowSelect={onRowSelect}
                    paginator
                    rows={5}
                    value={parseHeadFurtherDetails}
                    paginatorTemplate="FirstPageLink PrevPageLink PageLinks NextPageLink LastPageLink CurrentPageReport RowsPerPageDropdown"
                    currentPageReportTemplate="Showing {first} to {last} of {totalRecords}"
                    emptyMessage="No data found."
                >
                    <Column filter field="refFieldName" header="Column Name" />
                    <Column filter field="fixedLength" header="Fixed Length" />
                    <Column filter field="maxLength" header="Max Length" />
                    <Column filter field="minLength" header="Min Length" />
                    <Column filter field="isMandatory" body={statusBody} header="Mandatory" />
                </DataTable>
            </div>
        </div>
    );
}

export default ParserHeadDetails;

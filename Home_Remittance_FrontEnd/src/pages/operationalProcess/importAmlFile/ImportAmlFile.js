import React, { useState, useEffect } from "react";
import { Button } from "primereact/button";
import { handlePostRequest } from "../../../services/PostTemplate";
import { useDispatch } from "react-redux";
import { DataTable } from "primereact/datatable";
import { Column } from "primereact/column";
import { handleGetRequest } from "../../../services/GetTemplate";

const ImportAmlFile = () => {
    const [data, setdata] = useState([]);
    const [loading, setloading] = useState([]);
    const dispatch = useDispatch();

    const handleAutoImport = async () => {
        setloading(true);
        const res = await dispatch(handlePostRequest({}, "/js_hr_fileupload/ofac/autoImportOFACList", true, true));
        if (res?.responsecode === 1) {
            getAmlData();
            setloading(false);
        }
    };
    const getAmlData = async () => {
        setloading(true);
        const response = await handleGetRequest(`js_hr_fileupload/ofac/getOFACList`);
        if (response.responsecode === 1) {
            setdata(response?.data);
            setloading(false);
        }
    };
    useEffect(() => {
        getAmlData();
    }, []); // eslint-disable-line

    return (
        <div className="card">
            <h3>Import AML File</h3>
            <div className="mt-6">
                <Button label="Auto Import AML/OFAC" icon="pi pi-inbox" onClick={handleAutoImport} />
            </div>
            {/* <div>
                <AmlData getAcList={getAcList} data={data} />
            </div> */}
            <div className="mt-6">
                <div className="card p-datatable-sm">
                    <DataTable
                        header="Aml Files Data"
                        paginator
                        loading={loading}
                        rows={12}
                        value={data}
                        paginatorTemplate="FirstPageLink PrevPageLink PageLinks NextPageLink LastPageLink CurrentPageReport RowsPerPageDropdown"
                        currentPageReportTemplate="Showing {first} to {last} of {totalRecords}"
                        emptyMessage="No data found."
                    >
                        <Column filter field="name" header="Name" />
                        <Column filter field="cnic" header="CNIC" />
                        <Column filter field="fatherName" header="Father Name" />
                        <Column filter field="district" header="District" />
                        <Column filter field="province" header="Province" />
                    </DataTable>
                </div>
            </div>
        </div>
    );
};

export default ImportAmlFile;

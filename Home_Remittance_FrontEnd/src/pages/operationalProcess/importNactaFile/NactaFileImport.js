import React, { useEffect, useState } from "react";
import { Button } from "primereact/button";
import { handlePostRequest } from "../../../services/PostTemplate";
import { useDispatch } from "react-redux";
import { Splitter, SplitterPanel } from "primereact/splitter";
import { Dropdown } from "primereact/dropdown";
import { handleGetRequest } from "../../../services/GetTemplate";
import { DataTable } from "primereact/datatable";
import { Column } from "primereact/column";

const NactaFileImport = () => {
    const [filesOptions, setfilesOptions] = useState([]);
    const [filesValue, setfilesValue] = useState(null);
    const [nactaListData, setNactaListData] = useState([]);
    const [loading, setloading] = useState(false);
    const dispatch = useDispatch();

    const getNactaData = async () => {
        setloading(true);
        const response = await handleGetRequest("js_hr_fileupload/nacta/getNACTAList");
        if (response?.responsecode === 1) {
            setNactaListData(Array.isArray(response?.data) ? response.data : []);
        } else {
            setNactaListData([]);
        }
        setloading(false);
    };

    const handleAutoImport = async () => {
        const response = await dispatch(handlePostRequest({}, "js_hr_fileupload/nacta/autoImportNACTAList", true));
        if (response?.responsecode === 1) {
            getNactaData();
        }
    };

    const handleManualImport = async () => {
        const res = await dispatch(handlePostRequest(filesValue, "js_hr_fileupload/nacta/uploadNACTAListFromDirectory", true));
        if (res?.responsecode === 1) {
            getNactaData();
        }
    };

    useEffect(() => {
        handleGetRequest("js_hr_fileupload/nacta/getNACTAFilesForUpload").then((res) => {
            setfilesOptions(Array.isArray(res?.data) ? res.data : []);
        });
    }, []); // eslint-disable-line

    useEffect(() => {
        getNactaData();
    }, []); // eslint-disable-line

    return (
        <div className="card">
            <h3>Nacta File Import</h3>

            <div className="card">
                <Splitter style={{ height: "200px" }} className="mb-5">
                    <SplitterPanel className="flex align-items-center justify-content-center">
                        <div className="p-fluid p-formgrid p-grid" style={{ width: "70%" }}>
                            <div className="p-field p-col-12 p-md-12">
                                <div className="p-field">
                                    <label className="Label__Text">
                                        File<span className="Label__Required">*</span>
                                    </label>
                                    <Dropdown value={filesValue} options={filesOptions} onChange={(e) => setfilesValue(e.target.value)} optionLabel="fileName" className="Dropdown__Round" />
                                </div>
                            </div>
                            <div className="mt-6">
                                <Button label="Manual  Import Nacta" onClick={handleManualImport} />
                            </div>
                        </div>
                    </SplitterPanel>
                    <SplitterPanel className="flex align-items-center justify-content-center">
                        <div>
                            <Button className="p-button-success" icon="pi pi-inbox" label="Auto Import Nacta" onClick={handleAutoImport} />
                        </div>
                    </SplitterPanel>
                </Splitter>
            </div>
            <div className="mt-6">
                <div className="card p-datatable-sm">
                    <DataTable
                        header="NACTA List Data"
                        loading={loading}
                        paginator
                        rows={12}
                        value={nactaListData}
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

export default NactaFileImport;

import React, { useState, useEffect, useRef } from "react";
import { Button } from "primereact/button";
import classNames from "classnames";
import { Dropdown } from "primereact/dropdown";
import { handleGetRequest } from "../../../services/GetTemplate";
import { handlePostRequest } from "../../../services/PostTemplate";
import { useDispatch } from "react-redux";
import { InputText } from "primereact/inputtext";
import { Dialog } from "primereact/dialog";

function UploadFile() {
    const [remittingValues, setRemittingValues] = useState([]);
    const [selectedRemittingValue, setSelectedRemittingValue] = useState();
    const [selectedRemittingId, setSelectedRemittingId] = useState(null);
    const [processDate, setProcessDate] = useState("");
    const [fileName, setfileName] = useState("");
    const upload = useRef(null);
    const [selectedFile, setselectedFile] = useState();
    const [displayDialog, setDisplayDialog] = useState(false);
    const [dialogTextFileUpload, setDialogTextFileUpload] = useState("");

    const dispatch = useDispatch();

    useEffect(() => {
        handleGetRequest("js_hr_fileupload/lov/lovFinInstBank").then((res) => {
            setRemittingValues(Array.isArray(res?.data) ? res.data : []);
        });
    }, []); // eslint-disable-line

    const handleExcelFile = async (selectedFile) => {
        const formData = new FormData();
        formData.append("multipartFile", selectedFile);
        formData.append("processDate", processDate);
        formData.append("fininstBankId", selectedRemittingId);
        const res = await dispatch(handlePostRequest(formData, "js_hr_fileupload/remitFileUpload/manualUploadFile", true));
        if (res?.responsecode === 1) {
            setDisplayDialog(false);
            setfileName("");
            setselectedFile("");
            setRemittingValues("");
            setProcessDate("");
        } else {
            await setDialogTextFileUpload(res?.data?.messages);
            setDisplayDialog(true);
        }
    };
    const onFileChange = (e) => {
        setselectedFile(e.target.files[0]);
        setfileName(e.target.files[0].name);
    };
    const renderFooter = () => {
        return (
            <div>
                <Button label="Ok" icon="pi pi-check" onClick={() => onHide()} autoFocus />
            </div>
        );
    };
    const onHide = (name) => {
        setDisplayDialog(false);
    };
    const handleResetFile = (e) => {
        setfileName("");
        setselectedFile("");
        window.location.reload();
    };

    return (
        <div className="card Card__Round">
            <Dialog className="Proccess__Transaction" header="File Upload Information" visible={displayDialog} footer={renderFooter("displayDialog")} onHide={() => setDisplayDialog(false)}>
                <p>{dialogTextFileUpload}</p>
            </Dialog>
            <div className="Form__Header">
                <h1>File Upload</h1>
            </div>
            <form className="fluid mt-2">
                <div className="fluid formgrid grid">
                    <div className="field col-12 md:col-6">
                        <div className="field">
                            <label htmlFor="remittingId" className={classNames("Label__Text")}>
                                Remitting Names<span className="Label__Required">*</span>
                            </label>
                            <Dropdown
                                filter
                                placeholder="Select Remittance"
                                optionLabel="description"
                                options={remittingValues}
                                value={selectedRemittingValue}
                                onChange={(e) => {
                                    setSelectedRemittingValue(e.target.value);
                                    setSelectedRemittingId(e.target.value.id);
                                }}
                                className={classNames("Dropdown__Round")}
                            />
                        </div>
                    </div>
                    <div className="field col-12 md:col-6">
                        <div className="field">
                            <label htmlFor="processDate" className={classNames("Label__Text")}>
                                Process Date<span className="Label__Required">*</span>
                            </label>
                            <InputText type="date" id="processDate" name="processDate" value={processDate} onChange={(e) => setProcessDate(e.target.value)} className="Input__Round" />
                        </div>
                    </div>
                    <div className="field col-12 md:col-6">
                        <input type="file" accept="application/vnd.openxmlformats-officedocument.spreadsheetml.sheet" ref={upload} style={{ display: "none" }} onChange={onFileChange} />
                        <Button
                            style={{ marginLeft: "10px" }}
                            className="button-style"
                            onClick={(e) => {
                                e.preventDefault();
                                upload.current.click();
                            }}
                            label="Upload"
                            icon="pi pi-file-excel"
                        />
                        <label className="mx-5">{fileName}</label>
                        {fileName !== "" ? (
                            <>
                                <Button
                                    style={{ marginLeft: "10px" }}
                                    className="p-button-raised p-button-success"
                                    onClick={(e) => {
                                        e.preventDefault();
                                        handleExcelFile(selectedFile);
                                    }}
                                    label="Submit"
                                    icon="pi pi-cloud-upload"
                                />
                                <Button
                                    style={{ marginLeft: "10px" }}
                                    className="p-button-raised p-button-danger"
                                    onClick={(e) => {
                                        handleResetFile(e);
                                    }}
                                    label="Reset"
                                />
                            </>
                        ) : null}
                    </div>
                </div>
            </form>
        </div>
    );
}

export default UploadFile;

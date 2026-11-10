import React, { useState, useEffect } from "react";
import { Button } from "primereact/button";
import { Toast } from "primereact/toast";
import { Badge } from "primereact/badge";
import { Panel } from "primereact/panel";
import "./ImageUpload.css";

function FileUpload({ handleFile }) {
    const toastBC = React.useRef(null);
    const upload = React.useRef(null);
    const [files, setfiles] = useState("");
    const [loading, setloading] = useState(false);

    const header = () => {
        return (
            <>
                <input
                    type="file"
                    accept=".xlsx, .xls, .csv"
                    style={{ display: "none" }}
                    ref={upload}
                    onChange={(e) => {
                        onFileChange(e);
                    }}
                />
                <Button
                    onClick={(e) => {
                        e.preventDefault();
                        upload.current.click();
                    }}
                >
                    Upload File
                </Button>
                &nbsp;
            </>
        );
    };

    const onFileChange = async (e) => {
        e.preventDefault();
        setloading(true);
        await getBase64(e.target.files[0]);
        setloading(false);
    };

    async function getBase64(file) {
        if (file.type === "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet") {
            var reader = new FileReader();
            reader.readAsDataURL(file);
            reader.onload = function () {
                let newfiles = JSON.parse(JSON.stringify(files));
                const filetype = file.type.split("/");
                newfiles = { fileBase64: reader.result, fileName: file.name, fileSize: file.size, fileExt: `.${filetype[1]}` };
                setfiles(newfiles);
            };
        } else {
            toastBC.current.show({ severity: "warn", summary: "This file type not supported" });
        }
    }

    // const handleRemove = (b64) => {
    //     let newArr = files.filter((file) => JSON.stringify(file?.fileBase64) !== JSON.stringify(b64));
    //     setfiles(newArr);
    // };

    // const handleChange = (b64, desc) => {
    //     let idx = files.findIndex((file) => JSON.stringify(file?.fileBase64) === JSON.stringify(b64));
    //     let newArr = JSON.parse(JSON.stringify(files));
    //     newArr[idx].filedescr = desc;
    //     setfiles(newArr);
    // };

    useEffect(() => {
        handleFile(files);
    }, [files, handleFile]);

    return (
        <Panel header={header}>
            <Toast ref={toastBC} position="bottom-center" />

            <div className="image-flex ">
                {files ? (
                    <div className="card" key={files?.fileBase64}>
                        <Badge value={files?.fileName} className="p-mr-4" />
                        <Badge value={"Size :" + (files?.fileSize / (1024 * 1024)).toFixed(2) + " MB"} />
                    </div>
                ) : (
                    <center>
                        <h6 className="image-placeholder">Please Upload File</h6>
                    </center>
                )}
                {loading && <h5>Loading</h5>}
            </div>
        </Panel>
    );
}

export default FileUpload;

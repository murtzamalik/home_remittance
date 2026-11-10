import React, { useRef, useState, useEffect } from "react";
import { Toast } from "primereact/toast";
import { FileUpload } from "primereact/fileupload";

const MultipleFileUpload = ({ handleUpload, isMulti, heading, isConvert64 }) => {
    const [allFiles, setAllFiles] = useState([]);
    const toast = useRef(null);

    const onUpload = async ({ files }) => {
        if (isConvert64) {
            files.forEach(async (file) => {
                getBase64(file).then((data) => {
                    setAllFiles([...allFiles, data]);
                });
            });
        } else {
            setAllFiles(files);
        }
    };

    const getBase64 = (file) => {
        return new Promise((resolve, reject) => {
            const reader = new FileReader();
            reader.readAsDataURL(file);
            reader.onload = () => resolve(reader.result);
            reader.onerror = (error) => reject(error);
        });
    };

    useEffect(() => {
        handleUpload(allFiles);
    }, [allFiles]); // eslint-disable-line

    return (
        <div>
            <Toast ref={toast}></Toast>

            <div className="card">
                <h5>{heading}</h5>
                <FileUpload name="demo[]" multiple accept="image/*" maxFileSize={1000000} emptyTemplate={<p className="m-0">Drag and drop files to here to upload.</p>} customUpload={true} uploadHandler={onUpload} chooseLabel="Select Files" mode={isMulti ? "advanced" : "basic"} />
            </div>
        </div>
    );
};

export default MultipleFileUpload;

package org.ais.hr.fileupload.dto;

import java.util.Date;

public class ManualUploadFileRequest {
    private String fileName;

    private long remitterId;

    private String filePath;

    private Date processDate;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public long getRemitterId() {
        return remitterId;
    }

    public void setRemitterId(long remitterId) {
        this.remitterId = remitterId;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public Date getProcessDate() {
        return processDate;
    }

    public void setProcessDate(Date processDate) {
        this.processDate = processDate;
    }
}

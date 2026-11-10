package org.ais.hr.fileupload.dto;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;
@Validated
public class FileUploading {
    MultipartFile multipartFile;
    String annexure;
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private  Date processDate;
    private int remittingId;

    public MultipartFile getMultipartFile() {
        return multipartFile;
    }

    public void setMultipartFile(MultipartFile multipartFile) {
        this.multipartFile = multipartFile;
    }

    public String getAnnexure() {
        return annexure;
    }

    public void setAnnexure(String annexure) {
        this.annexure = annexure;
    }

    public Date getProcessDate() {
        return processDate;
    }

    public void setProcessDate(Date processDate) {
        this.processDate = processDate;
    }

    public int getRemittingId() {
        return remittingId;
    }

    public void setRemittingId(int remittingId) {
        this.remittingId = remittingId;
    }
}

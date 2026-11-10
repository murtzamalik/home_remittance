package org.ais.hr.transactions.dto;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.math.BigDecimal;

public class Mt102InquiryResponse {
    String bankName;
    String fileRefNo;
    BigDecimal amount;
    String finInstDescr;
    BigDecimal noOfTrans;
    String file;

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getFileRefNo() {
        return fileRefNo;
    }

    public void setFileRefNo(String fileRefNo) {
        this.fileRefNo = fileRefNo;
    }


    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getFinInstDescr() {
        return finInstDescr;
    }

    public void setFinInstDescr(String finInstDescr) {
        this.finInstDescr = finInstDescr;
    }

    public BigDecimal getNoOfTrans() {
        return noOfTrans;
    }

    public void setNoOfTrans(BigDecimal noOfTrans) {
        this.noOfTrans = noOfTrans;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }
}

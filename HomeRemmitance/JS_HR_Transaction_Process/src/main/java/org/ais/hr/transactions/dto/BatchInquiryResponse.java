package org.ais.hr.transactions.dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class BatchInquiryResponse {


    private BigDecimal transHeadId;

    private String sourceFileName;

    private String fileDate;

    private String finInstDescr;

    private BigDecimal totalRecords;

    private BigDecimal totalAmount;

    private BigDecimal paidAmount;

    private BigDecimal remainingAmount;

    List<TransStatusCount> transStatusCounts = new ArrayList<>();

    public BigDecimal getTransHeadId() {
        return transHeadId;
    }

    public void setTransHeadId(BigDecimal transHeadId) {
        this.transHeadId = transHeadId;
    }

    public String getSourceFileName() {
        return sourceFileName;
    }

    public void setSourceFileName(String sourceFileName) {
        this.sourceFileName = sourceFileName;
    }

    public String getFileDate() {
        return fileDate;
    }

    public void setFileDate(String fileDate) {
        this.fileDate = fileDate;
    }

    public String getFinInstDescr() {
        return finInstDescr;
    }

    public void setFinInstDescr(String finInstDescr) {
        this.finInstDescr = finInstDescr;
    }

    public BigDecimal getTotalRecords() {
        return totalRecords;
    }

    public void setTotalRecords(BigDecimal totalRecords) {
        this.totalRecords = totalRecords;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public BigDecimal getPaidAmount() {
        return paidAmount;
    }

    public void setPaidAmount(BigDecimal paidAmount) {
        this.paidAmount = paidAmount;
    }

    public BigDecimal getRemainingAmount() {
        return remainingAmount;
    }

    public void setRemainingAmount(BigDecimal remainingAmount) {
        this.remainingAmount = remainingAmount;
    }

    public List<TransStatusCount> getTransStatusCounts() {
        return transStatusCounts;
    }

    public void setTransStatusCounts(List<TransStatusCount> transStatusCounts) {
        this.transStatusCounts = transStatusCounts;
    }
}

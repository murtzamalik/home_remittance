package org.ais.hr.transactions.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BatchInquiryRequest {
    private List<Long> fininstBankId;

    private Date fromDate;

    private Date toDate;

    private List<String> fileNames = new ArrayList<>();

    private String apiType;

    public List<Long> getFininstBankId() {
        return fininstBankId;
    }

    public void setFininstBankId(List<Long> fininstBankId) {
        this.fininstBankId = fininstBankId;
    }

    public Date getFromDate() {
        return fromDate;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public Date getToDate() {
        return toDate;
    }

    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }

    public List<String> getFileNames() {
        return fileNames;
    }

    public void setFileNames(List<String> fileNames) {
        this.fileNames = fileNames;
    }

    public String getApiType() {
        return apiType;
    }

    public void setApiType(String apiType) {
        this.apiType = apiType;
    }
}

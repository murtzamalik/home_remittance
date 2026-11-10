package org.ais.hr.transactions.dto;

public class Mt102InquiryRequest {
    long finInstId;
    String bankName;



    public long getFinInstId() {
        return finInstId;
    }

    public void setFinInstId(long finInstId) {
        this.finInstId = finInstId;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }
}

package org.ais.hr.transactions.dto;

import java.math.BigDecimal;

public class SingleTransactionInquiryRequest {


    private String amount;

    private String beneficiaryAccountNo;

    private String beneficiaryAccountTitle;

    private String beneficiaryBankName;

    private String beneficiaryBranchAdd1;

    private String beneficiaryBranchAdd2;

    private String beneficiaryBranchCode;

    private String beneficiaryCnic;

    private String beneficiaryName;

    private String beneficiaryOtherContact;

    private String remitterAddress;

    private String remitterName;

    private String remitterNumber;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatusDescr() {
        return statusDescr;
    }

    public void setStatusDescr(String statusDescr) {
        this.statusDescr = statusDescr;
    }





    public String getTransType() {
        return transType;
    }

    public void setTransType(String transType) {
        this.transType = transType;
    }

    private String status;

    private String statusDescr;




    private String transType;

    private String transactionReference;



    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getBeneficiaryAccountNo() {
        return beneficiaryAccountNo;
    }

    public void setBeneficiaryAccountNo(String beneficiaryAccountNo) {
        this.beneficiaryAccountNo = beneficiaryAccountNo;
    }

    public String getBeneficiaryAccountTitle() {
        return beneficiaryAccountTitle;
    }

    public void setBeneficiaryAccountTitle(String beneficiaryAccountTitle) {
        this.beneficiaryAccountTitle = beneficiaryAccountTitle;
    }

    public String getBeneficiaryBankName() {
        return beneficiaryBankName;
    }

    public void setBeneficiaryBankName(String beneficiaryBankName) {
        this.beneficiaryBankName = beneficiaryBankName;
    }

    public String getBeneficiaryBranchAdd1() {
        return beneficiaryBranchAdd1;
    }

    public void setBeneficiaryBranchAdd1(String beneficiaryBranchAdd1) {
        this.beneficiaryBranchAdd1 = beneficiaryBranchAdd1;
    }

    public String getBeneficiaryBranchAdd2() {
        return beneficiaryBranchAdd2;
    }

    public void setBeneficiaryBranchAdd2(String beneficiaryBranchAdd2) {
        this.beneficiaryBranchAdd2 = beneficiaryBranchAdd2;
    }

    public String getBeneficiaryBranchCode() {
        return beneficiaryBranchCode;
    }

    public void setBeneficiaryBranchCode(String beneficiaryBranchCode) {
        this.beneficiaryBranchCode = beneficiaryBranchCode;
    }

    public String getBeneficiaryCnic() {
        return beneficiaryCnic;
    }

    public void setBeneficiaryCnic(String beneficiaryCnic) {
        this.beneficiaryCnic = beneficiaryCnic;
    }

    public String getBeneficiaryName() {
        return beneficiaryName;
    }

    public void setBeneficiaryName(String beneficiaryName) {
        this.beneficiaryName = beneficiaryName;
    }

    public String getBeneficiaryOtherContact() {
        return beneficiaryOtherContact;
    }

    public void setBeneficiaryOtherContact(String beneficiaryOtherContact) {
        this.beneficiaryOtherContact = beneficiaryOtherContact;
    }

    public String getRemitterAddress() {
        return remitterAddress;
    }

    public void setRemitterAddress(String remitterAddress) {
        this.remitterAddress = remitterAddress;
    }

    public String getRemitterName() {
        return remitterName;
    }

    public void setRemitterName(String remitterName) {
        this.remitterName = remitterName;
    }

    public String getRemitterNumber() {
        return remitterNumber;
    }

    public void setRemitterNumber(String remitterNumber) {
        this.remitterNumber = remitterNumber;
    }

    public String getTransactionReference() {
        return transactionReference;
    }

    public void setTransactionReference(String transactionReference) {
        this.transactionReference = transactionReference;
    }
}

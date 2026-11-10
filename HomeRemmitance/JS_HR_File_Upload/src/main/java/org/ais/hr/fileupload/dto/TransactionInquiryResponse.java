package org.ais.hr.fileupload.dto;

import java.math.BigDecimal;
import java.util.Date;

public class TransactionInquiryResponse {
    String finInstCode;

    String finInstDescr;

    String remitterName;

    String remitterNumber;

    String remitterAddress;

    String remitterCountry;

    String transactionReference;

    BigDecimal amount;

    String beneficaryName;

    String beneficaryCnic;

    String beneficaryAccNo;

    String beneficaryAccTitle;

    String beneficaryBranchCode;

    String beneficaryOtherContact;

    String beneficaryBankName;

    String beneficaryBranchAdd1;

    String beneficaryBranchAdd2;

    String valueDate;

    String paymentDate;

    String transMode;

    public String getFinInstCode() {
        return finInstCode;
    }

    public void setFinInstCode(String finInstCode) {
        this.finInstCode = finInstCode;
    }

    public String getFinInstDescr() {
        return finInstDescr;
    }

    public void setFinInstDescr(String finInstDescr) {
        this.finInstDescr = finInstDescr;
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

    public String getRemitterAddress() {
        return remitterAddress;
    }

    public void setRemitterAddress(String remitterAddress) {
        this.remitterAddress = remitterAddress;
    }

    public String getRemitterCountry() {
        return remitterCountry;
    }

    public void setRemitterCountry(String remitterCountry) {
        this.remitterCountry = remitterCountry;
    }

    public String getTransactionReference() {
        return transactionReference;
    }

    public void setTransactionReference(String transactionReference) {
        this.transactionReference = transactionReference;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getBeneficaryName() {
        return beneficaryName;
    }

    public void setBeneficaryName(String beneficaryName) {
        this.beneficaryName = beneficaryName;
    }

    public String getBeneficaryCnic() {
        return beneficaryCnic;
    }

    public void setBeneficaryCnic(String beneficaryCnic) {
        this.beneficaryCnic = beneficaryCnic;
    }

    public String getBeneficaryAccNo() {
        return beneficaryAccNo;
    }

    public void setBeneficaryAccNo(String beneficaryAccNo) {
        this.beneficaryAccNo = beneficaryAccNo;
    }

    public String getBeneficaryAccTitle() {
        return beneficaryAccTitle;
    }

    public void setBeneficaryAccTitle(String beneficaryAccTitle) {
        this.beneficaryAccTitle = beneficaryAccTitle;
    }

    public String getBeneficaryBranchCode() {
        return beneficaryBranchCode;
    }

    public void setBeneficaryBranchCode(String beneficaryBranchCode) {
        this.beneficaryBranchCode = beneficaryBranchCode;
    }

    public String getBeneficaryOtherContact() {
        return beneficaryOtherContact;
    }

    public void setBeneficaryOtherContact(String beneficaryOtherContact) {
        this.beneficaryOtherContact = beneficaryOtherContact;
    }

    public String getBeneficaryBankName() {
        return beneficaryBankName;
    }

    public void setBeneficaryBankName(String beneficaryBankName) {
        this.beneficaryBankName = beneficaryBankName;
    }

    public String getBeneficaryBranchAdd1() {
        return beneficaryBranchAdd1;
    }

    public void setBeneficaryBranchAdd1(String beneficaryBranchAdd1) {
        this.beneficaryBranchAdd1 = beneficaryBranchAdd1;
    }

    public String getBeneficaryBranchAdd2() {
        return beneficaryBranchAdd2;
    }

    public void setBeneficaryBranchAdd2(String beneficaryBranchAdd2) {
        this.beneficaryBranchAdd2 = beneficaryBranchAdd2;
    }

    public String getValueDate() {
        return valueDate;
    }

    public void setValueDate(String valueDate) {
        this.valueDate = valueDate;
    }

    public String getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(String paymentDate) {
        this.paymentDate = paymentDate;
    }

    public String getTransMode() {
        return transMode;
    }

    public void setTransMode(String transMode) {
        this.transMode = transMode;
    }
}

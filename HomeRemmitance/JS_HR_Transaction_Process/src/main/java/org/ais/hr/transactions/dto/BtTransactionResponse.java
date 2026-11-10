package org.ais.hr.transactions.dto;

import java.math.BigDecimal;
import java.util.Date;

public class BtTransactionResponse {
    
    BigDecimal transDetialId;
    BigDecimal transHeadId;
    String remitterName;
    String remitterNumber;
    String remitterAddress;
    String transactionReference;
    String transactionNumber;
    BigDecimal amount;
    String beneficaryName;
    String beneficaryCnic;
    String beneficaryAccNo;
    String beneficaryBranchCode;
    String beneficaryOtherContact;
    String beneficaryBankName;
    String beneficaryBranchAdd1;
    String beneficaryBranchAdd2;
    String beneficaryAccTitle;
    String status;
    String statusDescr;
    String t24TransactionReference;
    String t24TransactionDescr;
    BigDecimal createUser;
    Date createDate;
    BigDecimal lastUpdateUser;
    Date lastUpdateDate;
    BigDecimal updateIndex;
    String transType;
    String transBt;
    String transAmd;
    Date t24TransactionDate;
    String remitterCountry;

    public BigDecimal getCreateUser() {
        return createUser;
    }

    public BigDecimal getLastUpdateUser() {
        return lastUpdateUser;
    }

    public BigDecimal getUpdateIndex() {
        return updateIndex;
    }

    public BigDecimal getTransDetialId() {
        return transDetialId;
    }

    public void setTransDetialId(BigDecimal transDetialId) {
        this.transDetialId = transDetialId;
    }

    public BigDecimal getTransHeadId() {
        return transHeadId;
    }

    public void setTransHeadId(BigDecimal transHeadId) {
        this.transHeadId = transHeadId;
    }

    public void setCreateUser(BigDecimal createUser) {
        this.createUser = createUser;
    }

    public void setLastUpdateUser(BigDecimal lastUpdateUser) {
        this.lastUpdateUser = lastUpdateUser;
    }

    public void setUpdateIndex(BigDecimal updateIndex) {
        this.updateIndex = updateIndex;
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

    public String getTransactionReference() {
        return transactionReference;
    }

    public void setTransactionReference(String transactionReference) {
        this.transactionReference = transactionReference;
    }

    public String getTransactionNumber() {
        return transactionNumber;
    }

    public void setTransactionNumber(String transactionNumber) {
        this.transactionNumber = transactionNumber;
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

    public String getBeneficaryAccTitle() {
        return beneficaryAccTitle;
    }

    public void setBeneficaryAccTitle(String beneficaryAccTitle) {
        this.beneficaryAccTitle = beneficaryAccTitle;
    }

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

    public String getT24TransactionReference() {
        return t24TransactionReference;
    }

    public void setT24TransactionReference(String t24TransactionReference) {
        this.t24TransactionReference = t24TransactionReference;
    }

    public String getT24TransactionDescr() {
        return t24TransactionDescr;
    }

    public void setT24TransactionDescr(String t24TransactionDescr) {
        this.t24TransactionDescr = t24TransactionDescr;
    }





    public String getTransType() {
        return transType;
    }

    public void setTransType(String transType) {
        this.transType = transType;
    }

    public String getTransBt() {
        return transBt;
    }

    public void setTransBt(String transBt) {
        this.transBt = transBt;
    }

    public String getTransAmd() {
        return transAmd;
    }

    public void setTransAmd(String transAmd) {
        this.transAmd = transAmd;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(Date lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    public Date getT24TransactionDate() {
        return t24TransactionDate;
    }

    public void setT24TransactionDate(Date t24TransactionDate) {
        this.t24TransactionDate = t24TransactionDate;
    }

    public String getRemitterCountry() {
        return remitterCountry;
    }

    public void setRemitterCountry(String remitterCountry) {
        this.remitterCountry = remitterCountry;
    }

}

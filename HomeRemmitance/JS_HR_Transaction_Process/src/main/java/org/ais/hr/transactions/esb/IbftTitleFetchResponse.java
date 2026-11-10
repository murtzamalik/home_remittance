package org.ais.hr.transactions.esb;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class IbftTitleFetchResponse {
    @JsonProperty("MTI")
    public String mTI;
    @JsonProperty("SystemsTraceAuditNumber_011")
    public String systemsTraceAuditNumber_011;
    @JsonProperty("PrimaryAccountNumber_002")
    public String primaryAccountNumber_002;
    @JsonProperty("MerchantType_018")
    public String merchantType_018;
    @JsonProperty("BankCode")
    public String bankCode;
    @JsonProperty("ResponseMessage")
    public String responseMessage;
    @JsonProperty("ResponseCode_039")
    public String responseCode_039;
    @JsonProperty("AccountIdentification1_102")
    public String accountIdentification1_102;
    @JsonProperty("ProcessingCode_003")
    public String processingCode_003;
    @JsonProperty("TimeLocalTransaction_012")
    public String timeLocalTransaction_012;
    @JsonProperty("DateLocalTransaction_013")
    public String dateLocalTransaction_013;
    @JsonProperty("UserId")
    public String userId;
    @JsonProperty("TransmissionDatetime_007")
    public String transmissionDatetime_007;
    @JsonProperty("AccountIdentification2_103")
    public String accountIdentification2_103;
    @JsonProperty("TransactionDescription_104")
    public String transactionDescription_104;
    @JsonProperty("CNIC")
    public String cNIC;
    @JsonProperty("AcquiringInstitutionIdentificationCode_032")
    public String acquiringInstitutionIdentificationCode_032;
    @JsonProperty("ReservedPrivate_120")
    public String reservedPrivate_120;
    @JsonProperty("ToAccountTitle")
    public String toAccountTitle;
    @JsonProperty("BranchName")
    String branchName;

    @JsonIgnore
    private String message;

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public String getmTI() {
        return mTI;
    }

    public void setmTI(String mTI) {
        this.mTI = mTI;
    }

    public String getSystemsTraceAuditNumber_011() {
        return systemsTraceAuditNumber_011;
    }

    public void setSystemsTraceAuditNumber_011(String systemsTraceAuditNumber_011) {
        this.systemsTraceAuditNumber_011 = systemsTraceAuditNumber_011;
    }

    public String getPrimaryAccountNumber_002() {
        return primaryAccountNumber_002;
    }

    public void setPrimaryAccountNumber_002(String primaryAccountNumber_002) {
        this.primaryAccountNumber_002 = primaryAccountNumber_002;
    }

    public String getMerchantType_018() {
        return merchantType_018;
    }

    public void setMerchantType_018(String merchantType_018) {
        this.merchantType_018 = merchantType_018;
    }

    public String getBankCode() {
        return bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

    public String getResponseMessage() {
        return responseMessage;
    }

    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }

    public String getResponseCode_039() {
        return responseCode_039;
    }

    public void setResponseCode_039(String responseCode_039) {
        this.responseCode_039 = responseCode_039;
    }

    public String getAccountIdentification1_102() {
        return accountIdentification1_102;
    }

    public void setAccountIdentification1_102(String accountIdentification1_102) {
        this.accountIdentification1_102 = accountIdentification1_102;
    }

    public String getProcessingCode_003() {
        return processingCode_003;
    }

    public void setProcessingCode_003(String processingCode_003) {
        this.processingCode_003 = processingCode_003;
    }

    public String getTimeLocalTransaction_012() {
        return timeLocalTransaction_012;
    }

    public void setTimeLocalTransaction_012(String timeLocalTransaction_012) {
        this.timeLocalTransaction_012 = timeLocalTransaction_012;
    }

    public String getDateLocalTransaction_013() {
        return dateLocalTransaction_013;
    }

    public void setDateLocalTransaction_013(String dateLocalTransaction_013) {
        this.dateLocalTransaction_013 = dateLocalTransaction_013;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getTransmissionDatetime_007() {
        return transmissionDatetime_007;
    }

    public void setTransmissionDatetime_007(String transmissionDatetime_007) {
        this.transmissionDatetime_007 = transmissionDatetime_007;
    }

    public String getAccountIdentification2_103() {
        return accountIdentification2_103;
    }

    public void setAccountIdentification2_103(String accountIdentification2_103) {
        this.accountIdentification2_103 = accountIdentification2_103;
    }

    public String getTransactionDescription_104() {
        return transactionDescription_104;
    }

    public void setTransactionDescription_104(String transactionDescription_104) {
        this.transactionDescription_104 = transactionDescription_104;
    }

    public String getcNIC() {
        return cNIC;
    }

    public void setcNIC(String cNIC) {
        this.cNIC = cNIC;
    }

    public String getAcquiringInstitutionIdentificationCode_032() {
        return acquiringInstitutionIdentificationCode_032;
    }

    public void setAcquiringInstitutionIdentificationCode_032(String acquiringInstitutionIdentificationCode_032) {
        this.acquiringInstitutionIdentificationCode_032 = acquiringInstitutionIdentificationCode_032;
    }

    public String getReservedPrivate_120() {
        return reservedPrivate_120;
    }

    public void setReservedPrivate_120(String reservedPrivate_120) {
        this.reservedPrivate_120 = reservedPrivate_120;
    }

    public String getToAccountTitle() {
        return toAccountTitle;
    }

    public void setToAccountTitle(String toAccountTitle) {
        this.toAccountTitle = toAccountTitle;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

package org.ais.hr.transactions.esb;

import com.fasterxml.jackson.annotation.JsonProperty;

public class IbftTitleFetchReq {
    @JsonProperty("MTI")
    String mti;
    @JsonProperty("PrimaryAccountNumber_002")
    String primaryAccountNumber_002;
    @JsonProperty("ProcessingCode_003")
    String processingCode_003;
    @JsonProperty("TransmissionDatetime_007")
    String transmissionDatetime_007;
    @JsonProperty("SystemsTraceAuditNumber_011")
    String systemsTraceAuditNumber_011;
    @JsonProperty("TimeLocalTransaction_012")
    String timeLocalTransaction_012;
    @JsonProperty("DateLocalTransaction_013")
    String dateLocalTransaction_013;
    @JsonProperty("MerchantType_018")
    String merchantType_018;
    @JsonProperty("AcquiringInstitutionIdentificationCode_032")
    String acquiringInstitutionIdentificationCode_032;
    @JsonProperty("AccountIdentification1_102")
    String accountIdentification1_102;
    @JsonProperty("AccountIdentification2_103")
    String accountIdentification2_103;
    @JsonProperty("TransactionDescription_104")
    String transactionDescription_104;
    @JsonProperty("BankCode")
    String bankCode;
    @JsonProperty("UserId")
    String userId;
    @JsonProperty("CNIC")
    String cnic;
    @JsonProperty("ReservedPrivate_120")
    String reservedPrivate_120;

    public String getMti() {
        return mti;
    }

    public void setMti(String mti) {
        this.mti = mti;
    }

    public String getPrimaryAccountNumber_002() {
        return primaryAccountNumber_002;
    }

    public void setPrimaryAccountNumber_002(String primaryAccountNumber_002) {
        this.primaryAccountNumber_002 = primaryAccountNumber_002;
    }

    public String getProcessingCode_003() {
        return processingCode_003;
    }

    public void setProcessingCode_003(String processingCode_003) {
        this.processingCode_003 = processingCode_003;
    }

    public String getTransmissionDatetime_007() {
        return transmissionDatetime_007;
    }

    public void setTransmissionDatetime_007(String transmissionDatetime_007) {
        this.transmissionDatetime_007 = transmissionDatetime_007;
    }

    public String getSystemsTraceAuditNumber_011() {
        return systemsTraceAuditNumber_011;
    }

    public void setSystemsTraceAuditNumber_011(String systemsTraceAuditNumber_011) {
        this.systemsTraceAuditNumber_011 = systemsTraceAuditNumber_011;
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

    public String getMerchantType_018() {
        return merchantType_018;
    }

    public void setMerchantType_018(String merchantType_018) {
        this.merchantType_018 = merchantType_018;
    }

    public String getAcquiringInstitutionIdentificationCode_032() {
        return acquiringInstitutionIdentificationCode_032;
    }

    public void setAcquiringInstitutionIdentificationCode_032(String acquiringInstitutionIdentificationCode_032) {
        this.acquiringInstitutionIdentificationCode_032 = acquiringInstitutionIdentificationCode_032;
    }

    public String getAccountIdentification1_102() {
        return accountIdentification1_102;
    }

    public void setAccountIdentification1_102(String accountIdentification1_102) {
        this.accountIdentification1_102 = accountIdentification1_102;
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

    public String getBankCode() {
        return bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCnic() {
        return cnic;
    }

    public void setCnic(String cnic) {
        this.cnic = cnic;
    }

    public String getReservedPrivate_120() {
        return reservedPrivate_120;
    }

    public void setReservedPrivate_120(String reservedPrivate_120) {
        this.reservedPrivate_120 = reservedPrivate_120;
    }
}

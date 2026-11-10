package org.ais.hr.transactions.esb;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class IbftPaymentResponse {
    @JsonProperty("ProcessingCode_003")
    public String processingCode_003;
    @JsonProperty("TimeLocalTransaction_012")
    public String timeLocalTransaction_012;
    @JsonProperty("MTI")
    public String mti;
    @JsonProperty("DateLocalTransaction_013")
    public String dateLocalTransaction_013;
    @JsonProperty("UserId")
    public String userId;
    @JsonProperty("SystemsTraceAuditNumber_011")
    public String systemsTraceAuditNumber_011;
    @JsonProperty("PrimaryAccountNumber_002")
    public String primaryAccountNumber_002;
    @JsonProperty("MerchantType_018")
    public String merchantType_018;
    @JsonProperty("TransmissionDatetime_007")
    public String transmissionDatetime_007;
    @JsonProperty("CNIC")
    public String cnic;
    @JsonProperty("ResponseCode_039")
    public String responseCode_039;
    @JsonProperty("RelationshipNo")
    public String relationshipNo;
    @JsonProperty("BankName")
    public String bankName;
    @JsonIgnore
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
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

    public String getMti() {
        return mti;
    }

    public void setMti(String mti) {
        this.mti = mti;
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

    public String getTransmissionDatetime_007() {
        return transmissionDatetime_007;
    }

    public void setTransmissionDatetime_007(String transmissionDatetime_007) {
        this.transmissionDatetime_007 = transmissionDatetime_007;
    }

    public String getCnic() {
        return cnic;
    }

    public void setCnic(String cnic) {
        this.cnic = cnic;
    }

    public String getResponseCode_039() {
        return responseCode_039;
    }

    public void setResponseCode_039(String responseCode_039) {
        this.responseCode_039 = responseCode_039;
    }

    public String getRelationshipNo() {
        return relationshipNo;
    }

    public void setRelationshipNo(String relationshipNo) {
        this.relationshipNo = relationshipNo;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }
}

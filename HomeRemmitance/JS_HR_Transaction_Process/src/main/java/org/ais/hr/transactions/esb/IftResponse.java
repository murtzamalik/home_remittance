package org.ais.hr.transactions.esb;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class IftResponse {
    @JsonProperty("MTI")
    public String mTI;
    @JsonProperty("SystemsTraceAuditNumber_011")
    public String systemsTraceAuditNumber_011;
    @JsonProperty("MerchantType_018")
    public String merchantType_018;
    @JsonProperty("ResponseCode_039")
    public String responseCode_039;
    @JsonProperty("AccountIdentification1_102")
    public String accountIdentification1_102;
    @JsonProperty("ProcessingCode_003")
    public String processingCode_003;
    @JsonProperty("TimeLocalTransaction_012")
    public String timeLocalTransaction_012;
    @JsonProperty("AdditionalResponseData_044")
    public String additionalResponseData_044;
    @JsonProperty("DateLocalTransaction_013")
    public String dateLocalTransaction_013;
    @JsonProperty("CurrencyCodeSettlement_050")
    public String currencyCodeSettlement_050;
    @JsonProperty("TransmissionDatetime_007")
    public String transmissionDatetime_007;
    @JsonProperty("AccountIdentification2_103")
    public String accountIdentification2_103;
    @JsonProperty("AmountTransaction_004")
    public String amountTransaction_004;
    @JsonProperty("CurrencyCodeTransaction_049")
    public String currencyCodeTransaction_049;

    @JsonIgnore
    private String message;

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

    public String getMerchantType_018() {
        return merchantType_018;
    }

    public void setMerchantType_018(String merchantType_018) {
        this.merchantType_018 = merchantType_018;
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

    public String getAdditionalResponseData_044() {
        return additionalResponseData_044;
    }

    public void setAdditionalResponseData_044(String additionalResponseData_044) {
        this.additionalResponseData_044 = additionalResponseData_044;
    }

    public String getDateLocalTransaction_013() {
        return dateLocalTransaction_013;
    }

    public void setDateLocalTransaction_013(String dateLocalTransaction_013) {
        this.dateLocalTransaction_013 = dateLocalTransaction_013;
    }

    public String getCurrencyCodeSettlement_050() {
        return currencyCodeSettlement_050;
    }

    public void setCurrencyCodeSettlement_050(String currencyCodeSettlement_050) {
        this.currencyCodeSettlement_050 = currencyCodeSettlement_050;
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

    public String getAmountTransaction_004() {
        return amountTransaction_004;
    }

    public void setAmountTransaction_004(String amountTransaction_004) {
        this.amountTransaction_004 = amountTransaction_004;
    }

    public String getCurrencyCodeTransaction_049() {
        return currencyCodeTransaction_049;
    }

    public void setCurrencyCodeTransaction_049(String currencyCodeTransaction_049) {
        this.currencyCodeTransaction_049 = currencyCodeTransaction_049;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

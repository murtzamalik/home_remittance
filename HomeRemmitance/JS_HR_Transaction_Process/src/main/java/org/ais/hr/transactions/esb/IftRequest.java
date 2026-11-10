package org.ais.hr.transactions.esb;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;


public class IftRequest {

    @JsonProperty("MTI")
     String mti;
    @JsonProperty("ProcessingCode_003")
     String processingCode_003;
    @JsonProperty("AmountTransaction_004")
     String amountTransaction_004;
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
    @JsonProperty("CurrencyCodeTransaction_049")
     String currencyCodeTransaction_049;
    @JsonProperty("CurrencyCodeSettlement_050")
     String currencyCodeSettlement_050;
    @JsonProperty("AccountIdentification1_102")
     String accountIdentification1_102;
    @JsonProperty("AccountIdentification2_103")
     String accountIdentification2_103;
    @JsonProperty("ReservedPrivate_127")
     String reservedPrivate_127;

    public String getMti() {
        return mti;
    }

    public void setMti(String mti) {
        this.mti = mti;
    }

    public String getProcessingCode_003() {
        return processingCode_003;
    }

    public void setProcessingCode_003(String processingCode_003) {
        this.processingCode_003 = processingCode_003;
    }

    public String getAmountTransaction_004() {
        return amountTransaction_004;
    }

    public void setAmountTransaction_004(String amountTransaction_004) {
        this.amountTransaction_004 = amountTransaction_004;
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

    public String getCurrencyCodeTransaction_049() {
        return currencyCodeTransaction_049;
    }

    public void setCurrencyCodeTransaction_049(String currencyCodeTransaction_049) {
        this.currencyCodeTransaction_049 = currencyCodeTransaction_049;
    }

    public String getCurrencyCodeSettlement_050() {
        return currencyCodeSettlement_050;
    }

    public void setCurrencyCodeSettlement_050(String currencyCodeSettlement_050) {
        this.currencyCodeSettlement_050 = currencyCodeSettlement_050;
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

    public String getReservedPrivate_127() {
        return reservedPrivate_127;
    }

    public void setReservedPrivate_127(String reservedPrivate_127) {
        this.reservedPrivate_127 = reservedPrivate_127;
    }
}

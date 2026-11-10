package org.ais.hr.transactions.esb;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class IftTitleFetchResponse {

    @JsonProperty("MTI")
    private String mTI;
    @JsonProperty("SystemsTraceAuditNumber_011")
    private String systemsTraceAuditNumber011;
    @JsonProperty("PrimaryAccountNumber_002")
    private String primaryAccountNumber002;
    @JsonProperty("MerchantType_018")
    private String merchantType018;
    @JsonProperty("ResponseCode_039")
    private String responseCode039;
    @JsonProperty("AccountIdentification1_102")
    private String accountIdentification1102;
    @JsonProperty("ProcessingCode_003")
    private String processingCode003;
    @JsonProperty("TimeLocalTransaction_012")
    private String timeLocalTransaction012;
    @JsonProperty("DateLocalTransaction_013")
    private String dateLocalTransaction013;
    @JsonProperty("CurrencyCodeSettlement_050")
    private String currencyCodeSettlement050;
    @JsonProperty("TransmissionDatetime_007")
    private String transmissionDatetime007;
    @JsonProperty("AccountIdentification2_103")
    private String accountIdentification2103;
    @JsonProperty("ReservedPrivate_120")
    private String reservedPrivate120;
    @JsonProperty("CurrencyCodeTransaction_049")
    private String currencyCodeTransaction049;

    @JsonIgnore
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


    public String getmTI() {
        return mTI;
    }

    public void setmTI(String mTI) {
        this.mTI = mTI;
    }

    public String getSystemsTraceAuditNumber011() {
        return systemsTraceAuditNumber011;
    }

    public void setSystemsTraceAuditNumber011(String systemsTraceAuditNumber011) {
        this.systemsTraceAuditNumber011 = systemsTraceAuditNumber011;
    }

    public String getPrimaryAccountNumber002() {
        return primaryAccountNumber002;
    }

    public void setPrimaryAccountNumber002(String primaryAccountNumber002) {
        this.primaryAccountNumber002 = primaryAccountNumber002;
    }

    public String getMerchantType018() {
        return merchantType018;
    }

    public void setMerchantType018(String merchantType018) {
        this.merchantType018 = merchantType018;
    }

    public String getResponseCode039() {
        return responseCode039;
    }

    public void setResponseCode039(String responseCode039) {
        this.responseCode039 = responseCode039;
    }

    public String getAccountIdentification1102() {
        return accountIdentification1102;
    }

    public void setAccountIdentification1102(String accountIdentification1102) {
        this.accountIdentification1102 = accountIdentification1102;
    }

    public String getProcessingCode003() {
        return processingCode003;
    }

    public void setProcessingCode003(String processingCode003) {
        this.processingCode003 = processingCode003;
    }

    public String getTimeLocalTransaction012() {
        return timeLocalTransaction012;
    }

    public void setTimeLocalTransaction012(String timeLocalTransaction012) {
        this.timeLocalTransaction012 = timeLocalTransaction012;
    }

    public String getDateLocalTransaction013() {
        return dateLocalTransaction013;
    }

    public void setDateLocalTransaction013(String dateLocalTransaction013) {
        this.dateLocalTransaction013 = dateLocalTransaction013;
    }

    public String getCurrencyCodeSettlement050() {
        return currencyCodeSettlement050;
    }

    public void setCurrencyCodeSettlement050(String currencyCodeSettlement050) {
        this.currencyCodeSettlement050 = currencyCodeSettlement050;
    }

    public String getTransmissionDatetime007() {
        return transmissionDatetime007;
    }

    public void setTransmissionDatetime007(String transmissionDatetime007) {
        this.transmissionDatetime007 = transmissionDatetime007;
    }

    public String getAccountIdentification2103() {
        return accountIdentification2103;
    }

    public void setAccountIdentification2103(String accountIdentification2103) {
        this.accountIdentification2103 = accountIdentification2103;
    }

    public String getReservedPrivate120() {
        return reservedPrivate120;
    }

    public void setReservedPrivate120(String reservedPrivate120) {
        this.reservedPrivate120 = reservedPrivate120;
    }

    public String getCurrencyCodeTransaction049() {
        return currencyCodeTransaction049;
    }

    public void setCurrencyCodeTransaction049(String currencyCodeTransaction049) {
        this.currencyCodeTransaction049 = currencyCodeTransaction049;
    }
}

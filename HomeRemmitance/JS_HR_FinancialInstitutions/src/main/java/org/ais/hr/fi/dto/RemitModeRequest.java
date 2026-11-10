package org.ais.hr.fi.dto;


import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.math.BigDecimal;
import java.util.Date;


public class RemitModeRequest {

    private BigDecimal amountCapping;

    private String isActive;

    private String remitCode;

    private String remitDefault;

    private String remitDescr;



    public BigDecimal getAmountCapping() {
        return amountCapping;
    }

    public void setAmountCapping(BigDecimal amountCapping) {
        this.amountCapping = amountCapping;
    }

    public String getRemitCode() {
        return remitCode;
    }

    public void setRemitCode(String remitCode) {
        this.remitCode = remitCode;
    }

    public String getRemitDefault() {
        return remitDefault;
    }

    public void setRemitDefault(String remitDefault) {
        this.remitDefault = remitDefault;
    }

    public String getRemitDescr() {
        return remitDescr;
    }

    public void setRemitDescr(String remitDescr) {
        this.remitDescr = remitDescr;
    }

    public String getIsActive() {
        return isActive;
    }

    public void setIsActive(String isActive) {
        this.isActive = isActive;
    }
}

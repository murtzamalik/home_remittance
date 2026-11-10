package org.ais.hr.fi.dto;

import java.math.BigDecimal;

public class UpdateRemitModeRequest {

    private long remitModeId;

    private BigDecimal amountCapping;

    private String isActive;

    private String remitCode;

    private String remitDefault;

    private String remitDescr;

    public long getRemitModeId() {
        return remitModeId;
    }

    public void setRemitModeId(long remitModeId) {
        this.remitModeId = remitModeId;
    }

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

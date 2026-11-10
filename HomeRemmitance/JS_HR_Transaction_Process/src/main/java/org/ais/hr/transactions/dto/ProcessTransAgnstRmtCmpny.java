package org.ais.hr.transactions.dto;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * Author: Murtaza Malik
 * Date: 3/26/2022
 * Time: 2:07 PM
 * Project : login
 */
@Validated
public class ProcessTransAgnstRmtCmpny {


    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fromDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date toDate;

    private List<Long> remitIds;

    @NotNull
    @NotEmpty(message = "Kindly Select Transaction Type")
    private List<String> transType;

    public Date getFromDate() {
        return fromDate;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public Date getToDate() {
        return toDate;
    }

    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }

    public List<Long> getRemitIds() {
        return remitIds;
    }

    public void setRemitIds(List<Long> remitIds) {
        this.remitIds = remitIds;
    }

    public List<String> getTransType() {
        return transType;
    }

    public void setTransType(List<String> transType) {
        this.transType = transType;
    }
}

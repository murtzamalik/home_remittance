package org.ais.hr.transactions.dto;

import java.math.BigDecimal;

/**
 * Created by IntelliJ IDEA.
 * Author: Murtaza Malik
 * Date: 6/13/2022
 * Time: 4:56 PM
 * Project : Transaction_Process
 */
public class TransStatusCount {

    private String statusCode;

    private String statusDescr;

    private BigDecimal qty;


    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public String getStatusDescr() {
        return statusDescr;
    }

    public void setStatusDescr(String statusDescr) {
        this.statusDescr = statusDescr;
    }

    public BigDecimal getQty() {
        return qty;
    }

    public void setQty(BigDecimal qty) {
        this.qty = qty;
    }
}

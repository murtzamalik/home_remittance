package org.ais.hr.fi.dto;

import java.math.BigDecimal;

/**
 * Created by IntelliJ IDEA.
 * Author: Murtaza Malik
 * Date: 4/11/2022
 * Time: 12:38 PM
 * Project : fi
 */
public class ParserDetailUpdateRequest {

    private long parserDetailId;

    private String fixedLength;

    private String isMandatory;

    private BigDecimal maxLength;

    private BigDecimal minLength;

    private String refFieldName;

    private long sequence;

    private long transFieldId;

    public long getParserDetailId() {
        return parserDetailId;
    }

    public void setParserDetailId(long parserDetailId) {
        this.parserDetailId = parserDetailId;
    }

    public String getFixedLength() {
        return fixedLength;
    }

    public void setFixedLength(String fixedLength) {
        this.fixedLength = fixedLength;
    }

    public String getIsMandatory() {
        return isMandatory;
    }

    public void setIsMandatory(String isMandatory) {
        this.isMandatory = isMandatory;
    }

    public BigDecimal getMaxLength() {
        return maxLength;
    }

    public void setMaxLength(BigDecimal maxLength) {
        this.maxLength = maxLength;
    }

    public BigDecimal getMinLength() {
        return minLength;
    }

    public void setMinLength(BigDecimal minLength) {
        this.minLength = minLength;
    }

    public String getRefFieldName() {
        return refFieldName;
    }

    public void setRefFieldName(String refFieldName) {
        this.refFieldName = refFieldName;
    }

    public long getSequence() {
        return sequence;
    }

    public void setSequence(long sequence) {
        this.sequence = sequence;
    }

    public long getTransFieldId() {
        return transFieldId;
    }

    public void setTransFieldId(long transFieldId) {
        this.transFieldId = transFieldId;
    }
}

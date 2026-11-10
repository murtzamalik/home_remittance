package org.ais.hr.fi.dto;

/**
 * Created by IntelliJ IDEA.
 * Author: Murtaza Malik
 * Date: 4/11/2022
 * Time: 12:37 PM
 * Project : fi
 */
public class ParserHeadUpdateRequest {

    private long parserHeadId;

    private String code;

    private String description;

    private String isActive;

    public long getParserHeadId() {
        return parserHeadId;
    }

    public void setParserHeadId(long parserHeadId) {
        this.parserHeadId = parserHeadId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIsActive() {
        return isActive;
    }

    public void setIsActive(String isActive) {
        this.isActive = isActive;
    }
}

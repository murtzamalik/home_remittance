package org.ais.hr.user_management.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the LKP_COUNTRY database table.
 * 
 */
@Entity
@Table(name="LKP_COUNTRY")
@NamedQuery(name="LkpCountry.findAll", query="SELECT l FROM LkpCountry l")
public class LkpCountry implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="LKP_COUNTRY_COUNTRYID_GENERATOR", sequenceName="LKP_COUNTRY_SEQ",allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="LKP_COUNTRY_COUNTRYID_GENERATOR")
	@Column(name="COUNTRY_ID")
	private long countryId;

	@Column(name="COUNTRY_CODE")
	private String countryCode;

	@Column(name="COUNTRY_DEFAULT")
	private String countryDefault;

	@Column(name="COUNTRY_DESCR")
	private String countryDescr;

	@Column(name="COUNTRY_LEVEL")
	private String countryLevel;

	@Column(name="COUNTRY_SHORT")
	private String countryShort;

	@Column(name="COUNTRY_TYPE")
	private String countryType;

	@Temporal(TemporalType.DATE)
	private Date createdate;

	private BigDecimal createuser;

	@Column(name="IS_ACTIVE")
	private String isActive;

	@Column(name="ISO_STANDARD")
	private String isoStandard;

	@Temporal(TemporalType.DATE)
	private Date lastupdatedate;

	private BigDecimal lastupdateuser;

	@Column(name="LIMIT_BC")
	private BigDecimal limitBc;

	@Column(name="SBP_CODE")
	private String sbpCode;

	private BigDecimal updateindex;


	//bi-directional many-to-one association to LkpCountry
	@ManyToOne
	@JoinColumn(name="PARENT_COUNTRY_ID")
	@JsonIgnore
	private LkpCountry lkpCountry;


	public LkpCountry() {
	}

	public long getCountryId() {
		return this.countryId;
	}

	public void setCountryId(long countryId) {
		this.countryId = countryId;
	}

	public String getCountryCode() {
		return this.countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public String getCountryDefault() {
		return this.countryDefault;
	}

	public void setCountryDefault(String countryDefault) {
		this.countryDefault = countryDefault;
	}

	public String getCountryDescr() {
		return this.countryDescr;
	}

	public void setCountryDescr(String countryDescr) {
		this.countryDescr = countryDescr;
	}

	public String getCountryLevel() {
		return this.countryLevel;
	}

	public void setCountryLevel(String countryLevel) {
		this.countryLevel = countryLevel;
	}

	public String getCountryShort() {
		return this.countryShort;
	}

	public void setCountryShort(String countryShort) {
		this.countryShort = countryShort;
	}

	public String getCountryType() {
		return this.countryType;
	}

	public void setCountryType(String countryType) {
		this.countryType = countryType;
	}

	public Date getCreatedate() {
		return this.createdate;
	}

	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}

	public BigDecimal getCreateuser() {
		return this.createuser;
	}

	public void setCreateuser(BigDecimal createuser) {
		this.createuser = createuser;
	}

	public String getIsActive() {
		return this.isActive;
	}

	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}

	public String getIsoStandard() {
		return this.isoStandard;
	}

	public void setIsoStandard(String isoStandard) {
		this.isoStandard = isoStandard;
	}

	public Date getLastupdatedate() {
		return this.lastupdatedate;
	}

	public void setLastupdatedate(Date lastupdatedate) {
		this.lastupdatedate = lastupdatedate;
	}

	public BigDecimal getLastupdateuser() {
		return this.lastupdateuser;
	}

	public void setLastupdateuser(BigDecimal lastupdateuser) {
		this.lastupdateuser = lastupdateuser;
	}

	public BigDecimal getLimitBc() {
		return this.limitBc;
	}

	public void setLimitBc(BigDecimal limitBc) {
		this.limitBc = limitBc;
	}

	public String getSbpCode() {
		return this.sbpCode;
	}

	public void setSbpCode(String sbpCode) {
		this.sbpCode = sbpCode;
	}

	public BigDecimal getUpdateindex() {
		return this.updateindex;
	}

	public void setUpdateindex(BigDecimal updateindex) {
		this.updateindex = updateindex;
	}

	public LkpCountry getLkpCountry() {
		return this.lkpCountry;
	}

	public void setLkpCountry(LkpCountry lkpCountry) {
		this.lkpCountry = lkpCountry;
	}

}
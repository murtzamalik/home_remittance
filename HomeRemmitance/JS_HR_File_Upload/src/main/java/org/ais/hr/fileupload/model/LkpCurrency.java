package org.ais.hr.fileupload.model;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the LKP_CURRENCY database table.
 * 
 */
@Entity
@Table(name="LKP_CURRENCY")
@NamedQuery(name="LkpCurrency.findAll", query="SELECT l FROM LkpCurrency l")
public class LkpCurrency implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="LKP_CURRENCY_CURRENCYID_GENERATOR", sequenceName="LKP_CURRENCY_SEQ",allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="LKP_CURRENCY_CURRENCYID_GENERATOR")
	@Column(name="CURRENCY_ID")
	private long currencyId;

	@Temporal(TemporalType.DATE)
	private Date createdate;

	private BigDecimal createuser;

	@Column(name="CURRENCY_CODE")
	private String currencyCode;

	@Column(name="CURRENCY_DECIMAL_PLACES")
	private BigDecimal currencyDecimalPlaces;

	@Column(name="CURRENCY_DEFAULT")
	private String currencyDefault;

	@Column(name="CURRENCY_DESCR")
	private String currencyDescr;

	@Column(name="CURRENCY_SHORT")
	private String currencyShort;

	@Column(name="CURRENCY_SWIFT")
	private String currencySwift;

	@Column(name="CURRENCY_SYMBOL")
	private String currencySymbol;

	@Column(name="CURRENCY_TYPE")
	private String currencyType;

	@Column(name="DECIMAL_ALLOWED")
	private String decimalAllowed;

	@Column(name="FCY_DECIMAL_PLACES")
	private BigDecimal fcyDecimalPlaces;

	@Column(name="IS_ACTIVE")
	private String isActive;

	@Column(name="ISO_STANDARD")
	private String isoStandard;

	@Temporal(TemporalType.DATE)
	private Date lastupdatedate;

	private BigDecimal lastupdateuser;

	@Column(name="LIMIT_BC")
	private BigDecimal limitBc;

	@Column(name="RND_DECIMAL_PLACES")
	private BigDecimal rndDecimalPlaces;

	@Column(name="SBP_CODE")
	private String sbpCode;

	private BigDecimal updateindex;

	//bi-directional many-to-one association to LkpCountry
	@ManyToOne
	@JoinColumn(name="COUNTRY_ID")
	private LkpCountry lkpCountry;

	public LkpCurrency() {
	}

	public long getCurrencyId() {
		return this.currencyId;
	}

	public void setCurrencyId(long currencyId) {
		this.currencyId = currencyId;
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

	public String getCurrencyCode() {
		return this.currencyCode;
	}

	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}

	public BigDecimal getCurrencyDecimalPlaces() {
		return this.currencyDecimalPlaces;
	}

	public void setCurrencyDecimalPlaces(BigDecimal currencyDecimalPlaces) {
		this.currencyDecimalPlaces = currencyDecimalPlaces;
	}

	public String getCurrencyDefault() {
		return this.currencyDefault;
	}

	public void setCurrencyDefault(String currencyDefault) {
		this.currencyDefault = currencyDefault;
	}

	public String getCurrencyDescr() {
		return this.currencyDescr;
	}

	public void setCurrencyDescr(String currencyDescr) {
		this.currencyDescr = currencyDescr;
	}

	public String getCurrencyShort() {
		return this.currencyShort;
	}

	public void setCurrencyShort(String currencyShort) {
		this.currencyShort = currencyShort;
	}

	public String getCurrencySwift() {
		return this.currencySwift;
	}

	public void setCurrencySwift(String currencySwift) {
		this.currencySwift = currencySwift;
	}

	public String getCurrencySymbol() {
		return this.currencySymbol;
	}

	public void setCurrencySymbol(String currencySymbol) {
		this.currencySymbol = currencySymbol;
	}

	public String getCurrencyType() {
		return this.currencyType;
	}

	public void setCurrencyType(String currencyType) {
		this.currencyType = currencyType;
	}

	public String getDecimalAllowed() {
		return this.decimalAllowed;
	}

	public void setDecimalAllowed(String decimalAllowed) {
		this.decimalAllowed = decimalAllowed;
	}

	public BigDecimal getFcyDecimalPlaces() {
		return this.fcyDecimalPlaces;
	}

	public void setFcyDecimalPlaces(BigDecimal fcyDecimalPlaces) {
		this.fcyDecimalPlaces = fcyDecimalPlaces;
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

	public BigDecimal getRndDecimalPlaces() {
		return this.rndDecimalPlaces;
	}

	public void setRndDecimalPlaces(BigDecimal rndDecimalPlaces) {
		this.rndDecimalPlaces = rndDecimalPlaces;
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
package org.ais.hr.transactions.model;

import javax.persistence.*;
import java.io.Serializable;
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

	//bi-directional many-to-one association to LkpCity
	@OneToMany(mappedBy="lkpCountry")
	private List<LkpCity> lkpCities;

	//bi-directional many-to-one association to LkpCountry
	@ManyToOne
	@JoinColumn(name="PARENT_COUNTRY_ID")
	private LkpCountry lkpCountry;

	//bi-directional many-to-one association to LkpCountry
	@OneToMany(mappedBy="lkpCountry")
	private List<LkpCountry> lkpCountries;

	//bi-directional many-to-one association to LkpCurrency
	@OneToMany(mappedBy="lkpCountry")
	private List<LkpCurrency> lkpCurrencies;

	//bi-directional many-to-one association to TblFininstBank
	@OneToMany(mappedBy="lkpCountry1")
	private List<TblFininstBank> tblFininstBanks1;

	//bi-directional many-to-one association to TblFininstBank
	@OneToMany(mappedBy="lkpCountry2")
	private List<TblFininstBank> tblFininstBanks2;

	//bi-directional many-to-one association to TblLocation
	@OneToMany(mappedBy="lkpCountry")
	private List<TblLocation> tblLocations;

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

	public List<LkpCity> getLkpCities() {
		return this.lkpCities;
	}

	public void setLkpCities(List<LkpCity> lkpCities) {
		this.lkpCities = lkpCities;
	}

	public LkpCity addLkpCity(LkpCity lkpCity) {
		getLkpCities().add(lkpCity);
		lkpCity.setLkpCountry(this);

		return lkpCity;
	}

	public LkpCity removeLkpCity(LkpCity lkpCity) {
		getLkpCities().remove(lkpCity);
		lkpCity.setLkpCountry(null);

		return lkpCity;
	}

	public LkpCountry getLkpCountry() {
		return this.lkpCountry;
	}

	public void setLkpCountry(LkpCountry lkpCountry) {
		this.lkpCountry = lkpCountry;
	}

	public List<LkpCountry> getLkpCountries() {
		return this.lkpCountries;
	}

	public void setLkpCountries(List<LkpCountry> lkpCountries) {
		this.lkpCountries = lkpCountries;
	}

	public LkpCountry addLkpCountry(LkpCountry lkpCountry) {
		getLkpCountries().add(lkpCountry);
		lkpCountry.setLkpCountry(this);

		return lkpCountry;
	}

	public LkpCountry removeLkpCountry(LkpCountry lkpCountry) {
		getLkpCountries().remove(lkpCountry);
		lkpCountry.setLkpCountry(null);

		return lkpCountry;
	}

	public List<LkpCurrency> getLkpCurrencies() {
		return this.lkpCurrencies;
	}

	public void setLkpCurrencies(List<LkpCurrency> lkpCurrencies) {
		this.lkpCurrencies = lkpCurrencies;
	}

	public LkpCurrency addLkpCurrency(LkpCurrency lkpCurrency) {
		getLkpCurrencies().add(lkpCurrency);
		lkpCurrency.setLkpCountry(this);

		return lkpCurrency;
	}

	public LkpCurrency removeLkpCurrency(LkpCurrency lkpCurrency) {
		getLkpCurrencies().remove(lkpCurrency);
		lkpCurrency.setLkpCountry(null);

		return lkpCurrency;
	}

	public List<TblFininstBank> getTblFininstBanks1() {
		return this.tblFininstBanks1;
	}

	public void setTblFininstBanks1(List<TblFininstBank> tblFininstBanks1) {
		this.tblFininstBanks1 = tblFininstBanks1;
	}

	public TblFininstBank addTblFininstBanks1(TblFininstBank tblFininstBanks1) {
		getTblFininstBanks1().add(tblFininstBanks1);
		tblFininstBanks1.setLkpCountry1(this);

		return tblFininstBanks1;
	}

	public TblFininstBank removeTblFininstBanks1(TblFininstBank tblFininstBanks1) {
		getTblFininstBanks1().remove(tblFininstBanks1);
		tblFininstBanks1.setLkpCountry1(null);

		return tblFininstBanks1;
	}

	public List<TblFininstBank> getTblFininstBanks2() {
		return this.tblFininstBanks2;
	}

	public void setTblFininstBanks2(List<TblFininstBank> tblFininstBanks2) {
		this.tblFininstBanks2 = tblFininstBanks2;
	}

	public TblFininstBank addTblFininstBanks2(TblFininstBank tblFininstBanks2) {
		getTblFininstBanks2().add(tblFininstBanks2);
		tblFininstBanks2.setLkpCountry2(this);

		return tblFininstBanks2;
	}

	public TblFininstBank removeTblFininstBanks2(TblFininstBank tblFininstBanks2) {
		getTblFininstBanks2().remove(tblFininstBanks2);
		tblFininstBanks2.setLkpCountry2(null);

		return tblFininstBanks2;
	}

	public List<TblLocation> getTblLocations() {
		return this.tblLocations;
	}

	public void setTblLocations(List<TblLocation> tblLocations) {
		this.tblLocations = tblLocations;
	}

	public TblLocation addTblLocation(TblLocation tblLocation) {
		getTblLocations().add(tblLocation);
		tblLocation.setLkpCountry(this);

		return tblLocation;
	}

	public TblLocation removeTblLocation(TblLocation tblLocation) {
		getTblLocations().remove(tblLocation);
		tblLocation.setLkpCountry(null);

		return tblLocation;
	}

}
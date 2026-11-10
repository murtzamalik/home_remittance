package org.ais.hr.fi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the LKP_CITY database table.
 * 
 */
@Entity
@Table(name="LKP_CITY")
@NamedQuery(name="LkpCity.findAll", query="SELECT l FROM LkpCity l")
public class LkpCity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="LKP_CITY_CITYID_GENERATOR", sequenceName="LKP_CITY_SEQ",allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="LKP_CITY_CITYID_GENERATOR")
	@Column(name="CITY_ID")
	private long cityId;

	@Column(name="CITY_CODE")
	private String cityCode;

	@Column(name="CITY_DEFAULT")
	private String cityDefault;

	@Column(name="CITY_DESCR")
	private String cityDescr;

	@Column(name="CITY_LEVEL")
	private String cityLevel;

	@Column(name="CITY_SHORT")
	private String cityShort;

	@Column(name="CITY_TYPE")
	private String cityType;

	@Temporal(TemporalType.DATE)
	private Date createdate;

	private BigDecimal createuser;

	@Column(name="IS_ACTIVE")
	private String isActive;

	@Temporal(TemporalType.DATE)
	private Date lastupdatedate;

	private BigDecimal lastupdateuser;

	@Column(name="SBP_CODE")
	private String sbpCode;

	private BigDecimal updateindex;

	//bi-directional many-to-one association to LkpCity
	@ManyToOne
	@JoinColumn(name="PARENT_CITY_ID")
	private LkpCity lkpCity;

	//bi-directional many-to-one association to LkpCity
	@JsonIgnore
	@OneToMany(mappedBy="lkpCity")
	private List<LkpCity> lkpCities;

	//bi-directional many-to-one association to LkpCountry
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="COUNTRY_ID")
	private LkpCountry lkpCountry;

	//bi-directional many-to-one association to LkpProvince
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="PROVINCE_ID")
	private LkpProvince lkpProvince;

	//bi-directional many-to-one association to TblFininstBank
	@JsonIgnore
	@OneToMany(mappedBy="lkpCity")
	private List<TblFininstBank> tblFininstBanks;

	//bi-directional many-to-one association to TblLocation
	@JsonIgnore
	@OneToMany(mappedBy="lkpCity")
	private List<TblLocation> tblLocations;

	public LkpCity() {
	}

	public long getCityId() {
		return this.cityId;
	}

	public void setCityId(long cityId) {
		this.cityId = cityId;
	}

	public String getCityCode() {
		return this.cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}

	public String getCityDefault() {
		return this.cityDefault;
	}

	public void setCityDefault(String cityDefault) {
		this.cityDefault = cityDefault;
	}

	public String getCityDescr() {
		return this.cityDescr;
	}

	public void setCityDescr(String cityDescr) {
		this.cityDescr = cityDescr;
	}

	public String getCityLevel() {
		return this.cityLevel;
	}

	public void setCityLevel(String cityLevel) {
		this.cityLevel = cityLevel;
	}

	public String getCityShort() {
		return this.cityShort;
	}

	public void setCityShort(String cityShort) {
		this.cityShort = cityShort;
	}

	public String getCityType() {
		return this.cityType;
	}

	public void setCityType(String cityType) {
		this.cityType = cityType;
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

	public LkpCity getLkpCity() {
		return this.lkpCity;
	}

	public void setLkpCity(LkpCity lkpCity) {
		this.lkpCity = lkpCity;
	}

	public List<LkpCity> getLkpCities() {
		return this.lkpCities;
	}

	public void setLkpCities(List<LkpCity> lkpCities) {
		this.lkpCities = lkpCities;
	}

	public LkpCity addLkpCity(LkpCity lkpCity) {
		getLkpCities().add(lkpCity);
		lkpCity.setLkpCity(this);

		return lkpCity;
	}

	public LkpCity removeLkpCity(LkpCity lkpCity) {
		getLkpCities().remove(lkpCity);
		lkpCity.setLkpCity(null);

		return lkpCity;
	}

	public LkpCountry getLkpCountry() {
		return this.lkpCountry;
	}

	public void setLkpCountry(LkpCountry lkpCountry) {
		this.lkpCountry = lkpCountry;
	}

	public LkpProvince getLkpProvince() {
		return this.lkpProvince;
	}

	public void setLkpProvince(LkpProvince lkpProvince) {
		this.lkpProvince = lkpProvince;
	}

	public List<TblFininstBank> getTblFininstBanks() {
		return this.tblFininstBanks;
	}

	public void setTblFininstBanks(List<TblFininstBank> tblFininstBanks) {
		this.tblFininstBanks = tblFininstBanks;
	}

	public TblFininstBank addTblFininstBank(TblFininstBank tblFininstBank) {
		getTblFininstBanks().add(tblFininstBank);
		tblFininstBank.setLkpCity(this);

		return tblFininstBank;
	}

	public TblFininstBank removeTblFininstBank(TblFininstBank tblFininstBank) {
		getTblFininstBanks().remove(tblFininstBank);
		tblFininstBank.setLkpCity(null);

		return tblFininstBank;
	}

	public List<TblLocation> getTblLocations() {
		return this.tblLocations;
	}

	public void setTblLocations(List<TblLocation> tblLocations) {
		this.tblLocations = tblLocations;
	}

	public TblLocation addTblLocation(TblLocation tblLocation) {
		getTblLocations().add(tblLocation);
		tblLocation.setLkpCity(this);

		return tblLocation;
	}

	public TblLocation removeTblLocation(TblLocation tblLocation) {
		getTblLocations().remove(tblLocation);
		tblLocation.setLkpCity(null);

		return tblLocation;
	}

}
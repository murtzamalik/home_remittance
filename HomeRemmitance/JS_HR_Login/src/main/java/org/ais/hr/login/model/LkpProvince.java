package org.ais.hr.login.model;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the LKP_PROVINCE database table.
 * 
 */
@Entity
@Table(name="LKP_PROVINCE")
@NamedQuery(name="LkpProvince.findAll", query="SELECT l FROM LkpProvince l")
public class LkpProvince implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="LKP_PROVINCE_PROVINCEID_GENERATOR", sequenceName="LKP_PROVINCE_SEQ",allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="LKP_PROVINCE_PROVINCEID_GENERATOR")
	@Column(name="PROVINCE_ID")
	private long provinceId;

	@Temporal(TemporalType.DATE)
	private Date createdate;

	private BigDecimal createuser;

	@Column(name="IS_ACTIVE")
	private String isActive;

	@Temporal(TemporalType.DATE)
	private Date lastupdatedate;

	private BigDecimal lastupdateuser;

	@Column(name="PROVINCE_CODE")
	private String provinceCode;

	@Column(name="PROVINCE_DEFAULT")
	private String provinceDefault;

	@Column(name="PROVINCE_DESCR")
	private String provinceDescr;

	@Column(name="PROVINCE_SHORT")
	private String provinceShort;

	@Column(name="PROVINCE_TYPE")
	private String provinceType;

	@Column(name="SBP_CODE")
	private String sbpCode;

	private BigDecimal updateindex;

	//bi-directional many-to-one association to LkpCity
	@OneToMany(mappedBy="lkpProvince")
	private List<LkpCity> lkpCities;

	public LkpProvince() {
	}

	public long getProvinceId() {
		return this.provinceId;
	}

	public void setProvinceId(long provinceId) {
		this.provinceId = provinceId;
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

	public String getProvinceCode() {
		return this.provinceCode;
	}

	public void setProvinceCode(String provinceCode) {
		this.provinceCode = provinceCode;
	}

	public String getProvinceDefault() {
		return this.provinceDefault;
	}

	public void setProvinceDefault(String provinceDefault) {
		this.provinceDefault = provinceDefault;
	}

	public String getProvinceDescr() {
		return this.provinceDescr;
	}

	public void setProvinceDescr(String provinceDescr) {
		this.provinceDescr = provinceDescr;
	}

	public String getProvinceShort() {
		return this.provinceShort;
	}

	public void setProvinceShort(String provinceShort) {
		this.provinceShort = provinceShort;
	}

	public String getProvinceType() {
		return this.provinceType;
	}

	public void setProvinceType(String provinceType) {
		this.provinceType = provinceType;
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
		lkpCity.setLkpProvince(this);

		return lkpCity;
	}

	public LkpCity removeLkpCity(LkpCity lkpCity) {
		getLkpCities().remove(lkpCity);
		lkpCity.setLkpProvince(null);

		return lkpCity;
	}

}
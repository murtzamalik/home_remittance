package org.ais.hr.fi.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the LKP_REASON database table.
 * 
 */
@Entity
@Table(name="LKP_REASON")
@NamedQuery(name="LkpReason.findAll", query="SELECT l FROM LkpReason l")
public class LkpReason implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="LKP_REASON_REASONID_GENERATOR", sequenceName="LKP_REASON_SEQ",allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="LKP_REASON_REASONID_GENERATOR")
	@Column(name="REASON_ID")
	private long reasonId;

	@Temporal(TemporalType.DATE)
	private Date createdate;

	private BigDecimal createuser;

	@Column(name="IS_ACTIVE")
	private String isActive;

	@Temporal(TemporalType.DATE)
	private Date lastupdatedate;

	private BigDecimal lastupdateuser;

	@Column(name="REASON_CODE")
	private String reasonCode;

	@Column(name="REASON_DEFAULT")
	private String reasonDefault;

	@Column(name="REASON_DESCR")
	private String reasonDescr;

	@Column(name="REASON_SHORT")
	private String reasonShort;

	@Column(name="REASON_TYPE")
	private String reasonType;

	private BigDecimal updateindex;

	//bi-directional many-to-one association to TblUser
	@OneToMany(mappedBy="lkpReason1")
	private List<TblUser> tblUsers1;

	//bi-directional many-to-one association to TblUser
	@OneToMany(mappedBy="lkpReason2")
	private List<TblUser> tblUsers2;

	public LkpReason() {
	}

	public long getReasonId() {
		return this.reasonId;
	}

	public void setReasonId(long reasonId) {
		this.reasonId = reasonId;
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

	public String getReasonCode() {
		return this.reasonCode;
	}

	public void setReasonCode(String reasonCode) {
		this.reasonCode = reasonCode;
	}

	public String getReasonDefault() {
		return this.reasonDefault;
	}

	public void setReasonDefault(String reasonDefault) {
		this.reasonDefault = reasonDefault;
	}

	public String getReasonDescr() {
		return this.reasonDescr;
	}

	public void setReasonDescr(String reasonDescr) {
		this.reasonDescr = reasonDescr;
	}

	public String getReasonShort() {
		return this.reasonShort;
	}

	public void setReasonShort(String reasonShort) {
		this.reasonShort = reasonShort;
	}

	public String getReasonType() {
		return this.reasonType;
	}

	public void setReasonType(String reasonType) {
		this.reasonType = reasonType;
	}

	public BigDecimal getUpdateindex() {
		return this.updateindex;
	}

	public void setUpdateindex(BigDecimal updateindex) {
		this.updateindex = updateindex;
	}

	public List<TblUser> getTblUsers1() {
		return this.tblUsers1;
	}

	public void setTblUsers1(List<TblUser> tblUsers1) {
		this.tblUsers1 = tblUsers1;
	}

	public TblUser addTblUsers1(TblUser tblUsers1) {
		getTblUsers1().add(tblUsers1);
		tblUsers1.setLkpReason1(this);

		return tblUsers1;
	}

	public TblUser removeTblUsers1(TblUser tblUsers1) {
		getTblUsers1().remove(tblUsers1);
		tblUsers1.setLkpReason1(null);

		return tblUsers1;
	}

	public List<TblUser> getTblUsers2() {
		return this.tblUsers2;
	}

	public void setTblUsers2(List<TblUser> tblUsers2) {
		this.tblUsers2 = tblUsers2;
	}

	public TblUser addTblUsers2(TblUser tblUsers2) {
		getTblUsers2().add(tblUsers2);
		tblUsers2.setLkpReason2(this);

		return tblUsers2;
	}

	public TblUser removeTblUsers2(TblUser tblUsers2) {
		getTblUsers2().remove(tblUsers2);
		tblUsers2.setLkpReason2(null);

		return tblUsers2;
	}

}
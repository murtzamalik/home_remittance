package org.ais.hr.transactions.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the TBL_FININST_GL database table.
 * 
 */
@Entity
@Table(name="TBL_FININST_GL")
@NamedQuery(name="TblFininstGl.findAll", query="SELECT t FROM TblFininstGl t")
public class TblFininstGl implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TBL_FININST_GL_FININSTGLID_GENERATOR", sequenceName="TBL_FININST_GL_SEQ",allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TBL_FININST_GL_FININSTGLID_GENERATOR")
	@Column(name="FININST_GL_ID")
	private long fininstGlId;

	@Temporal(TemporalType.DATE)
	private Date createdate;

	private BigDecimal createuser;

	@Column(name="CURRENCY_ID")
	private BigDecimal currencyId;

	@Column(name="IS_ACTIVE")
	private String isActive;

	@Temporal(TemporalType.DATE)
	private Date lastupdatedate;

	private BigDecimal lastupdateuser;

	private BigDecimal updateindex;

	//bi-directional many-to-one association to TblFininstBank
	@ManyToOne
	@JoinColumn(name="FININST_BANK_ID")
	private TblFininstBank tblFininstBank;

	//bi-directional many-to-one association to TblGlAccount
	@ManyToOne
	@JoinColumn(name="GL_ACCOUNT_ID")
	private TblGlAccount tblGlAccount;

	public TblFininstGl() {
	}

	public long getFininstGlId() {
		return this.fininstGlId;
	}

	public void setFininstGlId(long fininstGlId) {
		this.fininstGlId = fininstGlId;
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

	public BigDecimal getCurrencyId() {
		return this.currencyId;
	}

	public void setCurrencyId(BigDecimal currencyId) {
		this.currencyId = currencyId;
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

	public BigDecimal getUpdateindex() {
		return this.updateindex;
	}

	public void setUpdateindex(BigDecimal updateindex) {
		this.updateindex = updateindex;
	}

	public TblFininstBank getTblFininstBank() {
		return this.tblFininstBank;
	}

	public void setTblFininstBank(TblFininstBank tblFininstBank) {
		this.tblFininstBank = tblFininstBank;
	}

	public TblGlAccount getTblGlAccount() {
		return this.tblGlAccount;
	}

	public void setTblGlAccount(TblGlAccount tblGlAccount) {
		this.tblGlAccount = tblGlAccount;
	}

}
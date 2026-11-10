package org.ais.hr.transactions.model;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the LKP_GL_ACCOUNT_TYPE database table.
 * 
 */
@Entity
@Table(name="LKP_GL_ACCOUNT_TYPE")
@NamedQuery(name="LkpGlAccountType.findAll", query="SELECT l FROM LkpGlAccountType l")
public class LkpGlAccountType implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="LKP_GL_ACCOUNT_TYPE_GLACCOUNTTYPEID_GENERATOR", sequenceName="LKP_GL_ACCOUNT_TYPE_SEQ",allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="LKP_GL_ACCOUNT_TYPE_GLACCOUNTTYPEID_GENERATOR")
	@Column(name="GL_ACCOUNT_TYPE_ID")
	private long glAccountTypeId;

	@Column(name="BRO_FORWARD")
	private String broForward;

	private String category;

	@Temporal(TemporalType.DATE)
	private Date createdate;

	private BigDecimal createuser;

	@Column(name="GL_ACCOUNT_TYPE")
	private String glAccountType;

	@Column(name="GL_ACCOUNT_TYPE_CODE")
	private String glAccountTypeCode;

	@Column(name="GL_ACCOUNT_TYPE_DESCR")
	private String glAccountTypeDescr;

	@Column(name="GL_ACCOUNT_TYPE_SHORT")
	private String glAccountTypeShort;

	@Column(name="IS_ACTIVE")
	private String isActive;

	@Column(name="IS_DEFAULT")
	private String isDefault;

	@Temporal(TemporalType.DATE)
	private Date lastupdatedate;

	private BigDecimal lastupdateuser;

	private String nature;

	private BigDecimal updateindex;

	//bi-directional many-to-one association to TblGlAccount
	@OneToMany(mappedBy="lkpGlAccountType")
	private List<TblGlAccount> tblGlAccounts;

	public LkpGlAccountType() {
	}

	public long getGlAccountTypeId() {
		return this.glAccountTypeId;
	}

	public void setGlAccountTypeId(long glAccountTypeId) {
		this.glAccountTypeId = glAccountTypeId;
	}

	public String getBroForward() {
		return this.broForward;
	}

	public void setBroForward(String broForward) {
		this.broForward = broForward;
	}

	public String getCategory() {
		return this.category;
	}

	public void setCategory(String category) {
		this.category = category;
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

	public String getGlAccountType() {
		return this.glAccountType;
	}

	public void setGlAccountType(String glAccountType) {
		this.glAccountType = glAccountType;
	}

	public String getGlAccountTypeCode() {
		return this.glAccountTypeCode;
	}

	public void setGlAccountTypeCode(String glAccountTypeCode) {
		this.glAccountTypeCode = glAccountTypeCode;
	}

	public String getGlAccountTypeDescr() {
		return this.glAccountTypeDescr;
	}

	public void setGlAccountTypeDescr(String glAccountTypeDescr) {
		this.glAccountTypeDescr = glAccountTypeDescr;
	}

	public String getGlAccountTypeShort() {
		return this.glAccountTypeShort;
	}

	public void setGlAccountTypeShort(String glAccountTypeShort) {
		this.glAccountTypeShort = glAccountTypeShort;
	}

	public String getIsActive() {
		return this.isActive;
	}

	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}

	public String getIsDefault() {
		return this.isDefault;
	}

	public void setIsDefault(String isDefault) {
		this.isDefault = isDefault;
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

	public String getNature() {
		return this.nature;
	}

	public void setNature(String nature) {
		this.nature = nature;
	}

	public BigDecimal getUpdateindex() {
		return this.updateindex;
	}

	public void setUpdateindex(BigDecimal updateindex) {
		this.updateindex = updateindex;
	}

	public List<TblGlAccount> getTblGlAccounts() {
		return this.tblGlAccounts;
	}

	public void setTblGlAccounts(List<TblGlAccount> tblGlAccounts) {
		this.tblGlAccounts = tblGlAccounts;
	}

	public TblGlAccount addTblGlAccount(TblGlAccount tblGlAccount) {
		getTblGlAccounts().add(tblGlAccount);
		tblGlAccount.setLkpGlAccountType(this);

		return tblGlAccount;
	}

	public TblGlAccount removeTblGlAccount(TblGlAccount tblGlAccount) {
		getTblGlAccounts().remove(tblGlAccount);
		tblGlAccount.setLkpGlAccountType(null);

		return tblGlAccount;
	}

}
package org.ais.hr.fileupload.model;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the TBL_GL_ACCOUNT database table.
 * 
 */
@Entity
@Table(name="TBL_GL_ACCOUNT")
@NamedQuery(name="TblGlAccount.findAll", query="SELECT t FROM TblGlAccount t")
public class TblGlAccount implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TBL_GL_ACCOUNT_GLACCOUNTID_GENERATOR", sequenceName="TBL_GL_ACCOUNT_SEQ",allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TBL_GL_ACCOUNT_GLACCOUNTID_GENERATOR")
	@Column(name="GL_ACCOUNT_ID")
	private long glAccountId;

	@Temporal(TemporalType.DATE)
	private Date createdate;

	private BigDecimal createuser;

	@Column(name="FC_ALLOWED")
	private String fcAllowed;

	@Column(name="GL_ACCOUNT_CODE")
	private String glAccountCode;

	@Column(name="GL_ACCOUNT_DESCR")
	private String glAccountDescr;

	@Column(name="GL_ACCOUNT_SHORT")
	private String glAccountShort;

	@Column(name="GL_ACCOUNT_TYPE")
	private String glAccountType;

	@Column(name="IS_ACTIVE")
	private String isActive;

	@Temporal(TemporalType.DATE)
	private Date lastupdatedate;

	private BigDecimal lastupdateuser;

	private BigDecimal updateindex;

	//bi-directional many-to-one association to TblFininstBank
	@OneToMany(mappedBy="tblGlAccount")
	private List<TblFininstBank> tblFininstBanks;

	//bi-directional many-to-one association to TblFininstGl
	@OneToMany(mappedBy="tblGlAccount")
	private List<TblFininstGl> tblFininstGls;

	//bi-directional many-to-one association to LkpGlAccountType
	@ManyToOne
	@JoinColumn(name="GL_ACCOUNT_TYPE_ID")
	private LkpGlAccountType lkpGlAccountType;

	//bi-directional many-to-one association to TblGlAccount
	@ManyToOne
	@JoinColumn(name="PARENT_GL_ACCOUNT_ID")
	private TblGlAccount tblGlAccount;

	//bi-directional many-to-one association to TblGlAccount
	@OneToMany(mappedBy="tblGlAccount")
	private List<TblGlAccount> tblGlAccounts;

	//bi-directional many-to-one association to TblOrganization
	@ManyToOne
	@JoinColumn(name="ORGANIZATION_ID")
	private TblOrganization tblOrganization;

	//bi-directional many-to-one association to TblLocation
	@OneToMany(mappedBy="tblGlAccount")
	private List<TblLocation> tblLocations;

	public TblGlAccount() {
	}

	public long getGlAccountId() {
		return this.glAccountId;
	}

	public void setGlAccountId(long glAccountId) {
		this.glAccountId = glAccountId;
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

	public String getFcAllowed() {
		return this.fcAllowed;
	}

	public void setFcAllowed(String fcAllowed) {
		this.fcAllowed = fcAllowed;
	}

	public String getGlAccountCode() {
		return this.glAccountCode;
	}

	public void setGlAccountCode(String glAccountCode) {
		this.glAccountCode = glAccountCode;
	}

	public String getGlAccountDescr() {
		return this.glAccountDescr;
	}

	public void setGlAccountDescr(String glAccountDescr) {
		this.glAccountDescr = glAccountDescr;
	}

	public String getGlAccountShort() {
		return this.glAccountShort;
	}

	public void setGlAccountShort(String glAccountShort) {
		this.glAccountShort = glAccountShort;
	}

	public String getGlAccountType() {
		return this.glAccountType;
	}

	public void setGlAccountType(String glAccountType) {
		this.glAccountType = glAccountType;
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

	public List<TblFininstBank> getTblFininstBanks() {
		return this.tblFininstBanks;
	}

	public void setTblFininstBanks(List<TblFininstBank> tblFininstBanks) {
		this.tblFininstBanks = tblFininstBanks;
	}

	public TblFininstBank addTblFininstBank(TblFininstBank tblFininstBank) {
		getTblFininstBanks().add(tblFininstBank);
		tblFininstBank.setTblGlAccount(this);

		return tblFininstBank;
	}

	public TblFininstBank removeTblFininstBank(TblFininstBank tblFininstBank) {
		getTblFininstBanks().remove(tblFininstBank);
		tblFininstBank.setTblGlAccount(null);

		return tblFininstBank;
	}

	public List<TblFininstGl> getTblFininstGls() {
		return this.tblFininstGls;
	}

	public void setTblFininstGls(List<TblFininstGl> tblFininstGls) {
		this.tblFininstGls = tblFininstGls;
	}

	public TblFininstGl addTblFininstGl(TblFininstGl tblFininstGl) {
		getTblFininstGls().add(tblFininstGl);
		tblFininstGl.setTblGlAccount(this);

		return tblFininstGl;
	}

	public TblFininstGl removeTblFininstGl(TblFininstGl tblFininstGl) {
		getTblFininstGls().remove(tblFininstGl);
		tblFininstGl.setTblGlAccount(null);

		return tblFininstGl;
	}

	public LkpGlAccountType getLkpGlAccountType() {
		return this.lkpGlAccountType;
	}

	public void setLkpGlAccountType(LkpGlAccountType lkpGlAccountType) {
		this.lkpGlAccountType = lkpGlAccountType;
	}

	public TblGlAccount getTblGlAccount() {
		return this.tblGlAccount;
	}

	public void setTblGlAccount(TblGlAccount tblGlAccount) {
		this.tblGlAccount = tblGlAccount;
	}

	public List<TblGlAccount> getTblGlAccounts() {
		return this.tblGlAccounts;
	}

	public void setTblGlAccounts(List<TblGlAccount> tblGlAccounts) {
		this.tblGlAccounts = tblGlAccounts;
	}

	public TblGlAccount addTblGlAccount(TblGlAccount tblGlAccount) {
		getTblGlAccounts().add(tblGlAccount);
		tblGlAccount.setTblGlAccount(this);

		return tblGlAccount;
	}

	public TblGlAccount removeTblGlAccount(TblGlAccount tblGlAccount) {
		getTblGlAccounts().remove(tblGlAccount);
		tblGlAccount.setTblGlAccount(null);

		return tblGlAccount;
	}

	public TblOrganization getTblOrganization() {
		return this.tblOrganization;
	}

	public void setTblOrganization(TblOrganization tblOrganization) {
		this.tblOrganization = tblOrganization;
	}

	public List<TblLocation> getTblLocations() {
		return this.tblLocations;
	}

	public void setTblLocations(List<TblLocation> tblLocations) {
		this.tblLocations = tblLocations;
	}

	public TblLocation addTblLocation(TblLocation tblLocation) {
		getTblLocations().add(tblLocation);
		tblLocation.setTblGlAccount(this);

		return tblLocation;
	}

	public TblLocation removeTblLocation(TblLocation tblLocation) {
		getTblLocations().remove(tblLocation);
		tblLocation.setTblGlAccount(null);

		return tblLocation;
	}

}
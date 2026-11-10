package org.ais.hr.user_management.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the TBL_ROLE_RIGHTS database table.
 * 
 */
@Entity
@Table(name="TBL_ROLE_RIGHTS")
@NamedQuery(name="TblRoleRight.findAll", query="SELECT t FROM TblRoleRight t")
public class TblRoleRight implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TBL_ROLE_RIGHTS_ROLERIGHTSID_GENERATOR", sequenceName="TBL_ROLE_RIGHTS_SEQ",allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TBL_ROLE_RIGHTS_ROLERIGHTSID_GENERATOR")
	@Column(name="ROLE_RIGHTS_ID")
	private long roleRightsId;

	@Column(name="AUTHORIZE_ALLOWED")
	private String authorizeAllowed;

	@Temporal(TemporalType.DATE)
	@Column(name="CHECK_DATE")
	private Date checkDate;

	@Column(name="CHECKER_COMMENTS")
	private String checkerComments;

	@Column(name="CHECKER_ID")
	private BigDecimal checkerId;

	@Temporal(TemporalType.DATE)
	private Date createdate;

	private BigDecimal createuser;

	@Column(name="HIDE_YN")
	private String hideYn;

	@Column(name="INSERT_ALLOWED")
	private String insertAllowed;

	@Temporal(TemporalType.DATE)
	private Date lastupdatedate;

	private BigDecimal lastupdateuser;

	@Column(name="MC_STATUS")
	private String mcStatus;

	@Column(name="SELECT_ALLOWED")
	private String selectAllowed;

	@Column(name="UPDATE_ALLOWED")
	private String updateAllowed;

	private BigDecimal updateindex;

	//bi-directional many-to-one association to TblMenu
	@ManyToOne
	@JoinColumn(name="MENU_ID")

	private TblMenu tblMenu;

	//bi-directional many-to-one association to TblRole
	@ManyToOne
	@JoinColumn(name="ROLE_ID")

	private TblRole tblRole;

	public TblRoleRight() {
	}

	public long getRoleRightsId() {
		return this.roleRightsId;
	}

	public void setRoleRightsId(long roleRightsId) {
		this.roleRightsId = roleRightsId;
	}

	public String getAuthorizeAllowed() {
		return this.authorizeAllowed;
	}

	public void setAuthorizeAllowed(String authorizeAllowed) {
		this.authorizeAllowed = authorizeAllowed;
	}

	public Date getCheckDate() {
		return this.checkDate;
	}

	public void setCheckDate(Date checkDate) {
		this.checkDate = checkDate;
	}

	public String getCheckerComments() {
		return this.checkerComments;
	}

	public void setCheckerComments(String checkerComments) {
		this.checkerComments = checkerComments;
	}

	public BigDecimal getCheckerId() {
		return this.checkerId;
	}

	public void setCheckerId(BigDecimal checkerId) {
		this.checkerId = checkerId;
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

	public String getHideYn() {
		return this.hideYn;
	}

	public void setHideYn(String hideYn) {
		this.hideYn = hideYn;
	}

	public String getInsertAllowed() {
		return this.insertAllowed;
	}

	public void setInsertAllowed(String insertAllowed) {
		this.insertAllowed = insertAllowed;
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

	public String getMcStatus() {
		return this.mcStatus;
	}

	public void setMcStatus(String mcStatus) {
		this.mcStatus = mcStatus;
	}

	public String getSelectAllowed() {
		return this.selectAllowed;
	}

	public void setSelectAllowed(String selectAllowed) {
		this.selectAllowed = selectAllowed;
	}

	public String getUpdateAllowed() {
		return this.updateAllowed;
	}

	public void setUpdateAllowed(String updateAllowed) {
		this.updateAllowed = updateAllowed;
	}

	public BigDecimal getUpdateindex() {
		return this.updateindex;
	}

	public void setUpdateindex(BigDecimal updateindex) {
		this.updateindex = updateindex;
	}

	public TblMenu getTblMenu() {
		return this.tblMenu;
	}

	public void setTblMenu(TblMenu tblMenu) {
		this.tblMenu = tblMenu;
	}

	public TblRole getTblRole() {
		return this.tblRole;
	}

	public void setTblRole(TblRole tblRole) {
		this.tblRole = tblRole;
	}

}
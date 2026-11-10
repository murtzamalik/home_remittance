package org.ais.hr.transactions.model;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the TBL_MENU database table.
 * 
 */
@Entity
@Table(name="TBL_MENU")
@NamedQuery(name="TblMenu.findAll", query="SELECT t FROM TblMenu t")
public class TblMenu implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TBL_MENU_MENUID_GENERATOR", sequenceName="TBL_MENU_SEQ",allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TBL_MENU_MENUID_GENERATOR")
	@Column(name="MENU_ID")
	private long menuId;

	@Temporal(TemporalType.DATE)
	private Date createdate;

	private BigDecimal createuser;

	private String hotkey;

	private String icon;

	@Temporal(TemporalType.DATE)
	private Date lastupdatedate;

	private BigDecimal lastupdateuser;

	@Column(name="\"LEVEL\"")
	private BigDecimal level;

	@Column(name="LEVEL_CODE")
	private String levelCode;

	@Column(name="MENU_DESCR")
	private String menuDescr;

	private BigDecimal noofchild;

	private String optcode;

	@Column(name="PRG_TYPE_CODE")
	private String prgTypeCode;

	private String purpose;

	@Column(name="\"SEQUENCE\"")
	private String sequence;

	@Column(name="SESSION_FIELD")
	private String sessionField;

	private String status;

	@Column(name="SUB_OPTCODE")
	private String subOptcode;

	private BigDecimal updateindex;

	@Column(name="VALIDATE_CLASS")
	private String validateClass;

	//bi-directional many-to-one association to TblApplication
	@ManyToOne
	@JoinColumn(name="APPLICATION_ID")
	private TblApplication tblApplication;

	public TblMenu() {
	}

	public long getMenuId() {
		return this.menuId;
	}

	public void setMenuId(long menuId) {
		this.menuId = menuId;
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

	public String getHotkey() {
		return this.hotkey;
	}

	public void setHotkey(String hotkey) {
		this.hotkey = hotkey;
	}

	public String getIcon() {
		return this.icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
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

	public BigDecimal getLevel() {
		return this.level;
	}

	public void setLevel(BigDecimal level) {
		this.level = level;
	}

	public String getLevelCode() {
		return this.levelCode;
	}

	public void setLevelCode(String levelCode) {
		this.levelCode = levelCode;
	}

	public String getMenuDescr() {
		return this.menuDescr;
	}

	public void setMenuDescr(String menuDescr) {
		this.menuDescr = menuDescr;
	}

	public BigDecimal getNoofchild() {
		return this.noofchild;
	}

	public void setNoofchild(BigDecimal noofchild) {
		this.noofchild = noofchild;
	}

	public String getOptcode() {
		return this.optcode;
	}

	public void setOptcode(String optcode) {
		this.optcode = optcode;
	}

	public String getPrgTypeCode() {
		return this.prgTypeCode;
	}

	public void setPrgTypeCode(String prgTypeCode) {
		this.prgTypeCode = prgTypeCode;
	}

	public String getPurpose() {
		return this.purpose;
	}

	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}

	public String getSequence() {
		return this.sequence;
	}

	public void setSequence(String sequence) {
		this.sequence = sequence;
	}

	public String getSessionField() {
		return this.sessionField;
	}

	public void setSessionField(String sessionField) {
		this.sessionField = sessionField;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getSubOptcode() {
		return this.subOptcode;
	}

	public void setSubOptcode(String subOptcode) {
		this.subOptcode = subOptcode;
	}

	public BigDecimal getUpdateindex() {
		return this.updateindex;
	}

	public void setUpdateindex(BigDecimal updateindex) {
		this.updateindex = updateindex;
	}

	public String getValidateClass() {
		return this.validateClass;
	}

	public void setValidateClass(String validateClass) {
		this.validateClass = validateClass;
	}

	public TblApplication getTblApplication() {
		return this.tblApplication;
	}

	public void setTblApplication(TblApplication tblApplication) {
		this.tblApplication = tblApplication;
	}

}
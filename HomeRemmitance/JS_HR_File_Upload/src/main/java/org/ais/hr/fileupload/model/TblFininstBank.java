package org.ais.hr.fileupload.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the TBL_FININST_BANK database table.
 * 
 */
@Entity
@Table(name="TBL_FININST_BANK")
@NamedQuery(name="TblFininstBank.findAll", query="SELECT t FROM TblFininstBank t")
public class TblFininstBank implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TBL_FININST_BANK_FININSTBANKID_GENERATOR", sequenceName="TBL_FININST_BANK_SEQ",allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TBL_FININST_BANK_FININSTBANKID_GENERATOR")
	@Column(name="FININST_BANK_ID")
	private long fininstBankId;

	@Column(name="ACCOUNT_DESCR")
	private String accountDescr;

	@Column(name="ACCOUNT_NO_PKR")
	private String accountNoPkr;

	@Column(name="ACTIVE_TAG")
	private String activeTag;

	@Column(name="AML_DATAMODE")
	private String amlDatamode;

	@Column(name="API_ON")
	private String apiOn;

	@Column(name="ARCHIVE_PATH")
	private String archivePath;

	@Column(name="ARCHIVE_PATHO")
	private String archivePatho;

	@Column(name="AUTO_CORRECT")
	private String autoCorrect;

	@Column(name="BANK_IMD")
	private String bankImd;

	@Temporal(TemporalType.DATE)
	@Column(name="BUSREL_DATE")
	private Date busrelDate;

	@Column(name="CC_EMAIL")
	private String ccEmail;

	@Column(name="CONTACT_PERSON")
	private String contactPerson;

	@Temporal(TemporalType.DATE)
	private Date createdate;

	private BigDecimal createuser;

	@Column(name="DESC_CONCAT")
	private String descConcat;

	@Column(name="DISPUTE_DOWNLOAD_PATH")
	private String disputeDownloadPath;

	@Column(name="DISPUTE_UPLOAD_PATH")
	private String disputeUploadPath;

	private String email;

	@Column(name="ERROR_FILE_PATH")
	private String errorFilePath;

	@Column(name="EXCH_BRANCH")
	private String exchBranch;

	@Column(name="EXCH_COMM")
	private String exchComm;

	private String fax;

	@Column(name="FILE_FOOTER")
	private String fileFooter;

	@Column(name="FILE_HEADER")
	private String fileHeader;

	@Column(name="FININST_BANKCODE")
	private String fininstBankcode;

	@Column(name="FININST_CODE")
	private String fininstCode;

	@Column(name="FININST_CODEREF")
	private String fininstCoderef;

	@Column(name="FININST_DCRPT")
	private String fininstDcrpt;

	@Column(name="FININST_DEFAULT")
	private String fininstDefault;

	@Column(name="FININST_DESCR")
	private String fininstDescr;

	@Column(name="FININST_NATURE")
	private String fininstNature;

	@Column(name="FININST_RPT")
	private String fininstRpt;

	@Column(name="FININST_RTGS")
	private String fininstRtgs;

	@Column(name="FININST_SBPCODE")
	private String fininstSbpcode;

	@Column(name="FININST_SHORT")
	private String fininstShort;

	@Column(name="FININST_TYPE")
	private String fininstType;

	@Column(name="FOOTER_LINES")
	private BigDecimal footerLines;

	@Column(name="FTEX_FIRST")
	private String ftexFirst;

	@Column(name="FTP_ADDRESS")
	private String ftpAddress;

	@Column(name="HEADER_LINES")
	private BigDecimal headerLines;

	private String ibft;

	@Column(name="IBFT_ACCOUNT_FORMAT")
	private String ibftAccountFormat;

	@Column(name="IBFT_ACCOUNT_LENGTH")
	private String ibftAccountLength;

	@Column(name="IBFT_AUTOPROCESS")
	private String ibftAutoprocess;

	@Column(name="IP_ADDRESS")
	private String ipAddress;

	@Column(name="IS_ACTIVE")
	private String isActive;

	@Temporal(TemporalType.DATE)
	private Date lastupdatedate;

	private BigDecimal lastupdateuser;

	@Column(name="MAP_CODE")
	private String mapCode;

	@Column(name="MBM_VALIDATE_IBAN")
	private String mbmValidateIban;

	private String mobile;

	@Column(name="NAME_SPACE")
	private String nameSpace;

	@Column(name="OUTPUT_PATH")
	private String outputPath;

	@Column(name="OVERSEAS_FILE_PATH")
	private String overseasFilePath;

	@Column(name="OVERSEAS_TEXTFILE_PATH")
	private String overseasTextfilePath;

	private String password;

	@Column(name="PASSWORD_PROTECTED")
	private String passwordProtected;

	private String phone;

	private String pobox;

	private String postal;

	@Column(name="PRI_DOWNLOAD_PATH")
	private String priDownloadPath;

	@Column(name="PRI_UPLOAD_PATH")
	private String priUploadPath;

	@Column(name="REG_ADDRESS1")
	private String regAddress1;

	@Column(name="REG_ADDRESS2")
	private String regAddress2;

	@Column(name="REG_ADDRESS3")
	private String regAddress3;

	@Column(name="REMIT_KEYMODE")
	private String remitKeymode;

	@Column(name="RIPPL_CONNECT")
	private String ripplConnect;

	@Column(name="SBP_COMM")
	private String sbpComm;

	@Column(name="SHARING_AMOUNT")
	private BigDecimal sharingAmount;

	@Column(name="SHARING_PER")
	private BigDecimal sharingPer;

	@Column(name="SPCADV_NOTE")
	private String spcadvNote;

	@Column(name="SWIFT_ID")
	private String swiftId;

	@Column(name="TIMEDIFF_GMT")
	private BigDecimal timediffGmt;

	private BigDecimal updateindex;

	@Column(name="VALIDATE_AML")
	private String validateAml;

	@Column(name="VALIDATE_IBAN")
	private String validateIban;

	private String website;

	@Column(name="WS_IP")
	private String wsIp;

	@Column(name="WS_PASSWORD")
	private String wsPassword;

	@Column(name="WS_URL")
	private String wsUrl;

	@Column(name="WS_USERID")
	private String wsUserid;

	@Column(name="ZIP_FILE")
	private String zipFile;

	@Column(name="ZIP_FORMAT")
	private String zipFormat;

	//bi-directional many-to-one association to LkpCity
	@ManyToOne
	@JoinColumn(name="CITY_ID")
	private LkpCity lkpCity;

	//bi-directional many-to-one association to LkpCountry
	@ManyToOne
	@JoinColumn(name="COUNTRY_ID")
	private LkpCountry lkpCountry1;

	//bi-directional many-to-one association to LkpCountry
	@ManyToOne
	@JoinColumn(name="SBP_COUNTRY_ID")
	private LkpCountry lkpCountry2;

	//bi-directional many-to-one association to TblGlAccount
	@ManyToOne
	@JoinColumn(name="GL_ACCOUNT_ID")
	private TblGlAccount tblGlAccount;

	//bi-directional many-to-one association to TblOrganization
	@ManyToOne
	@JoinColumn(name="ORGANIZATION_ID")
	private TblOrganization tblOrganization;

	//bi-directional many-to-one association to TblLocation
	@JsonIgnore
	@OneToMany(mappedBy="tblFininstBank")
	private List<TblLocation> tblLocations;

	//bi-directional many-to-one association to TblRemitter
	@JsonIgnore
	@OneToMany(mappedBy="tblFininstBank")
	private List<TblRemitter> tblRemitters;

	@ManyToOne
	@JoinColumn(name="PARSER_HEAD_ID")
	private TblParserHead tblParserHead;

	public TblParserHead getTblParserHead() {
		return tblParserHead;
	}

	public void setTblParserHead(TblParserHead tblParserHead) {
		this.tblParserHead = tblParserHead;
	}

	public TblFininstBank() {
	}

	public long getFininstBankId() {
		return this.fininstBankId;
	}

	public void setFininstBankId(long fininstBankId) {
		this.fininstBankId = fininstBankId;
	}

	public String getAccountDescr() {
		return this.accountDescr;
	}

	public void setAccountDescr(String accountDescr) {
		this.accountDescr = accountDescr;
	}

	public String getAccountNoPkr() {
		return this.accountNoPkr;
	}

	public void setAccountNoPkr(String accountNoPkr) {
		this.accountNoPkr = accountNoPkr;
	}

	public String getActiveTag() {
		return this.activeTag;
	}

	public void setActiveTag(String activeTag) {
		this.activeTag = activeTag;
	}

	public String getAmlDatamode() {
		return this.amlDatamode;
	}

	public void setAmlDatamode(String amlDatamode) {
		this.amlDatamode = amlDatamode;
	}

	public String getApiOn() {
		return this.apiOn;
	}

	public void setApiOn(String apiOn) {
		this.apiOn = apiOn;
	}

	public String getArchivePath() {
		return this.archivePath;
	}

	public void setArchivePath(String archivePath) {
		this.archivePath = archivePath;
	}

	public String getArchivePatho() {
		return this.archivePatho;
	}

	public void setArchivePatho(String archivePatho) {
		this.archivePatho = archivePatho;
	}

	public String getAutoCorrect() {
		return this.autoCorrect;
	}

	public void setAutoCorrect(String autoCorrect) {
		this.autoCorrect = autoCorrect;
	}

	public String getBankImd() {
		return this.bankImd;
	}

	public void setBankImd(String bankImd) {
		this.bankImd = bankImd;
	}

	public Date getBusrelDate() {
		return this.busrelDate;
	}

	public void setBusrelDate(Date busrelDate) {
		this.busrelDate = busrelDate;
	}

	public String getCcEmail() {
		return this.ccEmail;
	}

	public void setCcEmail(String ccEmail) {
		this.ccEmail = ccEmail;
	}

	public String getContactPerson() {
		return this.contactPerson;
	}

	public void setContactPerson(String contactPerson) {
		this.contactPerson = contactPerson;
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

	public String getDescConcat() {
		return this.descConcat;
	}

	public void setDescConcat(String descConcat) {
		this.descConcat = descConcat;
	}

	public String getDisputeDownloadPath() {
		return this.disputeDownloadPath;
	}

	public void setDisputeDownloadPath(String disputeDownloadPath) {
		this.disputeDownloadPath = disputeDownloadPath;
	}

	public String getDisputeUploadPath() {
		return this.disputeUploadPath;
	}

	public void setDisputeUploadPath(String disputeUploadPath) {
		this.disputeUploadPath = disputeUploadPath;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getErrorFilePath() {
		return this.errorFilePath;
	}

	public void setErrorFilePath(String errorFilePath) {
		this.errorFilePath = errorFilePath;
	}

	public String getExchBranch() {
		return this.exchBranch;
	}

	public void setExchBranch(String exchBranch) {
		this.exchBranch = exchBranch;
	}

	public String getExchComm() {
		return this.exchComm;
	}

	public void setExchComm(String exchComm) {
		this.exchComm = exchComm;
	}

	public String getFax() {
		return this.fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getFileFooter() {
		return this.fileFooter;
	}

	public void setFileFooter(String fileFooter) {
		this.fileFooter = fileFooter;
	}

	public String getFileHeader() {
		return this.fileHeader;
	}

	public void setFileHeader(String fileHeader) {
		this.fileHeader = fileHeader;
	}

	public String getFininstBankcode() {
		return this.fininstBankcode;
	}

	public void setFininstBankcode(String fininstBankcode) {
		this.fininstBankcode = fininstBankcode;
	}

	public String getFininstCode() {
		return this.fininstCode;
	}

	public void setFininstCode(String fininstCode) {
		this.fininstCode = fininstCode;
	}

	public String getFininstCoderef() {
		return this.fininstCoderef;
	}

	public void setFininstCoderef(String fininstCoderef) {
		this.fininstCoderef = fininstCoderef;
	}

	public String getFininstDcrpt() {
		return this.fininstDcrpt;
	}

	public void setFininstDcrpt(String fininstDcrpt) {
		this.fininstDcrpt = fininstDcrpt;
	}

	public String getFininstDefault() {
		return this.fininstDefault;
	}

	public void setFininstDefault(String fininstDefault) {
		this.fininstDefault = fininstDefault;
	}

	public String getFininstDescr() {
		return this.fininstDescr;
	}

	public void setFininstDescr(String fininstDescr) {
		this.fininstDescr = fininstDescr;
	}

	public String getFininstNature() {
		return this.fininstNature;
	}

	public void setFininstNature(String fininstNature) {
		this.fininstNature = fininstNature;
	}

	public String getFininstRpt() {
		return this.fininstRpt;
	}

	public void setFininstRpt(String fininstRpt) {
		this.fininstRpt = fininstRpt;
	}

	public String getFininstRtgs() {
		return this.fininstRtgs;
	}

	public void setFininstRtgs(String fininstRtgs) {
		this.fininstRtgs = fininstRtgs;
	}

	public String getFininstSbpcode() {
		return this.fininstSbpcode;
	}

	public void setFininstSbpcode(String fininstSbpcode) {
		this.fininstSbpcode = fininstSbpcode;
	}

	public String getFininstShort() {
		return this.fininstShort;
	}

	public void setFininstShort(String fininstShort) {
		this.fininstShort = fininstShort;
	}

	public String getFininstType() {
		return this.fininstType;
	}

	public void setFininstType(String fininstType) {
		this.fininstType = fininstType;
	}

	public BigDecimal getFooterLines() {
		return this.footerLines;
	}

	public void setFooterLines(BigDecimal footerLines) {
		this.footerLines = footerLines;
	}

	public String getFtexFirst() {
		return this.ftexFirst;
	}

	public void setFtexFirst(String ftexFirst) {
		this.ftexFirst = ftexFirst;
	}

	public String getFtpAddress() {
		return this.ftpAddress;
	}

	public void setFtpAddress(String ftpAddress) {
		this.ftpAddress = ftpAddress;
	}

	public BigDecimal getHeaderLines() {
		return this.headerLines;
	}

	public void setHeaderLines(BigDecimal headerLines) {
		this.headerLines = headerLines;
	}

	public String getIbft() {
		return this.ibft;
	}

	public void setIbft(String ibft) {
		this.ibft = ibft;
	}

	public String getIbftAccountFormat() {
		return this.ibftAccountFormat;
	}

	public void setIbftAccountFormat(String ibftAccountFormat) {
		this.ibftAccountFormat = ibftAccountFormat;
	}

	public String getIbftAccountLength() {
		return this.ibftAccountLength;
	}

	public void setIbftAccountLength(String ibftAccountLength) {
		this.ibftAccountLength = ibftAccountLength;
	}

	public String getIbftAutoprocess() {
		return this.ibftAutoprocess;
	}

	public void setIbftAutoprocess(String ibftAutoprocess) {
		this.ibftAutoprocess = ibftAutoprocess;
	}

	public String getIpAddress() {
		return this.ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
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

	public String getMapCode() {
		return this.mapCode;
	}

	public void setMapCode(String mapCode) {
		this.mapCode = mapCode;
	}

	public String getMbmValidateIban() {
		return this.mbmValidateIban;
	}

	public void setMbmValidateIban(String mbmValidateIban) {
		this.mbmValidateIban = mbmValidateIban;
	}

	public String getMobile() {
		return this.mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getNameSpace() {
		return this.nameSpace;
	}

	public void setNameSpace(String nameSpace) {
		this.nameSpace = nameSpace;
	}

	public String getOutputPath() {
		return this.outputPath;
	}

	public void setOutputPath(String outputPath) {
		this.outputPath = outputPath;
	}

	public String getOverseasFilePath() {
		return this.overseasFilePath;
	}

	public void setOverseasFilePath(String overseasFilePath) {
		this.overseasFilePath = overseasFilePath;
	}

	public String getOverseasTextfilePath() {
		return this.overseasTextfilePath;
	}

	public void setOverseasTextfilePath(String overseasTextfilePath) {
		this.overseasTextfilePath = overseasTextfilePath;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPasswordProtected() {
		return this.passwordProtected;
	}

	public void setPasswordProtected(String passwordProtected) {
		this.passwordProtected = passwordProtected;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPobox() {
		return this.pobox;
	}

	public void setPobox(String pobox) {
		this.pobox = pobox;
	}

	public String getPostal() {
		return this.postal;
	}

	public void setPostal(String postal) {
		this.postal = postal;
	}

	public String getPriDownloadPath() {
		return this.priDownloadPath;
	}

	public void setPriDownloadPath(String priDownloadPath) {
		this.priDownloadPath = priDownloadPath;
	}

	public String getPriUploadPath() {
		return this.priUploadPath;
	}

	public void setPriUploadPath(String priUploadPath) {
		this.priUploadPath = priUploadPath;
	}

	public String getRegAddress1() {
		return this.regAddress1;
	}

	public void setRegAddress1(String regAddress1) {
		this.regAddress1 = regAddress1;
	}

	public String getRegAddress2() {
		return this.regAddress2;
	}

	public void setRegAddress2(String regAddress2) {
		this.regAddress2 = regAddress2;
	}

	public String getRegAddress3() {
		return this.regAddress3;
	}

	public void setRegAddress3(String regAddress3) {
		this.regAddress3 = regAddress3;
	}

	public String getRemitKeymode() {
		return this.remitKeymode;
	}

	public void setRemitKeymode(String remitKeymode) {
		this.remitKeymode = remitKeymode;
	}

	public String getRipplConnect() {
		return this.ripplConnect;
	}

	public void setRipplConnect(String ripplConnect) {
		this.ripplConnect = ripplConnect;
	}

	public String getSbpComm() {
		return this.sbpComm;
	}

	public void setSbpComm(String sbpComm) {
		this.sbpComm = sbpComm;
	}

	public BigDecimal getSharingAmount() {
		return this.sharingAmount;
	}

	public void setSharingAmount(BigDecimal sharingAmount) {
		this.sharingAmount = sharingAmount;
	}

	public BigDecimal getSharingPer() {
		return this.sharingPer;
	}

	public void setSharingPer(BigDecimal sharingPer) {
		this.sharingPer = sharingPer;
	}

	public String getSpcadvNote() {
		return this.spcadvNote;
	}

	public void setSpcadvNote(String spcadvNote) {
		this.spcadvNote = spcadvNote;
	}

	public String getSwiftId() {
		return this.swiftId;
	}

	public void setSwiftId(String swiftId) {
		this.swiftId = swiftId;
	}

	public BigDecimal getTimediffGmt() {
		return this.timediffGmt;
	}

	public void setTimediffGmt(BigDecimal timediffGmt) {
		this.timediffGmt = timediffGmt;
	}

	public BigDecimal getUpdateindex() {
		return this.updateindex;
	}

	public void setUpdateindex(BigDecimal updateindex) {
		this.updateindex = updateindex;
	}

	public String getValidateAml() {
		return this.validateAml;
	}

	public void setValidateAml(String validateAml) {
		this.validateAml = validateAml;
	}

	public String getValidateIban() {
		return this.validateIban;
	}

	public void setValidateIban(String validateIban) {
		this.validateIban = validateIban;
	}

	public String getWebsite() {
		return this.website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public String getWsIp() {
		return this.wsIp;
	}

	public void setWsIp(String wsIp) {
		this.wsIp = wsIp;
	}

	public String getWsPassword() {
		return this.wsPassword;
	}

	public void setWsPassword(String wsPassword) {
		this.wsPassword = wsPassword;
	}

	public String getWsUrl() {
		return this.wsUrl;
	}

	public void setWsUrl(String wsUrl) {
		this.wsUrl = wsUrl;
	}

	public String getWsUserid() {
		return this.wsUserid;
	}

	public void setWsUserid(String wsUserid) {
		this.wsUserid = wsUserid;
	}

	public String getZipFile() {
		return this.zipFile;
	}

	public void setZipFile(String zipFile) {
		this.zipFile = zipFile;
	}

	public String getZipFormat() {
		return this.zipFormat;
	}

	public void setZipFormat(String zipFormat) {
		this.zipFormat = zipFormat;
	}

	public LkpCity getLkpCity() {
		return this.lkpCity;
	}

	public void setLkpCity(LkpCity lkpCity) {
		this.lkpCity = lkpCity;
	}

	public LkpCountry getLkpCountry1() {
		return this.lkpCountry1;
	}

	public void setLkpCountry1(LkpCountry lkpCountry1) {
		this.lkpCountry1 = lkpCountry1;
	}

	public LkpCountry getLkpCountry2() {
		return this.lkpCountry2;
	}

	public void setLkpCountry2(LkpCountry lkpCountry2) {
		this.lkpCountry2 = lkpCountry2;
	}

	public TblGlAccount getTblGlAccount() {
		return this.tblGlAccount;
	}

	public void setTblGlAccount(TblGlAccount tblGlAccount) {
		this.tblGlAccount = tblGlAccount;
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
		tblLocation.setTblFininstBank(this);

		return tblLocation;
	}

	public TblLocation removeTblLocation(TblLocation tblLocation) {
		getTblLocations().remove(tblLocation);
		tblLocation.setTblFininstBank(null);

		return tblLocation;
	}

	public List<TblRemitter> getTblRemitters() {
		return this.tblRemitters;
	}

	public void setTblRemitters(List<TblRemitter> tblRemitters) {
		this.tblRemitters = tblRemitters;
	}

	public TblRemitter addTblRemitter(TblRemitter tblRemitter) {
		getTblRemitters().add(tblRemitter);
		tblRemitter.setTblFininstBank(this);

		return tblRemitter;
	}

	public TblRemitter removeTblRemitter(TblRemitter tblRemitter) {
		getTblRemitters().remove(tblRemitter);
		tblRemitter.setTblFininstBank(null);

		return tblRemitter;
	}

}
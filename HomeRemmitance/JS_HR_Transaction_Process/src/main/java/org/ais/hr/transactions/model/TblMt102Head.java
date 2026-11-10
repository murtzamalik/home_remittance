package org.ais.hr.transactions.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the TBL_MT102_HEAD database table.
 * 
 */
@Entity
@Table(name="TBL_MT102_HEAD")
@NamedQuery(name="TblMt102Head.findAll", query="SELECT t FROM TblMt102Head t")
public class TblMt102Head implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TBL_MT102_HEAD_MT102HEADID_GENERATOR", sequenceName="TBL_MT102_HEAD_SEQ",allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TBL_MT102_HEAD_MT102HEADID_GENERATOR")
	@Column(name="MT102_HEAD_ID")
	private long mt102HeadId;

	@Column(name="BANK_NAME")
	private String bankName;

	@Temporal(TemporalType.DATE)
	private Date createdate;

	private BigDecimal createuser;

	@Column(name="FILE_REFERENCE_NO")
	private String fileReferenceNo;

	@Temporal(TemporalType.DATE)
	private Date lastupdatedate;

	private BigDecimal lastupdateuser;

	@Column(name="NO_OF_TRANSACTIONS")
	private BigDecimal noOfTransactions;

	private String status;

	private String statusdescr;

	@Column(name="TOTAL_AMOUNT")
	private BigDecimal totalAmount;

	private BigDecimal updateindex;

	//bi-directional many-to-one association to TblMt102Detail
	@OneToMany(mappedBy="tblMt102Head")
	private List<TblMt102Detail> tblMt102Details;

	public TblMt102Head() {
	}

	public long getMt102HeadId() {
		return this.mt102HeadId;
	}

	public void setMt102HeadId(long mt102HeadId) {
		this.mt102HeadId = mt102HeadId;
	}

	public String getBankName() {
		return this.bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
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

	public String getFileReferenceNo() {
		return this.fileReferenceNo;
	}

	public void setFileReferenceNo(String fileReferenceNo) {
		this.fileReferenceNo = fileReferenceNo;
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

	public BigDecimal getNoOfTransactions() {
		return this.noOfTransactions;
	}

	public void setNoOfTransactions(BigDecimal noOfTransactions) {
		this.noOfTransactions = noOfTransactions;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getStatusdescr() {
		return this.statusdescr;
	}

	public void setStatusdescr(String statusdescr) {
		this.statusdescr = statusdescr;
	}

	public BigDecimal getTotalAmount() {
		return this.totalAmount;
	}

	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}

	public BigDecimal getUpdateindex() {
		return this.updateindex;
	}

	public void setUpdateindex(BigDecimal updateindex) {
		this.updateindex = updateindex;
	}

	public List<TblMt102Detail> getTblMt102Details() {
		return this.tblMt102Details;
	}

	public void setTblMt102Details(List<TblMt102Detail> tblMt102Details) {
		this.tblMt102Details = tblMt102Details;
	}

	public TblMt102Detail addTblMt102Detail(TblMt102Detail tblMt102Detail) {
		getTblMt102Details().add(tblMt102Detail);
		tblMt102Detail.setTblMt102Head(this);

		return tblMt102Detail;
	}

	public TblMt102Detail removeTblMt102Detail(TblMt102Detail tblMt102Detail) {
		getTblMt102Details().remove(tblMt102Detail);
		tblMt102Detail.setTblMt102Head(null);

		return tblMt102Detail;
	}

}
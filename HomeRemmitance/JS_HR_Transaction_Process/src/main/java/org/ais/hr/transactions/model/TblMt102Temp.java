package org.ais.hr.transactions.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the TBL_MT102_TEMP database table.
 * 
 */
@Entity
@Table(name="TBL_MT102_TEMP")
@NamedQuery(name="TblMt102Temp.findAll", query="SELECT t FROM TblMt102Temp t")
public class TblMt102Temp implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TBL_MT102_TEMP_MT102TEMPID_GENERATOR", sequenceName="TBL_MT102_TEMP_SEQ",allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TBL_MT102_TEMP_MT102TEMPID_GENERATOR")
	@Column(name="MT102_TEMP_ID")
	private long mt102TempId;

	@Column(name="BATCH_NO")
	private BigDecimal batchNo;

	@Temporal(TemporalType.DATE)
	private Date createdate;

	private BigDecimal createuser;

	@Temporal(TemporalType.DATE)
	private Date lastupdatedate;

	private BigDecimal lastupdateuser;

	private String status;

	private String statusdescr;

	private BigDecimal updateindex;

	//bi-directional many-to-one association to TblTransDetail
	@ManyToOne
	@JoinColumn(name="TRANS_DETAIL_ID")
	private TblTransDetail tblTransDetail;

	public TblMt102Temp() {
	}

	public long getMt102TempId() {
		return this.mt102TempId;
	}

	public void setMt102TempId(long mt102TempId) {
		this.mt102TempId = mt102TempId;
	}

	public BigDecimal getBatchNo() {
		return this.batchNo;
	}

	public void setBatchNo(BigDecimal batchNo) {
		this.batchNo = batchNo;
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

	public BigDecimal getUpdateindex() {
		return this.updateindex;
	}

	public void setUpdateindex(BigDecimal updateindex) {
		this.updateindex = updateindex;
	}

	public TblTransDetail getTblTransDetail() {
		return this.tblTransDetail;
	}

	public void setTblTransDetail(TblTransDetail tblTransDetail) {
		this.tblTransDetail = tblTransDetail;
	}

}
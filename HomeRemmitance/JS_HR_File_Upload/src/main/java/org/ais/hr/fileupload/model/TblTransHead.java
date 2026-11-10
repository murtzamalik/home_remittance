package org.ais.hr.fileupload.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the TBL_TRANS_HEAD database table.
 * 
 */
@Entity
@Table(name="TBL_TRANS_HEAD")
@NamedQuery(name="TblTransHead.findAll", query="SELECT t FROM TblTransHead t")
public class TblTransHead implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TBL_TRANS_HEAD_TRANSHEADID_GENERATOR", sequenceName="TBL_TRANS_HEAD_SEQ",allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TBL_TRANS_HEAD_TRANSHEADID_GENERATOR")
	@Column(name="TRANS_HEAD_ID")
	private long transHeadId;

	@Column(name="API_TYPE")
	private String apiType;

	@Column(name="API_URL")
	private String apiUrl;

	@JsonIgnore
	@Temporal(TemporalType.DATE)
	private Date createdate;

	private BigDecimal createuser;

	@Column(name="FILE_NAME")
	private String fileName;
	@JsonIgnore
	@Temporal(TemporalType.DATE)
	private Date lastupdatedate;
	@JsonIgnore
	private BigDecimal lastupdateuser;

	@Column(name="RECORD_COUNT")
	private BigDecimal recordCount;

	private String status;
	@JsonIgnore
	private BigDecimal updateindex;


	//bi-directional many-to-one association to TblTransDetail
	@JsonIgnore
	@OneToMany(mappedBy="tblTransHead")
	private List<TblTransDetail> tblTransDetails;

	//bi-directional many-to-one association to TblRemitter
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="REMITTER_ID")
	private TblRemitter tblRemitter;


	@ManyToOne
	@JoinColumn(name="FININST_BANK_ID")
	private TblFininstBank tblFininstBank;

	@Transient
	private   List<String> errorsColumns = new ArrayList<>();


	public TblFininstBank getTblFininstBank() {
		return tblFininstBank;
	}

	public void setTblFininstBank(TblFininstBank tblFininstBank) {
		this.tblFininstBank = tblFininstBank;
	}

	public TblTransHead() {
	}

	public long getTransHeadId() {
		return this.transHeadId;
	}

	public void setTransHeadId(long transHeadId) {
		this.transHeadId = transHeadId;
	}

	public String getApiType() {
		return this.apiType;
	}

	public void setApiType(String apiType) {
		this.apiType = apiType;
	}

	public String getApiUrl() {
		return this.apiUrl;
	}

	public void setApiUrl(String apiUrl) {
		this.apiUrl = apiUrl;
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

	public String getFileName() {
		return this.fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
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

	public BigDecimal getRecordCount() {
		return this.recordCount;
	}

	public void setRecordCount(BigDecimal recordCount) {
		this.recordCount = recordCount;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public BigDecimal getUpdateindex() {
		return this.updateindex;
	}

	public void setUpdateindex(BigDecimal updateindex) {
		this.updateindex = updateindex;
	}

	public List<TblTransDetail> getTblTransDetails() {
		return this.tblTransDetails;
	}

	public void setTblTransDetails(List<TblTransDetail> tblTransDetails) {
		this.tblTransDetails = tblTransDetails;
	}

	public TblTransDetail addTblTransDetail(TblTransDetail tblTransDetail) {
		getTblTransDetails().add(tblTransDetail);
		tblTransDetail.setTblTransHead(this);

		return tblTransDetail;
	}

	public TblTransDetail removeTblTransDetail(TblTransDetail tblTransDetail) {
		getTblTransDetails().remove(tblTransDetail);
		tblTransDetail.setTblTransHead(null);

		return tblTransDetail;
	}

	public TblRemitter getTblRemitter() {
		return this.tblRemitter;
	}

	public void setTblRemitter(TblRemitter tblRemitter) {
		this.tblRemitter = tblRemitter;
	}

	public List<String> getErrorsColumns() {
		return errorsColumns;
	}

	public void setErrorsColumns(List<String> errorsColumns) {
		this.errorsColumns = errorsColumns;
	}
}
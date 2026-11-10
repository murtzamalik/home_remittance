package org.ais.hr.transactions.model;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the TBL_AMLFILE_LOG database table.
 * 
 */
@Entity
@Table(name="TBL_AMLFILE_LOG")
@NamedQuery(name="TblAmlfileLog.findAll", query="SELECT t FROM TblAmlfileLog t")
public class TblAmlfileLog implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TBL_AMLFILE_LOG_AMLFILELOGID_GENERATOR", sequenceName="TBL_AMLFILE_LOG_SEQ",allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TBL_AMLFILE_LOG_AMLFILELOGID_GENERATOR")
	@Column(name="AMLFILE_LOG_ID")
	private long amlfileLogId;

	@Temporal(TemporalType.DATE)
	private Date createdate;

	private BigDecimal createuser;

	@Column(name="FILE_FORMAT")
	private String fileFormat;

	@Column(name="FILE_NAME")
	private String fileName;

	@Column(name="PROCESS_NAME")
	private String processName;

	@Column(name="PROCESS_TYPE")
	private String processType;

	@Column(name="RECORD_COUNT")
	private BigDecimal recordCount;

	private String url;

	public TblAmlfileLog() {
	}

	public long getAmlfileLogId() {
		return this.amlfileLogId;
	}

	public void setAmlfileLogId(long amlfileLogId) {
		this.amlfileLogId = amlfileLogId;
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

	public String getFileFormat() {
		return this.fileFormat;
	}

	public void setFileFormat(String fileFormat) {
		this.fileFormat = fileFormat;
	}

	public String getFileName() {
		return this.fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getProcessName() {
		return this.processName;
	}

	public void setProcessName(String processName) {
		this.processName = processName;
	}

	public String getProcessType() {
		return this.processType;
	}

	public void setProcessType(String processType) {
		this.processType = processType;
	}

	public BigDecimal getRecordCount() {
		return this.recordCount;
	}

	public void setRecordCount(BigDecimal recordCount) {
		this.recordCount = recordCount;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}
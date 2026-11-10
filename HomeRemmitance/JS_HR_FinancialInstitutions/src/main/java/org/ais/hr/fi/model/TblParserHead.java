package org.ais.hr.fi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the TBL_PARSER_HEAD database table.
 * 
 */
@Entity
@Table(name="TBL_PARSER_HEAD")
@NamedQuery(name="TblParserHead.findAll", query="SELECT t FROM TblParserHead t")
public class TblParserHead implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TBL_PARSER_HEAD_PARSERHEADID_GENERATOR", sequenceName="TBL_PARSER_HEAD_SEQ",allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TBL_PARSER_HEAD_PARSERHEADID_GENERATOR")
	@Column(name="PARSER_HEAD_ID")
	private long parserHeadId;

	@Temporal(TemporalType.DATE)
	@Column(name="CHECK_DATE")
	private Date checkDate;

	@Column(name="CHECKER_COMMENTS")
	private String checkerComments;

	@Column(name="CHECKER_ID")
	private BigDecimal checkerId;

	private String code;

	@Temporal(TemporalType.DATE)
	private Date createdate;

	private BigDecimal createuser;

	private String description;

	@Column(name="IS_ACTIVE")
	private String isActive;

	@Temporal(TemporalType.DATE)
	private Date lastupdatedate;

	private BigDecimal lastupdateuser;

	@Column(name="MC_STATUS")
	private String mcStatus;

	private BigDecimal updateindex;



	//bi-directional many-to-one association to TblParserDetail
	@OneToMany(mappedBy="tblParserHead")
	private List<TblParserDetail> tblParserDetails;

	public TblParserHead() {
	}

	public long getParserHeadId() {
		return this.parserHeadId;
	}

	public void setParserHeadId(long parserHeadId) {
		this.parserHeadId = parserHeadId;
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

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
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

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	public String getMcStatus() {
		return this.mcStatus;
	}

	public void setMcStatus(String mcStatus) {
		this.mcStatus = mcStatus;
	}

	public BigDecimal getUpdateindex() {
		return this.updateindex;
	}

	public void setUpdateindex(BigDecimal updateindex) {
		this.updateindex = updateindex;
	}
	

	public List<TblParserDetail> getTblParserDetails() {
		return this.tblParserDetails;
	}

	public void setTblParserDetails(List<TblParserDetail> tblParserDetails) {
		this.tblParserDetails = tblParserDetails;
	}

	public TblParserDetail addTblParserDetail(TblParserDetail tblParserDetail) {
		getTblParserDetails().add(tblParserDetail);
		tblParserDetail.setTblParserHead(this);

		return tblParserDetail;
	}

	public TblParserDetail removeTblParserDetail(TblParserDetail tblParserDetail) {
		getTblParserDetails().remove(tblParserDetail);
		tblParserDetail.setTblParserHead(null);

		return tblParserDetail;
	}

}
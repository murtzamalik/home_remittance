package org.ais.hr.fi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the TBL_PARSER_DETAIL database table.
 * 
 */
@Entity
@Table(name="TBL_PARSER_DETAIL")
@NamedQuery(name="TblParserDetail.findAll", query="SELECT t FROM TblParserDetail t")
public class TblParserDetail implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TBL_PARSER_DETAIL_PARSERDETAILID_GENERATOR", sequenceName="TBL_PARSER_DETAIL_SEQ",allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TBL_PARSER_DETAIL_PARSERDETAILID_GENERATOR")
	@Column(name="PARSER_DETAIL_ID")
	private long parserDetailId;

	@Temporal(TemporalType.DATE)
	private Date createdate;

	private BigDecimal createuser;

	@Column(name="FIXED_LENGTH")
	private String fixedLength;

	@Column(name="IS_MANDATORY")
	private String isMandatory;

	@Temporal(TemporalType.DATE)
	private Date lastupdatedate;

	private BigDecimal lastupdateuser;

	@Column(name="MAX_LENGTH")
	private BigDecimal maxLength;

	@Column(name="MIN_LENGTH")
	private BigDecimal minLength;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="PARSER_HEAD_ID")
	private TblParserHead tblParserHead;

	@Column(name="REF_FIELD_NAME")
	private String refFieldName;

	@Column(name="SEQUENCE")
	private BigDecimal sequence;

	@ManyToOne
	@JoinColumn(name="TRANS_FIELDS_ID")
	private TblTransField tblTransField;

	private BigDecimal updateindex;

	public TblParserDetail() {
	}

	public long getParserDetailId() {
		return this.parserDetailId;
	}

	public void setParserDetailId(long parserDetailId) {
		this.parserDetailId = parserDetailId;
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

	public String getFixedLength() {
		return this.fixedLength;
	}

	public void setFixedLength(String fixedLength) {
		this.fixedLength = fixedLength;
	}

	public String getIsMandatory() {
		return this.isMandatory;
	}

	public void setIsMandatory(String isMandatory) {
		this.isMandatory = isMandatory;
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

	public BigDecimal getMaxLength() {
		return this.maxLength;
	}

	public void setMaxLength(BigDecimal maxLength) {
		this.maxLength = maxLength;
	}

	public BigDecimal getMinLength() {
		return this.minLength;
	}

	public void setMinLength(BigDecimal minLength) {
		this.minLength = minLength;
	}

	public String getRefFieldName() {
		return this.refFieldName;
	}

	public void setRefFieldName(String refFieldName) {
		this.refFieldName = refFieldName;
	}

	public BigDecimal getSequence() {
		return this.sequence;
	}

	public void setSequence(BigDecimal sequence) {
		this.sequence = sequence;
	}

	public BigDecimal getUpdateindex() {
		return this.updateindex;
	}

	public void setUpdateindex(BigDecimal updateindex) {
		this.updateindex = updateindex;
	}

	public TblParserHead getTblParserHead() {
		return tblParserHead;
	}

	public void setTblParserHead(TblParserHead tblParserHead) {
		this.tblParserHead = tblParserHead;
	}

	public TblTransField getTblTransField() {
		return tblTransField;
	}

	public void setTblTransField(TblTransField tblTransField) {
		this.tblTransField = tblTransField;
	}
}
package org.ais.hr.fi.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the TBL_TRANS_FIELDS database table.
 * 
 */
@Entity
@Table(name="TBL_TRANS_FIELDS")
@NamedQuery(name="TblTransField.findAll", query="SELECT t FROM TblTransField t")
public class TblTransField implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TBL_TRANS_FIELDS_TRANSDETAILID_GENERATOR", sequenceName="TBL_TRANS_FIELDS_SEQ",allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TBL_TRANS_FIELDS_TRANSDETAILID_GENERATOR")
	@Column(name="TRANS_FIELDS_ID")
	private long transFieldsId;

	@Temporal(TemporalType.DATE)
	private Date createdate;

	private BigDecimal createuser;

	@Column(name="DATA_TYPE")
	private String dataType;

	private String name;


	public TblTransField() {
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

	public String getDataType() {
		return this.dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getTransFieldsId() {
		return this.transFieldsId;
	}

	public void setTransFieldsId(long transFieldsId) {
		this.transFieldsId = transFieldsId;
	}

}
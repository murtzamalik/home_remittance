package org.ais.hr.transactions.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the TBL_NACTA_LIST database table.
 * 
 */
@Entity
@Table(name="TBL_NACTA_LIST")
@NamedQuery(name="TblNactaList.findAll", query="SELECT t FROM TblNactaList t")
public class TblNactaList implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TBL_NACTA_LIST_NACTALISTID_GENERATOR", sequenceName="TBL_NACTA_LIST_SEQ",allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TBL_NACTA_LIST_NACTALISTID_GENERATOR")
	@Column(name="NACTA_LIST_ID")
	private long nactaListId;

	private String cnic;

	@Temporal(TemporalType.DATE)
	private Date createdate;

	private BigDecimal createuser;

	private String district;

	@Column(name="FATHER_NAME")
	private String fatherName;

	private String name;

	@Column(name="PROCESS_NAME")
	private String processName;

	@Column(name="PROCESS_TYPE")
	private String processType;

	private String province;

	public TblNactaList() {
	}

	public long getNactaListId() {
		return this.nactaListId;
	}

	public void setNactaListId(long nactaListId) {
		this.nactaListId = nactaListId;
	}

	public String getCnic() {
		return this.cnic;
	}

	public void setCnic(String cnic) {
		this.cnic = cnic;
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

	public String getDistrict() {
		return this.district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getFatherName() {
		return this.fatherName;
	}

	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
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

	public String getProvince() {
		return this.province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

}
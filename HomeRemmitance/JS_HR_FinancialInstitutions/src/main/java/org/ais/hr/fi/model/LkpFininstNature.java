package org.ais.hr.fi.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the LKP_FININST_NATURE database table.
 * 
 */
@Entity
@Table(name="LKP_FININST_NATURE")
@NamedQuery(name="LkpFininstNature.findAll", query="SELECT l FROM LkpFininstNature l")
public class LkpFininstNature implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="LKP_FININST_NATURE_FININSTNATUREID_GENERATOR", sequenceName="LKP_FININST_NATURE_SEQ",allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="LKP_FININST_NATURE_FININSTNATUREID_GENERATOR")
	@Column(name="FININST_NATURE_ID")
	private long fininstNatureId;

	private String code;

	@Temporal(TemporalType.DATE)
	private Date createdate;

	private BigDecimal createuser;

	private String description;

	public LkpFininstNature() {
	}

	public long getFininstNatureId() {
		return this.fininstNatureId;
	}

	public void setFininstNatureId(long fininstNatureId) {
		this.fininstNatureId = fininstNatureId;
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

}
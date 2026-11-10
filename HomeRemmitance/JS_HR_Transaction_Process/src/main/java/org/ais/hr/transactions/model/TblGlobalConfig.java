package org.ais.hr.transactions.model;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the TBL_GLOBAL_CONFIG database table.
 * 
 */
@Entity
@Table(name="TBL_GLOBAL_CONFIG")
@NamedQuery(name="TblGlobalConfig.findAll", query="SELECT t FROM TblGlobalConfig t")
public class TblGlobalConfig implements Serializable {
	private static final long serialVersionUID = 1L;

	@Temporal(TemporalType.DATE)
	private Date createdate;

	private BigDecimal createuser;

	@Id
	@Column(name="GLOBAL_CONFIG_ID")
	private BigDecimal globalConfigId;

	@Column(name="KEY_NAME")
	private String keyName;

	@Column(name="KEY_VALUE")
	private String keyValue;

	public TblGlobalConfig() {
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

	public BigDecimal getGlobalConfigId() {
		return this.globalConfigId;
	}

	public void setGlobalConfigId(BigDecimal globalConfigId) {
		this.globalConfigId = globalConfigId;
	}

	public String getKeyName() {
		return this.keyName;
	}

	public void setKeyName(String keyName) {
		this.keyName = keyName;
	}

	public String getKeyValue() {
		return this.keyValue;
	}

	public void setKeyValue(String keyValue) {
		this.keyValue = keyValue;
	}

}
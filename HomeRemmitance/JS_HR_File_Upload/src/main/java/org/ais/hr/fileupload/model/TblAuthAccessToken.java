package org.ais.hr.fileupload.model;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the TBL_AUTH_ACCESS_TOKEN database table.
 * 
 */
@Entity
@Table(name="TBL_AUTH_ACCESS_TOKEN")
@NamedQuery(name="TblAuthAccessToken.findAll", query="SELECT t FROM TblAuthAccessToken t")
public class TblAuthAccessToken implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TBL_AUTH_ACCESS_TOKEN_AUTHACCESSTOKENID_GENERATOR", sequenceName="TBL_AUTH_ACCESS_TOKEN_SEQ",allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TBL_AUTH_ACCESS_TOKEN_AUTHACCESSTOKENID_GENERATOR")
	@Column(name="AUTH_ACCESS_TOKEN_ID")
	private long authAccessTokenId;

	@Column(name="ACCESS_TOKEN")
	private String accessToken;

	@Temporal(TemporalType.DATE)
	private Date createdate;

	@Temporal(TemporalType.DATE)
	@Column(name="EFFECTIVE_FROM")
	private Date effectiveFrom;

	@Temporal(TemporalType.DATE)
	@Column(name="EFFECTIVE_TO")
	private Date effectiveTo;

	@Column(name="USER_ID")
	private BigDecimal userId;

	public TblAuthAccessToken() {
	}

	public long getAuthAccessTokenId() {
		return this.authAccessTokenId;
	}

	public void setAuthAccessTokenId(long authAccessTokenId) {
		this.authAccessTokenId = authAccessTokenId;
	}

	public String getAccessToken() {
		return this.accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public Date getCreatedate() {
		return this.createdate;
	}

	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}

	public Date getEffectiveFrom() {
		return this.effectiveFrom;
	}

	public void setEffectiveFrom(Date effectiveFrom) {
		this.effectiveFrom = effectiveFrom;
	}

	public Date getEffectiveTo() {
		return this.effectiveTo;
	}

	public void setEffectiveTo(Date effectiveTo) {
		this.effectiveTo = effectiveTo;
	}

	public BigDecimal getUserId() {
		return this.userId;
	}

	public void setUserId(BigDecimal userId) {
		this.userId = userId;
	}

}
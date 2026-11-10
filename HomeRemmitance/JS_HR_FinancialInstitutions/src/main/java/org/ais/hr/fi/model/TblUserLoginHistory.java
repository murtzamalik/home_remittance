package org.ais.hr.fi.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the TBL_USER_LOGIN_HISTORY database table.
 * 
 */
@Entity
@Table(name="TBL_USER_LOGIN_HISTORY")
@NamedQuery(name="TblUserLoginHistory.findAll", query="SELECT t FROM TblUserLoginHistory t")
public class TblUserLoginHistory implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TBL_USER_LOGIN_HISTORY_USERLOGINHISTORYID_GENERATOR", sequenceName="TBL_USER_LOGIN_HISTORY_SEQ",allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TBL_USER_LOGIN_HISTORY_USERLOGINHISTORYID_GENERATOR")
	@Column(name="USER_LOGIN_HISTORY_ID")
	private long userLoginHistoryId;

	@Column(name="\"HOST\"")
	private String host;

	@Column(name="IP_ADDRESS")
	private String ipAddress;

	@Temporal(TemporalType.DATE)
	@Column(name="LOGIN_DATE")
	private Date loginDate;

	@Temporal(TemporalType.DATE)
	@Column(name="LOGOUT_DATE")
	private Date logoutDate;

	//bi-directional many-to-one association to TblUser
	@ManyToOne
	@JoinColumn(name="USER_ID")
	private TblUser tblUser;

	public TblUserLoginHistory() {
	}

	public long getUserLoginHistoryId() {
		return this.userLoginHistoryId;
	}

	public void setUserLoginHistoryId(long userLoginHistoryId) {
		this.userLoginHistoryId = userLoginHistoryId;
	}

	public String getHost() {
		return this.host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getIpAddress() {
		return this.ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public Date getLoginDate() {
		return this.loginDate;
	}

	public void setLoginDate(Date loginDate) {
		this.loginDate = loginDate;
	}

	public Date getLogoutDate() {
		return this.logoutDate;
	}

	public void setLogoutDate(Date logoutDate) {
		this.logoutDate = logoutDate;
	}

	public TblUser getTblUser() {
		return this.tblUser;
	}

	public void setTblUser(TblUser tblUser) {
		this.tblUser = tblUser;
	}

}
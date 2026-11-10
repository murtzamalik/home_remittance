package org.ais.hr.fileupload.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


/**
 * The persistent class for the TBL_USER_ACTIVITY_LOG database table.
 * 
 */
@Entity
@Table(name="TBL_USER_ACTIVITY_LOG")
@NamedQuery(name="TblUserActivityLog.findAll", query="SELECT t FROM TblUserActivityLog t")
public class TblUserActivityLog implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TBL_USER_ACTIVITY_LOG_USERACTIVITYLOGID_GENERATOR", sequenceName="TBL_USER_ACTIVITY_LOG_SEQ",allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TBL_USER_ACTIVITY_LOG_USERACTIVITYLOGID_GENERATOR")
	@Column(name="USER_ACTIVITY_LOG_ID")
	private long userActivityLogId;

	@Temporal(TemporalType.DATE)
	@Column(name="ACTIVITY_DATE")
	private Date activityDate;

	@Column(name="ACTIVITY_REMARKS")
	private String activityRemarks;

	//bi-directional many-to-one association to TblActivity
	@ManyToOne
	@JoinColumn(name="ACTIVITY_ID")
	private TblActivity tblActivity;

	//bi-directional many-to-one association to TblUser
	@ManyToOne
	@JoinColumn(name="USER_ID")
	private TblUser tblUser;

	//bi-directional many-to-one association to TblUserLoginHistory
	@ManyToOne
	@JoinColumn(name="USER_LOGIN_HISTORY_ID")
	private TblUserLoginHistory tblUserLoginHistory;

	public TblUserActivityLog() {
	}

	public long getUserActivityLogId() {
		return this.userActivityLogId;
	}

	public void setUserActivityLogId(long userActivityLogId) {
		this.userActivityLogId = userActivityLogId;
	}

	public Date getActivityDate() {
		return this.activityDate;
	}

	public void setActivityDate(Date activityDate) {
		this.activityDate = activityDate;
	}

	public String getActivityRemarks() {
		return this.activityRemarks;
	}

	public void setActivityRemarks(String activityRemarks) {
		this.activityRemarks = activityRemarks;
	}

	public TblActivity getTblActivity() {
		return this.tblActivity;
	}

	public void setTblActivity(TblActivity tblActivity) {
		this.tblActivity = tblActivity;
	}

	public TblUser getTblUser() {
		return this.tblUser;
	}

	public void setTblUser(TblUser tblUser) {
		this.tblUser = tblUser;
	}

	public TblUserLoginHistory getTblUserLoginHistory() {
		return this.tblUserLoginHistory;
	}

	public void setTblUserLoginHistory(TblUserLoginHistory tblUserLoginHistory) {
		this.tblUserLoginHistory = tblUserLoginHistory;
	}

}
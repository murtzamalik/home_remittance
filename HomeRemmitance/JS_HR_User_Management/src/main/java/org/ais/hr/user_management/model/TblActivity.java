package org.ais.hr.user_management.model;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the TBL_ACTIVITY database table.
 * 
 */
@Entity
@Table(name="TBL_ACTIVITY")
@NamedQuery(name="TblActivity.findAll", query="SELECT t FROM TblActivity t")
public class TblActivity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TBL_ACTIVITY_ACTIVITYID_GENERATOR", sequenceName="SEQ",allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TBL_ACTIVITY_ACTIVITYID_GENERATOR")
	@Column(name="ACTIVITY_ID")
	private long activityId;

	@Column(name="ACTIVITY_NAME")
	private String activityName;

	@Temporal(TemporalType.DATE)
	private Date createdate;

	private BigDecimal createuser;

	@Temporal(TemporalType.DATE)
	private Date lastupdatedate;

	private BigDecimal lastupdateuser;

	private BigDecimal updateindex;

	//bi-directional many-to-one association to TblUserActivityLog
	@OneToMany(mappedBy="tblActivity")
	private List<TblUserActivityLog> tblUserActivityLogs;

	public TblActivity() {
	}

	public long getActivityId() {
		return this.activityId;
	}

	public void setActivityId(long activityId) {
		this.activityId = activityId;
	}

	public String getActivityName() {
		return this.activityName;
	}

	public void setActivityName(String activityName) {
		this.activityName = activityName;
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

	public BigDecimal getUpdateindex() {
		return this.updateindex;
	}

	public void setUpdateindex(BigDecimal updateindex) {
		this.updateindex = updateindex;
	}

	public List<TblUserActivityLog> getTblUserActivityLogs() {
		return this.tblUserActivityLogs;
	}

	public void setTblUserActivityLogs(List<TblUserActivityLog> tblUserActivityLogs) {
		this.tblUserActivityLogs = tblUserActivityLogs;
	}

	public TblUserActivityLog addTblUserActivityLog(TblUserActivityLog tblUserActivityLog) {
		getTblUserActivityLogs().add(tblUserActivityLog);
		tblUserActivityLog.setTblActivity(this);

		return tblUserActivityLog;
	}

	public TblUserActivityLog removeTblUserActivityLog(TblUserActivityLog tblUserActivityLog) {
		getTblUserActivityLogs().remove(tblUserActivityLog);
		tblUserActivityLog.setTblActivity(null);

		return tblUserActivityLog;
	}

}
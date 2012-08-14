package gabriel.atrack.activity.model;

import gabriel.atrack.acl.model.Subject_;

import java.util.Collection;
import java.util.LinkedHashSet;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity(name = "Activity")
@Table(name = "T_ACTIVITY")
@NamedQueries({
		@NamedQuery(name = "Activity.findTopActivity_filter_by_Timestamp", query = "SELECT e FROM Activity e WHERE e.timeStamp >:timeStamp order by e.timeStamp"),
		@NamedQuery(name = "Activity.findTopActivity", query = "SELECT e FROM Activity e order by e.timeStamp") })
public class Activity_ {

	@Id
	@Column(name = "ACT_ID")
	private Long id;

	@Column(name = "CR_TIME")
	private Long timeStamp;

	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, optional = false, orphanRemoval = true)
	// @PrimaryKeyJoinColumn
	@JoinColumn(name = "ACT_CODE")
	private ActivityType_ activityType;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "SUBJECT_ID")
	private Subject_ initiater;

	@OneToMany(mappedBy = "activity", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Collection<ActivityData_> activityData = new LinkedHashSet<ActivityData_>();

	public void setId(Long id) {
		this.id = id;
	}

	public void setInitiater(Subject_ initiater) {
		this.initiater = initiater;
	}

	public void setActivityType(ActivityType_ activityType) {
		this.activityType = activityType;
	}

	public Collection<ActivityData_> getActivityData() {
		return activityData;
	}

	public Long getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(Long timeStamp) {
		this.timeStamp = timeStamp;
	}

}

package gabriel.atrack.activity.model;

import gabriel.atrack.dto.ActivityType;
import gabriel.atrack.dto.ActivityTypeDef;

import java.util.Collection;
import java.util.LinkedHashSet;

import javax.persistence.Cacheable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "T_ACT_TYPE")
// @NamedQueries({ @NamedQuery(name = "ActivityType.findByActivityName", query =
// "SELECT e FROM gabriel.atrack.model.ActivityType e WHERE e.activityName = ?")
// })
@Cacheable
public class ActivityType_ {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ACTIVITY_TYPE_ID")
	private long id;

	@Column(name = "ACTIVITY_TYPE_NAME", nullable = false, unique = true)
	private String activityName;

	@OneToMany(mappedBy = "activityType", cascade = CascadeType.ALL/*
																	 * , fetch=
																	 * FetchType
																	 * .EAGER
																	 */)
	private Collection<ActivityTypeDef_> typeDef = new LinkedHashSet<ActivityTypeDef_>();

	public ActivityType_() {
		// Requirement for JPA
	}

	public ActivityType_(final ActivityType dto) {
		setActivityName(dto.getActivityName());
		Collection<ActivityTypeDef> defDtos = dto.getTypeDef();
		for (ActivityTypeDef atdDto : defDtos) {
			typeDef.add(new ActivityTypeDef_(atdDto));
		}
	}

	public ActivityType_(Long id) {
		this.id = id;
	}

	public Collection<ActivityTypeDef_> getTypeDef() {
		return typeDef;
	}

	public void setTypeDef(Collection<ActivityTypeDef_> typeDef) {
		this.typeDef = typeDef;
	}

	public void setActivityName(final String activityName) {
		this.activityName = activityName;
	}

}

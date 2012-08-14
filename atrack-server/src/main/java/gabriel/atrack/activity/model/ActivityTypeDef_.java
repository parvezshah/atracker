package gabriel.atrack.activity.model;

import gabriel.atrack.dto.ActivityTypeDef;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "T_ACT_TYPE_DEF")
@Cacheable
public class ActivityTypeDef_ {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ACT_TYPE_DEF_ID")
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ACTIVITY_TYPE_ID")
	private ActivityType_ activityType;

	private String handle;

	public enum DATA_TYPE {
		STRING("t_str"), LONG("t_long");
		String code;

		DATA_TYPE(String code) {
			this.code = code;
		}

		public String getCode() {
			return code;
		}

		static DATA_TYPE getStatus(String code) {

			DATA_TYPE[] s = DATA_TYPE.values();
			for (DATA_TYPE t : s) {
				if (t.getCode().equals(code))
					return t;
			}
			return null;

		}
	}

	@Transient
	private DATA_TYPE dType;
	private String dataType;

	private Boolean isRequired;

	public ActivityTypeDef_(ActivityTypeDef atdDto) {
		setHandle(atdDto.getHandle());
		setIsRequired(atdDto.isRequired());
		setdType(DATA_TYPE.getStatus(atdDto.getDataType()));
	}

	public Long getId() {
		return id;
	}

	public ActivityType_ getActivityType() {
		return activityType;
	}

	public void setActivityType(ActivityType_ activityType) {
		this.activityType = activityType;
	}

	public String getHandle() {
		return handle;
	}

	public void setHandle(String handle) {
		this.handle = handle;
	}

	public Boolean getIsRequired() {
		return isRequired;
	}

	public void setIsRequired(Boolean isRequired) {
		this.isRequired = isRequired;
	}

	public void setdType(DATA_TYPE dType) {
		this.dType = dType;
		dataType = dType.getCode();
	}

	public DATA_TYPE getdType() {
		if (dType == null)
			dType = DATA_TYPE.getStatus(dataType);
		return dType;
	}

}

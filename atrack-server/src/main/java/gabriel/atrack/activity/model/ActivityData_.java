package gabriel.atrack.activity.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "T_ACTIVITY_DATA")
public class ActivityData_ {

	@Id
	@Column(name = "ACT_DATA_ID")
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ACT_ID")
	private Activity_ activity;

	@Column(nullable = false)
	private String handle;

	private String value;

	public ActivityData_() {
	}

	public ActivityData_(Activity_ activity) {
		this.activity = activity;

	}

	public void setHandle(String handle) {
		this.handle = handle;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	

}

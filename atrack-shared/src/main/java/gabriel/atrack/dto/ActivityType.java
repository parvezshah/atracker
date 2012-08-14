package gabriel.atrack.dto;

import java.util.Collection;
import java.util.LinkedHashSet;

public class ActivityType {

	private String activityName;
	private Collection<ActivityTypeDef> typeDef = new LinkedHashSet<ActivityTypeDef>();
	
	public String getActivityName() {
		return activityName;
	}

	public Collection<ActivityTypeDef> getTypeDef() {
		return typeDef;
	}

	public void setTypeDef(final Collection<ActivityTypeDef> typeDef) {
		this.typeDef = typeDef;
	}

	public void setActivityName(final String activityName) {
		this.activityName = activityName;
	} 
	
	
}

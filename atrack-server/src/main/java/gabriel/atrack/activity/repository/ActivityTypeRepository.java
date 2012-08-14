package gabriel.atrack.activity.repository;

import gabriel.atrack.activity.model.ActivityType_;

public interface ActivityTypeRepository {

	public ActivityType_ getActivityType(Long activitytCode);

	public void createActivityType(ActivityType_ at);

}

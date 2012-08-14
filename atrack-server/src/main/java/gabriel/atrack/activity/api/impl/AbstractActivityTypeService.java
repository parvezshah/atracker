package gabriel.atrack.activity.api.impl;

import gabriel.atrack.activity.api.ActivityTypeService;
import gabriel.atrack.activity.model.ActivityType_;
import gabriel.atrack.activity.repository.ActivityTypeRepository;
import gabriel.atrack.dto.ActivityType;

public abstract class AbstractActivityTypeService implements
		ActivityTypeService {

	@Override
	public void createActivityType(ActivityType activityType) {
		// TODO currently just setting it back in repository
		getActivityTypeRepository().createActivityType(
				new ActivityType_(activityType));

	}

	protected abstract ActivityTypeRepository getActivityTypeRepository();

}

package gabriel.atrack.activity.api.impl;

import gabriel.atrack.activity.api.ActivityTrackerServices;
import gabriel.atrack.activity.api.ActivityValidationService;
import gabriel.atrack.activity.model.Activity_;
import gabriel.atrack.activity.repository.ActivityRepository;
import gabriel.atrack.dto.Activity;

public abstract class AbstractActivityTrackerServices implements
		ActivityTrackerServices {

	@Override
	public void createActivity(final Activity activity) {
		Activity_ modelActivity = getActivityValidationService()
				.validateActivity(activity);
		getActivityRepository().saveActivity(modelActivity);

	}

	protected abstract ActivityValidationService getActivityValidationService();

	protected abstract ActivityRepository getActivityRepository();

}

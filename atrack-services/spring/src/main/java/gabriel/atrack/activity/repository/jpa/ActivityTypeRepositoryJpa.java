package gabriel.atrack.activity.repository.jpa;

import gabriel.atrack.activity.model.ActivityType_;
import gabriel.atrack.activity.repository.ActivityTypeRepository;
import gabriel.dao.jpa.GenericDao;

import org.springframework.stereotype.Component;

@Component("activityTypeRepositoryJpa")
public class ActivityTypeRepositoryJpa extends GenericDao<ActivityType_>
		implements ActivityTypeRepository {

	{// TODO: hack
		type = ActivityType_.class;
	}

	@Override
	public ActivityType_ getActivityType(Long activitytCode) {
		return super.find(activitytCode);

	}

	@Override
	public void createActivityType(ActivityType_ at) {
		super.create(at);
	}

}

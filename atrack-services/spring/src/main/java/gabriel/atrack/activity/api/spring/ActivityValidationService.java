package gabriel.atrack.activity.api.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import gabriel.atrack.acl.api.ACLService_;
import gabriel.atrack.activity.api.impl.AbstractActivityValidationService;
import gabriel.atrack.activity.model.Activity_;
import gabriel.atrack.activity.repository.ActivityTypeRepository;

@Component
public class ActivityValidationService extends
		AbstractActivityValidationService {

	@Autowired
	private ACLService_ aclService;

	@Autowired
	private ActivityTypeRepository activityTypeRepository;

	@Transactional(readOnly = true)
	@Override
	public Activity_ validateActivity(gabriel.atrack.dto.Activity activity) {
		return super.validateActivity(activity);
	}

	@Override
	protected ACLService_ getACLService() {
		return aclService;
	}

	@Override
	protected ActivityTypeRepository getActivityTypeRepository() {
		return activityTypeRepository;
	}

}

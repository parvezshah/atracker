package gabriel.atrack.activity.api.impl;

import gabriel.atrack.acl.api.ACLService_;
import gabriel.atrack.activity.api.ActivityValidationService;
import gabriel.atrack.activity.model.ActivityData_;
import gabriel.atrack.activity.model.ActivityType_;
import gabriel.atrack.activity.model.ActivityTypeDef_;
import gabriel.atrack.activity.repository.ActivityTypeRepository;
import gabriel.atrack.dto.Activity;
import gabriel.atrack.util.Assert;

import java.util.Collection;

public abstract class AbstractActivityValidationService implements
		ActivityValidationService {

	@Override
	public gabriel.atrack.activity.model.Activity_ validateActivity(
			final Activity activity) {
		gabriel.atrack.activity.model.Activity_ modelActivity = new gabriel.atrack.activity.model.Activity_();

		// check if the user is allowed to create activity
		ACLService_ aclService = getACLService();

		modelActivity.setInitiater(Assert.notNull(
				aclService.isSubjectActive(activity.getOwner()), ""));
		// check if activity code is there
		Long activitytCode = activity.getActivityCode();
		activitytCode = Assert.notNull(activitytCode, "");
		// get ActivityType from the code this also verifies that activityCode
		// is valid
		ActivityTypeRepository actTypeReo = getActivityTypeRepository();
		ActivityType_ actType = Assert.notNull(
				actTypeReo.getActivityType(activitytCode), "");
		// now set activity type
		modelActivity.setActivityType(new ActivityType_(activitytCode));

		Collection<ActivityTypeDef_> defes = actType.getTypeDef();
		// first condition input vales can be same or less then total acceptable
		// value
		if (activity.size() > defes.size()) {
			// TODO throw an exception
			System.out.println(" ERRROR COULD BE VERSION PROBLEM");
		}

		// iterate from Activity type to match syntax and parameter of the dto
		for (ActivityTypeDef_ typeDef : defes) {
			ActivityData_ activityData = /* new ActivityData(modelActivity); */new ActivityData_();
			modelActivity.getActivityData().add(activityData);

			String handle = typeDef.getHandle();
			activityData.setHandle(handle);

			switch (typeDef.getdType()) {
			case STRING:
				if (typeDef.getIsRequired()) {
					// check if present
					String value = Assert.notNullString(
							activity.getString(handle), "TODO");
					// add the value
					activityData.setValue(value);

				} else {
					// check if accidently added in Long
					Assert.isNull(activity.getLong(handle), "TODO");
				}
				break;
			case LONG:
				if (typeDef.getIsRequired()) {
					// check if present
					Long value = activity.getLong(handle);
					Assert.notNull(value, "TODO");
					// add the value
					activityData.setValue(String.valueOf(value));
				} else {
					// check if accidently added in String
					Assert.isNull(activity.getString(handle), "TODO");
				}
				break;
			}
			// activity.
		}

		System.out.println("VALIDATION DONE");
		return modelActivity;
	}

	protected abstract ACLService_ getACLService();

	protected abstract ActivityTypeRepository getActivityTypeRepository();

}

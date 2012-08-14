package gabriel.atrack.activity.api;

import gabriel.atrack.dto.Activity;

public interface ActivityValidationService {

	gabriel.atrack.activity.model.Activity_ validateActivity(Activity activity);
}

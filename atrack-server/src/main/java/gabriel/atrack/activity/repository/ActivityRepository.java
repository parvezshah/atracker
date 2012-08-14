package gabriel.atrack.activity.repository;

import java.util.List;

import gabriel.atrack.activity.model.Activity_;

public interface ActivityRepository {

	void saveActivity(final Activity_ activity);

	// batch save
	void saveActivity(final Activity_[] activity);

	List<Activity_> getActivity(Long ts, Integer maxRowCount);

}

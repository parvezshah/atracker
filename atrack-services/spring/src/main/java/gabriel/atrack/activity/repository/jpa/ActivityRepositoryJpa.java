package gabriel.atrack.activity.repository.jpa;

import java.util.Collection;
import java.util.List;

import gabriel.atrack.activity.model.Activity_;
import gabriel.atrack.activity.model.ActivityData_;
import gabriel.atrack.activity.repository.ActivityRepository;
import gabriel.dao.jpa.GenericDao;
import gabriel.util.UUIDGen;

import org.springframework.stereotype.Repository;

@Repository("activityRepositoryJpa")
public class ActivityRepositoryJpa extends GenericDao<Activity_> implements
		ActivityRepository {

	@Override
	public void saveActivity(Activity_ activity) {
		throw new UnsupportedOperationException("");
	}

	@Override
	public void saveActivity(Activity_[] activity) {
		// TODO not the right place
		// set the id on activity
		for (Activity_ act : activity) {
			act.setId(UUIDGen.getUUID());
			act.setTimeStamp(System.currentTimeMillis());
			Collection<ActivityData_> col = act.getActivityData();
			for (ActivityData_ activityData : col) {
				activityData.setId(UUIDGen.getUUID());
			}
		}
		super.createBatch(activity);

	}

	@Override
	public List<Activity_> getActivity(Long ts, Integer maxRowCount) {
		
		if(ts == null || ts == -1)
			return super.findAll("Activity.findTopActivity", null, maxRowCount);
			else
		return super.findAll("Activity.findTopActivity_filter_by_Timestamp", null, maxRowCount, ts);
	}

}

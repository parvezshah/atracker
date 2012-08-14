package gabriel.atrack.activity.repository.si;

import gabriel.atrack.activity.model.Activity_;
import gabriel.atrack.activity.repository.ActivityRepository;

import java.util.List;

import javax.sql.rowset.spi.SyncResolver;

import net.spy.memcached.MemcachedClient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public class ActivityRepositoryMemCache implements ActivityRepository {

	@Autowired
	@Qualifier("memcached.client")
	private MemcachedClient memcached;
	
	private final static String KEY = "Current_Activity_Cache"; 

	@Autowired
	@Qualifier
	private ActivityRepository activityRepositoryDAO;

	@Override
	public void saveActivity(Activity_ activity) {
		List<Activity_> activities = (List<Activity_>) memcached.get(KEY);
		if(activities == null)
		{
			synchronized (this) {
				activities = (List<Activity_>) memcached.get(KEY);
				if(activities == null)
				{
					activities= activityRepositoryDAO.getActivity(null, 1_00_000);
					memcached.add(KEY,2000, activities);
				}
			}
		
			
		}
		activities.add(activity);
		
		//memcached.add(key, exp, o)

	}

	@Override
	public void saveActivity(Activity_[] activity) {
		throw new UnsupportedOperationException("saveActivity");

	}

	@Override
	public List<Activity_> getActivity(Long ts, Integer size) {
		// TODO Auto-generated method stub
		return activityRepositoryDAO.getActivity(ts, size);
	}

}

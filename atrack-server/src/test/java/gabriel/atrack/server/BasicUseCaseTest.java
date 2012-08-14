package gabriel.atrack.server;

import gabriel.atrack.activity.api.ActivityTrackerServices;
import gabriel.atrack.activity.api.ActivityValidationService;
import gabriel.atrack.activity.api.impl.AbstractActivityTrackerServices;
import gabriel.atrack.activity.repository.ActivityRepository;
import gabriel.atrack.dto.Activity;

import java.security.Principal;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class BasicUseCaseTest {

	ActivityTrackerServices tracker;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {

		tracker = new AbstractActivityTrackerServices() {


			@Override
			protected ActivityRepository getActivityRepository() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			protected ActivityValidationService getActivityValidationService() {
				// TODO Auto-generated method stub
				return null;
			}

			
		};
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {

		final Activity jog = new Activity();

	//	Principal user = null;

		jog.setOwner(10000L);
		jog.setData("timing", 4820L);
		jog.setData("distance", 1000L);

		tracker.createActivity(jog);

		// tracker.
	}

}

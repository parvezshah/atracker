/**
 * 
 */
package gabriel.atrack;

import java.util.Collection;

import gabriel.atrack.acl.api.ACLService;
import gabriel.atrack.acl.api.ACLService_;
import gabriel.atrack.acl.api.impl.AbstractACLService;
import gabriel.atrack.acl.dto.Principal;
import gabriel.atrack.acl.dto.Subject;
import gabriel.atrack.acl.repository.ACLRepository;
import gabriel.atrack.activity.api.ActivityTrackerServices;
import gabriel.atrack.activity.api.ActivityTypeService;
import gabriel.atrack.activity.api.ActivityValidationService;
import gabriel.atrack.activity.api.impl.AbstractActivityTrackerServices;
import gabriel.atrack.activity.api.impl.AbstractActivityTypeService;
import gabriel.atrack.activity.api.impl.AbstractActivityValidationService;
import gabriel.atrack.activity.model.Activity_;
import gabriel.atrack.activity.repository.ActivityRepository;
import gabriel.atrack.activity.repository.ActivityTypeRepository;
import gabriel.atrack.activity.repository.jpa.ACLRepositoryJpa;
import gabriel.atrack.activity.repository.jpa.ActivityRepositoryJpa;
import gabriel.atrack.activity.repository.jpa.ActivityTypeRepositoryJpa;
import gabriel.atrack.dto.Activity;
import gabriel.atrack.dto.ActivityType;
import gabriel.atrack.dto.ActivityTypeDef;
import gabriel.atrack.mailbox.dto.ActionItem;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import junit.framework.Assert;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class UsecaseTests {

	private Long activityCode;

	private ActivityTrackerServices atrs;
	private ActivityTypeService ats;
	private ActivityValidationService avs;
	private ActivityTypeRepository atr;
	private ActivityRepository ar;
	private ACLService acs;
	private ACLRepository acr;

	private EntityManager em;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {

		EntityManagerFactory emf = Persistence
				.createEntityManagerFactory("TEST_ACTIVITY_PU");
		em = emf.createEntityManager();

		atr = new ActivityTypeRepositoryJpa();
		{
			((ActivityTypeRepositoryJpa) atr).setEntityManager(em);
		}
		acr = new ACLRepositoryJpa();
		{
			((ACLRepositoryJpa) acr).setEntityManager(em);
		}
		ar = new ActivityRepositoryJpa() {
			@Override
			public void saveActivity(Activity_ activity) {
				super.saveActivity(new Activity_[] { activity });
			}
		};
		{
			((ActivityRepositoryJpa) ar).setEntityManager(em);
		}

		acs = new AbstractACLService() {

			@Override
			protected ACLRepository getAclRepository() {
				return acr;
			}
		};

		avs = new AbstractActivityValidationService() {

			@Override
			protected ActivityTypeRepository getActivityTypeRepository() {
				return atr;
			}

			@Override
			protected ACLService_ getACLService() {
				return (ACLService_) acs;
			}
		};
		atrs = new AbstractActivityTrackerServices() {

			@Override
			protected ActivityValidationService getActivityValidationService() {
				return avs;
			}

			@Override
			protected ActivityRepository getActivityRepository() {
				return ar;
			}
		};

		ats = new AbstractActivityTypeService() {

			@Override
			protected ActivityTypeRepository getActivityTypeRepository() {
				return atr;
			}
		};

	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testRe() {
		// create Subject
		em.getTransaction().begin();
		createSubject(new Principal("parvezshah"));
		em.getTransaction().commit();

		em.getTransaction().begin();
		createSubject(new Principal("jsr"));
		em.getTransaction().commit();

		em.getTransaction().begin();
		makeConnection(new Principal("jsr"), new Principal("parvezshah"),
				"friend");
		em.getTransaction().commit();

		{
			em.getTransaction().begin();
			Collection<Principal> connections = getConnections(new Principal(
					"jsr"));
			em.getTransaction().commit();
			Assert.assertTrue(0 == connections.size());
		}

		em.getTransaction().begin();
		Collection<ActionItem> items = getActionItem(new Principal("parvezshah"));
		em.getTransaction().commit();
		Assert.assertTrue(1 == items.size());
		ActionItem actionItem = items.iterator().next();

		actionItem.setStatus("Approve");
		updateOrCreateActionItem(actionItem);

		{
			em.getTransaction().begin();
			Collection<Principal> connections = getConnections(new Principal(
					"jsr"));
			em.getTransaction().commit();
			Assert.assertTrue(1 == connections.size());
		}
	}

	@Test
	public void test() {
		em.getTransaction().begin();
		// create Principal
		createSubject(new Principal("parvezshah"));
		em.getTransaction().commit();

		em.getTransaction().begin();
		// create Activity Type
		createActivityType();
		em.getTransaction().commit();

		em.getTransaction().begin();
		// create Activity Data
		createActivityData();
		em.getTransaction().commit();
	}

	private void createSubject(Principal principal) {

		acs.createSubject(principal);
	}

	private void createActivityType() {
		ActivityType at = new ActivityType();
		at.setActivityName("jogging");
		ActivityTypeDef atd = new ActivityTypeDef();
		atd.setHandle("someString");
		atd.setDataType("t_str");
		atd.setRequired(Boolean.TRUE);

		at.getTypeDef().add(atd);

		atd = new ActivityTypeDef();
		atd.setHandle("someLong");
		atd.setDataType("t_long");
		atd.setRequired(Boolean.TRUE);

		at.getTypeDef().add(atd);

		atd = new ActivityTypeDef();
		atd.setHandle("notneeded");
		atd.setDataType("t_long");
		atd.setRequired(Boolean.FALSE);

		at.getTypeDef().add(atd);
		ats.createActivityType(at);

	}

	private void createActivityData() {
		// create Activity Data with information from state field
		Activity dtoActivity = new Activity();
		dtoActivity.setActivityCode(1L);
		dtoActivity.setData("someString", "string value");
		dtoActivity.setData("someLong", 1000L);
		dtoActivity.setOwner(1L);

		atrs.createActivity(dtoActivity);

	}

}

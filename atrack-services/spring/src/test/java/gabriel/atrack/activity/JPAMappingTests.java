package gabriel.atrack.activity;

import gabriel.atrack.acl.model.Subject_;
import gabriel.atrack.activity.model.ActivityData_;
import gabriel.atrack.activity.model.ActivityType_;
import gabriel.atrack.activity.model.Activity_;
import gabriel.atrack.activity.repository.ActivityRepository;
import gabriel.util.UUIDGen;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

//@ContextConfiguration
//@RunWith(SpringJUnit4ClassRunner.class)
public class JPAMappingTests {

	@PersistenceContext
	private EntityManager entityManager;

	@Autowired
	@Qualifier(value = "activityRepositoryJpa")
	private ActivityRepository activityRepository;

	 @Test
	@Transactional
	public void createSubject() {
		Subject_ one = new Subject_();
		entityManager.persist(one);
		entityManager.flush();

	}

	@Test
	@Transactional
	public void testSaveOrderWithItems() throws Exception {

		Activity_ activity = new Activity_();
		activity.setActivityType(new ActivityType_(100L));
		activity.setInitiater(new Subject_());
		ActivityData_ ad = new ActivityData_();
		// activity.getActivityData().add(ad);
		ad.setHandle("someString");
		ad.setValue("some value");
		ad.setHandle("someLong");

		ad.setValue(String.valueOf(1000L));

		activity.setId(UUIDGen.getUUID());
		ad.setId(UUIDGen.getUUID());
		entityManager.persist(activity);
		entityManager.flush();

		// activityRepository.saveActivity(new Activity[] { activity });
		/*
		 * Order order = new Order(); order.getItems().add(new Item());
		 * entityManager.persist(order); entityManager.flush();
		 * assertNotNull(order.getId());
		 */
	}
	/*
	 * @Test
	 * 
	 * @Transactional public void testSaveAndGet() throws Exception { Order
	 * order = new Order(); order.getItems().add(new Item());
	 * entityManager.persist(order); entityManager.flush(); // Otherwise the
	 * query returns the existing order (and we didn't set the // parent in the
	 * item)... entityManager.clear(); Order other = (Order)
	 * entityManager.find(Order.class, order.getId()); assertEquals(1,
	 * other.getItems().size()); assertEquals(other,
	 * other.getItems().iterator().next().getOrder()); }
	 * 
	 * @Test
	 * 
	 * @Transactional public void testSaveAndFind() throws Exception { Order
	 * order = new Order(); Item item = new Item(); item.setProduct("foo");
	 * order.getItems().add(item); entityManager.persist(order);
	 * entityManager.flush(); // Otherwise the query returns the existing order
	 * (and we didn't set the // parent in the item)... entityManager.clear();
	 * Order other = (Order) entityManager .createQuery(
	 * "select o from Order o join o.items i where i.product=:product")
	 * .setParameter("product", "foo").getSingleResult(); assertEquals(1,
	 * other.getItems().size()); assertEquals(other,
	 * other.getItems().iterator().next().getOrder()); }
	 */

}

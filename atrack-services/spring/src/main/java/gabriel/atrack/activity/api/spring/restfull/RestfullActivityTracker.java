package gabriel.atrack.activity.api.spring.restfull;

import gabriel.atrack.activity.api.ActivityValidationService;
import gabriel.atrack.activity.api.impl.AbstractActivityTrackerServices;
import gabriel.atrack.activity.repository.ActivityRepository;
import gabriel.atrack.dto.Activity;
import gabriel.web.exception.WebIllegalArgumentException;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/activity/")
public class RestfullActivityTracker extends AbstractActivityTrackerServices {

	@Autowired
	@Qualifier(value="activityRepositoryImpl")
	private ActivityRepository activityRepository;

	@Autowired
	private ActivityValidationService activityValidationService;

	@RequestMapping(consumes = "application/json", produces = "application/json", method = RequestMethod.POST, value = "/create")
	public ResponseEntity<String> createActivityFromJaso(
			@RequestBody final Activity activity) {
		System.out.println("STARTED THE MAGIC ");

		try {
			super.createActivity(activity);
			HttpHeaders headers = new HttpHeaders();
			headers.add("Content-Type", "application/json");
			return new ResponseEntity<String>(headers, HttpStatus.CREATED);
		} catch (IllegalArgumentException e) {
			throw new WebIllegalArgumentException(e);
		}
	}

	@RequestMapping(produces = "application/json", method = RequestMethod.GET, value = "/names")
	public ResponseEntity<List<Book>> getNames() {
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "application/json");
		return new ResponseEntity<List<Book>>(returnData(), headers,
				HttpStatus.OK);
	}

	class Book {

		private Integer id;
		private String name;

		public void setId(Integer id) {
			this.id = id;
		}

		public Integer getId() {
			return id;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getName() {
			return name;
		}

	}

	private List<Book> returnData() {

		ArrayList<Book> names = new ArrayList<Book>();

		Book b1 = new Book();
		Book b2 = new Book();
		Book b3 = new Book();

		b1.setName("book nro. 1");
		b1.setId(0);
		b2.setName("book nro. 2");
		b2.setId(1);
		b3.setName("book nro. 3");
		b3.setId(2);

		names.add(b1);
		names.add(b2);
		names.add(b3);

		return names;
	}

	@Override
	protected ActivityValidationService getActivityValidationService() {
		return activityValidationService;
	}

	@Override
	protected ActivityRepository getActivityRepository() {
		return activityRepository;
	}

}

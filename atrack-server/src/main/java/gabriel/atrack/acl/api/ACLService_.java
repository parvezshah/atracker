package gabriel.atrack.acl.api;

import gabriel.atrack.acl.model.Subject_;

public interface ACLService_ extends ACLService {

	Subject_ isSubjectActive(Long owner);

	

}

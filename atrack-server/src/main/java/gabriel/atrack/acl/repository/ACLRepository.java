package gabriel.atrack.acl.repository;

import gabriel.atrack.acl.model.Subject_;

public interface ACLRepository {

	Subject_ findSubject(Long owner);

	void createSubject(Subject_ subject_);

}

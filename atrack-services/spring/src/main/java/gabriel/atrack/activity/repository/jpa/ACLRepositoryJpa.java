package gabriel.atrack.activity.repository.jpa;

import gabriel.atrack.acl.model.Subject_;
import gabriel.atrack.acl.repository.ACLRepository;
import gabriel.dao.jpa.GenericDao;

import org.springframework.stereotype.Repository;

@Repository("aclRepositoryJpa")
public class ACLRepositoryJpa extends GenericDao<Subject_> implements
		ACLRepository {

	{//TODO: hack
		type = Subject_.class;		
	}
	@Override
	public Subject_ findSubject(final Long id) {
		return super.find(id);
	}

	@Override
	public void createSubject(final Subject_ subject) {
		super.create(subject);
	}

}

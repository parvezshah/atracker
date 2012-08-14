package gabriel.atrack.acl.api.spring;

import gabriel.atrack.acl.api.impl.AbstractACLService;
import gabriel.atrack.acl.repository.ACLRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class ACLService extends AbstractACLService {

	@Autowired
	@Qualifier("aclRepositoryJpa")
	private ACLRepository aclRepository;

	@Override
	protected ACLRepository getAclRepository() {
		return aclRepository;
	}

}

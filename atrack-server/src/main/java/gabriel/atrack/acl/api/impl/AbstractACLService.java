package gabriel.atrack.acl.api.impl;

import gabriel.atrack.acl.api.ACLService_;
import gabriel.atrack.acl.dto.Principal;
import gabriel.atrack.acl.model.Principal_;
import gabriel.atrack.acl.model.Subject_;
import gabriel.atrack.acl.repository.ACLRepository;

public abstract class AbstractACLService implements ACLService_ {

	@Override
	public Subject_ isSubjectActive(Long owner) {
		// TODO for now just check if user is present in the db
		return getAclRepository().findSubject(owner);

	}

	@Override
	public void createSubject(Principal principal) {
		// TODO some check is needed to find if principal can be added
		Subject_ sub = new Subject_();
		sub.getPrincipals().add(new Principal_(principal));

		getAclRepository().createSubject(sub);
	}

	protected abstract ACLRepository getAclRepository();

}

package gabriel.atrack.acl.model;

import gabriel.atrack.model.Entity;

import java.util.Collection;
import java.util.HashSet;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@javax.persistence.Entity
@Table(name = "T_SUBJECT")
public class Subject_ implements Entity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "SUBJECT_ID")
	private Long id;

	@OneToMany(mappedBy = "subject", cascade = CascadeType.PERSIST)
	private Collection<Principal_> principals = new HashSet<Principal_>();

	public Subject_() {
		// For JPA
	}

	@Override
	public Long getEntityID() {
		return id;
	}

	@Override
	public ENTITY_TYPE getEntityType() {
		return ENTITY_TYPE.SUBJECT;
	}

	public Collection<Principal_> getPrincipals() {
		return principals;
	}

}

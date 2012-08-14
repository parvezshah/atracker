package gabriel.atrack.acl.model;

import gabriel.atrack.acl.dto.Principal;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@javax.persistence.Entity
@Table(name = "T_PRINCIPAL")
public class Principal_ {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "PRINCIPAL_ID")
	private Long id;
	
	@Column(name = "APP_UID",unique=true,updatable=false)
	private String uid;
	
	
	public Principal_(Principal principal) {
		uid = principal.getUid();
	}

	@ManyToOne(fetch=FetchType.EAGER)
	private Subject_ subject;

}

package gabriel.atrack.acl.dto;

import java.util.List;
import java.util.Map;

public class Principal {

	private String uid;
	
	private Map<String, List<Principal>> connections;

	public Principal(final String uid) {
		this.uid = uid;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}
	
	

}

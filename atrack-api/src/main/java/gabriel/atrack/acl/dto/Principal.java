package gabriel.atrack.acl.dto;

public class Principal {

	private String uid;

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

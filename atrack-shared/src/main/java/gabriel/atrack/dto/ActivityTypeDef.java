package gabriel.atrack.dto;

public class ActivityTypeDef {

	private String handle;
	private Boolean required;
	private String dataType;

	public String getHandle() {
		return handle;
	}

	public Boolean isRequired() {
		return required;
	}

	public void setRequired(final Boolean required) {
		this.required = required;
	}

	public String getDataType() {
		return dataType;
	}

	public void setHandle(final String handle) {
		this.handle = handle;
	}

	public void setDataType(final String dataType) {
		this.dataType = dataType;

	}

}

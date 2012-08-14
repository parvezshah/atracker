package gabriel.atrack.dto;

import java.util.HashMap;

public class Activity {

	private Long owner;
	private Long activityCode;
	private Long creationTime;
	private HashMap<String, String> dataString = new HashMap<String, String>();
	private HashMap<String, Long> dataLong = new HashMap<String, Long>();

	public Long getOwner() {
		return owner;
	}

	public void setOwner(final Long owner) {
		this.owner = owner;
	}

	public Long getActivityCode() {
		return activityCode;
	}

	public void setActivityCode(final Long activityCode) {
		this.activityCode = activityCode;
	}

	public void setData(final String key, final Long value) {
		dataLong.put(key, value);
	}

	public void setData(final String key, final String value) {
		dataString.put(key, value);
	}

	public Long getLong(String key) {
		return dataLong.get(key);
	}

	public String getString(String key) {
		return dataString.get(key);
	}

	public int size() {
		return dataString.size() + dataLong.size();
	}

	// /------------Only used by jackson
	public HashMap<String, String> getDataString() {
		return dataString;
	}

	public void setDataString(HashMap<String, String> dataString) {
		this.dataString = dataString;
	}

	public HashMap<String, Long> getDataLong() {
		return dataLong;
	}

	public void setDataLong(HashMap<String, Long> dataLong) {
		this.dataLong = dataLong;
	}

	public Long getCreationTime() {
		return creationTime;
	}

	public void setCreationTime(Long creationTime) {
		this.creationTime = creationTime;
	}

}
